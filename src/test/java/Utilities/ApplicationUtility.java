package Utilities;
import java.time.*;
import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ApplicationUtility {
    //initializing webdriver
    public static WebDriver driver;

    //Browser initiation method
    public static void intiDriver(){
        OperationalUtility.LoadTestData();
        ChromeOptions options = new ChromeOptions();

        //Options Settings
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-error");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");

        //Setting up Webdriver
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver",
               // OperationalUtility.strChromedriverLocation);
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get(OperationalUtility.getProperty("URL"));


    }

    public static void explicitWait_Visibility(WebElement weWebElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(weWebElement));
    }
}
