### Selenium WebDriver With Java Training

**Modals:**

* Modals are UI elements that overlay the main content to display information, require user input, or confirm actions. They differ from alerts in that they can be more complex, offering various options and functionalities, and often require user interaction before allowing the user to proceed.
* There are mainly 3 types of Modals
  * Alert Modals: These are simple pop-ups that usually display a message and require the user to either accept or dismiss.
  * Confirmation Modals: These prompt the user to confirm or cancel an action, often presenting buttons like "OK" and "Cancel."
  * Modal Forms: These are more complex, containing input fields or options that need to be filled out before continuing.
* Locate the Modal Element: Use Selenium locators to find the modal element on the webpage.
* Interact with the Modal: Depending on the type of modal, interact with its elements using Selenium methods. For example, to click on buttons within the modal:
```java 
WebElement modal = driver.findElement(By.id("modalId"));
WebElement modalButton = modal.findElement(By.id("buttonId"));
modalButton.click();
```
* Handle Wait Conditions: Modals might not immediately appear or disappear, so using explicit waits with Selenium (e.g. WebDriverWait) to ensure the modal is present or disappears before interacting further can be crucial.
* In most cases, when a modal is open, the elements in the parent window (the main browser window) become inaccessible or are obscured by the modal. This is due to the modal overlaying the content of the parent window and capturing user focus.
**Refer this commit for more:** https://github.com/nidhinsai/Selenium-WebDriver-With-Java/commit/df63fa4f1bbd8afd3e8b0afdf40442bed30658e2