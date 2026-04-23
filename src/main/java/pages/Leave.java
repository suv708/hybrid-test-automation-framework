package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import elements.PageElement;
import utils.WebDriverUtils;

public class Leave {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement leaveMenu;
    private PageElement applyMenu;
    private PageElement leaveTypeDropdown;
    private PageElement fromDateField;
    private PageElement toDateField;
    private PageElement applyButton;
    private PageElement successMessage;

    public Leave(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        leaveMenu = new PageElement(driver,
                By.xpath("//span[text()='Leave']"),
                "Leave Menu");

        applyMenu = new PageElement(driver,
                By.xpath("//a[text()='Apply']"),
                "Apply Leave Menu");

        leaveTypeDropdown = new PageElement(driver,
                By.xpath("(//label[text()='Leave Type']/../following-sibling::div//div[contains(@class,'oxd-select-text')])[1]"),
                "Leave Type Dropdown");

        fromDateField = new PageElement(driver,
                By.xpath("//label[text()='From Date']/../following-sibling::div//input"),
                "From Date Field");

        toDateField = new PageElement(driver,
                By.xpath("//label[text()='To Date']/../following-sibling::div//input"),
                "To Date Field");

        applyButton = new PageElement(driver,
                By.xpath("//button[@type='submit']"),
                "Apply Button");

        successMessage = new PageElement(driver,
                By.xpath("//p[text()='Successfully Saved']"),
                "Success Message");
    }

    public void applyLeave() {

        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

        String sheetName = "test_Date";
        String tcId = "TC_POS_006";

        String configLeaveType = utils.getCellData(sheetName, tcId, "ConfigLeaveType").trim();
        String configFromDate = utils.getCellData(sheetName, tcId, "ConfigFromDate").trim();
        String configToDate = utils.getCellData(sheetName, tcId, "ConfigToDate").trim();

        utils.waitForElementClickable(leaveMenu);
        utils.click(leaveMenu);

        utils.waitForElementClickable(applyMenu);
        utils.click(applyMenu);

        
        if (configLeaveType.equals("1") || configLeaveType.equalsIgnoreCase("1.0")) {

            String leaveType = utils.getCellData(sheetName, tcId, "LeaveType").trim();

            utils.waitForElementClickable(leaveTypeDropdown);
            utils.click(leaveTypeDropdown);

            PageElement leaveTypeOption = new PageElement(driver,
                    By.xpath("//div[@role='listbox']//span[text()='" + leaveType + "']"),
                    "Leave Type Option");

            utils.waitForElementClickable(leaveTypeOption);
            utils.click(leaveTypeOption);
        }

       
        if (configFromDate.equals("1") || configFromDate.equalsIgnoreCase("1.0")) {

            String fromDate = utils.getCellData(sheetName, tcId, "FromDate").trim();

            utils.waitForElementVisible(fromDateField);
            utils.sendKeys(fromDateField, fromDate);
        }

        
        if (configToDate.equals("1") || configToDate.equalsIgnoreCase("1.0")) {

            String toDate = utils.getCellData(sheetName, tcId, "ToDate").trim();

            utils.waitForElementVisible(toDateField);
            utils.sendKeys(toDateField, toDate);
        }

       
        utils.waitForElementClickable(applyButton);
        utils.click(applyButton);

        
        if (utils.isDisplayedSafely(successMessage)) {
            String actualText = utils.getText(successMessage);
            utils.assertionChecker("Successfully Saved", actualText);
        }

        utils.closeExcel();
    }
}