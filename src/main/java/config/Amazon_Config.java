package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class Amazon_Config {

    static public AppiumDriver driver;

    public void intializeDriver(){
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), androidCapabilities());
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
        androidCapabilities.setCapability("deviceName", "Moto G4");
        androidCapabilities.setCapability("automationName", "uiautomator2");
        androidCapabilities.setCapability("platformVersion", "7.0");
        androidCapabilities.setCapability("app", System.getProperty("user.dir") + "/app/amazon_shopping.apk");
        androidCapabilities.setCapability("autoAcceptAlerts", true);
        androidCapabilities.setCapability("newCommandTimeout", 500);
        androidCapabilities.setCapability("autoGrantPermissions",true);
        androidCapabilities.setCapability("dontStopAppOnReset",true);
        androidCapabilities.setCapability(  "appPackage", "com.amazon.mShop.android.shopping");
        androidCapabilities.setCapability(  "appActivity", "com.amazon.mShop.home.HomeActivity");
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
