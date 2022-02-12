package test;

import caps.MobileCapabilityTypeEx;
import com.google.common.reflect.ClassPath;
import driver.PlatformType;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main implements MobileCapabilityTypeEx {

    @SuppressWarnings("UnstableApiUsage")
    public static void main(String[] args) throws IOException {
        // Get all classes with package prefix is 'test'
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();

        // Guava <---- Selenium <---- appium
        for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            String classInfoName = info.getName();
            boolean startWithTestDot = classInfoName.startsWith("test.");
            boolean isBaseTestClass = classInfoName.equalsIgnoreCase("test.BaseTest");
            boolean isMainClass = classInfoName.equalsIgnoreCase("test.Main");

            if (startWithTestDot && !isBaseTestClass && !isMainClass) {
                testClasses.add(info.load());
            }
        }

        // Get platform
        String platformName = System.getProperty("platform");
        if (platformName == null) {
            throw new RuntimeException("Please provide platform via -Dplatform");
        }

        try {
            PlatformType.valueOf(platformName);
        } catch (Exception ex) {
            throw new IllegalArgumentException("[ERR] We don't support " + platformName);
        }

        List<String> iPhoneDeviceList = Arrays.asList("iPhone 12", "iPhone 13");
        List<String> androidDeviceList = Arrays.asList("emulator-5554", "ce081718bae29a2b047e");
        List<String> deviceList = platformName.equalsIgnoreCase("ios") ? iPhoneDeviceList : androidDeviceList;

        // Device test classes based on device list
        int testNumEachDevice = testClasses.size() / deviceList.size();
        HashMap<String, List<Class<?>>> desireCaps = new HashMap<>();
        for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
            int startIndex = deviceIndex * testNumEachDevice;
            int endIndex = deviceIndex == deviceList.size() - 1 ? testClasses.size() : (startIndex + testNumEachDevice);
            List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
            desireCaps.put(deviceList.get(deviceIndex), subTestList);
        }
        TestNG testNG= new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");

        List<XmlTest> allTests = new ArrayList<>();
        for (String deviceName : desireCaps.keySet()) {
            XmlTest test = new XmlTest(suite);
            test.setName(deviceName);
            List<XmlClass> xmlClasses = new ArrayList<>();
            List<Class<?>> dedicatedClasses = desireCaps.get(deviceName);
            for (Class<?> dedicatedClass : dedicatedClasses) {
                xmlClasses.add(new XmlClass(dedicatedClass.getName()));
            }

            test.setXmlClasses(xmlClasses);
            test.addParameter(UDID, deviceName);
            test.addParameter(PLATFORM_NAME, platformName);
            test.addParameter(PLATFORM_VERSION, "15.0");
            test.addParameter(SYSTEM_PORT, String.valueOf(new SecureRandom().nextInt(1000)+8300));
            allTests.add(test);
        }

        suite.setTests(allTests);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(10);

        System.out.println(suite.toXml());

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
