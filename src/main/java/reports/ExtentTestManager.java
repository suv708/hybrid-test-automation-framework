//package reports;
//
//import com.aventstack.extentreports.ExtentTest;
//
//public class ExtentTestManager {
//
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    public static void setTest(ExtentTest extentTest) {
//        test.set(extentTest);
//    }
//
//    public static ExtentTest getTest() {
//        return test.get();
//    }
//
//    public static void removeTest() {
//        test.remove();
//    }
//}

package reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void unload() {
        extentTest.remove();
    }
}