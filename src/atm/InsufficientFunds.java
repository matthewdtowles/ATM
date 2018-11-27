/**
 * File: GUI.java
 * Author: Matthew Towles
 * Date: 11/11/2018
 * Purpose: Exception if withdrawal amount exceeds balance for ATM GUI
 */
package atm;

/**
 * Exception if funds requests exceeds amount held in account
 * 
 * @author matthew.towles
 */
public class InsufficientFunds extends Exception {
    // message displayed when thrown
    private String message;
    
    // constructor with arg
    public InsufficientFunds(String message) {
        this.message = message;
    }
    
    // no arg constructor
    public InsufficientFunds() {
        this("Insufficient funds.");
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
    
    /**
     * Return object in string form
     * @return
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
