//package listeners;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//
//import org.testng.*;
//
//import reports.ExtentManager;
//import reports.ExtentTestManager;
//
//import java.util.Map;
//
//public class TestListener implements ITestListener {
//
//    private static ExtentReports extent = ExtentManager.createExtentReport();
//
//    @Override
//    public void onTestStart(ITestResult result) {
//
//        String testName = result.getMethod().getMethodName();
//
//        Object[] params = result.getParameters();
//
//        if (params.length > 0 && params[0] instanceof Map) {
//
//            Map<String, String> testData = (Map<String, String>) params[0];
//
//            String tcId = testData.get("TC_ID");
//            String module = testData.get("Module");
//            String scenario = testData.get("Test Scenario");
//            String runner = testData.get("Runner");
//            String excelTestData = testData.get("Test Data");
//
//            testName = tcId + " | " + module + " | " + scenario;
//
//            ExtentTest test = extent.createTest(testName);
//
//            test.assignCategory(module);  // Module wise category
//
//            test.info("==================================");
//            test.info("TC_ID: " + tcId);
//            test.info("Module: " + module);
//            test.info("Scenario: " + scenario);
//            test.info("Runner: " + runner);
//            test.info("Test Data: " + excelTestData);
//            test.info("==================================");
//
//            ExtentTestManager.setTest(test);
//
//        } else {
//            ExtentTestManager.setTest(extent.createTest(testName));
//        }
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        ExtentTestManager.getTest().pass("✅ Test Passed Successfully");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        ExtentTestManager.getTest().fail("❌ Test Failed");
//        ExtentTestManager.getTest().fail(result.getThrowable());
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        ExtentTestManager.getTest().skip("⚠ Test Skipped");
//        if (result.getThrowable() != null) {
//            ExtentTestManager.getTest().skip(result.getThrowable());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        extent.flush();
//        ExtentTestManager.removeTest();
//        System.out.println("===== REPORT GENERATED SUCCESSFULLY =====");
//    }
//}

package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reports.ExtentManager;
import reports.ExtentTestManager;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.createExtentReport();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("===== REPORT INITIALIZED =====");
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        ExtentTest test = extent.createTest(testName);

        // IMPORTANT LINE
        ExtentTestManager.setTest(test);

        ExtentTestManager.getTest().info("Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail("Test Failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("===== REPORT GENERATED SUCCESSFULLY =====");
    }
}