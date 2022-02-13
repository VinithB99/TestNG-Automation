package testng;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class UIBank {
	
	public String sURL = "https://uibank.uipath.com/";
	public WebDriver driver;
	public ExtentHtmlReporter oHtml;
	public ExtentReports oExtent;
	public ExtentTest oTest;
	public String report = "./reports/UiBank.html";

	@BeforeClass
	public void setUpReport() {
		oHtml = new ExtentHtmlReporter(report);
		oHtml.setAppendExisting(false);
		oExtent = new ExtentReports();
		oExtent.attachReporter(oHtml);
	}
	@Test(priority = 1)
	public void invokeBrowser() {
		oTest=oExtent.createTest("Invoke Browser","User invoking a browser");
		oTest.assignCategory("Smoke");
		System.out.println("User invoking Chrome browser!!!");
		oTest.info("User Invoking a Browser");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		oTest.info("Browser is maximize automatically");
		driver.manage().window().maximize();
		oTest.info("Deleting the cookies");
		driver.manage().deleteAllCookies();
}
	@Test(priority =2)
	public void getPageInfo() {
		oTest=oExtent.createTest("Page information","User get the tittle and url of the page");
		oTest.assignCategory("Sanity");
		oTest.info("User getting Page information");
		driver.get(sURL);
		oTest.info("User Naviagted to : "+sURL);
		String title = driver.getTitle();
		String currentUrl = driver.getCurrentUrl();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		oTest.info("Page title to : "+title);
		System.out.println("Page Title is : "+title);
		System.out.println("Page URL is : "+currentUrl);
	}
	@Test(priority =3)
	public void userLogin() {
		oTest=oExtent.createTest("Login and Logout","User check the account login page and logout it.");
		oTest.assignCategory("Regression");
		oTest.info("User try Login the Bank Account");
		WebElement oName,oPass,oSub,oPro;
		String oVal;
		oTest.info("Enter the userName");
		oName = driver.findElement(By.xpath("//input[@id='username']"));
		oName.clear();
		oName.sendKeys("vinith");	
		oTest.info("Enter the password");
		oPass = driver.findElement(By.xpath("//input[@id='password']"));
		oPass.clear();
		oPass.sendKeys("Vinith@21");
		oTest.info("Click the Signin button");
		oSub = driver.findElement(By.xpath("//button[text()='Sign In']"));
		oSub.click();
		oTest.info("Click the Profile option");
		oPro=driver.findElement(By.xpath("//a[text()='Profile']"));
		if (oPro.isEnabled()) {
			oPro.click();
			oTest.info("Validate the user name");
			oVal=driver.findElement(By.xpath("//h5[@id='username']")).getText();
			if(oVal.contains("vinith")) {
				oTest.info("User Account Login succesfully");
			}
		}else {
			oTest.info("User not Loged In");
		}
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		oTest.info("User Logout the account");
	}
	@Test(priority =4)
	public void registerUser() {
		WebElement title,sex,mar;
		oTest=oExtent.createTest("Register new User","create the new account for then new user");
		oTest.assignCategory("Regression");
		oTest.info("User click the Register link");
		driver.findElement(By.xpath("//small[text()='Register For Account']")).click();
		oTest.info("User Enter the firstname");
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Arun");
		oTest.info("User select the Title");
		title=driver.findElement(By.xpath("//select[@id='title']"));
		Select oTitle=new Select(title);
		oTitle.selectByVisibleText("Mr");
		oTest.info("User select the Gender");
		sex=driver.findElement(By.xpath("//select[@id='sex']"));
		Select oSex=new Select(sex);
		oSex.selectByVisibleText("Male");
		oTest.info("User enter the age");
		driver.findElement(By.xpath("//input[@id='age']")).sendKeys("04/06/1990");
		oTest.info("User select the marital status");
		mar=driver.findElement(By.xpath("//select[@id='maritalStatus']"));
		Select oMar=new Select(mar);
		oMar.selectByVisibleText("Single");
		oTest.info("User Enter the userName");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Arun");
		oTest.info("User enter the Email id");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Arun90@gmail.com");
		oTest.info("User enter the password");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Arun@1234");
		oTest.info("User click the button");
		driver.findElement(By.xpath("//button[text()='Register']")).click();;
		
	}
	
	@AfterClass
	public void closeBrowser() {
		oExtent.flush();
		driver.quit();
	}

}
