/**
 * The Manager class represents a manager in a store.
 * It implements the StoreMember interface.
 * 
 * @author Shakela Hossain, Nihel Madani-Fouatih & Iana Feniuc
 * @lastUpdate 2023-11-30
 */
package finaljavaproject.Members;

public class Manager implements StoreMember{
    private String firstname;
    private String lastname;
    private String email;
    private String departement;

    /**
     * Constructor for a new Manager with the specified details.
     *
     * @param firstname The first name of the manager.
     * @param lastname  The last name of the manager.
     * @param email     The email address of the manager.
     */
    public Manager(String firstname, String lastname, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        
    };
    
    // Getters and setters
    public void setDepartement(String department){
        this.departement=department;
    }

    public void setFirstName(String firstname){
        this.firstname = firstname;
    };

    public void setLastName(String lastname){
        this.lastname = lastname;
    };

    public String getDepartement() {
        return departement;
    }

    public String getFirstname(){
        return this.firstname;
    };

    public String getLastname(){
        return this.lastname;
    };

    public void setEmail(String email){
        this.email = email;
    };

    public String getEmail(){
        return this.email;
    };

    /**
     * Returns a string representation of the Manager object.
     *
     * @return Idem description.
     */
    @Override
    public String toString(){
        return this.firstname + "; " + this.lastname + "; " + this.email+";";
    }
    
}
