package practice;

import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class MultipleElementDemo {

	@Test
	public void verifyInvalidError() {
		
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page pg = br.newPage();
		
		pg.navigate("https://www.bstackdemo.com/");
		
		//pg.locator("xpath=//textarea[@title='Search']").fill("qatestology java");
		
		Locator locators= pg.locator("xpath=//div[@class='filters']//span[@class='checkmark']");
		
		System.out.println(locators.count());
		
		for(int q=0; q<locators.count() ; q++)
		{
			String tempText = locators.nth(q).innerText();
			System.out.println(tempText);
			if(tempText.equals("Samsung"))
			{
				locators.nth(q).click();
				break;
			}
		}
		
		//locators.nth(0).click();
	
	}

}
