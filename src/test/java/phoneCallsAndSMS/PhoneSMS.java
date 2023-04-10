package phoneCallsAndSMS;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PhoneSMS {

    private static final String APP_ANDROID = "path to .apk file";
    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private static final String PHONE_NUMBER =  "8787876545";

    private By VERIFY_SCREEN = MobileBy.AccessibilityId("add id");
    private By SMS_WAITING = MobileBy.xpath("add xpath");
    private By SMS_VERIFIED = MobileBy.xpath("add xpath");

    private AndroidDriver driver;

    @Before
    private void setUpAndroid() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12");
        caps.setCapability("deviceName", "Pixel6");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("autoGrantPermissions","true");

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
    public void testPhoneAndSMS() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,5);
        driver.findElementByAccessibilityId("Login Screen").click();
        driver.makeGsmCall(PHONE_NUMBER, GsmCallActions.CALL);
        Thread.sleep(2000);
        driver.makeGsmCall(PHONE_NUMBER,GsmCallActions.ACCEPT);
        driver.findElementByAccessibilityId("username").sendKeys("yogesh");
        Thread.sleep(2000);
        driver.makeGsmCall(PHONE_NUMBER,GsmCallActions.CANCEL);
        driver.navigate().back();

        //SMS
        wait.until(ExpectedConditions.presenceOfElementLocated(VERIFY_SCREEN)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(SMS_WAITING));
        driver.sendSMS(PHONE_NUMBER,"Your code is 123456");
        wait.until(ExpectedConditions.presenceOfElementLocated(SMS_VERIFIED));
    }


}
