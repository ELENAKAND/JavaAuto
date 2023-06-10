package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObjectAndroid extends MainPageObject {
    private static final String
    SKIP_BUTTON_ANDROID = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    public WelcomePageObjectAndroid(AppiumDriver driver){
        super(driver);
    }
    public void clickSkipButtonAndroid(){
        this.waitForElementAndClick(SKIP_BUTTON_ANDROID, "Cannot find skip button Android", 5);
    }
}
