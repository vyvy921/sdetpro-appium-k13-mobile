package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginFormInteraction {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
//            By loginNavLoc = By.xpath("//android.view.View[@content-desc=\"Login\"]");
            WebElement loginNavEle = appiumDriver.findElement(AppiumBy.accessibilityId("Login"));
            loginNavEle.click();

            //Input username
//            By inputUsLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-email\"]");
            WebElement inputUsEle = appiumDriver.findElement(AppiumBy.accessibilityId("input-email"));
            inputUsEle.sendKeys("vyvy@gmail.com");

            //Input password
//            By inputPwLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-password\"]");
            WebElement inputPwEle = appiumDriver.findElement(AppiumBy.accessibilityId("input-password"));
            inputPwEle.sendKeys("12345678");

            //Click on login btn
//            By loginBtnLoc = By.xpath("//android.view.ViewGroup[@content-desc=\"button-LOGIN\"]");
            By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
            WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
            loginBtnEle.click();

            //Wait an Element
            By dialogMsgLoc = AppiumBy.id("android:id/message");
            By dialogBtnLoc = AppiumBy.id("android:id/button1");

            //using explicit wait
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15));
            WebElement dialogMsgElem = wait.until(ExpectedConditions.visibilityOfElementLocated(dialogMsgLoc));
            System.out.printf("Your dialog msg: %s", dialogMsgElem.getText());
            appiumDriver.findElement(dialogBtnLoc).click();




        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (Exception ignore) {
        }

        appiumDriver.quit();


    }
}
