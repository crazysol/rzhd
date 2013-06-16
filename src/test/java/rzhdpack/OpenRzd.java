package rzhdpack;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

public class OpenRzd {
	
	public static WebDriver driver = new FirefoxDriver();

	@Test
	public void openRzdRuAndVerifyItIsOpened() throws InterruptedException, IOException {
		Common common = new Common();
		driver.get("http://rzd.ru/");
		// List<WebElement> rzdLink = driver.findElements(By.xpath(
		// "//a[@href='http://rzd.ru']"));
		// assertEquals("Link wasn't found", 0, rzdLink.size());

		driver.findElement(By.cssSelector("#name0")).sendKeys("Москва");
		driver.findElement(By.cssSelector("#name1")).sendKeys("Харьков");
		driver.findElement(By.cssSelector("#date0")).clear();

		driver.findElement(By.cssSelector("#date0")).sendKeys("03.07.2013");
		driver.findElement(By.cssSelector("#Submit")).click();

		common.waitForElementPresent("//div[@id='Part0']//input[@name='car-type4']");

		 driver.findElement(By.xpath("//div[@id='Part0']//input[@name='car-type4']")).click();
		 driver.findElement(By.xpath("//div[@id='Part0']//input[@name='car-type5']")).click();
		 driver.findElement(By.xpath("//div[@id='Part0']//input[@name='car-type6']")).click();

		driver.findElement(
				By.xpath("//div[@id='Part0']//button[contains(@class,'btn-color-grey btn-icon')]"))
				.click();

		List<WebElement> trains = driver
				.findElements(By
						.xpath("//div[@class='pass_IU_TrainChoice__trainList']//div[@class='trslot trainBlock']"));
		
		FileWriter writer = new FileWriter("output.txt");
		for (WebElement train:trains)
		{
			String number = train.findElement(By.xpath(".//td[@class='pass_trListCol_2']//span[@class='train-num-0']")).getText();
			String from = train.findElement(By.xpath(".//td[@class='pass_trListCol_3']//span[@class='train-time']")).getText() +
					" " + train.findElement(By.xpath(".//td[@class='pass_trListCol_3']//b[@class='train-date']")).getText();
			
			String to = train.findElement(By.xpath(".//td[@class='pass_trListCol_7']//span[@class='train-time']")).getText() +
					" " + train.findElement(By.xpath(".//td[@class='pass_trListCol_7']//b[@class='train-date']")).getText();
			
			String seats = train.findElement(By.xpath(".//td[@class='pass_trListCol_8 free-seats']//b")).getText();
			
			String cost = train.findElement(By.xpath(".//td[@class='pass_trListCol_8 free-seats']//span")).getText();
			
			List<List<String>> trains_table = new ArrayList <List<String>> ();
			
			List<String> train_row = new ArrayList<String>();
			train_row.add(number);
			train_row.add(from);
			train_row.add(to);
			train_row.add(seats);
			train_row.add(cost);
			trains_table.add(train_row);
			
			 writer.write(number + " "+ from + " " + to + " " + seats + " " + cost + "\n"  );
			
			

			System.out.println(trains_table);
			// checks
			
			Thread.sleep(1000);
		}
		writer.close();
		// common.waitForAjax();
		Thread.sleep(10000);

	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
