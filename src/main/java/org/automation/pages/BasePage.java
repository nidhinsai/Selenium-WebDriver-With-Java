package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * <h2>BasePage — Training Notes: Page Object Model (POM)</h2>
 *
 * <p><strong>Page Object Model (POM)</strong> is a design pattern in Selenium
 * automation where each web page (or significant component) is represented by
 * its own Java class. This brings several benefits:</p>
 * <ul>
 *   <li><strong>Maintainability</strong> — if a locator changes, you fix it in
 *       one place (the page class), not in every test that uses it.</li>
 *   <li><strong>Readability</strong> — tests read like plain English:
 *       {@code loginPage.setUsername("admin").clickLogin()}.</li>
 *   <li><strong>Reusability</strong> — the same page object can be used across
 *       many test classes.</li>
 * </ul>
 *
 * <h3>Role of BasePage</h3>
 * <p>{@code BasePage} is an <em>abstract</em> superclass that every page object
 * extends. It holds the shared {@link WebDriver} reference and provides common
 * helper methods (explicit waits, click helpers, etc.) so page classes don't
 * repeat themselves (DRY — Don't Repeat Yourself).</p>
 *
 * <h3>Two Ways to Declare Locators</h3>
 * <p>This project uses both approaches so you can compare them:</p>
 * <ol>
 *   <li><strong>By locators</strong> (used in {@code LoginPage}, {@code DropdownPage}, …):
 *       <pre>
 *   private final By usernameField = By.id("username");
 *   driver.findElement(usernameField).sendKeys("admin");
 *       </pre>
 *       Simple and clear. {@code findElement()} is called each time,
 *       so the element is always looked up fresh from the DOM.</li>
 *
 *   <li><strong>@FindBy + PageFactory</strong> (used in {@code JavaScriptAlertsPage}, …):
 *       <pre>
 *   {@literal @FindBy(id = "username")}
 *   private WebElement usernameField;
 *   // Then in constructor:
 *   PageFactory.initElements(driver, this);
 *       </pre>
 *       {@code PageFactory} initialises the annotated fields lazily — the element
 *       is found when you first access the field. More concise but requires the
 *       constructor call to {@code PageFactory.initElements()}.</li>
 * </ol>
 *
 * <h3>Locator Strategy Guide</h3>
 * <table border="1">
 *   <tr><th>Strategy</th><th>Reliability</th><th>Example</th></tr>
 *   <tr><td>ID</td><td>★★★★★ (best)</td><td>{@code By.id("username")}</td></tr>
 *   <tr><td>Name</td><td>★★★★☆</td><td>{@code By.name("password")}</td></tr>
 *   <tr><td>CSS Selector</td><td>★★★★☆</td><td>{@code By.cssSelector("#login-btn")}</td></tr>
 *   <tr><td>Link Text</td><td>★★★☆☆</td><td>{@code By.linkText("Sign In")}</td></tr>
 *   <tr><td>XPath</td><td>★★★☆☆</td><td>{@code By.xpath("//button[@type='submit']")}</td></tr>
 *   <tr><td>ClassName</td><td>★★☆☆☆ (fragile)</td><td>{@code By.className("btn")}</td></tr>
 *   <tr><td>TagName</td><td>★☆☆☆☆ (avoid)</td><td>{@code By.tagName("button")}</td></tr>
 * </table>
 *
 * <p><strong>Rule of thumb:</strong> prefer ID → CSS → XPath, in that order.</p>
 */
public abstract class BasePage {

    /**
     * The WebDriver instance shared by all methods in this page object.
     * Declared {@code protected} so subclasses can access it directly.
     */
    protected final WebDriver driver;

    /**
     * Default explicit-wait timeout used by helper methods in this class.
     * Subclasses can create their own {@link WebDriverWait} with a different
     * value when a specific page requires longer load times.
     */
    private static final int DEFAULT_WAIT_SECONDS = 10;

    /**
     * Constructs a new page object with the given WebDriver.
     *
     * <p>Subclasses that use {@code @FindBy} annotations MUST call
     * {@code PageFactory.initElements(driver, this)} at the end of their own
     * constructor to initialise those fields.</p>
     *
     * @param driver The active WebDriver session.
     */
    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // ── Explicit Wait Helpers ─────────────────────────────────────────────────
    //
    // TRAINING NOTE — Explicit Waits
    // ───────────────────────────────
    // WebDriverWait + ExpectedConditions let you wait for a specific condition
    // to become true before proceeding. This is more reliable than Thread.sleep()
    // because it stops waiting the moment the condition is met.
    //
    // Common ExpectedConditions:
    //   visibilityOfElementLocated(By)  — element is on page AND visible
    //   elementToBeClickable(By)        — visible AND enabled (can receive clicks)
    //   invisibilityOfElementLocated(By)— element gone from view (useful after modal close)
    //   textToBePresentInElement(...)   — element contains expected text
    //   titleContains(String)           — page title contains string

    /**
     * Waits until the element identified by {@code locator} is visible in the
     * viewport, then returns it.
     *
     * @param locator The {@link By} locator for the element.
     * @return The visible {@link WebElement}.
     */
    protected WebElement waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element is both visible and enabled (clickable), then
     * clicks it. Safer than calling {@code findElement().click()} directly
     * when the element might not be ready.
     *
     * @param locator The {@link By} locator for the clickable element.
     */
    protected void waitAndClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Retrieves the current page title.
     *
     * <p>Useful in assertions to verify that navigation landed on the
     * expected page: {@code assertEquals(getPageTitle(), "Welcome")}.</p>
     *
     * @return The title string of the current page.
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }
}
