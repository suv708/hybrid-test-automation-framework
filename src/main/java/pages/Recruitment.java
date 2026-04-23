package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import elements.PageElement;
import utils.WebDriverUtils;

public class Recruitment {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement recruitmentMenu;
    private PageElement addButton;

    private PageElement firstNameField;
    private PageElement lastNameField;
    private PageElement emailField;
    private PageElement vacancyDropdown;
    private PageElement saveButton;

    private PageElement successMessage;
    private PageElement chooseVacany;
    private PageElement contactNumber;

    public Recruitment(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        recruitmentMenu = new PageElement(driver,
                By.xpath("//span[text()='Recruitment']"),
                "Recruitment Menu");

        addButton = new PageElement(driver,
                By.xpath("//button[contains(.,'Add')]"),
                "Add Candidate Button");

        firstNameField = new PageElement(driver,
                By.xpath("//input[@name='firstName']"),
                "First Name Field");

        lastNameField = new PageElement(driver,
                By.xpath("//input[@name='lastName']"),
                "Last Name Field");

        emailField = new PageElement(driver,
                By.xpath("//label[text()='Email']/../following-sibling::div//input"),
                "Email Field");

        vacancyDropdown = new PageElement(driver,
                By.xpath("//label[text()='Vacancy']/../following-sibling::div//div[contains(@class,'oxd-select-text')]"),
                "Vacancy Dropdown");
        
        chooseVacany = new PageElement(driver,By.xpath("//div[text()='Senior QA Lead']")," choose Vacany ");

        contactNumber = new PageElement(driver,
                By.xpath("(//input[@class='oxd-input oxd-input--active' and @placeholder='Type here'])[2]"),
                "contactNumber");
        
        saveButton = new PageElement(driver,
                By.xpath("//button[@type='submit']"),
                "Save Button");

        successMessage = new PageElement(driver,
                By.xpath("//p[text()='Successfully Saved']"),
                "Success Message");
    }

    public void addCandidate() throws InterruptedException {

        utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

        String sheetName = "test_Date";
        String tcId = "TC_POS_008";
        
        String ConfigCandidateName=utils.getCellData(sheetName, tcId,"ConfigCandidateName");
        String ConfigCandidateEmail=utils.getCellData(sheetName, tcId,"ConfigCandidateEmail");
        String ConfigCandidateLastName=utils.getCellData(sheetName, tcId,"ConfigCandidateLastName");
        String ConfigContactNumber=utils.getCellData(sheetName, tcId,"ConfigContactNumber");

        String firstName = utils.getCellData(sheetName, tcId, "CandidateName").trim();
        String lastName = utils.getCellData(sheetName, tcId, "CandidateLastName").trim();
        String email = utils.getCellData(sheetName, tcId, "CandidateEmail").trim();
        String vacancyName = utils.getCellData(sheetName, tcId, "Vacancy").trim();
        String contactNumber1 = utils.getCellData(sheetName, tcId, "ContactNumber").trim();

        utils.waitForElementClickable(recruitmentMenu);
        utils.click(recruitmentMenu);

        utils.waitForElementClickable(addButton);
        utils.click(addButton);

        if(ConfigCandidateName.equals("1")||ConfigCandidateName.equalsIgnoreCase("1.0")) {
        	utils.waitForElementVisible(firstNameField);
            utils.sendKeys(firstNameField, firstName);
        }
        
        if(ConfigContactNumber.equals("1")||ConfigContactNumber.equalsIgnoreCase("1.0")) {
        	utils.waitForElementVisible(contactNumber);
            utils.sendKeys(contactNumber, contactNumber1);
        }
        
        if(ConfigCandidateLastName.equals("1") || ConfigCandidateLastName.equalsIgnoreCase("1.0")) {
        	 utils.waitForElementVisible(lastNameField);
             utils.sendKeys(lastNameField, lastName);
        }
       
        if(ConfigCandidateEmail.equals("1") || ConfigCandidateEmail.equalsIgnoreCase("1.0")) {
        	utils.waitForElementVisible(emailField);
            utils.sendKeys(emailField, email);
        }
        
//        if (!vacancyName.equalsIgnoreCase("NA") && !vacancyName.isEmpty()) {
            utils.waitForElementClickable(vacancyDropdown);
            utils.click(vacancyDropdown);

//            PageElement vacancyOption = new PageElement(driver,
//            		By.xpath("//div[text()='"+vacancyName+"']"),
//                    "Vacancy Option");
//
//            utils.waitForElementClickable(vacancyOption);
//            utils.click(vacancyOption);
            
              utils.waitForElementClickable(chooseVacany);
              utils.click(chooseVacany);
//        }

        utils.waitForElementClickable(saveButton);
        utils.click(saveButton);

        if (utils.isDisplayedSafely(successMessage)) {
            String actualText = utils.getText(successMessage);
            utils.assertionChecker("Successfully Saved", actualText);
        }

        utils.closeExcel();
    }
}