package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        SAVE_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
      //  SAVE_BUTTON_SECOND_SAVING = "xpath://*[@text='Node.js']";
     //   ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action";
    //    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
    //    MY_LIST_OK_BUTTON = "id:android:id/button1";
        GO_BACK_ARROW_BUTTON = "xpath://XCUIElementTypeButton[@name='Back']";
    }
 public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
 }
}
