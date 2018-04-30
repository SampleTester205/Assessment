package pageObjects;

import ExtentReport.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;

import java.util.List;


public class PageObjectBase {

    public static WebElement findElement(By locator, WebDriver driver){
        WebElement element;
        try {
            element = driver.findElement(locator);
        }
        catch (Exception ex){
            ex.printStackTrace();
            ExtentTestManager.getTest().log(LogStatus.ERROR, "Error when finding element "+locator.toString());
            return  new WebElement() {
                @Override
                public void click() {

                }

                @Override
                public void submit() {

                }

                @Override
                public void sendKeys(CharSequence... keysToSend) {

                }

                @Override
                public void clear() {

                }

                @Override
                public String getTagName() {
                    return null;
                }

                @Override
                public String getAttribute(String name) {
                    return null;
                }

                @Override
                public boolean isSelected() {
                    return false;
                }

                @Override
                public boolean isEnabled() {
                    return false;
                }

                @Override
                public String getText() {
                    return null;
                }

                @Override
                public List<WebElement> findElements(By by) {
                    return null;
                }

                @Override
                public WebElement findElement(By by) {
                    return null;
                }

                @Override
                public boolean isDisplayed() {
                    return false;
                }

                @Override
                public Point getLocation() {
                    return null;
                }

                @Override
                public Dimension getSize() {
                    return null;
                }

                @Override
                public Rectangle getRect() {
                    return null;
                }

                @Override
                public String getCssValue(String propertyName) {
                    return null;
                }

                @Override
                public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
                    return null;
                }
            };
        }
        return element;
    }

    public static By findByXpath(String locator){
        By finder = By.xpath(locator);
        return  finder;
    }
    public static By findByTagName(String locator){
        By finder = By.tagName(locator);
        return  finder;
    }

    public static By findByLinkText(String locator){
        By finder = By.linkText(locator);
        return  finder;
    }

    public static By findById(String locator){
        By finder = By.id(locator);
        return  finder;
    }

    public static By findByName(String locator){
        By finder = By.name(locator);
        return  finder;
    }

    public static By findByCssSelector(String locator){
        By finder = By.cssSelector(locator);
        return  finder;
    }

    public static By findByClassName(String locator){
        By finder = By.className(locator);
        return  finder;
    }

    public static By findByPartialLinkText(String locator){
        By finder = By.partialLinkText(locator);
        return  finder;
    }

}
