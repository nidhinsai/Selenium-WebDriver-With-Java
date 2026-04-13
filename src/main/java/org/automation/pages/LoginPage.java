package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * <h2>LoginPage — Training Notes: Locator Robustness and Form Interaction</h2>
 *
 * <p>Represents the Form Authentication page at
 * {@code https://the-internet.herokuapp.com/login}.
 * Valid credentials: username {@code admin}, password {@code admin}.</p>
 *
 * <h3>Locator Design Principles</h3>
 * <p>Locators should be:</p>
 * <ol>
 *   <li><strong>Unique</strong> — matches exactly one element</li>
 *   <li><strong>Stable</strong> — does not change with styling or minor refactors</li>
 *   <li><strong>Descriptive</strong> — readable without a comment</li>
 * </ol>
 *
 * <h3>XPath Caution</h3>
 * <p>Original code used:
 * <pre>{@code By.xpath("//button[@type='submit']/i[text()=' Login']")}</pre>
 * That XPath is fragile: the text {@code " Login"} starts with a space. If the
 * HTML is rendered with different whitespace (e.g. {@code "Login"} without the
 * leading space), the locator silently fails.<br>
 * <strong>Fixed to:</strong> {@code By.xpath("//button[@type='submit']")} —
 * relies on the stable {@code type} attribute instead of rendered text.</p>
 *
 * <h3>sendKeys vs clear + sendKeys</h3>
 * <p>{@code clear()} removes any pre-filled text before typing. Always call it
 * before {@code sendKeys()} on an input that might retain state between tests
 * (e.g., browser auto-fill).</p>
 *
 * <h3>Return-new-page pattern</h3>
 * <p>{@code clickLogin()} returns a {@link SecureAreaPage} on success. The
 * test doesn't need to know how to reach that page — the page object
 * encapsulates the navigation.</p>
 */
public class LoginPage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────────────────
    //
    // By.id is the most reliable strategy: IDs are guaranteed unique per HTML spec.
    // Falls back to XPath for the submit button because it has no id attribute.

    /** The username input field — located by its stable HTML id attribute. */
    private final By usernameField = By.id("username");

    /** The password input field — located by its stable HTML id attribute. */
    private final By passwordField = By.id("password");

    /**
     * The login submit button.
     *
     * <p>XPath {@code //button[@type='submit']} selects any button element
     * with a {@code type} attribute equal to "submit". This is more robust
     * than matching on button text, which can change with i18n or copy edits.</p>
     */
    private final By loginButton   = By.xpath("//button[@type='submit']");

    /** The flash message element that shows login success or failure feedback. */
    private final By flashMessage  = By.id("flash");

    /**
     * Constructs a LoginPage with the given WebDriver.
     *
     * @param driver The active WebDriver session.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Types the given username into the username field.
     *
     * <p>{@code clear()} is called first to remove any existing value, then
     * {@code sendKeys()} simulates keyboard input character by character.</p>
     *
     * @param username The username string to enter.
     * @return {@code this} — enables method chaining:
     *         {@code loginPage.setUsername("x").setPassword("y").clickLogin()}.
     */
    public LoginPage setUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    /**
     * Types the given password into the password field.
     *
     * @param password The password string to enter.
     * @return {@code this} for method chaining.
     */
    public LoginPage setPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    /**
     * Clicks the Login button and returns the resulting Secure Area page.
     *
     * <p>This method assumes the credentials entered are valid. If login fails,
     * the browser stays on the login page and the returned {@link SecureAreaPage}
     * will not find its expected elements — the test will fail with a clear error.</p>
     *
     * @return A new {@link SecureAreaPage}.
     */
    public SecureAreaPage clickLogin() {
        driver.findElement(loginButton).click();
        return new SecureAreaPage(driver);
    }

    /**
     * Returns the text of the flash notification message.
     *
     * <p>Used in tests to assert that the correct success or error message is
     * displayed after a login attempt.</p>
     *
     * @return The visible flash message text, e.g.
     *         {@code "You logged into a secure area!"}.
     */
    public String getFlashMessage() {
        return driver.findElement(flashMessage).getText();
    }
}
