package appState;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AppBackdoor {
    private static final String APP_ANDROID = "path to .apk file";
    private static final String APPIUM = "http://localhost:4723/wd/hub";private static final String PHONE_NUMBER =  "8787876545";
    private AndroidDriver driver;

    @Before
    private void setUpAndroid() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12");
        caps.setCapability("deviceName", "Pixel6");
        caps.setCapability("automationName", "Espresso");

        try {
            driver = new AndroidDriver(new URL(APPIUM),caps);
        } catch (MalformedURLException e) {
            System.out.println("Something is wrong :"+"\u001B[31m"+"Check Caps");
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void testBackdoor() throws InterruptedException {
        ImmutableMap <String,Object> scriptArgs = ImmutableMap.of(
                "target","application",
                "methods", Arrays.asList(ImmutableMap.of(
                        "name","raisTost",
                        "args",Arrays.asList(ImmutableMap.of(
                                "value","Hello from script",
                                "type","String"
                        ))
                ))
        );
        driver.executeScript("mobile: backdoor",scriptArgs);
        Thread.sleep(3000);
    }
}
