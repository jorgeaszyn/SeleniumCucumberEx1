package exercise;

import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Part7 extends Utilities{
	
	private String accountName = "Account Name";
	private String phone = "Phone";
	private String accountNumber = "Account Number";
	private String website = "Website";
	
	public void part7(WebDriver driver, String accountValue, String phoneValue, String accNumbValue, String webSiteValue) throws SQLException {
		
		AccountsPageObjects p = new AccountsPageObjects(driver);
		
		WebDriverWait w = new WebDriverWait(driver, 15);

		openNewAccount();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.xpathLabel(phone))));
			
		p.inputSendKeys(accountName, accountValue);
		p.inputSendKeys(phone, phoneValue);
		p.inputSendKeys(accountNumber, accNumbValue);
		p.inputSendKeys(website, webSiteValue);		
		
		save(); 
		
	}
	
}


