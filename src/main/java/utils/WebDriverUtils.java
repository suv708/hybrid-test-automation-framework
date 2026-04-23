//package utils;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.*;
//
//import elements.PageElement;
//
//import java.io.FileInputStream;
//import java.time.Duration;
////import org.testng.Assert;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class WebDriverUtils {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private Actions actions;
//    private JavascriptExecutor js;
//    
//    // Excel Variables
//    private Workbook workbook;
//
//    // =================== CONSTRUCTOR ===================
//    public WebDriverUtils(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        this.actions = new Actions(driver);
//        this.js = (JavascriptExecutor) driver;
//    }
//    
//    // wait for method
//    public WebElement waitForElementClickable(PageElement element) {
//        return wait.until(ExpectedConditions.elementToBeClickable(element.getLocator()));
//    }
//    
//    // wait for visible
//    public WebElement waitForElementVisible(PageElement element) {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
//    }
//
//    // =================== EXCEL LOAD METHOD ===================
//    public void loadExcel(String excelPath) {
//        try {
//            FileInputStream fis = new FileInputStream(excelPath);
//            workbook = new XSSFWorkbook(fis);
//        } catch (Exception e) {
//            throw new RuntimeException("❌ Unable to load Excel file: " + excelPath);
//        }
//    }
//    
//    // Assertion 
//    public void assertionChecker(String actual, String expected) {
//
//        if (actual == null && expected == null) {
//            System.out.println("✅ Assertion Passed: Both values are null");
//            return;
//        }
//
//        if (actual == null || expected == null) {
//            throw new RuntimeException("❌ Assertion Failed: One value is null. Actual = "
//                    + actual + " , Expected = " + expected);
//        }
//
//        if (!actual.equals(expected)) {
//            throw new RuntimeException("❌ Assertion Failed: " + actual + " != " + expected);
//        }
//
//        System.out.println("✅ Assertion Passed: " + actual + " = " + expected);
//    }
//
//    // =================== WAITS ===================
//    public WebElement waitForVisibility(By locator) {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    public WebElement waitForClickable(By locator) {
//        return wait.until(ExpectedConditions.elementToBeClickable(locator));
//    }
//
//    public boolean waitForText(By locator, String expectedText) {
//        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
//    }
//
//    // =================== CLICK METHODS ===================
//    public void click(By locator) {
//        try {
//            waitForClickable(locator).click();
//        } catch (Exception e) {
//            jsClick(locator);
//        }
//    }
//    
//    public void click(PageElement element) {
//        try {
//            waitForClickable(element.getLocator()).click();
//        } catch (Exception e) {
//            jsClick(element.getLocator());
//        }
//    }
//    
//    public void clickWithWait(PageElement element) {
//
//        WebDriverWait wait30 = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        try {
//            wait30.until(ExpectedConditions.elementToBeClickable(element.getLocator())).click();
//        } catch (Exception e) {
//            WebElement ele = wait30.until(ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
//            js.executeScript("arguments[0].click();", ele);
//        }
//    }
//
//    public void jsClick(By locator) {
//        WebElement element = waitForVisibility(locator);
//        js.executeScript("arguments[0].click();", element);
//    }
//
//    public void clickWithRetry(By locator, int retryCount) {
//        int attempt = 0;
//
//        while (attempt < retryCount) {
//            try {
//                click(locator);
//                return;
//            } catch (Exception e) {
//                attempt++;
//
//                if (attempt == retryCount) {
//                    throw new RuntimeException("❌ Element not clickable even after retry: " + locator);
//                }
//            }
//        }
//    }
//
//    // =================== SEND KEYS METHODS ===================
////    public void sendKeys(PageElement geminiTextField, String value) {
////        WebElement element = waitForVisibility(geminiTextField);
////        element.clear();
////        element.sendKeys(value);
////    }
//    
//    public void sendKeys(PageElement element, String value) {
//        WebElement ele = waitForVisibility(element.getLocator());
//        ele.sendKeys(value);
//    }
//    
//    public void clearAndSendKeys(PageElement element, CharSequence... keys) {
//        WebElement ele = waitForVisibility(element.getLocator());
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
//
//        try {
//            ele.click();
//        } catch (Exception e) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
//        }
//
//        ele.sendKeys(Keys.CONTROL + "a");
//        ele.sendKeys(Keys.BACK_SPACE);
//
//        ele.sendKeys(keys);
//    }
//    
//    public void sendKeys(PageElement element, CharSequence... keys) {
//        WebElement ele = waitForVisibility(element.getLocator());
//        ele.click();   // focus on input
//        ele.sendKeys(keys);
//    }
//    
//    public void clearText(PageElement element) {
//        WebElement ele = waitForVisibility(element.getLocator());
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
//
//        try {
//            ele.click();
//        } catch (Exception e) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
//        }
//
//        ele.sendKeys(Keys.CONTROL + "a");
//        ele.sendKeys(Keys.BACK_SPACE);
//    }
//
//    public void sendKeysSlow(By locator, String value) {
//        WebElement element = waitForVisibility(locator);
//        element.clear();
//
//        for (char ch : value.toCharArray()) {
//            element.sendKeys(String.valueOf(ch));
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//    // =================== GET TEXT / ATTRIBUTES ===================
//    public String getText(By locator) {
//        return waitForVisibility(locator).getText().trim();
//    }
//    
//    public String getText(PageElement element) {
//        return waitForVisibility(element.getLocator()).getText().trim();
//    }
//
//    public String getAttribute(By locator, String attributeName) {
//        return waitForVisibility(locator).getAttribute(attributeName);
//    }
//
//    // =================== DROPDOWN METHODS ===================
//    public void selectByVisibleText(By locator, String text) {
//        Select dropdown = new Select(waitForVisibility(locator));
//        dropdown.selectByVisibleText(text);
//    }
//
//    public void selectByValue(By locator, String value) {
//        Select dropdown = new Select(waitForVisibility(locator));
//        dropdown.selectByValue(value);
//    }
//
//    public void selectByIndex(By locator, int index) {
//        Select dropdown = new Select(waitForVisibility(locator));
//        dropdown.selectByIndex(index);
//    }
//
//    // =================== ACTIONS METHODS ===================
//    public void hover(By locator) {
//        actions.moveToElement(waitForVisibility(locator)).perform();
//    }
//
//    public void doubleClick(By locator) {
//        actions.doubleClick(waitForClickable(locator)).perform();
//    }
//
//    public void rightClick(By locator) {
//        actions.contextClick(waitForClickable(locator)).perform();
//    }
//
//    // =================== SCROLL METHODS ===================
//    public void scrollToElement(By locator) {
//        WebElement element = waitForVisibility(locator);
//        js.executeScript("arguments[0].scrollIntoView(true);", element);
//    }
//
//    public void scrollDown() {
//        js.executeScript("window.scrollBy(0,500);");
//    }
//
//    // =================== VALIDATION METHODS ===================
//    public boolean isDisplayedSafely(PageElement element) {
//        try {
//            return waitForVisibility(element.getLocator()).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//    
//    public boolean isEnabled(By locator) {
//        try {
//            return waitForVisibility(locator).isEnabled();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public boolean isSelected(By locator) {
//        try {
//            return waitForVisibility(locator).isSelected();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    // =================== PAGE LOAD WAIT ===================
//    public void waitForPageLoad() {
//        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
//    }
//
//    // =================== ALERT METHODS ===================
//    public void acceptAlert() {
//        wait.until(ExpectedConditions.alertIsPresent()).accept();
//    }
//
//    public void dismissAlert() {
//        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
//    }
//
//    public String getAlertText() {
//        return wait.until(ExpectedConditions.alertIsPresent()).getText();
//    }
//
//    // ============================================================
//    // =================== EXCEL UTILITY METHODS ==================
//    // ============================================================
//
//    // ✅ Safe cell value fetcher
//    private String getCellValue(Cell cell) {
//        if (cell == null) return "";
//
//        DataFormatter formatter = new DataFormatter();
//        return formatter.formatCellValue(cell).trim();
//    }
//
//    // ✅ Get Row Number using TC_ID
//    private int getRowIndexByTCID(Sheet sheet, String tcId) {
//
//        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//            Row row = sheet.getRow(i);
//            if (row == null) continue;
//
//            Cell cell = row.getCell(0); // TC_ID column always 0
//            if (cell == null) continue;
//
//            String actualTCID = getCellValue(cell);
//
//            if (actualTCID.equalsIgnoreCase(tcId)) {
//                return i;
//            }
//        }
//
//        return -1;
//    }
//
//    // ✅ Get Column Number using Column Name
//    private int getColumnIndexByName(Sheet sheet, String columnName) {
//
//        Row headerRow = sheet.getRow(0);
//        if (headerRow == null)
//            throw new RuntimeException("❌ Header row not found in sheet!");
//
//        for (int col = 0; col < headerRow.getLastCellNum(); col++) {
//            Cell cell = headerRow.getCell(col);
//
//            if (cell != null && getCellValue(cell).equalsIgnoreCase(columnName)) {
//                return col;
//            }
//        }
//
//        return -1;
//    }
//
//    // ✅ MAIN METHOD (SheetName + TC_ID + ColumnName)
//    public String getCellData(String sheetName, String tcId, String columnName) {
//
//        if (workbook == null)
//            throw new RuntimeException("❌ Excel is not loaded! Call loadExcel(path) first.");
//
//        Sheet sheet = workbook.getSheet(sheetName);
//
//        if (sheet == null)
//            throw new RuntimeException("❌ Sheet not found: " + sheetName);
//
//        int rowIndex = getRowIndexByTCID(sheet, tcId);
//
//        if (rowIndex == -1)
//            throw new RuntimeException("❌ TC_ID not found: " + tcId);
//
//        int colIndex = getColumnIndexByName(sheet, columnName);
//
//        if (colIndex == -1)
//            throw new RuntimeException("❌ Column not found: " + columnName);
//
//        Row row = sheet.getRow(rowIndex);
//
//        return getCellValue(row.getCell(colIndex));
//    }
//
//    // ✅ Close workbook
//    public void closeExcel() {
//        try {
//            if (workbook != null) {
//                workbook.close();
//            }
//        } catch (Exception e) {
//            System.out.println("⚠️ Unable to close Excel.");
//        }
//    }
//}






package utils;

import elements.PageElement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import reports.ExtentTestManager;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;

public class WebDriverUtils {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;

    // Excel Variables
    private Workbook workbook;

    // =================== CONSTRUCTOR ===================
    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    // =================== LOG METHODS ===================
    private void logInfo(String message) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().info(message);
        } else {
            System.out.println(message);
        }
    }

    private void logPass(String message) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().pass(message);
        } else {
            System.out.println(message);
        }
    }

    private void logFail(String message) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().fail(message);
        } else {
            System.out.println(message);
        }
    }

    private void logWarning(String message) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().warning(message);
        } else {
            System.out.println(message);
        }
    }

    // =================== WAIT METHODS ===================
    public WebElement waitForElementClickable(PageElement element) {
        logInfo("⏳ Waiting for element clickable: " + element.getName() + " | Locator: " + element.getLocator());
        return wait.until(ExpectedConditions.elementToBeClickable(element.getLocator()));
    }

    public WebElement waitForElementVisible(PageElement element) {
        logInfo("⏳ Waiting for element visible: " + element.getName() + " | Locator: " + element.getLocator());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
    }

    public WebElement waitForVisibility(By locator) {
        logInfo("⏳ Waiting for visibility of locator: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        logInfo("⏳ Waiting for clickable locator: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForText(By locator, String expectedText) {
        logInfo("⏳ Waiting for text: '" + expectedText + "' on locator: " + locator);
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }

    // =================== EXCEL LOAD METHOD ===================
    public void loadExcel(String excelPath) {
        try {
            logInfo("📂 Loading Excel file: " + excelPath);
            FileInputStream fis = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fis);
            logPass("✅ Excel Loaded Successfully: " + excelPath);
        } catch (Exception e) {
            logFail("❌ Unable to load Excel file: " + excelPath);
            throw new RuntimeException("❌ Unable to load Excel file: " + excelPath);
        }
    }

    // =================== ASSERTION METHOD ===================
    public void assertionChecker(String expected, String actual) {

        logInfo("🔍 Assertion Check Started");
        logInfo("Expected Value: " + expected);
        logInfo("Actual Value: " + actual);

        if (expected == null && actual == null) {
            logPass("✅ Assertion Passed: Both values are null");
            return;
        }

        if (expected == null || actual == null) {
            logFail("❌ Assertion Failed: One value is null. Expected=" + expected + " Actual=" + actual);
            throw new RuntimeException("❌ Assertion Failed: One value is null. Expected=" + expected + " Actual=" + actual);
        }

        if (!expected.equals(actual)) {
            logFail("❌ Assertion Failed: Expected=" + expected + " Actual=" + actual);
            throw new RuntimeException("❌ Assertion Failed: Expected=" + expected + " Actual=" + actual);
        }

        logPass("✅ Assertion Passed: Expected=" + expected + " Actual=" + actual);
    }

    // =================== CLICK METHODS ===================
    public void click(By locator) {
        try {
            logInfo("🖱 Clicking on locator: " + locator);
            waitForClickable(locator).click();
            logPass("✅ Click Successful on locator: " + locator);
        } catch (Exception e) {
            logWarning("⚠ Normal click failed. Trying JS click on locator: " + locator);
            jsClick(locator);
        }
    }

    public void click(PageElement element) {
        try {
            logInfo("🖱 Clicking on element: " + element.getName() + " | Locator: " + element.getLocator());
            waitForClickable(element.getLocator()).click();
            logPass("✅ Click Successful: " + element.getName());
        } catch (Exception e) {
            logWarning("⚠ Normal click failed. Trying JS click: " + element.getName());
            jsClick(element.getLocator());
            logPass("✅ JS Click Successful: " + element.getName());
        }
    }

    public void clickWithWait(PageElement element) {

        WebDriverWait wait30 = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            logInfo("🖱 Clicking with wait: " + element.getName());
            wait30.until(ExpectedConditions.elementToBeClickable(element.getLocator())).click();
            logPass("✅ Click Successful: " + element.getName());
        } catch (Exception e) {
            logWarning("⚠ ClickWithWait failed. Trying JS click: " + element.getName());
            WebElement ele = wait30.until(ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
            js.executeScript("arguments[0].click();", ele);
            logPass("✅ JS Click Successful: " + element.getName());
        }
    }

    public void jsClick(By locator) {
        logInfo("⚡ Performing JS Click on locator: " + locator);
        WebElement element = waitForVisibility(locator);
        js.executeScript("arguments[0].click();", element);
        logPass("✅ JS Click executed successfully: " + locator);
    }

    public void clickWithRetry(By locator, int retryCount) {

        int attempt = 0;
        logInfo("🔁 Clicking with retry: " + locator + " | Retry Count: " + retryCount);

        while (attempt < retryCount) {
            try {
                click(locator);
                return;
            } catch (Exception e) {
                attempt++;
                logWarning("⚠ Retry Attempt " + attempt + " failed for locator: " + locator);

                if (attempt == retryCount) {
                    logFail("❌ Element not clickable even after retry: " + locator);
                    throw new RuntimeException("❌ Element not clickable even after retry: " + locator);
                }
            }
        }
    }

    // =================== SEND KEYS METHODS ===================
    public void sendKeys(PageElement element, String value) {
        logInfo("⌨ Typing in: " + element.getName() + " | Value: " + value);
        WebElement ele = waitForVisibility(element.getLocator());
        ele.sendKeys(value);
        logPass("✅ Value entered successfully in: " + element.getName());
    }

    public void clearAndSendKeys(PageElement element, CharSequence... keys) {

        WebElement ele = waitForVisibility(element.getLocator());

        logInfo("⌨ Clear & SendKeys on: " + element.getName() + " | Value: " + Arrays.toString(keys));

        js.executeScript("arguments[0].scrollIntoView(true);", ele);

        try {
            ele.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", ele);
        }

        ele.sendKeys(Keys.CONTROL + "a");
        ele.sendKeys(Keys.BACK_SPACE);

        ele.sendKeys(keys);

        logPass("✅ Value entered successfully in: " + element.getName());
    }

    public void sendKeys(PageElement element, CharSequence... keys) {

        logInfo("⌨ SendKeys on: " + element.getName() + " | Value: " + Arrays.toString(keys));

        WebElement ele = waitForVisibility(element.getLocator());
        ele.click();
        ele.sendKeys(keys);

        logPass("✅ Keys entered successfully in: " + element.getName());
    }

    public void clearText(PageElement element) {

        logInfo("🧹 Clearing text field: " + element.getName());

        WebElement ele = waitForVisibility(element.getLocator());

        js.executeScript("arguments[0].scrollIntoView(true);", ele);

        try {
            ele.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", ele);
        }

        ele.sendKeys(Keys.CONTROL + "a");
        ele.sendKeys(Keys.BACK_SPACE);

        logPass("✅ Text cleared successfully: " + element.getName());
    }

    public void sendKeysSlow(By locator, String value) {

        logInfo("⌨ Typing slowly into locator: " + locator + " | Value: " + value);

        WebElement element = waitForVisibility(locator);
        element.clear();

        for (char ch : value.toCharArray()) {
            element.sendKeys(String.valueOf(ch));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        logPass("✅ Slow typing completed: " + locator);
    }

    // =================== GET TEXT / ATTRIBUTES ===================
    public String getText(By locator) {
        logInfo("📄 Getting text from locator: " + locator);
        String text = waitForVisibility(locator).getText().trim();
        logInfo("📌 Text Found: " + text);
        return text;
    }

    public String getText(PageElement element) {
        logInfo("📄 Getting text from element: " + element.getName());
        String text = waitForVisibility(element.getLocator()).getText().trim();
        logInfo("📌 Text Found: " + text);
        return text;
    }

    public String getAttribute(By locator, String attributeName) {
        logInfo("📄 Getting attribute: " + attributeName + " from locator: " + locator);
        return waitForVisibility(locator).getAttribute(attributeName);
    }

    // =================== DROPDOWN METHODS ===================
    public void selectByVisibleText(By locator, String text) {
        logInfo("🔽 Selecting dropdown by visible text: " + text + " | Locator: " + locator);
        Select dropdown = new Select(waitForVisibility(locator));
        dropdown.selectByVisibleText(text);
        logPass("✅ Dropdown selected successfully: " + text);
    }

    public void selectByValue(By locator, String value) {
        logInfo("🔽 Selecting dropdown by value: " + value + " | Locator: " + locator);
        Select dropdown = new Select(waitForVisibility(locator));
        dropdown.selectByValue(value);
        logPass("✅ Dropdown selected successfully: " + value);
    }

    public void selectByIndex(By locator, int index) {
        logInfo("🔽 Selecting dropdown by index: " + index + " | Locator: " + locator);
        Select dropdown = new Select(waitForVisibility(locator));
        dropdown.selectByIndex(index);
        logPass("✅ Dropdown selected successfully: Index " + index);
    }

    // =================== ACTIONS METHODS ===================
    public void hover(By locator) {
        logInfo("🖱 Hovering on element: " + locator);
        actions.moveToElement(waitForVisibility(locator)).perform();
        logPass("✅ Hover performed successfully: " + locator);
    }

    public void doubleClick(By locator) {
        logInfo("🖱 Double clicking on element: " + locator);
        actions.doubleClick(waitForClickable(locator)).perform();
        logPass("✅ Double click successful: " + locator);
    }

    public void rightClick(By locator) {
        logInfo("🖱 Right clicking on element: " + locator);
        actions.contextClick(waitForClickable(locator)).perform();
        logPass("✅ Right click successful: " + locator);
    }

    // =================== SCROLL METHODS ===================
    public void scrollToElement(By locator) {
        logInfo("📜 Scrolling to element: " + locator);
        WebElement element = waitForVisibility(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        logPass("✅ Scrolled successfully to element: " + locator);
    }

    public void scrollDown() {
        logInfo("📜 Scrolling down page");
        js.executeScript("window.scrollBy(0,500);");
        logPass("✅ Scroll down performed");
    }

    // =================== VALIDATION METHODS ===================
    public boolean isDisplayedSafely(PageElement element) {
        try {
            boolean status = waitForVisibility(element.getLocator()).isDisplayed();
            logInfo("👀 Display check: " + element.getName() + " = " + status);
            return status;
        } catch (Exception e) {
            logWarning("⚠ Element not displayed: " + element.getName());
            return false;
        }
    }

    public boolean isEnabled(By locator) {
        try {
            boolean status = waitForVisibility(locator).isEnabled();
            logInfo("👀 Enabled check: " + locator + " = " + status);
            return status;
        } catch (Exception e) {
            logWarning("⚠ Element not enabled: " + locator);
            return false;
        }
    }

    public boolean isSelected(By locator) {
        try {
            boolean status = waitForVisibility(locator).isSelected();
            logInfo("👀 Selected check: " + locator + " = " + status);
            return status;
        } catch (Exception e) {
            logWarning("⚠ Element not selected: " + locator);
            return false;
        }
    }

    // =================== PAGE LOAD WAIT ===================
    public void waitForPageLoad() {
        logInfo("⏳ Waiting for Page Load...");
        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
        logPass("✅ Page Loaded Successfully");
    }

    // =================== ALERT METHODS ===================
    public void acceptAlert() {
        logInfo("⚠ Accepting alert...");
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        logPass("✅ Alert accepted successfully");
    }

    public void dismissAlert() {
        logInfo("⚠ Dismissing alert...");
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        logPass("✅ Alert dismissed successfully");
    }

    public String getAlertText() {
        logInfo("⚠ Getting alert text...");
        String text = wait.until(ExpectedConditions.alertIsPresent()).getText();
        logInfo("📌 Alert Text: " + text);
        return text;
    }

    // ============================================================
    // =================== EXCEL UTILITY METHODS ==================
    // ============================================================

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

    private int getRowIndexByTCID(Sheet sheet, String tcId) {

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Cell cell = row.getCell(0);
            if (cell == null) continue;

            String actualTCID = getCellValue(cell);

            if (actualTCID.equalsIgnoreCase(tcId)) {
                return i;
            }
        }

        return -1;
    }

    private int getColumnIndexByName(Sheet sheet, String columnName) {

        Row headerRow = sheet.getRow(0);
        if (headerRow == null)
            throw new RuntimeException("❌ Header row not found in sheet!");

        for (int col = 0; col < headerRow.getLastCellNum(); col++) {
            Cell cell = headerRow.getCell(col);

            if (cell != null && getCellValue(cell).equalsIgnoreCase(columnName)) {
                return col;
            }
        }

        return -1;
    }

    public String getCellData(String sheetName, String tcId, String columnName) {

//        logInfo("📌 Fetching Excel Data -> Sheet: " + sheetName + " | TC_ID: " + tcId + " | Column: " + columnName);

        if (workbook == null)
            throw new RuntimeException("❌ Excel is not loaded! Call loadExcel(path) first.");

        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null)
            throw new RuntimeException("❌ Sheet not found: " + sheetName);

        int rowIndex = getRowIndexByTCID(sheet, tcId);

        if (rowIndex == -1)
            throw new RuntimeException("❌ TC_ID not found: " + tcId);

        int colIndex = getColumnIndexByName(sheet, columnName);

        if (colIndex == -1)
            throw new RuntimeException("❌ Column not found: " + columnName);

        Row row = sheet.getRow(rowIndex);

        String value = getCellValue(row.getCell(colIndex));
        logInfo("📌 Excel Value Found: " + value);

        return value;
    }

    public void closeExcel() {
        try {
            if (workbook != null) {
                workbook.close();
                logPass("✅ Excel closed successfully");
            }
        } catch (Exception e) {
            logWarning("⚠ Unable to close Excel.");
        }
    }
}