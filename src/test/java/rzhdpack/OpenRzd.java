package rzhdpack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenRzd {
	
	@FindBy(css = "#name0")
	WebElement arrTextBox;
		
	@FindBy(id = "name1")
	WebElement depTextBox;
	
	@FindBy(id = "date0")
	WebElement dateField;
	
	@FindBy(id = "Submit")
	WebElement findButton;
	
	@FindBy(xpath = "//div[@id='Part0']//input[@name='car-type4']")
	WebElement stateroomCheckbox;
	
	@FindBy(xpath = "//div[@id='Part0']//input[@name='car-type5']")
	WebElement softStateroomCheckbox;
	
	@FindBy(xpath = "//div[@id='Part0']//input[@name='car-type6']")
	WebElement luxCheckbox;
	
	@FindBy(xpath = "//div[@id='Part0']//button[contains(@class,'btn-color-grey btn-icon')]")
	WebElement recalculateButton;
	
	@FindBy(xpath = "//div[@class='pass_IU_TrainChoice__trainList']//div[@class='trslot trainBlock']")
	List<WebElement> trains;
		
	public static WebDriver driver = new FirefoxDriver();
	
	private String arr;
	private String dep;
	private String date;
	private final String TRAIN_NUMBER_LOCATOR = 
			".//td[@class='pass_trListCol_2']//span[@class='train-num-0']";
	private final String TRAIN_FROM_TIME_LOCATOR = 
			".//td[@class='pass_trListCol_3']//span[@class='train-time']";
	private final String TRAIN_FROM_DATE_LOCATOR = 
			".//td[@class='pass_trListCol_3']//b[@class='train-date']";
	private final String TRAIN_TO_TIME_LOCATOR = 
			".//td[@class='pass_trListCol_7']//span[@class='train-time']";
	private final String TRAIN_TO_DATE_LOCATOR = 
			".//td[@class='pass_trListCol_7']//b[@class='train-date']";
	private final String SEATS_COUNT_LOCATOR = 
			".//td[@class='pass_trListCol_8 free-seats']//b";
	private final String TICKET_COST_LOCATOR = 
			".//td[@class='pass_trListCol_8 free-seats']//span";
	
	@BeforeTest
	public void startFFAndOpenURL() throws InterruptedException {

	    driver.get("http://rzd.ru");
	    PageFactory.initElements(driver, this);

	}
	
	@Test
	public void openRzdRuAndVerifyItIsOpened() throws InterruptedException, IOException {
		Common common = new Common();
		this.readFromFile("config.txt");
		
		arrTextBox.sendKeys(arr);
		depTextBox.sendKeys(dep);
		dateField.clear();
		dateField.sendKeys(date);
		findButton.click();

		common.waitForElementPresent("//div[@id='Part0']//input[@name='car-type4']");

		stateroomCheckbox.click();
		softStateroomCheckbox.click();
		luxCheckbox.click();
		recalculateButton.click();

		
		FileWriter writer = new FileWriter("output.txt");
		for (WebElement train:trains)
		{
			List<List<String>> trains_table = new ArrayList <List<String>>();
			List<String> train_row = new ArrayList<String>();
			
			String number = train.findElement(By.xpath(TRAIN_NUMBER_LOCATOR)).getText();
			String from = train.findElement(By.xpath(TRAIN_FROM_TIME_LOCATOR)).getText() +
					" " + train.findElement(By.xpath(TRAIN_FROM_DATE_LOCATOR)).getText();
			String to = train.findElement(By.xpath(TRAIN_TO_TIME_LOCATOR)).getText() +
					" " + train.findElement(By.xpath(TRAIN_TO_DATE_LOCATOR)).getText();
			String seats = train.findElement(By.xpath(SEATS_COUNT_LOCATOR)).getText();
			String cost = train.findElement(By.xpath(TICKET_COST_LOCATOR)).getText();
			
			train_row.add(number);
			train_row.add(from);
			train_row.add(to);
			train_row.add(seats);
			train_row.add(cost);
			trains_table.add(train_row);
			for(String trainOption : train_row){
			    writer.write(trainOption + " ");
			}
			writer.write("\n");
			System.out.println(trains_table);
			
		}
		writer.close();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

	public void readFromFile(String FileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(FileName));
		arr = br.readLine();
		dep = br.readLine();
		date = br.readLine();		
		br.close();
	}
	
}
