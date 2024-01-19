import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Selenium10 {

    // SVI testovi se izvrsavaju u JEDNOM browseru
    WebDriver driver;
    WebElement usernameField;
    WebElement passwordField;
    WebElement submitButton;
    String validUsername, validPassword, invalidUsername, loggedInURL, invalidPassword;
    WebDriverWait wait;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        // U ovoj klasi svi testovi se izvrsavaju u jednom browseru jer smo driver inicijalizovali u BeforeClass
        // To znaci da se inicijalizacija odradila samo jednom i samo na pocetku
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Implicit wait i explicit wait mora da se inicijalizuje nakon inicijalizacije drivera
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        validUsername = "student";
        invalidUsername = "student1";
        validPassword = "Password123";
        invalidPassword = "password";
        loggedInURL = "https://practicetestautomation.com/logged-in-successfully/";
    }

    @BeforeMethod
    public void pageSetUp() {
        // Da bismo sve radili u jednom browseru neophodno je da se u BeforeMethod metodi vracamo na pocetnu tacku
        // Nakon sto se svaki test zavrsi vraticemo se na ovaj URL
        driver.get("https://practicetestautomation.com/");
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();
        WebElement linkButton = driver.findElement(By.linkText("Test Login Page"));
        linkButton.click();

        // Dolazimo na login stranicu i zato mogu da nadjem ove elemente
        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.id("submit"));
        // Primetite da ovde nemamo element za Log out button
        // To je zato sto bi nam program pukao na toj liniji jer program ne moze da nadje taj element
    }

    @Test(priority = 10)
    public void userCanLogIn() {
        usernameField.sendKeys(validUsername);
        passwordField.sendKeys(validPassword);
        submitButton.click();

        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), loggedInURL);
        WebElement profileTitle = driver.findElement(By.className("post-title"));
        Assert.assertTrue(profileTitle.isDisplayed());
    }

    @Test(priority = 20)
    public void userCannotLogInWithInvalidUsername() throws InterruptedException {
        usernameField.sendKeys(invalidUsername);
        passwordField.sendKeys(validPassword);
        submitButton.click();

        Thread.sleep(1000);
        WebElement error = driver.findElement(By.id("error"));
        String errorMessage = error.getText();

        Assert.assertEquals(errorMessage, "Your username is invalid!");
        Assert.assertNotEquals(driver.getCurrentUrl(), loggedInURL);

    }

    @Test(priority = 30)
    public void userCannotLogInWithInvalidPassword() throws InterruptedException {
        usernameField.sendKeys(validUsername);
        passwordField.sendKeys(invalidPassword);
        submitButton.click();

        Thread.sleep(1000);
        WebElement error = driver.findElement(By.id("error"));
        String errorMessage = error.getText();

        Assert.assertEquals(errorMessage, "Your password is invalid!");
        Assert.assertNotEquals(driver.getCurrentUrl(), loggedInURL);

    }

    @AfterClass
    public void tearDown() {
        // driver.quit stavljamo u AfterClass jer zelimo da se zatvori kada se zavrse svi testovi
        // Posto radimo u jednom browseru, kada bismo quit stavili u AfterMethod onda bi nam program pukao
        // nakon sto se prvi test zavrsi
        driver.quit();
    }


}
