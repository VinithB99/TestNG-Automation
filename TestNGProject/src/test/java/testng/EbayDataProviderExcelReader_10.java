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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import wrapper.DataInputProvider;
public class EbayDataProviderExcelReader_10 {

	//public int iBrowserType = 5; // 1-Chrome,2-FF,3-Edge
	public String sURL = "https://www.ebay.com/";
	public WebDriver driver;
	
	@Parameters("BrowserName")
	@BeforeClass
	public void invokeBrowser(String sBrowser) {

		switch (sBrowser) {
		case "Chrome":
			System.out.println("User option is " + sBrowser + ",So invoking Chrome browser!!!");
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "Firefox":
			System.out.println("User option is " + sBrowser + ",So invoking Firefox browser!!!");
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "Edge":
			System.out.println("User option is " + sBrowser + ",So invoking Edge browser!!!");
			System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("User option is wrong " + sBrowser + ",So invoking default Chrome browser!!!");
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
	}

	@Test(priority = 1)
	public void windowSettings() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		}
	@Test(priority = 2)
	public void navigateURL() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test(priority = 3)
	public void getPageInfo() {
		String title = driver.getTitle();
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Page Title is : "+title);
		System.out.println("Page URL is : "+currentUrl);
	}
	

	@DataProvider(name = "productSearchCat")
	public Object[][] provideValues() {
		Object sData[][] = null;
		try {
			sData = DataInputProvider.getValue("EbayProduct");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sData;
	}
	
	@Test(priority = 4,dataProvider = "productSearchCat")
	public void searchProduct(String sPro,String sCat) {
		WebElement oTxt,oDrop,oBtn;
		oTxt = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		oTxt.clear();
		oTxt.sendKeys(sPro);
		
		oDrop = driver.findElement(By.xpath("//select[@id='gh-cat']"));
		Select oSelect = new Select(oDrop);
		oSelect.selectByVisibleText(sCat);
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
