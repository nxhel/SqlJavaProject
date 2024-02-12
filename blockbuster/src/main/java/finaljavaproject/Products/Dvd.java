/**
 * The Dvd class represents a DVD product.
 * It is an abstract class providing common attributes and methods for DVD products.
 * @author Shakela Hossain & Nihel Madani-Fouatih
 * @since 2023-11-16
 */
package finaljavaproject.Products;

public abstract class Dvd {
    private double price;
    private String title;
    private Genre genre;
    private String serialNumber;
    private double duration;
    private boolean isAvailable;
    private double discount;
    private int quantity;

    /**
     * Constructor for a new Dvd with the specified details.
     *
     * @param price        The price of the DVD.
     * @param title        The title of the DVD.
     * @param genre        The genre of the DVD.
     * @param serialNumber The serial number of the DVD.
     * @param duration     The duration of the DVD.
     * @param isAvailable  The availability status of the DVD.
     * @param discount     The discount applied to the DVD.
     * @param quantity     The quantity of the DVD available.
     */
    public Dvd(double price, String title, Genre genre, String serialNumber, double duration, boolean isAvailable, double discount, int quantity){
        this.price = price;
        this.title = title;
        this.genre = genre;
        this.serialNumber = serialNumber;
        this.duration = duration;
        this.isAvailable = isAvailable;
        this.discount = discount;
        this.quantity = quantity;
    };

    // Getters and Setters
    public void setPrice(double price){
        this.price = price;
    };
    
    public double getPrice(){
        return this.price;
    };

    public void setTitle(String title){
        this.title = title;
    };

    public String getTitle(){
        return this.title;
    };

    public void setGenre(Genre genre){
        this.genre = genre; 
    };

    public Genre getGenre(){
        return this.genre;
    };

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    };

    public String getSerialNumber(){
        return this.serialNumber;
    };

    public void setDuration(double duration){
        this.duration = duration;
    };

    public double getDuration(){
        return this.duration;
    };

    public void setAvailability(boolean isAvailable){
        this.isAvailable = isAvailable;
    };

    public boolean getAvailability(){
        return this.isAvailable;
    };

    public void setDiscount(double discount){
        this.discount = discount;
    };
    
    public double getDiscount(){
        return this.discount;
    };

    public void setQuantity(int quantity){
        this.quantity = quantity;
    };
    
    public int getQuantity(){
        return this.quantity;
    };

    /**
     * Returns a string representation of the Dvd object.
     *
     * @return Idem description.
     */
    @Override
    public String toString(){
        return this.price +";"+ this.title +";"+ this.genre +";"+ this.serialNumber +";"+ this.duration +";"+ this.isAvailable +";"+ this.discount +";"+ this.quantity+";";
    }
}
