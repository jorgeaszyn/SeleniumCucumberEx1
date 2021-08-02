package exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Part5PageFactory {

	WebDriver driver;
	
	public Part5PageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Account Name']//following::th[@scope='row'][1]//following-sibling::td/span/div")
	WebElement edit;
	
	public WebElement Edit() {
			
		return edit;
	}
	
}
