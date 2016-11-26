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

        TestsPlayer testsPlayer=new TestsPlayer(driver);

        String URL = "http://80.92.229.236:81";

        login(driver,"admin","123",URL);

        //click on link Insert
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        PokerPlayer firstPlayer = new PokerPlayer();
        PokerPlayer secondPlayer =new PokerPlayer();

        testsPlayer.createPlayer(firstPlayer);
        //click on button_save

        testsPlayer.assertString(driver.getTitle(),"Players");

        driver.findElement(By.xpath("//input[contains(@id, 'login') and not(contains(@id,'last'))]")).sendKeys(firstPlayer.getUserName());
        //click on button search

        //проверить найден ли игрок, может по почте глянуть
        //клинуть на иконку редактирования

        testsPlayer.checkContentPokerPlayer(firstPlayer);

        testsPlayer.editPlayer(secondPlayer);
        //click on button_save

        //проверить найден ли игрок, может по почте глянуть
        //клинуть на иконку редактирования

        testsPlayer.checkContentPokerPlayer(firstPlayer);
    }

}
