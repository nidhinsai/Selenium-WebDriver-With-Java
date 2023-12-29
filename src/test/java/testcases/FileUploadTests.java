package testcases;

import org.automation.pages.FileUploadPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test class for File Upload functionality on the-internet.herokuapp.com
 */
public class FileUploadTests extends BaseTest {

    private FileUploadPage fileUploadPage;

    /**
     * Method to set up the file upload page before each test method.
     */
    @BeforeMethod
    public void setUp() {
        fileUploadPage = homePage.clickFileUpload();
    }

    /**
     * Test method to validate file upload.
     */
    @Test
    public void testFileUpload() {
        String filePath = "/Users/nidhinsai/Documents/";
        String fileName = "testImage.JPG";
        fileUploadPage.uploadFile(filePath + fileName);
        assertEquals(fileUploadPage.getUploadedFileName(), fileName, "The uploaded file name doesn't match");
    }
}
