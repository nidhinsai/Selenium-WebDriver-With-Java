### Selenium WebDriver With Java Training

**TestNG XML files:**

TestNG XML files serve as configuration files that outline the test suite's structure, specify test classes, set parameters, and control test execution. These files contain metadata defining how tests should be run. 

Some key elements within TestNG XML files include:

* Test Suite: Defines a collection of tests to be executed together. It's enclosed within the <suite> tag.
* Test: Represents a logical grouping of test cases, enclosed within the <test> tag. Each test can include one or more classes or methods.
* Classes and Methods: <class> tags specify the test classes to be executed. <methods> tags allow selection of specific methods within the classes for execution.
* Parameters: <parameter> tags can be used to pass parameters to tests, classes, or methods. These parameters can influence test behavior dynamically.
* Listeners: TestNG XML files support attaching listeners that execute certain actions during different phases of test execution. Listeners help in logging, reporting, or taking specific actions before or after test execution.
* Data Providers: Define <dataProvider> tags to specify methods that provide test data. These methods feed data into test methods using the @DataProvider annotation.
* Suite Configuration: TestNG XML files can include suite-level configurations, such as setting thread counts for parallel execution, defining groups, setting timeouts, or configuring suite-level listeners.

Here's a simplified example:

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Sanity Test Suite">
    <test name="Login Tests" thread-count="5">
        <classes>
            <class name="testcases.LoginTests"/>
        </classes>
    </test>
</suite>
```
This XML file outlines a test suite named "Sanity Test Suite" containing a single test called "Login Tests" that executes the test class "testcases.LoginTests". TestNG XML files offer a structured way to organize and execute tests, providing flexibility and configurability to test suites

**Reference Commit:** Refer this commit for more - https://github.com/nidhinsai/Selenium-WebDriver-With-Java/commit/1d9b16ac56d07bbca84d2e08c4fb2047fc52caa4