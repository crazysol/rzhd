package rzhdpack;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Common extends OpenRzd{

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
