package finaljavaproject;


import finaljavaproject.Products.*;
import finaljavaproject.SortProducts.SortByDiscount;
import finaljavaproject.SortProducts.SortByDuration;
import finaljavaproject.SortProducts.SortByPrice;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class DvdComparatorTest {

    @Test
    public void testDvdWithSameDiscount() {
        Genre genre1 = Genre.valueOf("action".toUpperCase());
        Genre genre2 = Genre.valueOf("hiphop".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(10.0, "Title1", genre1, "20000", 10, isTrue, 10.0,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(13.0, "Title2", genre2, "20002", 5, isFalse, 10.0,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByDiscount();
        int result = comparator.compare(dvd1, dvd2);
        assertEquals("Dvd with same discount shoud give 0.", 0, result);
    }

    @Test
    public void testDvd1HigerDiscountThanDvd2() {
        Genre genre1 = Genre.valueOf("action".toUpperCase());
        Genre genre2 = Genre.valueOf("hiphop".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(10.0, "Title1", genre1, "20000", 10, isTrue, 10.0,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(13.0, "Title2", genre2, "20002", 5.0, isFalse, 5,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByDiscount();
        int result = comparator.compare(dvd1, dvd2);
        assertEquals("Dvd1 with higher discount should return 1.", 1, result);
    }

    @Test
    public void testDvd1LowerDiscountThanDvd2() {
        Genre genre1 = Genre.valueOf("action".toUpperCase());
        Genre genre2 = Genre.valueOf("hiphop".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(10.0, "Title1", genre1, "20000", 5, isTrue, 5,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(13.0, "Title2", genre2, "20002", 5.10, isFalse, 10.0,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByDiscount();
        int result = comparator.compare(dvd1, dvd2);
        assertEquals("Dvd1 with lower discount should return 1.", -1, result);
    }

    @Test
    public void testDvdWithSamePrice() {
        Genre genre = Genre.valueOf("action".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(2, "Title1", genre, "20000", 10.0, isTrue, 10.0,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(2, "Title2", genre, "20002", 10.0, isFalse, 10.0,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByPrice();
        int result = comparator.compare(dvd1, dvd2);
        assertEquals("Dvd with same price shoud give 0.", 0, result);
    }

     @Test
    public void testDvd1PriceHigherThanDvd2() {
        Genre genre = Genre.valueOf("action".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(26.0, "Title1", genre, "20000", 10.0, isTrue, 10.0,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(13.0, "Title2", genre, "20002", 10.0, isFalse, 10.0,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByPrice();
        int result = comparator.compare(dvd1, dvd2);
        assertEquals("Dvd1 with higer Price shoud return 1.", 1, result);
    }

      @Test
    public void testDvd1LowerHigherThanDvd2() {
        Genre genre = Genre.valueOf("action".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(5, "Title1", genre, "20000", 10.0, isTrue, 10.0,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(13.0, "Title2", genre, "20002", 10.0, isFalse, 10.0,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByPrice();
        int result = comparator.compare(dvd1, dvd2);
        assertEquals("Dvd1 with lower price shoud return -1.", -1, result);
    }

  

      @Test
    public void testDvdWithSameDuration() {
        Genre genre = Genre.valueOf("action".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(10.0, "Title1", genre, "20000", 300.0, isTrue, 10.0,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(13.0, "Title2", genre, "20002", 300.0, isFalse, 10.0,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByDuration();
        int result = comparator.compare(dvd1, dvd2);
        assertEquals("Dvd with same duration shoud give 0.", 0, result);
    }

    @Test
    public void testDvd1HigherDurationThanDvd2() {
        Genre genre = Genre.valueOf("action".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(10.0, "Title1", genre, "20000", 300.0, isTrue, 10.0,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(13.0, "Title2", genre, "20002", 10, isFalse, 10.0,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByDuration();
        int result = comparator.compare(dvd1, dvd2);
        assertEquals("Dvd1 with higer duration shoud return 1.", 1, result);
    }

     @Test
    public void testDvd1LowerDurationThanDvd2() {
        Genre genre = Genre.valueOf("action".toUpperCase());
        boolean isTrue = true;
        boolean isFalse = false;
        Dvd dvd1 = new Movie(10.0, "Title1", genre, "20000", 2, isTrue, 10.0,4, "Dir", "Stud");
        Dvd dvd2 = new Movie(13.0, "Title2", genre, "20002", 103, isFalse, 10.0,1, "Dir2", "Stud2");
        Comparator<Dvd> comparator = new SortByDuration();
        int result = comparator.compare(dvd1 , dvd2);
        assertEquals("Dvd1 with lower duration shoud return -1.", -1, result);
    }

    
}
