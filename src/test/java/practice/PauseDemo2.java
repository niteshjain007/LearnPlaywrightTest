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

public class PauseDemo2 {

	public static void main(String[] args) {
		
		//1. open browser
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false).setSlowMo(500));
		Page page = br.newPage();
		
		page.navigate("https://bstackdemo.com/");
		//page.pause();
		
		  page.getByText("OnePlus").click();
	      page.locator("[id=\"\\32 0\"]").getByText("Add to cart").click();
	      page.getByText("Checkout").click();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Select Username$"))).nth(2).click();
	      page.getByText("demouser", new Page.GetByTextOptions().setExact(true)).click();
	      page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Select Password$"))).nth(2).click();
	      page.getByText("testingisfun99", new Page.GetByTextOptions().setExact(true)).click();
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In")).click();
	      page.getByLabel("First Name").click();
	      page.getByLabel("First Name").fill("nitesh");
	      page.getByLabel("First Name").press("Tab");
	      page.getByLabel("Last Name").fill("test");
	      page.getByLabel("Last Name").press("Tab");
	      page.getByLabel("Address").fill("abcd");
	      page.getByLabel("State/Province").click();
	      page.getByLabel("State/Province").fill("dsef");
	      page.getByLabel("Postal Code").click();
	      page.getByLabel("Postal Code").fill("111000000");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
	      Download download = page.waitForDownload(() -> {
	        page.getByText("Download order receipt").click();
	      });
	      assertThat(page.getByText("Download order receipt")).isVisible();
	      
		
		page.close();
		br.close();
		pw.close();
	
	}

}
