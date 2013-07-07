package pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractWebPage {

	public MainPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "#name0")
	WebElement arrTextBox;

	@FindBy(id = "name1")
	WebElement depTextBox;

	@FindBy(id = "date0")
	WebElement dateField;

	@FindBy(id = "Submit")
	WebElement findButton;

	private String arr;
	private String dep;
	private String date;

	public void fillFromToDateForm() throws IOException {
		this.readFromFile("config.xml");
		arrTextBox.sendKeys(arr);
		depTextBox.sendKeys(dep);
		dateField.clear();
		dateField.sendKeys(date);
		findButton.click();
	}

	public void readFromFile(String FileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FileName));
		arr = br.readLine();
		dep = br.readLine();
		date = br.readLine();
		br.close();
	}

}
