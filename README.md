## Project Overview

- The Related Products Automation Framework is designed to validate related products section in product page. It provides end-to-end testing of the related products section for any main product, including category, price, stock, and quality checks.
This framework is built with Java, Playwright, TestNG, and Maven.

## Goals

The framework ensures that:

- Related products sections load correctly.
- Related products meet defined business rules:
- Maximum allowed number of products displayed.
- Same category as main product.
- Prices within acceptable percentage range.
- Only best sellers are displayed.
- Only in-stock products are shown.
- No duplicate products.
- Proper handling of API failures with fallback messages.
- Navigation to related product pages works as expected.

## Features

- Data-driven Testing using @DataProvider to run tests across multiple main products.
- Page Object Model (POM) for reusable page interactions.
- Comprehensive validations for related products.
- ExtentReports integration for reporting.
- Random navigation tests for related product links.

## Technologies & Tools
   
- Java 17+
- Playwright
- TestNG
- ExtentReports
- Maven 
- IntelliJ IDEA 

## Project Structure
- src/test/java/base/    → BaseTest.java (Playwright setup and teardown)
- src/test/java/pages/   → HomePage.java, ProductPage.java (Page Object Model)
- src/test/java/model/   → Product.java, RelatedItemsTestData.java
- src/test/java/data/    → CustomRelatedItemsData.java (Test data provider)
- src/test/java/utils/   → TestManager.java (ExtentReports management)
- src/test/java/tests/   → Test classes for each validation scenario
- src/test/java/listeners/   → TestListener.java (ExtentReports listener)
- report/      → Test execution HTML reports
- pom.xml      → Maven build configuration
- README.md / Documentation → Project documentation

## Test Data

- Stored in CustomRelatedItemsData.java.
Each dataset includes:
- Main product (name, URL, image, category, price, sales count, in-stock).
- Related products array (name, URL, image, category, price, sales count, in-stock).
- Expected results for each validation (category, price, stock, best sellers, API failure).


## Test Cases

The framework includes the following test cases:
- Verify that the Related Products section is displayed on the Product Detail Page 
- Verify that a maximum of six related products are displayed 
- Verify that related products belong to the same category as the main product 
- Verify that related products fall within the defined price range 
- Verify that related product details are displayed correctly 
- Verify navigation to Product Detail Page upon clicking a related product 
- Verify that best seller products are displayed in the Related Products section 
- Verify system behaviour when no related products are available 
- Verify system behaviour when less than six related products are available 
- Verify that related products outside the defined price range are not displayed 
- Verify that related products from different categories are not displayed 
- Verify that duplicate products are not displayed. 
- Verify that out of stock products are not displayed 
- Verify handling of related products with missing data 
- Verify system behaviour when the Related Products API fails

## Framework Workflow

- Setup: Playwright launches browser and creates page context (BaseTest).
- Navigation: Open eBay home page → search product → open product page.
- Page Loading: Wait for product page to load fully (ProductPage.waitForProduct).
- Validations: Each test performs specific validations using methods in ProductPage:
Section visibility,
Max products,
Category match,
Price range,
Best seller check,
Duplicate check,
Stock check,
Related product navigation,
API fallback handling
- Reporting: ExtentReports captures logs and pass/fail results.
- Teardown: Browser and Playwright session closed (BaseTest.tearDown).

## Prerequisites

- Java 21 installed
- Maven installed
- IntelliJ IDEA 
- Git installed

## Setup Instructions

1. Clone the repository:
   git clone https://github.com/kishosiva/playwright-related-products.git
   
3. Navigate to project folder:
   cd surge_global_assessment

4. Install dependencies:
   mvn clean install

## Run Tests

Run using Maven: mvn clean test
OR
Run testng.xml file

## Reporting
- Reports are generated in report/TestReport.html.
- Features: Test pass/fail status 
- Validation logs 
- Navigation logs
