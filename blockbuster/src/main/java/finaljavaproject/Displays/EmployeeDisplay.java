 /**
  *
  * @author Nihel Madani-Fouatih, Shakela Hossain & Iana Feniuc 
  * @since 2023-12-29
  */
package finaljavaproject.Displays;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import finaljavaproject.DataProcessing.*;
import finaljavaproject.Members.*;
import finaljavaproject.Members.Customer.*;
import finaljavaproject.Products.*;

public class EmployeeDisplay implements IDisplayer{
    private static final Scanner scan = new Scanner(System.in);
    private FileExporter  fileexporter = new FileExporter();
    private InventoryManager inventoryManager;
    private List<StoreMember> customers;
    
    public EmployeeDisplay (InventoryManager inventoryManager, List<StoreMember> customers){
        this.inventoryManager = inventoryManager;
        this.customers=customers;
    }

    /**
     * Prompts the user to input a number corresponding to a task within a specified range.
     *
     * @param minNumOfTasks minimum value of the task range.
     * @param maxNumOfTasks maximum value of the task range.
     * @return The task chosen by the user as a number within the specified range.
     */
    public int getUserTask(int minNumOfTasks,int maxNumOfTasks) {
        int userChosenTask = 0;
        while(true){
            try {
                System.out.println("Enter The Number Corresponding To Your Task");
                userChosenTask=scan.nextInt();
                if (!(userChosenTask >= minNumOfTasks && userChosenTask <= maxNumOfTasks)) {
                    throw new IllegalArgumentException();
                }
                break;
            } 
            catch(IllegalArgumentException e){
                System.out.println("You enter an invalid number. Please enter a value between " + minNumOfTasks +" and " + maxNumOfTasks);
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter only numbers. Please enter a valid number");
                scan.nextLine();
            }
        }
        scan.nextLine();
        return userChosenTask;
    }

    /**
     * Handles user tasks based on the chosen option.
     * @param userChosenTask The task number chosen by the user.
     */
    public void handleUserTasks(int userChosenTask){
        switch (userChosenTask) {
            case 1:
                handleOperationForPurchase();
                break;
            case 2:
                handleOperationForListingDvds();
                break;
            case 3:
                handleOperationForCheckingPoints();
                break;
            default:
                System.out.println("Invalid Choice For Task. Please Enter 1, 2 Or 3.");
        }
    }

    public void handleOperationForCheckingPoints(){
        System.out.println("Please Enter the  Email Of the Customer You Desire To Check The Points ");
        String userEmail = scan.nextLine();
        for (int  i=0; i<this.customers.size();i++){
            StoreMember customer = this.customers.get(i);
            if (customer.getEmail().equalsIgnoreCase(userEmail)) {
                int points = ((Customer) customer).getPoints();
                String fullName = ((Customer) customer).getFirstname() + ", " + ((Customer) customer).getLastname();
                System.out.println("Customer points for Customer " + fullName + " is [ " + points + " ]" );
            }
        }
    }

    public void  handleOperationForPurchase(){
        System.out.println("For a Returning Customer Press 1, For a New Customer Press 2");
        int choiceSortedDvds = getUserTask(1,2);
        switch (choiceSortedDvds) {
            case 1:
                PassCustomerPurchase(null);
                break;
            case 2:
                Customer newCustomer = createCustomer(typeOfCustomer());
                this.customers.add(newCustomer);
                PassCustomerPurchase(newCustomer.getEmail());
                break;
            default :
                System.out.println("Invalid Input");
        }
        MemberManager member = new MemberManager(this.customers);
        List<StoreMember> updateStoreMembers = member.listOfAllMembers();
        fileexporter.writeCustomer(updateStoreMembers);
    }

    public String typeOfCustomer(){
        System.out.println("For a Regular Customer press 1, For a Loyal Customer Press 2");
        int userCustomerType= getUserTask(1,2);
        String customerType= "";
        switch (userCustomerType) {
            case 1:
                customerType="Regular";
                break;
            case 2:
                customerType= "Loyal";
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        return customerType;
    }
    
    /**
     * Creates a new Customer (Regular or Loyal) based on the specified person type from the user.
     * @param person The type of Customer to create ("Regular" or "Loyal").
     * @return A new Customer instance (Regular or Loyal) based on the provided type.
     */
    public Customer createCustomer(String person){
        System.out.println("Enter The FirstName of The New " + person  + " Customer ");
        String userName= scan.nextLine();
        System.out.println("Enter The lastName of The New " + person + " Customer ");
        String userLastName= scan.nextLine();
        System.out.println("Enter a Valid Email for The New " + person + " Customer ");
        String userEmail= scan.nextLine();
        if(person.equalsIgnoreCase("Regular")){
             return new RegularCustomer(userName, userLastName, userEmail,0);
        }else{
            return new LoyalCustomer(userName, userLastName, userEmail,0);
        }
    }

    public void PassCustomerPurchase(String email){
        try{
            this.fileexporter.writeInPurchaseLog(Purchase(this.inventoryManager.listofDvds(),this.customers, email));
        }catch (IOException exception){
            System.out.println("Error Occured in OperationForPurchase");
        }
    }

    public void handleOperationForListingDvds(){
        System.out.println("To View A List Of Sorted Dvds Press 1, Filter a List Of Dvds Press 2");
        int choiceListingDvds = getUserTask(1,3);
        switch (choiceListingDvds) {
            case 1 :
                handleOperationForSortedDvds();
                break;
            case 2 : 
                handleOperationForFilteredDvds();      
                break;
            default :
                System.out.println("Invalid Input");  
        }
    }

    public void handleOperationForSortedDvds(){
        System.out.println("To View Sorted by Discount (Ascending Order) Press 1, By Duration (Ascending Order) Press 2,  By Price (Ascending Order) Press 3 ");
        int choiceSortedDvds = getUserTask(1,2);
        switch (choiceSortedDvds) {
            case 1 :
                printDvds(this.inventoryManager.SortByDiscountinAscensingOrder());
                break;
            case 2 : 
                printDvds(this.inventoryManager.SortByDurationinAscensingOrder());
                break;
            case 3 :
                printDvds(this.inventoryManager.SortByPriceinAscendingOrder());
            default :
                System.out.println("Invalid Input");  
            }
    }

    public void handleOperationForFilteredDvds(){
        System.out.println("To View Filtered by Title Press 1, By Genre Press 2,  By Price Less than 20 Press 3, By Availabality Press 4, By Type (Movie/Game/Music) Press 5 ");
        int choiceSortedDvds = getUserTask(1,5);
        String userLooksFor = "";
        switch (choiceSortedDvds) {
            case 1 :
                userLooksFor = "Title";
                printDvds(this.inventoryManager.FilterByTitleIfContainsTheChoosenWord(PromptUserToEnterString(userLooksFor)));
                break;
            case 2 : 
                userLooksFor = "Genre";
                printDvds(this.inventoryManager.FilterByGenre(getUserInputGenre("Enter A Genre")));
                break;
            case 3 :
                printDvds(this.inventoryManager.FilterByPriceLessThan20());
                break;
            case 4 :
                printDvds(this.inventoryManager.FilterByAvailability());
                break;
            case 5 :
                handleListOfDvdType();
            default :
                System.out.println("Invalid Input");  
            }
    }

    /**
     * Displays a list of DVDs depending on the user Choide
     * Displaying include lists of movies, music, games, or all DVDs in the inventory.
     * Calls corresponding methods to print the selected DVD lists.
     */
    public void handleListOfDvdType(){
        System.out.println("To See All Movies Press 1, All Music Press 2, All Games Press 3, Everything Press 4");
        int choiceListDvd = getUserTask(1,4);
         switch (choiceListDvd) {
            case 1 :
                printDvds(this.inventoryManager.listOfMovies());
                break;
            case 2 : 
                printDvds(this.inventoryManager.listOfMusic());
                break;
            case 3 :
                printDvds(this.inventoryManager.listOfGames());
                break;
             case 4 :
                printDvds(this.inventoryManager.listofDvds());
                break;
            default :
                System.out.println("Invalid Input");
        }
    }

    /**
     * Prompts the user to enter the title of the DVD they would like to purchase
     * It then checks if the DVD exists in the inventory,Returns the
     * purchased DVD or null if the DVD does not exist.
     *
     * @param dvds  A list of DVDs representing the current inventory.
     * @return      The purchased DVD or null if the DVD is not found.
     * 
     * @throws NullPointerException If no dvd is found with the provided title.
     */
    private static Dvd purchasedDvd(List<Dvd> dvds){ // not accessible outside
        System.out.println("What Dvd would you like to purchase?");
        String title = "";
        Dvd dvd = null;
        boolean valid = false;
        InventoryManager inventory = new InventoryManager(dvds);
        while(!valid){
            try{
                title = scan.nextLine();
                dvd = inventory.findDvd(title);
                if(dvd == null){
                    throw new NullPointerException();
                }else{
                    valid = true;
                }
            } catch(NullPointerException e){
                System.out.println("Sorry, the dvd does not exist. Enter a valid title");
            }
        }
        return dvd;
    }

    /**
     * Retrieves a StoreMember based on the provided customer email.
     *
     * 
     * This method prompts the user to enter a customer email, searches for
     * a matching StoreMember in the provided list, and returns the found member.
     * If no member is found, it throws a NullPointerException and returns null.
     *
     * @param members  A list of StoreMembers representing the store's customers.
     * @return         The StoreMember associated with the entered email or null if not found.
     *
     * @throws NullPointerException If no StoreMember is found with the provided email.
     */
    private static StoreMember customerRelatedToPurchase(List<StoreMember> members){ // not accessible outside
        System.out.println("Enter Customer Email: ");
        String customerEmail = "";
        StoreMember member = null; 
        MemberManager membersList = new MemberManager(members);
        boolean valid = false;
        while(!valid){
            try{
                customerEmail = scan.nextLine();
                member = membersList.findMemberByEmail(customerEmail);
                if(member == null){
                    throw new NullPointerException();
                }else{
                    valid = true;
                }  
            } catch(NullPointerException e){
                System.out.println("Sorry, the member does not exist. Please enter a valid email");
            }
        }
        return member;
    }
    /* 
    * Retrieves a StoreMember based on the provided customer email if its a new customer.
    * Since the email of the customer is asked when created, we just pass the email for this method.
    * @param members  A list of StoreMembers representing the store's customers.
    *                 The email of the costumer.
    */ 
    private static StoreMember customerRelatedToPurchaseIfNewCustomer(List <StoreMember> members, String email){
        MemberManager membersList = new MemberManager(members);
        StoreMember member = membersList.findMemberByEmail(email);
        return member;
    }

    /**
     * Simulates a purchase transaction for DVDs by a store member.
     *
     * This method guides the employee through the process of selecting DVDs,
     * specifying quantities, and calculating the total price for the purchase.
     * The details of the purchase are then logged and returned as a string.
     *
     * @param dvds    A list of available DVDs in the store's inventory.
     * @param members A list of store members, from which the customer related to the purchase is identified.
     * @return        A log string containing details of the purchase, including customer information, purchased DVDs, and total price.
     * @throws InputMismatchException If there is an error in user input (e.g., non-integer quantity).
     * @throws IllegalArgumentException If the user attempts to purchase zero DVDs or enters an invalid input.
     */
    public static String Purchase(List<Dvd> dvds, List<StoreMember> members, String email){ // use this for the application class for employee
        StoreMember member;
        if(email==null){
            member = customerRelatedToPurchase(members);
        }
        else{
            member = customerRelatedToPurchaseIfNewCustomer(members, email);
        }
        Boolean orderAgain = false;
        int quantity = 0;
        double price = 0.0;
        double totalPrice = 0.0;
        double discountedPrice = 0.0;
        Dvd purchase = null;
        List<String> purchaseLog = new ArrayList<String>();
        while (!orderAgain) {
            try {
                purchase = purchasedDvd(dvds);
                quantity = getValidQuantity(purchase);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid");
            } catch (InputMismatchException e) {
                System.out.println("Wrong input.");
            }
            // rest of your code
            price = purchase.getPrice()*quantity;
            String dvd = "Title: "+purchase.getTitle()+"  Qty: "+ quantity + "  Price: "+price;
            totalPrice += price;
            purchaseLog.add(dvd);
            System.out.println("Would you like to add another Item? Y/N");
            String ans = scan.nextLine();
            if(ans.equalsIgnoreCase("N")){
                orderAgain = true;
            }
        }
        discountedPrice = ((Customer) member).discountedPrice(price);
        String firstname = member.getFirstname();
        String lastname = member.getLastname();
        String log = "Customer: " + lastname + ", " + firstname + "; Purchase: " + purchaseLog + "; Total Price: " +totalPrice + "; Price with Discount: " + discountedPrice + ";";
        return log;
    }

    /**
     * Ensures the input quantity is valid for the given DVD purchase.
     *
     * @param purchase  The DVD for which the user is entering the quantity.
     * @return          The valid quantity entered by the user.
     * @throws InputMismatchException If there is an error in user input (non-integer quantity).
     * @throws NumberFormatException  If the entered quantity is not a valid integer.
     */
    public static int getValidQuantity(Dvd purchase) {
        while (true) {
            try {
                System.out.println("How many would you like to purchase?");
                int quantity = Integer.parseInt(scan.nextLine());
                if (quantity < purchase.getQuantity() && quantity > 0) {
                    purchase.setQuantity(purchase.getQuantity() - quantity);
                    return quantity;
                } else if (quantity > purchase.getQuantity()) {
                    System.out.println("Sorry, we don't have that many in stock");
                } else {
                    throw new InputMismatchException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            } catch (InputMismatchException e) {
                System.out.println("Wrong input.");
            }
        }
    }

    /**
     * Prompt the user to enter a Genre and return it.
     * @param message The message to display to the user.
     * @return The Genre value entered by the user.
     */
    public String getUserInputGenre(String message) {
        System.out.println(message);
        String genre =null;
        boolean valid =false;
        while(!valid){
        try{
            genre = scan.next();
            valid = this.inventoryManager.validateIfGenreExist(genre);
            if(!valid){
                throw new IllegalArgumentException();
            }
            scan.nextLine();

        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid Genre, Please Input a Correct Value");
            scan.nextLine();
        }
        }
        return genre;
    }

        /**
     * Prompts the user to enter a serial number for a DVD.
     * @return A string, the serial number for the DVD.
     */
    public String PromptUserToEnterString(String filteredBy){
        System.out.println("Please Enter A " + filteredBy +  " You are Looking for");
        String userInput= scan.nextLine();
        return userInput ;
    }

    /**
     * prints all of the Dvd in the provided list. 
     * @param dvds A list containing DVD objects.
     */
     public void printDvds (List<Dvd> dvds){
        for (Dvd dvd : dvds) {
            System.out.println(dvd.toString());
        }
    }

     @Override
    public void displayAvailableOptions() {
        System.out.println("====================SuperTrioStore====================");
        System.out.println("Choose between the options available, to Exit Press 0 ");
        System.out.println("to Pass a Purchase For a Customer Press 1");
        System.out.println("to List all Dvds Press 2");
        System.out.println("to Check Customer's Remaining Point Press 3 ");
        System.out.println("======================================================");
        
    }
}