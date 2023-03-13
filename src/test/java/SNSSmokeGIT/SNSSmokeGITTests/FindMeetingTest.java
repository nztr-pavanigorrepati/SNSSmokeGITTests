package SNSSmokeGIT.SNSSmokeGITTests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindMeetingTest {

	WebDriver driver;

	@BeforeTest
	public void loginSNS() {
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
	public void navigatingToFindMeetingPage() {
		driver.switchTo().frame("menu");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.linkText("RMM")).click();

		driver.findElement(By.linkText("Find Meeting")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");
	}

	@Test
	public void searchFindMeeting() {
		try {
			driver.findElement(By.id("txtFromDate")).clear();
			WebElement dateTextField3 = driver.findElement(By.id("txtFromDate"));
			dateTextField3.sendKeys(CommonVariables.futureDateString);
			driver.findElement(By.id("txtToDate")).clear();
			WebElement dateTextField4 = driver.findElement(By.id("txtToDate"));
			dateTextField4.sendKeys(CommonVariables.futureDateString);
		} catch (Exception e) {
			// If meeting is not present search with other dates
			driver.findElement(By.id("txtFromDate")).clear();
			WebElement dateTextField3 = driver.findElement(By.id("txtFromDate"));
			dateTextField3.sendKeys(CommonVariables.anotherFutureDateString);
			driver.findElement(By.id("txtToDate")).clear();
			WebElement dateTextField4 = driver.findElement(By.id("txtToDate"));
			dateTextField4.sendKeys(CommonVariables.anotherFutureDateString);
		}

		driver.findElement(By.name("cmdSearch")).click();
	}

	@Test

	public void selcetMeeting() throws IOException {
		try {

			// driver.findElement(By.partialLinkText("Avondale")).click();
			// driver.findElement(By.partialLinkText("Taupo")).click();
			driver.findElement(By.partialLinkText("Cambridge")).click();

		} catch (NoSuchElementException e) {
			System.out.println("Element not found in current page, continuing with the action");
			// continue with the next action
		}

		driver.switchTo().frame("footer");
		driver.findElement(By.xpath("//strong[text()='Race List']")).click();
		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");
		driver.switchTo().frame("ContentDetails");
		driver.findElement(By.xpath("//font[text()='Add Race']")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\AddRacePage.png");
		FileUtils.copyFile(src, trg);
		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		Select raceTypeDrp = new Select(driver.findElement(By.id("cmbRaceType")));
		raceTypeDrp.selectByValue("1");

		Select calssDrp = new Select(driver.findElement(By.id("cmbClass")));
		calssDrp.selectByValue("126");

		Select ageDrp = new Select(driver.findElement(By.id("cmbAge")));
		ageDrp.selectByValue("32");

		Select sexDrp = new Select(driver.findElement(By.id("cmbSex")));
		sexDrp.selectByVisibleText("No Sex Restriction");
		sexDrp.selectByValue("0");

		Select weightDrp = new Select(driver.findElement(By.id("cmbWeight")));
		weightDrp.selectByVisibleText("Handicap");
		weightDrp.selectByValue("47");

		driver.findElement(By.name("txtDistance")).sendKeys("1200");

		driver.findElement(By.name("cmdCreate")).click();

		driver.findElement(By.id("txtFieldSize")).sendKeys("12");
		driver.findElement(By.id("txtEmergencySize")).sendKeys("6");

		Select ballotRuleDrp = new Select(driver.findElement(By.id("cmbBallotRule")));
		ballotRuleDrp.selectByVisibleText("NZ Maiden");

		driver.findElement(By.name("txtTotalPrize")).clear();
		driver.findElement(By.name("txtTotalPrize")).sendKeys("10000");

		driver.findElement(By.name("cmdSave")).click();

		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src1 = ts1.getScreenshotAs(OutputType.FILE);
		File trg1 = new File(".\\screenshots\\RaceDetailsPage.png");
		FileUtils.copyFile(src1, trg1);

	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
