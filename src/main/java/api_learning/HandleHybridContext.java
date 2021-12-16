package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleHybridContext {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            // Click on Login label
            MobileElement webViewLabelElement = androidDriver.findElementByAccessibilityId("Webview");
            webViewLabelElement.click();

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 10L);
            webDriverWait.until(moreThanOneContext(androidDriver));

            androidDriver.getContextHandles().forEach(context -> {
                System.out.println(context);
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            androidDriver.quit();
        }
    }

    private static ExpectedCondition<Boolean> moreThanOneContext(AppiumDriver<MobileElement> appiumDriver){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return appiumDriver.getContextHandles().size() > 1;
            }
        };
    }
}
