package by.htp.insta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
	private final String BASE_URL = "https://www.instagram.com/accounts/login";

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
	}

	@FindBy(name = "username")
	private WebElement loginField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginButton;

	@FindBy(xpath = "//*[@id='react-root']/section/main/div/article/div/div[2]/p/a")
	private WebElement singUpLink;

	@FindBy(id = "slfErrorAlert")
	private WebElement alertMessage;

	@FindBy(linkText = "Log in")
	private WebElement loginLink;

	@FindBy(xpath = "//*[@id=\'react-root\']/div/div[2]/a[2]")
	private WebElement LinkNotNow;

	public void inputLogin(String login) {
		loginField.sendKeys(login);
	}

	public void inputPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickLogInButton() {
		loginButton.click();
	}

	public void login(String login, String password) throws InterruptedException {
		loginField.sendKeys(login);
		passwordField.sendKeys(password);
		Thread.sleep(3000);
		loginButton.click();
	}

	public String getTextSignUpLink() {
		String SingUp = singUpLink.getText();
		return SingUp;
	}

	public void clickLoginLink() {
		loginLink.click();
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

}

