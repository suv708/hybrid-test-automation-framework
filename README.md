# Hybrid Test Automation Framework (Java + Selenium + TestNG)

##  Overview
This project is a **Hybrid Test Automation Framework** developed using **Java**, **Selenium WebDriver**, **TestNG**, **Maven**, and **Page Object Model (POM)** design pattern.  
It is designed to support **scalable automation testing**, reusable utilities, and detailed reporting using **Extent Reports**.

This framework can be used for automating web applications with better maintainability and clean structure.

---

##  Tech Stack Used
- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **Page Object Model (POM)**
- **Extent Reports**
- **Git & GitHub**

---

##  Framework Features
- Hybrid Framework Architecture
- Page Object Model (POM) implementation
- TestNG based test execution
- Maven build and dependency management
- Extent Report generation
- Screenshot capture on test failure
- Reusable utility methods (Waits, Actions, Browser setup)
- Support for cross-browser execution (if implemented)
- Clean and maintainable folder structure

---

##  Project Structure
```bash
hybrid-test-automation-framework/
│── src/
│   ├── main/java/
│   │    ├── base/
│   │    ├── utils/
│   │    ├── elements/
│   │    ├── pages/
│   │    ├── constants/
│   │    ├── reports/
│   │    └── sendReport/
│   ├── test/java/
│   │    ├── tests/
│   │    └── listeners/
│   ├── test/resources/
│   │    ├── config.properties/
│   │    ├── Hybrid_Framework.xlsx/
│   │    └── testng.xml/
│── pom.xml
│── .gitignore
