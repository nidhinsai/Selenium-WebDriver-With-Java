package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page class representing the Secure Area Page of the application
 */
public class SecureAreaPage {

    private final WebDriver driver;
    private final By statusAlert = By.id("flash");

    /**
     * Constructor to initialize SecureAreaPage with WebDriver
     *
     * @param driver The WebDriver instance
     */
    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the text from the status alert on the Secure Area Page
     *
     * @return The text of the status alert
     */
    public String getAlertText() {
        return driver.findElement(statusAlert).getText();
    }
}
