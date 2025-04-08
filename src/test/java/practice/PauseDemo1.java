package practice;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

public class PauseDemo1 {

	public static void main(String[] args) {
		
		//1. open browser
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false).setSlowMo(500));
		Page page = br.newPage();
		
		page.navigate("https://bstackdemo.com/");
		page.pause();
	
		
		
		page.close();
		br.close();
		pw.close();
	
	}

}
