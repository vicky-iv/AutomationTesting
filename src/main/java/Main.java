import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String URL = "http://80.92.229.236:81";

        login(driver,"admin","123",URL);

        driver.findElement(By.xpath(".//a[contains(text(),'Insert')]")).click();

        TestsForPlayer testsPlayer=new TestsForPlayer(driver,true);
        PokerPlayer firstPlayer = new PokerPlayer();
        PokerPlayer secondPlayer =new PokerPlayer();

        testsPlayer.createPlayer(firstPlayer);
        driver.findElement(By.xpath(".//input[contains(@value,'Save')]")).click();

        System.out.println("Title 'Players' is opened: ");
        testsPlayer.assertString(driver.getTitle(),"Players"); //проверка заголовка

        driver.findElement(By.xpath("//input[contains(@id, 'login') and not(contains(@id,'last'))]")).sendKeys(firstPlayer.getUserName());//поиск игрока
        driver.findElement(By.xpath(".//input[contains(@value,'Search')]")).click();

        driver.findElement(By.xpath("//a[text()='"+firstPlayer.getEmail()+"']/ancestor::tr//a[contains(@href,'edit/id')]")).click();//нажатие на иконку редактирования

        System.out.println("Title 'Players - Edit' is opened: ");
        testsPlayer.assertString(driver.getTitle(), "Players - Edit");

        new TestsForPlayer(driver,false).checkContentPlayer(firstPlayer);

        new TestsForPlayer(driver,false).editPlayer(secondPlayer);
        driver.findElement(By.xpath(".//input[contains(@value,'Save')]")).click();

        System.out.println("Title 'Players' is opened: ");
        testsPlayer.assertString(driver.getTitle(),"Players"); //проверка заголовка

        driver.findElement(By.xpath("//a[text()='"+firstPlayer.getEmail()+"']/ancestor::tr//a[contains(@href,'edit/id')]")).click(); //нажатие на иконку редактирования

        System.out.println("Title 'Players - Edit' is opened: ");
        testsPlayer.assertString(driver.getTitle(), "Players - Edit");

        new TestsForPlayer(driver,false).checkContentPlayer(secondPlayer);
    }

}
