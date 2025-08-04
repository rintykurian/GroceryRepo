package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.AdminPage;
import elementRepository.LoginPage;
import elementRepository.UserCreationPage;
@Test
public class UserPageTest extends BaseClass{
	LoginPage lp;
	AdminPage ap;
	UserCreationPage up;
	public void verifyUserNavigatesToAdminUserPage() {
		lp= new LoginPage(driver);
		//ap= new AdminPage(driver);
		//up= new UserCreationPage(driver);
		ap=lp.login("admin","admin");
		ap.getAdminUser();
		ap.getAdminUserMoreInfo();
		Assert.assertEquals(up.getAdminUserHeading(),"Admin Users", "::Heading Not As Expected");
		
	}
	@Test
	public void verifySuccessMessageWhileCreatingUser() {
		lp= new LoginPage(driver);
		up= new UserCreationPage(driver);
		verifyUserNavigatesToAdminUserPage();
		up.newUserCreation("Rinty56", "Admin");
		String alertMessage= up.successAlert();
		System.out.println(alertMessage);
		boolean actual = alertMessage.contains("User Created Successfully");
		Assert.assertEquals(actual, true , "Success Message Not as expected");
		
	}
	@Test
	public void verifyUserAddedToList() {
		lp= new LoginPage(driver);
	    verifySuccessMessageWhileCreatingUser();
		up.successAlert();
		up.getTableName();
		up.getAdminUserTableElementText(1, 1);
		String firstNameInList=up.getAdminUserTableElementText(1, 1);
		String expectedUsername = up.getGeneratedUsername();
		String actualUsername = up.getAdminUserTableElementText(1, 1);
		Assert.assertEquals(actualUsername, expectedUsername, "::Username Doesn't Match");
		
	}
	@Test
	public void verifySuccessMessageAfterUpdation() {
		lp= new LoginPage(driver);
		up= new UserCreationPage(driver);
		verifyUserNavigatesToAdminUserPage();
		up.userUpdation();
		String updateAlert = up.updateSuccessAlert();
		System.out.println(updateAlert);
	}
	
	@Test
	public void verifySuccessMessageAfterDeletion() {
		lp= new LoginPage(driver);
		up= new UserCreationPage(driver);
		verifyUserNavigatesToAdminUserPage();
		up.userDeletion();
		String deleteAlert = up.deleteSuccessAlert();
		System.out.println(deleteAlert);
	}
	
}
