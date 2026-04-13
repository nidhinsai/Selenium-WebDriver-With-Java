package testcases;

import org.automation.pages.EntryAdPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

/**
 * <h2>ModalTests — Training Notes: Verifying Modal Overlay Behaviour</h2>
 *
 * <p>Tests the entry ad modal on
 * {@code https://the-internet.herokuapp.com/entry_ad}.</p>
 *
 * <h3>Modals (Overlays) in Selenium</h3>
 * <p>HTML modals (also called overlays or dialogs) are different from
 * JavaScript alert() dialogs:</p>
 * <ul>
 *   <li><strong>JS Alerts</strong> — browser-native dialogs. Need
 *       {@code driver.switchTo().alert()} to interact with them.</li>
 *   <li><strong>HTML Modals</strong> — regular DOM elements styled to appear
 *       as overlays. Interact with them using normal {@code findElement()}
 *       and {@code click()} calls.</li>
 * </ul>
 * <p>The entry ad on this page is an HTML modal, not a JS alert. No context
 * switching is needed — just find the close button and click it.</p>
 *
 * <h3>Waiting for Disappearance</h3>
 * <p>After clicking "Close", the modal may animate out over hundreds of
 * milliseconds. Always wait for it to disappear before interacting with
 * page elements underneath. Use:
 * <pre>
 *   wait.until(ExpectedConditions.invisibilityOfElementLocated(modal));
 * </pre>
 * See {@code EntryAdPage.closeModal()} for the full implementation.</p>
 *
 * <h3>assertFalse</h3>
 * <p>{@code assertFalse(condition, message)} passes when {@code condition}
 * is {@code false}. Use it to verify that something is <em>not</em> present,
 * not visible, or not selected:</p>
 * <pre>
 *   assertFalse(page.isModalVisible(), "Modal should be hidden after closing");
 * </pre>
 */
public class ModalTests extends BaseTest {

    /**
     * Verifies that the entry ad modal can be closed and is no longer visible.
     *
     * <p>The modal appears automatically on page load. The test:
     * <ol>
     *   <li>Navigates to the Entry Ad page</li>
     *   <li>Waits for the modal to appear (handled inside {@code closeModal()})</li>
     *   <li>Clicks the close button</li>
     *   <li>Asserts the modal is no longer visible</li>
     * </ol>
     * </p>
     */
    @Test
    public void entryAdModalCanBeClosed() {
        // ARRANGE
        EntryAdPage entryAdPage = homePage.clickEntryAd();

        // ACT — waits for modal, clicks close, waits for disappearance
        entryAdPage.closeModal();

        // ASSERT — modal should no longer be in the DOM or should be hidden
        assertFalse(
                entryAdPage.isModalVisible(),
                "Entry ad modal should not be visible after clicking close"
        );
    }
}
