import lib.CoreTestCase;
import lib.ui.*;
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForSearchResult("Object-oriented programming language"); //instead of waitForElementPresent
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language"); //instead of waitForElementAndClick for search result
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver); //ArticlePageObject initialization
        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title",
                "Object-oriented programming language",
                article_title
        );
    }
    @Test
    public void testClearSearchAndReturn() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Appium");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language"); //instead of waitForElementAndClick for search result
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test list";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle(); //1st arrow-button to go back
        ArticlePageObject.closeArticle(); //2nd arrow-button to go back one more time (to main page)
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMySavedLists(); //contains overlay "NOT NOW" closing (what if it won't appear next time?)
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openSavedFolderByName(name_of_folder);
        MyListsPageObject.swipeArticleToDelete(article_title); //contains waiting of appear/disappear article_title

    }
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);         //instead of waitForElementAndSendKeys
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }
    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        String search_line = "whdfjgkxkan";
        SearchPageObject.typeSearchLine(search_line);          //instead of waitForElementAndSendKeys
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language"); //instead of waitForElementAndClick for search result

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article was changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article was changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }
    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        SearchPageObject.waitForSearchResult("Object-oriented programming language"); //instead of waitForElementAndClick for search result
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
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

