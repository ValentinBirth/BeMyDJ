package de.htw.bemydj.djData;

/**
 * interface datastructure for a user
 */
public interface IUser {

    /** getter for username
     * @return String of username
     */
    String getName();

    /** getter for device adress
     * @return device adress
     */
    String getAdress();

    /** setter for username
     * @param name String of username
     */
    void setName(String name);
}
