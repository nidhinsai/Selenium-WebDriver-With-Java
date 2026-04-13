package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * <h2>EntryAdPage — Training Notes: WebDriverWait and Modal Overlays</h2>
 *
 * <p>Represents the Entry Ad page at
 * {@code https://the-internet.herokuapp.com/entry_ad}.
 * An advertisement modal appears automatically when the page loads.</p>
 *
 * <h3>Why a Longer Wait Here?</h3>
 * <p>The modal on this page is loaded via an ad network simulation — it may
 * take several seconds longer than a typical page element. A standard 10-second
 * wait would be flaky; we use {@value #MODAL_WAIT_SECONDS} seconds specifically
 * to accommodate the ad network delay.
 * Always set wait timeouts based on observed behaviour, not arbitrary guesses.</p>
 *
 * <h3>WebDriverWait + ExpectedConditions</h3>
 * <p>{@link WebDriverWait} polls the browser every 500ms (by default) until one
 * of two outcomes:
 * <ol>
 *   <li>The {@link org.openqa.selenium.support.ui.ExpectedCondition} returns a
 *       non-null / non-false value → wait completes successfully.</li>
 *   <li>The timeout expires → {@code TimeoutException} is thrown.</li>
 * </ol>
 * This is more efficient than {@code Thread.sleep()} because it stops polling
 * as soon as the condition is satisfied.</p>
 *
 * <h3>Named Constants vs Magic Numbers</h3>
 * <p>The original code used the literal {@code 40} as the timeout, with no
 * explanation of why. This has been replaced with a named constant
 * {@code MODAL_WAIT_SECONDS} with a Javadoc comment explaining the reason.
 * This is a key readability principle: numbers in code should always be named.</p>
 */
public class EntryAdPage extends BasePage {

    /**
     * The number of seconds to wait for the entry ad modal to appear.
     *
     * <p>Set higher than the default (10s) because the modal is loaded by an
     * ad-network simulation that can be slow to fire on the first page load.</p>
     */
    private static final int MODAL_WAIT_SECONDS = 40;

    // ── Locators ──────────────────────────────────────────────────────────────

    /** The modal dialog container that appears on page load. */
    private final By modal       = By.id("modal");

    /** The close button (×) inside the modal. */
    private final By closeButton = By.cssSelector("#modal .modal-footer p");

    /**
     * Constructs an EntryAdPage with the given WebDriver.
     *
     * @param driver The active WebDriver session.
     */
    public EntryAdPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Waits for the entry ad modal to appear, then closes it.
     *
     * <p>{@code ExpectedConditions.visibilityOfElementLocated(modal)} keeps
     * polling until the modal element is both present in the DOM
     * <em>and</em> visible (i.e., not hidden by CSS). This handles both the
     * case where the element doesn't exist yet and where it exists but is
     * {@code display: none}.</p>
     */
    public void closeModal() {
        // Wait up to MODAL_WAIT_SECONDS for the modal to become visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MODAL_WAIT_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(modal));

        // Modal is now visible — click the close button
        driver.findElement(closeButton).click();

        // Wait for the modal to disappear before returning control to the test
        // This prevents the next action from failing because the modal is still animating out
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modal));
    }

    /**
     * Returns whether the modal overlay is currently visible on the page.
     *
     * <p>{@code driver.findElement(modal).isDisplayed()} returns {@code true}
     * if the element exists in the DOM and has a non-zero size with visible
     * CSS. This is useful in assertions to verify the modal was actually closed.</p>
     *
     * @return {@code true} if the modal is visible, {@code false} otherwise.
     */
    public boolean isModalVisible() {
        try {
            return driver.findElement(modal).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Element not in DOM at all — definitely not visible
            return false;
        }
    }
}
