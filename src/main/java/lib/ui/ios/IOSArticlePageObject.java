package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        SAVE_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
      //  SAVE_BUTTON_SECOND_SAVING = "xpath://*[@text='Node.js']";
        ADD_TO_LIST = "id:Add ‘Java (programming language)’ to a reading list?"+"/..";
        MY_LIST_NAME_INPUT = "//XCUIElementTypeStaticText[@name='Reading list name']";
        MY_LIST_OK_BUTTON = "//XCUIElementTypeStaticText[@name='Create reading list']";
        CREATE_NEW_LIST_BUTTON = "//XCUIElementTypeStaticText[@name='Create a new list']";

    }
 public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
 }
}
