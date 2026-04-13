package testcases;

import org.automation.helpers.WebDriverProvider;
import org.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * <h2>BaseTest — Training Notes: Test Lifecycle with TestNG</h2>
 *
 * <p>{@code BaseTest} is the parent class for every test class in this project.
 * Any test class that extends it automatically inherits browser setup and teardown,
 * so each class focuses only on its own test logic.</p>
 *
 * <h3>TestNG Lifecycle Annotations</h3>
 * <p>TestNG controls <em>when</em> methods run using annotations:</p>
 * <pre>
 *  @BeforeSuite    — once before the entire test suite
 *  @BeforeClass    — once before all tests in THIS class
 *  @BeforeMethod   — before EACH individual @Test method
 *  @Test           — the actual test
 *  @AfterMethod    — after EACH individual @Test method
 *  @AfterClass     — once after all tests in THIS class
 *  @AfterSuite     — once after the entire test suite
 * </pre>
 * <p>This class uses {@code @BeforeClass} to start the browser once per test
 * class (efficient — not opening/closing Chrome 10 times), and
 * {@code @BeforeMethod} to navigate to the base URL before each test (clean
 * starting state).</p>
 *
 * <h3>Why static driver?</h3>
 * <p>The driver is declared {@code static} so that all test methods within
 * a subclass share the same browser session.
 * ⚠️ This is fine for sequential tests, but for <em>parallel</em> execution
 * each thread would need its own driver (e.g., via {@code ThreadLocal<WebDriver>}).</p>
 *
 * <h3>Why protected (not private)?</h3>
 * <p>{@code protected} means subclasses can access the field or method
 * directly. {@code private} would hide it from subclasses.</p>
 */
public class BaseTest {

    /**
     * The shared WebDriver instance.
     * Static so one browser window is reused for all tests in a subclass.
     */
    private static WebDriver driver;

    /** Manages driver lifecycle (creation, configuration, teardown). */
    private static WebDriverProvider webDriverProvider;

    /**
     * The entry point to the application under test.
     * Protected so subclass test methods can call its navigation methods directly.
     */
    protected static HomePage homePage;

    /**
     * Initialises the browser and creates page objects before any test in the
     * class runs.
     *
     * <p>{@code alwaysRun = true} ensures this runs even if the test is in a
     * group that is not selected — prevents NPE on the driver in teardown.</p>
     *
     * <p>To switch browsers, change the string here:
     * {@code "chrome"}, {@code "firefox"}, or {@code "safari"}.</p>
     *
     * <p><strong>Training Tip:</strong> In a real project you would read the
     * browser name from a system property so it can be set at runtime without
     * changing code:
     * <pre>
     *   String browser = System.getProperty("browser", "chrome");
     *   webDriverProvider = new WebDriverProvider(browser);
     * </pre>
     * Then run with: {@code mvn test -Dbrowser=firefox}
     * </p>
     */
    @BeforeClass(alwaysRun = true)
    protected void classSetup() {
        // "chrome" — change to "firefox" or "safari" as needed
        webDriverProvider = new WebDriverProvider("chrome");
        driver = webDriverProvider.getDriver();
        homePage = new HomePage(driver);
    }

    /**
     * Navigates to the test application's base URL before each test method.
     *
     * <p>This guarantees every test starts from a known, clean state regardless
     * of where the previous test left the browser.</p>
     *
     * <p>The application under test is <strong>The Internet</strong> by Dave Haeffner:
     * a deliberately simple web app with common UI patterns used to practice automation.
     * 🔗 https://the-internet.herokuapp.com</p>
     */
    @BeforeMethod
    protected void openTestUrl() {
        openUrl("https://the-internet.herokuapp.com");
    }

    /**
     * Quits the browser and frees all resources after every test in the class.
     *
     * <p>{@code alwaysRun = true} ensures this runs even if a test or
     * {@code @BeforeClass} throws an exception, preventing orphaned browser
     * processes.</p>
     */
    @AfterClass(alwaysRun = true)
    protected void tearDown() {
        webDriverProvider.quitDriver();
    }

    /**
     * Navigates the browser to the given URL.
     *
     * <p>{@code driver.get(url)} is equivalent to typing the URL in the address
     * bar and pressing Enter. It blocks until the page fires the
     * {@code document.readyState == "complete"} event.</p>
     *
     * @param url The full URL to navigate to (including https://).
     */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Exposes the WebDriver instance to subclasses that need low-level access
     * (e.g., switching frames, executing JavaScript).
     *
     * @return The active {@link WebDriver} session.
     */
    protected WebDriver getDriver() {
        return driver;
    }
}
