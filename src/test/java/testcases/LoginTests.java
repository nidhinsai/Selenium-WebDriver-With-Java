package testcases;

import org.automation.pages.LoginPage;
import org.automation.pages.SecureAreaPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Test class for login functionality on the-internet.herokuapp.com
 */
public class LoginTests extends BaseTest {

    /**
     * Tests successful login with valid credentials
     */
    @Test()
    public void testSuccessfulLogin() {
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        assertTrue(secureAreaPage.getAlertText().contains("You logged into a secure area!"), "Alert text is incorrect");
    }
}
