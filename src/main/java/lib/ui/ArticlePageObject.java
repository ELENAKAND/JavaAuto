package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            SAVE_BUTTON_SECOND_SAVING,
            ADD_TO_LIST,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            GO_BACK_ARROW_BUTTON,
            SEARCH_CANCEL_BUTTON,
            NO_THANKS_OVERLAY_BUTTON_ANDROID;

    public ArticlePageObject(AppiumDriver driver) {      //driver initialization
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");

        }
    }
    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        } else{
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        }
    }

        public void addArticleToMyList(String folder_name){
            this.waitForElementAndClick(
                    SAVE_BUTTON,
                    "Cannot find SAVE button",
                    5
            );
            this.waitForElementAndClick(
                    ADD_TO_LIST,
                    "Cannot find ADD TO LIST button",
                    5
            );
            this.waitForElementAndClick(
                    MY_LIST_NAME_INPUT,
                    "Cannot find NAME OF LIST input field",
                    5
            );
            this.waitForElementAndSendKeys(
                    MY_LIST_NAME_INPUT,
                    folder_name,
                    "Cannot send input to the name of list",
                    5
            );
            this.waitForElementAndClick(
                    MY_LIST_OK_BUTTON,
                    "Cannot find OK button",
                    5
            );
        }
        public void addArticlesToMySaved(String folder_name){
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find option to add article to reading list",5);
            this.waitForElementAndClick(CREATE_NEW_LIST_BUTTON, "Cannot find Create new list",5);
            this.waitForElementAndClick(MY_LIST_NAME_INPUT, "Cannot find Create new list",5);
            this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, folder_name, "Cannot send input to the name of list", 5);
            this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot find Create new list",5);
        }

        public void addAnotherArticleToMyList(){
            this.waitForElementAndClick(
                    SAVE_BUTTON_SECOND_SAVING,
                    "Cannot find second-save button ",
                    5
            );
            this.waitForElementAndClick(
                    ADD_TO_LIST,
                    "Cannot find ADD TO LIST button",
                    5
            );
        }
        public void closeArticle(){            //click arrow-button to go back
            this.waitForElementAndClick(
                    GO_BACK_ARROW_BUTTON,
                    "Cannot find arrow-button to go back after saving list",
                    5
            );
        }
        public void closeIOSArticle(){
            this. waitForElementAndClick(
                    GO_BACK_ARROW_BUTTON,
                    "Cannot find arrow-button to go back after saving list",
                    5
            );
            this. waitForElementAndClick(
                    SEARCH_CANCEL_BUTTON,
                    "Cannot find arrow-button to go back after saving list",
                    5
            );
        }


}
