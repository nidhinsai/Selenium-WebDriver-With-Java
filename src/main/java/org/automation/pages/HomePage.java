package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * <h2>HomePage — Training Notes: Locators and Page Chaining</h2>
 *
 * <p>Represents the home page at {@code https://the-internet.herokuapp.com}.
 * Its sole responsibility is navigating to sub-pages; each navigation method
 * returns a new page object representing the destination.</p>
 *
 * <h3>Page Chaining</h3>
 * <p>Methods return a new page object instead of {@code void}. This allows
 * test code to read naturally:
 * <pre>
 *   LoginPage login = homePage.clickFormAuthentication();
 *   login.setUsername("admin");
 * </pre>
 * The {@code HomePage} method creates the {@code LoginPage} — the test never
 * calls {@code new LoginPage(driver)} directly.</p>
 *
 * <h3>By.linkText() Locator</h3>
 * <p>{@code By.linkText("Form Authentication")} matches an {@code <a>} element
 * whose <em>exact</em>, <em>full</em> visible text is "Form Authentication".
 * Use {@code By.partialLinkText("Auth")} if only part of the text is known.</p>
 *
 * <h3>When to use which locator</h3>
 * <ul>
 *   <li>{@code By.id}          — always first choice; IDs must be unique per page</li>
 *   <li>{@code By.name}        — for form inputs that have a {@code name} attribute</li>
 *   <li>{@code By.cssSelector} — concise, fast, widely supported</li>
 *   <li>{@code By.linkText}    — only for {@code <a>} tags with stable text</li>
 *   <li>{@code By.xpath}       — most powerful but slowest; last resort</li>
 * </ul>
 */
public class HomePage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────────────────
    //
    // Using By.linkText because the home page links are simple stable anchor tags.
    // If the link text changes in a future release, only this constant needs updating.

    /** Link to the Form Authentication (Login) sub-page. */
    private final By formAuthLink     = By.linkText("Form Authentication");

    /** Link to the Dropdown sub-page. */
    private final By dropdownLink     = By.linkText("Dropdown");

    /** Link to the JavaScript Alerts sub-page. */
    private final By jsAlertsLink     = By.linkText("JavaScript Alerts");

    /** Link to the Context Menu sub-page. */
    private final By contextMenuLink  = By.linkText("Context Menu");

    /** Link to the Entry Ad (modal) sub-page. */
    private final By entryAdLink      = By.linkText("Entry Ad");

    /** Link to the File Upload sub-page. */
    private final By fileUploadLink   = By.linkText("File Upload");

    /**
     * Creates the HomePage with the WebDriver.
     *
     * <p>We pass {@code driver} up to {@link BasePage} via {@code super()}.
     * There are no {@code @FindBy} annotations here, so no
     * {@code PageFactory.initElements()} call is needed.</p>
     *
     * @param driver The active WebDriver session.
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the "Form Authentication" link and returns the resulting page.
     *
     * <p>{@code driver.findElement(By)} searches the current DOM for the first
     * element matching the locator. Throws {@code NoSuchElementException} if
     * nothing is found — the test then fails with a clear message.</p>
     *
     * @return A new {@link LoginPage} representing the login page.
     */
    public LoginPage clickFormAuthentication() {
        driver.findElement(formAuthLink).click();
        return new LoginPage(driver);
    }

    /**
     * Navigates to the Dropdown page.
     *
     * @return A new {@link DropdownPage}.
     */
    public DropdownPage clickDropdown() {
        driver.findElement(dropdownLink).click();
        return new DropdownPage(driver);
    }

    /**
     * Navigates to the JavaScript Alerts page.
     *
     * @return A new {@link JavaScriptAlertsPage}.
     */
    public JavaScriptAlertsPage clickJavaScriptAlerts() {
        driver.findElement(jsAlertsLink).click();
        return new JavaScriptAlertsPage(driver);
    }

    /**
     * Navigates to the Context Menu page.
     *
     * @return A new {@link ContextMenuPage}.
     */
    public ContextMenuPage clickContextMenu() {
        driver.findElement(contextMenuLink).click();
        return new ContextMenuPage(driver);
    }

    /**
     * Navigates to the Entry Ad (modal advertisement) page.
     *
     * @return A new {@link EntryAdPage}.
     */
    public EntryAdPage clickEntryAd() {
        driver.findElement(entryAdLink).click();
        return new EntryAdPage(driver);
    }

    /**
     * Navigates to the File Upload page.
     *
     * @return A new {@link FileUploadPage}.
     */
    public FileUploadPage clickFileUpload() {
        driver.findElement(fileUploadLink).click();
        return new FileUploadPage(driver);
    }
}
