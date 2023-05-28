package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {         //created for tests methods
    protected AppiumDriver driver;   //driver initialization
    public MainPageObject(AppiumDriver driver){      //class constructor the tests will access
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement assertElementHasText(By by, String error_message, String text) {
        WebElement text_element = waitForElementPresent(by, error_message, 5);
        String field_has_text = text_element.getAttribute("text");
        Assert.assertEquals(error_message, text, field_has_text);
        return text_element;
    }

    public WebElement assertListTitlesHaveText(By by, String error_message, String text) {
        WebElement text_element = waitForElementPresent(by, error_message, 5);
        String field_has_text = text_element.getAttribute("text");
        Assert.assertTrue(error_message, field_has_text.contains(text));
        return text_element;
    }
    public void swipeUp(int timeOfSwipe){
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
    public void swipeUpQuick(){
        swipeUp(200);
    }
    public void swipeUpToFindElement(By by, String error_message, int max_swipes){
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
    public void swipeElementToLeft(By by, String error_message){
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
    public int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void assertElementNotPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements>0){
            String default_message = "An element '"+ by.toString()+"' supposed to be not present";
            throw new AssertionError(default_message + "" +error_message);
        }
    }
    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    public WebElement assertElementPPresent(By by, String error_message, String attribute, String expected_text) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String actual_text = element.getAttribute(attribute); //attribute should be found
        Assert.assertEquals(error_message, expected_text, actual_text);
        return element;
    }
    public String findArticle(By by){
        String article_present = null;
        article_present = driver.findElement(by).toString();
        return article_present;
    }
    public void assertElementPresent(By by, String error_message){
        String title_present = findArticle(by);
        Assert.assertTrue("Cannot find article title", title_present!=null);
    }
}
