package testClasses;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import testUtility.BaseClass;
import test_pages.ReadConfig;
import testobjects.HomeElementsUI;
import testobjects.LoginElementUI;

public class HomePageTestUI extends BaseClass {

	@Test
	public void HomeScreen() throws Exception {
		ReadConfig read = new ReadConfig();
		HomeElementsUI h = new HomeElementsUI(driver);
		LoginElementUI l = new LoginElementUI(driver);
		LoginSauseUI lu = new LoginSauseUI();
		test = extent.createTest("Home Page validations  ");

		List<String> allitems = h.backpack();
		test.log(Status.INFO, "Data is: " + allitems.toString());
		if (h.getdata()) {
			test.log(Status.PASS, "Sause Backpack Was Available On Home Screen");
		} else {
			test.log(Status.FAIL, "Searched text Ws Not Available on Homepage");
		}
		// Click On Selected Linked Text

		List<String> productNames = h.getProdNames();
		// get all product names dynamically
		for (String prod : productNames) {
			h.clickProduct1(prod);
			test.log(Status.PASS, "Clicked On : " + prod);
			Thread.sleep(4000);
			driver.navigate().back();
			test.log(Status.PASS, "Navigated back To Screen");
			Thread.sleep(2000);
			

		}
	}
}































//add small delay to ensure page reloads before nextiteration }

			/*
			 * h.clickProduct1("Sauce Labs Onesie"); test.log(Status.PASS,
			 * "Clicked On : Sauce Labs Onesie"); driver.navigate().back();
			 * test.log(Status.PASS, "Navigated back To Screen"); Thread.sleep(2000);
			 * h.clickProduct1("Sauce Labs Bike Light"); test.log(Status.PASS,
			 * "Clicked On : Sauce Labs Bike Light"); Thread.sleep(2000);
			 * driver.navigate().back(); test.log(Status.PASS, "Navigated back To Screen");
			 * h.clickProduct1("Sauce Labs Bolt T-Shirt"); test.log(Status.PASS,
			 * "Clicked On : Sauce Labs Bolt T-Shirt"); Thread.sleep(2000);
			 * driver.navigate().back(); test.log(Status.PASS, "Navigated back To Screen");
			 * h.clickProduct1("Sauce Labs Fleece Jacket"); test.log(Status.PASS,
			 * "Clicked On : Sauce Labs Fleece Jacket"); Thread.sleep(2000);
			 * driver.navigate().back(); test.log(Status.PASS, "Navigated back To Screen");
			 * h.clickProduct1("Sauce Labs Onesie"); test.log(Status.PASS,
			 * "Clicked On : Sauce Labs Onesie"); Thread.sleep(2000);
			 * driver.navigate().back(); test.log(Status.PASS, "Navigated back To Screen");
			 * h.clickProduct1("Test.allTheThings() T-Shirt (Red)"); test.log(Status.PASS,
			 * "Clicked On : Test.allTheThings() T-Shirt (Red)"); Thread.sleep(2000);
			 * driver.navigate().back(); test.log(Status.PASS, "Navigated back To Screen");
			 */
