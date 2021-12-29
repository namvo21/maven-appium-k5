package test;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;

public class LoginFormTest {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            LoginPage loginPage = new LoginPage(androidDriver);
            loginPage.bottomNavigationComponent().clickOnLoginLabel();
            loginPage
                    .inputUsername("vhnam2110@gmail.com")
                    .inputPassword("12345678910")
                    .clickOnLoginBtn();
        } finally {
            androidDriver.quit();
        }
    }
}
