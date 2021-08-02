package stepDefinition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

import exercise.AccountsPageObjects;
import exercise.Part1;
import exercise.Utilities;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

public class stepDefinition extends Utilities {

	AccountsPageObjects p = new AccountsPageObjects(driver);

	private By navTabs = By.xpath("//div[@role='list']");
	private By locatorTabs = By.xpath("//a[@class='slds-context-bar__label-action dndItem']");

	static String lastAccountName;

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

	private By locatorLastName = By.xpath("//input[@name='lastName']");

	private String title = "Accounts";
	private String newValue1 = "Cold";
	private String newValue2 = "Prospect";
	private String newValue3 = "No";

	private String xpathSearchAccount = "//input[@placeholder='Search Accounts...']";
	private String xpathOptionsAccounts = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']/span";
	
	private String newValue4 = "1431655766";
	private String xpathMessageError = "//label[text()='Employees']//following::input[1]//following::div[1]";
	private String xpathIconError = "//lightning-icon[@title='Error']";
	private String messageError = "Employees: value outside of valid range on numeric field: 1431655766";
	
	// Scenario initialize

	@Given("Salesforce website is opens")
	public void salesforce_website_is_opens() {
		System.setProperty(driverBrowser, driverPath);

		// Switch off browser notifications
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);

		driver.get(urlApp);
		driver.manage().window().maximize();
	}

	@When("Enter username and password and log in")
	public void enter_username_and_password_and_log_in() {
		driver.findElement(username).sendKeys(user);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(login).click();
	}

	@Then("Navigate to Service app")
	public WebDriver navigate_to_service_app() {
		WebDriverWait w = new WebDriverWait(driver, 20);

		// open Service app
		w.until(ExpectedConditions.visibilityOfElementLocated(waffle));
		driver.findElement(waffle).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(locatorService));
		driver.findElement(locatorService).click();

		return driver;
	}

	// Scenario 1

	@Given("The diferent Service app tabs")
	public List<WebElement> the_diferent_service_app_tabs() {
		WebDriverWait w = new WebDriverWait(driver, 15);

		w.until(ExpectedConditions.visibilityOfElementLocated(navTabs));
		WebElement nav = driver.findElement(navTabs);
		List<WebElement> tabs = nav.findElements(locatorTabs);
		return tabs;
	}

	@Then("Navigate through all tabs, click new and cancel")
	public void navigate_through_all_tabs_click_new_and_cancel() {
		WebDriverWait w = new WebDriverWait(driver, 15);

		List<WebElement> tabs = the_diferent_service_app_tabs();

		for (int i = 0; i < tabs.size(); i++) {

			String titleName = tabs.get(i).getAttribute("title");

			switch (i) {
			case 1:
				jsClick(tab(titleName));
				break;
//			 	case 2:
//			 		jsClick(tab(titleName));
//			 		jsClick(tab("New"));		
//			 		jsClick(locatorCancel);
//					break;
//			 	case 3:
//			 		jsClick(tab(titleName));
//			 		jsClick(tab("New"));
//			 		jsClick(locatorCancel);
//					break;
//			 	case 4:
//			 		jsClick(tab(titleName));
//			 		jsClick(tab("New"));
//			 		jsClick(cancelCases);
//				    break;
//			 	case 5:
//			 		jsClick(tab(titleName));
//					w.until(ExpectedConditions.visibilityOfElementLocated(tab("New Report")));
//					jsClick(tab("New Report"));
//					w.until(ExpectedConditions.visibilityOfElementLocated(frameReport));
//					driver.switchTo().frame(driver.findElement(frameReport));		
//					jsClick(cancelReport);
//					driver.switchTo().defaultContent();
//					break;
//			 	case 6:
//			 		jsClick(tab(titleName));
//			 		jsClick(tab("New Dashboard"));
//					w.until(ExpectedConditions.visibilityOfElementLocated(frameDashboard));
//					driver.switchTo().frame(driver.findElement(frameDashboard));
//					jsClick(locatorCancel);
//					driver.switchTo().defaultContent();
//					break;
//			 	case 7:
//			 		jsClick(tab(titleName));
//			 		jsClick(tab("Home"));
//			 		break;
			}
		}

	}

	// Scenario 2

	@Given("A new account is opened")
	public void a_new_account_is_opened() {
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(tab("Accounts")));
		jsClick(tab("Accounts"));
		jsClick(tab("New"));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.xpathLabel(rating))));
	}

	@When("The database connects")
	public ResultSet the_database_connects() throws SQLException {
		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/excercise1b", "root",
				"2153");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from account limit 1");
		return rs;
	}

	@Then("Populate data")
	public void populate_data() throws SQLException {

		ResultSet rs = the_database_connects();
		while (rs.next()) {
			p.selectCombo(rating, rs.getString("rating"));
			p.inputSendKeys(accountName, rs.getString("accountName"));

			lastAccountName = rs.getString("accountName");

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
			p.inputDate(rs.getString("slaExpirationDateYear"), rs.getString("slaExpirationDateMonth"),
					rs.getString("slaExpirationDateDay"));
		}
	}

	@Then("Save the record")
	public void save_the_record() {
		save();
	}

	// Scenario 3

	@Given("A new account is opened again")
	public void a_new_account_is_opened_again() {
		openNewAccount();
	}

	@When("Mandatory fields are kept empty")
	public void mandatory_fields_are_kept_empty() {

	}

	@Then("Save record")
	public void save_record() {
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(locatorSave));
		save();
	}

	@And("Verify error")
	public void verify_error() {
		By locatorError = By.xpath("//lightning-icon[@title='Error']");
		Assert.assertTrue(driver.findElement(locatorError).isDisplayed());
	}

	// Scenario 4

	@Given("Contacts is opened in new tab")
	public void contacts_is_opened_in_new_tab() {
		By contacts = By.xpath("//a[@title='Contacts']");
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(contacts));
		String clickTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElement(contacts).sendKeys(clickTab);
	}

	@When("Switch to Contacts tab")
	public ArrayList<String> switch_to_contacts_tab() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		return tabs;
	}

	@And("Open new contact")
	public void open_new_contact() {
		openNewContact();
	}

	@And("The database connects again")
	public ResultSet the_database_connects_again() throws SQLException {
		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/excercise1b", "root",
				"2153");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from contact limit 1");
		return rs;
	}

	@Then("Populate last name")
	public void populate_last_name() throws SQLException {
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(locatorLastName));
		ResultSet rs = the_database_connects_again();
		while (rs.next()) {
			driver.findElement(locatorLastName).sendKeys(rs.getString("lastName"));
		}
	}

	@And("Populate account name with priorly created account")
	public void populate_account_name_with_priorly_created_account() {
		WebDriverWait w = new WebDriverWait(driver, 15);

		driver.findElement(By.xpath(xpathSearchAccount)).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOptionsAccounts)));

		List<WebElement> options = driver.findElements(By.xpath(xpathOptionsAccounts));
		System.out.println(options.size());

		for (int i = 0; i <= options.size() - 1; i++) {
			if (options.get(i).getText().equals(lastAccountName)) {
				options.get(i).click();
				break;
			}
		}
	}

	@Then("Save new contact")
	public void save_new_contact() {
		save();
	}

	@And("Go back to main tab")
	public void go_back_to_main_tab() {
		driver.switchTo().window(switch_to_contacts_tab().get(0));
	}

	// Scenario 5

	@Given("Accounts is opened")
	public void accounts_is_opened() {
		jsClick(tab(title));
	}

	@When("Open Edit account priorly created")
	public void open_edit_account_priorly_created() throws InterruptedException {
		Thread.sleep(3000);
		p.openEditAccount(lastAccountName);
	}

	@Then("Update two values")
	public void update_two_values() {
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.xpathLabel(rating))));
		p.selectCombo(rating, newValue1);
		p.selectCombo(type, newValue2);
	}

	@And("Scroll down")
	public void scroll_down() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('div.actionBody').scrollBy(0,800)"); // scroll down
	}

	@Then("Update another value")
	public void update_another_value() {
		p.selectCombo(upsellOpportunity, newValue3);
	}

	@And("Save update")
	public void save_update() {
		save();
	}

	@Then("Verify changes")
	public void verify_changes() throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, 15);
		Thread.sleep(3000);
		p.openEditAccount(lastAccountName);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.xpathLabel(rating))));

		Assert.assertEquals(p.actualValue(rating), newValue1);
		Assert.assertEquals(p.actualValue(type), newValue2);
		Assert.assertEquals(p.actualValue(upsellOpportunity), newValue3);

		jsClick(locatorCancel);
	}

	// Scenario 6

	@Given("Accounts is opened again")
	public void accounts_is_opened_again() {
		jsClick(tab(title));
	}

	@When("Open Edit account priorly created again")
	public void open_edit_account_priorly_created_again() throws InterruptedException {
		Thread.sleep(3000);
		p.openEditAccount(lastAccountName);
	}

	@Then("Edit Employee field with long digit value")
	public void edit_employee_field_with_long_digit_value() {
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.xpathLabel(employees))));
		p.clearInput(employees);
		p.inputSendKeys(employees, newValue4);
	}
	
	@And("Save update again")
	public void save_update_again() {
		save();
	}

	@Then("Verify message error")
	public void verify_message_error() {
		boolean getError = false;
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathIconError)));
		if (driver.findElement(By.xpath(xpathIconError)).isDisplayed() ||
				driver.findElement(By.xpath(xpathMessageError)).getText().equals(messageError)) {
			getError = true;
		}		
		
		Assert.assertTrue(getError);
		jsClick(locatorCancel);
	}
	
	// Scenario 7
	
	@Given("Each new account is opened")
    public void each_new_account_is_opened() {
		openNewAccount();
    }

    @Then("Populate and save data with {string}, {string}, {string} and {string}")
    public void populate_and_save_data_with_and(String accName, String accPhone, String accNumber, String accWebsite) {
    	WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.xpathLabel(phone))));
			
		p.inputSendKeys(accountName, accName);
		p.inputSendKeys(phone, accPhone);
		p.inputSendKeys(accountNumber, accNumber);
		p.inputSendKeys(website, accWebsite);
		
		save();
    }
    
    @Given("Close all browsers")
    public void close_all_browsers() {
    	driver.quit();
    }







	
}

	

	
