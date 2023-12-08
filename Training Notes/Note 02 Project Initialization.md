
### Selenium WebDriver With Java Training

1. **Add Dependencies**
   * Add dependencies for Selenium WebDriver, TestNG, and any other necessary libraries in your pom.xml.
   * For finding various maven libraries and artifacts, you can visit https://mvnrepository.com
   * Search the desired dependency in https://mvnrepository.com.
   * From the search results, click on the desired result.
   * The list of versions can be found, from which you can select any stable version.
   * Select Maven and copy the dependency XML.
   * Open pom.xml file and add tags <dependencies> </dependencies> within the scope of project tag.
   * Add the copied dependency XML within the scope of dependencies tag
   * Some important libraries:
     * https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
     * https://mvnrepository.com/artifact/org.testng/testng

2. **Exclude Unnecessary Files**
   * Add a .gitignore file to specify files and directories that should be ignored and not tracked by the version control system. It allows us to exclude certain files from being committed to the repository. (You will find .gitignore file created by default under your project)
   * In the .gitignore file add the files and directories you want to be excluded.

3. **Commit and Push the changes**
   * Press CONTROL+K
   * Commit window will be opened.
   * Click on each file listed in the commit window and verify the changes.
   * Select files you want to commit by clicking on the checkboxes.
   * Add a commit message in the text box.
   * After selecting the files, click Commit and Push (Otherwise you can commit first then go to Git > Push or press CONTROL+SHIFT+K to push all the commits)

---

Please refer commit - Project Initialization : https://github.com/nidhinsai/Selenium-WebDriver-With-Java/commit/435f31d463273a9f9169c43e8928f98d69d77134

---
**Additional Notes**

1. **XML**

XML (Extensible Markup Language) is a markup language that is designed to store and transport data. It's similar to HTML but focuses on describing data rather than displaying it. 
Here are some key aspects to understand about XML:
* Tags: Similar to HTML, XML uses tags to define elements. Tags are enclosed in angle brackets \<tag>.
* Elements: Elements consist of an opening tag, content, and a closing tag \<tag>content\</tag>.
* Attributes: Elements can have attributes with values \<tag attribute="value">content\</tag>).
* Nesting: Elements can be nested within other elements, creating a hierarchical structure.
* Self-Closing Tags: Tags can be self-closed \<tag/> if they contain no content.

2. **POM Files**

In a Maven project, the POM (Project Object Model) file is a fundamental part of the project structure. It's an XML file named pom.xml that contains project configuration information and serves as the backbone of the project. Here are the key aspects of the POM file:

* Project Information:
Group ID, Artifact ID, Version: These elements uniquely identify the project. The combination of these three coordinates forms the project's GAV (Group, Artifact, Version) coordinates, which are used to identify and locate artifacts in repositories.
* Dependencies: Specifies the external libraries and dependencies required by the project. Maven handles the resolution and downloading of these dependencies from repositories.
* Build Configuration: Build Plugins specifies additional plugins or tools that modify the build process. Examples include compiling code, running tests, generating reports, etc.
* Project Lifecycle: Maven defines a series of build phases (clean, compile, test, package, etc.) that execute in a predefined sequence known as the project lifecycle.
* Repository Information: Defines the repositories where Maven looks for project dependencies and plugins. Maven Central is the default repository.
* Project Properties: Allows defining reusable values that can be referenced within the POM or build process. Useful for maintaining version numbers, paths, etc.