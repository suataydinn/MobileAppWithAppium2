package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;


import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

//    public BasePage(AppiumDriver driver) {
//        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
//    }

    public void clickText(String text){
        driver.findElement(By.xpath("//*[@text='" + text + "']")).click();
    }

    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    protected void swipeVertical(double startPercentage, double endPercentage, double anchorPercentage) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver appiumDriver = (AppiumDriver) driver;
            Dimension size = driver.manage().window().getSize();
            int anchor = (int) (size.width * anchorPercentage);
            int startPoint = (int) (size.height * startPercentage);
            int endPoint = (int) (size.height * endPercentage);

            new TouchAction((PerformsTouchActions) appiumDriver)
                    .press(PointOption.point(anchor, startPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(anchor, endPoint))
                    .release().perform();
        }
    }

    protected void tapByCoordinates(int x, int y) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver appiumDriver = (AppiumDriver) driver;
            new TouchAction((PerformsTouchActions) appiumDriver)
                    .tap(PointOption.point(x, y))
                    .perform();
        }
    }


    protected void switchToFrame(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }




    protected boolean isAndroid() {
        return driver instanceof AndroidDriver;
    }

    protected boolean isIOS() {
        return driver instanceof IOSDriver;
    }

    protected boolean isWeb() {
        return !(driver instanceof AppiumDriver);
    }

    protected void scrollToElement(By locator) {
        if (isWeb()) {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } else if (isAndroid()) {
            ((AndroidDriver) driver).findElement(new AppiumBy.ByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
                            + "new UiSelector().descriptionContains(\"" + locator + "\").instance(0))"));
        }

    }
}