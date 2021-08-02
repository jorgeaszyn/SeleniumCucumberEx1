package exercise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

public class AccountsPageObjects extends Utilities {

	WebDriver driver;

	public AccountsPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String xpathLabel(String label) {
		String ret = "//label[text()='" + label + "']//following::input[1]";
		return ret;
	}

	private String xpathOptionsCombo(String label) {
		String ret = xpathLabel(label) + "//following::div[@role='listbox']/lightning-base-combobox-item";
		return ret;
	}

	public void inputSendKeys(String label, String value) {
		driver.findElement(By.xpath(xpathLabel(label))).sendKeys(value);
	}
	
	public void clearInput(String label) {
		driver.findElement(By.xpath(xpathLabel(label))).clear();;
	}

	public void selectCombo(String label, String value) {
		String xLabel = xpathLabel(label);
		String optionsCombo = xpathOptionsCombo(label);

		driver.findElement(By.xpath(xLabel)).click();
		List<WebElement> options = driver.findElements(By.xpath(optionsCombo));
		for (int i = 0; i <= options.size() - 1; i++) {
			if (options.get(i).getAttribute("data-value").equals(value)) {
				options.get(i).click();
				break;
			}
		}
	}

	public void inputDescription(String value) {
		By description = By.xpath("//label[text()='Description']//following::textarea");
		driver.findElement(description).sendKeys(value);
	}

	public void inputDate(String year, String month, String day) {
		By expDate = By.xpath("//input[@name='SLAExpirationDate__c']");
		By locatorYear = By.xpath("//select[@class='slds-select']");
		By labelMonth = By.xpath("//h2[@role='alert']");
		By selectMonth = By.xpath("//button[@title='Next Month']");
		By locatorDates = By.xpath("//span[@class='slds-day']");

		driver.findElement(expDate).click();

		WebElement y = driver.findElement(locatorYear);
		Select s = new Select(y);
		s.selectByValue(year);

		while (!driver.findElement(labelMonth).getText().contains(month)) {
			driver.findElement(selectMonth).click();
		}

		List<WebElement> dates = driver.findElements(locatorDates);

		for (int i = 0; i < dates.size(); i++) {
			String text = dates.get(i).getText();
			if (text.equalsIgnoreCase(day)) {
				if (i < 7 && Integer.parseInt(day) < 8) {
					dates.get(i).click();
					break;
				}
				if (i > 6) {
					dates.get(i).click();
					break;
				}
			}
		}
	}

	// Part 5

	public By xpathAccounts = By.xpath("//th[@scope='row']/span/a");

	private By xpathComboEdit(int pos) {
		By xCombo = By.xpath("//span[text()='Account Name']//following::th[@scope='row'][" + pos
				+ "]//following-sibling::td/span/div");
		return xCombo;
	}

	public void openEditAccount(ITestContext context) {

		// fetch saved accountName
		String lastAccount = (String) context.getAttribute("result");

		WebDriverWait w = new WebDriverWait(driver, 15);

		w.until(ExpectedConditions.visibilityOfElementLocated(xpathAccounts));
		List<WebElement> accounts = driver.findElements(xpathAccounts);

		// open combo
		for (int i = 0; i < accounts.size(); i++) {

			if (accounts.get(i).getAttribute("title").equals(lastAccount)) {

				driver.findElement(xpathComboEdit(i + 1)).click();
				break;
			}
		}

		w.until(ExpectedConditions.visibilityOfElementLocated(tab("Edit")));
		driver.findElement(tab("Edit")).click();
	}

	//same method with string argument
		public void openEditAccount(String value) {

			WebDriverWait w = new WebDriverWait(driver, 15);

			w.until(ExpectedConditions.visibilityOfElementLocated(xpathAccounts));
			List<WebElement> accounts = driver.findElements(xpathAccounts);

			// open combo
			for (int i = 0; i < accounts.size(); i++) {

				if (accounts.get(i).getAttribute("title").equals(value)) {

					driver.findElement(xpathComboEdit(i + 1)).click();
					break;
				}
			}

			w.until(ExpectedConditions.visibilityOfElementLocated(tab("Edit")));
			driver.findElement(tab("Edit")).click();
		}
	
	public void openEditAccountVerify() {

		WebDriverWait w = new WebDriverWait(driver, 15);

		w.until(ExpectedConditions.visibilityOfElementLocated(xpathAccounts));

		// open combo
		Part5PageFactory pf = new Part5PageFactory(driver);
		pf.Edit().click();

		w.until(ExpectedConditions.visibilityOfElementLocated(tab("Edit")));
		driver.findElement(tab("Edit")).click();
	}

	public String actualValue(String label) {

		String inputLabel = xpathLabel(label);
		String actualValue = driver.findElement(By.xpath(inputLabel)).getAttribute("value");
		return actualValue;
	}


}
