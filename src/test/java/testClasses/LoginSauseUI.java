package testClasses;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import testUtility.BaseClass;
import testUtility.ReadExcelData;
import test_pages.ReadConfig;
import testobjects.LoginElementUI;

public class LoginSauseUI extends BaseClass {

	/*
	 * @DataProvider(name = "logindata") public Object[][] login() { return new
	 * Object[][] { { "standard_use", "secret_sauce" }, { "locked_out_user",
	 * "secret_sauce" } }; }
	 */
	@DataProvider(name = "logindata")
	public Object[][] getexceldata() throws Exception {
		String excelpath = System.getProperty("user.dir") + "/testdata/loginuser1.xlsx";
		return ReadExcelData.getExcelData(excelpath, "sheet1");
	}

	@Test(dataProvider = "logindata")
	public void logintest(String username, String password) throws Exception {
		ReadConfig read = new ReadConfig();
		LoginElementUI l = new LoginElementUI(driver);

		test = extent.createTest("login test for username  ::::  " + username + " ::   " + password);
		driver.get(read.url());
		test.log(Status.PASS, "Navigated to url" + read.url());
		Assert.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl(),
				"Browser Not Correctly launched The Valid URL");
		test.log(Status.PASS, "Navigated to Correct Url  : " + driver.getCurrentUrl());
		Assert.assertEquals("Swag Labs", l.gettittle());
		test.log(Status.PASS, "Correct Tittle Get Fetched  "+l.gettittle());
		Assert.assertTrue(l.getusername().isEnabled(), "Username Field Is not enabled");
		test.log(Status.PASS, "username field is enabled and accepting data");
		Assert.assertTrue(l.getpassword().isEnabled(), "password field is not enabled");
		test.log(Status.PASS, "Password Field Enabled And Accepting Data");
		Thread.sleep(1000);
		String pagesource = l.attemptlogin(username, password);
		test.log(Status.PASS, "Login With test data :  " + " ,,, " + username + "  ,," + password);
		Thread.sleep(3000);
		// Assert.assertEquals(l.gethomeurl(),
		// "https://www.saucedemo.com/inventory.html","login url was not matched");

		if (l.gethomeurl().equals("https://www.saucedemo.com/inventory.html")) {
			test.log(Status.PASS, "Navigated to home page after Valid Login Data  ::  " + l.gethomeurl());

		} else if (pagesource.contains("Epic sadface: Username and password do not match any user in this service")) {
			test.log(Status.PASS, "Got The Error message Correctly For Wrong user name/password");
		} else if (pagesource.contains("Epic sadface: Sorry, this user has been locked out.")) {
			test.log(Status.PASS, "Valid Error message Shown For Locker user");
		}else {
			test.log(Status.FAIL, "Unexpected Behaviouyr Observeres");
		}
		//Thread.sleep(4000);

	}

}
