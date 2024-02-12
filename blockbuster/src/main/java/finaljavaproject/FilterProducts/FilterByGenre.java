package finaljavaproject.FilterProducts;

import finaljavaproject.Products.Dvd;

/**
 * A filter implementation to check the genre of DVD products.
 * This class implements the Filter interface for DVD products.
 * It evaluates whether a given DVD product belongs to a specified genre.
 *
 * @author Iana Feniuc
 * @since 2023-11-28
 */
public class FilterByGenre implements Filter<Dvd>{
    private String filter;

    /**
     * Constructor of a new FilterByGenre instance with the specified genre.
     *
     * @param filter The genre to filter by.
     */
    public FilterByGenre(String filter){
        this.filter=filter;
    }

    /**
     * Checks if the provided DVD product belongs to the specified genre.
     *
     * @param product The DVD product to be checked.
     * @return true if the product belongs to the specified genre, false otherwise.
     */
    public boolean checkIfTrue(Dvd product){
        return product.getGenre().toString().equalsIgnoreCase(this.filter);
    }
}
