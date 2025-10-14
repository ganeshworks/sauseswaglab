package testobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test_pages.ReadConfig1;

public class LoginElementUI {
	WebDriver driver;

	public LoginElementUI(WebDriver d) {
		this.driver = d;
	}

	By userid = By.id("user-name");
	By pass = By.id("password");
	By submitbtn = By.id("login-button");

	// actions
	public void username(String str) {
		driver.findElement(userid).sendKeys(str);
	}

	public void password(String str) {
		driver.findElement(pass).sendKeys(str);
	}

	public void submitbutton() {
		driver.findElement(submitbtn).click();
	}

	// Getter methods to access elements directly (for validations)
	public WebElement getusername() {
		return driver.findElement(userid);
	}

	public WebElement getpassword() {
		return driver.findElement(pass);
	}

	public WebElement getsubmitbtn() {
		return driver.findElement(submitbtn);
	}

	public List<String> getuserframedata() {
		List<WebElement> listdata = driver.findElements(By.id("login_credentials"));
		List<String> texts=new ArrayList<String>();
		for(WebElement text:listdata) {
			texts.add(text.getText());
		}
		return texts;
	}
	public List<String> getpasswordFrameData(){
		List<WebElement> getpasslist=driver.findElements(By.xpath("//div[@class='login_password']"));
		ArrayList<String> passlist=new ArrayList<String>();
		for(WebElement pass:getpasslist) {
			passlist.add(pass.getText());
		}
		return passlist;
	}
	public String attemptlogin(String username , String password) {
		username(username);
		password(password);
		submitbutton();
		return driver.getPageSource();
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
