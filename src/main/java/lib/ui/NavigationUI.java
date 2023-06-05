package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    private static final String
            MY_LISTS_LINK = "xpath://*[@text='Saved']",
            NOT_NOW_OVERLAY_BUTTON = "id:org.wikipedia:id/negativeButton";

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }
    public void clickMySavedLists(){
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find SAVED button on the main page",
                5
        );
        this.waitForElementAndClick(
                NOT_NOW_OVERLAY_BUTTON,
                "Cannot find NOT NOW button",
                5
        );
    }
}
