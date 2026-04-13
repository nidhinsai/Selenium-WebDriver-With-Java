package testcases;

import org.automation.pages.LoginPage;
import org.automation.pages.SecureAreaPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * <h2>LoginTests — Training Notes: Basic Test Structure and Assertions</h2>
 *
 * <p>Verifies the login functionality of the Form Authentication page.</p>
 *
 * <h3>Test Naming Convention</h3>
 * <p>Use a descriptive name that reads like a sentence:</p>
 * <pre>
 *   successfulLoginWithValidCredentials
 *   loginFailsWithInvalidPassword
 *   loginFailsWithEmptyUsername
 * </pre>
 * Avoid names like {@code test1}, {@code loginTest} — they don't tell you
 * what is being tested or what the expected outcome is.
 *
 * <h3>assertTrue vs assertEquals</h3>
 * <ul>
 *   <li>{@code assertTrue(condition)} — passes if {@code condition} is {@code true}.
 *       Use for boolean checks: {@code assertTrue(isVisible)}.</li>
 *   <li>{@code assertEquals(actual, expected)} — passes if both values are equal.
 *       Shows both values in the failure message — easier to debug.</li>
 *   <li>{@code assertContains(actual, substring)} (TestNG) — use when you only
 *       care about part of a string (e.g. flash messages with trailing "×").</li>
 * </ul>
 *
 * <h3>Arrange / Act / Assert (AAA)</h3>
 * <p>Structure each test in three phases:
 * <pre>
 *   // ARRANGE — set up preconditions
 *   LoginPage loginPage = homePage.clickFormAuthentication();
 *
 *   // ACT — perform the action under test
 *   SecureAreaPage secureArea = loginPage.setUsername("admin")
 *                                        .setPassword("admin")
 *                                        .clickLogin();
 *
 *   // ASSERT — verify the outcome
 *   assertTrue(secureArea.getFlashMessage().contains("You logged into a secure area!"));
 * </pre>
 * </p>
 */
public class LoginTests extends BaseTest {

    /**
     * Verifies that a user can log in with valid credentials.
     *
     * <p>The test navigates to the login page, enters the correct username and
     * password, submits the form, and asserts that the resulting page shows a
     * success message containing the expected text.</p>
     *
     * <p>{@code contains()} is used instead of {@code equals()} because the
     * flash message includes a trailing "×" close-button character:
     * {@code "You logged into a secure area!
×"}.</p>
     */
    @Test
    public void successfulLoginWithValidCredentials() {
        // ARRANGE
        LoginPage loginPage = homePage.clickFormAuthentication();

        // ACT
        SecureAreaPage secureArea = loginPage
                .setUsername("admin")
                .setPassword("admin")
                .clickLogin();

        // ASSERT
        assertTrue(
                secureArea.getFlashMessage().contains("You logged into a secure area!"),
                "Flash message should confirm successful login"
        );
    }

    /**
     * Verifies that entering an invalid password shows an error message.
     *
     * <p>The third argument to {@code assertTrue()} is the failure message —
     * shown in the test report when the assertion fails. Always provide a
     * meaningful failure message to speed up debugging.</p>
     */
    @Test
    public void loginFailsWithInvalidPassword() {
        // ARRANGE
        LoginPage loginPage = homePage.clickFormAuthentication();

        // ACT — wrong password, clicking login stays on the login page
        String flashMessage = loginPage
                .setUsername("admin")
                .setPassword("wrongpassword")
                .clickLogin()  // returns SecureAreaPage but we land on login page
                .getFlashMessage(); // using SecureAreaPage's method — message element is on both pages

        // ASSERT
        assertTrue(
                flashMessage.contains("Your password is invalid!"),
                "Flash message should indicate invalid credentials"
        );
    }
}
