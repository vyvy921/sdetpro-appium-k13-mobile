package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class SwipeHorizontally {

    public static void main(String[] args) {

        //Launch app
        AppiumDriver appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            //find Element [Swipe] location
            By formsBtnLoc = AppiumBy.accessibilityId("Swipe");

            //navigate to [Swipe] screen
            appiumDriver.findElement(formsBtnLoc).click();

            //Check we are on the same target screen before swiping up/down/left/right
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            //get window Dimension data (height, width)
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            System.out.printf("window Size hxw : %dx%d", screenHeight, screenWidth);

            // construct coordinator
            int startX = 70 * screenWidth / 100;
            int startY = 70 * screenHeight / 100;
            int endX = 30 * screenHeight / 100;
            int endY = startY;

            // specify Pointer Input as [TOUCH] with name [finger]
            PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            final int MAX_SWIPE_TIME = 5;
            for (int swipeCounter = 0; swipeCounter < MAX_SWIPE_TIME; swipeCounter++) {

                // specify sequence
                Sequence sequence = new Sequence(pointerInput, 1)
                        .addAction(pointerInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                        .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(new Pause(pointerInput, Duration.ofMillis(250)))
                        .addAction(pointerInput.createPointerMove(Duration.ofMillis(250), PointerInput.Origin.viewport(), endX, endY))
                        .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                //ask appium Server perform sequence
                appiumDriver.perform(Collections.singleton(sequence));

                //wait
                Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Quit app
        appiumDriver.quit();

    }
}
