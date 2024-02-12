package finaljavaproject.FilterProducts;

import finaljavaproject.Products.Dvd;

/**
 * A filter implementation to check if the price of DVD products is less than $20.
 * This class implements the Filter interface for DVD products.
 * It evaluates whether a given DVD product has a price less than $20.
 *
 * @author Iana Feniuc
 * @since 2023-11-28
 */
public class FilterByPriceLessThan20 implements Filter<Dvd>{

    /**
     * Checks if the provided DVD product has a price less than $20.
     *
     * @param product The DVD product to be checked.
     * @return        true if the product's price is less than $20, false otherwise.
     */
    public boolean checkIfTrue(Dvd product){
        return product.getPrice() < 20;
    }
}
