import locators.*;
import org.testng.annotations.Test;

public class Purchase_Product_Scenario {
    private Amazon_Login_Page login = new Amazon_Login_Page();
    private Amazon_Search_Page search = new Amazon_Search_Page();
    private Amazon_Product_Page product = new Amazon_Product_Page();
    private Amazon_Home_Page home = new Amazon_Home_Page();
    private Amazon_Purchase_Page purchase = new Amazon_Purchase_Page();


    @Test(priority=1)
    public void AmazonLogin(){
        login.clickOnAlreadyCustomer();
        login.enterLoginEmailAddress("7020214690");
        login.clickOnContinueButton();
        login.enterLoginPaasword("test123");
        login.clickOnSignInButton();
    }

    @Test(priority=2)
    public void AmazonSearchItem() {
        home.acceptLanguagePopup();
        search.clickOnSearchTextBox();
        search.enterTextInSearchTextBox("65-inch tv");
        search.selectFromSearchOption("65-inch tv");
        search.selectItemFromList();
    }


    @Test(priority=3)
    public void AmazonAddToCart() {
        product.clickOnAddToCart();
        product.clickToViewCart();
        product.clickProceedToBuy();
    }


    @Test(priority=4)
    public void AmazonProceedToCheckOut() {
        purchase.clickUseThisAddress();
        purchase.selectPreferedTimeSlot();
        purchase.clickOnPaymentOption("Net Banking");
        purchase.selectNetBankingOption("ICICI Bank");
    }


    @Test(priority=5)
    public void AmazonDeleteProductsFromCart(){
        home.navigateToHome();
        home.deleteProductsAddedToCart();
    }

}
