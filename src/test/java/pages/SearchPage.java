package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.OS;

import static utilities.Driver.getCurrentDriver;

public class SearchPage extends BasePage {

    @AndroidFindBy(id = "filterArea" )
    public WebElement filterButton;


    public SearchPage() {
        super(getCurrentDriver());
        PageFactory.initElements(new AppiumFieldDecorator(getCurrentDriver()), this);
    }

}