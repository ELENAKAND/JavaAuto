package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject{
    protected static  String
            MY_LISTS_LINK,
            SAVE_BUTTON,
            CLOSE_OVERLAY_BUTTON;


    public NavigationUI(AppiumDriver driver){
        super(driver);
    }
    public void clickMySavedLists(){
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find SAVED button on the main page",
                5
        );
        this.waitForElementAndClick(
                CLOSE_OVERLAY_BUTTON,
                "Cannot find NOT NOW button",
                5
        );
    }
}
