package test;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
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

public class BaseTest {

    private DriverFactoryEx driverFactory;
    private AppiumDriver<MobileElement> appiumDriver;

    private final List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private ThreadLocal<DriverFactoryEx> driverThread;
    private String udid;
    private String systemPort;

    /*@BeforeClass(alwaysRun = true)
    public void beforeClass(){
        driverFactory = new DriverFactoryEx();
    }*/

    /*@AfterClass(alwaysRun = true)
    public void afterClass(){

        driverFactory.quitAppiumSession();
    }*/

    @BeforeTest(alwaysRun = true, description = "Init all appium session")
    @Parameters({"udid", "systemPort"})
    public void beforeTest(String udid, String systemPort){
        this.udid = udid;
        this.systemPort = systemPort;
        driverThread = ThreadLocal.withInitial(() ->{
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        driverThread.get().quitAppiumSession();
    }

    /*protected AppiumDriver<MobileElement> getAndroidDriver(){
        if(appiumDriver == null){
            appiumDriver = driverFactory.getAndroidDriver();
        }
        return appiumDriver;
    }*/

    protected AppiumDriver<MobileElement> getAndroidDriver(){
        if(appiumDriver == null){
            appiumDriver = driverThread.get().getAndroidDriver(udid, systemPort);
        }
        if(appiumDriver == null){
            throw new RuntimeException("[ERR] Cannot establish a connection!!!");
        }
        return appiumDriver;
    }

    @AfterMethod(description = "Capture Screenshot on failure")
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            //1. Get Test Method name
            String methodName= result.getName();

            //2. Get taken time
            Calendar calendar = new GregorianCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);

            String dateTaken = year + "-" + month + "-" + day + "-" + hour + "-" + min + "-" + sec;

            //3. Location to save
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + dateTaken + ".png";

            //4. Save
            File screenshot = driverThread.get().getAndroidDriver().getScreenshotAs(OutputType.FILE);

            try{
                FileUtils.copyFile(screenshot, new File(fileLocation));
                Path content = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(content);
                Allure.addAttachment(methodName, inputStream);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

