
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class Zadatak4 {
    public static void main(String[] args) {

        //Zadatak 4
//Otici na Google
//Zatim ukucati "Wikipedia" u polje za pretragu
//Odraditi pretragu i otvoriti stranicu
//Na stranici Wikipedia pretraziti "Nikola Tesla"

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Wikipedia");

        List<WebElement> searchButton = driver.findElements(By.name("btnK"));
        searchButton.get(1).click();

        // Pogresan nacin
        //WebElement wikipediaLink = driver.findElement(By.className("LC20lb MBeuO DKV0Md"));

        WebElement wikipediaLink = driver.findElement(By.cssSelector(".LC20lb.MBeuO.DKV0Md"));

        wikipediaLink.click();

        WebElement wikipediaSearch = driver.findElement(By.id("searchInput"));

        wikipediaSearch.sendKeys("Nikola Tesla");

        //WebElement wikipediaButton = driver.findElement(By.cssSelector(".pure-button.pure-button-primary-progressive"));
        WebElement wikipediaButton = driver.findElement(By.cssSelector("button[type='submit']"));
        wikipediaButton.click();

        //--------------------------------

        // Testovi nemaju smisla ako se ne vrsi nikakva provera
        // Razlika izmedju testa i programa koji izvrsava neku akciju su provere, odnosno asertacije
        // Da bismo radili asertacije prvo nam je potrebno da ubacimo TestNG biblioteku u pom fajl

        // Testiramo da li je validan URL
        String expectedURL = "https://en.wikipedia.org/wiki/Nikola_Tesla";

        //Kada koristimo assertEquals - sa leve strane je REALAN rezultat, sa desne strane je OCEKIVAN rezultat
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        // Testiramo da li je naslov stranice "Nikola Tesla"
        WebElement pageTitle = driver.findElement(By.className("mw-page-title-main"));
        String pageTitleText = pageTitle.getText();

        String expectedTitle = "Nikola Tesla";

        Assert.assertEquals(pageTitleText, expectedTitle);

        // Testiramo da li postoji slika na stranici
        WebElement pageImage = driver.findElement(By.className("infobox-image"));

        Assert.assertTrue(pageImage.isDisplayed());

        driver.quit();

    }
}
