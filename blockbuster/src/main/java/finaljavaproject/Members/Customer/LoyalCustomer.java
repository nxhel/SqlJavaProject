package finaljavaproject.Members.Customer;

/**
 * The LoyalCustomer class represents a customer with loyalty benefits.
 * It extends the Customer class.
 *  
 * @author Shakela Hossain
 * @since 2023-11-16
 * @lastUpdate 2023-11-30
 */
public class LoyalCustomer extends Customer{

    /**
     * Constructs a new LoyalCustomer with the specified details.
     *
     * @param firstname      The first name of the customer.
     * @param lastname       The last name of the customer.
     * @param email          The email address of the customer.
     * @param premiumPoints  The premium points associated with the customer.
     */
    public LoyalCustomer(String firstname, String lastname, String email, int premiumPoints){
        super(firstname,lastname, email, premiumPoints);
    };

    /**
     * Calculates the discounted price based on the loyalty points.
     *
     * @param price The original price before applying any discount.
     * @return      The discounted price after considering loyalty points.
     */
    public double discountedPrice(Double price){
        int points = getPoints();
        int pointsThreshold = 100;
        double pointsToDollarRatio = 100.0;
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



    

