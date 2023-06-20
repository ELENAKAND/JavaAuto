package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static  String
            MY_LISTS_LINK,
     //       SAVE_BUTTON,
            SAVE_BUTTON_WITH_ARTICLE,
            CLOSE_OVERLAY_BUTTON,
            CLOSE_IOS_OVERLAY_BUTTON,
             SAVE_IOS_BUTTON_WITH_ARTICLE;


    public NavigationUI(AppiumDriver driver){
        super(driver);
    }
    public void clickMySavedLists(){
        this.waitForElementAndClick(
                SAVE_BUTTON_WITH_ARTICLE,
                "Cannot find SAVED button on the main page",
                5
        );
        this.waitForElementAndClick(
                CLOSE_OVERLAY_BUTTON,
                "Cannot find NOT NOW button",
                5
        );
    }
    public void clickIOSSavedLists(){
        this.waitForElementAndClick(
                SAVE_IOS_BUTTON_WITH_ARTICLE,
                "Cannot find iOS SAVE button",
                5
        );
        this.waitForElementAndClick(
                CLOSE_IOS_OVERLAY_BUTTON,
                "Cannot find NOT NOW button",
                5
        );
    }
}
