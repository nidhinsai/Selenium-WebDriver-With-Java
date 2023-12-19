/**
 * BaseTest class serves as a foundational structure for test classes using WebDriver for test automation.
 * It handles the initialization and cleanup of WebDriver instances for tests.
 */
package testcases;

import org.automation.helpers.WebDriverProvider;
import org.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private static WebDriver driver;
    private static WebDriverProvider webDriverProvider;
    protected static HomePage homePage;

    /**
     * Initializes the WebDriver instance before the test class execution.
     * Uses a WebDriverProvider to set up a WebDriver instance based on the Safari browser.
     * Sets up the WebDriver as a static variable accessible to test classes extending BaseTest.
     */
    @BeforeClass(alwaysRun = true)
    protected void classSetup() {
        webDriverProvider = new WebDriverProvider("chrome");
        driver = webDriverProvider.getDriver();
        homePage = new HomePage(driver);
    }

    /**
     * Opens the test URL before each test method
     */
    @BeforeMethod()
    protected void openTestUrl() {
        openUrl("https://the-internet.herokuapp.com");
    }

    /**
     * Cleans up and quits the WebDriver instance after all the test class methods have executed.
     * Invoked automatically after all the test methods in the class are run.
     */
    @AfterClass(alwaysRun = true)
    protected void tearDown() {
        webDriverProvider.quitDriver();
    }

    /**
     * Opens the specified URL in the WebDriver instance.
     *
     * @param url the URL to open in the browser.
     */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Provides access to the WebDriver instance.
     *
     * @return the WebDriver instance.
     */
    protected WebDriver getDriver() {
        return webDriverProvider.getDriver();
    }
}
