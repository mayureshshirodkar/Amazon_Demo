import locators.Amazon_Login_Page;
import locators.Amazon_Search_Page;
import org.testng.annotations.Test;

public class Purchase_Amazon_Product {
    private Amazon_Login_Page login = new Amazon_Login_Page();
    private Amazon_Search_Page search = new Amazon_Search_Page();

    @Test
    public void AmazonLogin(){
        login.clickOnAlreadyCustomer();
        login.enterLoginEmailAddress("7020214690");
        login.clickOnContinueButton();
        login.enterLoginPaasword("test123");
        login.clickOnSignInButton();
    }

    @Test
    public void AmazonSearchItem(){
        search.acceptLanguagePopup();
        search.clickOnSearchTextBox();
        search.enterTextInSearchTextBox("65-inch tv");
        search.selectFromSearchOption("65-inch tv");
        search.selectItemFromList();
        search.clickOnAddToCart();
        search.clickToViewCart();
        search.clickProceedToBuy();
        search.clickUseThisAddress();
        search.selectPreferedTimeSlot();
        search.clickOnPaymentOption("Net Banking");
        search.selectNetBankingOption("ICICI Bank");


    }

}
