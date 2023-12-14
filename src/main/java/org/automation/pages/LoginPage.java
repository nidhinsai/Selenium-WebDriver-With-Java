package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page class representing the Login Page of the application
 */
public class LoginPage {

    private final WebDriver driver;
    private final By usernameField = By.id("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']/i[text()=' Login']");

    /**
     * Constructor to initialize LoginPage with WebDriver
     *
     * @param driver The WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Enters the username in the username field
     *
     * @param username The username to be entered
     */
    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    /**
     * Enters the password in the password field
     *
     * @param password The password to be entered
     */
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Clicks the login button and navigates to the Secure Area Page
     *
     * @return SecureAreaPage object representing the Secure Area Page
     */
    public SecureAreaPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new SecureAreaPage(driver);
    }
}
