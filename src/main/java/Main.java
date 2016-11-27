import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Victoria on 26.11.2016.
 */
public class Main {

    public static void login(WebDriver driver, String userName, String password, String URL){
        driver.get(URL + "/auth/login"); // Open Poker

        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("logIn")).click();
    }

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        String URL = "http://80.92.229.236:81";

        login(driver,"admin","123",URL);

        driver.findElement(By.xpath(".//a[contains(text(),'Insert')]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        TestsPlayer testsPlayer=new TestsPlayer(driver);
        PokerPlayer firstPlayer = new PokerPlayer();
        PokerPlayer secondPlayer =new PokerPlayer();

        testsPlayer.createPlayer(firstPlayer);
        driver.findElement(By.xpath(".//input[contains(@value,'Save')]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        testsPlayer.assertString(driver.getTitle(),"Players");

        driver.findElement(By.xpath("//input[contains(@id, 'login') and not(contains(@id,'last'))]")).sendKeys(firstPlayer.getUserName());
        driver.findElement(By.xpath(".//input[contains(@value,'Search')]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[text()='bbb13@gmail.com']/ancestor::tr//a[contains(@href,'edit/")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        testsPlayer.checkContentPokerPlayer(firstPlayer);

        testsPlayer.editPlayer(secondPlayer);
        driver.findElement(By.xpath(".//input[contains(@value,'Save')]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[text()='bbb13@gmail.com']/ancestor::tr//a[contains(@href,'edit/")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        testsPlayer.checkContentPokerPlayer(firstPlayer);
    }

}
