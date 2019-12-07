import data.TestDataProvider;
import pages.*;
import org.testng.annotations.Test;

public class PurchaseScenario {
    private LoginPage login = new LoginPage();
    private SearchPage search = new SearchPage();
    private ProductPage product = new ProductPage();
    private HomePage home = new HomePage();
    private PurchasePage purchase = new PurchasePage();
    private String searchProductTitle;
    private String searchProductPrice;


    @Test(description = "Existing customer logs in to the app", dataProvider = "scenario_details",
            dataProviderClass = TestDataProvider.class)
    public void AmazonLogin(String user, String password){
        login.clickOnAlreadyCustomer();
        login.enterUserName(user);
        login.enterLoginPassword(password);
        login.clickOnSignInButton();
        login.verifySuccessfulLogin();
    }

    @Test(description = "Customer selects a product after search", dataProvider = "scenario_details",
            dataProviderClass = TestDataProvider.class)
    public void AmazonSearchItem(String search_item) {
        home.acceptLanguagePopup();
        search.searchForProduct(search_item);
        searchProductTitle = search.getProductTitleOnSearchPage();
        searchProductPrice = search.getProductPriceOnSearchPage();
        search.selectItemFromList();
        product.verifyCorrectProductSelected(searchProductTitle);
    }

    @Test(description = "Customer adds product to cart")
    public void AmazonProductDetails() {
        product.clickOnAddToCart();
        product.clickToViewCart();
        product.verifyCartDetails(searchProductTitle, searchProductPrice);
        product.clickProceedToBuy();
    }


    @Test(description = "Customer purchases the product", dataProvider = "scenario_details",
            dataProviderClass = TestDataProvider.class)
    public void AmazonProceedToCheckOut(String payment_details) {
        purchase.clickUseThisAddress();
        purchase.selectPreferedTimeSlot();
        purchase.selectPaymentAndProceed(payment_details);
        purchase.verifyUserOnPlaceOrder();
    }


    @Test(description = "Customer clears the cart")
    public void AmazonDeleteProductsFromCart(){
        home.navigateToHome();
        home.deleteProductsAddedToCart();
    }


}
