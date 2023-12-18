### Selenium WebDriver With Java Training

**Data Providers:**

The @DataProvider annotation in TestNG is used to supply test data to a test method. By annotating a method with @DataProvider, it becomes a data source for a test method, providing multiple sets of data that the test method can iterate through. This annotation is particularly useful for parameterizing tests, allowing the same test method to execute with different inputs.

Key Points:

The annotated method must return a 2D array of objects (Object[][]) or an Iterator<Object[]>.
Each element of the array or iterator represents a set of parameters to be passed to the test method.
The test method annotated with @Test(dataProvider = "dataProviderMethodName") receives data from the specified data provider method.
Data providers enhance test flexibility by separating test logic from test data, making tests more maintainable and reusable.

**Reference Commit:** Refer this commit to see how DataProvider is used for DropdownTests - https://github.com/nidhinsai/Selenium-WebDriver-With-Java/commit/e5ff4d9e4e75e298309d3aef105a42fbbb22b61f