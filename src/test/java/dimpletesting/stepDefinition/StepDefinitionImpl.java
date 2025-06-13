package dimpletesting.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import dimpletesting.TestComponents.BaseTest;
import dimpletesting.pageobjects.CartPage;
import dimpletesting.pageobjects.CheckoutPage;
import dimpletesting.pageobjects.ConfirmOrder;
import dimpletesting.pageobjects.LandingPage;
import dimpletesting.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmOrder confirmOrder;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = LaunchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue=landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException
	{
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry();
		confirmOrder = checkoutPage.placeOrderButton();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_on_confirmationpage(String string)
	{
		String confirmMessage = confirmOrder.confirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
}
