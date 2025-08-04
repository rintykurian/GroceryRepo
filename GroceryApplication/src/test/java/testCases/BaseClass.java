package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utilities.ExcelRead;
import utilities.ScreenShotCapture;

public class BaseClass {
	//In base class, we use beforeMethod and afterMethod
	WebDriver driver;
	ScreenShotCapture sc;
	public static Properties pro;
 
	public static void testBasic() throws IOException {
		pro = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		pro.load(fp);
	}
  @BeforeMethod(alwaysRun = true)// to execute all the time
  @Parameters("browser")
  public void beforeMethod(String browserName) throws IOException {
	  testBasic();
	  if(browserName.equals("chrome")) {
		  driver= new ChromeDriver();
	  }
	  else if(browserName.equals("firefox")){
		  driver= new FirefoxDriver();
	  }
	  
	  //driver.get("https://groceryapp.uniqassosiates.com/admin/login");
	  driver.get(pro.getProperty("baseURL"));
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }
  @AfterMethod(alwaysRun = true)

  public void afterMethode(ITestResult iTestResult) throws IOException {//iTestResult= listener: 
	  if (iTestResult.getStatus() == ITestResult.FAILURE) {
		  sc = new ScreenShotCapture();
		  sc.captureFailureScreenShot(driver, iTestResult.getName());
	  }
  driver.quit();
  }
  public static String loginData(int row, int column) throws IOException {
	  String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel\\Grocery_Application.xlsx";
	  return ExcelRead.getLoginData(path, "Login" ,row,column);
	  
  }

  /*@AfterMethod(alwaysRun = true)
  public void afterMethod() {
	  driver.quit();
  }*/

}
