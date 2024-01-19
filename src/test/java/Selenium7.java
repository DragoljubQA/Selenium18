import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium7 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://crop-circle.imageonline.co/#circlecropresult");

        //Lokaciju trazite tako sto kliknete desni klik na sliku gde se nalazi -> Properties -> Kopirate lokaciju
        //obavezno na kraju dodajte naziv slike + ekstenziju (tip fajla, u ovom slucaju je to png)
        String imageLocation = "C:\\Users\\drago\\Desktop\\selenium.png";

        WebElement button = driver.findElement(By.id("inputImage"));

        //Sliku uploadujete tako sto njenu lokaciju posaljete na dugme
        button.sendKeys(imageLocation);


    }
}
