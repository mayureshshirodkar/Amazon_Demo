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
     * Wait explicitly for an element to be invisible.
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
     * Wait explicitly for an elements to be present on the screen.
     * @param by Element to be visible
     * @param waitTime time in secs
     */
    private List<WebElement> waitForPresenceOfElements(By by, int waitTime) {
        try {
            return new WebDriverWait(getDriver(), waitTime).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Search for element by Xpath
     * @param xpath xpath locator for element
     */
    private WebElement findElementByXpath(String xpath){
        return waitForVisibilityOfElement(By.xpath(xpath), 15);
    }


    /**
     * Search for elemens by Xpath
     * @param xpath xpath locator for element
     */
    protected List<WebElement> findElementsByXpath(String xpath){
        return waitForPresenceOfElements(By.xpath(xpath), 15);
    }


    /**
     * Search for elements by id
     * @param id id locator for element
     */
    private WebElement findElementByID(String id){
        return waitForVisibilityOfElement(By.id(id), 15);
    }


    /**
     * Search for elements by id
     * @param id id locator for element
     */
    protected List<WebElement> findElementsByID(String id){
        return waitForPresenceOfElements(By.id(id), 20);
    }


    /**
     * Click on element located by ID
     * @param id id locator for element
     */
    protected void clickElementByID(String id){ findElementByID(id).click(); }


    /**
     * Send keys to an element located by ID
     * @param id id locator for element
     */
    protected void sendKeysToElementByID(String id, String value){
        findElementByID(id).sendKeys(value);
    }


    /**
     * Send keys to an element located by XPATH
     * @param xpath xpath locator for element
     */
    protected void sendKeysToElementByXpath(String xpath, String value){
        findElementByXpath(xpath).sendKeys(value);
    }


    /**
     * Click on element located by Xpath
     * @param xpath xpath locator for element
     */
    protected void clickElementByXpath(String xpath){ findElementByXpath(xpath).click(); }


    /**
     * Scroll to an element located by ResourceID
     * @param resource_id resource id locator for element
     */
    public void scrollToElementByResourceID(String resource_id){
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"" + resource_id + "\"));"));
    }


    /**
     * Scroll to an element located by text
     * @param text text for element
     */
    protected void scrollToElementByText(String text){
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
    }


    /**
     * Uninstall app based on bundle id of the app
     * @param bundle_id id for the app
     */
    protected void uninstallApp(String bundle_id) {
        getDriver().removeApp(bundle_id);
    }


    /**
     * Reset the state of the app
     */
    protected void relaunchApp() { getDriver().resetApp(); }


    protected void waitTimer(int time){
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
