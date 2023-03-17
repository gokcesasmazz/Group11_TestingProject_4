package Project;

import Utility.BaseDriver;
import Utility.BaseDriverParameter;
import Utility.Tools;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _01_ extends BaseDriverParameter {

    String email="Zgroup11@hotmail.com";
    @Test (priority = 1)
    void TestCase1(){

        driver.get(" https://demo.nopcommerce.com/register?returnUrl=%2F ");

        WebElement register= driver.findElement(By.linkText("Register"));
        register.click();

        WebElement female=driver.findElement(By.xpath("//label[text()='Female']"));
        female.click();

        WebElement firstname= driver.findElement(By.id("FirstName"));
        firstname.sendKeys("Group11");

        WebElement lastname= driver.findElement(By.id("LastName"));
        lastname.sendKeys("Techno");

        WebElement bDay= driver.findElement(By.name("DateOfBirthDay"));
        Select selectDay=new Select(bDay);
        selectDay.selectByValue("6");

        WebElement bMonth= driver.findElement(By.name("DateOfBirthMonth"));
        Select selectMonth=new Select(bMonth);
        selectMonth.selectByValue("6");

        WebElement bYear= driver.findElement(By.name("DateOfBirthYear"));
        Select selectYear=new Select(bYear);
        selectYear.selectByValue("2022");

        WebElement eMail= driver.findElement(By.id("Email"));
        eMail.sendKeys(email);

        WebElement pWord= driver.findElement(By.id("Password"));
        pWord.sendKeys("group11");

        WebElement confirm= driver.findElement(By.id("ConfirmPassword"));
        confirm.sendKeys("group11");

        WebElement rButton= driver.findElement(By.id("register-button"));
        rButton.click();

        WebElement success=driver.findElement(By.cssSelector("[class='result']"));
        Assert.assertTrue(success.getText().toLowerCase().contains("completed"));

    }
    @Test (priority = 2, dependsOnMethods = {"TestCase1"})
    void TestCase2(){

        driver.get("https://demo.nopcommerce.com/");

        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();

        WebElement eMail=driver.findElement(By.id("Email"));
        eMail.sendKeys(email);

        WebElement pWord=driver.findElement(By.id("Password"));
        pWord.sendKeys("group11");

        WebElement logButton=driver.findElement(By.cssSelector("[class='button-1 login-button']"));
        logButton.click();

        WebElement logOut=driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOut.isDisplayed());
    }
    @Test(dataProvider = "veriler", priority = 5)
    void TestCase3(String giris, String sifre){
        driver.get("https://demo.nopcommerce.com/");

        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();

        Actions aksiyon=new Actions(driver);
        aksiyon.sendKeys(giris).build().perform();
        Tools.Bekle(2);
        aksiyon.sendKeys(Keys.TAB).build().perform();
        aksiyon.sendKeys(sifre).build().perform();
        aksiyon.sendKeys(Keys.ENTER).build().perform();

        WebElement logOut=driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOut.isDisplayed());
    }
    @DataProvider
    public Object[][] veriler(){
        Object[][] data={

                {"SAMET<3@gmail.com","11group"},
                {email,"group11"},
        };
        return data;
    }
    @Test (priority = 6)
    void TestCase4(){

        driver.get("https://demo.nopcommerce.com/");

        List<String> tabText=new ArrayList<>(Arrays.asList("Computers","Electronics","Apparel",
                "Digital downloads", "Books","Jewelry","Gift Cards"));

        List<WebElement> tabMenu=driver.findElements(By.xpath("(//ul[@class='top-menu notmobile']/li/a)"));

        for (int i = 0; i < tabMenu.size(); i++) {
            Assert.assertEquals(tabMenu.get(i).getText(),tabText.get(i));
        }
    }
    @Test (priority = 3, dependsOnMethods = {"TestCase2"})
    void TestCase5(){

        WebElement gift= driver.findElement(By.linkText("Gift Cards"));
        gift.click();

        List<WebElement>cards=driver.findElements(By.cssSelector("h2>[href*='physical-gift-card']"));
        int sayi = (int) (Math.random() * cards.size());
        cards.get(sayi).click();

        Actions aksiyon=new Actions(driver);

        WebElement recipient= driver.findElement(By.cssSelector("[class='recipient-name']"));
        recipient.sendKeys("İsmet Hoca");
        aksiyon.sendKeys(Keys.TAB).build().perform();
        aksiyon.sendKeys(Keys.TAB).build().perform();
        aksiyon.sendKeys("Bu projeyi çok sevdik").build().perform();
        aksiyon.sendKeys(Keys.TAB).build().perform();
        aksiyon.sendKeys(Keys.TAB).build().perform();
        aksiyon.sendKeys(Keys.ENTER).build().perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("[class='content']")).getText().toLowerCase().contains("has been added"));
    }
    @Test (priority = 4, dependsOnMethods = {"TestCase2"})
    void TestCase6(){

        driver.get("https://demo.nopcommerce.com/");

        Actions aksiyon=new Actions(driver);
        WebElement computers=driver.findElement(By.linkText("Computers"));
        aksiyon.moveToElement(computers).build().perform();

        WebElement desktops=driver.findElement(By.linkText("Desktops"));
        aksiyon.click(desktops).build().perform();

        WebElement computer=driver.findElement(By.linkText("Build your own computer"));
        computer.click();

        WebElement ram=driver.findElement(By.id("product_attribute_2"));
        List<WebElement> ramOptions=driver.findElements(By.cssSelector("[id='product_attribute_2']>option[value='0']~option"));
        int sayi = (int) (Math.random() * ramOptions.size());

        Tools.Bekle(2); //hocam öldürmeyecek kadar kullandık
        Select select=new Select(ram);
        select.selectByIndex(sayi);

        List<WebElement> hddOptions=driver.findElements(By.cssSelector("ul[data-attr='3'] input"));
        int sayi2 = (int) (Math.random() *hddOptions.size());
        hddOptions.get(sayi2).click();

        WebElement add=driver.findElement(By.id("add-to-cart-button-1"));
        wait.until(ExpectedConditions.elementToBeClickable(add));
        add.click();

        WebElement bekleme=wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='content']")));
        Assert.assertTrue(bekleme.getText().toLowerCase().contains("has been added"));

        WebElement logOut=driver.findElement(By.linkText("Log out"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", logOut);
    }
    @Test (priority = 7)
    @Parameters ("aranacakKelime")
    void TestCase7(String text){

        driver.get("https://demo.nopcommerce.com/");

        Actions aksiyon=new Actions(driver);

        WebElement search=driver.findElement(By.id("small-searchterms"));
        search.sendKeys(text);
        aksiyon.sendKeys(Keys.ENTER).build().perform();

        WebElement product=driver.findElement(By.linkText("Adobe Photoshop CS4"));
        Assert.assertEquals(product.getText(),"Adobe Photoshop CS4","Text uyuşmadı");

    }
    }
