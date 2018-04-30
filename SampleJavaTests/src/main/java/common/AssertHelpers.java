package common;

import ExtentReport.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;


public class AssertHelpers extends Assertion {

    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

    private RemoteWebDriver driver;

    public AssertHelpers() {

    }

    public AssertHelpers(RemoteWebDriver driver) {
        setDriver(driver);
    }

    @Override
    protected void doAssert(IAssert<?> a) {
        onBeforeAssert(a);
        try {
            a.doAssert();
            onAssertSuccess(a);
            ExtentTestManager.getTest().log(LogStatus.PASS, a.getMessage());
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            m_errors.put(ex, a);
            String screenShotName = getScreenshotName();
            try {
                takeScreenShot(screenShotName);
                ExtentTestManager.getTest().log(LogStatus.FAIL, ex + ExtentTestManager.getTest().addScreenCapture(screenShotName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            onAfterAssert(a);
        }
    }

    public void assertAll() {
        if (!m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            boolean first = true;
            for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(ae.getKey().getMessage());
            }
            m_errors.clear();
            throw new AssertionError(sb.toString());
        }
    }

    public void assertTrueContains(String actual, String expected){
        String message = "Expect "+actual+" to contain "+expected;
        assertTrue(actual.contains(expected), message);
        ExtentTestManager.getTest().log(LogStatus.INFO, message);
    }

    public void assertEquals(Object actual, Object expected){
        String message = "Expect "+actual+" to equal "+expected;
        assertEquals(actual, expected, message);
        ExtentTestManager.getTest().log(LogStatus.INFO, message);
    }

    public void assertIsTrue(boolean actual, String message){
        if(message == "") {
            message = "Expect "+actual+" to be true";
        }
        assertTrue(actual, message);
        ExtentTestManager.getTest().log(LogStatus.INFO, message);
    }



    public void setDriver(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getScreenshotName(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+"_"+ UUID.randomUUID()+".png";
    }
    public String takeScreenShot(String screenShotName) throws IOException {
        screenShotName = screenShotName.replaceAll("/", "_");
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        String filePath = System.getProperty("user.dir") + "/ExtentReport/"+ screenShotName;
        FileUtils.copyFile(scrFile, new File(filePath));

//        Screenshot screenshot = new AShot().takeScreenshot(driver);
//        String filePath = System.getProperty("user.dir") + "/ExtentReport/"+ screenShotName;
//        File destination = new File(filePath);
//        ImageIO.write(screenshot.getImage(), "PNG", destination);
        return filePath;
    }

}
