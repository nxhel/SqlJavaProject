package finaljavaproject.DataProcessing;

import finaljavaproject.Members.*;
import finaljavaproject.Members.Customer.*;
import finaljavaproject.Products.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class LoadFromFile implements FileImporter {

    /**
    * Loads a list of DVD objects from a CSV file.
    * @return A list containing DVD objects read from the file.
    *         If an error occurs during file reading or processing, an empty list is returned.
    */
    public List<Dvd> loadDvdsFromFile() {
        
        try {
            Path thePath = Paths.get("Products.csv");
            
            if (!Files.exists(thePath)) {
                System.err.println("File Not Found");
                return null;
            }
            List<String> lines = Files.readAllLines(thePath);
            List<Dvd> allDvds = new ArrayList<>();
            boolean isFirstLine =true;
            for (String line : lines) {
                if(isFirstLine){
                    isFirstLine=false;
                    continue;
                }
                String[] columns = line.split(";");
                Dvd theDvd = createDvdFromFile(columns);
                allDvds.add(theDvd);
            }
    
            return allDvds;
        } catch (IOException e) {
            System.out.println("AN ERROR HAS OCCURRED");
            e.printStackTrace(); // Optionally log the exception for debugging purposes
            return new ArrayList<>(); // Return an empty list or handle the exception accordingly
        }
    }

        /**
     * Creates a DVD object based on the information from the provided columns array.
     *
     * @param columns Array of strings containing data to create a DVD object.
     *                The array should contain information in the following order:
     *                DVD details in this order: [Type, Price, Title, Genre, Serial Number, Duration,
     *                Availability, Discount, Quantity, Parameter1, Parameter2]
     *                Parameter1 is specific to the type (e.g., director, platform, artist)
     *                Parameter2 is specific to the type (e.g., studio, publisher, music label)
     *
     * @return A DVD object created based on the provided information.
     *         If the provided DVD type is invalid, returns null.
     */
    public Dvd createDvdFromFile(String[] columns){

        Dvd theDvd = null;
        String theType=  columns[0];
        double thePrice = Double.parseDouble(columns[1]);
        String theTitle = columns[2];
        Genre theGenre = Genre.valueOf(columns[3].toUpperCase());
        String theSN = columns[4];
        double theDuration = Double.parseDouble(columns[5]);
        boolean isAvailable = Boolean.parseBoolean(columns[6]);
        double theDiscount = Double.parseDouble(columns[7]);
        int quantity = Integer.parseInt(columns[8]); // I added this
       
        switch (theType){
            case "Movie":
                theDvd = new Movie(thePrice, theTitle, theGenre, theSN, theDuration, isAvailable, theDiscount, quantity,columns[9],columns[10]);
                break;
            case "Game":
                theDvd = new Game(thePrice, theTitle, theGenre, theSN, theDuration, isAvailable, theDiscount, quantity,columns[9],columns[10]);
                break;
            case "Music":
                theDvd = new Music(thePrice, theTitle, theGenre, theSN, theDuration, isAvailable, theDiscount, quantity,columns[9],columns[10]);
                break;
            default:
                System.out.println("Invalid Dvd Type");
            break;
        }
        return theDvd;
    }

    /**
     * Loads StoreMember information from a CSV file and returns a list of StoreMember objects.
     *
     * @return A list containing StoreMember objects read from the file.
     *         If the file is not found or an error occurs during the process, returns an empty list.
     */
    
    public List <StoreMember> loadStoreMembersFromFile(){
        try {
            Path thePath = Paths.get("Employees.csv");
            
            if (!Files.exists(thePath)) {
                System.err.println("File Not Found");
                return null;
            }

            List<String> lines = Files.readAllLines(thePath);
            List<StoreMember> allEmployee = new ArrayList<>();
            boolean isFirstLine =true;

            for (String line : lines) {
                if(isFirstLine){
                    isFirstLine=false;
                    continue;
                }
                String[] columns = line.split(";");
                StoreMember theStoreEmployee = createStoreMemberFromFile(columns);
                allEmployee.add(theStoreEmployee);
            }
            return allEmployee;
        }catch (IOException e) {
            e.printStackTrace(); // Optionally log the exception for debugging purposes
            return new ArrayList<>(); // Return an empty list or handle the exception accordingly
        }
    }

    /**
     * Creates a StoreMember object based on information parsed from a string array.
    * @param columns An array containing information about a store member.
    *                The array contains [Type, Firstname, Lastname, Email].
    * @return A StoreMember object created from the provided information.
    *         If the provided type is not recognized, returns null.
    */
    public StoreMember createStoreMemberFromFile(String[] columns){

        StoreMember theStoreEmployee = null;
        String theType = columns[0].trim(); 
        String firstname = columns[1].trim();
        String lastname = columns[2].trim();
        String email = columns[3].trim();
        switch (theType){
            case "Manager":
                theStoreEmployee = new Manager(firstname,lastname,email);
                break;
            case "Employee":
                theStoreEmployee = new Employee(firstname,lastname,email);
                break;
            default:
                System.out.println("Invalid Customer "+ email );
                break;
        }
        return theStoreEmployee;
    }
    
    /**
     *  Loads Customer information from a CSV file and returns a list of Customer objects.
     * @return A list of Customer objects containing information from the file.
     *         If the file is not found or an error occurs during reading, returns an empty list.
     */
    @Override
    public List <StoreMember> loadCustomersFromFile() {
       try {

            Path thePath = Paths.get("Customers.csv");
            
            if (!Files.exists(thePath)) {
                System.err.println("File Not Found");
                return null;
            }

            List<String> lines = Files.readAllLines(thePath);
            List<StoreMember> allCustomers = new ArrayList<>();
            boolean isFirstLine =true;

            for (String line : lines) {
                if(isFirstLine){
                    isFirstLine=false;
                    continue;
                }
                String[] columns = line.split(";");
                Customer theCustomer = createCustomerFromFile(columns);
                allCustomers.add(theCustomer);
            }
    
            return allCustomers;
        }catch (IOException e) {
            e.printStackTrace(); 
            return new ArrayList<>(); 
        }
    }
    /**
     * Loads user infromation from User.csv file .
     * 
     * @return A List ofStrings representing the user username and passewords. 
     */


    public List <String> loadUsersFromFile() {
       try {
            Path thePath = Paths.get("User.csv");
            
            if (!Files.exists(thePath)) {
                System.err.println("File Not Found");
                return null;
            }

            List<String> lines = Files.readAllLines(thePath);
            List <String> allUsers = new ArrayList<>();
            boolean isFirstLine =true;

            for (String line : lines) {
                if(isFirstLine){
                    isFirstLine=false;
                    continue;
                }
                String[] columns = line.split(";");
                allUsers.add(columns[0]);
                allUsers.add(columns[1]);
            }
    
            return allUsers;
        }

        catch (IOException e) {
            e.printStackTrace(); 
            return new ArrayList<>(); 
        }
    }

    /**
     * Creates a Customer object from the provided details.
     * @param columns Details in the format: [Type, First Name, Last Name, Email, Discount/Loyalty Points]
     *                The array contains [Type, Firstname, Lastname, Email] and
     *                - Discount/Loyalty Points: 'Loyal' customer's discount percentage or 'Regular' customer's loyalty points.
     * @return A Customer object based on the provided information. Returns null for invalid types or parsing errors.
     */

    public Customer createCustomerFromFile(String[] columns){

        Customer theCustomer = null;
        String theType = columns[0].trim(); 
        String firstname = columns[1].trim();
        String lastname = columns[2].trim();
        String email = columns[3].trim();
        int loyaltyPoints =  Integer.parseInt(columns[4]);
        
        switch (theType){
            case "RegularCustomer":
                theCustomer = new RegularCustomer(firstname,lastname,email,loyaltyPoints);
                break;
            case "LoyalCustomer":
                theCustomer = new LoyalCustomer(firstname,lastname,email, loyaltyPoints);
                break;
            default:
                System.out.println("Invalid Customer " + email );
                break;
        }
        return theCustomer;
    }

     public List<String> loadPurchaseLogFromFile() {
        
        try {
            Path thePath = Paths.get("PurchaseLog.csv");
            
            if (!Files.exists(thePath)) {
                System.err.println("File Not Found");
                return null;
            }
            List<String> lines = Files.readAllLines(thePath);
            List<String> allLogs = new ArrayList<>();
            boolean isFirstLine =true;
            for (String line : lines) {
                if(isFirstLine){
                    isFirstLine=false;
                    continue;
                }
                String[] columns = line.split(";");
                String theLog = createLogFromFile(columns);
                allLogs.add(theLog);
            }
    
            return allLogs;
        } catch (IOException e) {
            System.out.println("AN ERROR HAS OCCURRED");
            e.printStackTrace(); // Optionally log the exception for debugging purposes
            return new ArrayList<>(); // Return an empty list or handle the exception accordingly
        }
    }

    /**
     * Creates a log from an array of columns.
     * @param columns An array containing columns of log data.
     * The customer information, The purchase details, The total price.
     * @return A concatenated string containing customer, purchase, and total price information.
     */
     public String createLogFromFile(String[] columns){
        String theCustomer = columns[0];
        String thePurchase = columns[1];
        String theTotalPrice =columns[2];

        String toReturn = theCustomer +thePurchase +theTotalPrice ;
            return toReturn;
    }
}   





    
