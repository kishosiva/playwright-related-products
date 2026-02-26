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
public class RelatedProductsCategoryMatchTest extends BaseTest{
    @Test(dataProvider = "productData", dataProviderClass = CustomRelatedItemsData.class)
    public void verifyRelatedProductsCategory(RelatedItemsTestData data) {
        HomePage homePage = new HomePage(page);
        homePage.openHomePage();
        homePage.searchProduct(data.getMainProduct().getName());
        homePage.openProduct(data.getMainProduct().getUrl());

        ProductPage productPage = new ProductPage(page);
        productPage.waitForProduct();

        //boolean isVisible = productPage.isRelatedProductsVisible();
        //Assert.assertTrue(isVisible, "Related products section is visible");

        // Verify categories of related products
        boolean actualResult = productPage.verifyRelatedProductsSameCategory(data.getMainProduct().getCategory(), data.getRelatedCategories(), data.getMaxAllowed());
        assertEquals(actualResult, data.getExpectedResult02(), "Category check failed for the product: " + data.getMainProduct().getCategory());
    }
}
