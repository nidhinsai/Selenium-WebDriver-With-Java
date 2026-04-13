package org.automation.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * <h2>WebDriverProvider — Training Notes</h2>
 *
 * <p>This class is responsible for <em>creating</em> and <em>configuring</em> a
 * {@link WebDriver} instance. It follows the
 * <strong>Factory pattern</strong>: callers ask for "a driver" without knowing
 * the details of how it is constructed.</p>
 *
 * <h3>Why a separate class?</h3>
 * <ul>
 *   <li>Keeps test classes clean — they receive a ready-to-use driver.</li>
 *   <li>Changing the browser only requires updating the string passed in,
 *       not touching every test file.</li>
 *   <li>All driver configuration (timeouts, window size) is in one place.</li>
 * </ul>
 *
 * <h3>Supported browsers</h3>
 * <ul>
 *   <li>{@code "chrome"}   — Google Chrome (most common for automation)</li>
 *   <li>{@code "firefox"}  — Mozilla Firefox</li>
 *   <li>{@code "safari"}   — Apple Safari (macOS only, no WebDriverManager setup needed)</li>
 * </ul>
 *
 * <h3>Implicit Wait vs Explicit Wait</h3>
 * <p>This class sets an <strong>implicit wait</strong> of 10 seconds.
 * This tells WebDriver to poll the DOM for up to 10 seconds before throwing
 * a {@code NoSuchElementException} when an element is not immediately found.</p>
 * <ul>
 *   <li><strong>Implicit wait</strong> — global setting, applies to every
 *       {@code findElement()} call automatically.</li>
 *   <li><strong>Explicit wait</strong> ({@code WebDriverWait}) — applied to a
 *       specific condition (e.g., element visible, clickable). Preferred for
 *       complex scenarios because it is more precise.</li>
 *   <li><strong>Fluent wait</strong> — like explicit wait but you can also
 *       control polling frequency and ignore specific exceptions.</li>
 * </ul>
 * <p>⚠️ Mixing implicit and explicit waits can cause unpredictable behaviour.
 * Use one strategy consistently across your project.</p>
 */
public class WebDriverProvider {

    /** The WebDriver instance managed by this provider. */
    private final WebDriver driver;

    /**
     * Creates a new {@code WebDriverProvider} for the specified browser.
     *
     * <p>WebDriverManager automatically downloads the matching browser driver
     * binary so you do <em>not</em> need to call
     * {@code System.setProperty("webdriver.chrome.driver", "...")} manually.</p>
     *
     * @param browser The browser to launch. Accepted values (case-insensitive):
     *                {@code "chrome"}, {@code "firefox"}, {@code "safari"}.
     * @throws IllegalArgumentException if an unsupported browser name is given.
     */
    public WebDriverProvider(String browser) {
        this.driver = initializeDriver(browser);
        configureDriver(this.driver);
    }

    /**
     * Uses a {@code switch} expression (Java 14+) to instantiate the correct
     * driver. Each branch:
     * <ol>
     *   <li>Calls {@code WebDriverManager.xxxdriver().setup()} to handle the
     *       driver binary automatically.</li>
     *   <li>Returns a new driver instance.</li>
     * </ol>
     *
     * <p><strong>Training Note — ChromeOptions / FirefoxOptions:</strong><br>
     * {@code ChromeOptions} lets you customise the browser before it launches:
     * headless mode, disable extensions, custom profile, proxy settings, etc.
     * They are passed to the driver constructor.</p>
     *
     * @param browser Browser name string.
     * @return A configured {@link WebDriver} instance.
     */
    private WebDriver initializeDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                // Uncomment the line below to run without opening a browser window:
                // options.addArguments("--headless=new");
                // Useful in CI environments where there is no display available.
                yield new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                // options.addArguments("--headless");
                yield new FirefoxDriver(options);
            }
            case "safari" -> {
                // Safari uses Apple's built-in driver; no WebDriverManager setup needed.
                // Requires: Safari → Develop → Allow Remote Automation (enabled in Settings).
                WebDriverManager.safaridriver().setup();
                yield new SafariDriver();
            }
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: \"" + browser + "\". " +
                    "Valid options: chrome, firefox, safari");
        };
    }

    /**
     * Applies global driver settings that should be consistent for all tests.
     *
     * <h4>Settings applied:</h4>
     * <ul>
     *   <li><strong>Implicit Wait (10 s)</strong> — WebDriver will retry
     *       {@code findElement()} for up to 10 seconds before failing.
     *       This handles elements that load with a slight delay.</li>
     *   <li><strong>Maximise window</strong> — ensures locators that depend on
     *       viewport size work consistently across machines.</li>
     * </ul>
     *
     * @param driver The driver instance to configure.
     */
    private void configureDriver(WebDriver driver) {
        // Implicit wait: retries findElement() for up to 10 seconds.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    /**
     * Returns the {@link WebDriver} instance created by this provider.
     *
     * @return The active WebDriver instance.
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Safely quits the browser and ends the WebDriver session.
     *
     * <p>{@code driver.quit()} is preferred over {@code driver.close()} because:
     * <ul>
     *   <li>{@code close()} only closes the current browser window/tab.</li>
     *   <li>{@code quit()} closes all windows and releases all resources
     *       (including the driver process). Always call this in {@code @AfterClass}
     *       to prevent driver processes from accumulating.</li>
     * </ul>
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
