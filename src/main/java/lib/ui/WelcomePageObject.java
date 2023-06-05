package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {
    private static final String
    STEP_MAIN_PAGE = "id:The free encyclopedia",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_SEARCH_IN_LANG = "id:Search in nearly 300 languages",
    STEP_MAKE_APP_BETTER = "id:Help make the app better",
    STEP_NEXT_BUTTON = "xpath://*[@name='Next']",
    STEP_GET_STARTED_BUTTON = "xpath://*[@name='Get started']";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForMainPage() {
        this.waitForElementPresent(STEP_MAIN_PAGE, "Cannot find welcome page the free encyclopedia", 10);
    }
    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find second page The new ways", 10);
    }
    public void waitForSearchInLangText() {
        this.waitForElementPresent(STEP_SEARCH_IN_LANG, "Cannot find third page Search in nearly 300 languages", 10);
    }
    public void waitForMakeTheAppBetterText() {
        this.waitForElementPresent(STEP_MAKE_APP_BETTER, "Cannot find 4th page Help make the app better", 10);
    }
    public void clickNextButton() {
        this.waitForElementAndClick(STEP_NEXT_BUTTON, "Cannot find and click the Next button", 10);
    }
    public void clickGetStartedButton() {
        this.waitForElementAndClick(STEP_GET_STARTED_BUTTON, "Cannot find and click the Get started button", 10);
    }
}
