package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import test.GSon.LoginCred;

public class LoginFlow {
    private final AppiumDriver<MobileElement> appiumDriver;
    private LoginCred loginCredData;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFlow setData(LoginCred loginCredData){
        this.loginCredData = loginCredData;
        return this;
    }

    public LoginFlow login(){
        if(loginCredData == null)
            throw  new RuntimeException("Please call setData method first");
        LoginPage loginPage = new LoginPage(appiumDriver);
        loginPage.bottomNavigationComponent().clickOnLoginLabel();
        loginPage
                .inputUsername(loginCredData.getUsername())
                .inputPassword(loginCredData.getPassword())
                .clickOnLoginBtn();
        return this;
    }

    public void verifyLogin(){
        boolean isEmailInvalid = isEmailInvalid(loginCredData.getUsername());
        boolean isPasswordInvalid = isPasswordInvalid(loginCredData.getPassword());
        if(isEmailInvalid)
            verifyInvalidEmailFormat();
        if(isPasswordInvalid)
            verifyInvalidPasswordFormat();
        if(!isEmailInvalid && !isPasswordInvalid)
            verifyLoginSuccess();
    }

    private void verifyLoginSuccess() {
    }

    private void verifyInvalidPasswordFormat() {
    }

    private void verifyInvalidEmailFormat() {
    }

    private boolean isPasswordInvalid(String password) {
        return true;
    }

    private boolean isEmailInvalid(String username) {
        return true;
    }
}
