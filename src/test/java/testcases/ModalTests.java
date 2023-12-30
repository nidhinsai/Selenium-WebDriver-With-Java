package testcases;

import org.automation.pages.EntryAdPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Test class for Entry Ad page on the-internet.herokuapp.com to demonstrate interactions with modals
 */
public class ModalTests extends BaseTest {

    private EntryAdPage entryAdPage;

    /**
     * Method to set up the Entry Ad page before each test method.
     */
    @BeforeMethod
    public void setUp() {
        entryAdPage = homePage.clickEntryAd();
        entryAdPage.waitForModal();
    }

    /**
     * Test method to test modal from Entry Ad page.
     */
    @Test
    public void testModalWindow() {
        assertTrue(entryAdPage.isModalDisplayed(), "Modal window is not displayed");
        assertEquals(entryAdPage.getModalTitle(), "THIS IS A MODAL WINDOW", "Incorrect modal title");
        entryAdPage.closeModal();
        assertFalse(entryAdPage.isModalDisplayed(), "Modal window is not closed");
    }

}
