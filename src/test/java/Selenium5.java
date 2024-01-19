import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Selenium5 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://wordpress.com/");

        //Sleepers - ceka dok se ne zavrsi cekanje (bilo 3, 5 ili 10 sekundi ali prosledjujete milisekunde) i ceka do kraja bilo da se element pojavi ili ne
        //Implicit wait - ceka odredjeno vreme dok ne baci "NoElementFound" Exception (ali ce cekati najvise onoliko vremena koliko prosledite - ovde je to 10 sekundi)
        //Explicit wait - ceka dok se ne ispuni zadat uslov (ali ce cekati najvise onoliko vremena koliko prosledite u objektu - ovde je to 10 sekundi)

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement logInButton = driver.findElement(By.linkText("Log In"));
        logInButton.click();

        //sleep ce uvek prvi put davati gresku jer nije dodat Exception na metodu
        //Potrebno je samo da kliknete na sleep i zatim na 'Add exception'
        //Klik na 'sleep' -> Alt + Enter -> klik na 'Add exception'
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

        WebElement usernameField = driver.findElement(By.id("usernameOrEmail"));
        usernameField.sendKeys("dragoljubqa");

        WebElement continueButton = driver.findElement(By.cssSelector("button[type='submit']"));
        continueButton.click();

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Swordfish123!@#");
        continueButton.click();

    }
}
