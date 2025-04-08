package practice;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPageTest {

	public static void main(String[] args) throws InterruptedException {
		
	//1. open browser
		Playwright pw = Playwright.create();
		//Browser br= 	pw.chromium().launch();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false));
		Page pg = br.newPage();
		
		// open website "https://test.techlift.in/"
		pg.navigate("https://test.techlift.in/");
		//click on login link
		String pg_title = pg.title();
		System.out.println(pg_title);
		
		Locator loginLink = pg.locator("//a[text()='Login']");
		loginLink.click();
		Thread.sleep(4000);
					
		// specify username/email
		pg.locator("#login_email").type("test@test.com");
		Thread.sleep(4000);
		//specify password
		pg.locator("#login_password").type("abcd");
		Thread.sleep(4000);
		// click on login button
		pg.locator("button[type='submit'][class *= 'btn-login']").click();
		Thread.sleep(4000);
		// validate the error msg
		
		String errorMsg = pg.locator("span[class *='indicator'][class *='red']").textContent();
		System.out.println(errorMsg);
			
	//	assertThat(pg.locator("span[class *='indicator'][class *='red']")).hasText("Invalid Login");
		
		pg.close();
		br.close();
		pw.close();
		
	}

}
