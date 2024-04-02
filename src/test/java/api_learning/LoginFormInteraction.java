package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginFormInteraction {
    public static void main(String[] args) {
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            By loginNavLoc = By.xpath("//android.view.View[@content-desc=\"Login\"]");
            WebElement loginNavEle = appiumDriver.findElement(loginNavLoc);
            loginNavEle.click();

            //Input username
            By inputUsLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-email\"]");
            WebElement inputUsEle = appiumDriver.findElement(inputUsLoc);
            inputUsEle.sendKeys("userahihi");
            //Input password
            By inputPwLoc = By.xpath("//android.widget.EditText[@content-desc=\"input-password\"]");
            WebElement inputPwEle = appiumDriver.findElement(inputPwLoc);
            inputPwEle.sendKeys("password");

            //Click on login btn
            By loginBtnLoc = By.xpath("//android.view.ViewGroup[@content-desc=\"button-LOGIN\"]");
            WebElement loginBtnEle = appiumDriver.findElement(loginBtnLoc);
            loginBtnEle.click();


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
