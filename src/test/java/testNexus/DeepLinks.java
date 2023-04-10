package testNexus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DeepLinks {
    private static final String APP_ANDROID = "path to .apk file";
    private static final String APPIUM = "http://localhost:4723/wd/hub";private static final String PHONE_NUMBER =  "8787876545";
    private AndroidDriver driver;

    WebElement Screen;
    WebElement username;
    WebElement password;
    WebElement loginBtn;
    WebElement loginText;

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
    public void testLoginNormally(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        Screen= wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login")));
        Screen.click();
        username = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("add xpath")));
        username.sendKeys("yogesh");
        password = driver.findElement(MobileBy.xpath("add xpath"));
        password.sendKeys("12345");
        loginBtn = driver.findElement(MobileBy.xpath("add xpath"));
        loginBtn.click();

        loginText = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("add id")));
        assert(loginText.getText().contains("yogesh"));
    }

    @Test
    public void testLoginWithDeepLink(){
        driver.get("YOUR DEEP LINK URL");
        assert(loginText.getText().contains("yogesh"));
    }
}
