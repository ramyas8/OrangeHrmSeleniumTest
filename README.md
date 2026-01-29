## OrangeHrmSeleniumTest

This repository is a collection of Selenium tests to run functional tests against publicly
available https://opensource-demo.orangehrmlive.com/.
It uses Selenium WebDriver for browser automation and is built with a data-driven approach, reading and writing test
data to/from Excel files. The project leverages the Page Object Model (POM) design pattern for test structure and
maintainability.

### Features

* **Automated Employee Addition**: Automates the process of adding new employees with first name, last name, and
  username fields.
* **Data-Driven Login Testing:** Executes login test and employee addition test with multiple data sets stored in an
  Excel file.
* **Auto-generated Employee ID Capture:** Captures the system-generated Employee ID and updates it in the Excel file.
* **Modular Design:** Follows the POM design pattern for reusability and readability.

### Key Engineering Highlights

* **Dynamic Dropdown Logic:** Implemented a robust helper using **List-based element discovery** and **JavaScript
  Executors** to bypass element interception issues common in React applications.
* **Bidirectional Data Flow:** The framework doesn't just read from Excel; it captures auto-generated Employee IDs from
  the UI and **writes them back to the Excel source** to maintain data integrity for subsequent tests.
* **Advanced Synchronization:** Optimized test stability using **Explicit and Fluent Waits**, specifically tailored to
  handle OrangeHRM's asynchronous loaders and sliding modal animations.
* **Mac-specific Optimization:** Configured for ARM64 architecture (Apple Silicon) with specialized keyboard chord
  handling (`Keys.COMMAND + "a"`) for reliable input field clearing.

### Project Structure

```text
src
├── main/java
│   ├── orangehrmpages              # Page Objects & Business Logic
│   └── orangehrmabstractcomponents # Reusable UI helpers
├── test/java
│   ├── orangehrmtests              # TestNG Suites
│   └── orangehrmtestcomponents     # BaseTest & Excel Data Utilities
└── OrangeHrmData.xlsx              # External Data Source
```

### Tech Stack

- **Language**: Java 20
- **Automation Tool**: Selenium WebDriver (v4.25.0)
- **Test Runner**: TestNG
- **Build Tool**: Maven
- **IDE**: IntelliJ IDEA

### How to Run

1. Clone the repository:
   `git clone https://github.com/ramyas8/OrangeHrmSeleniumTest.git`
2. Navigate to project folder and run tests:
   `mvn test -DsuiteXmlFile=testng.xml`

### Test Cases

Comprehensive manual test cases and scenarios for this project can be found
here:  [View Manual Test Cases on Google Drive](https://docs.google.com/spreadsheets/d/14Fsn-Ran3bPh2K4K4lMHySpi5M6BCJCTGLDUBVI-uIw/edit?gid=0#gid=0)

