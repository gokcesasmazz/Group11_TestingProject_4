package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {
    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeClass
    public void baslangicIslemleri(){ //testNG public yazmayı zorunlu kılmıyor fakat extend aldığımız ve diğer package lerde kullanabilmemiz
                                      //için public yaptık. yoksa base driver extend edilir fakat çalışmazdı

        Logger logger= Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //driver=new ChromeDriver();

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
