package HW3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Victoria on 03.12.2016.
 */
public class LoginPage {
    private WebDriver driver;
    private static final String URL = "http://80.92.229.236:81/auth/login";

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "logIn")
    private WebElement logInBut;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void setUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickOnLogin() {
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
        if (numberOfError <= errorMsgs.size()) {
            return errorMsgs.get(numberOfError - 1).getText();
        } else {
            return "No such error message";
        }

    }

}
