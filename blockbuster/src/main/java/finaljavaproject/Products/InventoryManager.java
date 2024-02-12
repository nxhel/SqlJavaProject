/**
 * The InventoryManager class manages a list of DVDs, providing various operations such as filtering, sorting, and modifying.
 * 
 * @author Shakela Hossain, Nihel Madani-Fouatih & Iana Feniuc
 * @since 2023-11-16
 */
package finaljavaproject.Products;

import java.util.*;

import finaljavaproject.FilterProducts.FilterByAvailability;
import finaljavaproject.FilterProducts.FilterByPriceLessThan20;
import finaljavaproject.FilterProducts.FilterByGenre;
import finaljavaproject.FilterProducts.FilterByTitle;
import finaljavaproject.SortProducts.SortByDiscount;
import finaljavaproject.SortProducts.SortByDuration;
import finaljavaproject.SortProducts.SortByPrice;

public class InventoryManager{
    private static final Scanner scan = new Scanner(System.in);
     private List <Dvd> dvds;

    /**
     * Constructs a new InventoryManager with the specified list of DVDs.
     *
     * @param dvds The list of DVDs to manage.
     */
    public InventoryManager(List <Dvd> dvds){
        this.dvds=dvds;
    }

    /**
     * Gets the list of DVDs managed by this InventoryManager.
     *
     * @return The list of DVDs.
     */
    public List <Dvd> listofDvds(){
        return this.dvds;
    }
    
    /**
     * Gets the DVD at the specified index from the list.
     *
     * @param index The index of the DVD to retrieve.
     * @return      The DVD at the specified index.
     */
    public Dvd getDvd(int index){
        return this.dvds.get(index);
    }

    /**
     * prints all of the Dvd in the provided list. 
     * 
     * @param dvds A list containing DVD objects.
     */
    public void printDvds (List<Dvd> dvds){
        for (Dvd dvd : dvds) {
            System.out.println(dvd.toString());
        }
    }

    // /**
    //  * Prompt the user to enter a double value and return it.
    //  * 
    //  * @param message The message to display to the user so that he can enter a value matching the message requirement.
    //  * @return        The double value entered by the user.
    //  */
    // public double getUserInputDouble(String message) {
    //     System.out.println(message);
    //     Double userInput = scan.nextDouble();
    //     scan.nextLine(); 
    //     return userInput;
    // }
        
    /**
     * Returns a list of type Movies.
     * 
     * @return Idem description.
     */
    public List<Dvd> listOfMovies(){
        List<Dvd> movieList =  new ArrayList<Dvd>();
        for(Dvd dvdItem : this.dvds){
            if(dvdItem instanceof Movie){
                movieList.add(dvdItem);
            }
        }
        return movieList;
    }

    /**
     * Returns a list of type Music.
     * 
     * @return Idem description.
     */
    public List<Dvd> listOfMusic(){
        ArrayList<Dvd> musicList =  new ArrayList<Dvd>();
        for(Dvd dvdItem : this.dvds){
            if(dvdItem instanceof Music){
                musicList.add(dvdItem);
            }
        }
        return musicList;
    }
    
    /**
     * Returns a list of type Games.
     * 
     * @return Idem description.
     */
    public List<Dvd> listOfGames(){
        ArrayList<Dvd> gameList =  new ArrayList<Dvd>();
        for(Dvd dvdItem : this.dvds){
            if(dvdItem instanceof Game){
                gameList.add(dvdItem);
            }
        }
        return gameList;
    }
    
    /**
     * Prompt the user to enter a String title and return a Dvd object that matched the title.
     * 
     * @param title The title of the product we're searching for.
     * @return      A dvd object.
     */
    public Dvd findDvd(String title){
        for(Dvd dvdItem : this.dvds){
            if(dvdItem.getTitle().equalsIgnoreCase(title)){
                return dvdItem;
            }
        }
        return null;
    }

    /**
     * Prompt the user to enter a String genre and return true if a Genre enum with that value exists.
     * 
     * @param wantedGenre The genre of the product we're searching for.
     * @return            true if the Enum with that genre exist, false if it doesn't.
     */

    public boolean validateIfGenreExist( String wantedGenre){
            for(Genre gen : Genre.values()){
                if(gen.toString().equalsIgnoreCase(wantedGenre)){
                    return true;
                }
            }
            return false;
    }

    public boolean validateIfDvdExistsBySerialNumber(String serialNumber){
        for(Dvd dvdItem : this.dvds){
            if(dvdItem.getSerialNumber().equalsIgnoreCase(serialNumber)){
                return true;
            }
        }
        return false;
    }

    /**
     * Prompt the user to enter a String serialNumber and return a Dvd object that matched the serialNumber.
     * 
     * @param serialNumber The serialNumber of the product we're searching for.
     * @return             A dvd object or null if serial number doesn't exist.
     */
    public Dvd findDvdBySerialNumber(String serialNumber){
            for(Dvd dvdItem : this.dvds){
                if(dvdItem.getSerialNumber().equalsIgnoreCase(serialNumber)){
                    return dvdItem;
                }
            }
        return null;
    }

    /**
     * Sorting list by price ascending
     * 
     * @return  A sorted dvd list.
     */
    public List <Dvd> SortByPriceinAscendingOrder(){
        Collections.sort(this.dvds, new SortByPrice());
        return this.dvds;

    }
    
    // Idem previous method
    public List <Dvd> SortByDurationinAscensingOrder(){
        Collections.sort(this.dvds, new SortByDuration());
        return this.dvds;

    }
    // Idem previous method
    public List <Dvd> SortByDiscountinAscensingOrder(){
        Collections.sort(this.dvds, new SortByDiscount());
        return this.dvds;
    }

    /**
     * Filters the list of DVDs based on the title containing the chosen word.
     *
     * @param title The word to check if it is contained in the DVD titles.
     * @return      A list of DVDs whose titles contain the chosen word.
     */
    public List <Dvd> FilterByTitleIfContainsTheChoosenWord(String title){
        FilterByTitle filter = new FilterByTitle(title);
        List <Dvd> filterDvds = new ArrayList <Dvd>();
        for (Dvd dvd : filterDvds){
            if(filter.checkIfTrue(dvd)){
                filterDvds.add(dvd);
            }
        }
        return filterDvds;

    }

     /**
     * Filters the list of DVDs based on the specified genre.
     *
     * @param genre The genre to filter by.
     * @return      A list of DVDs belonging to the specified genre.
     */
    public List <Dvd> FilterByGenre(String genre){
        FilterByGenre filter = new FilterByGenre(genre);
        List < Dvd> filterDvds = new ArrayList<Dvd>();
        for (Dvd dvd : filterDvds){
            if(filter.checkIfTrue(dvd)){
                filterDvds.add(dvd);
            }
        }
        return filterDvds;

    }

    /**
     * Filters the list of DVDs to include only those with a price less than 20.
     *
     * @return A list of DVDs with a price less than 20.
     */
    public List <Dvd> FilterByPriceLessThan20(){
        FilterByPriceLessThan20 filter = new FilterByPriceLessThan20();
        List < Dvd> filterDvds = new ArrayList<Dvd>();
        for (Dvd dvd : filterDvds){
            if(filter.checkIfTrue(dvd)){
                filterDvds.add(dvd);
            }
        }
        return filterDvds;
    }

   /**
     * Filters the list of DVDs to include only those that are available.
     *
     * @return A list of available DVDs.
     */
    public List <Dvd> FilterByAvailability(){
        FilterByAvailability filter = new FilterByAvailability ();
        List < Dvd> filterDvds = new ArrayList<Dvd>();
        for (Dvd dvd : filterDvds){
            if(filter.checkIfTrue(dvd)){
                filterDvds.add(dvd);
            }
        }
        return filterDvds;
    }

    /**
     * Adds a product to the inventory.
     *
     * @param product The product to add to the inventory.
     */
    public void addToInventory(Object product){
        if(product instanceof Game){
            Game game = (Game) product;
            this.dvds.add(game);
        }
        else if(product instanceof Movie){
            Movie movie = (Movie) product;
            this.dvds.add(movie);
        }
        else {
            Music music = (Music) product;
            this.dvds.add(music);
        }

    }

    /**
     * Removes a product from the inventory.
     *
     * @param product The product to remove from the inventory.
     */
    public void removeFromInventory( Object product){
        if(product instanceof Game){
            Game game = (Game) product;
            this.dvds.remove(game);
        }
        else if(product instanceof Movie){
            Movie movie = (Movie) product;
            this.dvds.remove(movie);
        }
        else {
            Music music = (Music) product;
            this.dvds.remove(music);
        }
        
    }

    /**
     * Modifies the price of a product in the inventory.
     *
     * @param product The product whose price is to be modified.
     * @param price   The new price for the product.
     */
    public void modifyPrice(Object product, double price){
        if(product instanceof Game){
            Game game = (Game) product;
            game.setPrice(price);
        }
        else if(product instanceof Movie){
            Movie movie = (Movie) product;
            movie.setPrice(price);
        }
        else{
            Music music = (Music) product;
            music.setPrice(price);
        }

    }

    /**
     * Modifies the title of a product in the inventory.
     *
     * @param product The product whose title is to be modified.
     * @param title   The new title for the product.
     */
    public void modifyTitle(Object product, String title){
        if(product instanceof Game){
            Game game = (Game) product;
            game.setTitle(title);
        }
        else if(product instanceof Movie){
            Movie movie = (Movie) product;
            movie.setTitle(title);
        }
        else{
            Music music = (Music) product;
            music.setTitle(title);
        }

    }

    /**
     * Modifies the genre of a product in the inventory.
     *
     * @param product     The product whose genre is to be modified.
     * @param wantedGenre The new genre for the product.
     */
    public void modifyGenre(Object product, String wantedGenre){
        Genre genre = null ;
            for(Genre gen : Genre.values()){
                if(gen.toString().equalsIgnoreCase(wantedGenre)){
                    genre = gen;
                }
            }
        if(product instanceof Game){
            Game game = (Game) product;
            game.setGenre(genre);
        }
        else if(product instanceof Movie){
            Movie movie = (Movie) product;
            movie.setGenre(genre);
        }
        else {
            Music music = (Music) product;
            music.setGenre(genre);
        }

    }

    /**
     * Modifies the availability status of a product in the inventory.
     *
     * @param product      The product whose availability status is to be modified.
     * @param isAvailable  The new availability status for the product.
     */
    public void modifyAvailability(Object product, boolean isAvailable){
        if(product instanceof Game){
            Game game = (Game) product;
            game.setAvailability(isAvailable);
        }
        else if(product instanceof Movie){
            Movie movie = (Movie) product;
            movie.setAvailability(isAvailable);
        }
        else {
            Music music = (Music) product;
            music.setAvailability(isAvailable);
        }

    }

    /**
     * Modifies the discount applied to a product in the inventory.
     *
     * @param product  The product whose discount is to be modified.
     * @param discount The new discount for the product.
     */
    public void modifyDiscount(Object product, double discount){
        if(product instanceof Game){
            Game game = (Game) product;
            game.setDiscount(discount);
        }
        else if(product instanceof Movie){
            Movie movie = (Movie) product;
            movie.setDiscount(discount);
        }
        else {
            Music music = (Music) product;
            music.setDiscount(discount);
        }
    }

    /**
     * Returns a string representation of the InventoryManager object.
     *
     * @return Idem description.
     */
    public String toString(){
        String result = "";
        for(Dvd dvdItem : this.dvds){
            result += dvdItem + "\n";
        }
        return result;
    }

}
