package testUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listener extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    //@Override
    public void onStart(ITestContext testContext) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test-Report-" + timestamp + ".html";

        // Create reports folder if not exists
        String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;
        new File(System.getProperty("user.dir") + "/reports/Screenshots/").mkdirs();

        htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/src/main/java/extent-configuu.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Host Name", "QA Server");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Ganesh");

        htmlReporter.config().setDocumentTitle("MyLPGBooks Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setReportName("Automation Test Results");
    }

    //@Override
    public void onTestSuccess(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
        logger.log(Status.FAIL, tr.getThrowable()); // logs the exception message

        // Capture and attach screenshot
        String screenshotPath = captureScreenshot(BaseClass.driver, tr.getName());
        try {
            logger.fail("Screenshot below:").addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    //@Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

    public String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/reports/Screenshots/" + testName + "_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
}
