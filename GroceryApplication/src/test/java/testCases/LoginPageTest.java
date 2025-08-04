package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constant.Constant;
import elementRepository.AdminPage;
import elementRepository.LoginPage;
import utilities.ExcelRead;

public class LoginPageTest extends BaseClass {
	LoginPage lp ;
	AdminPage ap;
	ExcelRead er;
	
	
	@DataProvider(name="invalidLoginData")
	public Object[][] getInvalidloginData() {
	    return new Object[][] {
	        {"admin123", "admin124553"}
	       
	    };
	}
	@Test(groups= "Smoke")
  public void verifyDashboardTextWhileLoginWithValidCredentials() throws IOException {
	  lp=  new LoginPage(driver);// extends driver from the BaseClass
	  er= new ExcelRead();
      ap = lp.login(loginData(0,1), loginData(1,1));//Importing data from excel
	  String actual=ap.getDashboardText();
	  String expected="Dashboard";
	  String actualName= ap.profileName();
	  String expectedName="Admin";
	  Assert.assertEquals(actualName, expectedName, "::Profile Name Mismatch");
	  Assert.assertEquals(actual, expected, Constant.lp_verifyDashboardTextWhileLoginWithValidCredentials);
	
  }
  
  
  @Test(dataProvider = "invalidLoginData", groups= "Smoke")
  public void verifyAlertWhileLoginWithInValidCredentials(String username, String password ) {
	  lp=  new LoginPage(driver);// extends driver from the BaseClass
	  //lp.login("admin1","admin134");
	  ap = lp.login(username, password);
	  String actualMessage=lp.getAlertText();
	  String expectedMessage="Alert!";
	  boolean actual=actualMessage.contains(expectedMessage);
	  Assert.assertEquals(actual, true, "::Alert Text not as expected");
  
  }
}