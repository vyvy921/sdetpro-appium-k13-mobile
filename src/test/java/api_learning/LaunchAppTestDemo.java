package api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class LaunchAppTestDemo {

    public static void main(String[] args) {

        //DesiredCapabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
        desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability("udid", "emulator-5554");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("platformName", "android");

        //init session
        AppiumDriver appiumDriver = null;
        try {
            URL appiumServer = new URL("http://localhost:4723");
            appiumDriver = new AndroidDriver(appiumServer, desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //close, quit the session
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }


}