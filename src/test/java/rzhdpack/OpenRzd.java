package rzhdpack;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

public class OpenRzd {

	@Test
	public void openRzdRuAndVerifyItIsOpened() throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://rzd.ru/");
		List<WebElement> rzdLink = driver.findElements(By.xpath(
				"//a[@href='http://rzd.ru']"));
		assertEquals(0, rzdLink.size());
		driver.close();
	}

}
