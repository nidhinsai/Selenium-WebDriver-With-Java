package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * <h2>SecureAreaPage — Training Notes: Post-Login Page Objects</h2>
 *
 * <p>Represents the Secure Area reached after a successful login at
 * {@code https://the-internet.herokuapp.com/secure}.</p>
 *
 * <h3>By.id — The Gold Standard Locator</h3>
 * <p>{@code By.id()} is the most reliable locator strategy because:</p>
 * <ul>
 *   <li>The HTML specification requires IDs to be unique within a page.</li>
 *   <li>Browsers optimise {@code getElementById()} — it is the fastest DOM lookup.</li>
 *   <li>IDs are independent of page structure, so moving an element doesn't break the locator.</li>
 * </ul>
 * <p>Always check the HTML source for an ID before reaching for CSS or XPath.</p>
 *
 * <h3>Logout Flow</h3>
 * <p>{@code clickLogout()} demonstrates that navigation can go <em>backward</em>:
 * clicking Logout returns a {@link LoginPage}. This mirrors the user journey
 * and keeps the test fluent:
 * <pre>
 *   LoginPage back = secureAreaPage.clickLogout();
 *   assertThat(back.getFlashMessage()).contains("You have been logged out");
 * </pre>
 * </p>
 */
public class SecureAreaPage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────────────────

    /** The main heading of the Secure Area — "Secure Area". */
    private final By header     = By.tagName("h2");

    /** The flash message shown upon successful login or logout. */
    private final By flashAlert = By.id("flash");

    /** The logout button in the top-right of the page. */
    private final By logoutBtn  = By.cssSelector(".button.secondary.radius");

    /**
     * Constructs a SecureAreaPage with the given WebDriver.
     *
     * @param driver The active WebDriver session.
     */
    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns the text of the login success flash message.
     *
     * <p>Example expected text: {@code "You logged into a secure area!\n×"}.
     * Note the "×" close-button character at the end — use
     * {@code contains()} rather than {@code equals()} in assertions.</p>
     *
     * @return The full text of the flash element.
     */
    public String getFlashMessage() {
        return driver.findElement(flashAlert).getText();
    }

    /**
     * Returns the heading text of the secure area.
     *
     * <p>{@code By.tagName("h2")} matches the first {@code <h2>} element.
     * Use with caution — a page with multiple {@code <h2>} elements would
     * return the first one, which may not be what you expect. Prefer ID or
     * CSS when available.</p>
     *
     * @return The heading text, e.g. {@code "Secure Area"}.
     */
    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    /**
     * Clicks the Logout button and returns to the Login page.
     *
     * @return A new {@link LoginPage}.
     */
    public LoginPage clickLogout() {
        driver.findElement(logoutBtn).click();
        return new LoginPage(driver);
    }
}
