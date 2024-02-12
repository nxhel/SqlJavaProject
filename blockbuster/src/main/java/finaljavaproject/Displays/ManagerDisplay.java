/**
 * ManagerDisplay class implements the IDisplayer interface and provides a menu-driven
 * display for managing the inventory and members of a store.
 *
 * @author Iana Feniuc & Nihel Madani-Fouatih
 * @since 2023-12-29
 */
package finaljavaproject.Displays;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import finaljavaproject.DataProcessing.FileExporter;
import finaljavaproject.DataProcessing.LoadFromFile;
import finaljavaproject.Members.MemberManager;
import finaljavaproject.Members.StoreMember;
import finaljavaproject.Products.Dvd;
import finaljavaproject.Products.InventoryManager;
public class ManagerDisplay implements IDisplayer{

    private static final Scanner scan = new Scanner(System.in);
    private InventoryManager inventoryManager;
    private MemberManager memberManager;
    private FileExporter fileExporter;
    private List <Dvd> dvds;
    /**
     * Constructs a ManagerDisplay object with the specified InventoryManager
     * and MemberManager.
     *
     * @param inventoryman The InventoryManager to be associated with this display.
     * @param memberman    The MemberManager to be associated with this display.
     */
    public ManagerDisplay(InventoryManager inventoryman, MemberManager memberman){
        this.inventoryManager = inventoryman;
        this.memberManager = memberman;
        this.dvds = this.inventoryManager.listofDvds();
    }

    /**
     * Displays the available options for managing the store inventory and members.
     */
    @Override
    public void displayAvailableOptions() {
        System.out.println("====================SuperTrioStore====================");
        System.out.println(" Choose between the options available, to Exit Press 0");
        System.out.println("                To List Employees 1");
        System.out.println("              To List Customers Press 2");
        System.out.println("       To Modify a product Characteristic Press 3");
        System.out.println("        To See All of the Purchases Press 4");
        System.out.println("======================================================");

    }

    /**
     * Handles user tasks based on the provided user choice.
     *
     * @param userChoice The user's choice corresponding to a specific task.
     * @throws IOException
     */
    @Override
    public void handleUserTasks(int userChoice) throws IOException {
        switch (userChoice) {
            case 1: 
                System.out.println("You have selected to display the list of Employees...");
                System.out.println();
                //Display the list of employee by calling method from MemeberManger
                handleOperationForListingEmployee();
                break;
            case 2:
                System.out.println("You have selected to display the list of Customers...");
                System.out.println();
                //Display the list of customers
                handleOperationForListingMember();
                break;
            case 3:
                System.out.println("You have selected to modify certain characteristics of a product...");
                System.out.println();
                //Display the list of customers
                handleOperationForModyfiyngProducts();
                break;
            case 4:
            System.out.println("You have selected to see all the purchases of the store...");
            System.out.println();
            //Display the list of customers
            handleOperationForPurchases(); 
                break;
            default:
                System.out.println("Invalid Choice For Task. Please Enter 1, 2, 3 and 4.");
        }
    }

    /**
     * Gets the user's task choice within a specified range.
     *
     * @param minNumOfTasks The minimum valid task number.
     * @param maxNumOfTasks The maximum valid task number.
     * @return              The user's chosen task number.
     */
    public int getUserTask(int minNumOfTasks, int maxNumOfTasks) {
        int userChosenTask = 0;
        while(true){
            try {
                System.out.println("Enter The Number Corresponding To Your Task");
                userChosenTask=scan.nextInt();
                scan.nextLine();
                if (!(userChosenTask >= minNumOfTasks && userChosenTask <= maxNumOfTasks)) {
                    throw new IllegalArgumentException();
                }
                break;
            } 
            catch(IllegalArgumentException e){
                System.out.println("You enter an invalid number. Please enter a value between "+minNumOfTasks+" and "+maxNumOfTasks+".");
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter only numbers. Please enter a value between  "+minNumOfTasks+" and "+maxNumOfTasks+".");
                scan.nextLine();
            }
        }
        return userChosenTask;
    }

    /**
     * Displays the list of employees using the MemberManager.
     */
    public void handleOperationForListingEmployee(){
         List <StoreMember> members = this.memberManager.listOfEmployees();
         this.memberManager.printMembers(members);
    }

    /**
     * Displays the list of previous purchases using the LoadFromFile class.
     */
    public void handleOperationForPurchases(){
        LoadFromFile file = new LoadFromFile();
        List <String> purchases = file.loadPurchaseLogFromFile();
        for (String purchase : purchases) {
            System.out.println(purchase);
        }
    }

    /**
     * Displays the list of customers based on user's choice (all, loyal, or regular).
     */
    public void handleOperationForListingMember(){
        displayMembers();
        List <StoreMember> members;
        int choice = getUserTask(1,3);
        if(choice ==1){
            System.out.println("Displaying all the customers...");
            members = this.memberManager.listOfAllCustomers();
        }
        else if(choice == 2){
            System.out.println("Displaying only loyal customers...");
            members = this.memberManager.listOfLoyalCustomers();
        }
        else{
            System.out.println("Displaying only regular customers...");
            members = this.memberManager.listOfRegularcustomers();
        }

        this.memberManager.printMembers(members);
    }

    /**
     * Handles the modification of product characteristics (price, title, genre, etc.).
     * @throws IOException
     */
    public void handleOperationForModyfiyngProducts() throws IOException{
        Dvd dvd = validateIfSerialNumberExist();
        System.out.println("Perfect we have found the dvd!");
        this.displayModificationForProducts();
        int choice = getUserTask(1,5);
        String [] fields = new String[]{"price", "title","genre", "availbility", "discount"};
        boolean valid = false;
        int theProductIndex = choice-1;
        while(!valid){
            try{
                System.out.println("Please enter the new "+fields[theProductIndex]+":");
                if(choice==1){
                    double price = scan.nextDouble();
                    this.inventoryManager.modifyPrice(dvd, price);
                    System.out.println(dvd.getPrice());    
                }
                else if(choice==2){
                    String title = scan.nextLine();
                    this.inventoryManager.modifyTitle(dvd, title);
                }
                else if(choice==3){
                    String genre = this.validateIfGenreExist();
                    this.inventoryManager.modifyGenre(dvd, genre);
                }
                else if(choice==4){
                    boolean Availability = scan.nextBoolean();
                    this.inventoryManager.modifyAvailability(dvd,Availability);
                }
                else {
                    double discount = scan.nextDouble();
                    this.inventoryManager.modifyDiscount(dvd,discount);
                }
                valid=true;
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please re-enter:");
                scan.nextLine();
            }
        }
    }
    

    /**
     * Validates and retrieves a DVD object based on the provided serial number.
     *
     * @return The validated DVD object.
     */
    public Dvd validateIfSerialNumberExist(){
        String serialNumber = "";
        boolean valid =false;
        while(!valid){
            try{
                System.out.println("Enter the serialNumber of the wanted Dvd ");
                serialNumber = scan.next();
                scan.nextLine();
                valid = this.inventoryManager.validateIfDvdExistsBySerialNumber(serialNumber);
                if(!valid){
                    throw new IllegalArgumentException();
                }
            }
            catch(IllegalArgumentException e){
                System.out.println("We're sorry, but the Dvd's serial number doesn't correspond to anything in our inventory! Please try again.");
            }
        }
        Dvd dvd = this.inventoryManager.findDvdBySerialNumber(serialNumber);
        return dvd;
    }

    /**
     * Validates and retrieves a genre based on user input.
     *
     * @return The validated genre string.
     */
    public String validateIfGenreExist(){
        String genre = "";
        boolean valid =false;
        while(!valid){
            try{
                System.out.println("Enter the new genre :");
                genre = scan.next();

                valid = this.inventoryManager.validateIfGenreExist(genre);
                if(!valid){
                    throw new IllegalArgumentException();
                }
                scan.nextLine();
            }
            catch(IllegalArgumentException e){
                System.out.println("We're sorry, but this Genre doesn't correspont to anything in our inventory! Please try again:");
                scan.nextLine();
            }
        }
        return genre;
    }

    /**
     * Displays options for displaying different sets of members (all, loyal, regular).
     */
    public void displayMembers(){
        System.out.println("====================SuperTrioStore====================");
        System.out.println("        To display all the cutsomers press 1");
        System.out.println("         To display only loyal customers 2");
        System.out.println("      To display only regular customers press 3");
        System.out.println("======================================================");
    }

    /**
     * Displays options for modifying different characteristics of a DVD.
     */
    public void displayModificationForProducts(){
        System.out.println("====================SuperTrioStore====================");
        System.out.println("    To modify the price of the dvd press 1");
        System.out.println("    To modify the title of the dvd press 2");
        System.out.println("    To modify the genre of the dvd press 3");
        System.out.println("  To modify the avilability of the dvd press 4");
        System.out.println("   To modify the discount of the dvd press 5");
        System.out.println("======================================================");
    }
   
}

