package exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

public class Part4 extends Utilities{
	
	private By locatorLastName = By.xpath("//input[@name='lastName']");
	private By contacts = By.xpath("//a[@title='Contacts']");
	
	private String xpathSearchAccount = "//input[@placeholder='Search Accounts...']";
	private String xpathOptionsAccounts = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']/span";
	
	public void part4(WebDriver driver, ITestContext context) throws SQLException {
		
		WebDriverWait w = new WebDriverWait(driver, 15);
		
		//Open Contacts in new tab
		w.until(ExpectedConditions.visibilityOfElementLocated(contacts));
		String clickTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
	    driver.findElement(contacts).sendKeys(clickTab);
	
	    //Switch to Contacts tab
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
			
		openNewContact();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(locatorLastName));
		
		//Database connection
		String host = "localhost";
		String port = "3306";
		Connection con =  DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/excercise1b" , "root", "2153");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from contact limit 1");
		
		//Populate data
		while(rs.next()) {
			
			driver.findElement(locatorLastName).sendKeys(rs.getString("lastName"));				
		}
		
		//fetch priorly created accountName 
		String lastAccount = (String) context.getAttribute("result");
		selectCombo(driver, lastAccount);
		
		//Save new contact 
		save();
		
		//Go back to main tab
		driver.switchTo().window(tabs.get(0));
		
	}
	
	private void selectCombo(WebDriver driver, String value) {
		
		WebDriverWait w = new WebDriverWait(driver, 15);
		
		driver.findElement(By.xpath(xpathSearchAccount)).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOptionsAccounts)));
		
		List<WebElement> options = driver.findElements(By.xpath(xpathOptionsAccounts));
		System.out.println(options.size());
		
		for (int i = 0; i <= options.size() - 1; i++) {
			if (options.get(i).getText().equals(value)) {
				options.get(i).click();
				break;
			}
		}
	}


}
