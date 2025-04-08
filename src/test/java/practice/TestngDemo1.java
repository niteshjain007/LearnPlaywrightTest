package practice;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class TestngDemo1 {
	
	@Test(priority = 1)
	public void errorCHeckOnInvalidLogin()
	{
		//1. open browser
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false));
		Page pg = br.newPage();		
		pg.navigate("https://bstackdemo.com/");
		
		pg.locator("text=Sign In").click();
		pg.locator("id=login-btn").click();
		assertThat(pg.locator("text=Invalid Username")).isVisible();
		pg.waitForTimeout(2000);
		pg.close();
		br.close();
		pw.close();
	}
	
	@Test(priority = 2, dependsOnMethods = "errorCHeckOnInvalidLogin")
	public void checkValidLogin()
	{
		 Page pg= null;
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false));
		pg = br.newPage();

		pg.navigate("https://bstackdemo.com/");
		
		pg.locator("text=Sign In").click();
		
		pg.locator("div#username").click();
		pg.locator("//div[text()='demouser']").click();
		
		pg.locator("div#password").click();
		pg.locator("//div[text()='testingisfun99']").click();
		pg.locator("id=login-btn").click();
		
		pg.waitForTimeout(2000);
		Assert.assertTrue(pg.locator("//span[@id='signin' and text()='Logout']").isVisible());
	}
	
	

}
