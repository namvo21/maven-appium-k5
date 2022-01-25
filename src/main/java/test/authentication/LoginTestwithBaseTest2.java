package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test.GSon.LoginCred;
import test_flows.authentication.LoginFlow;
import utils.data.DataObjectBuilder;

public class LoginTestwithBaseTest2 extends BaseTest {

    @Test()
    public void loginwithValidCreds() {
        String jsonLoc = "/src/main/resources/test-data/ValidLoginCred.json";
        LoginCred loginCredData =  DataObjectBuilder.buildDataObject(jsonLoc,LoginCred.class);

        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();

        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.setData(loginCredData).login().verifyLogin();
        //Assert.fail();
    }
}
