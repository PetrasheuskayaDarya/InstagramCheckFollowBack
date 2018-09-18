package by.htp.insta.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingleton {
	private static WebDriver driver;
	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String CHROMEDRIVER_CHROMEDRIVER_EXE_PATH = "C:/Program Files/Drivers/chromedriver.exe";

	private DriverSingleton() {
	};

	public static WebDriver getDriver() {
		if (null == driver) {
			System.setProperty(WEBDRIVER_CHROME_DRIVER, CHROMEDRIVER_CHROMEDRIVER_EXE_PATH);
			driver = new ChromeDriver();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}

		return driver;
	}

	public static void closeDriver() {
		driver.quit();
		driver = null;
	}
}

