package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";
    @Override
    protected void setUp() throws Exception{
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14 Plus Simulator");
        capabilities.setCapability("platformVersion", "16.4");
        capabilities.setCapability("udid", "F0F248BE-F535-4BC8-8191-F36AFA7247EC");
        capabilities.setCapability("app", "/Users/elenakandaurova/Desktop/JavaAppAuto/JavaAuto/apks/Wikipedia693.app");
        capabilities.setCapability("automationName", "XCUITest");

        driver = new IOSDriver(new URL(AppiumURL), capabilities);
        this.rotateScreenPortrait();
    }
    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    protected void backgroundApp(Duration seconds){
        driver.runAppInBackground(seconds);
    }
}
