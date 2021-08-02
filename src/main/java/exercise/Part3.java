package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Part3 extends Utilities{

	private By locatorError = By.xpath("//lightning-icon[@title='Error']");
	
	public void part3(WebDriver driver) {
		
		WebDriverWait w = new WebDriverWait(driver, 15);
		
		openNewAccount();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(locatorSave));
		save();
		
		Assert.assertTrue(driver.findElement(locatorError).isDisplayed());
	}
}
