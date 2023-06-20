package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;


abstract public class MyListsPageObject extends MainPageObject{
    protected static  String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_TITLE_TPL,
            READING_LIST_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            CLICK_BY_ARTICLE,
            OVERLAY_BUTTON,
            TRASH_BUTTON,
            APPIUM_LOCATOR,
            SAVED_LIST_BUTTON;
    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    private static String getSavedArticleXpath(String article){
        return ARTICLE_TITLE_TPL.replace("{SUBSTRING}", article);
    }
    public static String getResultElement(String substring){   //Method won't interact with driver, just convert strings, so we can not to use driver and make method static
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    public MyListsPageObject(AppiumDriver driver){      //constructor
        super(driver);
    }
    public void openSavedFolderByName(String name_of_folder){
        if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(SAVED_LIST_BUTTON, "Cannot find reading list button", 5);
        } else {
            String folder_name_xpath = getFolderXpathByName(name_of_folder);
            this.waitForElementAndClick(
                    folder_name_xpath,
                    "Cannot find folder by name in the SAVE " + name_of_folder,
                    5
            );
        }
    }
    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by  title "+article_title,
                15);

    }
    public void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title "+article_title,
                15);

    }
    public void swipeArticleToDelete(String article_title){
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        if (Platform.getInstance().isIOS()){
        //    this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
            this.waitForElementAndClick(TRASH_BUTTON, "Cannot find trash icon", 5);
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
    public void swipeArticle2Delete(String substring){
    //    this.waitForArticle(substring, "Cannot find article");
        String article = this.getSavedArticleXpath(substring);
        this.swipeElementToLeft(article, "Cannot find to swipe");
        if (Platform.getInstance().isIOS()){
            //    this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
            this.waitForElementAndClick(TRASH_BUTTON, "Cannot find trash icon", 5);
        }
    }
}
