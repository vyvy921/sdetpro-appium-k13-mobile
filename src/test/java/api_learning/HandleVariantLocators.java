package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;

import java.util.Map;

public class HandleVariantLocators {

    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            //get platform info under test session
            Capabilities caps = appiumDriver.getCapabilities();
            String currentPlatform = CapabilityHelpers.getCapability(caps, "platformName", String.class);
            System.out.printf("Platform name is: %s\n", currentPlatform);

            //construct the locator map
            Map<Platform, By> loginBtnLocMap = Map.of(
                    Platform.ANDROID, AppiumBy.id("android-btn"),
                    Platform.IOS, AppiumBy.accessibilityId("ios-btn")
            );

            System.out.println(loginBtnLocMap.get(Platform.valueOf(currentPlatform)));

            //debug purpose only
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();


    }
}
