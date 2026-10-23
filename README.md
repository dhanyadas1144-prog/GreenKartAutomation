# GreenKart Selenium TestNG Automation Project

## 📌 Project Overview

This project automates web application testing using Selenium WebDriver, Java, TestNG, and Maven. It follows a structured approach to test web application functionality and validate user workflows.

## 🎯 Objectives

- Automate web application test cases using Selenium WebDriver.
- Implement test execution and validation using TestNG.
- Organize automation code using the Page Object Model (POM).
- Manage dependencies using Maven.
- Generate test execution reports through TestNG.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Automation Tool:** Selenium WebDriver
- **Testing Framework:** TestNG
- **Build Tool:** Maven
- **IDE:** Eclipse
- **Version Control:** Git and GitHub


*Note: Update the structure above if your actual project contains additional classes or different package names.*

## ✅ Key Features

- Browser initialization and test setup.
- Web element identification and interaction.
- Product selection and cart validation.
- Checkout workflow automation.
- Assertions to validate expected results.
- Test execution using TestNG annotations.
- Maven-based dependency management.

## ⚙️ Prerequisites

Install the following before running the project:

1. Java JDK
2. Eclipse IDE
3. Maven
4. Google Chrome browser
5. Git

## 🚀 How to Run the Project

### Step 1: Clone the repository

```bash
git clone https://github.com/dhanyadas1144-prog/GreenKartAutomation.git
```


### Step 2: Import into Eclipse

1. Open Eclipse.
2. Select **File → Import**.
3. Choose **Maven → Existing Maven Projects**.
4. Browse to the cloned project folder.
5. Click **Finish**.

### Step 3: Update Maven dependencies

Right-click the project and select:

**Maven → Update Project → OK**

### Step 4: Execute the test suite

1. Locate `testng.xml`.
2. Right-click the file.
3. Select **Run As → TestNG Suite**.

## 📊 Test Execution

TestNG executes the configured test cases and provides results showing passed, failed, and skipped tests.

Detailed results can be reviewed in the TestNG Results view and the generated test output, depending on the project configuration.

## 🔧 Framework Design

The project uses a Page Object Model (POM)-style structure to separate page interactions from test logic.

- **BaseTest:** Manages browser setup and teardown.
- **HomePage:** Contains methods for interacting with the home page.
- **CartPage:** Contains methods for cart-related operations.
- **CheckoutPage:** Contains methods for checkout operations.
- **EndToEndTest:** Validates the complete shopping workflow.

## 👩‍💻 Author

**Dhanya Narendran**

QA Automation | Selenium WebDriver | Java | TestNG | Maven

## 📄 License

This project is intended for learning and demonstration purposes.
