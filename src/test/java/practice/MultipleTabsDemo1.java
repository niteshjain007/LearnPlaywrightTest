package practice;

import java.util.List;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class MultipleTabsDemo1 {
	
	@Test
	public void openMultipleTabAndGotoFacebook()
	{
		Browser br = Playwright.create().chromium().launch(
				new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
		
		BrowserContext context = br.newContext();
		Page basePage = context.newPage();
		
		basePage.navigate("https://qatestology.in/");
		
		Locator allSocialLinks = basePage.locator("//div[@class='social-icons']//a");
		
		for(int v=0; v< allSocialLinks.count() ; v++)
		{
			allSocialLinks.nth(v).click();
		}
				
		List<Page> allWindows = context.pages();
		
		for(Page p : allWindows)
		{
			String title = p.title();
			System.out.println(title);
			if(title.contains("Testology India"))
			{
				p.bringToFront();
				p.locator("//input[@type='text' and @name='email']").fill("test@nitesh.com");
				p.waitForTimeout(4000);
				break;
			}
		}
		
		
		
	}

}
