
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import elements.PageElement;
import utils.WebDriverUtils;

public class MyInfo {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement myInfoMenu;
    private PageElement editButton;

    private PageElement firstNameField;
    private PageElement middleNameField;
    private PageElement lastNameField;

    private PageElement empId;
    private PageElement otherId;
    private PageElement licenseNumber;
    private PageElement licenseExpireDate;
    private PageElement dateOfBirth;
    private PageElement uploadInput;

    private PageElement saveButton;
    private PageElement saveButton1;
    private PageElement successMessage;

    public MyInfo(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        myInfoMenu = new PageElement(driver,
                By.xpath("//span[text()='My Info']"),
                "My Info Menu");

        editButton = new PageElement(driver,
                By.xpath("//button[contains(@class,'oxd-icon-button')]//i[contains(@class,'bi-pencil-fill')]"),
                "Edit Button");

        firstNameField = new PageElement(driver,
                By.xpath("//input[@name='firstName']"),
                "First Name Field");

        middleNameField = new PageElement(driver,
                By.xpath("//input[@name='middleName']"),
                "Middle Name Field");

        lastNameField = new PageElement(driver,
                By.xpath("//input[@name='lastName']"),
                "Last Name Field");

        empId = new PageElement(driver,
                By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"),
                "Employee Id");

        otherId = new PageElement(driver,
                By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]"),
                "Other Id");

        licenseNumber = new PageElement(driver,
                By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]"),
                "License Number");
        
        licenseExpireDate = new PageElement(driver,
                By.xpath("(//input[@placeholder='yyyy-dd-mm' ])[1]"),
                "license Expire Date");
        
        dateOfBirth = new PageElement(driver,
                By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]"),
                "date Of Birth");
        
        uploadInput= new PageElement(driver,
                By.xpath("//i[@class='oxd-icon bi-upload oxd-file-input-icon']"),
                "upload Input");
        
        saveButton = new PageElement(driver,
                By.xpath("(//button[@type='submit'])[1]"),
                "Save Button");
        
        saveButton1 = new PageElement(driver,
                By.xpath("(//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space'])[3]"),
                "Save Button");
        
        successMessage = new PageElement(driver,
                By.xpath("//p[text()='Successfully Updated']"),
                "Success Message");
    }

    public void updatePersonalDetails() throws InterruptedException {

        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

        String sheetName = "test_Date";
        String tcId = "TC_POS_009";

        String ConfigFirstName = utils.getCellData(sheetName, tcId, "ConfigEmployeeFirstName");
        String ConfigMiddleName = utils.getCellData(sheetName, tcId, "ConfigEmployeeMiddleName");
        String ConfigLastName = utils.getCellData(sheetName, tcId, "ConfigEmployeeLastName");
        String ConfigEmployeeId = utils.getCellData(sheetName, tcId, "ConfigEmployeeId");
        String ConfigOtherId = utils.getCellData(sheetName, tcId, "ConfigOtherId");
        String ConfigLicenceNumber = utils.getCellData(sheetName, tcId, "ConfigLicenceNumber");

        String firstName = utils.getCellData(sheetName, tcId, "EmployeeFirstName").trim();
        String middleName = utils.getCellData(sheetName, tcId, "EmployeeMiddleName").trim();
        String lastName = utils.getCellData(sheetName, tcId, "EmployeeLastName").trim();
        String empid = utils.getCellData(sheetName, tcId, "EmployeeId").trim();
        String otherid = utils.getCellData(sheetName, tcId, "OtherId").trim();
        String licenceNumberValue = utils.getCellData(sheetName, tcId, "LicenceNumber").trim();

        utils.waitForElementClickable(myInfoMenu);
        utils.click(myInfoMenu);

        utils.waitForElementClickable(editButton);
        utils.click(editButton);

        utils.waitForElementVisible(firstNameField);

        if (ConfigFirstName.equals("1") || ConfigFirstName.equalsIgnoreCase("1.0")) {
            utils.clearAndSendKeys(firstNameField, firstName);
        }

        if (ConfigMiddleName.equals("1") || ConfigMiddleName.equalsIgnoreCase("1.0")) {
            utils.clearAndSendKeys(middleNameField, middleName);
        }

        if (ConfigLastName.equals("1") || ConfigLastName.equalsIgnoreCase("1.0")) {
            utils.clearAndSendKeys(lastNameField, lastName);
        }

        if (ConfigEmployeeId.equals("1") || ConfigEmployeeId.equalsIgnoreCase("1.0")) {
            utils.clearAndSendKeys(empId, empid);
        }

        if (ConfigOtherId.equals("1") || ConfigOtherId.equalsIgnoreCase("1.0")) {
            utils.clearAndSendKeys(otherId, otherid);
        }

        if (ConfigLicenceNumber.equals("1") || ConfigLicenceNumber.equalsIgnoreCase("1.0")) {
            utils.clearAndSendKeys(licenseNumber, licenceNumberValue);
        }
        
        utils.clearAndSendKeys(licenseExpireDate,"2026-01-16");
        
        utils.clearAndSendKeys(dateOfBirth,"2005-01-07");
        
        utils.waitForElementClickable(saveButton);
        utils.click(saveButton);
        
        if (utils.isDisplayedSafely(successMessage)) {
            String actualText = utils.getText(successMessage);
            utils.assertionChecker("Successfully Updated", actualText);
        }
        
        utils.closeExcel();
    }
}

// ________________________________________________________________________
//
//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import elements.PageElement;
//import utils.WebDriverUtils;
//
//public class MyInfo {
//
//    private WebDriver driver;
//    private WebDriverUtils utils;
//
//    private PageElement myInfoMenu;
//
//    private PageElement firstNameField;
//    private PageElement middleNameField;
//    private PageElement lastNameField;
//
//    private PageElement empId;
//    private PageElement otherId;
//    private PageElement licenseNumber;
//    private PageElement licenseExpireDate;
//    private PageElement dateOfBirth;
//
//    private PageElement saveButton;
//    private PageElement successMessage;
//
//    public MyInfo(WebDriver driver) {
//        this.driver = driver;
//        this.utils = new WebDriverUtils(driver);
//
//        myInfoMenu = new PageElement(driver,
//                By.xpath("//span[text()='My Info']"),
//                "My Info Menu");
//
//        firstNameField = new PageElement(driver,
//                By.xpath("//input[@name='firstName']"),
//                "First Name Field");
//
//        middleNameField = new PageElement(driver,
//                By.xpath("//input[@name='middleName']"),
//                "Middle Name Field");
//
//        lastNameField = new PageElement(driver,
//                By.xpath("//input[@name='lastName']"),
//                "Last Name Field");
//
//        // ✅ Stable locators (Label Based)
//        empId = new PageElement(driver,
//                By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"),
//                "Employee Id");
//
//        otherId = new PageElement(driver,
//                By.xpath("//label[text()='Other Id']/../following-sibling::div/input"),
//                "Other Id");
//
//        licenseNumber = new PageElement(driver,
//                By.xpath("//label[text()=\"Driver's License Number\"]/../following-sibling::div/input"),
//                "License Number");
//
//        licenseExpireDate = new PageElement(driver,
//                By.xpath("//label[text()='License Expiry Date']/../following-sibling::div//input"),
//                "License Expire Date");
//
//        dateOfBirth = new PageElement(driver,
//                By.xpath("//label[text()='Date of Birth']/../following-sibling::div//input"),
//                "Date Of Birth");
//
//        saveButton = new PageElement(driver,
//                By.xpath("//button[@type='submit']"),
//                "Save Button");
//
//        successMessage = new PageElement(driver,
//                By.xpath("//p[contains(@class,'oxd-text--toast-message') and text()='Successfully Updated']"),
//                "Success Message");
//    }
//
//    public void updatePersonalDetails() {
//
//        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");
//
//        String sheetName = "test_Date";
//        String tcId = "TC_POS_009";
//
//        String ConfigFirstName = utils.getCellData(sheetName, tcId, "ConfigEmployeeFirstName");
//        String ConfigMiddleName = utils.getCellData(sheetName, tcId, "ConfigEmployeeMiddleName");
//        String ConfigLastName = utils.getCellData(sheetName, tcId, "ConfigEmployeeLastName");
//        String ConfigEmployeeId = utils.getCellData(sheetName, tcId, "ConfigEmployeeId");
//        String ConfigOtherId = utils.getCellData(sheetName, tcId, "ConfigOtherId");
//        String ConfigLicenceNumber = utils.getCellData(sheetName, tcId, "ConfigLicenceNumber");
//
//        String firstName = utils.getCellData(sheetName, tcId, "EmployeeFirstName").trim();
//        String middleName = utils.getCellData(sheetName, tcId, "EmployeeMiddleName").trim();
//        String lastName = utils.getCellData(sheetName, tcId, "EmployeeLastName").trim();
//        String empid = utils.getCellData(sheetName, tcId, "EmployeeId").trim();
//        String otherid = utils.getCellData(sheetName, tcId, "OtherId").trim();
//        String licenceNumberValue = utils.getCellData(sheetName, tcId, "LicenceNumber").trim();
//
//        utils.waitForPageLoad();
//
//        utils.click(myInfoMenu);
//
//        utils.waitForElementVisible(firstNameField);
//
//        if (ConfigFirstName.equals("1") || ConfigFirstName.equalsIgnoreCase("1.0")) {
//            utils.clearAndSendKeys(firstNameField, firstName);
//        }
//
//        if (ConfigMiddleName.equals("1") || ConfigMiddleName.equalsIgnoreCase("1.0")) {
//            utils.clearAndSendKeys(middleNameField, middleName);
//        }
//
//        if (ConfigLastName.equals("1") || ConfigLastName.equalsIgnoreCase("1.0")) {
//            utils.clearAndSendKeys(lastNameField, lastName);
//        }
//
//        if (ConfigEmployeeId.equals("1") || ConfigEmployeeId.equalsIgnoreCase("1.0")) {
//            utils.clearAndSendKeys(empId, empid);
//        }
//
//        if (ConfigOtherId.equals("1") || ConfigOtherId.equalsIgnoreCase("1.0")) {
//            utils.clearAndSendKeys(otherId, otherid);
//        }
//
//        if (ConfigLicenceNumber.equals("1") || ConfigLicenceNumber.equalsIgnoreCase("1.0")) {
//            utils.clearAndSendKeys(licenseNumber, licenceNumberValue);
//        }
//
//        // ✅ Date format correct: yyyy-mm-dd
//        utils.clearAndSendKeys(licenseExpireDate, "2026-01-16");
//        utils.clearAndSendKeys(dateOfBirth, "2005-01-07");
//
//        utils.click(saveButton);
//
//        if (utils.isDisplayedSafely(successMessage)) {
//            String actualText = utils.getText(successMessage);
//            utils.assertionChecker(actualText, "Successfully Updated");
//        }
//
//        utils.closeExcel();
//    }
//}