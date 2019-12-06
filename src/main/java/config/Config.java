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

    static private AppiumDriver driver;
    private String working_directory = System.getProperty("user.dir");

    private DesiredCapabilities androidCapabilities(){
        System.out.println("Setting Android Desired Capabilities");
        DesiredCapabilities androidCapabilities = new DesiredCapabilities();
        androidCapabilities.setCapability("deviceName", Utils.getValueForParam("android_device"));
        androidCapabilities.setCapability("automationName", Utils.getValueForParam("automation_type"));
        androidCapabilities.setCapability("app", working_directory + Utils.getValueForParam("android_app"));
        androidCapabilities.setCapability("platformVersion", Utils.getValueForParam("android_version"));
        androidCapabilities.setCapability("newCommandTimeout", Utils.getValueForParam("timeout"));
        androidCapabilities.setCapability("appPackage", Utils.getValueForParam("app_package"));
        androidCapabilities.setCapability("appActivity", Utils.getValueForParam("app_activity"));
        androidCapabilities.setCapability("autoAcceptAlerts", true);
        androidCapabilities.setCapability("autoGrantPermissions",true);
        androidCapabilities.setCapability("dontStopAppOnReset",true);
        return androidCapabilities;
    }

    @BeforeTest(alwaysRun = true)
    public void intializeDriver(){
        try {
            Utils.readEntirePropertyFile(working_directory + "/config/configuration.properties");
            driver = new AndroidDriver(new URL(Utils.getValueForParam("url_android")), androidCapabilities());
        }
        catch(MalformedURLException e){
            System.out.println("Url exception while defining the driver: \n");
            e.printStackTrace();
        }
    }

    @AfterTest(alwaysRun = true)
    public void quitAppium() {
        try {
            getDriver().quit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    protected AppiumDriver getDriver() {
        return driver;
    }
}
