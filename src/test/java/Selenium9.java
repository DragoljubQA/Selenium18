import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Selenium9 {

    //Koristeci anotacije za testiranje login stranice mogu da biram:
    //Da li hocu da se sve testira u jednom browseru
    //Da li hocu da svaki test otvara poseban browser

    //Obe opcije su u redu i imaju svoje prednosti i mane

    //Ako radim sve u jednom browseru testovi se mnogo brze izvrsavaju
    //Ali mogu biti nestabilni jer ce jedni testovi direktno uticati na druge

    //Ako radim svaki browser za sebe onda se testovi sporije izvrsavaju
    //Ali su testovi nezavisni jedni od drugih

    //Da bi driver mogao da bude vidljiv u vise metoda moram da ga deklarisem (napravim) van svih metoda
    //A inicijalizujem (dodeljujem vrednost) ga u setUp metodi

    // U ovoj klasi testovi ce raditi SAMO AKO ih pokrecete jedan po jedan!!!
    // Tj ako ih pokrenete klikom na 'run' ikonicu pored same METODE

    // Ako hocete da pokrenete sve testove onda cu pasti zbog konflikta izmedju testova
    // Tj ako ih pokrenete klikom na 'run' ikonicu pored same KLASE (11. red)

    WebDriver driver;
    WebElement usernameField;
    WebElement passwordField;
    WebElement submitButton;
    String validUsername, validPassword, invalidUsername, loggedInURL, invalidPassword;

    //Code koji se izvrsava samo jednom na pocetku
    @BeforeClass
    public void setUp() {
        // Pokrecemo browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/");

        // Nalazim elemente za jedno i drugo dugme
        // Napomena: Posto se ovaj deo nalazi u BeforeClass to znaci da ce se izvrsiti samo jednom na pocetku
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();
        WebElement linkButton = driver.findElement(By.linkText("Test Login Page"));
        linkButton.click();
    }

    //Code koji se izvrsava pre svakog testa
    @BeforeMethod
    public void pageSetUp() {
        //Kada se prvi test zavrsi (i ulogujemo se) nama ostaje otvorena stranica kada smo ulogovani
        //Pre nego sto pocni naredni test mora da se izvrsi ova BeforeMethod metoda
        //I zato dolazi do konflikta jer u narednom redu trazimo id='username' kada smo vec ulogovani
        //Program ne moze da nadje element i vraca nam gresku
        //Ovo je problem SAMO kada pokrenete sve testove
        //Ako pokrenete samo JEDAN test onda nece biti problema jer ce ova metoda da se izvrsi samo jednom
        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.id("submit"));

        validUsername = "student";
        invalidUsername = "student1";
        validPassword = "Password123";
        invalidPassword = "password";
        loggedInURL = "https://practicetestautomation.com/logged-in-successfully/";

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

}
