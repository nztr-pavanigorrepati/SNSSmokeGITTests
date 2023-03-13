package SNSSmokeGIT.SNSSmokeGITTests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class TrainerIdentificationTest {

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
	public void AddingTrainersandMutlipleHorses() throws IOException {
		driver.switchTo().frame("menu");

		driver.findElement(By.linkText("Trainer Identification")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		WebElement surName = driver.findElement(By.id("txtSurname"));
		surName.sendKeys("Forsman" + Keys.chord(Keys.SHIFT, "8")); // Andrew Forsman,Danny Frye

		driver.findElement(By.id("btnSearch")).click();

		driver.findElement(By.partialLinkText("Andrew Forsman")).click(); // Ben Foote

		driver.switchTo().frame("content");

		driver.findElement(By.xpath("//input[@name='chkPlusMeeting']")).click();

		WebElement dateTextField5 = driver.findElement(By.id("txtMeetDateFrom"));
		dateTextField5.sendKeys(CommonVariables.futureDateString);

		WebElement dateTextField6 = driver.findElement(By.id("txtMeetDateTo"));
		dateTextField6.sendKeys(CommonVariables.futureDateString);

		driver.findElement(By.id("btnNom")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		/*
		 * Select Meet1Drp = new Select(driver.findElement(By.name("lstMeet1")));
		 * Meet1Drp.selectByVisibleText("Fri 24Feb2023 Cambridge");
		 */
		String partialText = "Cambridge";
		for (int i = 1; i <= 6; i++) {
			String selectName = "lstMeet" + i;
			WebElement selectElement = driver.findElement(By.name(selectName));
			Select select = new Select(selectElement);
			List<WebElement> options = select.getOptions();
			for (WebElement option : options) {
				if (option.getText().trim().contains(partialText)) {
					select.selectByVisibleText(option.getText().trim());
					break;
				}
			}
		}

//		 Select Race1Drp = new Select(driver.findElement(By.name("lstRace1")));
//		 Race1Drp.selectByVisibleText("1 - NSC Hcp 0 - 100, 1200m, 12+6");

		String visibleText = "1 - NSC Hcp 0 - 100, 1200m, 12+6";
		for (int i = 1; i <= 6; i++) {
			String selectName = "lstRace" + i;
			WebElement selectElement = driver.findElement(By.name(selectName));
			Select select = new Select(selectElement);
			select.selectByVisibleText(visibleText);
		}
		String[] visibleTexts = { "Amazing Grace", "Come Together", "Dear John Lincoln", "Devastate", "Eagle Tarzan (AUS)",
				"Good Oil" }; //Abeyance
		for (int i = 1; i <= 6; i++) {
			String selectName = "lstHorse" + i;
			WebElement selectElement = driver.findElement(By.name(selectName));
			Select select = new Select(selectElement);
			select.selectByVisibleText(visibleTexts[i - 1]);
		}

		driver.findElement(By.name("cmdFinishNomsTr")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\TrainerIdentification1.png");
		FileUtils.copyFile(src, trg);

		driver.switchTo().frame("content");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement surName1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSurname")));
		surName1.sendKeys("Frye" + Keys.chord(Keys.SHIFT, "8"));

		driver.findElement(By.id("btnSearch")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement link = wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Danny Frye")));
		link.click();

		driver.switchTo().frame("content");
		driver.findElement(By.xpath("//input[@name='chkPlusMeeting']")).click();

		WebElement dateTextField7 = driver.findElement(By.id("txtMeetDateFrom"));
		dateTextField7.sendKeys(CommonVariables.futureDateString);

		WebElement dateTextField8 = driver.findElement(By.id("txtMeetDateTo"));
		dateTextField8.sendKeys(CommonVariables.futureDateString);

		driver.findElement(By.id("btnNom")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		/*
		 * Select Meet1Drp = new Select(driver.findElement(By.name("lstMeet1")));
		 * Meet1Drp.selectByVisibleText("Fri 24Feb2023 Cambridge");
		 */
		String partialText1 = "Cambridge";
		for (int i = 1; i <= 2; i++) {
			String selectName = "lstMeet" + i;
			WebElement selectElement = driver.findElement(By.name(selectName));
			Select select = new Select(selectElement);
			List<WebElement> options = select.getOptions();
			for (WebElement option : options) {
				if (option.getText().trim().contains(partialText1)) {
					select.selectByVisibleText(option.getText().trim());
					break;
				}
			}
		}

		/*
		 * Select Race1Drp = new Select(driver.findElement(By.name("lstRace1")));
		 * Race1Drp.selectByVisibleText("1 - NSC Hcp 0 - 100, 1200m, 12+6");
		 */

		String visibleText1 = "1 - NSC Hcp 0 - 100, 1200m, 12+6";
		for (int i = 1; i <= 2; i++) {
			String selectName = "lstRace" + i;
			WebElement selectElement = driver.findElement(By.name(selectName));
			Select select = new Select(selectElement);
			select.selectByVisibleText(visibleText1);
		}

		// Select Horse1Drp = new Select (driver.findElement(By.name("lstHorse1")));
		// Horse1Drp.selectByVisibleText("Aquattack"); //Billydude,I'm A
		// Tiger,Ingenious,Nortolose,Our Echo

		String[] visibleTexts1 = { "Our Echo", "Aquattack" };
		for (int i = 1; i <= 2; i++) {
			String selectName = "lstHorse" + i;
			WebElement selectElement = driver.findElement(By.name(selectName));
			Select select = new Select(selectElement);
			select.selectByVisibleText(visibleTexts1[i - 1]);
		}

		driver.findElement(By.name("cmdFinishNomsTr")).click();

		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src1 = ts1.getScreenshotAs(OutputType.FILE);
		File trg1 = new File(".\\screenshots\\TrainerIdentification2.png");
		FileUtils.copyFile(src1, trg1);

	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
