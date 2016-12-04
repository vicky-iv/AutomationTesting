package HW3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Victoria on 03.12.2016.
 */
public class EditPlayerPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[contains(@id,'login')]")
    private WebElement usernameField;

    @FindBy(xpath = "//input[contains(@id,'email')]")
    private WebElement emailField;

    @FindBy(xpath = "//input[contains(@id,'us_password')]")
    private WebElement passwordField;

    @FindBy(xpath = "//input[contains(@id,'confirm_password')]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[contains(@id,'us_fname')]")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[contains(@id,'us_lname')]")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[contains(@id,'us_city')]")
    private WebElement cityField;

    @FindBy(xpath = "//textarea[contains(@id,'us_address')]")
    private WebElement addressField;

    @FindBy(xpath = "//input[contains(@id,'us_phone')]")
    private WebElement phoneField;

    public EditPlayerPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void setUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void setAddress(String address) {
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void setPhone(Long phone) {
        phoneField.clear();
        phoneField.sendKeys(phone.toString());
    }

    public void clickOnSave(){
        driver.findElement(By.xpath(".//input[contains(@value,'Save')]")).click();
    }

    public void clickOnCancel(){
        driver.findElement(By.xpath(".//input[contains(@value,'Cancel')]")).click();
    }

    public String getEmail() {
        return emailField.getAttribute("value");
    }

    public String getFirstName() {
        return firstNameField.getAttribute("value");
    }

    public String getLastName() {
        return lastNameField.getAttribute("value");
    }

    public String getCity() {
        return cityField.getAttribute("value");
    }

    public String getAddress() {
        return addressField.getAttribute("value");
    }

    public String getPhone() {
        return phoneField.getAttribute("value");
    }
}
