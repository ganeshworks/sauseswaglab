package testobjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeElementsUI {

	WebDriver driver;

	public HomeElementsUI(WebDriver d) {
		driver = d;
	}

	By backpack = By.id("inventory_container");

	public List<String> backpack() throws Exception {

		List<WebElement> all = driver.findElements(backpack);
		ArrayList<String> text = new ArrayList<String>();
		for (WebElement items : all) {
			text.add(items.getText());

		}
		System.out.println(text);
		Thread.sleep(4000);
		return text;
	}

	public String getbackpack() {
		String back = driver.findElement(backpack).getText();
		System.out.println();
		return back;
	}
}
