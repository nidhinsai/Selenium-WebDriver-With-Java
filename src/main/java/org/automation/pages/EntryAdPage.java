package org.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page class for handling the Entry Ad modal.
 */
public class EntryAdPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "modal")
    private WebElement modalWindow;

    @FindBy(className = "modal-title")
    private WebElement modalTitle;

    @FindBy(className = "modal-footer")
    private WebElement modalFooter;

    /**
     * Constructor to initialize EntryAdPage with WebDriver.
     *
     * @param driver The WebDriver instance.
     */
    public EntryAdPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for the modal window to be visible.
     */
    public void waitForModal() {
        try {
            wait.until(ExpectedConditions.visibilityOf(modalWindow));
        } catch (NoSuchElementException | TimeoutException ignored) {
        }
    }

    /**
     * Checks if the modal window is displayed.
     *
     * @return True if the modal is displayed, false otherwise.
     */
    public boolean isModalDisplayed() {
        try {
            return modalWindow.isDisplayed();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Gets the title text of the modal.
     *
     * @return The text of the modal title.
     */
    public String getModalTitle() {
        return modalTitle.getText();
    }

    /**
     * Closes the modal window.
     */
    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(modalFooter)).click();
    }

}
