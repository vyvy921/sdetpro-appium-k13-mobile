package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NarrowDownSearchingScope {

    public static void main(String[] args) {

        //Launch app
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        /*Swipe vertically
         * - Which element?
         * - Where: start x,y | end x,y
         * - flow1: find Element location -> navigate to [Form] screen -> check if activeBtn is displayed -> get window Dimension (height, width) -> swipe up before interacting
         * - flow2: construct coordinator -> specify Pointer Input -> specify sequence
         * */

        try {
            //find Element [Form] location
            By formsBtnLoc = AppiumBy.accessibilityId("Forms");

            //navigate to [Form] screen
            appiumDriver.findElement(formsBtnLoc).click();

//Check we are on the same target screen before swiping up/down/left/right
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Form components\")")));

            //Swipe down to open notification
            openNotification(appiumDriver);

            try {
                Thread.sleep(2000);
            } catch (Exception ignore) {
            }

            //Narrow down searching scope
            List<WebElement> notificationEleList = appiumDriver.findElements(AppiumBy.id("com.android.systemui:id/expanded"));

            List<String> notificationTitles = new ArrayList<>();
            //Loop over elements inside and do assertion
            for (WebElement notificationEle : notificationEleList) {
                //Narrow down
                WebElement notificationTitleEle = notificationEle.findElement(AppiumBy.id("android:id/app_name_text"));
                notificationTitles.add(notificationTitleEle.getText());
            }
            System.out.println(notificationTitles);

            closeNotification(appiumDriver);

            try {
                Thread.sleep(2000);
            } catch (Exception ignore) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Quit app
        appiumDriver.quit();

    }

    public static void openNotification(AppiumDriver appiumDriver) {

//            //check to see activeBtn is displayed
//            boolean isActiveBtnDisplay = appiumDriver.findElement(activeBtnLoc).isDisplayed();
//            System.out.println("isActiveBtnDisplay" + isActiveBtnDisplay);

        //get window Dimension (height, width)
        //then swipe up before interacting
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        System.out.printf("window Size hxw : %dx%d\n", screenHeight, screenWidth);

        // construct coordinator
        int startX = 50 * screenWidth / 100;
        int startY = 0;
        int endX = startX;
        int endY = 80 * screenHeight / 100;

        // specify Pointer Input
        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // specify sequence
        Sequence sequence = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, Duration.ofMillis(250)))
                .addAction(pointerInput.createPointerMove(Duration.ofMillis(250), PointerInput.Origin.viewport(), endX, endY))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //ask appium Server perform sequence
        appiumDriver.perform(Collections.singleton(sequence));
    }

    public static void closeNotification(AppiumDriver appiumDriver) {
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        // construct coordinator
        int startX = 50 * screenWidth / 100;
        int startY = 90 * screenHeight / 100;
        int endX = startX;
        int endY = 0;

        // specify Pointer Input
        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // swipe up
        Sequence sequenceUp = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, Duration.ofMillis(250)))
                .addAction(pointerInput.createPointerMove(Duration.ofMillis(250), PointerInput.Origin.viewport(), endX, endY))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //ask appium Server perform sequence
        appiumDriver.perform(Collections.singleton(sequenceUp));

    }
/*
* SwipeHelper: Service methods: public openNotification(), swipeToBottom(), swipeToTop(), swipeUpHalfScreen()
* support method: private swipe(percentage....)
* Functional programing? or Object programing?
* */


}
