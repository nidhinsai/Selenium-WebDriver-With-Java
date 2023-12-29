package testcases;

import org.automation.pages.JavaScriptAlertsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Test class for handling various alerts using JavaScriptAlertsPage.
 */
public class AlertsTests extends BaseTest {

    private JavaScriptAlertsPage alertsPage;

    /**
     * Method to set up the alerts page before each test method.
     */
    @BeforeMethod
    public void setUp() {
        alertsPage = homePage.clickJavaScriptAlerts();
    }

    /**
     * Test method to validate closing a JavaScript Alert.
     */
    @Test
    public void testCloseJSAlert() {
        alertsPage.clickJSAlert();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertsPage.getAlertText(), "I am a JS Alert", "Incorrect alert text");
        alertsPage.acceptAlert();
        softAssert.assertEquals(alertsPage.getResultText(), "You successfully clicked an alert", "Incorrect result message");
        softAssert.assertAll();
    }

    /**
     * Test method to validate accepting a JavaScript Confirm.
     */
    @Test
    public void testAcceptJSConfirm() {
        alertsPage.clickJSConfirm();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertsPage.getAlertText(), "I am a JS Confirm", "Incorrect alert text");
        alertsPage.acceptAlert();
        softAssert.assertEquals(alertsPage.getResultText(), "You clicked: Ok", "Incorrect result message");
        softAssert.assertAll();
    }

    /**
     * Test method to validate dismissing a JavaScript Confirm.
     */
    @Test
    public void testDismissJSConfirm() {
        alertsPage.clickJSConfirm();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertsPage.getAlertText(), "I am a JS Confirm", "Incorrect alert text");
        alertsPage.dismissAlert();
        softAssert.assertEquals(alertsPage.getResultText(), "You clicked: Cancel", "Incorrect result message");
        softAssert.assertAll();
    }

    /**
     * Test method to validate entering text in a JavaScript Prompt.
     */
    @Test
    public void testEnterTextInJSPrompt() {
        alertsPage.clickJSPrompt();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertsPage.getAlertText(), "I am a JS prompt", "Incorrect alert text");
        String testMessage = "Testing alerts";
        alertsPage.sendKeysToAlert(testMessage);
        alertsPage.acceptAlert();
        softAssert.assertEquals(alertsPage.getResultText(), "You entered: " + testMessage, "Incorrect result message");
        softAssert.assertAll();
    }

    /**
     * Test method to validate canceling a JavaScript Prompt.
     */
    @Test
    public void testCancelJSPrompt() {
        alertsPage.clickJSPrompt();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertsPage.getAlertText(), "I am a JS prompt", "Incorrect alert text");
        alertsPage.dismissAlert();
        softAssert.assertEquals(alertsPage.getResultText(), "You entered: null", "Incorrect result message");
        softAssert.assertAll();
    }
}
