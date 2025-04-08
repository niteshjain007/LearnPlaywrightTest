package practice;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PageContextDemo1 {
	
	@Test
	public void workingWithNewTab()
	{
		Playwright pw = Playwright.create();
		
		Browser br = pw.chromium().launch(
				new LaunchOptions().setHeadless(false).setSlowMo(2000));
		
		
		BrowserContext context=	br.newContext();
	
		Page basePage = context.newPage();
		
		basePage.navigate("https://qatestology.in/");
		
		Page newPage = context.waitForPage(()->{
			basePage.locator("//a[text()='Selenium']").hover();
			basePage.locator("//a[text()='Selenium With Java']").click();
			
		});
		
		
		newPage.waitForTimeout(4000);
		
		
		boolean check= newPage.locator("//span[contains(text(),'First Selenium Code')]").isVisible();
		System.out.println(check);
		
		newPage.waitForTimeout(2000);
		basePage.bringToFront();
		basePage.waitForTimeout(2000);
		newPage.bringToFront();
		
	}

}
