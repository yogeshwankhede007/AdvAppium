package phoneOSApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SystemApp {
    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private AppiumDriver driver;
    private DesiredCapabilities caps;

    private void setAndroid() {
        caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12");
        caps.setCapability("deviceName", "Pixel6");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage","add app package");
        caps.setCapability("appActivity","add app activity");
        try {
            driver = new AndroidDriver(new URL(APPIUM), caps);
        } catch (MalformedURLException e) {
            System.out.println("Something is wrong :" + "\u001B[31m" + "Check Caps");
            throw new RuntimeException(e);
        }
    }

    private void setIOS() {
        caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "15.1");
        caps.setCapability("deviceName", "iPhone 12");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("BundleId","add bundle id");
        try {
            driver = new IOSDriver(new URL(APPIUM), caps);
        } catch (MalformedURLException e) {
            System.out.println("Something is wrong :" + "\u001B[31m" + "Check Caps");
            throw new RuntimeException(e);
        }
    }
@Before
    public void setUp(){
        if(caps.getCapability("automationName").equals("UiAutomator2")) {
            setAndroid();
        }
        else{
            setIOS();
        }
    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void testSystemApp(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(driver.getPageSource());
    }
}