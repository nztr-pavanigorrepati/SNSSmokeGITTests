package SNSSmokeGIT.SNSSmokeGITTests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NominationsTest {

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
	public void RaceMeetingNominations() {

		driver.switchTo().frame("menu");

		driver.findElement(By.linkText("Nominations")).click();

		driver.findElement(By.linkText("Race Meeting Nominations")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		driver.findElement(By.id("txtFromDate")).clear();
		WebElement dateTextField9 = driver.findElement(By.id("txtFromDate"));
		dateTextField9.sendKeys(CommonVariables.futureDateString);
		driver.findElement(By.id("txtToDate")).clear();
		WebElement dateTextField10 = driver.findElement(By.id("txtToDate"));
		dateTextField10.sendKeys(CommonVariables.futureDateString);

		driver.findElement(By.name("cmdSearch")).click();

		driver.findElement(By.partialLinkText("Cambridge")).click();

		WebElement messageElement = driver
				.findElement(By.xpath("//font[normalize-space()='Race Meeting Nominations']"));

		String messageText = messageElement.getText();

		Assert.assertEquals(messageText, "Race Meeting Nominations");

	}

	@Test
	public void ValidatingHorseslist() throws IOException

	{

		String[] horseNames = { "Amazing Grace", "Come Together", "Dear John Lincoln", "Devastate", "Eagle Tarzan (AUS)",
				"Good Oil", "Our Echo", "Aquattack" };
		String[] expectedStatus = { "Eligible", "Eligible", "Eligible", "Eligible", "Eligible", "Eligible", "Eligible",
				"Eligible" };

		for (int i = 0; i < horseNames.length; i++) {
			WebElement horseNameElement = driver.findElement(By.xpath("//font[text()='" + horseNames[i] + "']"));
			String horseNameText = horseNameElement.getText();
			Assert.assertEquals(horseNameText, horseNames[i]);

			WebElement statusElement = driver
					.findElement(By.xpath("//body[1]/table[3]/tbody[1]/tr[" + (i + 2) + "]/td[6]/font[1]"));
			String statusText = statusElement.getText().trim();
			Assert.assertEquals(statusText, expectedStatus[i]);

			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File trg = new File(".\\screenshots\\ValidatingHorseslist.png");
			FileUtils.copyFile(src, trg);

		}

		driver.switchTo().defaultContent();
	}

	// edit Meeting details page
	@Test

	public void EditMeetingDetailsPage() throws IOException {

		driver.switchTo().frame("menu");
		driver.findElement(By.linkText("RMM")).click();

		driver.findElement(By.linkText("Find Meeting")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");
		driver.findElement(By.id("txtFromDate")).clear();
		WebElement dateTextField11 = driver.findElement(By.id("txtFromDate"));
		dateTextField11.sendKeys(CommonVariables.futureDateString);
		driver.findElement(By.id("txtToDate")).clear();
		WebElement dateTextField12 = driver.findElement(By.id("txtToDate"));
		dateTextField12.sendKeys(CommonVariables.futureDateString);

		driver.findElement(By.name("cmdSearch")).click();

		try {
			WebElement meetingLink = driver.findElement(By.partialLinkText("Cambridge"));
			meetingLink.click();
			driver.switchTo().frame("ContentDetails");
			driver.findElement(By.xpath("//strong[normalize-space()='Edit Meeting Details']")).click();
		} catch (NoSuchElementException e) {
			// The meeting link wasn't found, click on the Edit Handicappers button
			System.out.println("The Cambridge meeting link was not found.");
			driver.switchTo().frame("ContentDetails");
			driver.findElement(By.xpath("//strong[normalize-space()='Edit Meeting Details']")).click();
		}

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		driver.findElement(By.name("txtNomsCloseDate")).clear();
		WebElement nominationsClose = driver.findElement(By.name("txtNomsCloseDate"));
		nominationsClose.sendKeys(CommonVariables.prevDate);

		driver.findElement(By.name("txtWeightsPub")).clear();
		WebElement weightsPublishing = driver.findElement(By.name("txtWeightsPub"));
		weightsPublishing.sendKeys(CommonVariables.prevDate);

		driver.findElement(By.name("txtAcceptCloseDate")).clear();
		WebElement acceptanceClose = driver.findElement(By.name("txtAcceptCloseDate"));
		acceptanceClose.sendKeys(CommonVariables.currentDate);
		
		driver.findElement(By.name("txtAcceptCloseTime")).clear();
		WebElement AcceptCloseTime = driver.findElement(By.name("txtAcceptCloseTime"));
		AcceptCloseTime.sendKeys("06:00");

		// Generate a random 4-digit number
		Random random = new Random();
		int randomNumber = random.nextInt(9000) + 1000;

		WebElement jetBetID = driver.findElement(By.name("txtJetBetID"));
		jetBetID.sendKeys(String.valueOf(randomNumber));

		WebElement saveButton = driver.findElement(By.name("cmdSave"));
		saveButton.click();

	/*	TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\SaveMeetingDetailsPage.png");
		FileUtils.copyFile(src, trg);*/

		// Waiting for the alert to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		// Get the text on the alert
		String alertText = alert.getText();
		System.out.println(alertText);

		// Click the OK button
		alert.accept();

		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src1 = ts1.getScreenshotAs(OutputType.FILE);
		File trg1 = new File(".\\screenshots\\EditMeetingDetailsPage.png");
		FileUtils.copyFile(src1, trg1);

		driver.switchTo().defaultContent();
	}

	@Test
	public void CloseRace() throws IOException {
		// close race

		driver.switchTo().frame("menu");

		driver.findElement(By.linkText("Nominations")).click();
		driver.findElement(By.linkText("Close Race")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		driver.findElement(By.id("txtFromDate")).clear();
		WebElement dateTextField13 = driver.findElement(By.id("txtFromDate"));
		dateTextField13.sendKeys(CommonVariables.futureDateString);
		driver.findElement(By.id("txtToDate")).clear();
		WebElement dateTextField14 = driver.findElement(By.id("txtToDate"));
		dateTextField14.sendKeys(CommonVariables.futureDateString);

		driver.findElement(By.name("cmdSearch")).click();

		driver.findElement(By.partialLinkText("Cambridge")).click();

		driver.findElement(By.xpath("//a[contains(text(),'Edit Close Race')]")).click();

		driver.findElement(By.name("chkCloseRace0")).click();

		driver.findElement(By.name("cmdSave")).click();

		driver.findElement(By.partialLinkText("Generate Product")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\CloseRacePage.png");
		FileUtils.copyFile(src, trg);

	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
