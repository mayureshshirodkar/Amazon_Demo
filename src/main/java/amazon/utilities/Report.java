package amazon.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Report {

    private static Logger reportLogger = LogManager.getLogger(Report.class);
    private static ExtentReports extentReports;
    private static ExtentHtmlReporter extentHtmlReporter;
    private static ExtentTest extentTest;


    /**
     * Initialize and configure the file path to create the html extent reports
     *
     * @param filePath - parameter name to read from the file
     */
    public static void initializeExtentReport(String filePath){

        extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);

        // allow automatic saving of media files relative to the report
        extentHtmlReporter.config().setAutoCreateRelativePathMedia(true);
        extentHtmlReporter.config().setTheme(Theme.DARK);
        Utils.infoLog(reportLogger,"Extent Reports Initialised!!");
    }


    /**
     * Create the extent test object to be used across the Test case
     * @param testName - Test case name
     */
    public static void createExtentTest(String testName){
        extentTest = extentReports.createTest(testName);
    }


    /**
     * Log the step status as passed
     * @param text - message to be dispalyed
     */
    public static void logStatusPass(String text){
        extentTest.pass(text);
    }


    /**
     * Log the step status as failed
     * @param text - message to be dispalyed
     * @param temp - temporary path to the screenshot
     */
    public static void logStatusFail(String text, String temp){
        try {
            extentTest.fail(text, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            new File(temp).delete();
        }
        catch (IOException io){
            io.printStackTrace();
        }

    }

    /**
     * Flush steps of test case to html report
     */
    public static void flushExtentTest(){
        extentReports.flush();
    }
}
