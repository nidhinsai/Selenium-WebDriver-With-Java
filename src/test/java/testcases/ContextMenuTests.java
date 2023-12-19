package testcases;

import org.automation.pages.ContextMenuPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Test class for Context Menu functionality on the-internet.herokuapp.com
 */
public class ContextMenuTests extends BaseTest {

    /**
     * Tests the right-click functionality on the context menu
     */
    @Test
    public void testRightClick() {
        ContextMenuPage contextMenuPage = homePage.clickContextMenu();
        contextMenuPage.contextClickInTheBox();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(contextMenuPage.getAlertText(), "You selected a context menu", "Popup message is not matching");
        contextMenuPage.acceptAlert();

        softAssert.assertAll();
    }
}
