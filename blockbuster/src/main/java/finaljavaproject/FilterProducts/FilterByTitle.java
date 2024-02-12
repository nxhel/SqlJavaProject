package finaljavaproject.FilterProducts;

import finaljavaproject.Products.Dvd;

/**
 * A filter implementation to check if the title of DVD products contains a specified substring.
 * This class implements the Filter interface for DVD products.
 * It evaluates whether a given DVD product's title contains a specified substring.
 *
 * @author Iana Feniuc
 * @since 2023-11-28
 */
public class FilterByTitle implements Filter <Dvd>{
    private String filter;

    /**
     * Constructor of a new FilterByTitle instance with the specified title substring.
     *
     * @param filter The title substring to filter by.
     */
    public FilterByTitle(String filter){
        this.filter=filter.toLowerCase();
    }

    /**
     * Checks if the provided DVD product's title contains the specified substring.
     *
     * @param product The DVD product to be checked.
     * @return        true if the product's title contains the specified substring, false otherwise.
     */
    public boolean checkIfTrue(Dvd product){
        return product.getTitle().toLowerCase().contains(this.filter);
    }
    
}
