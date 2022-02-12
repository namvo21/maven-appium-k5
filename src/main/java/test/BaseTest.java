package test;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import io.qameta.allure.Allure;
import org.testng.annotations.Optional;

public class BaseTest {

    private AppiumDriver<MobileElement> appiumDriver;

    private final List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private ThreadLocal<DriverFactoryEx> driverThread;

    private String udid;
    private String systemPort;
    private String platformName;
    private String platformVersion;

    @BeforeTest(alwaysRun = true, description = "Init all appium sessions")
    @Parameters({"udid", "systemPort", "platformName", "platformVersion"})
    public void beforeTest(String udid, String systemPort, String platformName, @Optional("platformVersion") String platformVersion){
        this.udid = udid;
        this.systemPort = systemPort;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        driverThread.get().quitAppiumSession();
    }

    protected AppiumDriver<MobileElement> getAndroidDriver(){
        if(appiumDriver == null){
            appiumDriver = driverThread.get().getAndroidDriver(udid, systemPort, platformName, platformVersion);
        }
        if(appiumDriver == null){
            throw new RuntimeException("[ERR] Cannot establish connection");
        }
        return appiumDriver;
    }

    @AfterMethod(description = "Capture Screenshot on failure")
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){

            // 1. Get the test method name
            String testMethodName = result.getName();

            // 2. Get taken time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String dateTaken = y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec;

            // 3. Location to save
            String fileLocation =
                    System.getProperty("user.dir") + "/screenshots/" + testMethodName + "_" + dateTaken + ".png";

            // 4. save
            File screenShot = driverThread.get().getAndroidDriver().getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShot, new File(fileLocation));

                // Get file content as InputStream to attach to allure
                Path content = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(content);
                Allure.addAttachment(testMethodName, inputStream);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}