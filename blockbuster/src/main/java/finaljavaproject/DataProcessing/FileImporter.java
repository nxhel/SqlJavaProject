/**
 * Interface for importing data from files into the application.
 * 
 * @author Nihel Madani-Fouatih
 * @since 2023-11-24
 */
package finaljavaproject.DataProcessing;

import finaljavaproject.Members.*;
import finaljavaproject.Members.Customer.*;
import finaljavaproject.Products.*;
import java.util.*;

public interface FileImporter {

     /**
     * Load DVDs from a file.
     *
     * @return A list of DVDs loaded from the file.
     */
     List <Dvd> loadDvdsFromFile();

     /**
     * Load store members from a file.
     *
     * @return A list of store members loaded from the file.
     */
     List <StoreMember> loadStoreMembersFromFile();

     /**
     * Load customers from a file.
     *
     * @return A list of customers loaded from the file.
     */
     List <StoreMember> loadCustomersFromFile();

     /**
     * Load purchase log entries from a file.
     *
     * @return A list of purchase log entries loaded from the file.
     */
     List<String> loadPurchaseLogFromFile();
     List <String> loadUsersFromFile();
}
