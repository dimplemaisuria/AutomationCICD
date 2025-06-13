package dimpletesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dimpletesting.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail=this.driver.findElement(By.id("userEmail"));
	//page Factory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword")).sendKeys("Abcd-1234");
	@FindBy(id="userPassword")
	WebElement password;
	
	//driver.findElement(By.id("login")).click();
	@FindBy(id="login")
	WebElement submit;
	//.ng-tns-c4-4.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css= ".toast-message")
	WebElement errorMessage;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalogue loginApplication(String email, String pswd)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pswd);
		submit.click();
		//creating object of the other class here 
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
