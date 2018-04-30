import loginPage from '../pages/loginPage';

describe ('Login Page Tests', () => {
	beforeEach(() => {
        loginPage.goto();
	});

    it('Invalid Login', () => {
        loginPage.loaded();
        loginPage.userId.sendKeys(loginPage.testData.username);
        loginPage.password.sendKeys(loginPage.testData.password);
        loginPage.loginButton.click();
        expect(loginPage.loginButton.isDisplayed()).toBe(false, 'Expect login button to still be there'); //Failing test on purpose to see screenshot in test report
    });

    it('View Sign Up Page', () => {
        loginPage.loaded();
        loginPage.signUpButton.click();
        expect(loginPage.signUpNameTxtBox.isDisplayed()).toBe(true, 'Sign up page should be displayed'); //assert first text box form is visible to ensure button worked
    });

});
