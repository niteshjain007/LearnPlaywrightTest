package practice;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Multiple_browser_demo2 {
	@Test
	public void multipleLoginCheck()
	{
		Playwright pw = Playwright.create();
		//browser1
		Browser br1 = pw.chromium().launch(new LaunchOptions().setHeadless(false));
		//br1->context1
		BrowserContext context1 = br1.newContext();
		//  context1->page1
		Page pg1 = context1.newPage();
		
		
		//browser1
		Browser br2 = pw.chromium().launch(new LaunchOptions().setHeadless(false));
		//br1->context1
		BrowserContext context2 = br2.newContext();
		//context2->page2
		Page pg2 = context2.newPage();
		
		pg1.navigate("https://www.bstackdemo.com/");
		pg1.locator("text=Sign In").click();
		
		pg1.locator("div#username").click();
		pg1.locator("//div[text()='demouser']").click();
		
		pg1.locator("div#password").click();
		pg1.locator("//div[text()='testingisfun99']").click();
		
		pg1.locator("id=login-btn").click();
		pg1.waitForTimeout(4000);
		
		// page2 flow
		pg2.navigate("https://www.bstackdemo.com/");
		pg2.locator("text=Sign In").click();
		
		pg2.locator("div#username").click();
		pg2.locator("//div[text()='existing_orders_user']").click();
		
		pg2.locator("div#password").click();
		pg2.locator("//div[text()='testingisfun99']").click();
		
		pg2.locator("id=login-btn").click();
		
		pg2.waitForTimeout(4000);
	}

}
