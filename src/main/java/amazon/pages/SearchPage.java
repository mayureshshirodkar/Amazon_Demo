package amazon.pages;

import amazon.base.Base;
import amazon.utilities.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage extends Base {

    private static Logger searchPageLogger = LogManager.getLogger(SearchPage.class);

    private String itemSearchTextBoxID = "rs_search_src_text";

    private String searchItemsLocator = "item_title";

    private String searchItemsPriceLocator = "//*[contains(@resource-id,'rs_results_styled_price_v2')]/android.widget.TextView";

    private String searchResultXpath(String value){ return "//android.widget.TextView[@text='" + value + "']"; }


    /**
     * Tap on the search text box
     */
    public void clickOnSearchTextBox(){
        clickElementByID(itemSearchTextBoxID, searchPageLogger, "User clicks on search text box");
    }

    /**
     * Enter search text in search text box
     */
    public void enterTextInSearchTextBox(String value){
        sendKeysToElementByID(itemSearchTextBoxID, value, searchPageLogger, "User enters text in search text box");
    }

    /**
     * Enter text in search text box
     */
    public void selectFromSearchOption(String value){
        clickElementByXpath(searchResultXpath(value), searchPageLogger, "User selects an option from the search");
    }

    /**
     * Select an item from the list of search
     */
    public void selectItemFromList(){
        List<WebElement> element = findElementsByID(searchItemsLocator);
        element.get(1).click();
        Utils.passStep(searchPageLogger, "User selects an item from list of products");
    }

    /**
     * Search for a product and view its details
     */
    public void searchForProduct(String search_text){
        clickOnSearchTextBox();
        enterTextInSearchTextBox(search_text);
        selectFromSearchOption(search_text);
    }

    /**
     * Get Product title from search list
     */
    public String getProductTitleOnSearchPage(){
        return findElementsByID(searchItemsLocator).get(1).getText();
    }

    /**
     * Get Product price from search list
     */

    public String getProductPriceOnSearchPage(){
        return findElementsByXpath(searchItemsPriceLocator).get(1).getText().replace("₹","").split(" ")[0];
    }


}
