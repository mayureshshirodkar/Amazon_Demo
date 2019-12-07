package base;

import config.Config;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class Base extends Config {


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
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceIdMatches(\"" + resource_id + "\"));"));
    }


    /**
     * Scroll to an element located by text
     * @param text text for element
     */
    protected void scrollToElementByText(String text){
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0));"));
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
     * Scroll up/down the scrollable area
     * @param direction - direction of scrolling the app
     */
    protected void scroll(String direction){
        Dimension size = getDriver().manage().window().getSize();
        int startX;
        int startY;
        int endX;
        int endY;
        if(direction.equalsIgnoreCase("down")) {
             startX = size.getWidth() / 2;
             startY = size.getHeight() / 2;
             endX = size.getWidth() / 2;
             endY = (int) (startY * 0.5);
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
    protected void relaunchApp() { getDriver().resetApp(); }


    /**
     *  Wait for application to pause for the time
     * @param time time to pause application
     */
    protected void waitTimer(int time){
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
