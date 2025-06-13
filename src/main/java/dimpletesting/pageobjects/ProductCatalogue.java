package dimpletesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dimpletesting.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}

	//page Factory
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	//By.cssSelector(".mb-3") -- because it's used for wait method
	By productsBy = By.cssSelector(".mb-3");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	//By.cssSelector("#toast-container")
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		//finding product name
		WebElement prod = getProductList().stream().filter(product->
			product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		//Add to Cart	
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
	}
	
}
