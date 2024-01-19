import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.Keys.ENTER;

public class Selenium4 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        // Na ovaj nacin ne treba traziti i raditi sa elementima jer se dosta delova ponavlja

        /*driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea")).sendKeys("ITBootcamp");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea")).sendKeys(ENTER);
        driver.navigate().back();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea")).sendKeys("Wikipedia");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea")).sendKeys(ENTER);
*/
        // Ovo je mnogo bolji nacin koji se koristi u praksi da kada zelite da radite sa elementima, napravite WebElement i smestite lokator unutar
        /*WebElement searchBox = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea"));
        searchBox.sendKeys("ITBootcamp");
        searchBox.sendKeys(ENTER);
        driver.navigate().back();
        searchBox.sendKeys("Wikipedia");
        searchBox.sendKeys(ENTER);*/

        // LOKATORI / SELEKTORI

        // ID - Prvi po hijerarhiji, u idealnoj situaciji je jedinstven (ne mora biti jedinstven), najstabilniji za lokatore
        WebElement searchBoxByID = driver.findElement(By.id("APjFqb"));

        // Name
        WebElement searchBoxByName = driver.findElement(By.name("q"));

        // Class
        WebElement searchBoxByClass = driver.findElement(By.className("gLFyf"));

        // CSS Selector
        WebElement searchBoxByCSS = driver.findElement(By.cssSelector("textarea[type='search']"));

        // Primer kada koristimo CSS Selector preko klase koja unutar vrednosti ima 'space'
        WebElement searchBoxByFakeClass = driver.findElement(By.className("search box google"));
        WebElement searchBoxByFakeCSS = driver.findElement(By.cssSelector(".search.box.google"));

        // Relative Xpath
        WebElement searchBoxRelativeXpath = driver.findElement(By.xpath("//div[2]/div/textarea"));

        // Absolute Xpath - Poslednji po hijerarhiji, najnestabilniji, moze se koristiti na pocetku dok se kreiraju testovi ali zahteva azuriranje
        WebElement searchBoxAbsoluteXpath = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea"));


    }
}
