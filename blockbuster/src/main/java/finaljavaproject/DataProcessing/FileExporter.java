/**
 * The FileExporter class implements the IDataExporter interface and provides methods to export
 * lists of DVDs and store members to CSV files.
 *
 * @author Shakela Hossain
 * @since 2023-11-28
 */
package finaljavaproject.DataProcessing;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import finaljavaproject.Members.Employee;
import finaljavaproject.Members.Manager;
import finaljavaproject.Members.StoreMember;
import finaljavaproject.Members.Customer.LoyalCustomer;
import finaljavaproject.Members.Customer.RegularCustomer;
import finaljavaproject.Products.Dvd;
import finaljavaproject.Products.Game;
import finaljavaproject.Products.Movie;
import finaljavaproject.Products.Music;

public class FileExporter implements IDataExporter{
    /**
     * Writes a list of DVDs to a CSV file named "ExportedDvd.csv". Each DVD is written as a separate row
     * in the CSV file, including details specific to its type (Movie, Music, or Game).
     *
     * @param dvdList       The list of DVDs to be exported.
     * @throws IOException  If an I/O error occurs while writing to the file.
     */
    public void writeDvds(List<Dvd> dvdList) throws IOException {
        try {
            // Define the file path for ExportedDvd.csv
            Path filePath = Paths.get("Products.csv");
            // Errases what was in the csv previously and then adds a new list
            Files.write(filePath, "\n".getBytes()); // this might not be needed. adjust according to code structure
            // Create the file if it does not exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            // Read existing lines from the file
            List<String> lines = Files.readAllLines(filePath);
            // Iterate through the list of DVDs
            for (Dvd dvd : dvdList) {
                if (dvd instanceof Movie){
                    Movie movie = (Movie)dvd;
                    String row = "Movie;" + movie;
                    lines.add(row);
                } else if (dvd instanceof Music){
                    Music music = (Music)dvd;
                    String row = "Music;" + music;
                    lines.add(row);
                } else if (dvd instanceof Game){
                    Game game = (Game)dvd;
                    String row = "Game;" + game;
                    lines.add(row);
                } else {
                    System.out.println("Dvd type does not exist");
                }
                // Write the updated lines to the file
                Files.write(filePath, lines);
            }
            System.out.println("Data written to ExportedDvd.csv successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing data to ExportedDvd.csv: "+ e.getMessage());
        }
    }

    /**
     * Writes a list of store members to a CSV file named "ExportedMembers.csv". Each store member is
     * written as a separate row in the CSV file, including details specific to their type
     * (Employee, Manager, LoyalCustomer, or RegularCustomer).
     *
     * @param memberList The list of store members to be exported.
     */
    public void writeMember(List<StoreMember> memberList) {
        try {
            // Define the file path for ExportedMembers.csv
            Path filePath = Paths.get("StoreMembers.csv");
            Files.write(filePath, "\n".getBytes()); 
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            // Read existing lines from the file
            List<String> lines = Files.readAllLines(filePath);
            // Iterate through the list of store members
            for (StoreMember member : memberList) {
                if (member instanceof Employee){
                    Employee employee = (Employee)member;
                    String row = "Employee;" + employee;
                    lines.add(row);
                } else if (member instanceof Manager){
                    Manager manager = (Manager)member;
                    String row = "Manager;" + manager;
                    lines.add(row);
                } else if (member instanceof LoyalCustomer){
                    LoyalCustomer loyalCustomer = (LoyalCustomer)member;
                    String row = "LoyalCustomer;" + loyalCustomer;
                    lines.add(row);
                } else if (member instanceof RegularCustomer){
                    RegularCustomer regularCustomer = (RegularCustomer)member;
                    String row = "RegularCustomer;" + regularCustomer;
                    lines.add(row);
                } else {
                    System.out.println("Member type does not exist");
                }
                // Write the updated lines to the file
                Files.write(filePath, lines);
            }
            System.out.println("Data written to StoreMembers.csv successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing data to StoreMembers.csv: " + e.getMessage());
        }
    }
    
    /**
     * Writes a list of DVDs that were purchased to a CSV file named "PurchaseLog.csv". Each DVD is written as a separate row
     * in the CSV file, including details specific to its type (Movie, Music, or Game).
     *
     * @param dvdList       The list of DVDs to be exported.
     * @throws IOException  If an I/O error occurs while writing to the file.
     */
    public void writeInPurchaseLog(String purchase) throws IOException {
        // Define the file path for PurchaseLog.csv
        Path filePath = Paths.get("PurchaseLog.csv");
        try {
            // Create the file if it does not exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            // Read existing lines from the file
            List<String> lines = Files.readAllLines(filePath);
            lines.add(purchase);
            
            // Write the updated lines to the file
            Files.write(filePath, lines);
            System.out.println("Data written to PurchaseLog.csv successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing data to PurchaseLog.csv: " + e.getMessage());
        }
    }

    /**
     * Writes a list of Employees to a CSV file named "EmployeeList.csv". Each store member is
     * written as a separate row in the CSV file, including details specific to their type
     * (Employee or Manager).
     *
     * @param employeeList The list of store members to be exported.
     */
    public void writeEmployee(List<StoreMember> employeeList) {
        try {
            // Define the file path for EmployeeList.csv
            Path filePath = Paths.get("Employees.csv");
            Files.write(filePath, "\n".getBytes()); 
            // Create the file if it does not exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            // Read existing lines from the file
            List<String> lines = Files.readAllLines(filePath);
            // Iterate through the list of store members
            for (StoreMember member : employeeList) {
                if (member instanceof Employee){
                    Employee employee = (Employee)member;
                    String row = "Employee; " + employee;
                    lines.add(row);
                } if (member instanceof Manager){
                    Manager manager = (Manager)member;
                    String row = "Manager; " + manager;
                    lines.add(row);
                } 
                // Write the updated lines to the file
                Files.write(filePath, lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing data to StoreMembers.csv: " + e.getMessage());
        }
    }

     /**
     * Writes a list of Customer to a CSV file named "CustomerList.csv". Each store member is
     * written as a separate row in the CSV file, including details specific to their type
     * (LoyalCustomer or RegularCustomer).
     *
     * @param customerList The list of store members to be exported.
     */
    public void writeCustomer(List<StoreMember> customerList) {
        try {
            // Define the file path for CustomerList.csv
            Path filePath = Paths.get("Customers.csv");
            Files.write(filePath, "\n".getBytes()); 
            // Create the file if it does not exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            // Read existing lines from the file
            List<String> lines = Files.readAllLines(filePath);
            // Iterate through the list of store members
            for (StoreMember member : customerList) {
                if (member instanceof LoyalCustomer){
                    LoyalCustomer loyalCustomer = (LoyalCustomer)member;
                    String row = "LoyalCustomer;" + loyalCustomer;
                    lines.add(row);
                } if (member instanceof RegularCustomer){
                    RegularCustomer regularCustomer = (RegularCustomer)member;
                    String row = "RegularCustomer;" + regularCustomer;
                    lines.add(row);
                } 
                // Write the updated lines to the file
                Files.write(filePath, lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing data to CustomerList.csv: " + e.getMessage());
        }
    }

}