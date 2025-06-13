package dimpletesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dimpletesting.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement Entercountry;
	
	By countryResult = (By.cssSelector(".ta-results"));
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	//driver.findElement(By.cssSelector(".actions a")).click();
	@FindBy(css=".actions a")
	WebElement placeOrder;
	
//	//Add country name
//	//Action class
//	Actions a = new Actions(driver);
//	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//	driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	public void selectCountry() throws InterruptedException
	{
		Actions a = new Actions(driver);
		a.sendKeys(Entercountry,"india").build().perform();
		waitForElementToAppear(countryResult);
		selectCountry.click();
		scrollDownWindow(driver);
	}
	public ConfirmOrder placeOrderButton()
	{
		placeOrder.click();
		ConfirmOrder confirmOrder = new ConfirmOrder(driver);
		return confirmOrder;
	}
}
