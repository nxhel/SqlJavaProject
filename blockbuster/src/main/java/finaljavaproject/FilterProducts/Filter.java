/**
 * The filter interface for DVD products.
 * This interface defines a method to check if a given DVD product meets certain criteria.
 * Implementing classes should provide the logic to evaluate the conditions for filtering.
 *
 * @param <Dvd> The type of DVD product that this filter operates on.
 * @author Iana Feniuc
 * @since 2023-11-28
 */
package finaljavaproject.FilterProducts;
public interface Filter<Dvd> {
    /**
     * Checks if the provided DVD product satisfies the filtering conditions.
     *
     * @param product The DVD product to be checked.
     * @return        true if the product meets the filtering conditions, false otherwise.
     */
    boolean checkIfTrue(Dvd product);
    
}
