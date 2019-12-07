package pages;

import base.Base;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage extends Base {

    private String itemSearchTextBoxID(){ return "rs_search_src_text"; }

    private String searchItemsLocator(){ return "item_title";}

    private String searchItemsPriceLocator(){ return "//android.view.ViewGroup[contains(@resource-id,'rs_results_styled_price_v2')]/android.widget.TextView";}

    private String searchResultXpath(String value){ return "//android.widget.TextView[@text='" + value + "']"; }


    public void clickOnSearchTextBox(){
        clickElementByID(itemSearchTextBoxID());
    }

    public void enterTextInSearchTextBox(String value){
        sendKeysToElementByID(itemSearchTextBoxID(), value);
    }

    public void selectFromSearchOption(String value){
        clickElementByXpath(searchResultXpath(value));
    }

    public void selectItemFromList(){
        List<WebElement> element = findElementsByID(searchItemsLocator());
        element.get(1).click();
    }


    public void searchForProduct(String search_text){
        clickOnSearchTextBox();
        enterTextInSearchTextBox(search_text);
        selectFromSearchOption(search_text);
    }

    public String getProductTitleOnSearchPage(){
        return findElementsByID(searchItemsLocator()).get(1).getText();
    }

    public String getProductPriceOnSearchPage(){
        return findElementsByXpath(searchItemsPriceLocator()).get(1).getText().replace("₹","").split(" ")[0];
    }


}
