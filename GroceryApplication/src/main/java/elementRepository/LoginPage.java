package elementRepository;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ExcelRead;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// with page factory- to use @findBy annotations
		//initElement- 	static method of Page Factory. 
	}
    @FindBy(xpath="//input[@placeholder='Username']")
    WebElement userNameField;
    @FindBy(xpath ="//input[@placeholder='Password']")
    WebElement passwordField;
    @FindBy(xpath ="//button[text()='Sign In']")
    WebElement signIn;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']//h5[text()=' Alert!'] ")
	WebElement alertMessage;
    
    public AdminPage login(String userName, String password){
   
    	userNameField.sendKeys(userName);
    	passwordField.sendKeys(password);
    	signIn.click();
    	return new AdminPage(driver);//instead of void we are using AdminPage- after signin it redirects to admin page
    }
    public String getAlertText() {
		return alertMessage.getText();
	}
}
