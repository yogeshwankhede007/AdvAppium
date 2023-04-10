package screenActions;

import io.appium.java_client.android.AndroidDriver;
import org.aspectj.util.FileUtil;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ScreenOrientation {

    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private static final String ANDROID_PHOTO_PATH = "/mnt/";
    private AndroidDriver driver;

    private void setUpAndroid(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("platformVersion","12");
        caps.setCapability("deviceName","Pixel6");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("appPackage","add app package");
        caps.setCapability("appActivity","add app activity");

        try {
            driver = new AndroidDriver(new URL(APPIUM),caps);
        } catch (MalformedURLException e) {
            System.out.println("Something is wrong :"+"\u001B[31m"+"Check Caps");
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
    @Test
    public void testScreenOrientations(){
        org.openqa.selenium.ScreenOrientation curOr = driver.getOrientation();
        System.out.println(curOr);
        if (curOr != org.openqa.selenium.ScreenOrientation.LANDSCAPE){
            driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
        }

        Dimension size = driver.manage().window().getSize();
        System.out.println(size);

        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        File saveFile = new File("location");
        try {
            FileUtil.copyFile(screenshot,saveFile);
        } catch (IOException e) {
            System.out.println("Check copy files");
            throw new RuntimeException(e);
        }
        driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);

    }
}
