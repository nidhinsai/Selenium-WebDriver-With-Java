package org.automation.pages;

import org.automation.enums.DropdownOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * <h2>DropdownPage — Training Notes: Selenium Select API</h2>
 *
 * <p>Represents the Dropdown page at
 * {@code https://the-internet.herokuapp.com/dropdown}.</p>
 *
 * <h3>The {@code Select} Class</h3>
 * <p>Standard HTML {@code <select>} dropdown menus cannot be interacted with
 * using a plain {@code click()}. Selenium provides the {@link Select} wrapper
 * class that understands the {@code <select>} / {@code <option>} structure.</p>
 *
 * <p>Three ways to choose an option:</p>
 * <ol>
 *   <li>{@code selectByVisibleText("Option 1")} — matches by the text the user sees.
 *       Most readable; use when text is stable.</li>
 *   <li>{@code selectByValue("1")} — matches the {@code value} HTML attribute.
 *       More stable than text if the UI copy changes.</li>
 *   <li>{@code selectByIndex(1)} — selects by zero-based position.
 *       Fragile if options are reordered; use only as a last resort.</li>
 * </ol>
 *
 * <h3>Caching the Select Object</h3>
 * <p>The original code created a new {@code Select} object in every method. This
 * is inefficient: each call to {@code new Select(driver.findElement(...))} re-queries
 * the DOM. This version uses a lazy-initialised {@code getDropdown()} helper to
 * create the {@code Select} once per page object lifetime.</p>
 *
 * <h3>Integration with DropdownOptions enum</h3>
 * <p>Methods accept {@link DropdownOptions} instead of raw {@code String}.
 * The enum's {@code getValue()} method returns the exact text Selenium needs.</p>
 */
public class DropdownPage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────────────────

    /**
     * The dropdown {@code <select>} element — located by its HTML id.
     * Passed into the {@code Select} constructor to enable the Select API.
     */
    private final By dropdownLocator = By.id("dropdown");

    /**
     * Cached {@link Select} wrapper. Created on first use via {@link #getDropdown()}.
     *
     * <p>Declared {@code null} initially — a pattern called <em>lazy
     * initialisation</em>: the object is created only when first needed.</p>
     */
    private Select dropdown;

    /**
     * Constructs a DropdownPage with the given WebDriver.
     *
     * @param driver The active WebDriver session.
     */
    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    // ── Private Helper ────────────────────────────────────────────────────────

    /**
     * Returns the cached {@link Select} wrapper, creating it on first call.
     *
     * <p>This ensures we only search the DOM for the element once per page object
     * lifetime, rather than on every method call.</p>
     *
     * @return The initialised {@link Select} for the dropdown element.
     */
    private Select getDropdown() {
        if (dropdown == null) {
            // new Select() wraps the raw WebElement and gives us the Select API
            dropdown = new Select(driver.findElement(dropdownLocator));
        }
        return dropdown;
    }

    // ── Public Actions ────────────────────────────────────────────────────────

    /**
     * Selects a dropdown option by its visible text label.
     *
     * <p>Calls {@code Select.selectByVisibleText()} which internally loops
     * through all {@code <option>} children and clicks the one whose
     * {@code .getText()} matches.</p>
     *
     * @param option One of the {@link DropdownOptions} enum constants.
     */
    public void selectOption(DropdownOptions option) {
        // option.getValue() returns e.g. "Option 1" — the exact visible text
        getDropdown().selectByVisibleText(option.getValue());
    }

    /**
     * Returns the visible text of the currently selected option.
     *
     * <p>{@code getFirstSelectedOption()} returns the first selected
     * {@code <option>} element (or the only one for single-select dropdowns).
     * {@code getText()} then strips the surrounding tags.</p>
     *
     * @return The visible text of the selected option, e.g. {@code "Option 1"}.
     */
    public String getSelectedOption() {
        return getDropdown().getFirstSelectedOption().getText();
    }
}
