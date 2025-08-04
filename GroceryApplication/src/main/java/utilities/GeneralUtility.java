package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class GeneralUtility {
	public void selectDropdownWithValue(WebElement element, String value) {
		Select object = new Select(element);
		object.selectByValue(value);
	}
	public void selectDropdownWithIndex(WebElement element, int index) {
		Select object = new Select(element);
		object.selectByIndex(index);
	}
	public void selectDropdownWithVisibleText(WebElement element, String text) {
		Select object = new Select(element);
		object.selectByVisibleText(text);
	}
	public void alertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void mouseRightClick(WebDriver driver, WebElement element) {
		Actions actObject = new Actions(driver);
		actObject.contextClick(element).perform();
	}
	public String getAttributeValueOfElement(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}
	public void clickJavaScriptExecutor(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}
	public String generateCurrentDateAndTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyhhmmss");
		return formatter.format(date);
	}
	public int randon(int limit) {
		Random random = new Random();		
		int randomNumber = random.nextInt(limit);
		return randomNumber;
	}
	
	public static String getCellValueForName(WebDriver driver, String tableId, String targetName, int columnIndex) {
        List<WebElement> nameList = driver.findElements(By.xpath("//table[@id='" + tableId + "']//tbody//tr//td[1]")
        );

        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).getText().trim().equalsIgnoreCase(targetName.trim())) {
                String cellXpath = "//table[@id='" + tableId + "']//tbody//tr[" + (i + 1) + "]//td[" + columnIndex + "]";
                WebElement cell = driver.findElement(By.xpath(cellXpath));
                return cell.getText();
            }
        }

        return null; // Name not found
    }
}
