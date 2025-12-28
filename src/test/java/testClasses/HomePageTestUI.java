package testClasses;

import java.time.Duration;
import java.util.List;

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

		List<String> productNames = h.getProdNames();
		// get all product names dynamically
		h.carttest();
		for (String prod : productNames) {
			if (h.getdata(prod)) {
				test.log(Status.PASS, prod+" Was Available On Home Screen");
			} else {
				test.log(Status.FAIL, prod+" Was Not Available on Homepage");
			}
			
			h.clickProduct1(prod);
			driver.navigate().refresh();
			test.log(Status.PASS, "Clicked On : " + prod);
			test.log(Status.INFO, "Clicked On Add to cart Button for "+prod);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			driver.navigate().back();
			test.log(Status.PASS, "Navigated back To Screen");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
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
