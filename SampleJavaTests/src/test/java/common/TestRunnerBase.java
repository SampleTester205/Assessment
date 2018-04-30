package common;

import ExtentReport.ExtentManager;
import ExtentReport.ExtentTestManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestRunnerBase {

    public ExtentReports reports;
    public String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(Calendar.getInstance().getTime());

    @DataProvider(name = "TestData", parallel = true)
    public Object[][] getDataFiles() {
        File folder = new File("src/test/resources/TestData");
        File[] listOfFiles = folder.listFiles();
        List<File> fileList = Arrays.asList(listOfFiles);
        List<TestData> testDataList = new ArrayList<TestData>();
        for(File file : fileList){
            try {
                ObjectMapper mapper = new ObjectMapper();
                TestData datafiles = mapper.readValue(file, TestData.class);
                testDataList.add(datafiles);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Object[][] data = new Object[testDataList.size()][1];
        for(int i = 0; i <testDataList.size(); i++){
            data[i][0] = testDataList.get(i);
        }

        return data;
    }


    @BeforeClass
    public void setup() throws IOException {
        reports = ExtentManager.getReporter(System.getProperty("user.dir") + "/ExtentReport/TestReport.html");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        ExtentTestManager.startTest(method.getDeclaringClass().getSimpleName()+"_"+method.getName()+"_"+currentDate);
    }

    @AfterMethod
    protected void afterMethod(ITestResult result, ITestContext ctx) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        }
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
    }

    public static void waitForVisibilityOf(WebElement locator, RemoteWebDriver driver){
        WebDriverWait wait = (WebDriverWait)new WebDriverWait(driver, 30)
                .ignoring(StaleElementReferenceException.class);
        try {
            wait.until(ExpectedConditions.visibilityOf(locator));
        }
        catch (NullPointerException ex) {
            ExtentTestManager.getTest().log(LogStatus.ERROR, "Could not find element to wait for Visibility of");
        }

    }

    public static void selectDropDownByText(WebElement element, String text){
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }
}
