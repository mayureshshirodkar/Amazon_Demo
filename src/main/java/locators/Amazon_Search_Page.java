package locators;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Amazon_Search_Page extends Base {

    private String itemSearchTextBoxID(){ return "rs_search_src_text"; }

    private String searchItemsLocator(){ return "item_title";}

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
        waitTimer(2000);
    }


    public void searchForProduct(String search_text){
        clickOnSearchTextBox();
        enterTextInSearchTextBox(search_text);
        selectFromSearchOption(search_text);
    }




}
