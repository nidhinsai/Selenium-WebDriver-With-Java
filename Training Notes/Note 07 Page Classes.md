### Selenium WebDriver With Java Training

1. **Page Object Model**
   * The Page Object Model (POM) is a design pattern used in test automation to create an abstraction layer between test code and the UI of an application. Each web page or a section of a web application has its own corresponding class that represents the elements and functionality of that page called a page class.Here are the key points:
     * Modular Design: POM divides each web page into its own class. Each class represents a single page of the application and contains the related elements and methods to interact with those elements.
     * Re-usability: POM promotes code re-usability by encapsulating page-specific actions and elements into separate classes. These classes can be reused across multiple tests.
     * Separation of Concerns: It separates the test logic (test methods) from the implementation details (locators and interactions with elements), enhancing maintainability and readability.
     * Easier Maintenance: Changes in the UI can be localized within the affected page class, reducing the impact on other parts of the code.
     * Improved Collaboration: POM enables better collaboration among team members, as the code becomes more organized and easier to understand.
2. **Locators**
   * Locators in Selenium WebDriver are used to identify and interact with elements on a web page. Here are various locators supported by Selenium:
     * ID: Locates elements by their unique ID attribute. Example: By.id("elementId")
     * Name: Finds elements by their name attribute. Example: By.name("elementName")
     * Class Name: Locates elements by their class attribute. Example: By.className("className")
     * Tag Name: Selects elements by their HTML tag name. Example: By.tagName("tagName")
     * Link Text: Specifically used for locating anchor elements (\<a> tags) by the visible text within the link. Example: By.linkText("linkText")
     * Partial Link Text: Locates elements by a portion of their visible text within an anchor element. Example: By.partialLinkText("partialLinkText")
     * XPath: Uses XPath expressions to navigate through elements in an XML/HTML document. Example: By.xpath("//div\[@id='example']")
     * CSS Selector: Employs CSS selectors to locate elements. Example: By.cssSelector("input\[type='text']")
3. **Page Class**
     * A page class contains:
       * WebDriver Instance: It initializes with a WebDriver instance to interact with the browser.
       * Element Locators: Locators that represent various web elements in a webpage.
       * Methods: Different Methods for interacting with the page elements.
4. Refer this commit for more: https://github.com/nidhinsai/Selenium-WebDriver-With-Java/commit/523a113ff228b9237bedfc9feb6964be7ddbe691
 
