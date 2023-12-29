package org.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page class for handling JavaScript alerts.
 */
public class JavaScriptAlertsPage {

    private final WebDriver driver;

    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    private WebElement jsAlertButton;

    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    private WebElement jsConfirmButton;

    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    private WebElement jsPromptButton;

    @FindBy(id = "result")
    private WebElement resultText;

    /**
     * Constructor for initializing the JavaScriptAlertsPage.
     *
     * @param driver WebDriver instance.
     */
    public JavaScriptAlertsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the JS Alert button.
     */
    public void clickJSAlert() {
        jsAlertButton.click();
    }

    /**
     * Clicks the JS Confirm button.
     */
    public void clickJSConfirm() {
        jsConfirmButton.click();
    }

    /**
     * Clicks the JS Prompt button.
     */
    public void clickJSPrompt() {
        jsPromptButton.click();
    }

    /**
     * Accepts the currently displayed alert.
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    /**
     * Dismisses the currently displayed alert.
     */
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * Enters text into the currently displayed alert.
     *
     * @param text Text to be entered into the alert.
     */
    public void sendKeysToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    /**
     * Gets the text of the currently displayed alert.
     *
     * @return Text of the alert.
     */
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    /**
     * Gets the result text displayed on the page.
     *
     * @return Result text.
     */
    public String getResultText() {
        return resultText.getText();
    }
}
