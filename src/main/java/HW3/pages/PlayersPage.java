package HW3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Victoria on 03.12.2016.
 */
public class PlayersPage {
    private WebDriver driver;
    private static final String URL = "http://80.92.229.236:81/players";

    @FindBy(xpath = "//input[contains(@id, 'login') and not(contains(@id,'last'))]")
    private WebElement usernameFieldForSearch;

    @FindBy(xpath = ".//input[contains(@id, 'email')]")
    private WebElement emailFieldForSearch;

    @FindBy(xpath = ".//input[contains(@id, 'city')]")
    private WebElement cityFieldForSearch;

    @FindBy(xpath = ".//input[contains(@id, 'firstname')]")
    private WebElement firstNameFieldForSearch;

    @FindBy(xpath = ".//input[contains(@id, 'lastname')]")
    private WebElement lastNameFieldForSearch;

    @FindBy(xpath = ".//input[contains(@id, 'deleted')]")
    private WebElement showDeleted;


    public PlayersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void clickOnSearch() {
        driver.findElement(By.xpath(".//input[contains(@value,'Search')]")).click();
    }

    public void clickOnReset() {
        driver.findElement(By.xpath(".//input[contains(@value,'Reset')]")).click();
    }

    public void clickOnInsert() {
        driver.findElement(By.xpath(".//a[contains(text(),'Insert')]")).click();
    }

    public void clickOnEditUser(String userName) {
        driver.findElement(By.xpath("//a[text()='" + userName + "']/ancestor::tr//a[contains(@href,'edit/id')]")).click();
    }

    public void clickOnLogout() {
        driver.findElement(By.xpath(".//a[contains(@href,'logout')]")).click();
    }

    public void clickOnDeleteUser(String userName) {
        driver.findElement(By.xpath(".//a[text()='" + userName + "']/ancestor::tr//a[contains(@href,'record_delete')]")).click();
        driver.switchTo().alert().accept();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isPlayerPresent(String username) {
        return driver.findElements(By.xpath(".//a[contains(text(),'" + username + "')]")).size() > 0;
    }

    public void setPlayerFieldForSearch(String username) {
        usernameFieldForSearch.clear();
        usernameFieldForSearch.sendKeys(username);
    }

    public void setEmailFieldForSearch(String email) {
        emailFieldForSearch.clear();
        emailFieldForSearch.sendKeys(email);
    }

    public void setCityFieldForSearch(String city) {
        cityFieldForSearch.clear();
        cityFieldForSearch.sendKeys(city);
    }

    public void setFirstNameFieldForSearch(String firstName) {
        firstNameFieldForSearch.clear();
        firstNameFieldForSearch.sendKeys(firstName);
    }

    public void setLastNameFieldForSearch(String lastName) {
        lastNameFieldForSearch.clear();
        lastNameFieldForSearch.sendKeys(lastName);
    }

    public void setShowDeleted() {
        showDeleted.click();
    }

    public boolean isSelectedShowDeleted() {
        return showDeleted.isSelected();
    }
}
