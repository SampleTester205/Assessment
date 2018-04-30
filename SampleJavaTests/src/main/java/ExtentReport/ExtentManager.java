package ExtentReport;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;


public class ExtentManager
{
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, false, DisplayOrder.NEWEST_FIRST, NetworkMode.ONLINE);

            extent
                    .addSystemInfo("Environment", "QA");
        }

        return extent;
    }

    public synchronized static ExtentReports getReporter() {
        return extent;
    }
}
