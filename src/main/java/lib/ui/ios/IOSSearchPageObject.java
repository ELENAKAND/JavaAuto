package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
    //    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";// or [contains(@name,'{SUBSTRING}')];
        SEARCH_RESULT_BY_SUBSTRING_TPL = "id:{SUBSTRING}";

        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText";
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
        GO_BACK_ARROW_BUTTON = "id:Back"; //"xpath://XCUIElementTypeButton[@name='Back']";
        CLEAR_TEXT = "xpath://XCUIElementTypeButton[@name='Clear text']";
    }
    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
