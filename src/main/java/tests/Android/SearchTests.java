package main.java.tests.Android;

import main.java.lib.CoreTestCase;
import main.java.lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForSearchResult("Object-oriented programming language"); //instead of waitForElementPresent
    }
    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.clickCancelSearch();             //go back to the main page
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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.waitForSearchResult("Search Wikipedia");
    }
    @Test            //EX #3 (REFACTORED)
    public void testGetSearchResultsAndCancel() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);    //instead of waitForElementAndSendKeys
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found no results",
                amount_of_search_results > 0
        );
        SearchPageObject.clickCrossCancelButton();
        SearchPageObject.waitForSearchListIsEmpty();
    }
    @Test
    public void testSearchByWord() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForSearchResult("Java");
        /*
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
        );*/
    }


    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        String search_line = "whdfjgkxkan";
        SearchPageObject.typeSearchLine(search_line);          //instead of waitForElementAndSendKeys
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}

