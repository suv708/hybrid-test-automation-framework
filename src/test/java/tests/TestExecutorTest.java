package tests;

import base.BaseTest;
import org.testng.SkipException;
import org.testng.annotations.*;

import pages.*;

import reports.ExtentTestManager;
import utils.DataParser;
import utils.ExcelUtils;

import java.util.*;
//package tests;

import base.BaseTest;
import org.testng.annotations.Listeners;
import listeners.TestListener;

@Listeners(TestListener.class)

public class TestExecutorTest extends BaseTest {

    LoginPage loginPage;
    Dashboard das;
    Admin ad;
    Pim pm;
    Time ti;
    Recruitment re;
    MyInfo my;

    @BeforeMethod
    public void init() {
        setup();

        loginPage = new LoginPage(getDriver());
        das = new Dashboard(getDriver());
        ad = new Admin(getDriver());
        pm = new Pim(getDriver());
        ti = new Time(getDriver());
        re = new Recruitment(getDriver());
        my = new MyInfo(getDriver());
    }

    @DataProvider(name = "excelData")
    public Object[][] getExcelData() {

        List<Map<String, String>> allData = ExcelUtils.getTestData("test_Case");
        List<Map<String, String>> runnableData = new ArrayList<>();

        for (Map<String, String> row : allData) {
            if (row.get("Runner").equalsIgnoreCase("Yes")) {
                runnableData.add(row);
            }
        }

        Object[][] data = new Object[runnableData.size()][1];

        for (int i = 0; i < runnableData.size(); i++) {
            data[i][0] = runnableData.get(i);
        }

        return data;
    }

    @Test(dataProvider = "excelData")
    public void runTestCase(Map<String, String> testData) throws InterruptedException {

        String tcId = testData.get("TC_ID");
        String module = testData.get("Module");
        String scenario = testData.get("Test Scenario");
        String runner = testData.get("Runner");
        String excelTestData = testData.get("Test Data");

        System.out.println("==================================");
        System.out.println("Executing TC_ID: " + tcId);
        System.out.println("Module: " + module);
        System.out.println("Scenario: " + scenario);
        System.out.println("==================================");

        // Report Logs
        ExtentTestManager.getTest().info("Execution Started for TC_ID: " + tcId);
        ExtentTestManager.getTest().info("Module Name: " + module);
        ExtentTestManager.getTest().info("Scenario: " + scenario);

        if (!runner.equalsIgnoreCase("Yes")) {
            ExtentTestManager.getTest().skip("Skipping TC_ID: " + tcId + " because Runner is No");
            throw new SkipException("Skipping TC_ID: " + tcId + " because Runner is No");
        }

        Map<String, String> parsedData = DataParser.parseData(excelTestData);
        ExtentTestManager.getTest().info("Parsed Test Data: " + parsedData);

        switch (module.toLowerCase()) {

            case "login":
                ExtentTestManager.getTest().info("Login Module Started");

                if (scenario.equalsIgnoreCase("Login with valid credentials")) {
                    ExtentTestManager.getTest().info("Entering Username and Password...");
                    loginPage.login();
                    ExtentTestManager.getTest().pass("Login Completed Successfully");
                }
                break;

            case "dashboard":
                ExtentTestManager.getTest().info("Dashboard Module Started");
                loginPage.login();
                das.dashBoard();
                ExtentTestManager.getTest().pass("Dashboard validated successfully");
                break;

            case "admin":
                ExtentTestManager.getTest().info("Admin Module Started");
                loginPage.login();
                ad.clickAdmin();
                ExtentTestManager.getTest().pass("Admin module executed successfully");
                break;

            case "pim":
                ExtentTestManager.getTest().info("PIM Module Started");
                loginPage.login();
                pm.addEmployee();
                ExtentTestManager.getTest().pass("Employee added successfully");
                break;

            case "time":
                ExtentTestManager.getTest().info("Time Module Started");
                loginPage.login();
                ti.addTimesheetHours();
                ExtentTestManager.getTest().pass("Timesheet updated successfully");
                break;

            case "recruitment":
                ExtentTestManager.getTest().info("Recruitment Module Started");
                loginPage.login();
                re.addCandidate();
                ExtentTestManager.getTest().pass("Candidate added successfully");
                break;

            case "myinfo":
                ExtentTestManager.getTest().info("MyInfo Module Started");
                loginPage.login();
                my.updatePersonalDetails();
                ExtentTestManager.getTest().pass("Personal details updated successfully");
                break;

            default:
                ExtentTestManager.getTest().warning("No automation written for module: " + module);
                System.out.println("❌ No automation written for module: " + module);
                break;
        }
    }

    @AfterMethod
    public void close() throws InterruptedException {
        tearDown();
    }
}