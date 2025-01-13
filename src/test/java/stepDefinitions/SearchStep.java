package stepDefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import pages.HomePage;
import utilities.MobileUtils;
import utilities.OS;

import static utilities.Driver.getCurrentDriver;

public class SearchStep {
        private pages.SearchPage searchPage = new pages.SearchPage();
        HomePage homePage = new HomePage();

        private static String currentScenarioName;

        public SearchStep() {

        }

        @Before
        public void setUp(Scenario scenario) {
            currentScenarioName = scenario.getName();
        }

    @And("click filter button")
    public void clickFilterButton() {
        searchPage.filterButton.click();
    }




    }


