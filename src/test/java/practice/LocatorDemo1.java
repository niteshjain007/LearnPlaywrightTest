package practice;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LocatorDemo1 {

	public static void main(String[] args) {
		
		//1. open browser
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false));
		Page pg = br.newPage();
		
		pg.navigate("https://bstackdemo.com/");
	
		assertThat(pg).hasTitle("StackDemo");
		
		pg.locator("text=Sign In").click();
		
		pg.locator("id=login-btn").click();
	
		
		assertThat(pg.locator("text=Invalid Username")).isVisible();

		
		
		
		
		pg.close();
		br.close();
		pw.close();
	
	}

}
