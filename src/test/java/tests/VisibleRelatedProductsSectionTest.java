package tests;

import base.BaseTest;
import data.CustomRelatedItemsData;
import model.RelatedItemsTestData;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

import static org.testng.Assert.assertTrue;

@Listeners(listeners.TestListener.class)
public class VisibleRelatedProductsSectionTest extends BaseTest {
    @Test(dataProvider = "productData", dataProviderClass = CustomRelatedItemsData.class)
    public void verifyRelatedProductsSection(RelatedItemsTestData data) {
        HomePage homePage = new HomePage(page);
        // Open eBay home page
        homePage.openHomePage();
        //Search product
        homePage.searchProduct(data.getMainProduct().getName());
        //Open specific product
        homePage.openProduct(data.getMainProduct().getUrl());

        //Wait for product page to load
        ProductPage productPage = new ProductPage(page);
        productPage.waitForProduct();

        //Verify Related products section is visible
        boolean isVisible = productPage.isRelatedProductsVisible();
        assertTrue(isVisible, "Related products section should be visible for " + data.getMainProduct().getName());
    }
}
