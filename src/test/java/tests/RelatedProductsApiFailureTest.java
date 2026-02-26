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
public class RelatedProductsApiFailureTest extends BaseTest {

    @Test(dataProvider = "productData", dataProviderClass = CustomRelatedItemsData.class)
    public void verifyRelatedProductsApiFailure(RelatedItemsTestData data) {
        HomePage homePage = new HomePage(page);
        homePage.openHomePage();
        homePage.searchProduct(data.getMainProduct().getName());
        homePage.openProduct(data.getMainProduct().getUrl());

        ProductPage productPage = new ProductPage(page);
        productPage.waitForProduct();

        boolean isVisible = productPage.isRelatedProductsVisible();
        Assert.assertTrue(isVisible, "Related products section is visible");

        String actualMessage = productPage.handleRelatedProductsAPI(
                data.getRelatedProducts(),
                data.isApiFailed()
        );

        if (data.isApiFailed()) {
            System.out.println("Expected Error Message: " + data.getApiExpectedMessage());
            System.out.println("Actual Message: " + actualMessage);

            Assert.assertEquals(actualMessage,
                    data.getApiExpectedMessage(),
                    "Fallback message not displayed correctly!");
        } else {
            Assert.assertEquals(actualMessage, "success");
        }
    }

}
