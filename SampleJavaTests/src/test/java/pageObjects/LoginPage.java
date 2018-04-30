package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends PageObjectBase {


    public static WebElement username_txtbox(WebDriver driver){
        WebElement element = findElement(findByXpath("//input[contains(@ng-model,'login.username')]"), driver);
        return element;
    }

    public static WebElement password_txtbox(WebDriver driver){
        WebElement element = findElement(findByXpath("//input[contains(@ng-model,'login.password')]"), driver);
        return element;
    }

    public static WebElement login_btn(WebDriver driver){
        WebElement element = findElement(findById("start_shopping_login_button"), driver);
        return element;
    }

    public static WebElement signUp_btn(WebDriver driver){
        WebElement element = findElement(findByXpath("//button[contains(text(), 'Sign up')]"), driver);
        return element;
    }

    public static WebElement signUpName_txtbox(WebDriver driver){
        WebElement element = findElement(findByXpath("//input[contains(@ng-model,'register.name')]"), driver);
        return element;
    }



}
