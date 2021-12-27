package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HandleMultipleApps {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            MobileElement loginLabelElement = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElement.click();

            // Go to login form -> Input correct creds then login
            MobileElement usernameElement = androidDriver.findElementByAccessibilityId("input-email");
            MobileElement passwordElement = androidDriver.findElementByAccessibilityId("input-password");
            MobileElement loginButtonElement = androidDriver.findElementByAccessibilityId("button-LOGIN");
            usernameElement.sendKeys("teo@sth.com");
            passwordElement.sendKeys("87654321");
            loginButtonElement.click();

            // Put app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Open Settings -> Handle Wifi
            androidDriver.activateApp("com.android.settings");
            androidDriver.findElementByXPath("//*[@text='Connections']").click();
            androidDriver.findElementByXPath("//*[@text='Wi-Fi']").click();
            MobileElement wifiSwitchBtnElem = androidDriver.findElement(By.id("com.android.settings:id/switch_widget"));
            boolean isWifiOn = wifiSwitchBtnElem.getText().equals("ON");

            wifiSwitchBtnElem.click();
            if(isWifiOn){
                wifiSwitchBtnElem.click();
            }

            // Open the test app again
            androidDriver.activateApp("com.wdiodemoapp");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            androidDriver.quit();
        }
    }
}
