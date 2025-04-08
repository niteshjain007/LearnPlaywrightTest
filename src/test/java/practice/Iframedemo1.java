package practice;

import java.util.List;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Iframedemo1 {

	@Test
	public void iframe_demo()
	{
		Browser br = Playwright.create().chromium().launch(
				new BrowserType.LaunchOptions().setHeadless(false));
		
		Page pg = br.newPage();
		
		pg.navigate("https://www.redbus.in/");
		
		pg.locator("//span[text()='Account']").click();
		pg.locator("//span[text()='Login/ Sign Up']").click();
		
		//pg.locator("//input[@id='mobileNoInp']").fill("123456789");
		
		
		/*List<Frame> allFramewsInMyCurrentPage = pg.frames();
		
		System.out.println("number of frames="+ allFramewsInMyCurrentPage.size());
		
		allFramewsInMyCurrentPage.get(7)
		.locator("//input[@id='mobileNoInp']").fill("123456789");*/
		
		
		FrameLocator myframe = pg.frameLocator("//iframe[@class='modalIframe']");
		myframe.locator("//input[@id='mobileNoInp']").fill("1233556");
		
		
		pg.waitForTimeout(4000);
	}
}
