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
	By cartbtn = By.id("add-to-cart");
	By prodcontainer = By.xpath("//div[@class='inventory_item_description']");
	By cartlist = By.xpath("//div[@class='cart_list']");
	By cartbutton = By.xpath("//a[@class='shopping_cart_link']");
	By backkbtn=By.id("back-to-products");
	
	public WebElement clickonbaack() {
		return driver.findElement(backkbtn);
	}

	public boolean carttest() {
		boolean test = driver.findElement(cartbutton).isDisplayed();
		return test;

	}

	public List<String> backpack() throws Exception {

		List<WebElement> all = driver.findElements(backpack);
		ArrayList<String> text = new ArrayList<String>();
		for (WebElement items : all) {
			text.add(items.getText());
		}
		return text;
	}

	public boolean getdata(String name) throws Exception {
		boolean test = backpack().stream().anyMatch(item -> item.contains(name));
		return test;
	}

	public void clickoncart() {
		List<WebElement> cartbtns = driver.findElements(cartbtn);

	}

	public void clickProduct1(String names) throws Exception {
		List<WebElement> productNames = driver.findElements(listprodnames);
		// Step 2: Loop through and find "Sauce Labs Backpack"

		for (WebElement product : productNames) {
			String name = product.getText().trim();
			if (name.contains(names)) {
				// Step 3: Click the product using its WebElement
				product.click(); // Direct click on the link text WebElement
				WebElement cartbtn1 = driver.findElement(cartbtn);
				String btntext = cartbtn1.getText();
				if (btntext.equalsIgnoreCase("Add to cart")) {
					Thread.sleep(2000);
					cartbtn1.click();
					Thread.sleep(2000);
					clickonbaack().click();
					if (carttest()) {
						driver.findElement(cartbutton).click();
						System.out.println("testcart icon");
						List<WebElement> crtlist = driver.findElements(cartlist);
						if (crtlist.contains(names)) {
							System.out.println("test back navigation");
							driver.navigate().back();
						}

					}
				}
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
