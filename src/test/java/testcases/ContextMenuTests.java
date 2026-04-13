package testcases;

import org.automation.pages.ContextMenuPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * <h2>ContextMenuTests — Training Notes: Testing Complex User Interactions</h2>
 *
 * <p>Tests the right-click context menu on
 * {@code https://the-internet.herokuapp.com/context_menu}.</p>
 *
 * <h3>When to Test Complex Interactions</h3>
 * <p>Actions like right-click, drag-and-drop, hover, and double-click are
 * frequently broken by framework upgrades or CSS changes. Automating them
 * catches regressions that manual testing might miss — especially because
 * developers rarely test these flows manually after every change.</p>
 *
 * <h3>Test Isolation</h3>
 * <p>Each {@code @Test} method should be independent. The {@code @BeforeMethod}
 * in {@code BaseTest} navigates to the home page before each test. This means
 * even if one test leaves the browser in an unexpected state (e.g., an open
 * alert), the next test starts clean.</p>
 *
 * <h3>Actions Class Recap</h3>
 * <p>See {@code ContextMenuPage} for the full training explanation. The key
 * pattern: {@code new Actions(driver).contextClick(element).perform()}.</p>
 */
public class ContextMenuTests extends BaseTest {

    /**
     * Verifies that right-clicking the hotspot produces the expected alert text.
     *
     * <p>The test navigates to the Context Menu page, right-clicks the hotspot,
     * reads the resulting alert, and verifies its content.</p>
     */
    @Test
    public void rightClickShowsContextMenuAlert() {
        // ARRANGE
        ContextMenuPage contextMenuPage = homePage.clickContextMenu();

        // ACT — right-click and immediately read the alert (it appears synchronously)
        contextMenuPage.rightClickHotspot();
        String alertText = contextMenuPage.getAlertTextAndAccept();

        // ASSERT
        assertEquals(alertText, "You selected a context menu",
                "Alert text should confirm context menu was triggered");
    }
}
