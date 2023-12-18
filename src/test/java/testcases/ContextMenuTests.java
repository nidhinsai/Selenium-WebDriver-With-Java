package testcases;

import org.automation.pages.ContextMenuPage;
import org.automation.pages.HomePage;
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
        HomePage homePage = new HomePage(driver);
        ContextMenuPage contextMenuPage = homePage.clickContextMenu();
        contextMenuPage.contextClickInTheBox();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(contextMenuPage.getAlertText(), "You selected a context menu", "Popup message is not matching");
        contextMenuPage.acceptAlert();

        softAssert.assertAll();
    }
}
