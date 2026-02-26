package tests;

import base.BaseTest;
import data.CustomRelatedItemsData;
import model.RelatedItemsTestData;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

@Listeners(listeners.TestListener.class)
public class BestSellerProductsTest extends BaseTest {
    @Test(dataProvider = "productData", dataProviderClass = CustomRelatedItemsData.class)
    public void verifyOnlyBestSellerProducts(RelatedItemsTestData data) {
        HomePage homePage = new HomePage(page);
        homePage.openHomePage();
        homePage.searchProduct(data.getMainProduct().getName());
        homePage.openProduct(data.getMainProduct().getUrl());

        ProductPage productPage = new ProductPage(page);
        productPage.waitForProduct();

        boolean isVisible = productPage.isRelatedProductsVisible();
        Assert.assertTrue(isVisible, "Related products section is visible");

        // Verify only bestsellers
        int threshold = 1000;

        boolean actual = productPage.verifyOnlyBestSellerProducts(data.getRelatedProducts(), threshold
        );
        Assert.assertEquals(actual, data.getExpectedResult04(), "Best seller validation failed for: " + data.getMainProduct().getName()
        );
    }
}
