package amazon.config;

import amazon.utilities.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import amazon.utilities.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class Config {

    static private AppiumDriver driver=null;
    private String working_directory = System.getProperty("user.dir");
    private static Logger configLogger = LogManager.getLogger(Config.class);

    /**
     * Defines Android Capabilities
     * @return DesiredCapabilities
     */
    private DesiredCapabilities androidCapabilities(){
        Utils.infoLog(configLogger,"Setting Android Desired Capabilities");
        DesiredCapabilities androidCapabilities = new DesiredCapabilities();
        androidCapabilities.setCapability("deviceName", Utils.getValueForParam("android_device"));
        androidCapabilities.setCapability("automationName", Utils.getValueForParam("automation_type"));
        androidCapabilities.setCapability("app", working_directory + Utils.getValueForParam("android_app"));
        androidCapabilities.setCapability("platformVersion", Utils.findAndroidVersion());
        androidCapabilities.setCapability("newCommandTimeout", Utils.getValueForParam("timeout"));
        androidCapabilities.setCapability("appPackage", Utils.getValueForParam("app_package"));
        androidCapabilities.setCapability("appActivity", Utils.getValueForParam("app_activity"));
        androidCapabilities.setCapability("autoAcceptAlerts", true);
        androidCapabilities.setCapability("autoGrantPermissions",true);
        androidCapabilities.setCapability("dontStopAppOnReset",true);
        return androidCapabilities;
    }

    /**
     * Set test data and initialise the reports
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        setTestData();
        initializeReports();
    }

    /**
     * Initialise the WebDriver session
     */
    @BeforeTest(alwaysRun = true)
    public void initialize(){
        try {
            if(driver==null)
                driver = new AndroidDriver(new URL(Utils.getValueForParam("url_android")), androidCapabilities());
            Utils.infoLog(configLogger,"Driver Initialised!!");
        }
        catch(MalformedURLException e){
            Utils.infoLog(configLogger,"Url exception while defining the driver: "+ e.getMessage());
        }
    }


    /**
     * Quit the running WebDriver session
     */
    @AfterTest(alwaysRun = true)
    public void quit() {
        try {
            getDriver().quit();
            Utils.infoLog(configLogger,"WebDriver Session Quit!!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Get the Webdriver instance
     * @return driver
     */
    protected AppiumDriver getDriver() {
        return driver;
    }


    private void setTestData(){
        Utils.readPropertyFileToMap(working_directory + "/test_data/configuration.properties");
        Utils.readExcelFileToMap(working_directory + "/test_data/TestData.xlsx");
    }

    private void initializeReports(){
        Report.initializeExtentReport(working_directory + "/reports/report_"+ System.currentTimeMillis() +".html");
    }
}
