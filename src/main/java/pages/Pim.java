package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import elements.PageElement;
import utils.WebDriverUtils;

public class Pim {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement pimMenu;
    private PageElement addEmployeeBtn;
    private PageElement firstNameField;
    private PageElement lastNameField;
    private PageElement employeeIdField;
    private PageElement saveButton;
    private PageElement successMessage;

    public Pim(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        pimMenu = new PageElement(driver,
                By.xpath("//span[text()='PIM']"),
                "PIM Menu");

        addEmployeeBtn = new PageElement(driver,
                By.xpath("//a[text()='Add Employee']"),
                "Add Employee Button");

        firstNameField = new PageElement(driver,
                By.xpath("//input[@name='firstName']"),
                "First Name Field");

        lastNameField = new PageElement(driver,
                By.xpath("//input[@name='lastName']"),
                "Last Name Field");

        employeeIdField = new PageElement(driver,
                By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"),
                "Employee Id Field");

        saveButton = new PageElement(driver,
                By.xpath("//button[@type='submit']"),
                "Save Button");

        successMessage = new PageElement(driver,
                By.xpath("//p[text()='Successfully Saved']"),
                "Success Message");
    }

    public void addEmployee() {
    	
        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

        String sheetName = "test_Date";
        String tcId = "TC_POS_005";

        String configFirstName = utils.getCellData(sheetName, tcId, "ConfigEmployeeFirstName").trim();
        String configLastName = utils.getCellData(sheetName, tcId, "ConfigEmployeeLastName").trim();
        String configEmpId = utils.getCellData(sheetName, tcId, "ConfigEmployeeId").trim();
        String configSuccess = utils.getCellData(sheetName, tcId, "ConfigSucessText").trim();

        utils.waitForElementClickable(pimMenu);
        utils.click(pimMenu);

        utils.waitForElementClickable(addEmployeeBtn);
        utils.click(addEmployeeBtn);

        
        if (configFirstName.equals("1") || configFirstName.equalsIgnoreCase("1.0")) {
            String firstName = utils.getCellData(sheetName, tcId, "EmployeeFirstName").trim();
            utils.waitForElementVisible(firstNameField);
            utils.sendKeys(firstNameField, firstName);
        }

       
        if (configLastName.equals("1") || configLastName.equalsIgnoreCase("1.0")) {
            String lastName = utils.getCellData(sheetName, tcId, "EmployeeLastName").trim();
            utils.waitForElementVisible(lastNameField);
            utils.sendKeys(lastNameField, lastName);
        }

        
        if (configEmpId.equals("1") || configEmpId.equalsIgnoreCase("1.0")) {
            String empId = utils.getCellData(sheetName, tcId, "EmployeeId").trim();
            utils.waitForElementVisible(employeeIdField);
            utils.sendKeys(employeeIdField, empId);
        }

        utils.waitForElementClickable(saveButton);
        utils.click(saveButton);

        
        if (utils.isDisplayedSafely(successMessage)) {

            String actualText = utils.getText(successMessage);

            if (configSuccess.equals("1") || configSuccess.equalsIgnoreCase("1.0")) {
                String expectedText = utils.getCellData(sheetName, tcId, "SucessText").trim();
                utils.assertionChecker(actualText, expectedText);
            }
        }

        utils.closeExcel();
    }
}
