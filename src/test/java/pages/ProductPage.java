package pages;

import com.microsoft.playwright.Page;
import model.Product;
import model.RelatedItemsTestData;

import java.util.HashSet;
import java.util.Set;

public class ProductPage {

    private Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    // Wait for product page to load
    public void waitForProduct() {
        try {
            page.waitForSelector("#itemTitle, h1.x-item-title__mainTitle",
                    new Page.WaitForSelectorOptions().setTimeout(60000));
            System.out.println("Product page loaded successfully");
        } catch (Exception e) {
            throw new RuntimeException("BLOCKED by CAPTCHA or slow network");
        }
        // Scroll to bottom to trigger lazy load
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
        page.waitForTimeout(2000);
    }

    // Verify if Related products section is visible
    public boolean isRelatedProductsVisible() {
        try {
            // Scroll down to load lazy sections
            page.mouse().wheel(0, 3000);
            page.waitForTimeout(3000);
            // Try multiple real selectors
            String relatedProductsSection =
                    "section:has-text('People who viewed this item also viewed')," +
                            "section:has-text('Similar items')," +
                            "section:has-text('Sponsored items')," +
                            "div[data-testid='x-similar-items']";

            page.waitForSelector(relatedProductsSection,
                    new Page.WaitForSelectorOptions().setTimeout(20000));
            System.out.println("Related products section is visible");
            return true;
        } catch (Exception e) {
            System.out.println("Related products section is not visible");
            return false;
        }
    }

    // Verify maximum 6 related products display
    public boolean verifyMaxRelatedProducts(int visibleItems, int maxAllowed) {
        System.out.println("Visible related products: " + visibleItems);
        System.out.println("Maximum allowed to visible: " + maxAllowed);

        if (visibleItems > maxAllowed) {
            System.out.println("WARNING: More than " + maxAllowed + " products visible: " + visibleItems);
            return false; // Fail if actually exceeded
        } else if (visibleItems == 0) {
            System.out.println( "No related products found");
            return true;
        } else if (visibleItems < maxAllowed)
            System.out.println( "products visible: " + visibleItems);
        return true;
    }

    // Verify that related products belong to the same category as the main product
    public boolean verifyRelatedProductsSameCategory(String mainCategory, String[] relatedCategories, int maxVisible) {

        int itemsToCheck = Math.min(maxVisible, relatedCategories.length);

        for (int i = 0; i < itemsToCheck; i++) {
            if (!relatedCategories[i].equalsIgnoreCase(mainCategory)) {
                System.out.println("Related products " + " category mismatch: " + relatedCategories[i]);
                return false; // Fail if any mismatch
            }
        }

        System.out.println("All related products match the main category: " + mainCategory);
        return true; // Pass if all match
    }

    // Verify that related products fall within the defined price range
    public boolean verifyRelatedProductsPriceRange(double mainPrice, double[] relatedPrices, double percentage) {
        if (mainPrice == 0.0) return false;

        double minPrice = mainPrice * (1 - percentage);
        double maxPrice = mainPrice * (1 + percentage);

        System.out.println("Main Price: " + mainPrice +
                " | Allowed Range: " + minPrice + " - " + maxPrice);

        boolean allValid = true; // track overall result

        for (int i = 0; i < relatedPrices.length; i++) {
            double price = relatedPrices[i];
            System.out.println("Related Product " + (i + 1) + " Price: " + price);

            if (price < minPrice || price > maxPrice) {
                System.out.println("Price outside the range: " + price);
                allValid = false;
            }
        }
        if (allValid) {
            System.out.println("All related products prices are inside the price range");
        } else {
            System.out.println("Some related products are outside the price range");
        }
        return allValid;
    }

    // Verify that related product details
    public boolean validateRelatedProductsDetails(RelatedItemsTestData data) {
        Product[] relatedProducts = data.getRelatedProducts();
        boolean allValid = true;

        for (int i = 0; i < relatedProducts.length; i++) {
            Product p = relatedProducts[i];

            System.out.println("   Product " + (i + 1));
            System.out.println("   Name: " + p.getName());
            System.out.println("   Image: " + p.getImage());
            System.out.println("   Price: " + p.getPrice());

            boolean nameValid = p.getName() != null && !p.getName().isEmpty();
            boolean imageValid = p.getImage() != null && !p.getImage().isEmpty();
            boolean priceValid = p.getPrice() > 0;

            if (!nameValid || !imageValid || !priceValid) {
                System.out.println("Missing/Invalid data in product " + (i + 1));
                allValid = false;
            }
        }
        return allValid;
    }

    // Verify related product navigation
    public Product navigateToRelatedProduct(int index, RelatedItemsTestData data) {
        Product[] relatedProducts = data.getRelatedProducts();
        if (relatedProducts == null || relatedProducts.length == 0) {
            System.out.println("No related products available to navigate");
            return null;
        }
        if (index < 0 || index >= relatedProducts.length) {
            System.out.println("Invalid index: " + index);
            return null;
        }
        Product clickedProduct = relatedProducts[index];
        System.out.println("Navigating to related product: " + clickedProduct.getName() +
                " | URL: " + clickedProduct.getUrl());
        return clickedProduct;
    }

    // Verify bestseller products display
    public boolean verifyOnlyBestSellerProducts(Product[] relatedProducts, int threshold) {

        if (relatedProducts == null || relatedProducts.length == 0) {
            System.out.println("No related products found");
            return false;
        }
        boolean allBestSellers = true;

        for (int i = 0; i < relatedProducts.length; i++) {
            Product product = relatedProducts[i];

            System.out.println(" Product " + (i + 1) + ": " + product.getName()
                    + " | Sales: " + product.getSalesCount());

            if (product.getSalesCount() < threshold) {
                System.out.println("Not a best seller product: " + product.getName());
                allBestSellers = false;
            }
        }

        if (allBestSellers) {
            System.out.println("All related products are best sellers");
        } else {
            System.out.println("Some products are not best sellers");
        }
        return allBestSellers;
    }

    // Duplicate product check
    public boolean hasDuplicateProducts(Product[] products) {
        if (products == null || products.length == 0) {
            return false;
        }
        Set<String> productNames = new HashSet<>();

        for (Product p : products) {
            if (p == null || p.getName() == null || p.getName().isEmpty()) {
                continue;
            }
            if (!productNames.add(p.getName())) {
                return true; // duplicate found
            }
        }
        return false;
    }

    // Verify Only in-stock products shown
    public boolean verifyOnlyInStockProducts(Product[] products) {
        if (products == null || products.length == 0) {
            return true;
        }
        boolean allInStock = true;

        for (Product p : products) {
            System.out.println("Checking product: " + p.getName());

            if (!p.getInStock()) {
                System.out.println("Out of stock product found: " + p.getName());
                allInStock = false;
            }
        }
        if (allInStock) {
            System.out.println("All products are in stock");
        } else {
            System.out.println("Some products are out of stock");
        }
        return allInStock;
    }

    // Verify handle related product API
    public String handleRelatedProductsAPI(Product[] products, boolean apiFailed) {

        if (apiFailed) {
            System.out.println("API failed to load related products");
            return "Unable to load related products";
        }

        if (products == null || products.length == 0) {
            System.out.println("No related products available");
            return "No related products found";
        }

        System.out.println("Related products loaded successfully");
        return "success";
    }

}