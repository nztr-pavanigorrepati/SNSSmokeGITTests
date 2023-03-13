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

public class HandicappingTest {

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
	public void Assignhandicapping() throws IOException {
		driver.switchTo().frame("menu");

		driver.findElement(By.linkText("Handicapping")).click();
		driver.findElement(By.partialLinkText("Assign Handicappers")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		driver.findElement(By.id("txtFromDate")).clear();
		WebElement dateTextField15 = driver.findElement(By.id("txtFromDate"));
		dateTextField15.sendKeys(CommonVariables.futureDateString);
		driver.findElement(By.id("txtToDate")).clear();
		WebElement dateTextField16 = driver.findElement(By.id("txtToDate"));
		dateTextField16.sendKeys(CommonVariables.futureDateString);

		driver.findElement(By.name("cmdSearch")).click();

		try {
			WebElement meetingLink = driver.findElement(By.partialLinkText("Cambridge"));
			meetingLink.click();
			driver.findElement(By.xpath("//strong[normalize-space()='Edit Handicappers']")).click();
		} catch (NoSuchElementException e) {
			// The meeting link wasn't found, click on the Edit Handicappers button
			System.out.println("The Cambridge meeting link was not found.");
			driver.findElement(By.xpath("//strong[normalize-space()='Edit Handicappers']")).click();
		}

		Select handiCapperDrp = new Select(driver.findElement(By.name("lstHandicappers0")));
		handiCapperDrp.selectByVisibleText(" Abbott");

		Select hcpTemplateDrp = new Select(driver.findElement(By.name("lstHcpTemplate0")));
		hcpTemplateDrp.selectByVisibleText("BM 65");

		driver.findElement(By.name("cmdAssignHnd")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\AssignhandicappingPage.png");
		FileUtils.copyFile(src, trg);

		driver.switchTo().defaultContent();

	}

	@Test
	public void HandicapRace() throws IOException {

		driver.switchTo().frame("menu");
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.linkText("Handicapping")).click();
		driver.findElement(By.linkText("Handicapping")).click();

		driver.findElement(By.linkText("Handicap Race")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		driver.findElement(By.id("txtFromDate")).clear();
		WebElement dateTextField17 = driver.findElement(By.id("txtFromDate"));
		dateTextField17.sendKeys(CommonVariables.futureDateString);
		driver.findElement(By.id("txtToDate")).clear();
		WebElement dateTextField18 = driver.findElement(By.id("txtToDate"));
		dateTextField18.sendKeys(CommonVariables.futureDateString);

		Select handiCapperDrp1 = new Select(driver.findElement(By.name("lstHandicappers")));
		handiCapperDrp1.selectByVisibleText(" Abbott");

		driver.findElement(By.name("cmdSearch")).click();

		driver.findElement(By.xpath("//font[normalize-space()='1 - 0 - 100 Hcp']")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\HandicapRacePage.png");
		FileUtils.copyFile(src, trg);

		// driver.findElement(By.id("btnSetPreRaceRatingFromLastRating")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement FindRating = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("btnSetPreRaceRatingFromLastRating")));
		FindRating.click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement IntialiseWeights = wait1
				.until(ExpectedConditions.elementToBeClickable(By.id("btnInitialiseWeights")));
		IntialiseWeights.click();

		// driver.findElement(By.id("grid_grid_data_1_8")).sendKeys("45.0");

		/*
		 * WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(10)); //
		 * wait for up to 10 seconds WebElement weightInput =
		 * wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//input[@field='Weight']"))); weightInput.click(); weightInput.clear();
		 * weightInput.sendKeys("45.0");
		 */

		// driver.findElement(By.id("grid_grid_data_1_7")).sendKeys("45.0");
		// driver.findElement(By.xpath("//td[@class='w2ui-tb-caption']")).click();

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement CalculateBallot = wait2.until(ExpectedConditions.elementToBeClickable(By.id("btnCalculateBallot")));
		CalculateBallot.click();

		driver.switchTo().defaultContent();

		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src1 = ts1.getScreenshotAs(OutputType.FILE);
		File trg1 = new File(".\\screenshots\\HandicapRacePage1.png");
		FileUtils.copyFile(src1, trg1);
	}

	@Test
	public void ConfirmWeights() throws IOException {

		driver.switchTo().frame("menu");

		driver.findElement(By.linkText("Handicapping")).click();
		driver.findElement(By.linkText("Handicapping")).click();
		driver.findElement(By.partialLinkText("Confirm Weights")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		driver.findElement(By.id("txtFromDate")).clear();
		WebElement dateTextField19 = driver.findElement(By.id("txtFromDate"));
		dateTextField19.sendKeys(CommonVariables.futureDateString);
		driver.findElement(By.id("txtToDate")).clear();
		WebElement dateTextField20 = driver.findElement(By.id("txtToDate"));
		dateTextField20.sendKeys(CommonVariables.futureDateString);

		driver.findElement(By.name("cmdSearch")).click();

		// driver.findElement(By.partialLinkText("Cambridge")).click();

		try {
			WebElement meetingLink = driver.findElement(By.partialLinkText("Cambridge"));
			meetingLink.click();
			driver.findElement(By.xpath("//strong[text()='Edit Confirmations']")).click();
		} catch (NoSuchElementException e) {
			// The meeting link wasn't found, click on the Edit Handicappers button
			System.out.println("The Cambridge meeting link was not found.");
			driver.findElement(By.xpath("//strong[text()='Edit Confirmations']")).click();
		}

		// driver.findElement(By.xpath("//strong[text()='Edit
		// Confirmations']")).click();

		driver.findElement(By.name("chkConfirmWeight0")).click();

		// driver.findElement(By.xpath("//input[@value='Save']")).click();
		driver.findElement(By.xpath("(//input[@name='cmdConfirmWeight'])[1]")).click();

		driver.findElement(By.xpath("//strong[text()='Generate Products']")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\ConfirmWeightsPage.png");
		FileUtils.copyFile(src, trg);

	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
