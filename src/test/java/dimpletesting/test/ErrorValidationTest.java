package dimpletesting.test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import dimpletesting.TestComponents.BaseTest;
import dimpletesting.pageobjects.CartPage;
import dimpletesting.pageobjects.ProductCatalogue;


public class ErrorValidationTest extends BaseTest{
	
		@Test(groups = {"ErrorHandling"}, retryAnalyzer=dimpletesting.TestComponents.Retry.class)
		public void LoginErrorValidation() throws IOException, InterruptedException
		{
			String productName="ZARA COAT 3";
			landingPage.loginApplication("dimpl@gmail.com", "Abcd-1234");
			//div[aria-label='Incorrect email or password.']
			System.out.println(landingPage.getErrorMessage());
			Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		}
		
		@Test
		public void ProductErrorValidation() throws IOException, InterruptedException
		{
			String productName="ZARA COAT 3";
			ProductCatalogue productCatalogue=landingPage.loginApplication("dimple@gmail.com", "Abcd-1234");
			List<WebElement>products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage=productCatalogue.goToCartPage();
			Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
		}
}
