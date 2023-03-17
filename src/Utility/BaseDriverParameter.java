package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriverParameter {
    public WebDriver driver; //paralel test yapabilmemiz için static olan webdriver ı non-static yaptık
    public static WebDriverWait wait;
    @BeforeClass
    @Parameters("browserTipi") // xml den alacağımız parametre adı
    public void baslangicIslemleri(String browserTipi){ //burda tanımladığımız string java da yazdığımız istediğimiz adı verebiliriz

        Logger logger= Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        switch (browserTipi.toLowerCase())
        {
            case "firefox" :
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
                driver = new FirefoxDriver();
                System.out.println("firefox started");
                break;

            case "safari":
                driver=new SafariDriver();
                break;

            case "edge":
                driver=new EdgeDriver();
                break;

            default:
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();

        Duration dr=Duration.ofSeconds(30);
        driver.manage().timeouts().pageLoadTimeout(dr);
        driver.manage().timeouts().implicitlyWait(dr);
        //loginTest();

        wait=new WebDriverWait(driver, Duration.ofSeconds(30)); //gun03, class_02 de ekledik

        //mail : gokcetheqa@hotmil.com
        //şifre : gokcetheqa30k

    }
    void loginTest(){
        System.out.println("Login Test");

        driver.get("https://opencart.abstracta.us/index.php?route=account/login");

        WebElement eMail=driver.findElement(By.id("input-email"));
        eMail.sendKeys("gokcetheqa@hotmail.com");

        WebElement pWord=driver.findElement(By.id("input-password"));
        pWord.sendKeys("gokcetheqa30k");

        WebElement login=driver.findElement(By.cssSelector("[type='submit']"));
        login.click();

        Assert.assertTrue(driver.getTitle().equals("My Account"));
    }
    @AfterClass
    public void bitisIslemleri(){
        Tools.Bekle(3);
        driver.quit();

    }
}
