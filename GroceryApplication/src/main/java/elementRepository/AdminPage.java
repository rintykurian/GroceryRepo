package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtility;

public class AdminPage {
WebDriver driver;
GeneralUtility gu = new GeneralUtility();

    //creating constructor
	public AdminPage(WebDriver driver) { 
		this.driver = driver;
		PageFactory.initElements(driver, this);// with page factory- to use @findBy annotations
	}
	@FindBy(xpath="//a[@class = 'nav-link']//p[contains(text(), 'Dashboard')]")
	WebElement dashBoard;
	@FindBy(xpath= "//a[text()=' Admin']")
	WebElement profileName;
	@FindBy(xpath = "//div[@class='small-box bg-info']//p[text()='Admin Users']")
	WebElement adminUsers;
	@FindBy(xpath ="//a[@href='https://groceryapp.uniqassosiates.com/admin/list-admin' and @class= 'small-box-footer']")
	WebElement adminUsersMoreInfo;
	
	public String profileName() {
		return profileName.getText();
	}
	public String getDashboardText() {
		return dashBoard.getText();
	}
	public String getAdminUser() {
		return adminUsers.getText();
	}
	public UserCreationPage getAdminUserMoreInfo() {
		 adminUsersMoreInfo.click();
		 return new UserCreationPage(driver);
	}
	
	
}