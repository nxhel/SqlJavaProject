/**
 * The StoreMember interface represents a member of a store.
 * It defines methods for setting and getting member details such as
 * first name, last name, and email.
 * 
 * @author Shakela Hossain
 * @since 2023-11-16
 */
package finaljavaproject.Members;

public interface StoreMember { 
    /**
     * Sets the first name of the store member.
     *
     * @param firstname The first name of the store member.
     */
    void setFirstName(String firstname);

    /**
     * Sets the last name of the store member.
     *
     * @param lastname The last name of the store member.
     */
    void setLastName(String lastname);

    /**
     * Gets the first name of the store member.
     *
     * @return The firstname of the store member.
     */
    String getFirstname();

    /**
     * Gets the last name of the store member.
     *
     * @return The lastname of the store member.
     */
    String getLastname();

    /**
     * Sets the email address of the store member.
     *
     * @param email The email address of the store member.
     */
    void setEmail(String email);

    /**
     * Gets the email address of the store member.
     *
     * @return The email address of the store member.
     */
    String getEmail();
}

