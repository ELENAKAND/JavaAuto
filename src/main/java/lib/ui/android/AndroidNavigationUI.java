package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:org.wikipedia:id/nav_tab_reading_lists"; //Save icon from android main page
        SAVE_BUTTON = "//android.widget.TextView[@content-desc='Save']";
        CLOSE_OVERLAY_BUTTON = "org.wikipedia:id/negativeButton";
    }
    public AndroidNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
