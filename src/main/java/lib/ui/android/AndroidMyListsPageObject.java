package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static{
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']"; // {section easy to change} by .replace() method
       // ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        ARTICLE_TITLE_TPL = "xpath://*[@text='{SUBSTRING}']";
        APPIUM_LOCATOR = "xpath://*[@text='Appium']";

     //   OVERLAY_BUTTON = "id:org.wikipedia:id/negativeButton";

    }
public AndroidMyListsPageObject(AppiumDriver driver){
        super(driver);
}
}
