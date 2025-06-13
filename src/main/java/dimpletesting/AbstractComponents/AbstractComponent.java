package dimpletesting.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dimpletesting.pageobjects.CartPage;
import dimpletesting.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void scrollDownWindow(WebDriver driver) throws InterruptedException
	{
		//Javascript using consol on inspect page -- Scroll down page
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,500)");
				Thread.sleep(3000);
	}
	public CartPage goToCartPage()
	{
		cart.click();
		CartPage cartPage= new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage= new OrderPage(driver);
		return orderPage;
	}
	}
