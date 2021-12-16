package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SwipeToOpenNotification {
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

        int yStartPoint = 0;
        int yEndPoint = (100 * screenHeight) / 100;

        // Convert to PointOption - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Perform Actions
        // press -> move up -> release
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(endPoint)
                .release()
                .perform();

        List<MobileElement> notificationLists = androidDriver.findElements(By.id("android:id/notification_main_column"));

        if(notificationLists.isEmpty()){
            throw new RuntimeException("Notification list is empty");
        }

        List<Notification> notifications = new ArrayList<>();

        notificationLists.forEach(notificationList ->{
            String notificationTitle = notificationList.findElement(By.id("android:id/title")).getText();
            By bigTextByID = MobileBy.id("android:id/big_text");
            By textByID = MobileBy.id("android:id/text");

            List<MobileElement> bigTextElems = androidDriver.findElements(bigTextByID);
            List<MobileElement> textElems = androidDriver.findElements(textByID);

            List<MobileElement> notificationBodyElems = !bigTextElems.isEmpty() ? bigTextElems : textElems;

            String notificationBody = notificationBodyElems.isEmpty() ? null : notificationBodyElems.get(0).getText();

            notifications.add(new Notification(notificationTitle, notificationBody));
        });

        notifications.forEach(notification -> {
            System.out.println(notification);
        });

        // Swipe Up to close notification bar
        touchAction
                .press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(startPoint)
                .release()
                .perform();

        Thread.sleep(1000);
    }

    public static class Notification{
        private String title;
        private String content;

        public Notification(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Notification{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
