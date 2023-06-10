package lib;
import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import lib.ui.WelcomePageObjectAndroid;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import java.time.Duration;


public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.skipWelcomePageForAndroid();
      //  WebElement element = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
      //  element.click();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(Duration seconds) {
        driver.runAppInBackground(seconds);
    }
    private void skipWelcomePageForIOSApp(){
        if (Platform.getInstance().isIOS()){
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
    private void skipWelcomePageForAndroid(){
        if (Platform.getInstance().isAndroid()){
            WelcomePageObjectAndroid WelcomePageObjectAndroid = new WelcomePageObjectAndroid(driver);
            WelcomePageObjectAndroid.clickSkipButtonAndroid();
        }
    }
}

