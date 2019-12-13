package amazon.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Report {

    private static Logger reportLogger = LogManager.getLogger(Report.class);

    private static ExtentReports extent;
    private static ExtentHtmlReporter reporter;
    private static ExtentTest logger;


    public static void initializeExtentReport(String filePath){
        reporter = new ExtentHtmlReporter(filePath);
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        Utils.infoLog(reportLogger,"Extent Reports Initialised!!");
        // allow automatic saving of media files relative to the report
        reporter.config().setAutoCreateRelativePathMedia(true);
    }


    public static void createExtentTest(String testName){
        logger = extent.createTest(testName);
    }

    public static void logStatusPass(String text){
        logger.log(Status.PASS, text);
    }

    public static void logStatusFail(String text, String temp){
        try {
            logger.log(Status.FAIL, text, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            new File(temp).delete();
        }
        catch (IOException io){
            io.printStackTrace();
        }

    }

    public static void flushExtentTest(){
        extent.flush();
    }
}
