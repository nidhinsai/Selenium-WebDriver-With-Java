### Selenium WebDriver With Java Training

**Alert Class:**

* In web applications, alerts are dialog boxes that pop up to convey important messages or to prompt the user for some action. They are commonly used to display warnings, confirmations, or information that requires immediate attention.

* In Selenium, the Alert class is used to handle these pop-up alerts. It provides methods to interact with JavaScript alert boxes, confirm boxes, and prompt boxes that appear in the web browser during automated testing. The Alert class enables you to perform actions like accepting, dismissing, or getting text from these pop-ups within your test scripts.

* Here's a basic rundown of methods provided by the Alert class in Selenium:

  * driver.switchTo().alert(): This method switches the driver's focus to the currently active alert.
  * alert.accept(): Accepts the alert by clicking the 'OK' button or equivalent action.
  * alert.dismiss(): Dismisses or cancels the alert by clicking the 'Cancel' button or equivalent action.
  * alert.getText(): Retrieves the text displayed on the alert box.
  * alert.sendKeys("text"): Sends specified text to a prompt alert (if it allows text input).


**Refer this commit for more:** https://github.com/nidhinsai/Selenium-WebDriver-With-Java/commit/b0d4174c2478afe29eab517e98073fb3464ab7d5