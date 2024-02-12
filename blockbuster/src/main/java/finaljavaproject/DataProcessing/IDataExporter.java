/**
 * Interface for exporting data from the application to external sources.
 * 
 * @author Shakela Hossain
 * @since 2023-11-27
 */
package finaljavaproject.DataProcessing;

import java.io.IOException;
import java.util.List;
import finaljavaproject.Members.StoreMember;
import finaljavaproject.Products.Dvd;

public interface IDataExporter {

    /**
     * Write DVDs to an external destination.
     *
     * @param dvdList A list of DVDs to be written.
     * @throws IOException If an I/O error occurs while writing.
     */
    void writeDvds(List<Dvd> dvdList)throws IOException;

    /**
     * Write store members to an external destination.
     *
     * @param memberList A list of store members to be written.
     * @throws IOException If an I/O error occurs while writing.
     */
    void writeMember(List<StoreMember> memberList)throws IOException;
   
    /**
     * Write a purchase log entry to an external destination.
     *
     * @param purchase The purchase log entry to be written.
     * @throws IOException If an I/O error occurs while writing.
     */
    void writeInPurchaseLog(String purchase) throws IOException;
   
    /**
     * Write employees to an external destination.
     *
     * @param employeeList A list of employees to be written.
     */
    void writeEmployee(List<StoreMember> employeeList);

    /**
     * Write customers to an external destination.
     *
     * @param customerList A list of customers to be written.
     */
    void writeCustomer(List<StoreMember> customerList);
}
