## OrangeHrmSeleniumTest
This repository is a collection of Selenium tests to run functional tests against publicly available https://opensource-demo.orangehrmlive.com/. 
 It uses Selenium WebDriver for browser automation and is built with a data-driven approach, reading and writing test data to/from Excel files. The project leverages the Page Object Model (POM) design pattern for test structure and maintainability.

### Features
* **Automated Employee Addition**: Automates the process of adding new employees with first name, last name, and username fields.
* **Data-Driven Login Testing:** Executes login test and employee addition test with multiple data sets stored in an Excel file.
* **Auto-generated Employee ID Capture:** Captures the system-generated Employee ID and updates it in the Excel file.
* **Modular Design:** Follows the POM design pattern for reusability and readability.


### Technologies used
#### Languages:
* Java 
#### Frameworks & Libraries: 
* Selenium WebDriver
* TestNG
* Apache POI (for Excel operations)
* Extent Reports (for test reporting)
#### Build Tool:
* Maven
#### Browser:
* Chrome
#### IDE:
* Intellij IDEA

### Test Cases 
