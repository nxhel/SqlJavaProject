package finaljavaproject;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import finaljavaproject.DataProcessing.FileExporter;
import finaljavaproject.DataProcessing.FileImporter;
import finaljavaproject.DataProcessing.IDatabaseImporter;
import finaljavaproject.DataProcessing.LoadFromDatabase;
import finaljavaproject.DataProcessing.LoadFromFile;
import finaljavaproject.Displays.*;
import finaljavaproject.Members.*;
import finaljavaproject.Products.*;

public class SuperTrioStore {
    private static final Scanner scan = new Scanner(System.in);
     public static void main( String[] args ) throws IOException{

        // Creation of needed objects
        // Comment out these lines to skip the database login--------------------------------
        FileImporter IO = new LoadFromFile();
        FileExporter fileExporter = new FileExporter();
        System.out.println("Connecting to the database. Please enter your credentials: ");
        String user = new String(System.console().readLine("Enter your username: "));
        String pwd = new String(System.console().readPassword("Enter your password: "));

        IDatabaseImporter Idb= new LoadFromDatabase(user,pwd);
        // -----------------------------------------------------------------------------------
        //Exemple of how to display a list of Dvds
        // for (Dvd log :Idb.loadMoviesFromDatabse()){
        //     System.out.println(log);
        // }

        InventoryManager inventoryManager = new InventoryManager(IO.loadDvdsFromFile());
        MemberManager memberManager = new MemberManager (IO.loadStoreMembersFromFile());

        //Loads the list of purchases WHY LOGS
        List<String> logs = IO.loadPurchaseLogFromFile();

        //Loads the list of customers
        
        List<StoreMember> customers = IO.loadCustomersFromFile();
        memberManager.appendCustomers(customers); 
        
        //Loaded the username+passewords from file
        List <String> users = IO.loadUsersFromFile();
   
        //The login of the user starts here!
        boolean valid = false;
        //User information
        String username ="";
        String passeword ="";

        while(!valid){
            try{
                System.out.println("Connecting to the SuperTrioStore. Enter your store credentials: ");
                System.out.println("Enter Username:");
                username = scan.next();
                scan.nextLine();
                System.out.println("Enter Passeword:");
                passeword = scan.next();
                scan.nextLine();
                valid = checkIfUsernameMatchPasseword(users, username,passeword);
                if(!valid){
                    throw new IllegalArgumentException();
                }
                System.out.println("You have sucessefully logged in!");
            }
            catch(IllegalArgumentException e){
                System.out.println("Sorry, but the username or passeword that youre trying to input doesn't exist.Try inputting again.");
            }
        }
        //Descision of what interface the user can access
        int userRole = getRoleOfUser(users,username);

        IDisplayer iDisplayer;
        int maxTask;
        if(userRole==0){
            iDisplayer = new AdminDisplay(inventoryManager,memberManager );
            maxTask = 6; 
        }
        else if (userRole==2){
            iDisplayer = new EmployeeDisplay(inventoryManager, customers);
            maxTask = 4;
        }
        else{
            iDisplayer= new ManagerDisplay(inventoryManager,memberManager);
            maxTask = 4;
        }

        //This regualtes the tasks of each member
        boolean exitTheProgram=false;
        while(!exitTheProgram){
            iDisplayer.displayAvailableOptions();
            int userChosenTask =  iDisplayer.getUserTask(0,maxTask);
            //If user exits
            if(userChosenTask==0){
                goodbyeMessage();
                exitTheProgram=true; 
                }
            //If user continues to do tasks
            else{
                iDisplayer.handleUserTasks(userChosenTask);
                }
            }
            fileExporter.writeDvds(inventoryManager.listofDvds());
    }
    /*
     * This method prints the goodbye message when user exits the program.
     */
    public static void goodbyeMessage(){
            System.out.println("===========SuperTrioStore============");
            System.out.println("---It was good seeing you again---");
            System.out.println("---GoodBye, see you next time!!---");
            System.out.println("  ---TheSuperTrio BlockBuster--- ");
            System.out.println("=====================================");
    }
    /*
     * This method checks if the username and the passeword of the user match in our system.
     *  @return true if the username and passeword match/false if they don't match.
     */
    public static boolean checkIfUsernameMatchPasseword(List <String> users, String username, String passeword){
        for(int i=0; i<users.size();i+=2){
            if(users.get(i).equals(username) && users.get(i+1).equals(passeword)){
                return true;
            }
        }
        return false ;
    }
    /*
     * This method will be user after the passeword and username have been checked and correpond to each other.
     * She will help us idetify to which interface whould we send the user(which role he has in our store).
     * @return an int representing the number of the interface we'll send him to.
     */
    public static int getRoleOfUser(List <String> users ,String username){
        int role = 0;
        for(int i= 0; i<users.size();i+=2){
            if(users.get(i).equals(username)){
                role= i;
            }
        }
        return role;
    }
}
