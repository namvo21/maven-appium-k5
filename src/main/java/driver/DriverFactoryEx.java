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

    // Lesson 22
    public AppiumDriver<MobileElement> getAndroidDriver(String udid, String systemPort, String platformName, String platformVersion){
        initDriver(udid, systemPort, platformName, platformVersion);
        return appiumDriver;
    }

    // Lesson 21
//    public AppiumDriver<MobileElement> getAndroidDriver(String udid, String systemPort) {
//        initDriver(udid, systemPort);
//        return appiumDriver;
//    }

    public AppiumDriver<MobileElement> getAndroidDriver(){
        return appiumDriver;
    }
    // Lesson 22
    private void initDriver(String udid, String systemPort, String platformName, String platformVersion) {

        try{
            PlatformType.valueOf(platformName.toLowerCase());
        }catch(Exception ex){
            throw new IllegalArgumentException("We don't support " + platformName);
        }

        boolean isAndroid = platformName.equalsIgnoreCase("android");

        try {
            // Specify capabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME,platformName);

            if(isAndroid){
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, Integer.parseInt(systemPort));
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, udid);
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            }else{
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.WDA_LOCAL_PORT, Integer.parseInt(systemPort));
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, udid);
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_VERSION,platformVersion);
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.BUNDLE_ID, "org.wdioNativeDemoApp");
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.NO_RESET, false);
            }

            //Send the desireCaps into Appium Server
            URL remoteServer = new URL("http://127.0.0.1:4723/wd/hub");
            String hub = System.getProperty("hub") != null ? System.getProperty("hub") : System.getenv("hub");
            if(hub != null)
                remoteServer = new URL(hub.concat(":4444/wd/hub"));
            appiumDriver = new AndroidDriver<>(remoteServer , desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Lesson 21
//    private void initDriver(String udid, String systemPort) {
//        try {
//            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "ios");
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "XCUITest");
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, "iPhone 11 Pro");
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_VERSION, "13.3");
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP, "https://github.com/webdriverio/native-demo-app/releases/download/v0.4.0/iOS-Simulator-NativeDemoApp-0.4.0.app.zip");
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.NO_RESET, false);
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.WDA_LOCAL_PORT, Integer.parseInt(systemPort));
//
//            //Send the desireCaps into Appium Server
//            URL remoteServer = new URL("http://127.0.0.1:4723/wd/hub");
//            String hub = System.getProperty("hub") != null ? System.getProperty("hub") : System.getenv("hub");
//            if (hub != null)
//                remoteServer = new URL(hub.concat(":4444/wd/hub"));
//            appiumDriver = new AndroidDriver<>(remoteServer, desiredCapabilities);
//            appiumDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public void quitAppiumSession(){
        if(appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
