import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Zadatak9 {
    public static void main(String[] args) throws InterruptedException {

        /*Zadatak 9
https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
Testirati dodavanje knjige u korpu i da li se knjiga obrise kada obrisete kolacice*/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");
        //Amazon je uveo proveru da li se program pokrece automatizacijom i trazi da se odradi captcha
        //Ubacen je thread sleep kako bismo rucno uneli captchu i nastavili dalje
        Thread.sleep(10000);

        //Posto proveravam da li je nesto dodato u korpu, moram prvo da proverim da li je korpa prazna
        //Prvo proveravam da li je broj iznad korpe zaista 0
        WebElement cartBeforeAdding = driver.findElement(By.id("nav-cart-count"));
        String cartNumber = cartBeforeAdding.getText();
        Assert.assertEquals(cartNumber, "0");

        //Zatim idem na korpu da se uverim da je zaista prazna, da nije samo upisan broj 0, a da u korpi ima nesto
        WebElement cartButton1 = driver.findElement(By.id("nav-cart"));
        cartButton1.click();
        //Ova klasa je prisutna samo kada je korpa prazna, proverio sam rucno da li je element prisutan
        // kada ima nesto u korpi i ne element ne postoji kada korpa nije prazna
        WebElement emptyCart = driver.findElement(By.cssSelector(".a-row.sc-your-amazon-cart-is-empty"));
        Assert.assertTrue(emptyCart.isDisplayed());

        //Vracam se nazad da dodam knjigu u korpu
        driver.navigate().back();
        //Pre nego sto dodam zelim da izvucem naziv knjige kako bih se posle uverio da je zapravo bas ta knjiga
        // dodata u korpu
        WebElement book = driver.findElement(By.id("productTitle"));
        String bookTitleToBeAdded = book.getText();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        WebElement successfulMessage = driver.findElement(By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS"));
        Assert.assertTrue(successfulMessage.isDisplayed());

        //Sada proveravam da li se broj na korpi promenio sa 0 na 1
        WebElement cartAfterAdding = driver.findElement(By.id("nav-cart-count"));
        cartNumber = cartAfterAdding.getText();
        Assert.assertEquals(cartNumber, "1");

        //Ponovo otvaram korpu da vidim jel knjiga zaista dodata
        // ali posto bih koristio isto dugme za cart, moram ponovo da nadjem to dugme jer je prethodni element
        // za korpu sada 'stale'
        WebElement cartButton2 = driver.findElement(By.id("nav-cart"));
        cartButton2.click();

        //Uzimam naslov knjige u korpi
        WebElement bookInCart = driver.findElement(By.className("a-truncate-cut"));
        String bookTitleInCart = bookInCart.getText();

        //Uporedjujem naslov knjige u korpi sa naslovom knjige pre nego sto sam je dodao u korpu
        Assert.assertEquals(bookTitleToBeAdded, bookTitleInCart);

        //Brisem sve kolacice i radim refresh da bi se brisanje primetilo
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        //Ponovo proveravam da li je broj na korpi ponovo 0
        WebElement cartAfterRemoving = driver.findElement(By.id("nav-cart-count"));
        cartNumber = cartAfterRemoving.getText();
        Assert.assertEquals(cartNumber, "0");

        //Posto se nalazim vec na korpi, ne moram da klikcem na korpu
        // i proveravam da je element 'prazne korpe' prisutan
        WebElement emptyCart1 = driver.findElement(By.cssSelector(".a-row.sc-your-amazon-cart-is-empty"));
        Assert.assertTrue(emptyCart1.isDisplayed());

    }
}
