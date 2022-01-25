package test.authentication;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test.GSon.LoginCred;
import test_flows.authentication.LoginFlow;
import utils.data.DataObjectBuilder;

public class LoginTestwithBaseTest1 extends BaseTest {

    @Test
    public void loginValidCreds() {
        String jsonLoc = "/src/main/resources/test-data/ValidLoginCred.json";
        LoginCred loginCredData =  DataObjectBuilder.buildDataObject(jsonLoc,LoginCred.class);

        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();

        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.setData(loginCredData).login().verifyLogin();
        //Assert.fail();
    }

    @Test
    public void testMethod02(){
        LoginPage loginPage = new LoginPage(getAndroidDriver());
        loginPage.bottomNavigationComponent().clickOnLoginLabel();
        //Assert.fail();
    }
}
