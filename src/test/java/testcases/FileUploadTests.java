package testcases;

import org.automation.pages.FileUploadPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * <h2>FileUploadTests — Training Notes: Portable File Paths in Tests</h2>
 *
 * <p>Tests the file upload feature on
 * {@code https://the-internet.herokuapp.com/upload}.</p>
 *
 * <h3>CRITICAL: Never Hardcode Absolute File Paths</h3>
 * <p>Using an absolute path like {@code /Users/john/Documents/file.txt} is a
 * common beginner mistake. The path is valid only on the author's machine.
 * The test fails immediately on any other machine, CI server, or Docker container.</p>
 *
 * <p><strong>Solution:</strong> store test resources in
 * {@code src/test/resources/} and resolve the path using
 * {@code System.getProperty("user.dir")}:
 * <pre>
 *   // Works on any machine — resolves to the Maven project root at runtime
 *   String filePath = System.getProperty("user.dir")
 *                   + "/src/test/resources/sample-upload.txt";
 * </pre>
 * {@code user.dir} is a JVM system property always set to the current working
 * directory, which Maven sets to the project root when running {@code mvn test}.</p>
 *
 * <h3>Test Resource Files</h3>
 * <p>Small files needed for tests (images, PDFs, CSVs) should live in
 * {@code src/test/resources/}. Maven includes this directory in the test
 * classpath automatically. Keep them minimal — just enough to exercise the
 * upload flow, not real production data.</p>
 */
public class FileUploadTests extends BaseTest {

    /**
     * Verifies that a file can be uploaded and the filename is confirmed on the result page.
     *
     * <p>The file {@code sample-upload.txt} lives in {@code src/test/resources/}
     * and is included in this repository. It is a tiny text file used purely to
     * exercise the upload mechanism — its content does not matter.</p>
     */
    @Test
    public void uploadFileSuccessfully() {
        // ARRANGE — build a portable, machine-independent path
        // System.getProperty("user.dir") returns the Maven project root
        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/src/test/resources/sample-upload.txt";
        String expectedFileName = "sample-upload.txt";

        // Navigate to the upload page via the home page
        FileUploadPage fileUploadPage = homePage.clickFileUpload();

        // ACT — upload the file using sendKeys on the file input element
        fileUploadPage.uploadFile(filePath);

        // ASSERT — verify the upload confirmation page shows the correct filename
        assertEquals(
                fileUploadPage.getUploadedFileName(),
                expectedFileName,
                "Uploaded filename should match the file we sent"
        );
    }
}
