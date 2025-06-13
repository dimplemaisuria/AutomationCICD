package dimpletesting.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dimpletesting.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		
		//properies class can read global property
		//using GlobalData.properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\dimpletesting\\Resources\\GlobalData.properties");
		prop.load(fis);
		//to use in cmd to invoke browser
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "D:\\eclipseWorkspace\\shoppingcart\\shoppingcartpractice\\chromedriver.exe");
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //help to run full screen
			
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","D:\\eclipseWorkspace\\shoppingcart\\shoppingcartpractice\\geckodriver");
	        driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "D:\\eclipseWorkspace\\shoppingcart\\shoppingcartpractice\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\test-output\\" + testCaseName + ".png";
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException 
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath));
				//StandardCharsets.UTF_8);
		
		//String to HashMap Jackson DatBind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
				{			});
		return data;
		//(map,map1)
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
		driver=initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
}
