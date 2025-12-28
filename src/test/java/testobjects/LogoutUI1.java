package testobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutUI1 {

	WebDriver driver;
	
	public LogoutUI1(WebDriver d){
		driver=d;
	}
	
	By humber=By.id("react-burger-menu-btn");
	By logoutbtn=By.id("logout_sidebar_link");
	
	public void clickonhumber() {
		driver.findElement(humber).click();
	}
	public void clickonlogout() {
		driver.findElement(logoutbtn).click();
	}
}
