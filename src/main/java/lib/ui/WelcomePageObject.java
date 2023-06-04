package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {
    private static final String
    STEP_MAIN_PAGE = "The free encyclopedia",
    STEP_NEW_WAYS_TO_EXPLORE = "New ways to explore",
    STEP_SEARCH_IN_LANG = "Search in nearly 300 languages",
    STEP_MAKE_APP_BETTER = "Help make the app better",
    STEP_NEXT_BUTTON = "//*[@name='Next']",
    STEP_GET_STARTED_BUTTON = "//*[@name='Get started']";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForMainPage() {
        this.waitForElementPresent(By.id(STEP_MAIN_PAGE), "Cannot find welcome page the free encyclopedia", 10);
    }
    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE), "Cannot find second page The new ways", 10);
    }
    public void waitForSearchInLangText() {
        this.waitForElementPresent(By.id(STEP_SEARCH_IN_LANG), "Cannot find third page Search in nearly 300 languages", 10);
    }
    public void waitForMakeTheAppBetterText() {
        this.waitForElementPresent(By.id(STEP_MAKE_APP_BETTER), "Cannot find 4th page Help make the app better", 10);
    }
    public void clickNextButton() {
        this.waitForElementAndClick(By.xpath(STEP_NEXT_BUTTON), "Cannot find and click the Next button", 10);
    }
    public void clickGetStartedButton() {
        this.waitForElementAndClick(By.xpath(STEP_GET_STARTED_BUTTON), "Cannot find and click the Get started button", 10);
    }
}
