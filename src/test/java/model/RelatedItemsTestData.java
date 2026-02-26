package model;

public class RelatedItemsTestData {

    private Product mainProduct;
    private Product[] relatedProducts;
    private int visibleItems;
    private int maxAllowed;
    private String[] relatedCategories;
    private double[] relatedPrices;
    private double percentage;
    private boolean expectedResult01;
    private boolean expectedResult02;
    private boolean expectedResult03;
    private boolean expectedResult04;
    private boolean expectOnlyInStock;
    boolean apiFailed;
    String apiExpectedMessage;

    public RelatedItemsTestData(Product mainProduct,
                                Product[] relatedProducts,
                                int visibleItems,
                                int maxAllowed,
                                String[] relatedCategories,
                                double[] relatedPrices,
                                double percentage,
                                boolean expectedResult01,
                                boolean expectedResult02,
                                boolean expectedResult03,
                                boolean expectedResult04, boolean expectOnlyInStock,
                                boolean apiFailed,
                                String apiExpectedMessage) {

        this.mainProduct = mainProduct;
        this.relatedProducts = relatedProducts;
        this.visibleItems = visibleItems;
        this.maxAllowed = maxAllowed;
        this.relatedCategories = relatedCategories;
        this.relatedPrices = relatedPrices;
        this.percentage = percentage;
        this.expectedResult01 = expectedResult01;
        this.expectedResult02 = expectedResult02;
        this.expectedResult03 = expectedResult03;
        this.expectedResult04 = expectedResult04;
        this.expectOnlyInStock = expectOnlyInStock;
        this.apiFailed = apiFailed;
        this.apiExpectedMessage = apiExpectedMessage;
    }

    public Product getMainProduct() { return mainProduct; }
    public Product[] getRelatedProducts() { return relatedProducts; }
    public int getVisibleItems() { return visibleItems; }
    public int getMaxAllowed() { return maxAllowed; }
    public String[] getRelatedCategories() { return relatedCategories; }
    public double[] getRelatedPrices() { return relatedPrices; }
    public double getPercentage() { return percentage; }
    public boolean getExpectedResult01() { return expectedResult01; }
    public boolean getExpectedResult02() { return expectedResult02; }
    public boolean getExpectedResult03() { return expectedResult03; }
    public boolean getExpectedResult04() { return expectedResult04; }
    public boolean isExpectOnlyInStock() { return expectOnlyInStock; }
    public boolean isApiFailed() { return apiFailed; }
    public String getApiExpectedMessage() { return apiExpectedMessage; }

}