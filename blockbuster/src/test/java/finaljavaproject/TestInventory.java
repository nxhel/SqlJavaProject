package finaljavaproject;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import finaljavaproject.Products.Dvd;
import finaljavaproject.Products.Genre;
import finaljavaproject.Products.InventoryManager;
import finaljavaproject.Products.Movie;

public class TestInventory {

    List <Dvd> dvds = new ArrayList<Dvd>();
    InventoryManager inventory = new InventoryManager(dvds);

    @Test
    public void testIfAddsToInventory() {
        Dvd dvd = new Movie(17.99,"Pirates of the Carribean",Genre.ACTION, "220006", 120, true, 0.00,3, "Gore Verbinski", "Walt Disney Pictures" );
        inventory.addToInventory(dvd);
        assertEquals(dvd,inventory.findDvd("Pirates of the Carribean"));
    }
    @Test
    public void testIfRemoveToInventory() {
        Dvd dvd = new Movie(17.99,"Pirates of the Carribean",Genre.ACTION, "220006", 120, true, 0.00,3, "Gore Verbinski", "Walt Disney Pictures" );
        inventory.removeFromInventory(dvd);
        assertEquals(null,inventory.findDvd("Pirates of the Carribean"));
    }
    @Test
    public void testIfModifiesPrice() {
        Dvd dvd = new Movie(17.99,"Pirates of the Carribean",Genre.ACTION, "220006", 120, true, 0.00,3, "Gore Verbinski", "Walt Disney Pictures" );
        dvds.add(dvd);
        inventory.modifyPrice(dvd, 13.88);
        assertEquals(13.88,dvd.getPrice(),2);
    }
    @Test
    public void testIfModifiesTitle() {
        Dvd dvd = new Movie(17.99,"Pirates of the Carribean",Genre.ACTION, "220006", 120, true, 0.00,3, "Gore Verbinski", "Walt Disney Pictures" );
        dvds.add(dvd);
        inventory.modifyTitle(dvd, "Pirates of Montreal");
        assertEquals("Pirates of Montreal",dvd.getTitle());
    }
    @Test
    public void testIfModifiesGenre() {
        Dvd dvd = new Movie(17.99,"Pirates of the Carribean",Genre.ACTION, "220006", 120, true, 0.00,3, "Gore Verbinski", "Walt Disney Pictures" );
        dvds.add(dvd);
        inventory.modifyGenre(dvd, "Adventure");
        assertEquals(Genre.ADVENTURE,dvd.getGenre());
    }
    @Test
    public void testIfModifiesAvailability() {
        Dvd dvd = new Movie(17.99,"Pirates of the Carribean",Genre.ACTION, "220006", 120, true, 0.00,3, "Gore Verbinski", "Walt Disney Pictures" );
        dvds.add(dvd);
        inventory.modifyAvailability(dvd, false);
        assertEquals(false,dvd.getAvailability());
    }
    @Test
    public void testIfModifiesDiscount() {
        Dvd dvd = new Movie(17.99,"Pirates of the Carribean",Genre.ACTION, "220006", 120, true, 0.00,3, "Gore Verbinski", "Walt Disney Pictures" );
        dvds.add(dvd);
        inventory.modifyDiscount(dvd, 3.35);
        assertEquals(3.35,dvd.getDiscount(),2);
    }


    
}
