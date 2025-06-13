package dimpletesting.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dimpletesting.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);	
	}
	/*
	 * List<WebElement> cartProducts =
	 * driver.findElements(By.cssSelector(".cartSection h3")); 
	 * Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().
	 * equalsIgnoreCase(productName)); Assert.assertTrue(match);
	 * //Click on chckout button
	 * driver.findElement(By.cssSelector(".totalRow button")).click();
	 */
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().
				 equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage()
	{
		checkout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
