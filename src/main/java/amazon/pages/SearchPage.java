package amazon.pages;

import amazon.base.Base;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage extends Base {

    private String itemSearchTextBoxID = "rs_search_src_text";

    private String searchItemsLocator = "item_title";

    private String searchItemsPriceLocator = "//android.view.ViewGroup[contains(@resource-id,'rs_results_styled_price_v2')]/android.widget.TextView";

    private String searchResultXpath(String value){ return "//android.widget.TextView[@text='" + value + "']"; }

    /**
     * Tap on the search text box
     */
    public void clickOnSearchTextBox(){
        clickElementByID(itemSearchTextBoxID);
    }

    /**
     * Enter search text in search text box
     */
    public void enterTextInSearchTextBox(String value){
        sendKeysToElementByID(itemSearchTextBoxID, value);
    }

    /**
     * Enter text in search text box
     */
    public void selectFromSearchOption(String value){
        clickElementByXpath(searchResultXpath(value));
    }

    /**
     * Select an item from the list of search
     */
    public void selectItemFromList(){
        List<WebElement> element = findElementsByID(searchItemsLocator);
        element.get(1).click();
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