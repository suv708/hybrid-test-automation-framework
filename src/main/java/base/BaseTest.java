//package base;
//
//import org.openqa.selenium.PageLoadStrategy;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//
//import utils.ConfigReader;
//
//import java.time.Duration;
//
//public class BaseTest {
//
//    public static WebDriver driver;
//
//    public void setup() {
//
//    	 String browser = ConfigReader.getProperty("browser");
//
//    	    if (browser.equalsIgnoreCase("chrome")) {
//    	        ChromeOptions options = new ChromeOptions();
//    	        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
//    	        driver = new ChromeDriver(options);
//    	    }
//            else if (browser.equalsIgnoreCase("firefox")) {
//               driver = new FirefoxDriver();
//            } 
//            else if (browser.equalsIgnoreCase("edge")) {
//               driver = new EdgeDriver();
//            }
//
//        // ✅ TIMEOUTS
//    	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
//
//        // ✅ URL
//    	    driver.manage().window().maximize();
//    	    driver.get(ConfigReader.getProperty("base.url"));
//    }
//
//    public void tearDown() {
//        if (driver != null) {   // ✅ FIXED YOUR ERROR
//            driver.quit();
//        }
//    }
//}

package base;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import sendReport.ShareReport;

import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setup() {

        String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver.set(new ChromeDriver(options));
        } 
        else if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
        } 
        else if (browser.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
        } 
        else {
            throw new RuntimeException("❌ Invalid browser name in config.properties");
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        
        getDriver().manage().window().maximize();
        getDriver().get(ConfigReader.getProperty("base.url"));
    }

    public void tearDown() throws InterruptedException {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
        
       
        ShareReport sr = new ShareReport();
        sr.sendReport("csuvarna715@gmail.com");
    }
}