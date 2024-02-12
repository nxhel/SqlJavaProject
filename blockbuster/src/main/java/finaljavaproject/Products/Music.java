/**
 * Represents a music DVD in the inventory, extending the Dvd class.
 * 
 * @author Shakela Hossain, Nihel Madani-Fouatih & Iana Feniuc
 * @since 2023-11-16
 */
package finaljavaproject.Products;

public class Music extends Dvd{

    private String artist;
    private String musicLabel;

    /**
     * Constructor of a Music object with the specified attributes.
     *
     * @param price         The price of the music DVD.
     * @param title         The title of the music DVD.
     * @param genre         The genre of the music DVD.
     * @param serialNumber  The serial number of the music DVD.
     * @param duration      The duration of the music DVD in minutes.
     * @param isAvailable   The availability status of the music DVD.
     * @param discount      The discount applied to the music DVD.
     * @param quantity      The quantity of this music DVD in the inventory.
     * @param artist        The artist or band of the music DVD.
     * @param musicLabel    The music label producing the music DVD.
     */
    public Music(double price, String title, Genre genre, String serialNumber, double duration, boolean isAvailable, double discount,int quantity,String artist,String musicLabel){
        super( price,  title,  genre,  serialNumber,  duration,  isAvailable,  discount,quantity);
        this.artist=artist;
        this.musicLabel=musicLabel;
    };

    // Getters and setters
    public String getMusicLabel() {
        return this.artist;
    }

    public void setMusicLabel(String musiclabel) {
        this.musicLabel=musiclabel;
    }

     public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist=artist;
    }
       
    /**
     * Overrides the toString method to include additional music DVD information.
     *
     * @return A string representation of the music DVD, including artist and music label information.
     */
    @Override
    public String toString() {
        return super.toString() + this.artist + ";" + this.musicLabel + ";" ;
    }
}
