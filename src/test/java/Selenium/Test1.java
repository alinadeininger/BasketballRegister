package Selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Test1 {

    @Test
    public void registerSuccessfully() {
        WebDriver driver = new ChromeDriver();

        driver.get("file:///C:/Users/Alina/OneDrive/Dokumente/MVT25/Testautomation/Register/Register.html");
        driver.manage().window().maximize();

        sendInput(driver, By.cssSelector("input[name='DateOfBirth']"), "12/12/2000");

        sendInput(driver, By.cssSelector("input#member_firstname"), "Alina");

        sendInput(driver, By.cssSelector("input#member_lastname"), "Deininger");

        sendInput(driver, By.cssSelector("input#member_emailaddress"), "aldei@gmail.com");

        sendInput(driver, By.cssSelector("input#member_confirmemailaddress"), "aldei@gmail.com");

        sendInput(driver, By.cssSelector("input#signupunlicenced_password"), "pannkakor123");

        sendInput(driver, By.cssSelector("input#signupunlicenced_confirmpassword"), "pannkakor123");

        click(driver, By.cssSelector("label[for='sign_up_25'] > span.box"));

        click(driver, By.cssSelector("label[for='sign_up_26'] > span.box"));

        click(driver, By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box"));

        click(driver, By.cssSelector("input[name='join']"));

        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";

        String actual = driver.findElement(By.cssSelector("h2.text-center")).getText();

        assertEquals(expected, actual);

    }

    @Test
    public void missingLastName(){
        WebDriver driver = new ChromeDriver();

        driver.get("file:///C:/Users/Alina/OneDrive/Dokumente/MVT25/Testautomation/Register/Register.html");
        driver.manage().window().maximize();

        sendInput(driver, By.cssSelector("input[name='DateOfBirth']"), "12/12/2000");

        sendInput(driver, By.cssSelector("input#member_firstname"), "Alina");


        sendInput(driver, By.cssSelector("input#member_emailaddress"), "aldei@gmail.com");

        sendInput(driver, By.cssSelector("input#member_confirmemailaddress"), "aldei@gmail.com");

        sendInput(driver, By.cssSelector("input#signupunlicenced_password"), "pannkakor123");

        sendInput(driver, By.cssSelector("input#signupunlicenced_confirmpassword"), "pannkakor123");

        click(driver, By.cssSelector("label[for='sign_up_25'] > span.box"));

        click(driver, By.cssSelector("label[for='sign_up_26'] > span.box"));

        click(driver, By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box"));

        click(driver, By.cssSelector("input[name='join']"));


        String expected = "Last Name is required";

        String actual = driver.findElement(By.cssSelector("span[data-valmsg-for='Surname']")).getText();

        assertEquals(expected, actual);

    }

    @Test
    public void passwordNotMatching() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("file:///C:/Users/Alina/OneDrive/Dokumente/MVT25/Testautomation/Register/Register.html");
        driver.manage().window().maximize();

        sendInput(driver, By.cssSelector("input[name='DateOfBirth']"), "12/12/2000");

        sendInput(driver, By.cssSelector("input#member_firstname"), "Alina");

        sendInput(driver, By.cssSelector("input#member_lastname"), "Deininger");

        sendInput(driver, By.cssSelector("input#member_emailaddress"), "aldei@gmail.com");

        sendInput(driver, By.cssSelector("input#member_confirmemailaddress"), "aldei@gmail.com");

        sendInput(driver, By.cssSelector("input#signupunlicenced_password"), "pannkakor123");

        sendInput(driver, By.cssSelector("input#signupunlicenced_confirmpassword"), "pannkakor1234");

        driver.findElement(By.cssSelector("body")).click(); //click somewhere to get out of the input field password

        click(driver, By.cssSelector("label[for='sign_up_25'] > span.box"));

        click(driver, By.cssSelector("label[for='sign_up_26'] > span.box"));

        click(driver, By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box"));

        click(driver, By.cssSelector("input[name='join']"));

        String expected = "Password did not match";

        String actual = driver.findElement(By.cssSelector("span[for='signupunlicenced_confirmpassword']")).getText();

        assertEquals(expected, actual);
    }

    @Test
    public void termsAndConditionsUnchecked(){
        WebDriver driver = new ChromeDriver();

        driver.get("file:///C:/Users/Alina/OneDrive/Dokumente/MVT25/Testautomation/Register/Register.html");
        driver.manage().window().maximize();

        sendInput(driver, By.cssSelector("input[name='DateOfBirth']"), "12/12/2000");

        sendInput(driver, By.cssSelector("input#member_firstname"), "Alina");

        sendInput(driver, By.cssSelector("input#member_lastname"), "Deininger");

        sendInput(driver, By.cssSelector("input#member_emailaddress"), "aldei@gmail.com");

        sendInput(driver, By.cssSelector("input#member_confirmemailaddress"), "aldei@gmail.com");

        sendInput(driver, By.cssSelector("input#signupunlicenced_password"), "pannkakor123");

        sendInput(driver, By.cssSelector("input#signupunlicenced_confirmpassword"), "pannkakor123");

        click(driver, By.cssSelector("label[for='sign_up_26'] > span.box"));

        click(driver, By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box"));

        click(driver, By.cssSelector("input[name='join']"));

        String expected = "You must confirm that you have read and accepted our Terms and Conditions";

        String actual = driver.findElement(By.cssSelector("span[for='TermsAccept']")).getText();

        assertEquals(expected, actual);

    }

    private static void sendInput(WebDriver driver, By locator, String text) {
       driver.findElement(locator).sendKeys(text);


    }

    public static void click(WebDriver driver, By by) {

        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(by));

        driver.findElement(by).click();


    }
}
