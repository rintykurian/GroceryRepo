package elementRepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.FakerUtility;
import utilities.GeneralUtility;
import utilities.WaitUtility;

public class UserCreationPage {
WebDriver driver;
GeneralUtility gu = new GeneralUtility();
FakerUtility fu = new FakerUtility();
WaitUtility wu= new WaitUtility();
String generatedUsername;


//creating constructor
	public UserCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// with page factory- to use @findBy annotations
	}
	
	//@FindBy(xpath= "//a[@onclick='click_button(1)' and contains(text(), 'New')]")
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	WebElement newUserButton;
	@FindBy(xpath = "//input[@id = 'username']")
	WebElement adminUserName;
	@FindBy(xpath = "//input[@id = 'password']")
	WebElement adminPassword;
	@FindBy(xpath = "//select[@id = 'user_type']")
	WebElement adminUserType;
	@FindBy(xpath= "//button[@name = 'Create']")
	WebElement saveButton;
	//@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']//h5[text()=' Alert!']")
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement alertText;
	@FindBy(xpath="//h1[@class='m-0 text-dark' and contains(text(),'Admin Users')]")
	WebElement adminUsersHeading;
	@FindBy(xpath= "//div[@class='card-header']//h4[text()='Admin Users']")
	WebElement tableName;
	@FindBy(xpath ="//td//a[@class='btn btn-sm btn btn-primary btncss']")
	WebElement editIcon;
	@FindBy(xpath="//button[@name='Update']")
	WebElement updateButton;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement updateAlertMessage;
	@FindBy(xpath="//td//a[@class='btn btn-sm btn btn-danger btncss']")
	WebElement deleteIcon;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")	
	WebElement deleteAlertMessage;
	//User Creation
	public void newUserCreation(String password, String userType) {
		newUserButton.click();
		//wu.WaitForWebElementClickable(driver, adminUserName, 5);
		adminUserName.sendKeys(fu.generateName());
		//adminUserName.sendKeys("Rinty" + gu.randon(100));//another way to enter values
		adminPassword.sendKeys(password);
		gu.selectDropdownWithVisibleText(adminUserType, userType);
		saveButton.click();
	}
	//Alert after user creation
	public String successAlert() {
			return alertText.getText();
	}
	//To get Admin Page Heading
	public String getAdminUserHeading() {
		return adminUsersHeading.getText();
	}
	//To get Table Name
	public String getTableName() {
		return tableName.getText();
	}
	//To get element from table
	public String getAdminUserTableElementText(int row, int column) {
		String path = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+row+"]//td["+column+"]";
		WebElement element = driver.findElement(By.xpath(path));
		return element.getText();
	}
	//To get element in the first row
	public String returnFirstRowname()
    {
 	   WebElement firstRowName =driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]//td[1]"));
 	   return firstRowName.getText();
    }
	 public String getGeneratedUsername() {
	        return generatedUsername;
	    }

	//User Updation
	public void userUpdation() {
		returnFirstRowname();
		editIcon.click();
		adminUserName.clear();
		generatedUsername = fu.generateName();
		adminUserName.sendKeys(generatedUsername);
		updateButton.click();	
	}
	//Alert after updation
	public String updateSuccessAlert() {
		return updateAlertMessage.getText();
	}
	//User Deletion
	public void userDeletion() {
		returnFirstRowname();
		deleteIcon.click();	
		String alertText = driver.switchTo().alert().getText();
  	    System.out.println(alertText);
		driver.switchTo().alert().accept();
	}
	public String deleteSuccessAlert() {
		return deleteAlertMessage.getText();
	}
	
	
}
