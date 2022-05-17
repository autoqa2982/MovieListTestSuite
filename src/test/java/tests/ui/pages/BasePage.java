package tests.ui.pages;

import java.time.Duration;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean waitForAlert(long timeout) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(timeout))
			.ignoring(NoAlertPresentException.class)
			.ignoring(UnhandledAlertException.class)
			.until(ExpectedConditions.alertIsPresent());
			return true;
		}catch(Exception e) {}
		return false;
	}
	
	

}
