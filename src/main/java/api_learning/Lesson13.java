package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Lesson13 {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            // Click on Login label
            MobileElement loginLabelElement = androidDriver.findElementByAccessibilityId("Login");
            loginLabelElement.click();

            //Lesson 13
            final int EMAIL_FIELD_INDEX = 0;
            final int PASSWORD_FIELD_INDEX = 0;
            List<MobileElement> loginFormInputElems = androidDriver.findElements(By.xpath("//android.widget.EditText"));
            if(loginFormInputElems.isEmpty()){
                throw new RuntimeException("Login form elems can't be found");
            }else{
                loginFormInputElems.get(EMAIL_FIELD_INDEX).sendKeys("new@sth.com");
                loginFormInputElems.get(PASSWORD_FIELD_INDEX).sendKeys("12345678");
            }

            //Finding elements
            MobileElement usernameElement = androidDriver.findElementByAccessibilityId("input-email");
            MobileElement passwordElement = androidDriver.findElementByAccessibilityId("input-password");
            MobileElement loginButtonElement = androidDriver.findElementByAccessibilityId("button-LOGIN");

            // Input username
            usernameElement.sendKeys("teo");

            // Input password
            passwordElement.sendKeys("12345678910");

            // Click on Login Button
            loginButtonElement.click();

            //Clear
            usernameElement.clear();
            usernameElement.sendKeys("vhnam2110@gmail.com");

            loginButtonElement.click();

            //Lesson 13
            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 10L);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));

            MobileElement loginDialogTitleElem = androidDriver.findElement(By.id("android:id/alertTitle"));
            System.out.println("Login Title: " + loginDialogTitleElem.getText());

            MobileElement loginDialogXpathElem = androidDriver.findElementByXPath(
                    "//*[@resource-id='android:id/topPanel']//*[@resource-id='android:id/alertTitle']");
            System.out.println(loginDialogXpathElem.getText());

            List<MobileElement> badSelectorElems = androidDriver.findElementsByXPath("wrong");
            if(!badSelectorElems.isEmpty()){
                throw new RuntimeException("Wrong element still displayed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            androidDriver.quit();
        }
    }
}
