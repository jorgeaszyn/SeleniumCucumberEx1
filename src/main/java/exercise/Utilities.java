package exercise;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	protected String driverBrowser = "webdriver.chrome.driver";
	protected String driverPath = "C:\\Selenium_drivers\\chromedriver.exe";
	protected String urlApp = "https://login.salesforce.com/";
	protected String user = "aszyn@jorge.com";
	protected String pwd = "Hugo2153";

	protected By username = By.id("username");
	protected By password = By.id("password");
	protected By login = By.id("Login");	
	protected By waffle = By.xpath("//div[@class='slds-icon-waffle']");
	protected By locatorService = By.xpath("//p[text()='Service']");
	
	public By locatorCancel = By.xpath("//button[contains(text(),'Cancel')]");
	public By locatorSave = By.xpath("//button[@name='SaveEdit']");
	
	//return tab locator by title
	public By tab(String title) {
		By t = By.xpath("//a[@title='" + title + "']");
		return t;
	}
	
	public static WebDriver driver;
	
    public WebDriver initialize() {
		
		System.setProperty(driverBrowser, driverPath);

		// Switch off browser notifications
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);

		driver.get(urlApp);
		driver.manage().window().maximize();
		
		//Login
		driver.findElement(username).sendKeys(user);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(login).click();
		
		WebDriverWait w = new WebDriverWait(driver, 20);
		
		//open Service app
		w.until(ExpectedConditions.visibilityOfElementLocated(waffle));
		driver.findElement(waffle).click();	
		w.until(ExpectedConditions.visibilityOfElementLocated(locatorService));
		driver.findElement(locatorService).click();
		
		return driver;
	}

    //click with JavascriptExecutor
	public void jsClick(By locator) {
		try { Thread.sleep(3000L); }
	    catch (InterruptedException ie) { ; /* ignore */ }
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
	}
	
	public void openNewAccount() {
		
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(tab("Accounts")));
		jsClick(tab("Accounts"));
		jsClick(tab("New"));
	}
	
	public void openNewContact() {
		
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions.visibilityOfElementLocated(tab("Contacts")));
		jsClick(tab("Contacts"));
		jsClick(tab("New"));
	}
	
	//save new account & contact 
	public void save() {
		driver.findElement(locatorSave).click();
	}
	
	
	
}
