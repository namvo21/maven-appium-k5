package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Map;

public class LoginPage {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Success\"")
    private MobileElement msgTitleElem;


    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(10)), this);
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

    public String loginMsgText(){
        Capabilities caps = this.appiumDriver.getCapabilities();
        String platform = CapabilityHelpers.getCapability(caps, "platformName", String.class);
        System.out.println(platform);
        return msgTitleElem.getText();
    }

    public BottomNavigationComponent bottomNavigationComponent(){
        return new BottomNavigationComponent(appiumDriver);
    }

}
