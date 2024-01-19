import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak6 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/");

        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();

        WebElement testLoginPageButton = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageButton.click();

        String validUsername = "student";
        String validPassword = "Password123";
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(validUsername);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(validPassword);

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        logOutButton.click();

        //-------------------

        Assert.assertNotEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");

        WebElement submitButtonAfterLogout = driver.findElement(By.id("submit"));
        Assert.assertTrue(submitButtonAfterLogout.isDisplayed());


    }
}
