package practice;

import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class TextInElementDemo {

	@Test
	public void verifyInvalidError() {
		
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false));
		Page pg = br.newPage();
		
		pg.navigate("https://www.bstackdemo.com/");
		pg.locator("id=signin").click();
		
		pg.locator("id=login-btn").click();
		
		String expectedError = "Invalid Username";
		String actualError = pg.locator("//h3[@class='api-error']").textContent();
		
		Assert.assertEquals(actualError,expectedError);
		
		String errormsg2 = pg.locator("//h3[@class='api-error']").innerText();
		System.out.println(errormsg2);
		
	String errorMsg3= (String)pg.evaluate("document.getElementsByClassName('api-error')[0].textContent");
		
	System.out.println(errorMsg3);
	
		PlaywrightAssertions.assertThat(pg.locator("//h3[@class='api-error']"))
							.containsText(expectedError);
		
		PlaywrightAssertions.assertThat(pg.locator("//h3[@class='api-error']"))
		.containsText(Pattern.compile("Invalid credential"));
	}

}
