package testClasses;

import org.testng.annotations.Test;

import testUtility.BaseClass;
import testobjects.LogoutUI;

public class LogoutUItest extends BaseClass {
	
	@Test
	public void logoutest() {
		LogoutUI lo=new LogoutUI(driver);
		
		lo.clickonhumber();
		lo.clickonlogout();
	}
	
}
