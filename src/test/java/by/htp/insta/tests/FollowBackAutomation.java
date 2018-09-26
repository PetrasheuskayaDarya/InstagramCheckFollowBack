package by.htp.insta.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.insta.steps.Steps;

public class FollowBackAutomation {
	private Steps steps;

	@BeforeMethod(description = "Init browser", groups = { "likeAutomation" })
	public void setUp() throws InterruptedException {
		steps = new Steps();
		steps.initBrowser();
		steps.LogIn();
		Thread.sleep(3000);
	}

	@Test(groups = { "FollowAutomation" })
	public void FollowAutomation() throws InterruptedException {
		
        steps.getAllAccountsNameWhoFollowUs();// 1 
		steps.changeAccountsThatWasChecked();// 2 for checking follow back or not
		//steps.unfollowAccount();

	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		steps.closeDriver();
	}

}
