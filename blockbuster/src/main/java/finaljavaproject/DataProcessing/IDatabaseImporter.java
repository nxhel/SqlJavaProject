/**
 * Interface for importing data from a database into the application.
 * 
 * @author Nihel Madani-Fouatih & Shakela Hossain
 * @since 2023-11-24
 */
package finaljavaproject.DataProcessing;

import java.util.*;
import finaljavaproject.Members.*;
import finaljavaproject.Members.Customer.*;
import finaljavaproject.Products.*;

public interface IDatabaseImporter {

     /**
     * Load movies from the database.
     *
     * @return A list of movies loaded from the database.
     */
    List<Dvd> loadMoviesFromDatabse();

    /**
     * Load store members from the database.
     *
     * @return A list of store members loaded from the database.
     */
    List<StoreMember> loadStoreMembersFromDatabse();

    /**
     * Load customers from the database.
     * @return A list of customers loaded from the database.
     */
    List<Customer> loadCustomersFromDatabase();
}
