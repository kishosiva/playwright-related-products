package tests;

import base.BaseTest;
import data.CustomRelatedItemsData;
import model.Product;
import model.RelatedItemsTestData;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

import java.util.Random;

@Listeners(listeners.TestListener.class)
public class RelatedProductNavigationTest extends BaseTest {
    @Test(dataProvider = "productData", dataProviderClass = CustomRelatedItemsData.class)
    public void verifyRelatedProductNavigation(RelatedItemsTestData data) {
        Product[] related = data.getRelatedProducts();

        HomePage homePage = new HomePage(page);
        homePage.openHomePage();
        homePage.searchProduct(data.getMainProduct().getName());
        homePage.openProduct(data.getMainProduct().getUrl());

        ProductPage productPage = new ProductPage(page);
        productPage.waitForProduct();

        boolean isVisible = productPage.isRelatedProductsVisible();
        Assert.assertTrue(isVisible, "Related products section is visible");

        // Navigation to related product
        Random rand = new Random();
        int index = rand.nextInt(related.length);

        Product clickedProduct = productPage.navigateToRelatedProduct(index, data);

        Assert.assertNotNull(clickedProduct, "Navigation failed");
        Assert.assertEquals(clickedProduct.getUrl(), related[index].getUrl(), "URL mismatch");
    }


}
