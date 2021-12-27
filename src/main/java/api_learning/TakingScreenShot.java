package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TakingScreenShot {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabelElement = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElement.click();

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 10L);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("button-LOGIN")));

            // Taking screenshot | Whole screen
            File base64ScreenshotData = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/srceenshots/").concat("LoginForm.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            // Taking screenshot on an element
            MobileElement loginBtnElemenet = androidDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            File base64LoginBtnData = loginBtnElemenet.getScreenshotAs(OutputType.FILE);
            String loginBtnFileLocation = System.getProperty("user.dir").concat("/srceenshots/").concat("LoginButton.png");
            FileUtils.copyFile(base64LoginBtnData, new File(loginBtnFileLocation));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
