package common;

import ExtentReport.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class TestCase {

    private RemoteWebDriver driver;
    private String url;
    private Boolean useSauceLabs;
    private String browser;
    public AssertHelpers assertHelpers = new AssertHelpers();


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void setDriver() {

        try {
            if(getUseSauceLabs()) {
                setDriver(setupSauceLabsDriver("Windows 10", "chrome"));
            }
            else {
                setDriver(setupLocalDriver("chrome"));
            }
        }
        catch (MalformedURLException ex) {
            driver = null;
        }

        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.navigate().to("http://4.bp.blogspot.com/_-31KojMr-J0/TLijiVAp60I/AAAAAAAAFNo/mP0zY35kLG8/s1600/bluesteel_jpg_595x325_crop_upscale_q85.jpg");
        Dimension d = new Dimension(1440,900);  //(x,y coordinators in pixels)
        driver.manage().window().setSize(d);
    }

    public Boolean getUseSauceLabs() {
        return useSauceLabs;
    }

    public void setUseSauceLabs(Boolean useSauceLabs) {
        this.useSauceLabs = useSauceLabs;
    }

    public void setDriver(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public AssertHelpers getAssertHelpers() {
        return assertHelpers;
    }

    private RemoteWebDriver setupSauceLabsDriver(String os, String browserName) throws MalformedURLException {
        String USERNAME = "CRAP";
        String ACCESS_KEY = "CRAP";
        String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

        if(browserName.equalsIgnoreCase("firefox")){
            DesiredCapabilities caps = DesiredCapabilities.firefox();
            caps.setCapability("name", this.getClass().getSimpleName());
            caps.setCapability("platform", os);
            caps.setCapability("version", "latest");
            return new RemoteWebDriver(new URL(URL), caps);

        }
        else if(browserName.equalsIgnoreCase("chrome")){
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability("name", this.getClass().getSimpleName());
            caps.setCapability("platform", os);
            caps.setCapability("version", "60.0");
            return new RemoteWebDriver(new URL(URL), caps);

        }
        else if(browserName.equalsIgnoreCase("edge")){
            DesiredCapabilities caps = DesiredCapabilities.edge();
            caps.setCapability("name", this.getClass().getSimpleName());
            caps.setCapability("platform", os);
            caps.setCapability("version", "latest");
            caps.setCapability("avoidProxy", "true");
            return new RemoteWebDriver(new URL(URL), caps);
        }
        else if(browserName.equalsIgnoreCase("safari")){
            DesiredCapabilities caps = DesiredCapabilities.safari();
            caps.setCapability("name", this.getClass().getSimpleName());
            caps.setCapability("platform", os);
            caps.setCapability("version", "latest");
            return new RemoteWebDriver(new URL(URL), caps);
        }

        return null;
    }

    private RemoteWebDriver setupLocalDriver(String browserName){
        if(browserName.equalsIgnoreCase("firefox")){
            FirefoxDriverManager.getInstance().setup();
            System.setProperty("lmportal.xvfb.id", ":1");
            return new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("chrome")){
            ChromeDriverManager.getInstance().setup();
            System.setProperty("lmportal.xvfb.id", ":1");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            return new ChromeDriver(options);
        }else if(browserName.equalsIgnoreCase("safari")){
            return new SafariDriver();
        }else if(browserName.equalsIgnoreCase("ie")){
            InternetExplorerDriverManager.getInstance().setup();
            return new InternetExplorerDriver();
        }
        return null;
    }

    public void startTest() {
        setDriver();
        getAssertHelpers().setDriver(getDriver());
        ExtentTestManager.getTest().log(LogStatus.INFO, "RUNNING TEST ON URL "+getUrl());
    }

    public void endTest(){
        //Quit Driver
        driver.close();
        System.out.println("QUITING BROWSER");
        driver.quit();
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setAssertHelpers(AssertHelpers assertHelpers) {
        this.assertHelpers = assertHelpers;
    }

    public void setUrlFromCommandLine() {
        if (System.getProperty("testUrl") != null) {
            setUrl(System.getProperty("testUrl"));
            System.out.println(getUrl());
        }
    }
}
