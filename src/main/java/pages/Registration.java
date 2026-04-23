//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import elements.PageElement;
//import utils.WebDriverUtils;
//
//public class Registration {
//	
//	
//	private WebDriver driver;
//    private PageElement clkRegistration;
//    private PageElement password;
//    private PageElement loginBtn;
//
//    public Registration(WebDriver driver) {
//        this.driver = driver;
//        clkRegistration = new PageElement(driver, By.xpath("username"), "Username Field");
//        password = new PageElement(driver, By.name("password"), "Password Field");
//        loginBtn = new PageElement(driver, By.xpath("//input[@value='Log In']"), "Login Button");
//    }
//    
//    WebDriverUtils utils=new WebDriverUtils(driver);
//    
//    public void login(String user, String pass) {
//    	utils.click(clkRegistration);
//    	
//    }
//    
//}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import elements.PageElement;
import utils.WebDriverUtils;

public class Registration {

    private WebDriver driver;
    private WebDriverUtils utils;

    private PageElement clkRegistration;
    private PageElement fristName;
    private PageElement lastName;
    private PageElement loginBtn;
    private PageElement customerSignup;
    private PageElement email;
    private PageElement textOfCaptcha;
    
    private PageElement zipCode;
    private PageElement phone;
    private PageElement ssn;
    private PageElement userName;
    private PageElement password;
    private PageElement confirmPassword;
    private PageElement geminiTextField;
    private PageElement copyFromGemini;
    private PageElement pastBox;
    private PageElement checkBox;
    private PageElement createAccountBtn;
    private PageElement registrationStatus;

    public Registration(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);

        clkRegistration = new PageElement(driver, By.xpath("//button[@class='btn light flex items-center gap-1.5']"), "Click Registration .");
        fristName = new PageElement(driver, By.xpath("//input[@id='first_name']"), "Frist Name ");
        lastName = new PageElement(driver,By.xpath("//input[@id='last_name']"), "Last Name ");	
        customerSignup = new PageElement(driver, By.xpath("(//span[@class='font-medium'])[3]"), "Customer Signup "); 
       
        email = new PageElement(driver, By.xpath("//input[@id='email']"), "Email");
        textOfCaptcha = new PageElement(driver, By.xpath("//div[@class='form-control']/label[@for='captcha_answer']"), "textOfCaptcha");
        geminiTextField = new PageElement(driver, By.xpath("//div[@data-placeholder='Ask Gemini 3']"), "Gemini Text Field ");
        copyFromGemini = new PageElement(driver, By.xpath("(//mat-icon[@data-mat-icon-name='content_copy'])[2]"), "Copy from gemini ");
        pastBox = new PageElement(driver, By.xpath("//input[@id='captcha_answer']"), "catcha text area");
        checkBox = new PageElement(driver, By.xpath("//div[@class='checkbox-custom']"), "check Box");
        password = new PageElement(driver, By.xpath("//input[@id='password']"), "Password ");
        confirmPassword = new PageElement(driver, By.xpath("//input[@id='confirm_password']"), "Confirm Password");
        createAccountBtn = new PageElement(driver, By.xpath("//button[@class='btn emerald w-full relative']"), "create account btn ");
        registrationStatus = new PageElement(driver, By.xpath("//div[@class='alert-success']/p"), "registrationStatus");
 
      //div[@class='alert-success']/p
    }

    public void register() throws InterruptedException {
      
        // - + - + - + 
       WebDriverUtils utils = new WebDriverUtils(driver);

       utils.loadExcel("src/test/resources/Hybrid_Framework.xlsx");

       String sheetName = "test_Date";
       String tcId = "TC_POS_001";
       
       utils.click(clkRegistration.getLocator());
       utils.click(customerSignup.getLocator());
       
       String configFirstName = utils.getCellData(sheetName, tcId, "ConfigFirstName").trim();
       String configLastName  = utils.getCellData(sheetName, tcId, "ConfigLastName").trim();
       String configEmail  = utils.getCellData(sheetName, tcId, "ConfigEmail").trim();
       String configPassword  = utils.getCellData(sheetName, tcId, "configPassword").trim(); 
       String configRegVerification  = utils.getCellData(sheetName, tcId, "configRegVerification").trim(); 

       
       
      String firstName="";
       if (configFirstName.equals("1") || configFirstName.equals("1.0")) {
           firstName = utils.getCellData(sheetName, tcId, "FirstName");
           System.out.println("FirstName: " + firstName);
        
           fristName.getElement().sendKeys(firstName);
       }
       
       if (configLastName.equals("1") || configLastName.equals("1.0")) {
           String lastNameFetch = utils.getCellData(sheetName, tcId, "LastName");
           System.out.println("lastName: " + lastNameFetch);
           lastName.getElement().sendKeys(lastNameFetch);
       }
       
       if (configEmail.equals("1") || configEmail.equals("1.0")) {
           String cityFetch = utils.getCellData(sheetName, tcId, "Email");
           System.out.println("City: " + cityFetch);
           email.getElement().sendKeys(cityFetch);
       }
  
       
       navigateToGemini();
       
       Thread.sleep(2000);
              
       utils.sendKeys(pastBox,Keys.CONTROL,"v");
       
       if (configPassword.equals("1") || configPassword.equals("1.0")) {
           String passwordFetch = utils.getCellData(sheetName, tcId, "Password");
           System.out.println("Password: " + passwordFetch);
           password.getElement().sendKeys(passwordFetch);
           confirmPassword.getElement().sendKeys(passwordFetch); 
       }
       utils.click(checkBox);
       utils.click(createAccountBtn.getLocator());
       
       if (configRegVerification.equals("1") || configRegVerification.equals("1.0")) {
	       String regStatus = utils.getText(registrationStatus.getLocator());
	       System.out.println(regStatus);
	       String regExcel=utils.getCellData(sheetName, tcId, "RegVerification");	  
	       utils.assertionChecker(regStatus, regExcel);
       }
       
       utils.closeExcel();
       
       System.out.println(" Gite Patil ");
        // - + - + - +
    }
    public  void navigateToGemini() throws InterruptedException {
    	 String captchaText = utils.getText(textOfCaptcha.getLocator());
         System.out.println(captchaText);
         
        String ag=captchaText+" only number output i want ";
  		System.out.println(ag);
  		
  		String parent = driver.getWindowHandle();

  		driver.switchTo().newWindow(WindowType.TAB);
  		String child = driver.getWindowHandle();

//  		driver.get("https://gemini.google.com/app/8fd05e0947a2cb01?hl=en-GB");
  		
  		driver.get("https://gemini.google.com/app?hl=en-IN");
  		
  		Thread.sleep(2000); 
  		driver.navigate().refresh();
  		
  		Thread.sleep(2000); 
  		
  		utils.sendKeys(geminiTextField,ag+Keys.ENTER);
  		
  		utils.clickWithWait(copyFromGemini);
  		
  		driver.switchTo().window(parent);
  		
    }
}
