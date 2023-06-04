package main.java.tests.Android;

import main.java.lib.CoreTestCase;
import main.java.lib.ui.ArticlePageObject;
import main.java.lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
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
        assertEquals(
                "Article was changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
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
      //  this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
