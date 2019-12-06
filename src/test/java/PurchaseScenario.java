import pages.*;
import org.testng.annotations.Test;

public class PurchaseScenario {
    private LoginPage login = new LoginPage();
    private SearchPage search = new SearchPage();
    private ProductPage product = new ProductPage();
    private HomePage home = new HomePage();
    private PurchasePage purchase = new PurchasePage();


    @Test(priority=1, dataProvider = "scenario_details", dataProviderClass = TestDataProvider.class)
    public void AmazonLogin(String user, String password){
        login.clickOnAlreadyCustomer();
        login.enterUserName(user);
        login.enterLoginPassword(password);
        login.clickOnSignInButton();
        login.verifySuccessfulLogin();
    }

    @Test(priority=2, dataProvider = "scenario_details", dataProviderClass = TestDataProvider.class)
    public void AmazonSearchItem(String search_item) {
        home.acceptLanguagePopup();
        search.searchForProduct(search_item);
        search.selectItemFromList();
        product.clickOnAddToCart();
        product.clickToViewCart();
        product.clickProceedToBuy();
    }


    @Test(priority=3, dataProvider = "scenario_details", dataProviderClass = TestDataProvider.class)
    public void AmazonProceedToCheckOut(String payment_details) {
        purchase.clickUseThisAddress();
        purchase.selectPreferedTimeSlot();
        purchase.selectPaymentAndProceed(payment_details);
    }


    @Test(priority=4)
    public void AmazonDeleteProductsFromCart(){
        home.navigateToHome();
        home.deleteProductsAddedToCart();
    }


}
