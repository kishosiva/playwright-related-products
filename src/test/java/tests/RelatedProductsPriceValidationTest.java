package tests;

import base.BaseTest;
import data.CustomRelatedItemsData;
import model.RelatedItemsTestData;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

import static org.testng.Assert.assertEquals;

@Listeners(listeners.TestListener.class)
public class RelatedProductsPriceValidationTest extends BaseTest{
    @Test(dataProvider = "productData", dataProviderClass = CustomRelatedItemsData.class)
    public void verifyRelatedProductsPriceValidation(RelatedItemsTestData data) {
        HomePage homePage = new HomePage(page);
        homePage.openHomePage();
        homePage.searchProduct(data.getMainProduct().getName());
        homePage.openProduct(data.getMainProduct().getUrl());

        ProductPage productPage = new ProductPage(page);
        productPage.waitForProduct();

        boolean isVisible = productPage.isRelatedProductsVisible();
        Assert.assertTrue(isVisible, "Related products section is visible");

        boolean actualResult = productPage.verifyRelatedProductsPriceRange(data.getMainProduct().getPrice(), data.getRelatedPrices(), data.getPercentage());
        assertEquals(actualResult, data.getExpectedResult03(), "Price range validation failed: " + data.getMainProduct().getPrice());
    }
}
