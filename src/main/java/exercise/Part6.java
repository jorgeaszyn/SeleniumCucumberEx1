package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

public class Part6 extends Utilities{
	
	private String label1 = "Employees";
	private String newValue1 = "1431655766";
	
	private String title = "Accounts";	
	
	private String xpathMessageError = "//label[text()='Employees']//following::input[1]//following::div[1]";
	private String xpathIconError = "//lightning-icon[@title='Error']";
	private String messageError = "Employees: value outside of valid range on numeric field: 1431655766";
	
	public void part6 (WebDriver driver, ITestContext context) throws InterruptedException {
		
		AccountsPageObjects po = new AccountsPageObjects(driver);
		WebDriverWait w = new WebDriverWait(driver, 15);
				
		//open Accounts
		w.until(ExpectedConditions.visibilityOfElementLocated(tab(title)));
		jsClick(tab(title));
		
		Thread.sleep(3000);
		po.openEditAccount(context);
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(po.xpathLabel(label1))));

		Thread.sleep(3000);
		
		//Edit record with long digit value
		po.clearInput(label1);
		po.inputSendKeys(label1, newValue1);
		
		save();
		
		Assert.assertTrue(getError());
		
		jsClick(locatorCancel);
	}
		
	private boolean getError() {
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathIconError)));
		if (driver.findElement(By.xpath(xpathIconError)).isDisplayed() ||
				driver.findElement(By.xpath(xpathMessageError)).getText().equals(messageError)) {
			return true;
		}
		return false;
	}
		
}
	

