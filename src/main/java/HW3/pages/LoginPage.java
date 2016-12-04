package HW3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Victoria on 03.12.2016.
 */
public class LoginPage {
    private WebDriver driver;
    private static final String URL = "http://80.92.229.236:81/auth/login";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public void setUsername(String username) {
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickOnLogin() {
        WebElement logInBut = driver.findElement(By.id("logIn"));
        logInBut.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath(".//ul[contains(@class,'errors')]/li")).getText();
    }

    public String getErrorMessage(int numberOfError) {
        List<WebElement> errorMsgs = driver.findElements(By.xpath(".//*[contains(@class,'errors')]"));
        return errorMsgs.get(numberOfError - 1).getText();
    }

}
