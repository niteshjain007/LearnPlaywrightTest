package practice;

import java.nio.file.Path;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.FileChooser;

public class PromptAlertDemo {
	
	@Test(enabled = true)
	public void alertHandle()
	{
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false).setSlowMo(2000));
		Page pg = br.newPage();
		pg.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		// handleAlert();
		pg.onDialog(dialog -> {
			String checkMsg = dialog.message();
			Assert.assertTrue(checkMsg.contains("I am a JS prompt"));
			dialog.accept("qatestology nitesh");
		});
		
		pg.locator("xpath=//button[text()='Click for JS Prompt']").click();
	
		pg.waitForTimeout(5000);
	}

}
