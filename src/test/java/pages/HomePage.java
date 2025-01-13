package pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.MobileUtils;
import utilities.OS;

import static utilities.Driver.getCurrentDriver;


public class HomePage extends BasePage {

    @AndroidFindBy(xpath = "//*[@text='Üye Olmadan Devam Et']" )
    public WebElement guestButton;

    @AndroidFindBy(id = "searchTextView" )
    public WebElement searchBox;

    @AndroidFindBy(xpath = "(//*[@resource-id='com.akakce.akakce:id/searchTextView'])[2]" )
    public WebElement searchBoxInput;

    public HomePage() {
        super(getCurrentDriver());
        PageFactory.initElements(new AppiumFieldDecorator(getCurrentDriver()), this);
    }


    public void searchSelectProduct(String product){
//        By anaSayfaButon = OS.isAndroid() ?
//                AppiumBy.id("searchTextView") ://Anroid
//                AppiumBy.accessibilityId("12000");//ios
//        getCurrentDriver().findElement(anaSayfaButon).click();

        MobileUtils.waitFor(3);
        searchBox.click();
        MobileUtils.waitFor(1);
        searchBoxInput.sendKeys(product);
        MobileUtils.waitFor(1);
        ((JavascriptExecutor) Driver.getCurrentDriver()).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        MobileUtils.waitFor(2);
    }

}
