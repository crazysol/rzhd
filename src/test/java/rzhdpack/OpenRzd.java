package rzhdpack;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


public class OpenRzd {

	private static WebDriver driver = new FirefoxDriver();
		
	@Test
	public void openRzdRuAndVerifyItIsOpened() throws InterruptedException {
		driver.get("http://rzd.ru/");
		// List<WebElement> rzdLink = driver.findElements(By.xpath(
			//	"//a[@href='http://rzd.ru']"));
		// assertEquals("Link wasn't found", 0, rzdLink.size());
		
		driver.findElement(By.cssSelector("#name0")).sendKeys("Москва");
		driver.findElement(By.cssSelector("#name1")).sendKeys("Харьков");
		driver.findElement(By.cssSelector("#date0")).clear();
		
		driver.findElement(By.cssSelector("#date0")).sendKeys("30.06.2013");
		driver.findElement(By.cssSelector("#Submit")).click();
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//div[@id='Part0']//input[@name='car-type4']")).click();
		driver.findElement(By.xpath("//div[@id='Part0']//input[@name='car-type5']")).click();
		driver.findElement(By.xpath("//div[@id='Part0']//input[@name='car-type6']")).click();
		
		driver.findElement(By.xpath("//div[@id='Part0']//button[contains(@class,'btn-color-grey btn-icon')]")).click();
		
		Thread.sleep(10000);
		
		
		
		
		
	}
	
	@AfterTest
	public void closeBrowser(){
		driver.close();
	}
	

}
