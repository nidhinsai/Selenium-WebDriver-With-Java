package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page class representing the Home Page of the application
 */
public class HomePage {

    private final WebDriver driver;
    private final By formAuthenticationLink = By.linkText("Form Authentication");
    private final By dropdownLink = By.linkText("Dropdown");
    private final By contextMenuLink = By.linkText("Context Menu");
    private final By javaScriptAlertsLink = By.linkText("JavaScript Alerts");
    private final By fileUploadLink = By.linkText("File Upload");
    private final By entryAdLink = By.linkText("Entry Ad");

    /**
     * Constructor to initialize HomePage with WebDriver
     *
     * @param driver The WebDriver instance
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks the 'Form Authentication' link on the Home Page
     *
     * @return LoginPage object representing the Form Authentication page
     */
    public LoginPage clickFormAuthentication() {
        driver.findElement(formAuthenticationLink).click();
        return new LoginPage(driver);
    }

    /**
     * Clicks the 'Dropdown' link on the Home Page
     *
     * @return DropdownPage object representing the Dropdown page
     */
    public DropdownPage clickDropDown() {
        driver.findElement(dropdownLink).click();
        return new DropdownPage(driver);
    }

    /**
     * Clicks the 'Context Menu' link on the Home Page
     *
     * @return ContextMenuPage object representing the Context Menu page
     */
    public ContextMenuPage clickContextMenu() {
        driver.findElement(contextMenuLink).click();
        return new ContextMenuPage(driver);
    }

    /**
     * Clicks the 'JavaScript Alerts' link on the Home Page
     *
     * @return JavaScriptAlertsPage object representing the JavaScript Alerts page
     */
    public JavaScriptAlertsPage clickJavaScriptAlerts() {
        driver.findElement(javaScriptAlertsLink).click();
        return new JavaScriptAlertsPage(driver);
    }

    /**
     * Clicks the 'File Upload' link on the Home Page
     *
     * @return FileUploadPage object representing the File Upload page
     */
    public FileUploadPage clickFileUpload() {
        driver.findElement(fileUploadLink).click();
        return new FileUploadPage(driver);
    }

    /**
     * Clicks the 'Entry Ad' link on the Home Page
     *
     * @return EntryAdPage object representing the Entry Ad page
     */
    public EntryAdPage clickEntryAd() {
        driver.findElement(entryAdLink).click();
        return new EntryAdPage(driver);
    }
}
