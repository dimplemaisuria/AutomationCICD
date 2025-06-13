package dimpletesting.test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dimpletesting.TestComponents.BaseTest;
import dimpletesting.pageobjects.CartPage;
import dimpletesting.pageobjects.CheckoutPage;
import dimpletesting.pageobjects.ConfirmOrder;
import dimpletesting.pageobjects.OrderPage;
import dimpletesting.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	String productName="ZARA COAT 3";
		@Test(dataProvider="getData", groups= {"Purchase"})
		public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
		{
			
			//Create page object and use methods
			//email: dimple@gmail.com Password: Abcd-1234
			//creating object of product catalogue class along with landing page methods
			ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
			//Create page object and use methods
			//ProductCatalogue productCatalogue=new ProductCatalogue(driver);
			List<WebElement>products = productCatalogue.getProductList();
			//finding product name
			//Add to Cart	
			productCatalogue.addProductToCart(input.get("product"));
			CartPage cartPage=productCatalogue.goToCartPage();
			//CartPage object and calling methods
			//CartPage cartPage= new CartPage(driver);
			Boolean match = cartPage.verifyProductDisplay(input.get("product"));
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
			//Add country name
			//Action class
			checkoutPage.selectCountry();
			ConfirmOrder confirmOrder = checkoutPage.placeOrderButton();
			//Thank you page
			String confirmMessage = confirmOrder.confirmMessage();
			//String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
			//Order number
			String orderNumber = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
			System.out.println(orderNumber);
		}
		//To Verify Zara Coat 3 displays in Order Page
		@Test(dependsOnMethods = {"submitOrder"})
		public void OrderHistoryTest()
		{
			//Depend on submitOrder()
			//String productName="ZARA COAT 3";
			ProductCatalogue productCatalogue=landingPage.loginApplication("dimple@gmail.com", "Abcd-1234");
			OrderPage orderPage=productCatalogue.goToOrderPage();
			Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		}

		
		@DataProvider
		public Object[][] getData() throws IOException
		{

			
			List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\dimpletesting\\data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		//Extent Reports
		
//		@DataProvider
//		public Object[][] getData()
//		{
//			return new Object[][] {{"dimple@gmail.com","Abcd-1234","ZARA COAT 3"},{"dimple@gmail.com","Abcd-1234","ZARA COAT 3"}};
//		}
		//Hash map
//		HashMap<String,String> map=new HashMap<String,String>();
//			map.put("email", "dimple@gmail.com");
//			map.put("password", "Abcd-1234");
//			map.put("product", "ZARA COAT 3");
//			
//		HashMap<String,String> map1=new HashMap<String,String>();
//			map1.put("email", "dimple@gmail.com");
//			map1.put("password", "Abcd-1234");
//			map1.put("product", "ADIDAS ORIGINAL");	
}
