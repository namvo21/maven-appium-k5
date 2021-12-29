package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavigationComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By loginLabelSel = MobileBy.AccessibilityId("Login");

    public BottomNavigationComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void clickOnLoginLabel(){
        appiumDriver.findElement(loginLabelSel).click();
    }
}
