package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static{
        FOLDER_BY_NAME_TPL = "//XCUIElementTypeStaticText[@name='{FOLDER_NAME}']"; // {section easy to change} by .replace() method
        ARTICLE_BY_TITLE_TPL = "//XCUIElementTypeStaticText[@name='{TITLE}'] ";////XCUIElementTypeStaticText[contains(@name,‘{TITLE}’)]
        READING_LIST_BUTTON = "//XCUIElementTypeStaticText[@name='Reading lists']";

    }
    public IOSMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}

