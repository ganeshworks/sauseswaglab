package testUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

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
	@BeforeSuite(alwaysRun = true)
	public void beforesuite() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--headless=new"); // run headless on Jenkins Linux
		options.addArguments("--remote-allow-origins=*");

		// 👇 unique directory for each run
		options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

}
