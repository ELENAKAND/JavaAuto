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
    @Test            //EX #3 (REFACTORED)
    public void testGetSearchResultsAndCancel() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);    //instead of waitForElementAndSendKeys
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found no results",
                amount_of_search_results > 0
        );
        SearchPageObject.clickCrossCancelButton();
        SearchPageObject.waitForSearchListIsEmpty();
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
    @Test        //EX #5 (REFACTORED)
    public void testSaveTwoArticlesAndDeleteOne() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");// on the search list
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement(); //to assure the article is opened
        String article_title = ArticlePageObject.getArticleTitle(); //?
        String name_of_folder = "Test list";
        ArticlePageObject.addArticleToMyList(name_of_folder);//goes until OK button
        SearchPageObject.clickCancelSearchTwice(); //go back to main page
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_second_value = "Appium";
        SearchPageObject.typeSearchLine(search_second_value);    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");// on the search list
        ArticlePageObject.waitForTitleElement(); //to assure the article is opened
        ArticlePageObject.addAnotherArticleToMyList(); //goes until ADD TO LIST
        MyListsPageObject MylistsPageObject = new MyListsPageObject(driver);
        String saved_folder_name = "Test list";
        MylistsPageObject.openSavedFolderByName(saved_folder_name); //click on saved list and the new article will add
        SearchPageObject.clickCancelSearchTwice(); //go back to main page
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMySavedLists(); //contains overlay "NOT NOW" closing (what if it won't appear next time?)
        SearchPageObject.waitForSearchResult(name_of_folder); //assure the saving  LIST exists
        SearchPageObject.clickByArticleWithSubstring(name_of_folder); //click on LIST by substring
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.swipeArticleToDelete(article_title); //contains waiting of appear/disappear article_title
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps"); //click by article on the reading list
        ArticlePageObject.waitForTitleElement(); //to assure the article is opened with title
        String appium_article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title",
                "Automation for Apps",
                appium_article_title
        );
    }

    @Test               //EX #6 (REFACTORED)
    public void testFindArticleTitle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");// on the search list
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertArticleHasTitleWithoutWait();
    }
}

