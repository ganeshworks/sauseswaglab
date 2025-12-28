package testClasses;

import org.testng.annotations.Test;

import testUtility.BaseClass;
import testobjects.LogoutUI1;

public class LogoutUItest extends BaseClass {
	
	@Test
	public void logoutest() {
		LogoutUI1 lo=new LogoutUI1(driver);
		
		lo.clickonhumber();
		lo.clickonlogout();
	}
	
}
