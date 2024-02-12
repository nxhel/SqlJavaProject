/**
 * The Employee class represents an employee in a store.
 * It implements the StoreMember interface.
 * 
 * @author Shakela Hossain & Iana Feniuc
 * @lastUpdate 2023-11-30
 */
package finaljavaproject.Members;

public class Employee implements StoreMember{
    private String firstname;
    private String lastname;
    private String email;
    private String jobRole;

    /**
     * Constructor of a new Employee with the specified details.
     *
     * @param firstname The first name of the employee.
     * @param lastname  The last name of the employee.
     * @param email     The email address of the employee.
     */
    public Employee(String firstname, String lastname, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    // Getters and setters
    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public void setFirstName(String firstname){
        this.firstname = firstname;
    };

    public void setLastName(String lastname){
        this.lastname = lastname;
    };

    public String getFirstname(){
        return this.firstname;
    };

    public String getLastname(){
        return this.lastname;
    };
    public String getJobRole() {
        return jobRole;
    }

    public void setEmail(String email){
        this.email = email;
    };

    public String getEmail(){
        return this.email;
    };
    
    /**
     * Returns a string representation of the Employee object.
     *
     * @return Idem description.
     */
    @Override
    public String toString(){
        return this.firstname +"; "+ this.lastname +"; "+ this.email+";";
    }

}
