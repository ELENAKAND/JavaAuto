package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static{
        FOLDER_BY_NAME_TPL = "xpath://XCUIElementTypeStaticText[@name='{FOLDER_NAME}']"; // {section easy to change} by .replace() method
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";////XCUIElementTypeStaticText[contains(@name,‘{TITLE}’)]
        READING_LIST_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Reading lists']";
        SAVED_LIST_BUTTON = "id:Test list";
        OVERLAY_BUTTON = "xpath://XCUIElementTypeButton[@name='Close'']";
        TRASH_BUTTON = "xpath://XCUIElementTypeButton[@name='swipe action delete']";
        ARTICLE_TITLE_TPL ="xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
    }
    public IOSMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}

