package dimpletesting.test;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dimpletesting.pageobjects.LandingPage;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// new comment added
		String productName="ZARA COAT 3";
		System.setProperty("webdriver.chrome.driver", "D:\\eclipseWorkspace\\shoppingcart\\shoppingcartpractice\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
		LandingPage landingPage = new LandingPage(driver);
		//email: dimple@gmail.com Password: Abcd-1234
		driver.findElement(By.id("userEmail")).sendKeys("dimple@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abcd-1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//collect all products in list
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		//finding product name
		WebElement prod = products.stream().filter(product->
			product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		//Add to Cart	
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);  
				
		//Click on chckout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Add country name
		//Action class
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		//Javascript using consol on inspect page -- Scroll down page
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".actions a")).click();
		
		//Thank you page
		String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		//Order number
		String orderNumber = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
		System.out.println(orderNumber);
		driver.close();
		
	}

}
