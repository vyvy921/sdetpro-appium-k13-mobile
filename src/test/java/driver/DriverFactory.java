package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    //input: Platform
    //output: appiumDriver

    public static AppiumDriver getDriver(Platform platform) {
        AppiumDriver appiumDriver = null;
        //check driver !null before return

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4725");
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (platform) {
            case ANDROID:
                appiumDriver = new AndroidDriver(appiumServer, desiredCapabilities);
                break;

            case IOS:
                appiumDriver = new IOSDriver(appiumServer, desiredCapabilities);
                break;
        }

        if (appiumDriver == null) {
            throw new RuntimeException("Can't construct the appium server URL");
        }

        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        return appiumDriver;
    }

}
