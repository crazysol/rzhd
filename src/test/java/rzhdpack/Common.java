package rzhdpack;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common extends OpenRzd{
	
	public void waitForElementPresent(String xpath){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public void waitForAjax(){
		
		for(int seconds = 0; seconds <= 30 ; seconds++){
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			Object ajax = executor.executeScript("return jQuery.active");
			String ajaxValue = ajax.toString();
			 if (seconds >= 20){
	                break;
	            }
	            else if(ajaxValue.equals("0")){
	                break;
	            }
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
						
		}
	}
}
