package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://*[@text='Java (programming language)']";

        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        SAVE_BUTTON_SECOND_SAVING = "xpath://*[@text='Node.js']";
        ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "id:android:id/button1";
        GO_BACK_ARROW_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
      //  GO_BACK_ARROW_BUTTON = "id:Navigate up";

    //    NO_THANKS_OVERLAY_BUTTON_ANDROID = "id:android:id/button2"; //click Save icon from main page with already saved article
    }
    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
