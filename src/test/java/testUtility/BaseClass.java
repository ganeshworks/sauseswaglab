package testUtility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	protected static ExtentTest test;
	protected static ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
    public ExtentTest logger;
	/*
	 * @BeforeSuite(alwaysRun = true) public void beforesuite() {
	 * 
	 * WebDriverManager.chromedriver().setup();
	 * 
	 * ChromeOptions options=new ChromeOptions();
	 * 
	 * options.addArguments("--incognito");
	 * 
	 * driver=new ChromeDriver(options);
	 * 
	 * driver.manage().window().maximize();
	 */
	//@Parameters("env") 
	@BeforeSuite(alwaysRun = true)
	public void beforesuite() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		//options.addArguments("--headless=new"); // run headless on Jenkins Linux
		options.addArguments("--remote-allow-origins=*");

		// 👇 unique directory for each run
		options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		//intialize extentreports
		/*
		 * String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
		 * Date()); String report=System.getProperty("user.dir")+"/reports"; //String
		 * report=System.getProperty("user.dir")+"/reports/automation_report_"+timestamp
		 * +".html"; File reportfile=new File(report,"automation_report_" + timestamp +
		 * ".html"); ExtentSparkReporter reporter=new
		 * ExtentSparkReporter(reportfile.getAbsolutePath());
		 * reporter.config().setReportName("Sauce Demo UI Automation Results");
		 * reporter.config().setDocumentTitle("Automation Execution Report");
		 */
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test-Report-" + timestamp + ".html";

        // Create reports folder if not exists
        String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;
        new File(System.getProperty("user.dir") + "/reports/Screenshots/").mkdirs();

        htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/src/main/java/extent-configuu.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
		
		 extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "ganesh");
		extent.setSystemInfo("browser", "chrome headless");
	}
	@AfterSuite
	public void tearDown() {
		/*
		 * if (driver != null) { driver.quit(); }
		 */
	    extent.flush();
	}
	
	/*
	 * @BeforeSuite(alwaysRun = true) public void beforesuite() {
	 * WebDriverManager.chromedriver().setup();
	 * 
	 * ChromeOptions options = new ChromeOptions();
	 * options.addArguments("--incognito", "--no-sandbox",
	 * "--disable-dev-shm-usage", "--disable-gpu", "--remote-allow-origins=*");
	 * options.addArguments("--user-data-dir=/tmp/chrome-user-data-" +
	 * System.currentTimeMillis());
	 * 
	 * driver = new ChromeDriver(options); driver.manage().window().maximize();
	 * 
	 * // Initialize ExtentReports String timestamp = new
	 * SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()); String reportsDir
	 * = System.getProperty("user.dir") + "/reports/Screenshots"; new
	 * File(reportsDir).mkdirs();
	 * 
	 * File reportFile = new File(reportsDir, "automation_report_" + timestamp +
	 * ".html"); ExtentSparkReporter reporter = new ExtentSparkReporter(reportFile);
	 * reporter.config().setDocumentTitle("Automation Execution Report");
	 * reporter.config().setReportName("Sauce Demo UI Automation Results");
	 * reporter.config().setEncoding("utf-8");
	 * reporter.config().setTheme(Theme.STANDARD); // optional: DARK / STANDARD
	 * 
	 * extent = new ExtentReports(); extent.attachReporter(reporter);
	 * extent.setSystemInfo("Environment", "QA"); extent.setSystemInfo("Tester",
	 * "Ganesh"); extent.setSystemInfo("Browser", "Chrome Headless"); }
	 * 
	 * @AfterSuite(alwaysRun = true) public void tearDown() { if (driver != null) {
	 * driver.quit(); } extent.flush(); }
	 */



}
