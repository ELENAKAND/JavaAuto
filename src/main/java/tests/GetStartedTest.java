package tests;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void testPassThroughWelcome(){
        if (this.Platform.isAndroid()) {
            return;
        }
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
