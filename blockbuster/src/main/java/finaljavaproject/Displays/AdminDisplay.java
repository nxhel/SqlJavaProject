 /**
  *
  * @author Nihel Madani-Fouatih & Iana Feniuc 
  * @since 2023-12-29
  */
package finaljavaproject.Displays;

import java.io.IOException;
import java.util.*;

import finaljavaproject.DataProcessing.FileExporter;
import finaljavaproject.Members.*;
import finaljavaproject.Members.Customer.*;
import finaljavaproject.Products.*;


public class AdminDisplay implements IDisplayer{    
    private static final Scanner scan = new Scanner(System.in);
    private InventoryManager inventoryManager;
    private MemberManager memberManager;
    private FileExporter fileExporter = new FileExporter();

    /**
     * Initializes an AdminDisplay instance with the provided InventoryManager and MemberManager.
     * @param inventoryman The InventoryManager instance to manage inventory-related operations.
     * @param memberman The MemberManager instance to manage member-related operations.
     */
    public AdminDisplay (InventoryManager inventoryman, MemberManager memberman){
        this.inventoryManager = inventoryman;
        this.memberManager = memberman;
     }
        
    /**
     * Prompts the user to input a number corresponding to a task within a specified range.
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
     * @throws IOException
     */
    public void handleUserTasks(int userChosenTask) throws IOException{
        switch (userChosenTask) {
            case 1:
                handleOperationForAddingMember();
                break;
            case 2:
                handleOperationForRemovingMember();
                break;
            case 3:
                handleOperationForListingMember();
                break;
            case 4:
                handleOperationForAddingDvd();
                break;
            case 5:
                handleOperationForRemovingDvd();
                break;
            case 6:
                handleOperationForlistingDvd();
                break;
            default:
                System.out.println("Invalid Choice For Task. Please Enter 1,2,3,4,5 Or 6.");
        }
    }

    /**
     * Handles the operation to create a new StoreMember(Employee, Manager, Regular Customer, Loyal Customer).
     * It prompts the user to choose the type of member to be added and then adds the member to the MemberManager.
     */
    public void handleOperationForAddingMember(){
        displayAction("To Create An Employee Press 1,\n" + "A Manager Press 2,\n" + "A Regular Customer Press 3,\n" + "A Loyal Customer Press 4");
        int choiceStoreMember = getUserTask(1,4);
        String person = "";
        switch (choiceStoreMember) {
            case 1 :
                person= "Employee";
                StoreMember employee = createStoreMemberEmployee(person);
                this.memberManager.addMember(employee);
                break;
            case 2 : 
                person= "Manager";
                StoreMember manager = createStoreMemberEmployee(person);
                this.memberManager.addMember(manager);
                break;
            case 3 :
                person = "Regular";
                Customer RegularCustomer = createCustomer(person);
                this.memberManager.addMember(RegularCustomer);
                break;
             case 4 :
                person = "Loyal";
                Customer loyalCustomer = createCustomer(person);
                this.memberManager.addMember(loyalCustomer);
                break;
            default :
                System.out.println("Invalid Input");
        }
        List<StoreMember> updatedMembers = this.memberManager.listOfAllMembers();
        for(StoreMember updatedMember: updatedMembers){
            if(updatedMember instanceof Customer){
                fileExporter.writeCustomer(updatedMembers);
            }else if ((updatedMember instanceof Manager) || (updatedMember instanceof Employee)){
                fileExporter.writeEmployee(updatedMembers);
            }else{
                System.out.println("Sorry, this store memeber does not exist");
            }
        }
    }

    /**
     * Creates a new StoreMember (Employee or Manager) based on the specified person type that the user Chose.
     * @param person The type of StoreMember to create ("Employee" or "Manager").
     * @return A new StoreMember instance (Employee or Manager) based on the provided type.
     */
    public StoreMember createStoreMemberEmployee(String person){
        String userName = getUserInputString("Enter The FirstName of The New " + person);
        String userLastName= getUserInputString("Enter The lastName of The New " + person);
        String userEmail = getUserInputString("Enter a Valid Email for The New " + person );
        if(person.equalsIgnoreCase("Employee")){
            return new Employee(userName, userLastName, userEmail);
        }else{
            return new Manager(userName, userLastName, userEmail);
        }
    }

    /**
     * Creates a new Customer (Regular or Loyal) based on the specified person type from the user.
     * @param person The type of Customer to create ("Regular" or "Loyal").
     * @return A new Customer instance (Regular or Loyal) based on the provided type.
     */
    public Customer createCustomer(String person){
        String userName = getUserInputString("Enter The FirstName of The New " + person);
        String userLastName= getUserInputString("Enter The lastName of The New " + person);
        String userEmail = getUserInputString("Enter a Valid Email for The New " + person );
        if(person.equalsIgnoreCase("Regular")){
            System.out.println("Enter a Valid Email for The New " + person );
            return new RegularCustomer(userName, userLastName, userEmail,0);
        }else{
            return new LoyalCustomer(userName, userLastName, userEmail,0);
        }
    }

    /**
     * Handles the operation to remove a member of different type (Employee, Manager, Regular Customer, Loyal Customer)
     * Prompts the user to select the type of member to remove, and prompts the member's email for identification.
     * Removes the chosen member from the member manager's list.
     */
    public void handleOperationForRemovingMember(){
        displayAction("To Remove An Employee Press 1,\n" + "A Manager Press 2,\n" + "A Regular Customer Press 3,\n" + 
        "A Loyal Customer Press 4");
        int choiceStoreMember =getUserTask(1,4);

        switch (choiceStoreMember) {
            case 1 :
                StoreMember employee = this.memberManager.findMemberByEmail(PromptUserToEnterEmail());
                this.memberManager.removeMember(employee);
                break;
            case 2 : 
                StoreMember manager = this.memberManager.findMemberByEmail(PromptUserToEnterEmail());
                this.memberManager.removeMember(manager);
                break;
            case 3 :
                StoreMember RegularCustomer = this.memberManager.findMemberByEmail(PromptUserToEnterEmail());
                this.memberManager.removeMember(RegularCustomer);
                break;
             case 4 :
                StoreMember LoyalCustomer = this.memberManager.findMemberByEmail(PromptUserToEnterEmail());
                this.memberManager.removeMember(LoyalCustomer);
                break;
            default :
                System.out.println("Invalid Input");
        } 
        List<StoreMember> updatedMembers = this.memberManager.listOfAllMembers();
        for(StoreMember updatedMember: updatedMembers){
            if(updatedMember instanceof Customer){
                fileExporter.writeCustomer(updatedMembers);
            }else if ((updatedMember instanceof Manager) || (updatedMember instanceof Employee)){
                fileExporter.writeEmployee(updatedMembers);
            }else{
                System.out.println("Sorry, this store memeber does not exist");
            }
        }
    }

    /**
     * Prompts the user to enter an email to find a store member.
     * @return A string, the user-entered email.
     */
    public String PromptUserToEnterEmail(){
        System.out.println("Please Enter the Email To find the Member");
        String userEmail= scan.nextLine();
        return userEmail ;
    }

    /**
     * Handles the operation for listing members based on the user's choice.
     * Retrieves user input to display the list of the chosen type of members.
     */
    public void handleOperationForListingMember(){
        displayAction("To See All Employees Press 1,\n" + "All Managers Press 2,\n" + "All Regular Customers Press 3,\n" + 
        "All Loyal Customers Press 4,\n" + "All Store Members Press 5");
        int choiceListMembers = getUserTask(1,5);

         switch (choiceListMembers) {
            case 1 :
                 this.memberManager.printMembers(this.memberManager.listOfEmployees());
                break;
            case 2 : 
                 this.memberManager.printMembers(this.memberManager.listOfManagers());
                break;
            case 3 :
                this.memberManager.printMembers(this.memberManager.listOfRegularcustomers());
                break;
             case 4 :
                 this.memberManager.printMembers(this.memberManager.listOfLoyalCustomers());
                break;
            case 5: 
                 this.memberManager.printMembers(this.memberManager.listOfAllMembers());
                break;
            default :
                System.out.println("Invalid Input");
        }
    }

    /**
     * Handles the operation for adding a new DVD item to the inventory.
     * Prompts the user to select the type of DVD (Movie, Game, or Music) to add.
     * Retrieves user input to create a new DVD of the chosen type and adds it to the inventory.
     * @throws IOException
     */
    public void handleOperationForAddingDvd() throws IOException{
        displayAction("To Add A New Movie Press 1,\n" + "A New Game Press 2,\n" + "A New Music Press 3");
        int userDvdChoice = getUserTask(1,3);
        String dvdType = "";
        switch (userDvdChoice) {
            case 1 :
                dvdType= "Movie";
                Dvd movie = createStoreDvd(dvdType);
                this.inventoryManager.addToInventory(movie);
                break;
            case 2 : 
                dvdType= "Game";
                Dvd game = createStoreDvd(dvdType);
                this.inventoryManager.addToInventory(game);
                break;
            case 3 :
                dvdType = "Music";
                Dvd music = createStoreDvd(dvdType);
                this.inventoryManager.addToInventory(music);
                break;
            default :
                System.out.println("Invalid Input");
        }
        List<Dvd> updatedDvds =  this.inventoryManager.listofDvds();
        fileExporter.writeDvds(updatedDvds);
    }

        /**
     * Creates a new DVD object based on user input.
     * @param dvdType The type of DVD to create ("Movie", "Music", or "Game").
     * @return A new DVD object with informatino provided by the user
     */
    public Dvd createStoreDvd(String dvdType){

        double newPrice = getUserInputDouble("Enter The Price of The New " + dvdType);
        String newTitle = getUserInputString("Enter The Title of The New " + dvdType);
        Genre newGenre = getUserInputGenre("Enter the Genre for The New " + dvdType);
        String newSerialNum = getUserInputString("Enter The Serial Number of The New " + dvdType);
        double newDuration = getUserInputDouble("Enter the Duration for The New " + dvdType);
        boolean newAvailability = true; 
        double newDiscount = getUserInputDouble("Enter the Discount for The New " + dvdType);
        int newQty = getUserInputInt("Enter the Quantity for The New " + dvdType);
    
        if (dvdType.equalsIgnoreCase("Movie")) {
            String userDirector = getUserInputString("Enter the Director for The New " + dvdType);
            String userStudio = getUserInputString("Enter the Studio for The New " + dvdType);
            return new Movie(newPrice, newTitle, newGenre, newSerialNum, newDuration, newAvailability, newDiscount, newQty, userDirector, userStudio);
        } else if (dvdType.equalsIgnoreCase("Music")) {
            String userArtist = getUserInputString("Enter the Artist for The New " + dvdType);
            String userMusicLabel = getUserInputString("Enter the Music Label for The New " + dvdType);
            return new Music(newPrice, newTitle, newGenre, newSerialNum, newDuration, newAvailability, newDiscount, newQty, userArtist, userMusicLabel);
        } else {
            String userPlatform = getUserInputString("Enter the Gaming Platform for The New " + dvdType);
            String userPublisher = getUserInputString("Enter the Publisher for The New " + dvdType);
            return new Game(newPrice, newTitle, newGenre, newSerialNum, newDuration, newAvailability, newDiscount, newQty, userPlatform, userPublisher);
        }
    }

    /**
     * Removes a Dvd from the Inventory based on the UserChoice (movie/music/game.)
     * Calls corresponding methods to find the DVD by its serial number and removes it from the inventory.
     * @throws IOException
     */
    public void handleOperationForRemovingDvd() throws IOException{
        displayAction("To Remove A Movie Item Press 1,\n" + "A Music Item Press 2,\n" +"A Game Item Press 3");
        int choiceStoreMember =getUserTask(1,3);

        switch (choiceStoreMember) {
            case 1 :

                Dvd movie = this.inventoryManager.findDvdBySerialNumber(PromptUserToEnterDvdSerialNumber());
                this.inventoryManager.removeFromInventory(movie);
                break;
            case 2 : 
                Dvd music = this.inventoryManager.findDvdBySerialNumber(PromptUserToEnterDvdSerialNumber());
                this.inventoryManager.removeFromInventory(music);
                break;
            case 3 :
                Dvd Game = this.inventoryManager.findDvdBySerialNumber(PromptUserToEnterDvdSerialNumber());
                this.inventoryManager.removeFromInventory(Game);
                break;
            default :
                System.out.println("Invalid Input");
        }
        List<Dvd> updatedDvds =  this.inventoryManager.listofDvds();
        fileExporter.writeDvds(updatedDvds);
    }

    /**
     * Prompts the user to enter a serial number for a DVD.
     * @return A string, the serial number for the DVD.
     */
    public String PromptUserToEnterDvdSerialNumber(){
        System.out.println("Please Enter the Serial Number in Dvd");
        String serialNumber= scan.nextLine();
        return serialNumber ;
    }


    /**
     * Displays a list of DVDs depending on the user Choide
     * Displaying include lists of movies, music, games, or all DVDs in the inventory.
     * Calls corresponding methods to print the selected DVD lists.
     */
    public void handleOperationForlistingDvd(){
        displayAction("To See All Movies Press 1,\n" + "All Music Press 2,\n" + "All Games Press 3,\n" + "Everything Press 4");
        int choiceListDvd = getUserTask(1,4);

         switch (choiceListDvd) {
            case 1 :
                this.inventoryManager.printDvds(this.inventoryManager.listOfMovies());
                break;
            case 2 : 
                this.inventoryManager.printDvds(this.inventoryManager.listOfMusic());
                break;
            case 3 :
                this.inventoryManager.printDvds(this.inventoryManager.listOfGames());
                break;
             case 4 :
                this.inventoryManager.printDvds(this.inventoryManager.listofDvds());
                break;
            default :
                System.out.println("Invalid Input");
        }
    }

    /**
     * Prompt the user to enter a string and return it.
     * @param message The message to display actions that the user can do 
     */
    public void  displayAction(String message) {
        System.out.println();
        System.out.println("==========================DVD=========================");
        System.out.println(message);
        System.out.println("======================================================");
    }


    /**
     * Prompt the user to enter a double value and return it.
     * @param message The message to display to the user so that he can enter a value matching the message requirement.
     * @return The double value entered by the user.
     */
    public double getUserInputDouble(String message) {
        System.out.println(message);
        double userInput =0.0 ;
        boolean valid =false;
        while(!valid){
            try{
                userInput = scan.nextDouble();
                scan.nextLine();
                valid=true;
            }
            catch(InputMismatchException e){
                System.out.println("Enter A valid Number");
    
            }
        }
        return userInput;
    }
   
    
    /**
     * Prompt the user to enter a string and return it.
     * @param message The message to display to the user so that he enters a string related to the message.
     * @return The string entered by the user.
     */
    public String getUserInputString(String message) {
        System.out.println(message);
        return scan.nextLine();
    }
    
    /**
     * Prompt the user to enter a Genre and return it.
     * @param message The message to display to the user.
     * @return The Genre value entered by the user.
     */
    public Genre getUserInputGenre(String message) {
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
        return Genre.valueOf(genre.toUpperCase());
    }
    

     
  
    
    /**
     * Prompt the user to enter an integer and return it.
     * @param message The message to display to the user.
     * @return The integer entered by the user.
     */
    public int getUserInputInt(String message) {
        System.out.println(message);
        int userInput =0 ;
        boolean valid = false;
        while(!valid){
            try{
                userInput = scan.nextInt();
                scan.nextLine();
                valid = true;
            }
            catch(InputMismatchException e){
                System.out.println("Enter A valid Number");
                scan.nextLine();
            }
        }
        return userInput;
    }


    /**
     * Displays available options for the user to chose from
     * Such as adding/removing members and DVDs, as well as displaying lists of members and DVDs.
     */
    public void displayAvailableOptions(){

        System.out.println("=====================SuperTrioStore====================");
        System.out.println("Choose between the options available, to Exit Press 0 ");
        System.out.println("             To Add a New Member Press 1              ");
        System.out.println("             To Remove a Member Press 2               ");
        System.out.println("          To See All of The  Members Press 3          ");
        System.out.println("             To Add a New Dvd Press 4                 ");
        System.out.println("              To Remove a Dvd Press 5                 ");
        System.out.println("              To See All Dvds Press 6                 ");
        System.out.println("======================================================");
    }
}