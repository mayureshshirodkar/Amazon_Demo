package amazon.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Report {

    private static Logger reportLogger = LogManager.getLogger(Report.class);

    private static ExtentReports extentReports;
    private static ExtentHtmlReporter extentHtmlReporter;
    private static ExtentTest extentTest;


    public static void initializeExtentReport(String filePath){

        extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);

        // allow automatic saving of media files relative to the report
        extentHtmlReporter.config().setAutoCreateRelativePathMedia(true);
        Utils.infoLog(reportLogger,"Extent Reports Initialised!!");
    }


    public static void createExtentTest(String testName){
        extentTest = extentReports.createTest(testName);
    }

    public static void logStatusPass(String text){
        extentTest.pass(text);
    }

    public static void logStatusFail(String text, String temp){
        try {
            extentTest.fail(text, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            new File(temp).delete();
        }
        catch (IOException io){
            io.printStackTrace();
        }

    }

    public static void flushExtentTest(){
        extentReports.flush();
    }
}
