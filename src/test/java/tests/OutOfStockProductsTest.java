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
public class OutOfStockProductsTest extends BaseTest {
    @Test(dataProvider = "productData", dataProviderClass = CustomRelatedItemsData.class)
    public void verifyOutOfStockProducts(RelatedItemsTestData data) {
        HomePage homePage = new HomePage(page);
        homePage.openHomePage();
        homePage.searchProduct(data.getMainProduct().getName());
        homePage.openProduct(data.getMainProduct().getUrl());

        ProductPage productPage = new ProductPage(page);
        productPage.waitForProduct();

        //boolean isVisible = productPage.isRelatedProductsVisible();
        //Assert.assertTrue(isVisible, "Related products section is visible");

        boolean actual = productPage.verifyOnlyInStockProducts(data.getRelatedProducts());
        boolean expected = data.isExpectOnlyInStock();

        Assert.assertEquals(actual, expected, "Stock validation failed!");
    }

}
