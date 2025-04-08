package practice;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class LocatorDemo2 {
	static Page pg= null;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		getDataFromCSV();
		// 1. open browser
		Playwright pw = Playwright.create();
		Browser br = pw.chromium().launch(new LaunchOptions().setHeadless(false));
		pg = br.newPage();

		pg.navigate("https://bstackdemo.com/");
		
		pg.locator("text=Sign In").click();
		
		pg.locator("div#username").click();
		pg.locator("//div[text()='demouser']").click();
		
		pg.locator("div#password").click();
		pg.locator("//div[text()='testingisfun99']").click();
		pg.locator("id=login-btn").click();
		
		// selecting product = add to cart
		
		selectPhoneAndAddToCart("iPhone XS Max");
		    
		// checkout click
		pg.locator("//div[@class='buy-btn' and text()='Checkout']").click();
		//shipping address for fill
		fillShippingAddress();
		
		//assertion to validate order summary is visible or not
		assertThat(pg.locator("text=Order Summary")).isVisible();
		
		//assert download receipt is present or not
		assertThat(pg.locator("//a[@id='downloadpdf' and text()='Download order receipt']")).isVisible();

		// click on download receipt
		pg.locator("//a[@id='downloadpdf' and text()='Download order receipt']").click();
		
		
	}
	
	
	private static void fillShippingAddress() {
		pg.locator("id=firstNameInput").fill("testname");
		pg.waitForTimeout(1000);
		pg.locator("id=lastNameInput").fill("lastname");
		pg.locator("id=addressLine1Input").fill("test_address");
		pg.waitForTimeout(1000);
		pg.locator("id=provinceInput").fill("AU");
		pg.locator("id=postCodeInput").fill("0999");
		pg.waitForTimeout(4000);
		pg.locator("id=checkout-shipping-continue").click();
		
	}

	/*private static void fillShippingAddress() {
		pg.locator("id=firstNameInput").fill(records.get(0).get(0));
		pg.waitForTimeout(1000);
		pg.locator("id=lastNameInput").fill(records.get(0).get(1));
		pg.locator("id=addressLine1Input").fill(records.get(0).get(2));
		pg.waitForTimeout(1000);
		pg.locator("id=provinceInput").fill(records.get(0).get(3));
		pg.locator("id=postCodeInput").fill(records.get(0).get(4));
		pg.waitForTimeout(4000);
		pg.locator("id=checkout-shipping-continue").click();
	}*/

	private static void selectPhoneAndAddToCart(String phoneName) {
	
		String addtoCartForPhone = "//p[text()='"+phoneName+"']/ancestor::div[@class='shelf-item']//div[text()='Add to cart']";
		pg.locator(addtoCartForPhone).click();
	}
	
	static List<List<String>> records = new ArrayList<>();
	
	public static void getDataFromCSV() throws FileNotFoundException, IOException
	{
		
		BufferedReader br = new BufferedReader(new FileReader("testData.csv"));
		    String line;
		    while ((line = br.readLine()) != null) {
		    	System.out.println(line);
		        String[] values = line.split(",");
		        records.add(Arrays.asList(values));
		    }
		br.close();
		}
	

}
