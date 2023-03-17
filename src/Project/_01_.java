package Project;

import Utility.BaseDriver;
import Utility.BaseDriverParameter;
import Utility.Tools;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class _01_ extends BaseDriverParameter {

    String email="3group11@hotmail.com";
//    @Test
//    void TestCase1(){
//
//        driver.get(" https://demo.nopcommerce.com/register?returnUrl=%2F ");
//
//        WebElement register= driver.findElement(By.linkText("Register"));
//        register.click();
//
//        WebElement female=driver.findElement(By.xpath("//label[text()='Female']"));
//        female.click();
//
//        WebElement firstname= driver.findElement(By.id("FirstName"));
//        firstname.sendKeys("Group11");
//
//        WebElement lastname= driver.findElement(By.id("LastName"));
//        lastname.sendKeys("Techno");
//
//        WebElement bDay= driver.findElement(By.name("DateOfBirthDay"));
//        Select selectDay=new Select(bDay);
//        selectDay.selectByValue("6");
//
//        WebElement bMonth= driver.findElement(By.name("DateOfBirthMonth"));
//        Select selectMonth=new Select(bMonth);
//        selectMonth.selectByValue("6");
//
//        WebElement bYear= driver.findElement(By.name("DateOfBirthYear"));
//        Select selectYear=new Select(bYear);
//        selectYear.selectByValue("2022");
//
//        WebElement eMail= driver.findElement(By.id("Email"));
//        eMail.sendKeys(email);
//
//        WebElement pWord= driver.findElement(By.id("Password"));
//        pWord.sendKeys("group11");
//
//        WebElement confirm= driver.findElement(By.id("ConfirmPassword"));
//        confirm.sendKeys("group11");
//
//        WebElement rButton= driver.findElement(By.id("register-button"));
//        rButton.click();
//
//        WebElement success=driver.findElement(By.cssSelector("[class='result']"));
//        Assert.assertTrue(success.getText().toLowerCase().contains("completed"));
//
//    }
    @Test
    void TestCase2(){

        driver.get(" https://demo.nopcommerce.com/register?returnUrl=%2F ");

        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();

        WebElement eMail=driver.findElement(By.id("Email"));
        eMail.sendKeys(email);

        WebElement pWord=driver.findElement(By.id("Password"));
        pWord.sendKeys("group11");

        //driver.switchTo().alert().accept();

        WebElement logButton=driver.findElement(By.cssSelector("[class='button-1 search-box-button']"));
        logButton.click();

        Tools.Bekle(100);
    }
}
