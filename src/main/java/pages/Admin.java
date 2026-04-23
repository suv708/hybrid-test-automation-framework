package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import elements.PageElement;
import utils.WebDriverUtils;

public class Admin {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement adminMenu;
    private PageElement userRoleField;
    private PageElement searchButton;
    private PageElement recordEdit;
    private PageElement clickSaveBtn;
    private PageElement sucessText;

    public Admin(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        adminMenu = new PageElement(driver,
                By.xpath("//span[text()='Admin']"),
                "Admin Menu");

        userRoleField = new PageElement(driver,
                By.xpath("//label[text()='Username']/../following-sibling::div/input"),
                "Username Field");

        searchButton = new PageElement(driver,
                By.xpath("//button[@type='submit']"),
                "Search Button");

        recordEdit = new PageElement(driver,
                By.xpath("//div[@class=\"oxd-table-cell oxd-padding-cell\"]/following::button[2]"),
                "User Record Edit ");
        
        clickSaveBtn = new PageElement(driver,
                By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"),
                "click save Btn ");
        
        sucessText = new PageElement(driver,
                By.xpath("//p[text()='Successfully Updated']"),
                "sucess Text");
        
    }

    public void clickAdmin() {

        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

        String sheetName = "test_Date";
        String tcId = "TC_POS_004";

        String configUserRole = utils.getCellData(sheetName, tcId, "ConfigUserRole").trim();
        String configSucess = utils.getCellData(sheetName, tcId, "ConfigSucessText").trim();

        
        utils.waitForElementClickable(adminMenu);
        utils.click(adminMenu);
        
       if(configUserRole.equals("1")||configUserRole.equalsIgnoreCase("1.0")) {
        
        String searchUserRole = utils.getCellData(sheetName, tcId, "UserRole").trim();
        
        utils.waitForElementVisible(userRoleField);
        
        utils.sendKeys(userRoleField, searchUserRole);
        
        utils.waitForElementClickable(searchButton);
        
        utils.click(searchButton);
        
        utils.waitForElementClickable(recordEdit);
        
        utils.click(recordEdit);
        
        utils.waitForElementClickable(clickSaveBtn);
        
        utils.click(clickSaveBtn);
        
       }
       if(utils.isDisplayedSafely(sucessText)) {
    	 String app=utils.getText(sucessText);
    	 
    	 if(configSucess.equals("1") || configSucess.equalsIgnoreCase("1.0")) {
    		 String exe=utils.getCellData(sheetName, tcId,"SucessText");
    		 utils.assertionChecker(app, exe);
    	 }
    	 
       }
       
        utils.closeExcel();
    }
}