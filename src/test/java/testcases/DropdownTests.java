package testcases;

import org.automation.pages.DropdownPage;
import org.automation.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test class for Dropdown functionality on the-internet.herokuapp.com
 */
public class DropdownTests extends BaseTest {

    /**
     * Opens the test URL before each test method
     */
    @BeforeMethod()
    public void openTestUrl() {
        openUrl("https://the-internet.herokuapp.com");
    }

    /**
     * Tests the selection of an option from the dropdown
     */
    @Test
    public void testDropdownSelection() {
        HomePage homePage = new HomePage(driver);
        DropdownPage dropDownPage = homePage.clickDropDown();
        String option = "Option 1";
        dropDownPage.selectFromDropDown(option);
        assertEquals(dropDownPage.getSelectedOption(), option, "Incorrect option selected");
    }
}
