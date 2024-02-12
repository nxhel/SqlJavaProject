/**
 * The MemberManager class manages a list of store members, providing
 * operations to add, remove, and retrieve members based on various criteria.
 * 
 * @author Shakela Hossain, Nihel Madani-Fouatih & Iana Feniuc
 * @since 2023-11-16
 * @lastUpdate 2023-11-30
 */
package finaljavaproject.Members;

import java.util.ArrayList;
import java.util.List;

import finaljavaproject.Members.Customer.Customer;
import finaljavaproject.Members.Customer.LoyalCustomer;
import finaljavaproject.Members.Customer.RegularCustomer;
public class MemberManager{
    private List <StoreMember> Member;

    /**
     * Constructor of a new MemberManager with the specified list of members.
     *
     * @param members The initial list of store members.
     */
    public MemberManager(List <StoreMember> members  ){
        this.Member =members;
    }

    /**
     * Appends a list of customers to the existing members list.
     *
     * @param customers The list of customers to append.
     */
    public void appendCustomers(List<StoreMember> customers) {
        this.Member.addAll(customers);
    }
    
    /**
     * Adds a store member to the members list.
     *
     * @param member The store member to add.
     */
    public void addMember(StoreMember member){
        this.Member.add(member);
    }

    /**
    * Prints each store member in the provided list.
    *
    * @param members A list containing StoreMember objects to be printed.
    */
    public void printMembers (List<StoreMember> members){
        for (StoreMember member : members) {
            System.out.println(member.toString());
        }
    }
    
    /**
     * Removes a store member from the members list.
     *
     * @param member The store member to remove.
     */
    public void removeMember(StoreMember member){
        int indexToRemove = this.Member.indexOf(member);
        this.Member.remove(indexToRemove);

    }

    /**
     * Gets the store member at the specified index.
     *
     * @param index The index of the store member to retrieve.
     * @return      The store member at the specified index.
     */
    public StoreMember getMember(int index){
        return this.Member.get(index);
    }

    /**
     * Gets a list containing all store members.
     *
     * @return A list containing all store members.
     */
    public List <StoreMember> listOfAllMembers(){
        return this.Member;
    }

    // Idem of previous
    public List <StoreMember> listOfAllCustomers(){
        List<StoreMember> customerList =  new ArrayList<StoreMember>();
        for(StoreMember member : this.Member){
            if(member instanceof Customer){
                customerList.add(member);
            }
        }
        return customerList;
    }

    // Idem of previous
    public List<StoreMember> listOfEmployees(){
        List<StoreMember> employeeList =  new ArrayList<StoreMember>();
        for(StoreMember employee : this.Member){
            if(employee instanceof Employee){
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    // Idem of previous
    public List<StoreMember> listOfManagers(){
        List<StoreMember> managerList =  new ArrayList<StoreMember>();
        for(StoreMember manager : this.Member){
            if(manager instanceof Manager){
                managerList.add(manager);
            }
        }
        return managerList;
    }

    // Idem of previous
    public List<StoreMember> listOfLoyalCustomers(){
        List<StoreMember> loyalCustomerList =  new ArrayList<StoreMember>();
        for(StoreMember customer : this.Member){
            if(customer instanceof LoyalCustomer){
                loyalCustomerList.add(customer);
            }
        }
        return loyalCustomerList;
    }

    // Idem of previous
    public List<StoreMember> listOfRegularcustomers(){
        List<StoreMember> regularCustomerList =  new ArrayList<StoreMember>();
        for(StoreMember customer : this.Member){
            if(customer instanceof RegularCustomer){
                regularCustomerList.add(customer);
            }
        }
        return regularCustomerList;
    }

    /**
     * Finds a store member by their first and last name.
     *
     * @param firstname The first name of the store member.
     * @param lastname  The last name of the store member.
     * @return          The store member found, or null if not found.
     */
    public StoreMember findMember(String firstname,String lastname){
        for(StoreMember member : this.Member){
            if(member.getFirstname().equalsIgnoreCase(firstname)&& member.getLastname().equalsIgnoreCase(lastname)){
                return member;
            }
        }
        return null;
    }
    
    /**
     * Finds a store member by their email address.
     *
     * @param email The email address of the store member.
     * @return      The store member found, or null if not found.
     */
    public StoreMember findMemberByEmail(String email){
        for(StoreMember member : this.Member){
            if(member.getEmail().equalsIgnoreCase(email)){
                return member;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the MemberManager object.
     *
     * @return Idem description.
     */
    public String toString(){
        String result = "";
        for(StoreMember member : this.Member){
            result += member + "\n";
        }
        return result;
    }
}
