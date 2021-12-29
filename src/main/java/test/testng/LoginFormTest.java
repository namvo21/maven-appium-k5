package test.testng;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginFormTest {

    @Test
    public void loginWithValidCreds() {
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

    @Test
    public void doSth(){
        System.out.println("Do Sth");
    }
}
