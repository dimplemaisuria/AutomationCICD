package dimpletesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dimpletesting.AbstractComponents.AbstractComponent;

public class ConfirmOrder extends AbstractComponent{
		WebDriver driver;		
		
		public ConfirmOrder(WebDriver driver) 
		{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
		
		@FindBy(css=".hero-primary")
		WebElement confirmMessage;
		
		public String confirmMessage()
		{
			return confirmMessage.getText();
			 
		}
}
