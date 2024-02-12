/**
 * The Game class represents a game product, extending the Products.Dvd class.
 * @author Shakela Hossain, Nihel Madani-Fouatih & Iana Feniuc
 * @lastUpdate 2023-11-30
 */
package finaljavaproject.Products;

/**
     * Constructs a new Game with the specified details.
     *
     * @param price        The price of the game.
     * @param title        The title of the game.
     * @param genre        The genre of the game.
     * @param serialNumber The serial number of the game.
     * @param duration     The duration of the game.
     * @param isAvailable  The availability status of the game.
     * @param discount     The discount applied to the game.
     * @param quantity     The quantity of the game available.
     * @param platform     The gaming platform of the game.
     * @param publisher    The publisher of the game.
     */
public class Game extends Dvd{
    private String Platform ;
    private String Publisher;
    public Game(double price, String title, Genre genre, String serialNumber, double duration, boolean isAvailable, double discount, int quantity, String platform , String publisher){
        super( price,  title,  genre,  serialNumber,  duration,  isAvailable,  discount, quantity);
        this.Platform=platform;
        this.Publisher=publisher;
    };

    // Getters and setters
    public String getPlatform() {
        return Platform;
    }

    public void setGamingPlatform(String gamingPlatform) {
        this.Platform = gamingPlatform;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        this.Publisher = publisher;
    }

    /**
     * Returns a string representation of the Game object.
     *
     * @return Idem description.
     */
    @Override
    public String toString() {
        return super.toString() + this.Platform + ";" + this.Publisher + ";" ; 
    }
} 


    



















































































