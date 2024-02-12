package finaljavaproject.FilterProducts;

import finaljavaproject.Products.Dvd;

/**
 * A filter implementation to check the availability of DVD products.
 * This class implements the Filter interface for DVD products.
 * It evaluates whether a given DVD product is available or not.
 *
 * @author Iana Feniuc
 * @since 2023-11-28
 */
public class FilterByAvailability  implements Filter <Dvd> {
    /**
     * Checks if the provided DVD product is available.
     *
     * @param product The DVD product to be checked.
     * @return        true if the product is available, false otherwise.
     */
    public boolean checkIfTrue(Dvd product){
        return product.getAvailability();
    }
}
