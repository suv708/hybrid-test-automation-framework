
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import elements.PageElement;
import utils.WebDriverUtils;

public class LoginPage {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement username;
    private PageElement password;
    private PageElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        username = new PageElement(driver,
                By.xpath("//input[@name='username']"),
                "Username Field");

        password = new PageElement(driver,
                By.xpath("//input[@type='password']"),
                "Password Field");

        loginBtn = new PageElement(driver,
                By.xpath("//button[@type='submit']"),
                "Login Button");
    }

    public void login() {

        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

        String sheetName = "test_Date";
        String tcId = "TC_POS_002";

        String configUsername = utils.getCellData(sheetName, tcId, "ConfigUsername").trim();
        String configPassword = utils.getCellData(sheetName, tcId, "ConfigPassword").trim();

        if (configUsername.equals("1") || configUsername.equals("1.0")) {
            String usernameValue = utils.getCellData(sheetName, tcId, "Username").trim();

            utils.waitForElementVisible(username);
            utils.sendKeys(username, usernameValue);
        }

        if (configPassword.equals("1") || configPassword.equals("1.0")) {
            String passwordValue = utils.getCellData(sheetName, tcId, "Password").trim();

            utils.waitForElementVisible(password);
            utils.sendKeys(password, passwordValue);
        }

        utils.waitForElementClickable(loginBtn);
        utils.click(loginBtn);

        utils.closeExcel();
    }
}