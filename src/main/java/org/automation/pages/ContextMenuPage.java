package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Page class representing the Context Menu Page of the application
 */
public class ContextMenuPage {

    private final WebDriver driver;
    private final By box = By.id("hot-spot");

    /**
     * Constructor to initialize ContextMenuPage with WebDriver
     *
     * @param driver The WebDriver instance
     */
    public ContextMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Performs a context click on the box element
     */
    public void contextClickInTheBox() {
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(box)).perform();
    }

    /**
     * Gets the text from the alert dialog
     *
     * @return The text present in the alert dialog
     */
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    /**
     * Accepts the alert dialog
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
