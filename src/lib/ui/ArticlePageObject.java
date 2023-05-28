package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
    TITLE = "pcs-edit-section-title-description",
    FOOTER_ELEMENT = "//*[@text='View article in browser']",
    SAVE_BUTTON = "//*[@text='WORA']",
    ADD_TO_LIST = "org.wikipedia:id/snackbar_action",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "android:id/button1",
    GO_BACK_ARROW_BUTTON = "Navigate up";



    public ArticlePageObject(AppiumDriver driver){      //driver initialization
        super(driver);
    }
    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }
    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter(){
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }
    public void addArticleToMyList(String folder_name){
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find SAVE button",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST),
                "Cannot find ADD TO LIST button",
                5
        );
        this.waitForElementAndClick(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find NAME OF LIST input field",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                folder_name,
                "Cannot send input to the name of list",
                5
        );
        this.waitForElementAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "Cannot find OK button",
                5
        );
    }
    public void closeArticle(){            //click arrow-button to go back
        this.waitForElementAndClick(
                By.id(GO_BACK_ARROW_BUTTON),
                "Cannot find arrow-button to go back after saving list",
                5
        );
    }
}
