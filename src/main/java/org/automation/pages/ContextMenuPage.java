package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * <h2>ContextMenuPage — Training Notes: The Actions Class</h2>
 *
 * <p>Represents the Context Menu page at
 * {@code https://the-internet.herokuapp.com/context_menu}.
 * Right-clicking a hotspot element triggers a browser context menu.</p>
 *
 * <h3>Why the Actions Class?</h3>
 * <p>Some user interactions are more complex than a simple {@code click()} or
 * {@code sendKeys()}. The {@link Actions} class provides a <em>fluent API</em>
 * for composing complex gestures:</p>
 * <ul>
 *   <li>{@code contextClick(element)} — right-click</li>
 *   <li>{@code doubleClick(element)} — double-click</li>
 *   <li>{@code moveToElement(element)} — hover</li>
 *   <li>{@code dragAndDrop(source, target)} — drag and drop</li>
 *   <li>{@code keyDown(Keys.SHIFT)} / {@code keyUp(Keys.SHIFT)} — modifier keys</li>
 *   <li>{@code clickAndHold()} / {@code release()} — press and hold</li>
 * </ul>
 *
 * <h3>Method Chaining and .perform()</h3>
 * <p>Actions methods return the same {@code Actions} object, enabling chaining:</p>
 * <pre>
 *   new Actions(driver)
 *       .moveToElement(element)   // hover first
 *       .contextClick(element)    // then right-click
 *       .perform();               // MUST call perform() to execute!
 * </pre>
 * <p>Nothing happens until {@code perform()} is called — it dispatches all
 * queued actions to the browser in a single low-level sequence.</p>
 *
 * <h3>Context Menu and Alerts</h3>
 * <p>After right-clicking the hotspot on this page, the browser fires a
 * JavaScript {@code alert()} with the text "You selected a context menu".
 * The test then switches to the alert to read and dismiss it.</p>
 */
public class ContextMenuPage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────────────────

    /**
     * The hot-spot div that triggers the context menu on right-click.
     * Located by its CSS id — a reliable, fast selector.
     */
    private final By hotspot = By.id("hot-spot");

    /**
     * Constructs a ContextMenuPage with the given WebDriver.
     *
     * @param driver The active WebDriver session.
     */
    public ContextMenuPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Right-clicks the hotspot element to trigger the context menu.
     *
     * <p>The sequence:
     * <ol>
     *   <li>{@code new Actions(driver)} — create an Actions builder bound to this driver.</li>
     *   <li>{@code .contextClick(element)} — queue a right-click action on the element.</li>
     *   <li>{@code .perform()} — dispatch the queued action to the browser.</li>
     * </ol>
     * After this call, the browser fires a JavaScript alert containing
     * the text "You selected a context menu".</p>
     */
    public void rightClickHotspot() {
        // driver.findElement finds the element in the current DOM
        var hotspotElement = driver.findElement(hotspot);
        // Actions: create → configure → perform
        new Actions(driver)
                .contextClick(hotspotElement)
                .perform();
    }

    /**
     * Retrieves the text from the JavaScript alert that appears after the
     * right-click, then dismisses (accepts) the alert.
     *
     * <p><strong>TRAINING NOTE — switchTo().alert():</strong><br>
     * When a JavaScript alert/confirm/prompt is open, the browser enters a
     * "modal lock" — no other elements can be interacted with. Selenium must
     * explicitly switch its focus to the alert context using
     * {@code driver.switchTo().alert()}, which returns an {@link org.openqa.selenium.Alert}
     * object. The key methods are:</p>
     * <ul>
     *   <li>{@code alert.getText()} — read the alert message</li>
     *   <li>{@code alert.accept()} — click OK / confirm</li>
     *   <li>{@code alert.dismiss()} — click Cancel</li>
     *   <li>{@code alert.sendKeys(text)} — type into a prompt()</li>
     * </ul>
     *
     * @return The text content of the alert, e.g.
     *         {@code "You selected a context menu"}.
     */
    public String getAlertTextAndAccept() {
        var alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept(); // dismiss the alert so subsequent actions can proceed
        return text;
    }
}
