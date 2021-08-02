package exercise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Part1 extends Utilities{
	
	private By navTabs = By.xpath("//div[@role='list']");
	private By locatorTabs = By.xpath("//a[@class='slds-context-bar__label-action dndItem']");
	private By locatorCancel = By.xpath("//button[contains(text(),'Cancel')]");
	private By cancelCases = By.xpath("//div[3]/button[1]");
	private By frameReport = By.cssSelector("iframe[title='Report Builder']");
	private By cancelReport = By.cssSelector(".slds-button.slds-button_neutral.report-type-cancel");
	private By frameDashboard = By.cssSelector("iframe[title='dashboard']");
	
	public void part1(WebDriver driver) {
		
		WebDriverWait w = new WebDriverWait(driver, 15);

		w.until(ExpectedConditions.visibilityOfElementLocated(navTabs));
		WebElement nav = driver.findElement(navTabs);
		List<WebElement> tabs =  nav.findElements(locatorTabs);
        	
		for(int i = 0; i < tabs.size(); i++) {
			
			String titleName = tabs.get(i).getAttribute("title");
			
			 switch(i) {
			 	case 1:
			 		jsClick(tab(titleName));
			 		break;
			 	case 2:
			 		jsClick(tab(titleName));
			 		jsClick(tab("New"));		
			 		jsClick(locatorCancel);
					break;
			 	case 3:
			 		jsClick(tab(titleName));
			 		jsClick(tab("New"));
			 		jsClick(locatorCancel);
					break;
			 	case 4:
			 		jsClick(tab(titleName));
			 		jsClick(tab("New"));
			 		jsClick(cancelCases);
				    break;
			 	case 5:
			 		jsClick(tab(titleName));
					w.until(ExpectedConditions.visibilityOfElementLocated(tab("New Report")));
					jsClick(tab("New Report"));
					w.until(ExpectedConditions.visibilityOfElementLocated(frameReport));
					driver.switchTo().frame(driver.findElement(frameReport));		
					jsClick(cancelReport);
					driver.switchTo().defaultContent();
					break;
			 	case 6:
			 		jsClick(tab(titleName));
			 		jsClick(tab("New Dashboard"));
					w.until(ExpectedConditions.visibilityOfElementLocated(frameDashboard));
					driver.switchTo().frame(driver.findElement(frameDashboard));
					jsClick(locatorCancel);
					driver.switchTo().defaultContent();
					break;
			 	case 7:
			 		jsClick(tab(titleName));
			 		jsClick(tab("Home"));
			 		break;
			 }
		}
	}
}		

