import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;




public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();       //access to junit setUp()
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find string Object-oriented...",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot click arrow-sign",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.id("Navigate up"),
                "Arrow-sign still here",
                5
        );

    }

    @Test
    public void testCompareArticleTitle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"), //container id+subtitle text
                "Cannot find Java search result",
                20
        );
        WebElement title_element = MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"), //Article's title-id
                "Cannot find article title",
                15
        );
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );

    }

    @Test
    public void testClearSearchAndReturn() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send input",
                5
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find Arrow button",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.id("Navigate up"),
                "Arrow button still here",
                5
        );
    }


    @Test
    public void testElementHasText() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                "Search Wikipedia"
        );
    }

    @Test
    public void testGetSearchResultsAndCancel() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send input",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_description"),
                "Cannot find className elements"
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot click x button",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_description"),
                "Search results still here",
                5
        );
    }

    @Test
    public void testSearchByWord() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send input",
                5
        );
        MainPageObject.assertListTitlesHaveText(
                By.xpath("//*[@text='Java']"),
                "Cannot find text Java",
                "Java"
        );
        MainPageObject.assertListTitlesHaveText(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find text Java",
                "Java"
        );
        MainPageObject.assertListTitlesHaveText(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find text Java",
                "Java"
        );

    }

    @Test
    public void testSwipeArticleTitle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot send input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"), //res-id+ title text in one
                "Cannot find Appium search result",
                20
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Appium']"), //Article's title-id
                "Cannot find article title",
                15
        );
        MainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find footer element",
                20
        );
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"), //container id+subtitle text
                "Cannot find Java search result",
                20
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"), //to be sure article is opened
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='WORA']"),
                "Cannot find SAVE button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find ADD TO LIST button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find NAME OF LIST input field",
                5
        );
        String folder_name = "Test list";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folder_name,
                "Cannot send input to the name of list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find OK button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find arrow-button to go back after saving list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find 2nd arrow-button to go back again",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Saved']"),
                "Cannot find SAVED button on the main page",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/negativeButton"),
                "Cannot find NOT NOW button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + folder_name + "']"),
                "Cannot find list name in the SAVED-folder",
                5
        );
        //includes waitForElementPresent (to make sure the article on the list)
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find saved article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Saved article still on the list",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        String search_line = "Linkin Park discography";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find search result \n" + search_line,
                15
        );
        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator)
        );
        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        String search_line = "whdfjgkxkan";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";
        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label for " + search_line,
                15
        );
        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"), //container id+subtitle text
                "Cannot find search result, searching by " + search_line,
                15
        );
        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article was changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article was changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"), //container id+subtitle text
                "Cannot find Java search result",
                5
        );
        driver.runAppInBackground(5);
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Object-oriented programming language']"), //container id+subtitle text
                "Cannot find article after returning from background",
                2
        );
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        String search_value = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                search_value,
                "Cannot send input value " + search_value,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"), //container id+subtitle text
                "Cannot find Java search result",
                20
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"), //to be sure article is opened
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='WORA']"),
                "Cannot find SAVE button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find ADD TO LIST button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find NAME OF LIST input field",
                5
        );
        String folder_name = "Test list";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folder_name,
                "Cannot send input to the name of list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find OK button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find arrow-button to go back after saving list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find 2nd arrow-button to go back again",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        String search_second_value = "Appium";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                search_second_value,
                "Cannot send input for " + search_second_value,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"), //res-id+ title text in one
                "Cannot find Appium search result",
                20
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Appium']"), //Article's title-id
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Node.js']"),
                "Cannot find SAVE button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find ADD TO LIST button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title_container"),
                "Cannot find Test list folder",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='VIEW LIST']"),
                "Cannot find Test list folder",
                5
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find saved article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Saved article still on the list",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Appium']"), //Article's title-id
                "Cannot find article title",
                15
        );
        String element_attribute_list = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='Appium']"),
                "text",
                "Cannot get attribute",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Appium']"),
                "Cannot find Appium on the saved list",
                5
        );
        String element_attribute_article = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='Appium']"),
                "text",
                "Cannot get attribute",
                5
        );
        Assert.assertEquals(
                "Article's attribute are different",
                element_attribute_list,
                element_attribute_article
        );
    }

    @Test
    public void testArticleHasTitle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        String search_value = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                search_value,
                "Cannot send input value " + search_value,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find the article " + search_value + " on the search list",
                5
        );
        MainPageObject.assertElementPPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article title",
                "text",
                "Java (programming language)"
        );
    }
    @Test
    public void testFindArticleTitle(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        String search_value = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                search_value,
                "Cannot send input value " + search_value,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find the article " + search_value + " on the search list",
                5
        );
       MainPageObject.assertElementPresent(
               By.xpath("//*[@text='Java (programming language)']"),
               "Cannot find"
       );

    }
}

