package finaljavaproject.Members.Customer;

import finaljavaproject.Members.StoreMember;

/**
 * An abstract class representing a customer in the store.
 * This class implements the StoreMember interface and provides common attributes
 * and methods for store customers. It includes information such as first name, last name,
 * email, and loyalty points. Subclasses must implement the discountedPrice(double price)
 * method to calculate discounted prices for purchases.
 *
 * @author Shakela Hossain, Nihel Madani-Fouatih & Iana Feniuc
 * @since 2023-11-16
 * @lastUpdate 2023-11-30
 */
public abstract class Customer implements StoreMember{
    private String firstname;
    private String lastname ;
    private String email;
    private int points;

    /**
     * Constructs a new Customer with the specified attributes.
     *
     * @param firstname The first name of the customer.
     * @param lastname  The last name of the customer.
     * @param email     The email address of the customer.
     * @param points    The loyalty points of the customer.
     */
    public Customer(String firstname, String lastname, String email,int points){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.points= points;
    };

    // Getters and setters
    public int getPoints(){
        return this.points;
    }

    public void setPoints(int points){
        this.points=points;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }
    
    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

     /**
     * Returns a string representation of the Customer object.
     *
     * @return Idem description.
     */
    @Override
    public String toString(){
        return this.firstname + ";"+ this.lastname + ";" + this.email + ";" +this.points;
    }
    //method which takes in a price of type double and returns a type double of a discounted price
    public abstract double discountedPrice(Double price);
}
