package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwipeVertically {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        MobileElement formLabel = androidDriver.findElementByAccessibilityId("Forms");
        formLabel.click();

        // Get mobile size
        Dimension windowSize = androidDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        //Calculate touch points
        int xStartPoint = (50 * screenWidth) / 100;
        int xEndPoint = xStartPoint;

        int yStartPoint = (70 * screenHeight) / 100;
        int yEndPoint = (10 * screenHeight) / 100;

        // Convert to PointOption - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Avoid screen transition
        WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 5L);
        webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

        // Perform Actions
        // press -> move up -> release
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(endPoint)
                .release()
                .perform();

        Thread.sleep(3000);

        // Find Active button
        MobileElement activeButtonElems = androidDriver.findElementByAccessibilityId("button-Active");
        // Click on Active button
        activeButtonElems.click();
    }
}
