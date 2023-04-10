package platforms;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class WebviewAutomation {

    private static final  String APP_ANDROID = " path of .apk file";
    private static final String APP_iOS = "path of .ipa file";
    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private AppiumDriver driver;

    private void setUpAndroid(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("platformVersion","12");
        caps.setCapability("deviceName","Pixel6");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("app",APP_ANDROID);
        try {
            driver = new AppiumDriver(new URL(APPIUM),caps);
        } catch (MalformedURLException e) {
            System.out.println("Something is wrong :"+"\u001B[31m"+"Check Caps");
            throw new RuntimeException(e);
        }

    }
}
