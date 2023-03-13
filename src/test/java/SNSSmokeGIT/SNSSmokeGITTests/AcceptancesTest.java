package SNSSmokeGIT.SNSSmokeGITTests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

public class AcceptancesTest {

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
	public void AcceptanceCloseRace() throws IOException {

		 driver.switchTo().frame("menu");

         driver.findElement(By.linkText("Acceptances")).click();
         driver.findElement(By.linkText("Close Race")).click();

         driver.switchTo().defaultContent();

         driver.switchTo().frame("content");
         
         driver.findElement(By.id("txtFromDate")).clear();
     	 WebElement dateTextField21 = driver.findElement(By.id("txtFromDate"));
     	 dateTextField21.sendKeys(CommonVariables.futureDateString);
     	 driver.findElement(By.id("txtToDate")).clear();
     	 WebElement dateTextField22 = driver.findElement(By.id("txtToDate"));
     	 dateTextField22.sendKeys(CommonVariables.futureDateString);
     				
     	 driver.findElement(By.name("cmdSearch")).click();
     				
     	 driver.findElement(By.partialLinkText("Cambridge")).click();
     	 
     	 driver.findElement(By.name("chkCloseRace0")).click();
     	 
     	 driver.findElement(By.name("cmdSave")).click();
     	 
     	driver.switchTo().defaultContent(); 
     	
     	TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\AcceptanceCloseRace.png");
		FileUtils.copyFile(src, trg);

	}

	@Test
	public void AcceptanceProcessing() throws IOException {

		driver.switchTo().frame("menu");

        driver.findElement(By.linkText("Acceptances")).click();
        driver.findElement(By.linkText("Acceptances")).click();
        driver.findElement(By.linkText("Processing")).click();
        
        driver.switchTo().defaultContent();

        driver.switchTo().frame("content");
        
         driver.findElement(By.id("txtFromDate")).clear();
    	 WebElement dateTextField23 = driver.findElement(By.id("txtFromDate"));
    	 dateTextField23.sendKeys(CommonVariables.futureDateString);
    	 driver.findElement(By.id("txtToDate")).clear();
    	 WebElement dateTextField24 = driver.findElement(By.id("txtToDate"));
    	 dateTextField24.sendKeys(CommonVariables.futureDateString);
    	 
    	 driver.findElement(By.name("cmdSearch")).click();
			
     	 driver.findElement(By.partialLinkText("Cambridge")).click();
     	 
     	WebElement messageElement = driver.findElement(By.xpath("//font[text()='- Race Meeting Acceptances']"));
     	 
    	String messageText = messageElement.getText();

		// Assert that the extracted text is equal to the expected message
		Assert.assertEquals(messageText, "- Race Meeting Acceptances");
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\RaceMeetingAcceptances.png");
		FileUtils.copyFile(src, trg);
		
		driver.findElement(By.xpath("//a[normalize-space()='1 - 0 - 100 Hcp 1200m(noms: 1 - 0 - 100 Hcp)']")).click();
		
		WebElement proofSheet = driver.findElement(By.id("trainerTitle"));
		
		String messageText1 = proofSheet.getText();
		
		Assert.assertEquals(messageText1, "- Acceptance Proof Sheet");
		
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src1 = ts1.getScreenshotAs(OutputType.FILE);
		File trg1 = new File(".\\screenshots\\AcceptanceProofSheet.png");
		FileUtils.copyFile(src1, trg1);
		
		driver.findElement(By.id("btnEditProofSheet")).click();
		
		WebDriverWait wait0 =new WebDriverWait(driver,Duration.ofSeconds(20));
     	WebElement chkAutoBallotOrder = wait0.until(ExpectedConditions.elementToBeClickable(By.id("chkAutoBallotOrder")));
     	chkAutoBallotOrder.click(); 
		
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(20));
     	WebElement SaveProofSheet = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSaveProofSheet")));
     	SaveProofSheet.click(); 
		
    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
    	wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.className("light-box-content")));

    	WebElement submitButton = driver.findElement(By.id("btnSubmitFinalField"));
    	submitButton.click();

    	WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(30));
		Alert alert = wait2.until(ExpectedConditions.alertIsPresent());
		

		String alertText = alert.getText();
		System.out.println(alertText);
		alert.accept();

		 WebDriverWait wait3 = new WebDriverWait(driver,Duration.ofSeconds(30));
		 WebElement Completed = wait3.until(ExpectedConditions.elementToBeClickable(By.linkText("Completed")));
		 Completed.click();
		 
		driver.switchTo().defaultContent(); 

	}

	@Test
	public void AcceptanceFinalField() throws IOException  {

		driver.switchTo().frame("menu");

        driver.findElement(By.linkText("Acceptances")).click();
        driver.findElement(By.linkText("Acceptances")).click();
        driver.findElement(By.linkText("Final Fields")).click();
        
        driver.switchTo().defaultContent();

        driver.switchTo().frame("content");
        
         driver.findElement(By.id("txtFromDate")).clear();
    	 WebElement dateTextField25 = driver.findElement(By.id("txtFromDate"));
    	 dateTextField25.sendKeys(CommonVariables.futureDateString);
    	 driver.findElement(By.id("txtToDate")).clear();
    	 WebElement dateTextField26 = driver.findElement(By.id("txtToDate"));
    	 dateTextField26.sendKeys(CommonVariables.futureDateString);
    	 
    	 driver.findElement(By.name("cmdSearch")).click();
			
     	 driver.findElement(By.partialLinkText("Cambridge")).click();
     	 
     	WebElement messageElement1 = driver.findElement(By.xpath("//font[text()='- Acceptance Final Field Inquiry']"));
     	 
     	String messageText2 = messageElement1.getText();

		// Assert that the extracted text is equal to the expected message
		Assert.assertEquals(messageText2, "- Acceptance Final Field Inquiry"); 
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\screenshots\\TrainerIdentification1.png");
		FileUtils.copyFile(src, trg);
	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
