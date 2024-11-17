package practice;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Check1 {

	public static void main(String[] args) throws InterruptedException {
		
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(
				new LaunchOptions().setHeadless(false));
		Page pg= br.newPage();
		
		pg.navigate("https://test.techlift.in");
		
		Locator loginLink = pg.locator("//a[text()='Login']");
		loginLink.click();
		Thread.sleep(4000);
		//assertThat(pg).hasTitle("Techlift Test");
		pg.locator("#login_email").type("test@test.com");
		//pg.getByPlaceholder("Email address").type("test@test.com");
		Thread.sleep(4000);
		pg.locator("#login_password").type("wrongpass");
		Thread.sleep(4000);
		pg.locator("//button[@type='submit' and contains(@class,btn-login) and contains(text(),'Login')]").click();
		Thread.sleep(4000);
		String check = pg.locator("span[class *='indicator'][class *='red']").textContent();
		System.out.println(check);
		
		//assertThat(pg.locator("span[class *='indicator'][class *='red']")).hasText("hi..");
	
	}

}
