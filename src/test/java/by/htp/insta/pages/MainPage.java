package by.htp.insta.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.insta.dao.AccountDao;
import by.htp.insta.dao.imple.AccountDaoImple;
import by.htp.insta.entity.Account;

public class MainPage extends AbstractPage {
	private final String BASE_URL = "https://mail.ru/login";
	List<String> accountsThatNeedUnfollow = new ArrayList<>();

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

	@FindBy(xpath = "//*[@id='react-root']/section/nav/div[2]/div/div/div[3]/div/div[3]/a")
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

	@FindBy(xpath = "html/body/div[3]/div/div/div[1]/div[2]/button")
	private WebElement closeListWhoFollowUsButton;

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

	@FindBy(xpath = "html/body/div[3]/div/div/div[2]/ul/div/li[1]/div/div[1]/div/div[1]/a")
	private WebElement firstAccountInList;

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
		scrollAllWhoFollowUsList();
		String element;
		for (int i = 0; i < listAccountNameWhoFollow.size(); i++) {
			element = listAccountNameWhoFollow.get(i).getAttribute("title");
			System.out.println(element);
		}
	}

	public void checkAndChangeAccountsWhoFollow8DaysAgo() throws InterruptedException {
		AccountDao accountDao = new AccountDaoImple();

		List<Account> listCheckedTable = accountDao.selectCheckedTable();
		for (int i = 0; i < listCheckedTable.size(); i++) {
			System.out.println(listCheckedTable.get(i));// the list of all accounts from db with false and 2 weeks ago

		}
		Account account2 = new Account();
		String account3;
		for (int cntrx = 0; cntrx < listCheckedTable.size(); cntrx++) {
			account2 = listCheckedTable.get(cntrx);
			account3 = account2.getNikName();
			System.out.println(account2.getNikName());// Take one by nikName from listCheckedTable

			if (getAllAccountsNameWhoFollowUsWithoutSteps().contains(account3)) {
				System.out.println("------FollowBack------");
				accountDao.updateIfFollowBack(account2);
			} else {
				System.out.println("------NotFollowBack------");
				accountDao.updateIfNotFollowBack(account2);
				accountsThatNeedUnfollow.add(account2.getNikName());
			}

		}
		for (int b = 0; b < accountsThatNeedUnfollow.size(); b++) {
			System.out.println(accountsThatNeedUnfollow.get(b));// List of people who not follow back
		}

		System.out.println("---------------Work on people who not follow back----------------");

		closeListWhoFollowUsButton.click();
		profile.click();
		linkWeFollow.click();
		scrollAllWeFollowList();
		getAllAccountsNameWhoWeFollowWithoutSteps();

		System.out.println("-----------List of accounts that we need to unfollow--------");

		for (int b = 0; b < accountsThatNeedUnfollow.size(); b++) {
			System.out.println(accountsThatNeedUnfollow.get(b));
		}

		for (int i = 0; i < accountsThatNeedUnfollow.size(); i++) {
			String n = accountsThatNeedUnfollow.get(i);
			if (getAllAccountsNameWhoWeFollowWithoutSteps().contains(n)) {
				WebElement accountThatWeWantUnfollow = driver.findElement(By.linkText(n));
				accountThatWeWantUnfollow.click();
				unfollowAccount();
			}
		}
	}

	public void unfollowAccount() throws InterruptedException {
		followingButton.click();
		unfollowButton.click();
		Thread.sleep(500);
		profile.click();
		linkWeFollow.click();
	}

	public void scrollAllWeFollowList() throws InterruptedException {
		int count = 1;
		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		do {
			Thread.sleep(700);
			eventFiringWebDriver.executeScript("document.querySelector('.isgrP').scrollTop=20000");
			count++;
		} while (count < 100);
	}

	public void scrollAllWhoFollowUsList() throws InterruptedException {
		int count = 1;
		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		do {
			Thread.sleep(700);
			eventFiringWebDriver.executeScript("document.querySelector('.isgrP').scrollTop=20000");
			count++;
		} while (count < 100);
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
		// for (int c = 0; c < element2.size(); c++) {
		// System.out.println(element2.get(c));
		// }
		return (List<String>) element2;

	}

}
