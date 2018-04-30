import BasePage from './basePage';

class LoginPage extends BasePage {
    constructor() {
        super();
        this.userId = element(by.model('login.username'));
        this.password = element(by.model('login.password'));
        this.loginButton = element(by.id('start_shopping_login_button'));
        this.signUpButton = element(by.xpath("//button[contains(text(), 'Sign up')]"));
        this.signUpNameTxtBox = element(by.model('register.name'));
        this.url = 'https://shop.shipt.com';
        this.pageLoaded = this.isVisible(this.loginButton);
    }

}
export default new LoginPage();