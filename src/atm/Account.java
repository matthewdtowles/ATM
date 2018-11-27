/**
 * File: GUI.java
 * Author: Matthew Towles
 * Date: 11/11/2018
 * Purpose: Bank account for ATM GUI program
 */
package atm;

import javax.swing.JOptionPane;

/**
 * Bank account for ATM application
 * 
 * @author matthew.towles
 */
public class Account {
    
    // max number of free withdrawals
    private static final int MAX_FREE_WITHDRAWALS = 4;
    
    // fee for each withdrawal after MAX_FREE_WITHDRAWALS exceeded
    private static final double SERVICE_CHARGE = 1.5;
    
    // withdrawal increment amounts
    private static final int WITHDRAWAL_INCREMENTS = 20;
    
    // number of withdrawals made from all accounts
    private static int withdrawalCount = 0;
    
    // balance of the account
    private double balance;
    
    // type of account - e.g.: "checking" or "savings"
    private String type;
    
    
    /**
     * Constructor
     * 
     * @param type
     * @param balance 
     */
    public Account(String type, double balance) {
        this.type = type;
        this.balance = balance;
    }
    
    /**
     * Add amount passed to balance
     * 
     * @param amount
     */
    public void deposit(double amount) {
        // save previous balance for message
        String oldBalance = this.getPrintableBalance();
        
        // message output to user
        String message;
        
        // add amount to account
        if (this.credit(amount)) {
            // success message
            message = "Deposit successful: " + getPrintableAmount(amount) + 
                    " added to " + this.type + "\n\nPrevious balance: " + oldBalance +
                    "\nCurrent balance: " + this.getPrintableBalance();
        } else {
            // failure message
            message = "Deposit of " + getPrintableAmount(amount) +
                    " from " + this.type + 
                    " cannot be processed at this time.";
        }

        // show message in JOptionPane
        JOptionPane.showMessageDialog(null, message);

    } 
    
    /**
     * Subtract amount passed from balance
     * 
     * @param amount
     * @throws InsufficientFunds
     */
    public void withdraw(double amount) throws InsufficientFunds {
        // is amount valid? 
        // i.e.: can it be satisfied with only bills in atm?
        if (Account.isValidWithdrawalAmount(amount)) {
            // is there enough money in this account?
            if (this.hasSufficientFunds(amount)) {
                // save previous balance for message
                String oldBalance = this.getPrintableBalance();
        
                // add service charge if applicable
                double totalAmount = amount + Account.getChargeAmountApplied();
                
                // message output to user
                String message;
                
                // remove money from balance
                if (this.debit(totalAmount)) {
                
                    // track total withdrawals made
                    Account.incrementWithdrawalCount();

                    // success message
                    message = getPrintableAmount(amount) + " successfully" +
                            " withdrawn from " + this.type;
                    
                    // check if fee was applied and add to message
                    if (totalAmount > amount) {
                        // let user know a fee was applied to account
                        message += "\nA fee of " + 
                                getPrintableAmount(SERVICE_CHARGE) + " was applied";
                    }
                    
                    // give user balance info for account
                    message += "\n\nPrevious balance: " + oldBalance +
                            "\nCurrent balance: " + this.getPrintableBalance();
                    
                } else {
                    // failure message
                    message = "Withdrawal of " + getPrintableAmount(amount) +
                            " from " + this.type + 
                            " cannot be processed at this time.";
                }
                // show message of results
                JOptionPane.showMessageDialog(null, message);
            }
        } else {
            String message = "Please enter increments of " + Account.getWithdrawalIncrements();
            JOptionPane.showMessageDialog(null, message, "Invalid Amount", JOptionPane.WARNING_MESSAGE);
        }

    }
    
    /**
     * Add money to current account from other account
     * 
     * @param accountFrom
     * @param amount
     * @throws InsufficientFunds 
     */
    public void transfer(Account accountFrom, double amount) throws InsufficientFunds {
        if (accountFrom.hasSufficientFunds(amount)) {
            // save previous balances for message
            String oldFromBalance = accountFrom.getPrintableBalance();
            String oldToBalance = this.getPrintableBalance();

            String message;
            
            // take amount from accountFrom and add to current account
            if (accountFrom.debit(amount) && this.credit(amount)) {
                // success message
                message = getPrintableAmount(amount) + " transferred from " +
                        accountFrom.getType() + " to " + this.type +
                        "\n\n" + accountFrom.getType() + ":" +
                        "\n\tPrevious balance: " + oldFromBalance +
                        "\n\tCurrent balance: " + accountFrom.getPrintableBalance() +
                        "\n\n" + this.type + ":" +
                        "\n\tPrevious balance: " + oldToBalance +
                        "\n\tCurrent balance: " + this.getPrintableBalance();

            } else {
                // failure message
                message = "Transfer could not be processed at this time." +
                        "\nFailed to transfer " + getPrintableAmount(amount) +
                        " from " + accountFrom.getType() + " to " + this.type;
            }
            // show message of results to user
            JOptionPane.showMessageDialog(null, message);
        }
    }
    
    /**
     * Remove amount from balance
     * Return whether action was performed
     * 
     * @param amount
     * @return debited
     */
    private boolean debit(double amount) {
        boolean debited = false;
        // execute if amount is valid
        if (amount >= 0.01) {
            this.balance -= amount;
            debited = true;
        }
        return debited;
    }
    
    /**
     * Add amount to balance
     * Return whether action was performed
     * 
     * @param amount
     * @return credited
     */
    private boolean credit(double amount) {
        boolean credited = false;
        // execute if amount is valid (real $ amount)
        if (amount >= 0.01) {
            this.balance += amount;
            credited = true;
        }
        return credited;
    }
    
    /**
     * Check if amount is valid withdrawal amount
     * Must be divisible by 20 and more than 0
     * 
     * @param amount
     * @return 
     */
    static boolean isValidWithdrawalAmount(double amount) {
        if (amount > 0) {
            return amount % Account.WITHDRAWAL_INCREMENTS == 0;
        }
        return false;
    }
    
    /**
     * Increment withdrawal count by 1
     * 
     * Each time a withdrawal is successfully made from an account, increment
     */
    private static void incrementWithdrawalCount() {
        Account.withdrawalCount++;
    }
    
    /**
     * Returns true if there are enough funds to cover requested amount
     * Throws InsufficientFunds exception otherwise
     * 
     * @param amount
     * @return
     * @throws InsufficientFunds 
     */
    public boolean hasSufficientFunds(double amount) throws InsufficientFunds {
        // add service charge if applicable
        amount += Account.getChargeAmountApplied();
        
        if (this.balance < amount) {
            // error message
            String message = "Insufficient funds. " + getPrintableAmount(amount)
                    + " Exceeds your " + "balance of " 
                    + this.getPrintableBalance() + ".";
            
            throw new InsufficientFunds(message);
        }
        return true;
    }
    
    /**
     * Returns amount applied for service charge of withdrawals
     * 
     * @return 
     */
    public static double getChargeAmountApplied() {
        // if too many withdrawals - apply service charge
        if (withdrawalCount >= MAX_FREE_WITHDRAWALS) {
            return SERVICE_CHARGE;
        } 
        // no service charge:
        return 0;
    }
    
    /**
     * Return balance as a string in USD format
     * (e.g.: $4.10) rather than (4.1) 
     * 
     * @return 
     */
    public String getPrintableBalance() {
        String bal = "$";
        bal += String.format("%.2f", this.balance);
        return bal;
    }
    
    /**
     * Return amount as a string in USD format
     * Like getPrintableBalance but for any argument passed
     * 
     * @param amount
     * @return 
     */
    public static String getPrintableAmount(double amount) {
        String amt = "$";
        amt += String.format("%.2f", amount);
        return amt;
    }
    
    // getters for class vars/constansts:
    
    public static int getWithdrawalIncrements() {
        return WITHDRAWAL_INCREMENTS;
    }
    
    // getters for instance vars:
    
    public double getBalance() {
        return this.balance;
    }
    
    public String getType() {
        return this.type;
    }

}
