package e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.TestCase;
import common.TestData;
import common.TestRunnerBase;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.io.File;
import java.io.IOException;

public class Login extends TestRunnerBase {

    private ObjectMapper mapper = new ObjectMapper();
    private TestData testData;
    private TestCase testCase = mapper.readValue(new File("src/test/resources/TestProperties/defaultProps.json"), TestCase.class);

    @Factory(dataProvider = "TestData")
    public Login(TestData testData) throws IOException {
        this.testData = testData;
    }

    public Login() throws IOException {

    }

    @Test(priority = 0, description = "Invalid Login")
    public void invalidLogin() {
        testCase.startTest();
        testCase.getDriver().navigate().to(testCase.getUrl());
        LoginPage.username_txtbox(testCase.getDriver()).sendKeys(testData.getUsername());
        LoginPage.password_txtbox(testCase.getDriver()).sendKeys(testData.getPassword());
        LoginPage.login_btn(testCase.getDriver()).click();
        //Failing test on purpose to see screenshot in test report by asserting element isn't visible
        testCase.getAssertHelpers().assertTrue(!LoginPage.username_txtbox(testCase.getDriver()).isDisplayed(), "Expect login button to still be there");
        testCase.assertHelpers.assertAll();
    }

    @Test(priority = 1, description = "View Sign Up Page")
    public void viewSignUpPage() {
        LoginPage.signUp_btn(testCase.getDriver()).click();
        testCase.getAssertHelpers().assertTrue(LoginPage.signUpName_txtbox(testCase.getDriver()).isDisplayed(), "Sign up page should be displayed");
        testCase.assertHelpers.assertAll();
    }



}
