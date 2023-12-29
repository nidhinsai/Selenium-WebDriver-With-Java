package org.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page class for handling file upload functionality.
 */
public class FileUploadPage {

    private final WebDriver driver;

    @FindBy(id = "file-upload")
    private WebElement fileUploadInput;

    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    @FindBy(id = "uploaded-files")
    private WebElement uploadedFiles;

    /**
     * Constructor for FileUploadPage class.
     *
     * @param driver WebDriver instance.
     */
    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Uploads a file by providing the absolute file path.
     *
     * @param absoluteFilePath Absolute path of the file to be uploaded.
     */
    public void uploadFile(String absoluteFilePath) {
        fileUploadInput.sendKeys(absoluteFilePath);
        uploadButton.click();
    }

    /**
     * Gets the name of the uploaded file.
     *
     * @return Name of the uploaded file.
     */
    public String getUploadedFileName() {
        return uploadedFiles.getText();
    }
}
