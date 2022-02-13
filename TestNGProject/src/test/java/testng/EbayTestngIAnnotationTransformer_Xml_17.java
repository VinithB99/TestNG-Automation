package testng;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class EbayTestngIAnnotationTransformer_Xml_17 {

	public int iBrowserType = 5; // 1-Chrome,2-FF,3-Edge
	public String sURL = "https://www.ebay.com/";
	public WebDriver driver;

	@BeforeClass
	public void invokeBrowser() {

		switch (iBrowserType) {
		case 1:
			System.out.println("User option is " + iBrowserType + ",So invoking Chrome browser!!!");
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case 2:
			System.out.println("User option is " + iBrowserType + ",So invoking Firefox browser!!!");
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case 3:
			System.out.println("User option is " + iBrowserType + ",So invoking Edge browser!!!");
			System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("User option is wrong " + iBrowserType + ",So invoking default Chrome browser!!!");
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
	}

	@Test
	public void windowSettings() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		}
	@Test(enabled = false)
	public void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test
	public void getPageInfo() {
		String title = driver.getTitle();
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Page Title is : "+title);
		System.out.println("Page URL is : "+currentUrl);
		//Assert.assertTrue(false);
	}
	
	@Test
	public void searchProduct() {
		WebElement oTxt,oDrop,oBtn;
		oTxt = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		oTxt.sendKeys("iPhone");
		
		oDrop = driver.findElement(By.xpath("//select[@id='gh-cat']"));
		Select oSelect = new Select(oDrop);
		oSelect.selectByVisibleText("Cell Phones & Accessories");
		System.out.println("Dropdown is multi-select : "+oSelect.isMultiple());
		List<WebElement> options = oSelect.getOptions();
		System.out.println("Total Option is : "+options.size());
		for (WebElement element : options) {
			System.out.println(element.getText());
		}
		
		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.submit();
		
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
