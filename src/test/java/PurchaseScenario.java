import amazon.base.Base;
import amazon.pages.*;
import amazon.utilities.Report;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import amazon.utilities.Utils;

import java.lang.reflect.Method;

public class PurchaseScenario {
    private Base base =new Base();
    private LoginPage login = new LoginPage();
    private SearchPage search = new SearchPage();
    private ProductPage product = new ProductPage();
    private HomePage home = new HomePage();
    private PurchasePage purchase = new PurchasePage();

    private String searchProductTitle;
    private String searchProductPrice;
    private String test_name;


    @Test(description = "Existing customer logs in to the app - (False Scenario)")
    public void AmazonIncorrectLogin(){
        String user = Utils.getValue(test_name, "username");
        String password = Utils.getValue(test_name, "password");

        login.clickOnAlreadyCustomer();
        login.enterUserName(user);
        login.enterLoginPassword(password);
        login.clickOnSignInButton();
        login.verifySuccessfulLogin();
    }

    @Test(description = "Existing customer logs in to the app")
    public void AmazonCorrectLogin(){
        String user = Utils.getValue(test_name, "username");
        String password = Utils.getValue(test_name, "password");

        base.relaunchApp();
        login.clickOnAlreadyCustomer();
        login.enterUserName(user);
        login.enterLoginPassword(password);
        login.clickOnSignInButton();
        login.verifySuccessfulLogin();
    }

    @Test(description = "Customer selects a product after search")
    public void AmazonSearchItem() {
        String search_item = Utils.getValue(test_name, "search item");

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


    @Test(description = "Customer purchases the product")
    public void AmazonProceedToCheckOut() {
        String payment_details = Utils.getValue(test_name, "payment mode");

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


    @BeforeMethod
    public void beforeMethod(Method method_name){
        test_name = method_name.getName();
        Report.createExtentTest(test_name);

    }


    @AfterMethod
    public void afterMethod(ITestResult testResult){
        Report.flushExtentTest();
    }

}
