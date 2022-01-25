package driver;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactoryEx {
    private AppiumDriver<MobileElement> appiumDriver;

    public AppiumDriver<MobileElement> getAndroidDriver(String udid, String systemPort){
        initDriver(udid, systemPort);
        return appiumDriver;
    }

    public AppiumDriver<MobileElement> getAndroidDriver(){
        return appiumDriver;
    }

    private void initDriver(String udid, String systemPort) {
        try {
            // Specify capabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, systemPort);
            //desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "ce081718bae29a2b047e");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, udid);
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

            //Send the desireCaps into Appium Server
            //URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub");
            //URL appiumServer = new URL("http://192.168.1.46:4444/wd/hub");
            URL remoteServer = new URL("http://127.0.0.1:4723/wd/hub");
            //String hub = System.getProperty("hub") != null ? System.getProperty("hub") : System.getenv("hub");
            //if(hub != null)
            //    remoteServer = new URL(hub.concat(":4444/wd/hub"));
            //appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
            //appiumDriver = new AndroidDriver<>(remoteServer, desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void quitAppiumSession(){
        if(appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
