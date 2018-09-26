package by.htp.insta.steps;

import org.openqa.selenium.WebDriver;

import by.htp.insta.driver.DriverSingleton;
import by.htp.insta.pages.LoginPage;
import by.htp.insta.pages.MainPage;
import by.htp.insta.utils.MyInstaPropertyManager;

public class Steps {
	private WebDriver driver;
	MyInstaPropertyManager conf = new MyInstaPropertyManager();
	LoginPage loginPage = new LoginPage(driver);
	MainPage mainPage = new MainPage(driver);

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}

	public void LogIn() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = new MainPage(driver);
		loginPage.openPage();
		loginPage.login(conf.getLogin(), conf.getPass());
		// mainPage.clickNotNowLink();
		mainPage.clickNotNowNotifications();
	}

	public void getAllAccountsNameWhoFollowUs() throws InterruptedException {
		MainPage mainPage = new MainPage(driver);
		mainPage.getAllAccountsNameWhoFollowUs();
	}

	public void changeAccountsThatWasChecked() throws InterruptedException {
		MainPage mainPage = new MainPage(driver);
		mainPage.checkAndChangeAccountsWhoFollow8DaysAgo();
	}

}
