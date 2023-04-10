package platforms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class iOSDevice {
    private static final  String APP_iOS = " path of .apk file";
    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private static final String DEVICE_ID ="get it by typing cmd - adb devices";
    private AppiumDriver driver;

    @Before
    private void setiOS(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","iOS");
        caps.setCapability("platformVersion","15.1");
        caps.setCapability("deviceName","iPhone 12");
        caps.setCapability("automationName","XCUITest");
        caps.setCapability("app",APP_iOS);
        caps.setCapability("udid",DEVICE_ID);
        caps.setCapability("xcodeOrgId","add org id");
        caps.setCapability("xcodeSigningId","iPhone Developer");
        try {
            driver = new IOSDriver(new URL(APPIUM),caps);
        } catch (MalformedURLException e) {
            System.out.println("Something is wrong :"+"\u001B[31m"+"Check Caps");
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void  testLoginOnRealDevice(){
        WebDriverWait wait = new WebDriverWait(driver,10);

        WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login")));
        screen.click();

        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("username")));
        username.sendKeys("yogesh");

        WebElement password = driver.findElement(MobileBy.AccessibilityId("password"));
        password.sendKeys("12345");

        WebElement login = driver.findElement(MobileBy.AccessibilityId("loginbtn"));
        login.click();

        WebElement loginText = wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.xpath(" Write xpath of locator u want to assert")));

        assert(loginText.getText().contains("text of your xpath"));

    }
}
