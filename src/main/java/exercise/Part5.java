package exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

public class Part5 extends Utilities{
	
	private String label1 = "Rating";
	private String label2 = "Type";
	private String label3 = "Upsell Opportunity";
	private String newValue1 = "Cold";
	private String newValue2 = "Prospect";
	private String newValue3 ="No";
	
	private String title = "Accounts";	
	
	public void part5 (WebDriver driver, ITestContext context) throws InterruptedException {
		
		AccountsPageObjects po = new AccountsPageObjects(driver);
		WebDriverWait w = new WebDriverWait(driver, 15);
		JavascriptExecutor js = (JavascriptExecutor) driver;
				
		//open Accounts
		w.until(ExpectedConditions.visibilityOfElementLocated(tab(title)));
		jsClick(tab(title));
		
		Thread.sleep(3000);
		po.openEditAccount(context);
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(po.xpathLabel(label1))));
		
		//Update 3 values
		po.selectCombo(label1, newValue1);
		po.selectCombo(label2, newValue2);
			
		js.executeScript("document.querySelector('div.actionBody').scrollBy(0,800)"); //scroll down	
		po.selectCombo(label3 ,newValue3);

		save();
		
		//verify updated records
		Thread.sleep(3000);
		po.openEditAccountVerify();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(po.xpathLabel(label1))));
		
		Assert.assertEquals(po.actualValue(label1), newValue1);
		Assert.assertEquals(po.actualValue(label2), newValue2);
		Assert.assertEquals(po.actualValue(label3), newValue3);		
		
		jsClick(locatorCancel);
	}
}
