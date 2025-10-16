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
		// test.log(Status.INFO, "data is "+allitems.toString());
		test.log(Status.INFO, "Data is: " + allitems.toString());

		if (h.backpack().contains("Sauce Labs Backpack")) {
			test.log(Status.PASS, "Sause Backpack Was Available On Home Screen");
		}

		/*
		 * Assert.assertTrue(h.backpack().contains("Sauce Labs Backpack"
		 * ),"sause was not there"); test.log(Status.PASS,
		 * "Sause Backpack Was Available On Home Screen");
		 */

	}
}
