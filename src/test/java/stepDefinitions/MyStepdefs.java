package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class MyStepdefs {

    private WebDriver driver;

    @Given("I have {} as a browser")
    public void iHaveAsABrowser(String browser) {

        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
            }
            case "edge" -> {
                EdgeOptions options2 = new EdgeOptions();
                options2.addArguments("--start-maximized");
                driver = new EdgeDriver(options2);
            }
            case "firefox" -> {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.get("file:///C:/Users/Alina/OneDrive/Dokumente/MVT25/Testautomation/Register/Register.html");
    }

    @Given("I enter a date of birth {int} {int} {int}")
    public void iEnterADateOfBirth(int day, int month, int year) {
        String dob = day + "/" + month + "/" + year;
        sendInput(driver, By.cssSelector("input[name='DateOfBirth']"), dob);
    }

    @And("Enter first name {}")
    public void enterFirstName(String first) {
        sendInput(driver, By.cssSelector("input#member_firstname"), first);
    }

    @And("Enter last name {}")
    public void enterLastName(String last) {
        sendInput(driver, By.cssSelector("input#member_lastname"), last);
    }

    @And("Enter email {} plus matching confirmation")
    public void enterEmailPlusConfirmation(String email) {
        sendInput(driver, By.cssSelector("input#member_emailaddress"), email);
        sendInput(driver, By.cssSelector("input#member_confirmemailaddress"), email);
    }

    @And("Enter password {} plus matching confirmation")
    public void enterPasswordPlusConfirmation(String password) {
        enterAPassword(password);
        enterPasswordConfirmation(password);
    }

    @And("I accept terms and conditions")
    public void iAcceptTermsAndConditions() {
        click(driver, By.cssSelector("label[for='sign_up_25'] > span.box"));
    }

    @And("I assure that I am an adult")
    public void iAssureThatIAmAdult() {
        click(driver, By.cssSelector("label[for='sign_up_26'] > span.box"));
    }

    @And("I agree on Code of Ethics and Conduct")
    public void iAgreeOnCodeOfEthicsAndConduct() {
        click(driver, By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box"));
    }

    @When("I submit the form")
    public void iSubmitTheForm() {
        click(driver, By.cssSelector("input[name='join']"));
    }

    @And("Add password {}")
    public void enterAPassword(String password) {
        sendInput(driver, By.cssSelector("input#signupunlicenced_password"), password);
    }

    @And("Enter wrong password confirmation {}")
    public void enterPasswordConfirmation(String password) {
        sendInput(driver, By.cssSelector("input#signupunlicenced_confirmpassword"), password);
        driver.findElement(By.cssSelector("body")).click();
    }

    @Then("I registered successfully")
    public void iRegisteredSuccessfully() {
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        String actual = driver.findElement(By.cssSelector("h2.text-center")).getText();

        assertEquals(expected, actual);
    }

    @Then("I should receive error message {string}")
    public void iShouldReceiveErrorMessage(String expected) {
        String actual = "";

        switch (expected) {
            case "Last Name is required" -> {
                actual = driver.findElement(By.cssSelector("span[data-valmsg-for='Surname']")).getText();
            }
            case "Password did not match" -> {
                actual = driver.findElement(By.cssSelector("span[for='signupunlicenced_confirmpassword']")).getText();
            }
            case "You must confirm that you have read and accepted our Terms and Conditions" -> {
                actual = driver.findElement(By.cssSelector("span[for='TermsAccept']")).getText();
            }
        }
        assertEquals(expected, actual);
    }


    private static void sendInput(WebDriver driver, By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public static void click(WebDriver driver, By by) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @After
    public void tearDown () {
        driver.close();
    }

}
