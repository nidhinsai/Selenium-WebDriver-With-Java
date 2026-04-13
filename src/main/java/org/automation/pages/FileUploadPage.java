package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * <h2>FileUploadPage — Training Notes: File Uploads with Selenium</h2>
 *
 * <p>Represents the File Upload page at
 * {@code https://the-internet.herokuapp.com/upload}.</p>
 *
 * <h3>How File Upload Works in Selenium</h3>
 * <p>There are three common approaches:</p>
 *
 * <ol>
 *   <li><strong>sendKeys on the file input (used here)</strong><br>
 *       The cleanest approach for standard {@code <input type="file">} elements.
 *       Call {@code sendKeys("/absolute/path/to/file")} — Selenium sends the
 *       file path as keyboard input, which the browser's file-chooser dialog
 *       accepts without ever opening the native OS dialog.
 *       <pre>
 *   driver.findElement(By.id("file-upload")).sendKeys("/full/path/file.txt");
 *       </pre>
 *       <strong>IMPORTANT:</strong> the path must be absolute and the file must
 *       exist on the same machine running the browser (or the Grid node).</li>
 *
 *   <li><strong>Robot class</strong> — Java's {@code java.awt.Robot} can
 *       simulate keyboard input to an OS file dialog. More fragile because it
 *       depends on OS-level focus and window ordering. Avoid if possible.</li>
 *
 *   <li><strong>JavaScript injection</strong> — removes the {@code disabled}
 *       attribute and sets the value via JS. Useful when the input is hidden or
 *       styled, but bypasses real user interaction — not always reliable.</li>
 * </ol>
 *
 * <h3>Portable File Paths</h3>
 * <p>Never hardcode an absolute path like {@code /Users/john/Documents/file.txt}.
 * That path breaks on every other machine. Instead, store test files in
 * {@code src/test/resources/} and resolve the path at runtime:
 * <pre>
 *   String path = System.getProperty("user.dir") + "/src/test/resources/sample-upload.txt";
 * </pre>
 * {@code System.getProperty("user.dir")} returns the project root — the same
 * directory where you run {@code mvn test}. This works on any machine.</p>
 */
public class FileUploadPage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────────────────

    /**
     * The file input element ({@code <input type="file">}).
     * Using By.id — the most reliable locator.
     */
    private final By fileInput    = By.id("file-upload");

    /**
     * The upload submit button.
     * Using By.id for reliability.
     */
    private final By uploadButton = By.id("file-submit");

    /**
     * The filename text shown after a successful upload completion.
     * Confirms which file was uploaded.
     */
    private final By uploadedFile = By.id("uploaded-files");

    /**
     * Constructs a FileUploadPage with the given WebDriver.
     *
     * @param driver The active WebDriver session.
     */
    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Uploads a file by sending its absolute path to the file input element.
     *
     * <p>Selenium's {@code sendKeys()} on an {@code <input type="file">}
     * element behaves differently from typing: it sets the input's file
     * selection to the given path without opening the OS file picker dialog.
     * This is only possible because WebDriver has direct browser-level access.</p>
     *
     * @param filePath The absolute path to the file to upload.
     *                 Example: {@code System.getProperty("user.dir") + "/src/test/resources/sample-upload.txt"}
     */
    public void uploadFile(String filePath) {
        // sendKeys on a file input sets the selected file — no dialog opens
        driver.findElement(fileInput).sendKeys(filePath);
        driver.findElement(uploadButton).click();
    }

    /**
     * Returns the name of the file shown on the confirmation page.
     *
     * <p>After a successful upload, the page displays the filename under an
     * element with id {@code "uploaded-files"}. This text is asserted in tests
     * to confirm the correct file was uploaded.</p>
     *
     * @return The uploaded filename text, e.g. {@code "sample-upload.txt"}.
     */
    public String getUploadedFileName() {
        return driver.findElement(uploadedFile).getText();
    }
}
