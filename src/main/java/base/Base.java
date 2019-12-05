package base;

import config.Amazon_Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Base extends Amazon_Config{

    private static Map<String,String> parameters =new HashMap<String, String>();

    /**
     * Wait explicitly for an element to be visible.
     * @param by Element to be visible
     * @param waitTime time in secs
     */
    protected WebElement waitForVisibilityOfElement(By by, int waitTime) {
        try {
            return new WebDriverWait(getDriver(), waitTime).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Wait explicitly for an element to be visible.
     * @param by Element to be visible
     * @param waitTime time in secs
     */
    protected Boolean waitForElementToBeInvisible(By by, int waitTime) {
        try {
             return new WebDriverWait(getDriver(), waitTime).until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Wait explicitly for an element to be visible.
     * @param by Element to be visible
     * @param waitTime time in secs
     */
    protected List<WebElement> waitForPresenceOfElements(By by, int waitTime) {
        try {
            return new WebDriverWait(getDriver(), waitTime).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected WebElement findElementByXpath(String xpath){ return waitForVisibilityOfElement(By.xpath(xpath), 15); }

    protected List<WebElement> findElementsByXpath(String xpath){ return waitForPresenceOfElements(By.xpath(xpath), 15); }

    protected WebElement findElementByID(String id){
        return waitForVisibilityOfElement(By.id(id), 15);
    }

    protected List<WebElement> findElementsByID(String id){
        return waitForPresenceOfElements(By.id(id), 15);
    }




    protected void clickElementByID(String id){ findElementByID(id).click(); }

    protected void sendKeysToElementByID(String id, String value){
        findElementByID(id).sendKeys(value);
    }

    protected void sendKeysToElementByXpath(String xpath, String value){
        findElementByXpath(xpath).sendKeys(value);
    }

    protected void clickElementByXpath(String xpath){ findElementByXpath(xpath).click(); }

    public void scrollToElementByResourceID(String resource_id){
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"" + resource_id + "\"));"));
    }

    protected void scrollToElementByText(String text){
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
    }


    protected void uninstallApp() {
        getDriver().removeApp("bundle_id");
    }


    protected void relaunchApp() { getDriver().resetApp(); }


    /**
     * readInternalHash - Will read the specified parameter from the local data structure which
     * is loaded by readEntirePropertyFile function
     * @param param - parameter name to read from the file
     * @return  returns the value of the parameter
     */
    protected static String readInternalHash(String param)
    {
        if(parameters.containsKey(param.toLowerCase()))
        {
            return parameters.get(param).trim();
        }
        else if(param.startsWith("//") || param.startsWith("(//"))
            return param;
        else
        {
            return "";
        }
    }
    /**
     * readEntirePropertyFile - will read the entire property file in local data structure
     * which can then be read using readInternalHash function
     * @param filename - name of the file to read
     * @return returns the value of the parameter
     */
    protected static void readEntirePropertyFile(String filename)
    {
        try {
            File file = new File(filename);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            Enumeration<?> e = properties.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = properties.getProperty(key);
                parameters.put(key.toLowerCase(), value);
            }
            System.out.println("Reading "+filename);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    protected void waitTimer(int time){
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
