package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SwipeHorizontally {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        MobileElement swipeLabel = androidDriver.findElementByAccessibilityId("Swipe");
        swipeLabel.click();

        // Avoid screen transition
        WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 10L);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Swipe horizontal']")));

        // Get mobile size
        Dimension windowSize = androidDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        //Calculate touch points
        int xStartPoint = 90 * screenWidth / 100;
        int xEndPoint = 10 * screenWidth / 100;

        int yStartPoint = 50 * screenHeight / 100;
        int yEndPoint = yStartPoint;

        // Convert to PointOption - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Perform Actions
        // press -> move up -> release
        TouchAction touchAction = new TouchAction(androidDriver);
        final int MAX_SWIPE_TIME = 5;
        int swipeTime = 0;

        while(swipeTime < MAX_SWIPE_TIME){
            List<MobileElement> matchedElems = androidDriver.findElementsByXPath("//*[@text='EXTENDABLE']");
            if(!matchedElems.isEmpty()) break;

            touchAction
                    .longPress(startPoint)
                    .moveTo(endPoint)
                    .release()
                    .perform();

            swipeTime++;
        }

        if(swipeTime == MAX_SWIPE_TIME){
            throw new RuntimeException("Card not found");
        }
    }
}
