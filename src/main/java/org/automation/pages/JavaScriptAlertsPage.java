package org.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * <h2>JavaScriptAlertsPage — Training Notes: PageFactory and Alert Handling</h2>
 *
 * <p>Represents the JavaScript Alerts page at
 * {@code https://the-internet.herokuapp.com/javascript_alerts}.</p>
 *
 * <h3>PageFactory + @FindBy</h3>
 * <p>This class uses the {@code @FindBy} annotation style instead of explicit
 * {@code By} objects. Compare the two approaches:</p>
 *
 * <table border="1">
 *   <tr><th>By locator (LoginPage style)</th><th>@FindBy (this class)</th></tr>
 *   <tr>
 *     <td>{@code private final By btn = By.xpath("//button[1]");}<br>
 *         {@code driver.findElement(btn).click();}</td>
 *     <td>{@code @FindBy(xpath = "//button[1]")}<br>
 *         {@code private WebElement btn;}<br>
 *         {@code btn.click();}</td>
 *   </tr>
 *   <tr>
 *     <td>Element looked up fresh each call (safer for dynamic pages)</td>
 *     <td>Element proxy created once; re-looked up on first real access (lazy)</td>
 *   </tr>
 *   <tr>
 *     <td>No constructor ceremony needed</td>
 *     <td>MUST call {@code PageFactory.initElements(driver, this)} in constructor</td>
 *   </tr>
 * </table>
 *
 * <h3>JavaScript Alert vs Confirm vs Prompt</h3>
 * <ul>
 *   <li><strong>alert()</strong> — one button (OK). {@code accept()} to close.</li>
 *   <li><strong>confirm()</strong> — two buttons (OK / Cancel).
 *       {@code accept()} = OK, {@code dismiss()} = Cancel.</li>
 *   <li><strong>prompt()</strong> — text input + OK/Cancel.
 *       {@code sendKeys("text")} before {@code accept()} to type into it.</li>
 * </ul>
 *
 * <h3>switchTo() context switching</h3>
 * <p>{@code driver.switchTo()} is the gateway for switching Selenium's active
 * context. It can switch to:
 * <ul>
 *   <li>{@code .alert()} — a browser dialog</li>
 *   <li>{@code .frame(id)} — an iframe</li>
 *   <li>{@code .window(handle)} — a different browser tab/window</li>
 *   <li>{@code .defaultContent()} — back to the main document</li>
 * </ul>
 * Failing to switch context before interacting causes {@code NoAlertPresentException}
 * or {@code NoSuchFrameException}.</p>
 */
public class JavaScriptAlertsPage extends BasePage {

    // ── @FindBy Locators ──────────────────────────────────────────────────────
    //
    // @FindBy(xpath = "...") is equivalent to By.xpath("...") but uses
    // annotation syntax. PageFactory initialises these lazily via a proxy —
    // the actual findElement() call happens the first time you call a method
    // on btnAlert (like .click()).

    /** Button that triggers a simple JavaScript alert(). */
    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    private WebElement btnAlert;

    /** Button that triggers a JavaScript confirm() box. */
    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    private WebElement btnConfirm;

    /** Button that triggers a JavaScript prompt() dialog. */
    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    private WebElement btnPrompt;

    /** The result text element that shows what action was taken on the alert. */
    @FindBy(id = "result")
    private WebElement resultText;

    /**
     * Constructs a JavaScriptAlertsPage.
     *
     * <p>{@code PageFactory.initElements(driver, this)} scans this class for
     * {@code @FindBy} annotations and replaces each annotated field with a
     * Selenium <em>proxy</em>. The proxy defers the actual DOM search until the
     * field is first used — this is why the constructor doesn't need the page
     * to be fully loaded.</p>
     *
     * @param driver The active WebDriver session.
     */
    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
        // REQUIRED when using @FindBy — must be called after super()
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the "JS Alert" button, which triggers a browser alert().
     *
     * <p>After calling this method, the browser shows a modal dialog.
     * Call {@link #acceptAlert()} or read the text before dismissing.</p>
     */
    public void clickAlertButton() {
        btnAlert.click();
    }

    /**
     * Clicks the "JS Confirm" button, which triggers a browser confirm().
     */
    public void clickConfirmButton() {
        btnConfirm.click();
    }

    /**
     * Clicks the "JS Prompt" button, which triggers a browser prompt().
     */
    public void clickPromptButton() {
        btnPrompt.click();
    }

    /**
     * Reads the text from the currently open alert/confirm/prompt dialog,
     * then accepts (clicks OK on) it.
     *
     * @return The message text displayed in the dialog.
     */
    public String acceptAlert() {
        // switchTo().alert() shifts Selenium's focus to the dialog
        var alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept(); // clicks OK — for alert() and confirm()
        return text;
    }

    /**
     * Dismisses (clicks Cancel on) the currently open confirm dialog.
     *
     * <p>{@code dismiss()} is equivalent to pressing Escape or clicking Cancel
     * on confirm/prompt dialogs. On a simple alert() it behaves the same as
     * {@code accept()} (there is only one button).</p>
     */
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * Types the given text into a prompt() dialog, then accepts it.
     *
     * @param text The text to enter into the prompt input field.
     */
    public void typeInPromptAndAccept(String text) {
        var alert = driver.switchTo().alert();
        alert.sendKeys(text); // type into the prompt input
        alert.accept();
    }

    /**
     * Returns the result message displayed on the page after interacting
     * with an alert. This message reflects the last alert action taken.
     *
     * @return Text such as {@code "You successfully clicked an alert"}.
     */
    public String getResultText() {
        return resultText.getText();
    }
}
