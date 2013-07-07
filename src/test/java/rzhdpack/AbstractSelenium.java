package rzhdpack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class AbstractSelenium {
	
	public static WebDriver driver;
	
	@BeforeTest
	public void startFFAndOpenURL() throws InterruptedException {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	    driver.get("http://rzd.ru");
	    PageFactory.initElements(driver, this);

	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
