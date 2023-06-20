package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForSearchResult("Object-oriented programming language"); //instead of waitForElementPresent
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.clickCancelSearch();             //go back to the main page
    }

    @Test
    public void testClearSearchAndReturn() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testElementHasText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.waitForSearchResult("Search Wikipedia");
    }

    @Test
    public void testGetSearchResultsAndCancel() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);    //instead of waitForElementAndSendKeys
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found no results",
                amount_of_search_results > 0
        );
        SearchPageObject.clearSearchField();
        SearchPageObject.waitForSearchListIsEmpty();
    }

    @Test
    public void testSearchByWord() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForSearchResult("Java");
    }


    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);         //instead of waitForElementAndSendKeys
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        String search_line = "whdfjgkxkan";
        SearchPageObject.typeSearchLine(search_line);          //instead of waitForElementAndSendKeys
        SearchPageObject.waitForEmptyResultsLabel();
        if (Platform.getInstance().isIOS()) {
            return; //can't define specific locator for iOS
        } else {
            SearchPageObject.assertThereIsNoResultOfSearch();
        }
    }
}

