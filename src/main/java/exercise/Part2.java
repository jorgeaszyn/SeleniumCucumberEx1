package exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

public class Part2 extends Utilities{
	
	private String rating = "Rating";
	private String accountName = "Account Name";
	private String phone = "Phone";
	private String fax = "Fax";
	private String accountNumber = "Account Number";
	private String website = "Website";
	private String accountSite = "Account Site";
	private String tickerSymbol = "Ticker Symbol";
	private String type = "Type";
	private String ownership = "Ownership";
	private String industry = "Industry";
	private String employees = "Employees";
	private String annualRevenue = "Annual Revenue";
	private String sicCode = "SIC Code";
	private String billingStreet = "Billing Street";
	private String billingCity = "Billing City";
	private String billingStateProvince = "Billing State/Province";
	private String billingPostalCode = "Billing Zip/Postal Code";
	private String billingCountry = "Billing Country";
	private String shippingStreet = "Shipping Street";
	private String shippingCity = "Shipping City";
	private String shippingStateProvince = "Shipping State/Province";
	private String shippingPostalCode = "Shipping Zip/Postal Code";
	private String shippingCountry = "Shipping Country";
	private String customerPriority = "Customer Priority";
	private String sla = "SLA";
	private String slaSerialNumber = "SLA Serial Number";
	private String numberOfLocations = "Number of Locations";
	private String upsellOpportunity = "Upsell Opportunity";
	private String active = "Active";
	
	public void part2(WebDriver driver, ITestContext context) throws SQLException {
		
		AccountsPageObjects p = new AccountsPageObjects(driver);
		
		WebDriverWait w = new WebDriverWait(driver, 15);

		openNewAccount();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.xpathLabel(rating))));
		
		//Database connection
		String host = "localhost";
		String port = "3306";
		Connection con =  DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/excercise1b" , "root", "2153");
		Statement s = con.createStatement();
		ResultSet rs =  s.executeQuery("select * from account limit 1");
		
		//Populate data
		while(rs.next()) {
			
			p.selectCombo(rating, rs.getString("rating"));
			p.inputSendKeys(accountName, rs.getString("accountName"));
			
			//save accountName
			String str =  rs.getString("accountName");
			context.setAttribute("result", str);	
			
			p.inputSendKeys(phone, rs.getString("phone"));
			p.inputSendKeys(fax, rs.getString("fax"));
			p.inputSendKeys(accountNumber, rs.getString("accountNumber"));
			p.inputSendKeys(website, rs.getString("website"));
			p.inputSendKeys(accountSite, rs.getString("accountSite"));
			p.inputSendKeys(tickerSymbol, rs.getString("tickerSymbol"));
			p.selectCombo(type, rs.getString("accountType"));
			p.selectCombo(ownership, rs.getString("ownership"));
			p.selectCombo(industry, rs.getString("industry"));
			p.inputSendKeys(employees, rs.getString("employees"));
			p.inputSendKeys(annualRevenue, rs.getString("annualRevenue"));
			p.inputSendKeys(sicCode, rs.getString("sicCode"));
			p.inputSendKeys(billingStreet, rs.getString("billingStreet"));
			p.inputSendKeys(billingCity, rs.getString("billingCity"));
			p.inputSendKeys(billingStateProvince, rs.getString("billingStateProvince"));
			p.inputSendKeys(billingPostalCode, rs.getString("billingPostalCode"));
			p.inputSendKeys(billingCountry, rs.getString("billingCountry"));
			p.inputSendKeys(shippingStreet, rs.getString("shippingStreet"));
			p.inputSendKeys(shippingCity, rs.getString("shippingCity"));
			p.inputSendKeys(shippingStateProvince, rs.getString("shippingStateProvince"));
			p.inputSendKeys(shippingPostalCode, rs.getString("shippingPostalCode"));
			p.inputSendKeys(shippingCountry, rs.getString("shippingCountry"));
			p.selectCombo(customerPriority, rs.getString("customerPriority"));
			p.selectCombo(sla, rs.getString("sla"));
			p.inputSendKeys(slaSerialNumber, rs.getString("slaSerialNumber"));
			p.inputSendKeys(numberOfLocations, rs.getString("numberOfLocations"));
			p.selectCombo(upsellOpportunity, rs.getString("upsellOpportunity"));
			p.selectCombo(active, rs.getString("accountActive"));
			p.inputDescription(rs.getString("accountDescription"));
			p.inputDate(rs.getString("slaExpirationDateYear"), rs.getString("slaExpirationDateMonth"), rs.getString("slaExpirationDateDay"));
		}	
		
		save(); 
		
	}

}


