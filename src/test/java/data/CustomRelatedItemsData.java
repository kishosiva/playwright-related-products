package data;

import model.Product;
import model.RelatedItemsTestData;
import org.testng.annotations.DataProvider;

public class CustomRelatedItemsData {

    @DataProvider(name = "productData")
    public static Object[][] getData() {

        // ===== MAIN PRODUCT 1 =====
        Product main1 = new Product(
                "Wallet",
                "https://www.ebay.com/itm/389656904564",
                "img-main-1",
                "Accessories",
                23.99,
                700,
                true
        );

        Product[] related1 = new Product[]{
                new Product("Leather Wallet", "https://www.ebay.com/itm/406721074236", "img1", "Accessories",18.54, 800, true),
                new Product("Leather Wallet", "https://www.ebay.com/itm/125640279122", "img2", "Accessories",21.01, 1000, true),
                new Product("Travel Wallet", "https://www.ebay.com/itm/127666520211", "img3", "Accessories",25.11, 300, true),
                new Product("Men Wallet", "https://www.ebay.com/itm/29780841502", "img4", "Accessories",28.44, 740, true),
                new Product("Office Use Wallet", "https://www.ebay.com/itm/406717645172", "img5", "Accessories",30.45, 490, true),
                new Product("Casual Use Wallet", "https://www.ebay.com/itm/168185446233", "img6", "Accessories",78.25, 1200, true)
        };

        // ===== MAIN PRODUCT 2 =====
        Product main2 = new Product(
                "Shoes",
                "https://www.ebay.com/itm/317840439508",
                "img-main-2",
                "Shoes",
                189.77,
                1000,
                true
        );

        Product[] related2 = new Product[]{
                new Product("Adidas", "url1", "img1", "Shoes",179.12, 2000, true),
                new Product("", "url2", "img2", "Shoes",250.15, 5000, false),
                new Product("Puma", "url3", "img3", "Shoes",198.45, 4000, true),
                new Product("Skechers", "url4", "", "Shoes",187.14, 6000, true),
                new Product("Ecco", "url5", "img5", "Shoes",0, 1700, false),
                new Product("Converse", "url6", "img6", "Shoes",178.58, 2000, true)
        };

        // ===== MAIN PRODUCT 3 =====
        Product main3 = new Product(
                "Watch",
                "https://www.ebay.com/itm/157702258131",
                "img-main-3",
                "Watch",
                99.01,
                800,
                true
        );

        Product[] related3 = new Product[]{
                new Product("Rolex", "url1", "img1", "Watch",88.12,5000, true),
                new Product("Omega", "url2", "img2", "Watch",90.12, 3000, true),
                new Product("Cartier", "url3", "img3", "Watch",100.44, 1000, true),
                new Product("Piaget", "url4", "img4", "Watch",110.25, 1000, true),
                new Product("Hublot", "url5", "img5", "Watch",71.01, 7000, true),
                new Product("Vincero", "url6", "img6", "Watch",89.25, 1500, true)
        };

        // ===== MAIN PRODUCT 4 =====
        Product main4 = new Product(
                "Phone",
                "https://www.ebay.com/itm/186577116952",
                "img-main-4",
                "Phone",
                170.95,
                2000,
                true
        );

        Product[] related4 = new Product[]{
                new Product("iPhone 14", "url1", "img1", "Phone",165, 300, false),
                new Product("iPhone 11", "url2", "img2", "Phone",168.12, 500, true),
                new Product("iPhone 16", "url3", "img3", "Phone",170.25, 700, true),
                new Product("iPhone 14 pro", "url4", "img4", "Phone",171.02, 1500, false),
                new Product("iPhone 14", "url5", "img5", "Phone",166.22, 780, true),
                new Product("iPhone 13", "url6", "img6", "Phone",174.25, 380, false)
        };

        return new Object[][]{

                { new RelatedItemsTestData(
                        main1,
                        related1,
                        6,
                        6,
                        new String[]{"Accessories","Accessories","Accessories","Accessories","Accessories","Accessories"},
                        new double[]{18.45, 21.01, 25.11, 28.44, 30.45, 18.25},
                        0.30,
                        true,
                        true,
                        true,
                        false,
                        true,
                        false, // apiFailed
                        ""     // no message
                )},

                { new RelatedItemsTestData(
                        main2,
                        related2,
                        10,
                        6,
                        new String[]{"Shoes","Shoes","Shoes","bag","Shoes","Shoes"},
                        new double[]{179.12, 250.15, 198.45, 187.14, 133.25, 178.58},
                        0.25,
                        false,
                        false,
                        false,
                        true,
                        false,
                        true, // apiFailed
                        "Unable to load related products"

                )},

                { new RelatedItemsTestData(
                        main3,
                        related3,
                        4,
                        6,
                        new String[]{"Watch","Watch","Watch","Watch","wallet","Watch"},
                        new double[]{88.12, 90.12, 100.44, 110.25, 71.01, 89.25},
                        0.20,
                        true,
                        false,
                        false,
                        true,
                        true,
                        false, // apiFailed
                        ""     // no message

                )},
                { new RelatedItemsTestData(
                        main4,
                        related4,
                        0,
                        6,
                        new String[]{"Phone","Phone","Phone","Phone","Phone","Phone"},
                        new double[]{165, 168.12, 170.25, 171.02, 166.22, 174.25},
                        0.20,
                        true,
                        true,
                        true,
                        false,
                        false,
                        true, // apiFailed
                        "Unable to load related products"

                )}
        };
    }
}