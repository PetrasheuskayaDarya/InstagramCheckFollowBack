package by.htp.insta.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.insta.dao.AccountDao;
import by.htp.insta.dao.imple.AccountDaoImple;
import by.htp.insta.entity.Account;
import by.htp.insta.steps.Steps;

public class MainPage extends AbstractPage {
	private final String BASE_URL = "https://mail.ru/login";

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

	@FindBy(xpath = "//button[text()='Not Now']")
	private WebElement NotNowNotifications;

	@FindBy(xpath = "//button[text()='Log Out']")
	private WebElement logOutButton;

	@FindBy(xpath = "//*[@id='react-root']/div/div[2]/a[2]")
	private WebElement LinkNotNow;

	@FindBy(className = "coreSpriteDesktopNavProfile")
	private WebElement profileUser;

	@FindBy(className = "//*[@id='react-root']/section/nav/div[2]/div/div/div[3]/div/div[3]/a")
	private WebElement profile;

	@FindBy(className = "dCJp8")
	private WebElement parameters;

	@FindBy(xpath = "//input[@type='text']")
	private WebElement searchField;

	@FindBy(xpath = "//button[contains(@class,'coreSpriteHeartOpen')]/span")
	private WebElement heartButton;

	@FindBy(xpath = "//button[contains(@class,'oW_lN')]")
	private WebElement followButton;

	@FindBy(xpath = "//div[@class='fuqBx']/a[1]")
	private WebElement firstItemFromDropDown;

	@FindBy(xpath = "//div[contains(@class,'Nnq7C')]/div")
	private List<WebElement> listOfAllPosts;

	@FindBy(xpath = "//a[contains (@class,'notranslate')]")
	private List<WebElement> listAccountNameWhoFollow;

	@FindBy(xpath = "//a[contains(@class,'nJAzx')]")
	private WebElement accountName;

	@FindBy(xpath = "//*[@id='react-root']/section/main/article/div[2]/div/div[1]/div[1]/a/div/div[2]")
	private WebElement firstRecentPost;

	@FindBy(xpath = "//a[text() = 'Next']")
	private WebElement nextPostButton;

	@FindBy(xpath = "//button[@class='ckWGn']")
	private WebElement closePostButton;

	@FindBy(xpath = "//*[@id='react-root']/section/main/div/header/section/ul/li[2]/a")
	private WebElement linkFollowers;

	@FindBy(xpath = "//*[@id='react-root']/section/main/div/header/section/ul/li[3]/a")
	private WebElement linkWeFollow;

	@FindBy(xpath = "//a[contains(@class,'coreSpriteDesktopNavProfile')]")
	private WebElement profileIcon;

	@FindBy(xpath = "html/body/div[3]/div/div/div[1]/div[2]/button")
	private WebElement closeButton;

	@FindBy(xpath = "//a[contains (@class,'notranslate')]")
	private List<WebElement> listAccountNameWhoWeFollow;

	@FindBy(xpath = "//*[@id='react-root']/section/main/div/header/section/div[1]/span/span[1]/button")
	private WebElement followingButton;

	@FindBy(xpath = "html/body/div[3]/div/div/div/div[3]/button[1]")
	private WebElement unfollowButton;

	public void clickOnParameters() {
		parameters.click();
	}

	public void clickNotNowNotifications() {
		NotNowNotifications.click();
	}

	public void clickOnLogOutButton() {
		logOutButton.click();
	}

	public void clickNotNowLink() {
		LinkNotNow.click();
	}

	public String getTextNotNowNotification() {
		String profile = NotNowNotifications.getText();
		return profile;
	}

	public void clickOnProfileUser() {
		profileUser.click();
	}

	public void waitElementNotNowPresent() {
		WebElement dinamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Not Now']")));
	}

	public void waitElementSearchFieldPresent() {
		WebElement dinamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']]")));
	}

	public WebElement waitForFirstItemInDropDownPresent() {
		WebElement dinamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='fuqBx']/a[1]")));
		return dinamicElement;
	}

	public void waitForPageLoads() throws InterruptedException {
		Thread.sleep(3000);
	}

	public void clickOnFirstItemInDropDown() {
		waitForFirstItemInDropDownPresent().click();
	}

	public void clickOnClosePostButton() {
		closePostButton.click();
	}

	public String getAccountName() {
		String name;
		name = accountName.getAttribute("title");
		System.out.println(name);
		return name;
	}

	public void getAllAccountsNameWhoFollowUs() throws InterruptedException {
		waitForPageLoads();
		profileIcon.click();
		linkFollowers.click();
		String element;
		for (int i = 0; i < listAccountNameWhoFollow.size(); i++) {
			element = listAccountNameWhoFollow.get(i).getAttribute("title");
			System.out.println(element);
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public void checkAndChangeAccountsWhoFollow8DaysAgo() throws InterruptedException {
		AccountDao accountDao = new AccountDaoImple();

		List<Account> listCheckedTable = accountDao.selectCheckedTable();
		for (int i = 0; i < listCheckedTable.size(); i++) {
			System.out.println(listCheckedTable.get(i));
			// listCheckedTable.get(0); //список с nikNames and false
		}
		Account account2 = new Account();
		String account3;
		for (int cntrx = 0; cntrx < listCheckedTable.size(); cntrx++) {
			account2 = listCheckedTable.get(cntrx);
			account3 = account2.getNikName();
			System.out.println(account2.getNikName());// берём поочерёдно каждый nikName из listCheckedTable

			
			if (getAllAccountsNameWhoFollowUsWithoutSteps().contains(account3)) {
				System.out.println("Подписался назад");
				accountDao.updateIfFollowBack(account2);
			} else {
				System.out.println("Не подписался назад");
				accountDao.updateIfNotFollowBack(account2);

				//Actions newTab = new Actions(driver);
				//driver.switchTo().window(newTab);
				// driver.get("https://www.instagram.com/daryaaa756/");

//				   driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
//				   driver.navigate().to("https://www.instagram.com/daryaaa756");

				// Steps steps = new Steps();
				// steps.initBrowser();
				// steps.LogIn();
				// Thread.sleep(3000);
				// profile.click();
//				   linkWeFollow.click();
				
				
//				if (getAllAccountsNameWhoWeFollowWithoutSteps().contains(account3)) {
//					WebElement accountThatWeWantUnfollow = driver.findElement(By.linkText(account3));
//					accountThatWeWantUnfollow.click();
//					followingButton.click();
//					unfollowButton.click();
//					driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
//					
//				}

			}
		}
	}

	public List<String> getAllAccountsNameWhoFollowUsWithoutSteps() {
		List<String> element2 = new ArrayList<String>();
		String element;
		for (int i = 0; i < listAccountNameWhoFollow.size(); i++) {
			element = listAccountNameWhoFollow.get(i).getAttribute("title");
			element2.add(element);
		}
		return (List<String>) element2;
	}

	public List<String> getAllAccountsNameWhoWeFollowWithoutSteps() {
		List<String> element2 = new ArrayList<String>();
		String element;
		for (int i = 0; i < listAccountNameWhoWeFollow.size(); i++) {
			element = listAccountNameWhoWeFollow.get(i).getAttribute("title");
			element2.add(element);
		}
		return (List<String>) element2;
	}

	public void unFollowUser() throws InterruptedException {
		Steps steps = new Steps();
		steps.initBrowser();
		steps.LogIn();
		Thread.sleep(3000);
		linkWeFollow.click();

	}
}
