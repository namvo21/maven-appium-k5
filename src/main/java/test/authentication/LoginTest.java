package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.GSon.LoginCred;
import test_flows.authentication.LoginFlow;
import utils.data.DataObjectBuilder;

import java.util.Arrays;
import java.util.List;

public class LoginTest extends BaseTest{
    /*static {
        loginCredData = Arrays.asList(new LoginCred("",""), new LoginCred("vhnam2110@gmail.com","123456"));
    }

    @Test
    public void loginwithInvalidCreds() {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        for (LoginCred loginCred : loginCredData) {
            LoginPage loginPage = new LoginPage(androidDriver);
            loginPage.bottomNavigationComponent().clickOnLoginLabel();
            loginPage
                    .inputUsername(loginCred.getUsername())
                    .inputPassword(loginCred.getPassword())
                    .clickOnLoginBtn();
        }
    }*/

    @Test()
    public void loginwithValidCreds() {
        String jsonLoc = "/src/main/resources/test-data/ValidLoginCred.json";
        LoginCred loginCredData =  DataObjectBuilder.buildDataObject(jsonLoc,LoginCred.class);

        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.setData(loginCredData).login().verifyLogin();
    }

    @Test(dataProvider = "invalidLoginData")
    public void loginwithInvalidCreds(LoginCred loginCredData) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        LoginPage loginPage = new LoginPage(androidDriver);
        loginPage.bottomNavigationComponent().clickOnLoginLabel();
        loginPage
                .inputUsername(loginCredData.getUsername())
                .inputPassword(loginCredData.getPassword())
                .clickOnLoginBtn();
    }

    @DataProvider()
    public LoginCred[] invalidLoginData(){
        String jsonLoc = "/src/main/resources/test-data/InvalidLoginCred.json";
        return DataObjectBuilder.buildDataObject(jsonLoc,LoginCred[].class);
    }
}
