package amazon.base;

import amazon.config.Config;
import amazon.pages.LoginPage;
import amazon.utilities.Report;
import amazon.utilities.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Base extends Config {

    private static Logger baseLogger = LogManager.getLogger(LoginPage.class);


    /**
     * Wait explicitly for an element to be visible.
     * @param by Element to be visible
     * @param waitTime time in secs
     */
    protected WebElement waitForVisibilityOfElement(By by, int waitTime) {
        try {
            return new WebDriverWait(getDriver(), waitTime).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            Utils.debugLog(baseLogger,"Error while waiting for visibility of element- \n" + e.getMessage() + "\n");
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
            Utils.debugLog(baseLogger,"Error while waiting for invisibility of element- \n" + e.getMessage() + "\n");
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
            Utils.debugLog(baseLogger,"Error while waiting for presence of element- \n" + e.getMessage()+ "\n");
        }
        return null;
    }


    /**
     * Search for element by Xpath
     * @param xpath xpath locator for element
     */
    protected WebElement findElementByXpath(String xpath){
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
     * @param log logger for the class
     * @param message message to be written to report
     */
    protected void clickElementByID(String id, Logger log, String message){
        try {
            findElementByID(id).click();
            Utils.passStep(log,message);
        }
        catch (Exception e){
            Utils.failStep(log, message, takeScreenshot(), e.getMessage());
        }
    }


    /**
     * Send keys to an element located by ID
     * @param id id locator for element
     * @param log logger for the class
     * @param message message to be written to report
     */
    protected void sendKeysToElementByID(String id, String value, Logger log, String message){
        try {
            findElementByID(id).sendKeys(value);
            Utils.passStep(log, message);
        }
        catch (Exception e){
            Utils.failStep(log, message, takeScreenshot(), e.getMessage());
        }
    }


    /**
     * Send keys to an element located by XPATH
     * @param xpath xpath locator for element
     * @param log logger for the class
     * @param message message to be written to report
     */
    protected void sendKeysToElementByXpath(String xpath, String value, Logger log, String message){
        try {
            findElementByXpath(xpath).sendKeys(value);
            Utils.passStep(log,message);
        }
        catch (Exception e){
            Utils.failStep(log, message, takeScreenshot(), e.getMessage());
        }
    }


    /**
     * Click on element located by Xpath
     * @param xpath xpath locator for element
     * @param log logger for the class
     * @param message message to be written to report
     */
    protected void clickElementByXpath(String xpath, Logger log, String message){
        try{
            findElementByXpath(xpath).click();
            Utils.passStep(log,message);
        }
        catch (Exception e){
            Utils.failStep(log, message, takeScreenshot(), e.getMessage());
        }
    }


    /**
     * Click on elements from list located by Xpath
     * @param xpath xpath locator for element
     * @param log logger for the class
     * @param message message to be written to report
     */
    protected void clickElementsByXpath(String xpath, Logger log, String message){
        try {
            List<WebElement> elements = findElementsByXpath(xpath);
            for (WebElement element : elements) {
                element.click();
            }
            Utils.passStep(log,message);
        }
        catch (Exception e){
            Utils.failStep(log, message, takeScreenshot(), e.getMessage());
        }
    }


    /**
     * Check if element is displayed
     * @param xpath xpath locator for element
     */
    protected boolean isElementDisplayed(String xpath){
        try {
            WebElement element = waitForVisibilityOfElement(By.xpath(xpath), 15);

            if(element !=null)
                return element.isDisplayed();
        }
        catch (Exception e){
            Utils.debugLog(baseLogger,"Error while waiting for visibility of element- \n" + e.getMessage() + "\n");
        }
        return false;
    }



    /**
     * Scroll to an element located by text
     * @param text text for element
     */
    protected void scrollToElementByText(String text){
        try {
            getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0));"));
        }
        catch(Exception e){
            Utils.debugLog(baseLogger,"Error while scrolling to element- " + e.getMessage()+ "\n");
        }
    }

    /**
     *  Wait for application to pause for the time
     * @param time time to pause application in seconds
     */
    protected void waitTimer(int time){
        try {
            Thread.sleep(time*1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Scroll to an element located by ResourceID
     * @param resource_id resource id locator for element
     */
    public void scrollToElementByResourceID(String resource_id){
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceIdMatches(\"" + resource_id + "\"));"));
    }

    /**
     * Fling to top of the scrollable page using UIAutomator
     */
    protected void scrollToTop(){
        ((AndroidDriver) getDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).flingBackward();");
    }

    /**
     * Fling to bottom of the scrollable page using UIAutomator
     */
    protected void scrollBottom(){
        ((AndroidDriver) getDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).flingForward();");
    }

    /**
     * Swipe up/down the scrollable area
     * @param direction - direction of scrolling the scrollable ui
     */
    protected void swipeInDirection(String direction){
        Dimension size = getDriver().manage().window().getSize();
        int startX;
        int startY;
        int endX;
        int endY;
        if(direction.equalsIgnoreCase("down")) {
             startX = size.getWidth() / 2;
             startY = (int) (size.getHeight () * 0.75);
             endX = size.getWidth() / 2;
             endY = (int) (size.getHeight() * 0.2);
        }
        else{
             startX = size.getWidth () / 2;
             startY = (int) (size.getHeight () * 0.20);
             endX = size.getWidth () / 2;
             endY = (int) (size.getHeight () * 0.75);
        }


        TouchAction action = new TouchAction (getDriver());
        action.press (PointOption.point(startX, startY))
                .moveTo (PointOption.point(endX, endY))
                .release ()
                .perform ();
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
    public void relaunchApp() { getDriver().resetApp(); }


    /**
     * Take Screenshot on error
     * @return  temporary screenshot file path
     */
    public String takeScreenshot() {
        String path = "";
        try {
            File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

            path = System.getProperty("user.dir")+"/"+System.currentTimeMillis()+".jpg";

            FileUtils.copyFile(scrFile, new File(path));

        } catch (IOException io) {
            Utils.debugLog(baseLogger,"Error Taking Screenshot - " + io.getMessage());
        }
        return path;
    }


    /**
     * Assertion if element is present
     * @param  xpath xpath of element
     * @param  verify_msg verification message
     */
    protected void assertIfElementPresent(String xpath, String verify_msg) {

        if(waitForVisibilityOfElement(By.xpath(xpath), 10) != null) {
            Report.logStatusPass(verify_msg);
        }
        else{
            String temp = takeScreenshot();
            Report.logStatusFail(verify_msg, temp);
            Assert.fail("Assertion Failed!");
            }
        }

    /**
     * Assertion if element is actual and expected values are same
     * @param  actual actual value
     * @param  expected expected value to be verified
     * @param  verify_msg verification message
     */
    protected void assertIfEqual(String actual, String expected, String verify_msg) {

        if(actual.equalsIgnoreCase(expected)) {
            Report.logStatusPass(verify_msg);
        }
        else{
            String temp = takeScreenshot();
            Report.logStatusFail(verify_msg, temp);
            Assert.fail("Assertion Failed!");
        }
    }

    /**
     * Assertion if element is actual and expected values are same
     * @param  actual actual value
     * @param  expected expected value to be verified
     * @param  verify_msg verification message
     */
    protected void assertIfTrue(Set<String> actual, String expected, String verify_msg) {

        if(actual.contains(expected)) {
            Report.logStatusPass(verify_msg);
        }
        else{
            String temp = takeScreenshot();
            Report.logStatusFail(verify_msg, temp);
            Assert.fail("Assertion Failed!");
        }
    }

}
