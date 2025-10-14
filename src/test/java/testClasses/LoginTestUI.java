package testClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testUtility.BaseClass;
import test_pages.*;
import testobjects.LoginElementUI;

public class LoginTestUI extends BaseClass {

	@Test(enabled = false)
	public void logintest() {

		ReadConfig read = new ReadConfig();
		LoginElementUI l = new LoginElementUI(driver);
		driver.get(read.url());

		String actual = driver.getCurrentUrl();
		String expected = "https://www.saucedemo.com/";

		SoftAssert soft = new SoftAssert();
		// Assertion applied for url check
		soft.assertEquals(actual, expected, "url has not matched");
		soft.assertAll();

		if (actual.equalsIgnoreCase(expected)) {
			List<WebElement> store = driver.findElements(By.xpath("//div[@class='login_credentials_wrap-inner']"));
			for (int i = 0; i < store.size(); i++) {
				System.out.println(store.get(i).getText());
				String text = store.get(i).getText();
				// getting usernames from frames
				if (text.contains("standard_user")) {
					// if frame contains expecetd value
					l.username("standard_user");
					String value = driver.findElement(By.id("user-name")).getAttribute("value");
					// checking passed sendkeys corrrectly went to fields
					if (value.equals("standard_user")) {
						System.out.println("text passed");
					} else {
						System.out.println("text failed to entered");
					}
					/// checking for password from frame
					if (text.contains("secret_sauce")) {
						l.password("secret_sau");
						// checking correct password value passed to field
						String value1 = driver.findElement(By.id("password")).getAttribute("value");
						if (value1.equals("secret_sau")) {
							System.out.println("password text passed ");
						} else {
							System.out.println("password text failed");
						}
						// checking color applied for submit button
						String color = driver.findElement(By.id("login-button")).getCssValue("background-color");
						System.out.println("color was " + color);
						if (color.equals("rgba(61, 220, 145, 1)")) {
							System.out.println("correct color applied for submit button");
						}
						WebElement submit = driver.findElement(By.id("login-button"));
						if (submit.isDisplayed() && submit.isEnabled()) {
							Assert.assertTrue(submit.isDisplayed(), "not dispayed submit button");
							l.submitbutton();
							System.out.println("submit button was enabled correctly ");
						}

						System.out.println(driver.getPageSource());
						Assert.assertTrue(
								driver.getPageSource().contains(
										"Epic sadface: Username and password do not match any user in this service"),
								"did not have valid assertions");
					}
				}

			}
		}
	}

	@Test(enabled = false)

	public void loginuitest() {
		ReadConfig read = new ReadConfig();
		LoginElementUI l = new LoginElementUI(driver);
		driver.get(read.url());

		String actualurl = driver.getCurrentUrl();
		// verifying launched for valid url
		Assert.assertEquals(actualurl, "https://www.saucedemo.com/", "Launch Was Not on valid Url");

		// verify userid and password fields available
		/*
		 * if (l.getusername().isEnabled()) { // System.out.println(l.getframedata());
		 * for (int i = 0; i < l.getuserframedata().size(); i++) {
		 * System.out.println("usernames data" + l.getuserframedata().get(i)); String
		 * usernames = l.getuserframedata().get(i);
		 * 
		 * if (usernames.contains("standard_user")) { l.username("standard_user"); if
		 * (l.getpassword().isDisplayed() &&
		 * l.getpasswordFrameData().get(i).contains("secret_sauce")) {
		 * l.password("secret_sauc"); if (l.getsubmitbtn().isDisplayed() &&
		 * l.getsubmitbtn().isEnabled()) { l.submitbutton();
		 * //System.out.println(driver.getPageSource()); String pagesource =
		 * driver.getPageSource(); if(pagesource.
		 * contains("Epic sadface: Username and password do not match any user in this service"
		 * )) { Assert.assertTrue(true , "confdition failed");
		 * System.out.println("error message validation passed"); }
		 * 
		 * }
		 * 
		 * } } }
		 * 
		 * }
		 */
		Assert.assertTrue(l.getusername().isEnabled(), "Username field not enabled");

		String pagesource = l.attemptlogin("standard_user", "standard_us");
	}

	@DataProvider(name = "logindata")
	public Object[][] logindata() {
		return new Object[][] { { "standard_user", "secret_sauc" }, { "standard_user", "secret_sauce" } };
	}

	@Test(dataProvider = "logindata")
	public void loginUItest1(String username, String password) {
		ReadConfig1 read = new ReadConfig1();
		LoginElementUI l = new LoginElementUI(driver);
		driver.get(read.url());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Invalid launch URL");

		Assert.assertTrue(l.getusername().isEnabled(), "Username field not enabled");

		String pagesource = l.attemptlogin(username, password);

		if (password.equals("secret_sauc")) {
			Assert.assertTrue(pagesource.contains(
					"Epic sadface: Username and password do not match any user in this service"));
			System.out.println("❌ Validation passed: Incorrect password message displayed");
		} else {
			Assert.assertTrue(
					pagesource.contains("inventory.html") || driver.getCurrentUrl().contains("inventory.html"),
					"User did not navigate to inventory page after login");

		}
	}
}
