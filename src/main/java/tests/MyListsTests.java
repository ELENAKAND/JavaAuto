package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Test list";
@Test
public void testSaveTwoArticlesAndDeleteOne() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");// on the search list
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement(); //to assure the article is opened
        String article_title = ArticlePageObject.getArticleTitle(); //?
    if (Platform.getInstance().isAndroid()){
        ArticlePageObject.addArticleToMyList(name_of_folder);//goes until OK button
    } else {
        ArticlePageObject.addArticlesToMySaved(name_of_folder);//save to the list for iOS
    }
    if (Platform.getInstance().isAndroid()) {
        ArticlePageObject.closeArticle(); //1st arrow-button to go back on android
        ArticlePageObject.closeArticle(); //2nd arrow-button to go back one more time (to main page) on android
    } else {
        ArticlePageObject.closeIOSArticle(); //return to the main page
    }
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_second_value = "Appium";
        SearchPageObject.typeSearchLine(search_second_value);    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");// on the search list
        ArticlePageObject.waitForArticle("Appium", "Cannot find Appium article"); //to assure the article is opened
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addAnotherArticleToMyList(); //goes until ADD TO LIST
        } else {
            ArticlePageObject.addAnotherArticleToMySaved(name_of_folder);
        }
        MyListsPageObject MylistsPageObject = MyListsPageObjectFactory.get(driver);
        String saved_folder_name = "Test list";
        MylistsPageObject.openSavedFolderByName(saved_folder_name); //click on saved list and the new article will add

        if (Platform.getInstance().isAndroid()) {
        ArticlePageObject.closeArticle(); //1st arrow-button to go back on android
        ArticlePageObject.closeArticle(); //2nd arrow-button to go back one more time (to main page) on android
        } else {
        ArticlePageObject.closeIOSArticle(); //return to the main page
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            NavigationUI.clickMySavedLists(); //contains overlay "NOT NOW" closing (what if it won't appear next time?)
        } else {
            NavigationUI.clickIOSSavedLists();
        }
        SearchPageObject.waitForSearchResult(name_of_folder); //assure the saving  LIST exists
        SearchPageObject.clickByArticleWithSubstring(name_of_folder); //click on LIST by substring
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
      //  MyListsPageObject.swipeArticleToDelete(article_title); //contains waiting of appear/disappear article_title
        ArticlePageObject.waitForArticle("Appium", "Cannot find Appium article"); //to assure the article is opened

        MyListsPageObject.swipeArticle2Delete("Appium");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language"); //click by article on the reading list
        ArticlePageObject.waitForTitleElement(); //to assure the article is opened with title
        String java_article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
        "We see unexpected title",
        "Java (programming language)",
        java_article_title
        );
        }



  @Test
  public void testSaveFirstArticleToMyList() {
      SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

      SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
      SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
      SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language"); //instead of waitForElementAndClick for search result

      ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
      ArticlePageObject.waitForTitleElement();
      String article_title = ArticlePageObject.getArticleTitle();
      if (Platform.getInstance().isAndroid()) {
          ArticlePageObject.addArticleToMyList(name_of_folder);//save to the list for Android
      } else {
          ArticlePageObject.addArticlesToMySaved(name_of_folder);//save to the list for iOS
      }
      if (Platform.getInstance().isAndroid()) {
          ArticlePageObject.closeArticle(); //1st arrow-button to go back on android
          ArticlePageObject.closeArticle(); //2nd arrow-button to go back one more time (to main page) on android
      } else {
          ArticlePageObject.closeIOSArticle(); //return to the main page
      }
      NavigationUI NavigationUI = NavigationUIFactory.get(driver);
      NavigationUI.clickMySavedLists(); //contains closing overlay

      MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
      MyListsPageObject.openSavedFolderByName(name_of_folder);//name of folder is the name of saved list
      MyListsPageObject.swipeArticleToDelete(article_title); //contains waiting of appear/disappear article_title
  }
}
