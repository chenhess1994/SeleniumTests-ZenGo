package basicweb;



import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumZengo {
	WebDriver driver;
	
	public void launch(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\chen5\\libs\\Selenium-jars\\chrome driver\\chromedriver.exe");
		driver =new ChromeDriver();
	}
	
	public String openPage() {	
		driver.get("https://zengo.com/");
		//We need to increase the window to find the button id.
		driver.manage().window().setSize(new Dimension(1920, 1080));
		
		String expectedUrl="https://zengo.com/";
		String actual=driver.getCurrentUrl();
		
		return expectedUrl.equals(actual)?"test 1 complete":"test 1 failed";	
	}
	
	public String goToFreeBitcoin(){
		driver.findElement(By.id("menu-item-6043")).click();
		
		String expectedUrl="https://zengo.com/free-bitcoin/";
		String actual=driver.getCurrentUrl();
		
		return expectedUrl.equals(actual)?"test 2 complete":"test 2 failed";
	}
	
	public String downloadZengo() {
		driver.findElement(By.linkText("Download ZenGo")).click();
		Sleeping("wait 3 second...");
		boolean isPopUpDisplay = driver.findElement(By.id("popup-model")).isDisplayed();
		
		String isdisplay ="pop-up is display " + isQRExist();
		String isNotdisplay ="pop-up not display"+ isQRExist();
		
		return isPopUpDisplay?isdisplay:isNotdisplay;
	}
	
	
	public String isQRExist() {
		
		String expected="qr code";
		String actual=driver.findElement(By.id("popup-model")).findElement(By.tagName("img")).getAttribute("alt");
		
		return expected.equals(actual)?"qr is displayed":"qr dosent display";
	}
	
	public void getBackHomePage() {
		System.out.println("back home page");
		driver.navigate().back();
	}
	
	public void closeBrowser() {
		System.out.println("quit web");
		driver.quit();
	}

	public void Sleeping(String msg) {
		try {
			System.out.println(msg);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		//objects
		SeleniumZengo obj = new SeleniumZengo();
		obj.launch();
		
		//Automation & tests
		//case 1
		System.out.println(obj.openPage());
		//case 2
		System.out.println(obj.goToFreeBitcoin());
		//case 3
		System.out.println(obj.downloadZengo());
		
		//finish process
		obj.getBackHomePage();
		obj.closeBrowser();
	}
		
}
