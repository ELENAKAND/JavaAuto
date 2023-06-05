package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
    TITLE = "id:pcs-edit-section-title-description",
    FOOTER_ELEMENT = "xpath://*[@text='View article in browser']",
    SAVE_BUTTON = "xpath://*[@text='WORA']",
    SAVE_BUTTON_SECOND_SAVING = "xpath://*[@text='Node.js']",
    ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action",
    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "id:android:id/button1",
    GO_BACK_ARROW_BUTTON = "xpath://*[@class='android.widget.ImageButton'][@content-desc='Navigate up']";



    public ArticlePageObject(AppiumDriver driver){      //driver initialization
        super(driver);
    }
    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }
    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter(){
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                20
        );
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
}
