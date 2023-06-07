package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
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

        assertEquals(
                "We see unexpected title",
                "Automation for Apps",
                appium_article_title
        );
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

}
