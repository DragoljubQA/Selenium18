import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;


public class Zadatak5 {
    public static void main(String[] args) {

        // Zadatak 5
// Testirati log in stranice https://practicetestautomation.com/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/");

        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();

        WebElement testLoginPageButton = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageButton.click();

        // Provera da li je id=username jedinstven na stranici
        /*List<WebElement> username = driver.findElements(By.id("username"));
        System.out.println("Lista: " + username.size());*/

        String validUsername = "student";
        String validPassword = "Password123";
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(validUsername);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(validPassword);

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        //-------------------------

        String expectedURL = "https://practicetestautomation.com/logged-in-successfully/";

        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        WebElement title = driver.findElement(By.className("post-title"));
        String actualTitle = title.getText();
        String expectedTitle = "Logged In Successfully";

        Assert.assertEquals(title.getText(), "Logged In Successfully");
        Assert.assertEquals(actualTitle, expectedTitle);

        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        //boolean logOutButtonIsPresent = logOutButton.isDisplayed();
        Assert.assertTrue(logOutButton.isDisplayed());

        WebElement welcomeText = driver.findElement(By.className("has-text-align-center"));

        Assert.assertEquals(welcomeText.getText(), "Congratulations "+validUsername+". You successfully logged in!");


    }
}
