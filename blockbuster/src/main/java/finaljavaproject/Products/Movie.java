/**
 * Represents a movie in the inventory, extending the Dvd class.
 * 
 * @author Shakela Hossain, Nihel Madani-Fouatih & Iana Feniuc
 * @since 2023-11-16
 */
package finaljavaproject.Products;

public class Movie extends Dvd{
    private String Director;
    private String Studio;
    
    /**
     * Constructor of a Movie object with the specified attributes.
     *
     * @param price         The price of the movie.
     * @param title         The title of the movie.
     * @param genre         The genre of the movie.
     * @param serialNumber  The serial number of the movie.
     * @param duration      The duration of the movie in minutes.
     * @param isAvailable   The availability status of the movie.
     * @param discount      The discount applied to the movie.
     * @param quantity      The quantity of this movie in the inventory.
     * @param director      The director of the movie.
     * @param studio        The studio producing the movie.
     */
    public Movie(double price, String title, Genre genre, String serialNumber, double duration, boolean isAvailable, double discount, int quantity, String director, String studio){
        super( price,  title,  genre,  serialNumber,  duration,  isAvailable,  discount, quantity);
        this.Director=director;
        this.Studio=studio;
    };

    // Getters and setters
    public String getDirectorName() {
        return Director;
    }

    public void setDirectorName(String directorName) {
        this.Director = directorName;
    }

    public String getStudio() {
        return Studio;
    }

    public void setStudio(String studio) {
        this.Studio = studio;
    }

    /**
     * Overrides the toString method to include additional movie information.
     *
     * @return A string representation of the movie, including director and studio information.
     */
    @Override
    public String toString() {
        return super.toString() + this.Director + ";" + this.Studio + ";" ; 
    }
}
