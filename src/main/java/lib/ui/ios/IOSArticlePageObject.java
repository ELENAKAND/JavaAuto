package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
     //   FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']"; the same locator is on the page on start
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
      //  SAVE_BUTTON_SECOND_SAVING = "xpath://*[@text='Node.js']";
     //   ADD_TO_LIST = "xpath://XCUIElementTypeStaticText[@name='Add “Java (programming language)” to a reading list?']";
      //  SECOND_ADD_TO_LIST = "id:Add “Appium” to a reading list?";
   //     ADD_TO_LIST = "xpath://XCUIElementTypeStaticText[contains(@name,“reading list”)]";
        ADD_TO_LIST = "xpath://XCUIElementTypeStaticText[@name='Add “Java (programming language)” to a reading list?']";
        MY_LIST_NAME_INPUT = "xpath://XCUIElementTypeStaticText[@name='Reading list name']";
        MY_LIST_OK_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Create reading list']";
        CREATE_NEW_LIST_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Create a new list']";
        GO_BACK_IOS_BUTTON = "id:Back";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        ADD_APPIUM_TO_LIST = "xpath://XCUIElementTypeStaticText[@name='Add “Appium” to a reading list?']";

    }
 public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
 }
}
