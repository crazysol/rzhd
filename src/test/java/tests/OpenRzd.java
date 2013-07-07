package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import pages.AvailableTrainsPage;
import pages.MainPage;
import rzhdpack.AbstractSelenium;

public class OpenRzd extends AbstractSelenium {

	@Test
	public void getTrains() throws InterruptedException,
			IOException {

		MainPage mainPage = new MainPage(driver);
		
		AvailableTrainsPage availableTrainsPage = mainPage
				.fillFromToDateFormAndSubmit();
		availableTrainsPage.uncheckCheckboxesAndClickRecalculate();
		availableTrainsPage.getResultsAndWriteToFile();

	}

}
