import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Selenium2 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.google.com/");

        //Da bismo otvorili nove tabove moramo da pozovemo JavascriptExecutor kako bi mogla da se izvrsi skripta za otvaranje novih tabova
        JavascriptExecutor js = (JavascriptExecutor) driver; //Kreiram JavascriptExecutor objekat koji ce izvrsiti skriptu
        js.executeScript("window.open()"); //Izvrsavanje skripte iz parametra, odnosno otvaranje novog taba
        js.executeScript("window.open()"); //Ponavljam skriptu jos jednom
        js.executeScript("window.open()"); //Ponavljam skriptu jos jednom

        // Sada imam ukupno 4 taba (pocetni i tri dodatno otvorena)
        //Da bih mogao da izvrsim prebacivanje na drugi tab, moram ID svakog taba da smestim u listu
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        //driver.getWindowHandles() - funkcija koja uzima sve TRENUTNO otvorene tabove i smesta u listu

        driver.switchTo().window(listaTabova.get(1)); //Prebacivanje fokusa na tab sa indexom 1
        driver.get("https://www.linkedin.com");

        driver.switchTo().window(listaTabova.get(2));
        driver.get("https://www.joberty.com");

        driver.switchTo().window(listaTabova.get(3));
        driver.get("https://www.youtube.com");

        // 0 -- prvi tab
        // 0 - 1 -- kada otvorimo novi tab
        // 0 - 2 - 1 -- kada otvorimo novi tab dok smo na tabu 0
        // 0 - 3 - 2 - 1 -- kada otvorimo jos jedan tab dok smo na tabu 0

        driver.close(); //Zatvaram tab
        //Ali koji zatvaram? Onaj koji je poslednji bio u fokusu, odnosno index 3
        //Da bih se prebacio na drugi neki tab MORAM da prebacim fokus na drugi tab, iako fizicki vidim da je meni fokus na nekom drugom tabu u browseru
        driver.switchTo().window(listaTabova.get(2));
        driver.close();
        driver.switchTo().window(listaTabova.get(1));
        driver.close();

        //Ako zelim da zatvorim sve tabove, jednostavno pozovem funkciju:
        //driver.quit();

    }
}
