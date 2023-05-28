package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language"); //instead of waitForElementAndClick for search result
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver); //ArticlePageObject initialization
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Object-oriented programming language",
                article_title
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
    public void testFindArticleTitle(){      //test always failed without timeout
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");// on the search list
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertArticleHasTitleWithoutWait();
    }
}
