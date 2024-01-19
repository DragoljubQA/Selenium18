import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium6 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wordpress.com/");

        Cookie cookie = new Cookie("wordpress_logged_in","dragoljubqa%7C1800042611%7Cjc9p3CvC6tLTLfuGDib4nnV2uKrs7OKHHyV6L7AriiN%7Cd14372257247bdfc3d82fafbcec2c20b73e475bbb73502707da969830d06628f");

        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

        Thread.sleep(4000);

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

    }
}
