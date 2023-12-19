package testcases;

import org.automation.enums.DropdownOptions;
import org.automation.pages.DropdownPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test class for Dropdown functionality on the-internet.herokuapp.com
 */
public class DropdownTests extends BaseTest {

    /**
     * Provides test data for the dropdown options
     *
     * @return A 2D array containing DropdownOptions.OPTION_1 and DropdownOptions.OPTION_2
     */
    @DataProvider(name = "dropdownOptionsTestData")
    public Object[][] dropdownOptions() {
        return new Object[][]{{DropdownOptions.OPTION_1}, {DropdownOptions.OPTION_2}};
    }

    /**
     * Tests the selection of an option from the dropdown
     *
     * @param option The DropdownOptions enum value representing the option to select
     */
    @Test(dataProvider = "dropdownOptionsTestData")
    public void testDropdownSelection(DropdownOptions option) {
        DropdownPage dropDownPage = homePage.clickDropDown();
        dropDownPage.selectFromDropDown(option);
        assertEquals(dropDownPage.getSelectedOption(), option.getOption(), "Incorrect option selected");
    }
}
