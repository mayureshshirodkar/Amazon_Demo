package config;

import utilities.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class Config {

    static private AppiumDriver driver=null;
    private String working_directory = System.getProperty("user.dir");

    /**
     * Defines Android Capabilities
     * @return DesiredCapabilities
     */
    private DesiredCapabilities androidCapabilities(){
        System.out.println("Setting Android Desired Capabilities");
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
     * Initialise the WebDriver session
     */
    @BeforeTest(alwaysRun = true)
    public void initialize(){
        try {
            Utils.readEntirePropertyFile(working_directory + "/data/configuration.properties");
            if(driver==null)
                driver = new AndroidDriver(new URL(Utils.getValueForParam("url_android")), androidCapabilities());
                System.out.println("Driver Initialised!!");
        }
        catch(MalformedURLException e){
            System.out.println("Url exception while defining the driver: \n");
            e.printStackTrace();
        }
    }


    /**
     * Quit the running WebDriver session
     */
    @AfterTest(alwaysRun = true)
    public void quit() {
        try {
            getDriver().quit();
            System.out.println("WebDriver Session Quit!!");
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
}
