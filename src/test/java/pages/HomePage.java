package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class HomePage {

    private Page page;
    private final String homeUrl = "https://www.ebay.com/";

    public HomePage(Page page) {
        this.page = page;
    }

    /** Open home page */
    public void openHomePage() {
        page.navigate(homeUrl, new Page.NavigateOptions()
                .setTimeout(60000) // increase timeout
                .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)); // faster than "load"

        System.out.println("Home Page Title: " + page.title());
    }

    /** Search for an item and click the search button */
    public void searchProduct(String keyword) {
        Locator searchBox = page.locator("input#gh-ac");
        searchBox.fill(keyword);
        // Trigger search
        searchBox.press("Enter");
        System.out.println("✅ Search triggered using ENTER for: " + keyword);
    }

    /** Open a product by URL */
    public void openProduct(String productUrl) {
        page.navigate(productUrl,
                new Page.NavigateOptions()
                        .setTimeout(60000)
                        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
        );

        // Optional stabilization
        page.waitForSelector("body");
    }
}