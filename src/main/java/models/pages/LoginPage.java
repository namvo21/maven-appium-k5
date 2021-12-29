package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;

public class LoginPage {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    // Return found element(s)
    private MobileElement usernameElem(){
        return appiumDriver.findElement(usernameSel);
    }

    public LoginPage inputUsername(String userName){
        usernameElem().sendKeys(userName);
        return this;
    }

    public LoginPage inputPassword(String password){
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public void clickOnLoginBtn(){
        appiumDriver.findElement(loginBtnSel).click();
    }

    public BottomNavigationComponent bottomNavigationComponent(){
        return new BottomNavigationComponent(appiumDriver);
    }

}
