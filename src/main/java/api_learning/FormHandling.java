package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FormHandling {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            // Click on Login label
            MobileElement loginLabelElement = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElement.click();

            //Finding elements | Extendable, Maintainable, Reusable --> Reliable
            MobileElement usernameElement = androidDriver.findElementByAccessibilityId("input-email");
            MobileElement passwordElement = androidDriver.findElementByAccessibilityId("input-password");
            MobileElement loginButtonElement = androidDriver.findElementByAccessibilityId("button-LOGIN");

            // Input username
            usernameElement.sendKeys("teo");

            // Input password
            passwordElement.sendKeys("12345678910");

            // Click on Login Button
            loginButtonElement.click();

            //Clear
            usernameElement.clear();
            usernameElement.sendKeys("vhnam2110@gmail.com");
            loginButtonElement.click();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            androidDriver.quit();
        }
    }
}
