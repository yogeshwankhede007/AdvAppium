package fileTransfers;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidFileTrans {
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
    public void testPhotos(){
        File img = new File("src/test/resources/fsociety.jpg").getAbsoluteFile();
        try {
            driver.pushFile(ANDROID_PHOTO_PATH+ "/"+ img.getName(),img);
        } catch (IOException e) {
            System.out.println("check path");
            throw new RuntimeException(e);
        }
    }


}
