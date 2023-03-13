package SNSSmokeGIT.SNSSmokeGITTests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateRaceMeetingTest {

	WebDriver driver;

	@BeforeTest
	public void loginSNS() throws IOException {

		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\pavanig\\Downloads\\chromedriver_win32\\chromedriver.exe");
		 * driver = new ChromeDriver();
		 */
		
		   System.setProperty("webdriver.chrome.driver", "C:\\Users\\pavanig\\Documents\\chromedriver_win32\\chromedriver.exe");
		   
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.setCapability("browserVersion", "111.0.5563.64");
			
			driver = new ChromeDriver(options);

		String username = "pgorrepati";
		String password = "testing";
		String url = "https://" + username + ":" + password + "@uatnztr.racingaustralia.horse/roarrs/FrameDefault.asp";
		driver.get(url);
		driver.manage().window().maximize();
		Assert.assertEquals(driver.getTitle(),
				"Welcome to the Single National System (SNS) – 'SNS Code of Conduct' (Last Update: October 2021)");
		driver.findElement(By.linkText("I Agree to the SNS Code of Conduct and Terms of Use")).click();

	}

	@Test
	public void navigatingToCreatMeetingPage() {
		driver.switchTo().frame("menu");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.linkText("RMM")).click();

		driver.findElement(By.linkText("Create Meetings")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

	}

	@Test
	public void createMeetingsPage() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Select regionDrp = new Select(driver.findElement(By.name("cmbState")));
		regionDrp.selectByVisibleText("NTH");

		Select regionAuthorityDrp = new Select(driver.findElement(By.name("cmbRegAuthority")));
		regionAuthorityDrp.deselectAll();
		regionAuthorityDrp.selectByVisibleText("NZ Northern");

		Select countryDrp = new Select(driver.findElement(By.name("cmbMeetingCategory")));
		countryDrp.selectByVisibleText("Country");

		driver.findElement(By.name("cmdSubmit")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\CreateMeetingPage.png");
		FileUtils.copyFile(src, trg);

	}

	@Test
	public void saveMeetingPage() throws IOException {
		WebElement dateTextField1 = driver.findElement(By.name("txtDate1"));
		dateTextField1.sendKeys(CommonVariables.futureDateString);

		Select clubDrp = new Select(driver.findElement(By.name("cmbClubs1")));
		clubDrp.selectByVisibleText("Helensville DRC 110"); // Helensville DRC 110,Matamata RC 202,Whakatane RC
															// 209,Taumarunui RC 205,Avondale JC 101

		Select venueDrp = new Select(driver.findElement(By.id("cmbVenue1")));
		venueDrp.selectByVisibleText("Cambridge"); // Cambridge,Matamata,Ruakaka,Taupo,Avondale

		Select tabDrp = new Select(driver.findElement(By.name("cmbTAB1")));
		tabDrp.selectByVisibleText("TAB");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\CreateMeetingPage1.png");
		FileUtils.copyFile(src, trg);

		Select dayNightDrp = new Select(driver.findElement(By.name("cmbDayNight1")));
		dayNightDrp.selectByVisibleText("Day");
		driver.findElement(By.name("cmdSave")).click();

		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src1 = ts1.getScreenshotAs(OutputType.FILE);
		File trg1 = new File(".\\screenshots\\saveMeetingPage.png");
		FileUtils.copyFile(src1, trg1);

	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
