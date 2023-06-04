package main.java.tests.iOS;

import main.java.lib.iOSTestCase;
import main.java.lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {
    @Test
    public void testPassThroughWelcome(){
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForMainPage();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForSearchInLangText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForMakeTheAppBetterText();
        WelcomePageObject.clickGetStartedButton();
    }
}
