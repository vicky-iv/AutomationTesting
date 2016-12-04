package HW3.tests;

import HW3.pages.EditPlayerPage;
import HW3.pages.LoginPage;
import HW3.pages.PlayersPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Victoria on 03.12.2016.
 */
public class SearchTests {
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

    private void createUserForSearch() {

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
        editPlayerPage = new EditPlayerPage(driver);

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
    }

    /**
     * Default preconditions:
     * 1. Open FireFox browser
     * 2. Set implicit waits to 10 seconds
     * 3. Open application Login Page URL
     * 4. Sign in as "admin"
     * 5. Create user for search
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

        playersPage = new PlayersPage(driver);
        createUserForSearch();
    }

    /**
     * Preconditions:
     * Open application Players Page URL
     */
    @BeforeMethod
    public void beforeMethod() {
        playersPage.open();
    }

    /**
     * Steps to reproduce:
     * 1. Fill Player field
     * 2. Click on 'Search' button
     * 3. Verify that the player is in the search results
     */
    @Test
    public void searchByUsername() {
        playersPage.setPlayerFieldForSearch(userName);
        playersPage.clickOnSearch();
        Assert.assertTrue(playersPage.isPlayerPresent(userName), "Player by Username not found");
    }

    /**
     * Steps to reproduce:
     * 1. Fill Email field
     * 2. Click on 'Search' button
     * 3. Verify that the player is in the search results
     */
    @Test
    public void searchByEmail() {
        playersPage.setEmailFieldForSearch(email);
        playersPage.clickOnSearch();
        Assert.assertTrue(playersPage.isPlayerPresent(userName), "Player by email not found");
    }

    /**
     * Steps to reproduce:
     * 1. Fill City field
     * 2. Click on 'Search' button
     * 3. Verify that the player is in the search results
     */
    @Test
    public void searchByCity() {
        playersPage.setCityFieldForSearch(city);
        playersPage.clickOnSearch();
        Assert.assertTrue(playersPage.isPlayerPresent(userName), "Player by City not found");
    }

    /**
     * Steps to reproduce:
     * 1. Fill First Name field
     * 2. Click on 'Search' button
     * 3. Verify that the player is in the search results
     */
    @Test
    public void searchByFirstName() {
        playersPage.setFirstNameFieldForSearch(firstName);
        playersPage.clickOnSearch();
        Assert.assertTrue(playersPage.isPlayerPresent(userName), "Player by First Name not found");
    }

    /**
     * Steps to reproduce:
     * 1. Fill Last Name field
     * 2. Click on 'Search' button
     * 3. Verify that the player is in the search results
     */
    @Test
    public void searchByLastName() {
        playersPage.setLastNameFieldForSearch(lastName);
        playersPage.clickOnSearch();
        Assert.assertTrue(playersPage.isPlayerPresent(userName), "Player by Last Name not found");
    }

    /**
     * Steps to reproduce:
     * 1. Fill Player field
     * 2. Click on 'Search' button
     * 3. Click on 'Delete' icon
     * 4. Fill Player field
     * 5. Select 'Show Deleted' checkbox
     * 6. Click on 'Search' button
     * 3. Verify that the player is in the search results
     */
    @Test
    public void searchByUsernameDeletedPlayer() {
        playersPage.setPlayerFieldForSearch(userName);
        playersPage.clickOnSearch();
        playersPage.clickOnDeleteUser(userName);

        playersPage.setPlayerFieldForSearch(userName);
        if (!playersPage.isSelectedShowDeleted()) playersPage.setShowDeleted();
        playersPage.clickOnSearch();
        Assert.assertTrue(playersPage.isPlayerPresent(userName), "Deleted Player by username not found");
    }

    /**
     * Click on 'Reset' button
     */
    @AfterMethod
    public void afterMethod() {
        playersPage.clickOnReset();
    }

    /**
     * Close the browser
     */
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
