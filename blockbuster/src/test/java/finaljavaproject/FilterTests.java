package finaljavaproject;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

import finaljavaproject.FilterProducts.FilterByAvailability;
import finaljavaproject.FilterProducts.FilterByGenre;
import finaljavaproject.FilterProducts.FilterByPriceLessThan20;
import finaljavaproject.FilterProducts.FilterByTitle;
import finaljavaproject.Products.Dvd;
import finaljavaproject.Products.Game;
import finaljavaproject.Products.Genre;

public class FilterTests {


    @Test
    public void testFilterWithAvailabilityTrue() {
        Dvd dvd = new Game(79.99,"Fortnite",Genre.SHOOTER, "000001", 4.00, true, 0.00,6, "PlaySation", "Epic Games" );
        FilterByAvailability availabilityFilter = new FilterByAvailability();
        assertEquals(true,availabilityFilter.checkIfTrue(dvd));
    }

    @Test 
    public void testFilterWithAvailabilityFalse(){
        Dvd dvd = new Game(79.99,"Fortnite",Genre.SHOOTER, "000001", 4.00, false, 0.00,6, "PlaySation", "Epic Games" );
        FilterByAvailability availabilityFilter = new FilterByAvailability();
        assertEquals(false,availabilityFilter.checkIfTrue(dvd));
    }

    @Test
    public void testFilterIfGoodGenre() {
        Dvd dvd = new Game(69.99,"SuperMarioBros",Genre.ADVENTURE, "000002", 3.00, false, 10.99, 10, "Nintento Switch", "Nintendo");
        FilterByGenre genreFilter = new FilterByGenre("Adventure");
        assertEquals(true,genreFilter.checkIfTrue(dvd));
    }

    @Test
    public void testFilterIfWrongGenre() {
        Dvd dvd = new Game(69.99,"SuperMarioBros",Genre.ADVENTURE, "000002", 3.00, false, 10.99, 10, "Nintento Switch", "Nintendo");
        FilterByGenre genreFilter = new FilterByGenre("Action");
        assertEquals(false,genreFilter.checkIfTrue(dvd));
    }

    @Test
    public void testFilterIfPriceLessThan20() {
        Dvd dvd = new Game(19.99,"Minecraft",Genre.SANDBOX, "000005", 6.00, false, 0.00,3, "XBOX", "Mojang" );
        FilterByPriceLessThan20 priceFilter = new FilterByPriceLessThan20();
        assertEquals(true,priceFilter.checkIfTrue(dvd));
    }

    @Test
    public void testFilterIfPriceMoreThan20() {
        Dvd dvd = new Game(23.99,"Minecraft",Genre.SANDBOX, "000005", 6.00, false, 0.00,3, "XBOX", "Mojang" );
        FilterByPriceLessThan20 priceFilter = new FilterByPriceLessThan20();
        assertEquals(false,priceFilter.checkIfTrue(dvd));
    }

    @Test
    public void testFilterIfTitleContainsRightWord() {
        Dvd dvd = new Game(90.00,"Mortal-Combat",Genre.COMBAT, "000003", 1.00, true, 5.00,6,"Acclaim", "Midway Games" );
        FilterByTitle titleFilter = new FilterByTitle("mortal");
        assertEquals(true,titleFilter.checkIfTrue(dvd));
    }
    @Test
    public void testFilterIfTitleDoesntContainWord() {
        Dvd dvd = new Game(90.00,"Mortal-Combat",Genre.COMBAT, "000003", 1.00, true, 5.00,6,"Acclaim", "Midway Games" );
        FilterByTitle titleFilter = new FilterByTitle("fortnite");
        assertEquals(false,titleFilter.checkIfTrue(dvd));
    }
}




    

