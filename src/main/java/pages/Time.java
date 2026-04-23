package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import elements.PageElement;
import utils.WebDriverUtils;
import org.openqa.selenium.Keys;

public class Time {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement timeMenu;
    private PageElement timesheetsMenu;
    private PageElement myTimesheetsMenu;
    private PageElement editButton;
    private PageElement addRowButton;
    private PageElement hoursField;
    private PageElement saveButton;
    private PageElement successMessage;
    private PageElement projectTextField;

    public Time(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        timeMenu = new PageElement(driver,
                By.xpath("//span[text()='Time']"),
                "Time Menu");

        timesheetsMenu = new PageElement(driver,
                By.xpath("//span[text()='Timesheets ']"),
                "Timesheets Menu");

        myTimesheetsMenu = new PageElement(driver,
                By.xpath("//a[text()='My Timesheets']"),
                "My Timesheets Menu");

        editButton = new PageElement(driver,
                By.xpath("//button[contains(.,'Edit')]"),
                "Edit Timesheet Button");

        addRowButton = new PageElement(driver,
                By.xpath("//i[@class='oxd-icon bi-plus']"),
                "Add Row Button");

        hoursField = new PageElement(driver,
                By.xpath("(//input[contains(@class,'oxd-input')])[last()]"),
                "Hours Field");
        
        projectTextField = new PageElement(driver,
                By.xpath("//label[text()='Project']/../following-sibling::div//input"),
                "Project Text Field ");
        

        saveButton = new PageElement(driver,
                By.xpath("//button[@type='submit']"),
                "Save Button");

        successMessage = new PageElement(driver,
                By.xpath("//p[text()='Successfully Saved']"),
                "Success Message");
        
    }

    public void addTimesheetHours() throws InterruptedException {

        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

        String sheetName = "test_Date";
        String tcId = "TC_POS_007";

        String configHours = utils.getCellData(sheetName, tcId, "ConfigHours").trim();

        utils.waitForElementClickable(timeMenu);
        utils.click(timeMenu);

        utils.waitForElementClickable(timesheetsMenu);
        utils.click(timesheetsMenu);

        utils.waitForElementClickable(myTimesheetsMenu);
        utils.click(myTimesheetsMenu);

        if (utils.isDisplayedSafely(editButton)) {
            utils.waitForElementClickable(editButton);
            utils.click(editButton);
        }
        
         utils.click(projectTextField);
         utils.sendKeys(projectTextField,"ACME");
         utils.waitForElementClickable(projectTextField); 
         Thread.sleep(1000); 
         utils.sendKeys(projectTextField, Keys.ARROW_DOWN);
         utils.sendKeys(projectTextField, Keys.ENTER);

//        if (utils.isDisplayedSafely(addRowButton)) {
//            utils.waitForElementClickable(addRowButton);
//            utils.click(addRowButton);
//        }

        if (configHours.equals("1") || configHours.equalsIgnoreCase("1.0")) {

            String hours = utils.getCellData(sheetName, tcId, "Hours").trim();

            utils.waitForElementVisible(hoursField);
            utils.sendKeys(hoursField, hours);
        }

        
        utils.waitForElementClickable(saveButton);
        utils.click(saveButton);

        
        if (utils.isDisplayedSafely(successMessage)) {
            String actualText = utils.getText(successMessage);
            utils.assertionChecker("Successfully Saved", actualText);
        }

        utils.closeExcel();
    }
}