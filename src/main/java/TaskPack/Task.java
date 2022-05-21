package TaskPack;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Task {
	
	String url ="https://osa-web.t-cg.co.uk/qatest";
	WebDriver driver;
	String headerSec ;
	String searchword = "B16 8PE";

	/*
	 *  To initialize the chromedriver and to open the url
	 * please check the drivers before you run this the chromdriver version should be match with our browser
	 * 
	 */
	@BeforeTest
	public  void inti() {
		
		System.setProperty("webdriver.chrome.driver", "../Iris/src/test/java/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
	}
	
	/* 
	 * Search method is used to search for "B16 8PE" and to scroll down the page to open contact group
	 */
	
	@Test(priority=1)
	public void search()
	{
		driver.findElement(By.xpath("//*[@placeholder ='Type a postcode here']")).sendKeys(searchword);
		driver.findElement(By.xpath("//*[@id='searchPostcodeButton']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement Element = driver.findElement(By.xpath("((//Strong[text()='Contact Group'])[1])/parent::h2/parent::li"));	
        js.executeScript("arguments[0].scrollIntoView();", Element);
		 System.out.println("scrolling down");
		driver.findElement(By.xpath("//Strong[text()='Contact Group']/parent::h2/following-sibling::div/p[text()='Active']")).click();
	}
	
		/*
		 * to perform the operations for calling headersectinvaaa() method and navigating back 
		 * And again performing the headerSectionva() to validate our scenario
		 */
	    @Test(priority=2)
		public void validate()
		{
	    	
	    	headerSectionVa();
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			headerSectionVa();

		}
	    
	    /*
	     * Used to validate whether the news section is loaded or not
	     */
	    public void headerSectionVa() {
	    	
	    	WebDriverWait explwait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    	explwait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
	    	
			headerSec =driver.findElement(By.tagName("h1")).getText();
			System.out.println(headerSec);
			Assert.assertEquals(headerSec,"News");
	    }
}
