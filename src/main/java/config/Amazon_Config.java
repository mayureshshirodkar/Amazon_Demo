package config;

import base.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class Amazon_Config {

    static public AppiumDriver driver;
    public String working_directory = System.getProperty("user.dir");

    public void intializeDriver(){
        try {
            driver = new AndroidDriver(new URL(Utils.getValueForParam("url_android")), androidCapabilities());
        }
        catch(MalformedURLException e){
            System.out.println("Url exception while defining the driver: \n");
            e.printStackTrace();
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    private DesiredCapabilities androidCapabilities(){
        System.out.println("Setting Android Desired Capabilities");
        DesiredCapabilities androidCapabilities = new DesiredCapabilities();
        androidCapabilities.setCapability("deviceName", Utils.getValueForParam("android_device"));
        androidCapabilities.setCapability("automationName", Utils.getValueForParam("automation_type"));
        androidCapabilities.setCapability("app", working_directory + Utils.getValueForParam("android_app"));
        androidCapabilities.setCapability("platformVersion", Utils.getValueForParam("android_version"));
        androidCapabilities.setCapability("newCommandTimeout", 500);
        androidCapabilities.setCapability("autoAcceptAlerts", true);
        androidCapabilities.setCapability("autoGrantPermissions",true);
        androidCapabilities.setCapability("dontStopAppOnReset",true);
        androidCapabilities.setCapability(  "appPackage", Utils.getValueForParam("app_package"));
        androidCapabilities.setCapability(  "appActivity", Utils.getValueForParam("app_activity"));
        return androidCapabilities;
    }

    public void quitAppium() {
        try {
            getDriver().quit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
