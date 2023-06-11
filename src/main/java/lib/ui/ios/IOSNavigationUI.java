package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']"; //Save icon from iOS main page
        CLOSE_OVERLAY_BUTTON = "//XCUIElementTypeButton[@name='Close']";
        SAVE_BUTTON = "//XCUIElementTypeButton[@name='Saved']";

    }
    public IOSNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
