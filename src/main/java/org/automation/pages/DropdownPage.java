package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Page class for interacting with dropdown elements
 */
public class DropdownPage {

    private final WebDriver driver;
    private final By dropdown = By.id("dropdown");

    /**
     * Constructor to initialize DropdownPage with WebDriver
     *
     * @param driver The WebDriver instance
     */
    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Selects an option from the dropdown by visible text
     *
     * @param option The text of the option to be selected
     */
    public void selectFromDropDown(String option) {
        WebElement dropdownElement = driver.findElement(dropdown);
        Select dropdownSelect = new Select(dropdownElement);
        dropdownSelect.selectByVisibleText(option);
    }

    /**
     * Gets the text of the currently selected option in the dropdown
     *
     * @return The text of the selected option
     */
    public String getSelectedOption() {
        WebElement dropdownElement = driver.findElement(dropdown);
        Select dropdownSelect = new Select(dropdownElement);
        return dropdownSelect.getFirstSelectedOption().getText();
    }
}
