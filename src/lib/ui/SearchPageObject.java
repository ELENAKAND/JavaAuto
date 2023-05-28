package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;


public class SearchPageObject extends MainPageObject{  //Created for search methods
    private static final String                        //private because we will use these CONSTANTS in this file only
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]",
        SEARCH_CANCEL_BUTTON = "Navigate up",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']";

    public SearchPageObject(AppiumDriver driver){      //Driver initialization from MainPageObject
        super(driver);
    }
    /*TEMPLATE METHODS:*/
    private static String getResultSearchElement(String substring){   //Method won't interact with driver, just convert strings, so we can not to use driver and make method static
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
        }
    /**/
    public void initSearchInput(){
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }
    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring "+substring);
    }
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button still present", 5);
    }
    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring "+substring, 10);
    }
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find search result",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }
    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15
        );
    }
    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results"
        );
    }
}
