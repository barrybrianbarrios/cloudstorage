package com.udacity.jwdnd.course1.cloudstorage;
import com.udacity.jwdnd.course1.cloudstorage.PageObjects.HomePageObject;
import com.udacity.jwdnd.course1.cloudstorage.PageObjects.LoginPageObject;
import com.udacity.jwdnd.course1.cloudstorage.PageObjects.ResultPageObject;
import com.udacity.jwdnd.course1.cloudstorage.PageObjects.SignUpPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private LoginPageObject loginPage;
	private SignUpPageObject signupPage;
	private HomePageObject homePage;
	private ResultPageObject resultPage;
	private String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + port;
		signupPage = new SignUpPageObject(driver);
		loginPage = new LoginPageObject(driver);
		resultPage = new ResultPageObject(driver);
		homePage = new HomePageObject(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void signUpAndLoginTest(){

		//Signup Test
		String username = "jbeezy";
		String password = "foosheezy";

		driver.get(baseURL);
		loginPage.Register();
		//driver.get(baseURL + "/signup");

		signupPage.signup("John", "Beezy", username, password);
		Assertions.assertTrue(signupPage.getSuccessMessage().isDisplayed());

		// Login Test
		signupPage.clickLoginLink();
		loginPage.login(username, password);
		Assertions.assertTrue(driver.getTitle().equals("Home"));

		//Logout Test
		//driver.findElement(By.id("logoutDiv")).findElement(By.className("btn")).click();
		loginPage.Logout();
		Assertions.assertFalse(driver.getTitle().equals("Home"));
		Assertions.assertEquals("Login", driver.getTitle());
	}

	// Note Tests
	@Test
	@DisplayName("Required 2A: Creates a note, and verifies it is displayed.")
	public void testSaveNote() {
		driver.get(baseURL);
		loginPage.Register();
		String testTitle = "Test Title";
		String testDescription = "Test Description";
		signupPage.signup("Barry", "Note", "barry", "12345678");
		signupPage.clickLoginLink();
		loginPage.login("barry", "12345678");
		homePage.clickNotesTab(driver);
		homePage.clickAddNoteButton(driver);
		homePage.saveNote(driver, testTitle, testDescription);
		boolean bSuccessMsg = resultPage.isSuccessMessage(driver);
		resultPage.clickNavLink(driver);
		homePage.clickNotesTab(driver);
		String resultTitle = homePage.findOnPage(driver, testTitle);
		String resultDescription = homePage.findOnPage(driver, testDescription);
		Assertions.assertAll("Save New Note",
				() -> Assertions.assertTrue(bSuccessMsg, "The success message was not displayed."),
				() -> Assertions.assertEquals(testTitle, resultTitle, "The new note title was not displayed"),
				() -> Assertions.assertEquals(testDescription, resultDescription, "The new note description was not displayed")
		);
	}

	@Test
	@DisplayName("Required 2B: Edits an existing note and verifies that the changes are displayed")
	public void testEditNote() {
		String testTitle = "Test Title";
		String testDescription = "Test Description.";
		String editTitle = "Edit Title";
		String editDescription = "Edit Description.";
		String expectedTitle = testTitle + editTitle;
		String expectedDescription = testDescription + editDescription;
		driver.get(baseURL);
		loginPage.Register();
		signupPage.signup("Barry", "Note", "barry", "12345678");
		signupPage.clickLoginLink();
		loginPage.login("barry", "12345678");
		homePage.clickNotesTab(driver);
		homePage.clickAddNoteButton(driver);
		homePage.saveNote(driver, testTitle, testDescription);
		resultPage.clickNavLink(driver);
		homePage.clickNotesTab(driver);
		// get the id of the last item in the list.
		String buttonId = homePage.getLastAddedNoteEditButtonID();
		homePage.clickEditNoteButton(driver, buttonId);
		homePage.saveNote(driver, editTitle, editDescription);
		boolean bSuccessMsg = resultPage.isSuccessMessage(driver);
		resultPage.clickNavLink(driver);
		homePage.clickNotesTab(driver);
		String resultTitle = homePage.findOnPage(driver, expectedTitle);
		String resultDescription = homePage.findOnPage(driver, expectedDescription);

		Assertions.assertAll("Edit Note",
				() -> Assertions.assertTrue(bSuccessMsg, "The success message was not displayed."),
				() -> Assertions.assertEquals(expectedTitle, resultTitle, "The edited title is incorrect."),
				() -> Assertions.assertEquals(expectedDescription, resultDescription, "The edited description is incorrect.")
		);
	}

	@Test
	@DisplayName("Required 2C: Deletes a note and verifies that the note is no longer displayed")
	public void testDeleteNote() {
		String testTitle = "Test Title";
		String testDescription = "Test Description.";
		driver.get(baseURL);
		loginPage.Register();
		signupPage.signup("Barry", "Note", "barry", "12345678");
		signupPage.clickLoginLink();
		loginPage.login("barry", "12345678");
		homePage.clickNotesTab(driver);
		homePage.clickAddNoteButton(driver);
		homePage.saveNote(driver, testTitle, testDescription);
		resultPage.clickNavLink(driver);
		homePage.clickNotesTab(driver);
		String resultTitle = homePage.findOnPage(driver, testTitle);
		String resultDescription = homePage.findOnPage(driver, testDescription);
		String buttonId = homePage.getLastAddedNoteDeleteButtonID();
		homePage.clickDeleteNoteButton(buttonId);
		boolean bSuccessMsg = resultPage.isSuccessMessage(driver);
		resultPage.clickNavLink(driver);
		homePage.clickNotesTab(driver);
		boolean bSuccessDelete = homePage.isAnyNoteDisplayed(driver);
		Assertions.assertAll("Delete Note",
				() -> Assertions.assertTrue(bSuccessMsg, "The success message was not displayed."),
				() -> Assertions.assertFalse(bSuccessDelete, "The note was not deleted."),
				() -> Assertions.assertEquals(testTitle, resultTitle, "The new note title was never saved."),
				() -> Assertions.assertEquals(testDescription, resultDescription, "The new note description was never saved.")
		);
	}

	// Credential Test
	@Test
	@DisplayName("Required 3A: Creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.")
	public void testSaveCredential() {
		String testUrl = "http://gmail.com";
		String testUsername = "cube";
		String testPassword = "bcubeb3";
		driver.get(baseURL);
		loginPage.Register();
		signupPage.signup("Barry", "Cred", "barry", "12345678");
		signupPage.clickLoginLink();
		loginPage.login("barry", "12345678");
		homePage.clickCredentialsTab(driver);
		homePage.clickAddCredentialButton(driver);
		homePage.saveCredential(driver, testUrl, testUsername, testPassword);
		boolean bSuccessMsg = resultPage.isSuccessMessage(driver);
		resultPage.clickNavLink(driver);
		homePage.clickCredentialsTab(driver);
		boolean bResultUrl = homePage.isOnPage(driver, testUrl);
		boolean bResultUsername = homePage.isOnPage(driver, testUsername);
		boolean bPasswordEncrypted = homePage.isNotOnPage(driver, testPassword);
		Assertions.assertAll("Save New Credential",
				() -> Assertions.assertTrue(bSuccessMsg, "The success message was not displayed after saving the new credential."),
				() -> Assertions.assertTrue(bResultUrl, "The credential url was not displayed."),
				() -> Assertions.assertTrue(bResultUsername, "The credential username was not displayed."),
				() -> Assertions.assertTrue(bPasswordEncrypted,"The credential password was displayed and not encrypted.")
		);
	}

	@Test
	@DisplayName("Required 3B: Views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.")
	public void testEditCredential() {
		String testUrl = "http://dropbox";
		String testUsername = "jbeezy";
		String testPassword = "foosheezy";
		String editUrl = ".com";
		String editUsername = "23";
		String editPassword = "567";
		String expectedUrl = testUrl + editUrl;
		String expectedUsername = testUsername + editUsername;
		String expectedPassword = testPassword + editPassword;
		driver.get(baseURL);
		loginPage.Register();
		signupPage.signup("Barry", "Barrios", "b3", "12345678");
		signupPage.clickLoginLink();
		loginPage.login("b3", "12345678");
		homePage.clickCredentialsTab(driver);
		homePage.clickAddCredentialButton(driver);
		homePage.saveCredential(driver, testUrl, testUsername, testPassword);
		resultPage.clickNavLink(driver);
		homePage.clickCredentialsTab(driver);
		// get the id of the last item in the list.
		String buttonId = homePage.getLastAddedCredentialEditButtonID();
		homePage.clickEditCredentialButton(driver, buttonId);
		homePage.saveCredential(driver, editUrl, editUsername, editPassword);
		boolean bSuccessMsg = resultPage.isSuccessMessage(driver);
		resultPage.clickNavLink(driver);
		homePage.clickCredentialsTab(driver);
		boolean bResultUrl = homePage.isOnPage(driver, expectedUrl);
		boolean bResultUsername = homePage.isOnPage(driver, expectedUsername);
		homePage.clickEditCredentialButton(driver, buttonId);
		String resultPassword = homePage.getDecryptedPassword(driver);
		Assertions.assertAll("Edit Credential",
				() -> Assertions.assertTrue(bSuccessMsg, "The success message was not displayed after the edit."),
				() -> Assertions.assertTrue(bResultUrl, "The edited url is incorrect."),
				() -> Assertions.assertTrue(bResultUsername, "The edited username is incorrect."),
				() -> Assertions.assertEquals(expectedPassword, resultPassword, "The edited password is incorrect or not decrypted.")
		);
	}

	@Test
	@DisplayName("Required 3C: Deletes an existing set of credentials and verifies that the credentials are no longer displayed.")
	public void testDeleteCredential() {
		String testUrl = "http://facebook.com";
		String testUsername = "bcubeb3";
		String testPassword = "12345678";
		driver.get(baseURL);
		loginPage.Register();
		signupPage.signup( "Barry", "Barrios", "bcubeb3", "12345678");
		signupPage.clickLoginLink();
		loginPage.login("bcubeb3", "12345678");
		homePage.clickCredentialsTab(driver);
		homePage.clickAddCredentialButton(driver);
		homePage.saveCredential(driver, testUrl, testUsername, testPassword);
		resultPage.clickNavLink(driver);
		homePage.clickCredentialsTab(driver);
		// get the id of the last item in the list.
		boolean bCredentialSaved = homePage.isOnPage(driver, testUrl);
		String buttonId = homePage.getLastAddedCredentialDeleteButtonID();
		homePage.clickDeleteCredentialButton(driver, buttonId);
		boolean bSuccessMsg = resultPage.isSuccessMessage(driver);
		resultPage.clickNavLink(driver);
		homePage.clickCredentialsTab(driver);
		boolean bSuccessDelete = homePage.isAnyCredentialDisplayed(driver);
		Assertions.assertAll("Delete Credential",
				() -> Assertions.assertTrue(bCredentialSaved, "The credential was never save for later deletion."),
				() -> Assertions.assertTrue(bSuccessMsg, "The success message was not displayed after delete."),
				() -> Assertions.assertEquals(0,driver.findElements(By.tagName("td")).size())
				//() -> Assertions.assertFalse(bSuccessDelete, "The credential was not deleted.")
		);
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

}
