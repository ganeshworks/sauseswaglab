package testobjects;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeElementsUI {

	WebDriver driver;

	public HomeElementsUI(WebDriver d) {
		driver = d;
	}

	By backpack = By.id("inventory_container");
	By items = By.xpath("//div[@class='inventory_list']");
	By getelemet = By.className("inventory_item_name");
	By listprodnames = By.xpath("//div[@class='inventory_item_name ']");

	public List<String> backpack() throws Exception {

		List<WebElement> all = driver.findElements(backpack);
		ArrayList<String> text = new ArrayList<String>();
		for (WebElement items : all) {
			text.add(items.getText());
		}
		return text;
	}

	public boolean getdata() throws Exception {
		boolean test = backpack().stream().anyMatch(item -> item.toLowerCase().contains("sauce labs backpack"));
		return test;
	}

	public String getbackpack() {
		String back = driver.findElement(backpack).getText();
		System.out.println();
		return back;
	}

	public String getlistofitems() {
		return driver.findElement(items).getText();
	}

	public void clickProduct1(String names) {
		List<WebElement> productNames = driver.findElements(listprodnames);

		// Step 2: Loop through and find "Sauce Labs Backpack"
		for (WebElement product : productNames) {
			String name = product.getText().trim();
			
			if (name.contains(names)) {
				// Step 3: Click the product using its WebElement
				product.click(); // Direct click on the link text WebElement
				break; // stop after clicking
				
			}
		}

	}

	public List<String> getProdNames() {
		List<WebElement> prodElements = driver.findElements(listprodnames);
		List<String> names = new ArrayList<>();

		for (WebElement element : prodElements) {
			names.add(element.getText().trim());
		}
		return names;
	}

}
