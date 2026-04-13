package testcases;

import org.automation.enums.DropdownOptions;
import org.automation.pages.DropdownPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * <h2>DropdownTests — Training Notes: @DataProvider (Parametrised Tests)</h2>
 *
 * <p>Tests the HTML {@code <select>} dropdown on
 * {@code https://the-internet.herokuapp.com/dropdown}.</p>
 *
 * <h3>What is @DataProvider?</h3>
 * <p>A {@code @DataProvider} method supplies multiple sets of input data to a
 * single {@code @Test} method. TestNG runs the test once per data row, making
 * it easy to verify many combinations without duplicating test code.</p>
 *
 * <h3>Return Type</h3>
 * <p>The method returns {@code Object[][]} — a 2D array where each inner array
 * is one test run's parameters:
 * <pre>
 *   return new Object[][] {
 *       { param1_run1, param2_run1 },   // test run 1
 *       { param1_run2, param2_run2 },   // test run 2
 *   };
 * </pre>
 * The test method must have matching parameters in the same order.</p>
 *
 * <h3>Linking @DataProvider to @Test</h3>
 * <p>Use {@code @Test(dataProvider = "providerName")} to link:
 * <pre>
 *   {@literal @DataProvider(name = "dropdownData")}
 *   public Object[][] dropdownData() { ... }
 *
 *   {@literal @Test(dataProvider = "dropdownData")}
 *   public void testDropdown(DropdownOptions option, String expected) { ... }
 * </pre>
 * The {@code name} attribute must match exactly (case-sensitive).</p>
 *
 * <h3>DataProvider vs @Parameters</h3>
 * <p>{@code @Parameters} reads values from the TestNG XML suite file at runtime.
 * {@code @DataProvider} supplies values from Java code — more flexible and
 * type-safe. Prefer {@code @DataProvider} for data-driven tests.</p>
 */
public class DropdownTests extends BaseTest {

    /**
     * Supplies test data: each row contains a {@link DropdownOptions} value
     * and the expected visible text that should be selected after clicking it.
     *
     * <p>Using the {@code DropdownOptions} enum ensures typos are caught at
     * compile time, not at runtime after a test run.</p>
     *
     * @return A 2D array of {option, expectedText} pairs.
     */
    @DataProvider(name = "dropdownOptions")
    public Object[][] dropdownOptions() {
        return new Object[][] {
                { DropdownOptions.OPTION_1, "Option 1" },
                { DropdownOptions.OPTION_2, "Option 2" },
        };
    }

    /**
     * Verifies that selecting each option updates the dropdown's displayed value.
     *
     * <p>This single test method runs twice — once per row from
     * {@code dropdownOptions}. TestNG labels each run with the data values
     * in the report:</p>
     * <pre>
     *   selectDropdownOption[0] (OPTION_1, Option 1) — PASSED
     *   selectDropdownOption[1] (OPTION_2, Option 2) — PASSED
     * </pre>
     *
     * @param option       The enum constant to pass to {@code selectOption()}.
     * @param expectedText The visible text that should be shown after selection.
     */
    @Test(dataProvider = "dropdownOptions")
    public void selectDropdownOption(DropdownOptions option, String expectedText) {
        // ARRANGE
        DropdownPage dropdownPage = homePage.clickDropdown();

        // ACT
        dropdownPage.selectOption(option);

        // ASSERT
        assertEquals(
                dropdownPage.getSelectedOption(),
                expectedText,
                "Selected option text should match: " + expectedText
        );
    }
}
