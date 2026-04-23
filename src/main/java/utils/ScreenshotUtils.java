package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String reportFolder = System.getProperty("user.dir") + "/Reports/Screenshots";

            File folder = new File(reportFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotPath = reportFolder + "/" + testName + "_" + timeStamp + ".png";

            File dest = new File(screenshotPath);
            Files.copy(src.toPath(), dest.toPath());

            return screenshotPath;

        } catch (Exception e) {
            System.out.println("❌ Screenshot Failed: " + e.getMessage());
            return null;
        }
    }
}