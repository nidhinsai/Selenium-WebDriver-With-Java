### Selenium WebDriver With Java Training

**Base Test Class**
* WebDriver Initialization: In the classSetup() method annotated with @BeforeClass, it initializes a WebDriver instance using the WebDriverProvider. The WebDriverProvider allows you to set up a WebDriver based on the specified browser type (in this case, "safari"). This WebDriver instance is made available to all the test classes that extend the BaseTest.
* TearDown: The tearDown() method annotated with @AfterClass is responsible for quitting the WebDriver after all the tests in the class have executed. It ensures that the WebDriver resources are released and avoids leaving browsers running unnecessarily.
* Helper Methods:
   * openUrl(String url): A helper method to navigate the WebDriver to a specified URL. This method encapsulates the logic to navigate the browser to a given URL and can be reused across different test cases.
* BaseTest encapsulates common setup and teardown operations related to WebDriver initialization and cleanup. Extending this class in test cases avoids redundant code in each test class and promotes code reusability.
* Refer the below commit for the implementation of WebDriverProvider class: https://github.com/nidhinsai/Selenium-WebDriver-With-Java/commit/5e362011da6f7a05ac03c0c5e4b6733a42703d2e