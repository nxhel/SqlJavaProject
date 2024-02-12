package finaljavaproject.Members.Customer;

/**
 * The RegularCustomer class represents a customer with regular benefits.
 * It extends the Customer class.
 * 
 * @author Shakela Hossain
 * @since 2023-11-16
 * @lastUpdate 2023-11-30
 */
public class RegularCustomer extends Customer{

    /**
     * Constructs a new RegularCustomer with the specified details.
     *
     * @param firstname     The first name of the customer.
     * @param lastname      The last name of the customer.
     * @param email         The email address of the customer.
     * @param regularPoints The regular points associated with the customer.
     */
    public RegularCustomer(String firstname, String lastname, String email, int regularPoints){
        super(firstname,lastname, email,regularPoints);
    };


    /**
     * Calculates the discounted price based on the regular customer benefits.
     * Regular customers require more points to make a dollar for the discount.
     *
     * @param price The original price before applying any discount.
     * @return      The discounted price after considering regular customer benefits.
     */
    public double discountedPrice(Double price){
        int points = getPoints();
        int pointsThreshold = 200;
        double pointsToDollarRatio = 200.0;
        if (points < pointsThreshold) {
            // Cannot use points if they are smaller than 100
            return price;
        }
        double discountAmount = Math.min(points / pointsToDollarRatio, price);
        double discountedPrice = Math.max(price - discountAmount, 0.0);
        // Set points to the value remaining after using points to reach a price of zero
        setPoints(Math.max((int) (points - discountAmount * pointsToDollarRatio), 0));
        // Calculate points for each dollar spent after the discounted price
        int additionalPoints = (int) ((price - discountedPrice) / 1.0);
        setPoints(getPoints() + additionalPoints);
        return discountedPrice;
    }
}