package HW3.tests;

import HW3.pages.EditPlayerPage;
import HW3.pages.LoginPage;
import HW3.pages.PlayersPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Victoria on 03.12.2016.
 */
public class CRUDUserTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private PlayersPage playersPage;
    private EditPlayerPage editPlayerPage;

    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private Long phone;

    private void searchPlayer(String userName) {
        playersPage.setPlayerFieldForSearch(userName);
        playersPage.clickOnSearch();
    }

    /**
     * Default preconditions:
     * 1. Open FireFox browser
     * 2. Set implicit waits to 10 seconds
     * 3. Open application Login Page URL
     * 4. Sign in as "admin"
     */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.clickOnLogin();
    }

    /**
     * Preconditions:
     * Open application Players Page URL
     */
    @BeforeMethod
    public void beforeMethod() {
        playersPage = new PlayersPage(driver);
        playersPage.open();
        editPlayerPage = new EditPlayerPage(driver);
    }

    /**
     * Steps to reproduce:
     * 1. Generate random values for fields:
     * username
     * email
     * password and confirm password
     * first name
     * last name
     * city
     * address
     * phone
     * 2. Click on 'Insert' button
     * 3. Fill the fields:
     * username
     * email
     * password and confirm password
     * first name
     * last name
     * city
     * address
     * phone
     * 4. Click on 'Save' button
     * 5. Verify that title of the page equals to "Players"
     */
    @Test(priority = 1)
    public void createUser() {
        RandomStringUtils randomStringUtils = new RandomStringUtils();
        this.userName = randomStringUtils.randomAlphanumeric(7);
        this.email = userName + "@gmail.com";
        this.password = randomStringUtils.randomAlphanumeric(7);
        this.firstName = randomStringUtils.randomAlphanumeric(7);
        this.lastName = randomStringUtils.randomAlphanumeric(7);
        this.city = randomStringUtils.randomAlphanumeric(7);
        this.address = randomStringUtils.randomAlphanumeric(7);
        this.phone = Long.parseLong(randomStringUtils.randomNumeric(12));

        playersPage.clickOnInsert();

        editPlayerPage.setUsername(userName);
        editPlayerPage.setEmail(email);
        editPlayerPage.setPassword(password);
        editPlayerPage.setConfirmPassword(password);
        editPlayerPage.setFirstName(firstName);
        editPlayerPage.setLastName(lastName);
        editPlayerPage.setCity(city);
        editPlayerPage.setAddress(address);
        editPlayerPage.setPhone(phone);

        editPlayerPage.clickOnSave();

        Assert.assertEquals(editPlayerPage.getTitle(), "Players", "Wrong Title after save new player");
    }

    /**
     * Steps to reproduce:
     * 1. Generate random values for fields:
     * email
     * first name
     * last name
     * city
     * address
     * phone
     * 2. Click on 'Insert' button
     * 3. Fill the fields:
     * email
     * first name
     * last name
     * city
     * address
     * phone
     * 4. Click on 'Save' button
     * 5. Verify that title of the page equals to "Players"
     */
    @Test(priority = 2)
    public void editUser() {
        RandomStringUtils randomStringUtils = new RandomStringUtils();
        this.email = randomStringUtils.randomAlphanumeric(7) + "@mail.com";
        this.firstName = randomStringUtils.randomAlphanumeric(7);
        this.lastName = randomStringUtils.randomAlphanumeric(7);
        this.city = randomStringUtils.randomAlphanumeric(7);
        this.address = randomStringUtils.randomAlphanumeric(7);
        this.phone = Long.parseLong(randomStringUtils.randomNumeric(12));

        searchPlayer(userName);
        playersPage.clickOnEditUser(userName);

        editPlayerPage.setEmail(email);
        editPlayerPage.setFirstName(firstName);
        editPlayerPage.setLastName(lastName);
        editPlayerPage.setCity(city);
        editPlayerPage.setAddress(address);
        editPlayerPage.setPhone(phone);

        editPlayerPage.clickOnSave();
        Assert.assertEquals(editPlayerPage.getTitle(), "Players", "Wrong Title after saving the edited user");
    }

    /**
     * Steps to reproduce:
     * 1. Search player by username
     * 2. Click on 'Edit' icon
     * 3. Verify the contents of the fields:
     * email
     * first name
     * last name
     * city
     * address
     * phone
     */
    @Test(priority = 3)
    public void checkContentPlayer() {
        searchPlayer(userName);
        playersPage.clickOnEditUser(userName);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(editPlayerPage.getEmail(), email, "Wrong Email");
        softAssert.assertEquals(editPlayerPage.getFirstName(), firstName, "Wrong First Name");
        softAssert.assertEquals(editPlayerPage.getLastName(), lastName, "Wrong Last Name");
        softAssert.assertEquals(editPlayerPage.getCity(), city, "Wrong City");
        softAssert.assertEquals(editPlayerPage.getAddress(), address, "Wrong Address");
        softAssert.assertEquals(editPlayerPage.getPhone(), phone.toString(), "Wrong Phone");
        softAssert.assertAll();
    }

    /**
     * Steps to reproduce:
     * 1. Search player by username
     * 2. Click on 'Delete' icon
     * 3. Search player by username
     * 4. Verify of absence the player in search results
     */
    @Test(priority = 4)
    public void deleteUser() {
        searchPlayer(userName);
        playersPage.clickOnDeleteUser(userName);

        searchPlayer(userName);
        Assert.assertFalse(playersPage.isPlayerPresent(userName), "Player didn't deleted");
    }

    /**
     * Close the browser
     */
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
