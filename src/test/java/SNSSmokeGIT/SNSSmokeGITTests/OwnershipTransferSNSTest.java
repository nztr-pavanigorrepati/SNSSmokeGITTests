package SNSSmokeGIT.SNSSmokeGITTests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

public class OwnershipTransferSNSTest {

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
	public void SearchingandTransferringHorse() {

		driver.switchTo().frame("menu");

		driver.findElement(By.linkText("ROR")).click();

		driver.findElement(By.xpath("(//a[normalize-space()='Horse'])[2]")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		// Searching the horse with Ha* and with status Active

		WebElement horseName = driver.findElement(By.id("horseName"));
		horseName.sendKeys("Ha" + Keys.chord(Keys.SHIFT, "8"));

		Select StatusDrp = new Select(driver.findElement(By.name("horseStatus")));
		StatusDrp.selectByVisibleText("Active");

		driver.findElement(By.id("btnSearch")).click();

		// Selecting the searched horse

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement selectinghorse = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='404749']")));
		selectinghorse.click();

		// To click on owners tab

		driver.findElement(By.xpath("//div[normalize-space()='Owners']")).click();

		// To click on Transfer button

		driver.switchTo().frame("ror_content_frame");

		WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement Transfer = wait0.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='btnTransfer']")));
		Transfer.click();

	}
	 
	@Test	
	public void TransferApplicationDetailsandAddressvalidation() {
		// Find the input field for the calendar widget
		WebElement dateReceived = driver.findElement(By.id("date_received"));

		// Get the current date
		Date currentDate = Calendar.getInstance().getTime();

		// Format the current date as a string in the format expected by the calendar
		// widget
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormat.format(currentDate);

		// Set the value of the input field to the current date
		dateReceived.sendKeys(dateString);

		// selecting date start

		// driver.findElement(By.id("date_start")).clear();

		WebElement dateStart = driver.findElement(By.id("date_start"));
		Date currentDate1 = Calendar.getInstance().getTime();

		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		String dateString1 = dateFormat1.format(currentDate1);

		dateStart.sendKeys(dateString1);

		// driver.findElement(By.xpath("//input[@value='RACING_MANAGER']")).click();

		driver.findElement(By.name("tax_receipt_to")).click();

		driver.findElement(By.id("horse_address")).sendKeys("Apartment 1001a 111 Dixon Street");

		driver.findElement(By.id("btnValidateLocation")).click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement AddressSearch = wait1.until(ExpectedConditions.elementToBeClickable(By.name("AddressResultGroup")));
		AddressSearch.click();

		// driver.findElement(By.name("AddressResultGroup")).click();

		driver.findElement(By.id("vlce_btnAddressSelectConfirm")).click();
	}
	
	@Test
	public void AddingRacingManagersandOwners() {
		driver.findElement(By.id("btnCopyBase")).click(); // copy existing horse

		// Changing the share percentage to existing owner
		WebElement sharePercent = driver.findElement(
				By.xpath("//tr[4]//td[@style='text-align:center']/input[@class='sns_track_value owner_percentage']"));
		sharePercent.clear();
		sharePercent.sendKeys("20");

		// deleting 2 old owners
		/*
		 * driver.findElement(By.
		 * xpath("//tr[@id='row_4']//img[@title='Remove Owner Details']")).click();
		 * driver.findElement(By.xpath("//div[@class='buttons']//div[@class='yes']")).
		 * click();
		 * 
		 * driver.findElement(By.
		 * xpath("//tr[@id='row_5']//img[@title='Remove Owner Details']")).click();
		 * driver.findElement(By.xpath("//div[@class='buttons']//div[@class='yes']")).
		 * click();
		 */

		try {
			driver.findElement(By.xpath("//tr[@id='row_4']//img[@title='Remove Owner Details']")).click();
			// driver.findElement(By.xpath("//div[@class='buttons']//div[@class='yes']")).click();
			driver.findElement(By.xpath("//div[normalize-space()='Yes']")).click();

			/*
			 * driver.findElement(By.
			 * xpath("//tr[@id='row_5']//img[@title='Remove Owner Details']")).click();
			 * driver.findElement(By.xpath("//div[@class='buttons']//div[@class='yes']")).
			 * click();
			 */

		} catch (NoSuchElementException e) {
			// The above 2 rows are not present so continuing with the other actions
			System.out.println("2 rows are not present,continuing with the other actions");
		}

		// Adding first owner

		driver.findElement(By.id("btnAddRow")).click();

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement AddingPersonID = wait2
				.until(ExpectedConditions.elementToBeClickable(By.id("vlce_txtSrchPerPersonID")));
		AddingPersonID.sendKeys("75683");

		driver.findElement(By.id("vlce_btnSearch")).click();

		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement SelectingPerson = wait3
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/span[@class='clickable'][1]")));
		SelectingPerson.click();

		// Adding the Added first person share percentage, Account number and Account
		// name

		WebDriverWait percent = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement sharePercent1 = percent.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//tr[5]//td[@style='text-align:center']/input[@class='sns_track_value owner_percentage']")));
		sharePercent1.sendKeys("5");

		WebElement accountNumber1 = driver.findElement(
				By.xpath("//tr[5]//td[@style='text-align:center']/input[@class='sns_track_value owner_eft_number']"));
		accountNumber1.sendKeys("00");

		WebElement accountName1 = driver
				.findElement(By.xpath("//tr[5]//td/input[@class='sns_track_value owner_eft_name']"));
		accountName1.sendKeys("NewAccount");

		driver.findElement(By.id("btnSaveOwnership")).click();
	}
	
	@Test
	public void AddingandAcceptingPayment() throws InterruptedException {
		WebDriverWait addPaymentWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement btnAddPayment = addPaymentWait
				.until(ExpectedConditions.elementToBeClickable(By.id("btnAddPayment")));
		btnAddPayment.click();

		WebDriverWait paymentWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement paymentTypeElement = paymentWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("payment_type")));
		Select paymentTypeDrp = new Select(paymentTypeElement);
		paymentTypeDrp.selectByValue("EFT");

		// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		/*
		 * Select PaymentTypeDrp = new
		 * Select(driver.findElement(By.id("payment_type")));
		 * PaymentTypeDrp.selectByValue("EFT");
		 */

		driver.findElement(By.id("btnSave")).click();

		Thread.sleep(1000);

		WebDriverWait acceptWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement btnAccept = acceptWait.until(ExpectedConditions.elementToBeClickable(By.id("btnAccept")));
		btnAccept.click();

		WebDriverWait confirmWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement btnConfrim = confirmWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Yes']")));
		btnConfrim.click();

		Thread.sleep(10000);

		WebDriverWait waitTitle = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement messageTitle = waitTitle.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Registrar of Racehorses']")));
		String messageText = messageTitle.getText();
		Assert.assertEquals(messageText, "Registrar of Racehorses");

		// driver.findElement(By.partialLinkText("Tax Receipt")).click();

		Thread.sleep(10000);
		// driver.navigate().refresh();

		//driver.switchTo().defaultContent();
		// driver.switchTo().defaultContent();
	}
	
	@Test
	public void BankAccountApproval() {
		driver.switchTo().frame("menu");

		driver.findElement(By.partialLinkText("SNS Finance Extra")).click();

		// Thread.sleep(20000);

		driver.findElement(By.linkText("Bank Account Approval")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame("content");

		// driver.findElement(By.name("DateFrom"));
		// driver.findElement(By.name("DateTo"));

		WebElement DateFrom = driver.findElement(By.name("DateFrom"));
		Date currentDate2 = Calendar.getInstance().getTime();

		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dateString2 = dateFormat2.format(currentDate2);

		DateFrom.sendKeys(dateString2);

		WebElement DateTo = driver.findElement(By.name("DateTo"));
		Date currentDate3 = Calendar.getInstance().getTime();

		DateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
		String dateString3 = dateFormat3.format(currentDate3);

		DateTo.sendKeys(dateString3);

		driver.findElement(By.id("ownerCode")).sendKeys("75683");

		driver.findElement(By.id("btnSearch")).click();

		driver.findElement(By.id("btnApprove")).click();

		WebDriverWait approveWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement approveConfrim = approveWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Yes']")));
		approveConfrim.click();	

	}
	
	@AfterTest
	public void tearDown() {

		driver.quit();

	}


}
