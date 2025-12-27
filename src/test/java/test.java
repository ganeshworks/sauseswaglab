import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {
	WebDriver driver;
	@Test
	public void test1() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://mylpgdev.metagas.in/");
		By id=By.xpath("//input[@placeholder='ENTER AGENCY ID']");
		Actions actions=new Actions(driver);
		//actions.sendKeys(Keys.ENTER).perform();
		actions.scrollByAmount(0, 500).perform();


	}
}
