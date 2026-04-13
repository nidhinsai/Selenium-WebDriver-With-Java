package testcases;

import org.automation.pages.JavaScriptAlertsPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

/**
 * <h2>AlertsTests — Training Notes: SoftAssert vs Hard Assert</h2>
 *
 * <p>Tests JavaScript alert(), confirm(), and prompt() dialogs on
 * {@code https://the-internet.herokuapp.com/javascript_alerts}.</p>
 *
 * <h3>Hard Assert (org.testng.Assert)</h3>
 * <p>When a hard assertion fails, the test <strong>stops immediately</strong>.
 * Any subsequent assertions in the same test method are skipped.
 * Use when the failed assertion means there is no point continuing.</p>
 * <pre>
 *   Assert.assertEquals(actual, expected); // test stops here on failure
 *   Assert.assertTrue(condition);          // never reached if above failed
 * </pre>
 *
 * <h3>SoftAssert (org.testng.asserts.SoftAssert)</h3>
 * <p>A {@link SoftAssert} records failures but <strong>continues running</strong>.
 * At the end, call {@code softAssert.assertAll()} to throw a single combined
 * failure report listing all failures found.
 * Use when you want to check multiple independent conditions in one test pass:</p>
 * <pre>
 *   SoftAssert soft = new SoftAssert();
 *   soft.assertEquals(page.getTitle(), "Expected Title");
 *   soft.assertTrue(page.isButtonVisible());
 *   soft.assertAll(); // MUST call — otherwise failures are silently ignored!
 * </pre>
 * ⚠️ <strong>Never forget {@code assertAll()}</strong> — if omitted, a failing
 * soft assertion will not cause the test to fail.
 */
public class AlertsTests extends BaseTest {

    /**
     * Verifies that clicking the JS Alert button shows the expected message.
     *
     * <p>Hard assert is appropriate here — if the alert text is wrong, the page
     * is broken and there's nothing else meaningful to check.</p>
     */
    @Test
    public void jsAlertShowsCorrectMessage() {
        JavaScriptAlertsPage alertsPage = homePage.clickJavaScriptAlerts();
        alertsPage.clickAlertButton();
        String alertText = alertsPage.acceptAlert();
        assertEquals(alertText, "I am a JS Alert",
                "Alert dialog should display expected message");
    }

    /**
     * Verifies that accepting a JS Confirm box records the correct result.
     */
    @Test
    public void jsConfirmAcceptUpdatesResult() {
        JavaScriptAlertsPage alertsPage = homePage.clickJavaScriptAlerts();
        alertsPage.clickConfirmButton();
        alertsPage.acceptAlert();
        assertEquals(alertsPage.getResultText(), "You clicked: Ok",
                "Result should indicate the confirm was accepted");
    }

    /**
     * Verifies that dismissing a JS Confirm records the correct result.
     */
    @Test
    public void jsConfirmDismissUpdatesResult() {
        JavaScriptAlertsPage alertsPage = homePage.clickJavaScriptAlerts();
        alertsPage.clickConfirmButton();
        alertsPage.dismissAlert();
        assertEquals(alertsPage.getResultText(), "You clicked: Cancel",
                "Result should indicate the confirm was dismissed");
    }

    /**
     * Verifies the JS Prompt dialog using SoftAssert to check both the
     * result text and the entered value.
     *
     * <p>SoftAssert is used here because we want to verify two separate facts
     * in one test. If the first assertion fails, we still want to see whether
     * the second also fails — giving a complete picture of what's broken.</p>
     */
    @Test
    public void jsPromptAcceptsTypedText() {
        // ARRANGE
        JavaScriptAlertsPage alertsPage = homePage.clickJavaScriptAlerts();
        String inputText = "Hello Selenium";

        // ACT
        alertsPage.clickPromptButton();
        alertsPage.typeInPromptAndAccept(inputText);

        // ASSERT — SoftAssert collects all failures before reporting
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                alertsPage.getResultText().contains(inputText),
                "Result text should contain the typed input"
        );
        softAssert.assertAll(); // MANDATORY — triggers the combined failure report
    }
}
