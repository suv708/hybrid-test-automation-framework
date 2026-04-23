
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import elements.PageElement;
import utils.WebDriverUtils;

public class Dashboard {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement dashBoard;
   
    public Dashboard(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        dashBoard = new PageElement(driver,
                By.xpath("//h6[text()='Dashboard']"),
                "Username Field");
    }

    public void dashBoard() {

        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

        String sheetName = "test_Date";
        String tcId = "TC_POS_003";

        String configUsername = utils.getCellData(sheetName, tcId, "ConfigUsername").trim();
        String configPassword = utils.getCellData(sheetName, tcId, "ConfigPassword").trim();
        
        utils.waitForElementVisible(dashBoard);
        String dash=utils.getText(dashBoard).trim();
        utils.assertionChecker("Dashboard", dash);
        utils.closeExcel();
    }
}