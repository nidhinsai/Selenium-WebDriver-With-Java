# Selenium WebDriver With Java — Training Project

A hands-on Selenium automation framework built for **learning by reading code**.
Every class, method, and locator is documented with *why*, not just *what*.

> **Test site:** [The Internet](https://the-internet.herokuapp.com) by Dave Haeffner —
> a purpose-built app with common UI patterns used to practice automation.

---

## Quick Start

```bash
# Clone
git clone https://github.com/nidhinsai/Selenium-WebDriver-With-Java.git
cd Selenium-WebDriver-With-Java

# Run all tests (Chrome, headless by default)
mvn test

# Run a specific suite
mvn test -Dsuite=LoginTests
mvn test -Dsuite=DropdownTests
mvn test -Dsuite=AlertsTests
mvn test -Dsuite=ContextMenuTests
mvn test -Dsuite=FileUploadTests
mvn test -Dsuite=ModalTests
mvn test -Dsuite=AllTests
```

---

## Project Structure

```
src/
├── main/java/org/automation/
│   ├── enums/
│   │   └── DropdownOptions.java      ← enum for type-safe test data
│   ├── helpers/
│   │   └── WebDriverProvider.java    ← browser factory (Chrome/Firefox/Safari)
│   └── pages/                        ← Page Object Model classes
│       ├── BasePage.java             ← abstract base: shared driver, wait helpers
│       ├── HomePage.java             ← navigation to sub-pages
│       ├── LoginPage.java            ← form authentication
│       ├── SecureAreaPage.java       ← post-login area
│       ├── DropdownPage.java         ← Select API
│       ├── JavaScriptAlertsPage.java ← alert/confirm/prompt (@FindBy + PageFactory)
│       ├── ContextMenuPage.java      ← Actions class (right-click)
│       ├── EntryAdPage.java          ← WebDriverWait + modal overlay
│       └── FileUploadPage.java       ← sendKeys file upload
└── test/
    ├── java/testcases/
    │   ├── BaseTest.java             ← TestNG lifecycle (@BeforeClass/@AfterClass)
    │   ├── LoginTests.java           ← assertTrue, AAA pattern
    │   ├── DropdownTests.java        ← @DataProvider (parametrised tests)
    │   ├── AlertsTests.java          ← SoftAssert vs hard Assert
    │   ├── ContextMenuTests.java     ← Actions interaction tests
    │   ├── FileUploadTests.java      ← portable file path, sendKeys upload
    │   └── ModalTests.java           ← HTML modal handling, assertFalse
    └── resources/
        ├── sample-upload.txt         ← test resource for file upload test
        └── test-xml/
            ├── LoginTests.xml
            ├── DropDownTests.xml
            ├── ContextMenuTests.xml
            ├── AlertsTests.xml       ← new
            ├── FileUploadTests.xml   ← new
            ├── ModalTests.xml        ← new
            └── AllTests.xml          ← new — runs entire suite
```

---

## Key Concepts Covered

### Page Object Model (POM)
Each web page has its own Java class. Locators and page actions live in the page
class; test classes contain only assertions and flow.
→ See `BasePage.java` for the full pattern explanation.

### Locator Strategies (Priority Order)

| Priority | Strategy | Example |
|----------|----------|---------|
| 1 ★★★★★ | `By.id` | `By.id("username")` |
| 2 ★★★★☆ | `By.name` | `By.name("email")` |
| 3 ★★★★☆ | `By.cssSelector` | `By.cssSelector("#btn.primary")` |
| 4 ★★★☆☆ | `By.linkText` | `By.linkText("Sign In")` |
| 5 ★★★☆☆ | `By.xpath` | `By.xpath("//button[@type='submit']")` |
| 6 ★★☆☆☆ | `By.className` | avoid — classes change with CSS refactors |
| 7 ★☆☆☆☆ | `By.tagName` | last resort only |

→ See `BasePage.java` and `LoginPage.java`.

### Wait Strategies

| Type | How | When to use |
|------|-----|-------------|
| **Implicit wait** | `driver.manage().timeouts().implicitlyWait(Duration)` | Set once; applies globally to all `findElement()` calls |
| **Explicit wait** | `new WebDriverWait(driver, Duration).until(ExpectedConditions.x)` | Wait for a specific condition (visibility, clickability, etc.) |
| **Fluent wait** | `new FluentWait(driver).withTimeout(...).pollingEvery(...).ignoring(...)` | Full custom polling control |
| **Thread.sleep** | `Thread.sleep(ms)` | Never — always use explicit wait instead |

→ See `WebDriverProvider.java` and `EntryAdPage.java`.

### Two Locator Declaration Styles

**By objects** (`LoginPage` style):
```java
private final By usernameField = By.id("username");
driver.findElement(usernameField).sendKeys("admin");
```

**@FindBy + PageFactory** (`JavaScriptAlertsPage` style):
```java
@FindBy(id = "username")
private WebElement usernameField;
// constructor must call:
PageFactory.initElements(driver, this);
// usage:
usernameField.sendKeys("admin");
```

### TestNG Lifecycle

```
@BeforeSuite → @BeforeClass → @BeforeMethod → @Test → @AfterMethod → @AfterClass → @AfterSuite
```

→ See `BaseTest.java` for the full explanation.

### SoftAssert vs Hard Assert

| Hard Assert | SoftAssert |
|-------------|------------|
| `Assert.assertEquals(a, b)` | `softAssert.assertEquals(a, b)` |
| Test stops on first failure | All assertions run; failures collected |
| Use when failure = no point continuing | Use to check multiple independent conditions |
| No special teardown needed | **Must call `softAssert.assertAll()`** |

→ See `AlertsTests.java`.

### @DataProvider (Parametrised Tests)

```java
@DataProvider(name = "myData")
public Object[][] myData() {
    return new Object[][] {
        { "input1", "expected1" },
        { "input2", "expected2" },
    };
}

@Test(dataProvider = "myData")
public void myTest(String input, String expected) { ... }
```

→ See `DropdownTests.java`.

---

## Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| Selenium Java | 4.20.0 | Browser automation |
| TestNG | 7.10.1 | Test runner, assertions, lifecycle |
| WebDriverManager | 5.8.0 | Auto-downloads ChromeDriver/GeckoDriver |

---

## Bug Fixes Applied

| File | Issue | Fix |
|------|-------|-----|
| `FileUploadTests.java` | Hardcoded `/Users/nidhinsai/Documents/` path | Replaced with `System.getProperty("user.dir") + "/src/test/resources/"` |
| `LoginPage.java` | XPath `/i[text()=' Login']` with leading space | Simplified to `//button[@type='submit']` |
| `BaseTest.java` | Javadoc said "Safari browser" | Corrected to Chrome |
| `EntryAdPage.java` | Magic number `40` for timeout | Named constant `MODAL_WAIT_SECONDS = 40` with explanation |
| `DropdownPage.java` | New `Select` object created on every call | Lazy-initialised once per page object |

---

## Missing Test Suites Added

`AlertsTests.xml`, `FileUploadTests.xml`, `ModalTests.xml`, `AllTests.xml`
were absent from the original. All four are now in `src/test/resources/test-xml/`.
