package practice;

import java.nio.file.Path;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.FileChooser;

public class UploadFileDemo1 {
	
	@Test(enabled = false)
	public void uploadAfile()
	{
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page pg = br.newPage();
		
		pg.navigate("https://the-internet.herokuapp.com/upload");
		
		//pg.locator("id=file-upload").setInputFiles(Path.of("D:\\PlaywrightDec2024\\check.txt"));
		
		pg.waitForTimeout(5000);
		//below code will work if system accepts multiple file upload
		/*Path files[] = {
				Path.of("D:\\PlaywrightDec2024\\check.txt"),
				Path.of("D:\\PlaywrightDec2024\\check2.txt")
		};
		pg.locator("id=file-upload").setInputFiles(files);
		*/
		
		
		pg.locator("id=file-upload").setInputFiles(Path.of(System.getProperty("user.dir")+"//src//test//resources//fileToBeUploaded//check.txt"));
		
		
		
		
	}
	
	
	@Test(enabled = false)
	public void uploadmultipleFileUsingDragAndDrop()
	{
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page pg = br.newPage();
		
		pg.navigate("https://the-internet.herokuapp.com/upload");
		
		FileChooser filech = pg.waitForFileChooser(()->
				pg.locator("id=drag-drop-upload").click());
		// get filesToopload
		Path files[] = {
				Path.of("D:\\PlaywrightDec2024\\check.txt"),
				Path.of("D:\\PlaywrightDec2024\\check2.txt")
		};
		
		filech.setFiles(files);
		
		pg.waitForTimeout(5000);
	}

	
	@Test(enabled = true)
	public void alertHandle()
	{
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page pg = br.newPage();
		pg.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		// handleAlert();
		pg.onDialog(dialog -> {
			System.out.println(dialog.message());
			dialog.accept();
		});
		
		pg.locator("xpath=//button[text()='Click for JS Alert']").click();
	
		pg.waitForTimeout(5000);
	}

}
