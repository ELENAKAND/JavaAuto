import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/elenakandaurova/Desktop/JavaAppAuto/JavaAuto/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebElement element = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find string Object-oriented...",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot click arrow-sign",
                5
        );
        waitForElementNotPresent(
                By.id("Navigate up"),
                "Arrow-sign still here",
                5
        );

    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"), //container id+subtitle text
                "Cannot find Java search result",
                20
        );
        WebElement title_element = waitForElementPresent(
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
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send input",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                10
        );
        waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find Arrow button",
                5
        );
        waitForElementNotPresent(
                By.id("Navigate up"),
                "Arrow button still here",
                5
        );
    }


    @Test
    public void testElementHasText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                "Search Wikipedia"
        );
    }

    @Test
    public void testGetSearchResultsAndCancel() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send input",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_description"),
                "Cannot find className elements"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot click x button",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_description"),
                "Search results still here",
                5
        );
    }

    @Test
    public void testSearchByWord() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search container",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send input",
                5
        );
        assertListTitlesHaveText(
                By.xpath("//*[@text='Java']"),
                "Cannot find text Java",
                "Java"
        );
        assertListTitlesHaveText(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find text Java",
                "Java"
        );
        assertListTitlesHaveText(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find text Java",
                "Java"
        );

    }

    @Test
    public void testSwipeArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot send input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"), //res-id+ title text in one
                "Cannot find Appium search result",
                20
        );
        waitForElementPresent(
                By.xpath("//*[@text='Appium']"), //Article's title-id
                "Cannot find article title",
                15
        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find footer element",
                20
        );
    }
    @Test
    public void saveFirstArticleToMyList(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@class='android.view.ViewGroup']//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot send input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"), //container id+subtitle text
                "Cannot find Java search result",
                20
        );
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"), //to be sure article is opened
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@text='WORA']"),
                "Cannot find SAVE button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find ADD TO LIST button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find NAME OF LIST input field",
                5
        );
        String folder_name = "Test list";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folder_name,
                "Cannot send input to the name of list",
                5
        );
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find OK button",
                5
        );
        waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find arrow-button to go back after saving list",
                5
        );
        waitForElementAndClick(
                By.id("Navigate up"),
                "Cannot find 2nd arrow-button to go back again",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Saved']"),
                "Cannot find SAVED button on the main page",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/negativeButton"),
                "Cannot find NOT NOW button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='" + folder_name +"']"),
                "Cannot find list name in the SAVED-folder",
                5
        );

        //includes waitForElementPresent (to make sure the article on the list)
        swipeElementToLeft(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Saved article still on the list",
                5
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement assertElementHasText(By by, String error_message, String text) {
        WebElement text_element = waitForElementPresent(by, error_message, 5);
        String field_has_text = text_element.getAttribute("text");
        Assert.assertEquals(error_message, text, field_has_text);
        return text_element;
    }

    private WebElement assertListTitlesHaveText(By by, String error_message, String text) {
        WebElement text_element = waitForElementPresent(by, error_message, 5);
        String field_has_text = text_element.getAttribute("text");
        Assert.assertTrue(error_message, field_has_text.contains(text));
        return text_element;
    }
    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }
    protected void swipeUpQuick(){
        swipeUp(200);
    }
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size()==0){
            if(already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up.\n"+error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX(); //find left element edge  on X
        int right_x = left_x + element.getSize().getWidth();//add width of element and get right edge
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}

