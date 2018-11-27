/**
 * File: GUI.java
 * Author: Matthew Towles
 * Date: 11/11/2018
 * Purpose: Creates a GUI for an ATM
 */
package atm;

import javax.swing.JOptionPane;

/**
 * GUI for ATM Application
 * 
 * @author matthew.towles
 */
public class GUI extends javax.swing.JFrame {
       
    // the actual accounts
    private Account checkingAccount;
    private Account savingsAccount;
    
    // keep track of which account has been selected
    private Account selectedAccount;
    private Account unselectedAccount;
    
    // amount currently entered by user
    private double amountEntered;
    

    /**
     * Creates new form GUI
     */
    public GUI() {
        
        initComponents();
        
        // initialitze checking account and savings account
        this.checkingAccount = new Account("checking", 0.0);
        this.savingsAccount = new Account("savings", 0.0);
        
        // currently selected account by default is checking
        this.selectedAccount = this.checkingAccount;
        this.unselectedAccount = this.savingsAccount;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accountTypes = new javax.swing.ButtonGroup();
        withdrawButton = new javax.swing.JButton();
        depositButton = new javax.swing.JButton();
        transferButton = new javax.swing.JButton();
        balanceButton = new javax.swing.JButton();
        amountInput = new javax.swing.JTextField();
        checkingOption = new javax.swing.JRadioButton();
        savingsOption = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ATM Machine");

        withdrawButton.setText("Withdraw");
        withdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
            }
        });

        depositButton.setText("Deposit");
        depositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositButtonActionPerformed(evt);
            }
        });

        transferButton.setText("Transfer to");
        transferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferButtonActionPerformed(evt);
            }
        });

        balanceButton.setText("Balance");
        balanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceButtonActionPerformed(evt);
            }
        });

        amountInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                amountInputFocusLost(evt);
            }
        });
        amountInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountInputActionPerformed(evt);
            }
        });

        accountTypes.add(checkingOption);
        checkingOption.setSelected(true);
        checkingOption.setText("Checking");
        checkingOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkingOptionActionPerformed(evt);
            }
        });

        accountTypes.add(savingsOption);
        savingsOption.setText("Savings");
        savingsOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savingsOptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkingOption)
                        .addGap(18, 18, 18)
                        .addComponent(savingsOption))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(amountInput)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(transferButton)
                                .addComponent(withdrawButton))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(depositButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(balanceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {balanceButton, depositButton, transferButton, withdrawButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(withdrawButton)
                        .addGap(6, 6, 6)
                        .addComponent(transferButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(depositButton)
                        .addGap(6, 6, 6)
                        .addComponent(balanceButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkingOption)
                    .addComponent(savingsOption))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // event listeners:
    
    /**
     * Deposit amount specified by user into account selected
     * @param evt 
     */
    private void depositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositButtonActionPerformed
        this.updateAmountEntered();
        this.selectedAccount.deposit(this.amountEntered);
    }//GEN-LAST:event_depositButtonActionPerformed

    /**
     * Show balance of selected account in a JOptionPane
     * Fires when Balance button is clicked
     * @param evt 
     */
    private void balanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceButtonActionPerformed
        // show current balance in JOptionPane of selected account
        JOptionPane.showMessageDialog(this, this.selectedAccount.getPrintableBalance());
    }//GEN-LAST:event_balanceButtonActionPerformed

    /**
     * Implemented - leave empty
     * @param evt 
     */
    private void amountInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountInputActionPerformed

    }//GEN-LAST:event_amountInputActionPerformed

    /**
     * When 'checking' radio button selected, update selectedAccount
     * @param evt 
     */
    private void checkingOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkingOptionActionPerformed
        this.selectedAccount = this.checkingAccount;
        this.unselectedAccount = this.savingsAccount;
    }//GEN-LAST:event_checkingOptionActionPerformed

    /**
     * When 'savings' radio button selected, update selectedAccount
     * @param evt 
     */
    private void savingsOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savingsOptionActionPerformed
        this.selectedAccount = this.savingsAccount;
        this.unselectedAccount = this.checkingAccount;
    }//GEN-LAST:event_savingsOptionActionPerformed

    /**
     * Withdraw amount specified by user from selected account
     * @param evt 
     */
    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawButtonActionPerformed
        try {
            this.updateAmountEntered();
            this.selectedAccount.withdraw(this.amountEntered);
        } catch(InsufficientFunds e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                    "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_withdrawButtonActionPerformed

    /**
     * Transfer from unselected to selected account
     * @param evt 
     */
    private void transferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferButtonActionPerformed
        try {
            this.updateAmountEntered();
            this.selectedAccount.transfer(this.unselectedAccount, 
                    this.amountEntered);
            
        } catch(InsufficientFunds e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                    "Transfer Failed - Insufficient Funds", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_transferButtonActionPerformed

    /**
     * Update amountEntered after user finished typing in this field
     * @param evt 
     */
    private void amountInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_amountInputFocusLost
        this.updateAmountEntered();
    }//GEN-LAST:event_amountInputFocusLost

    /**
     * Update amountEntered variable with amount in input field
     * Handle NumberFormatException in case user enters non-numeric value
     */
    private void updateAmountEntered() {
        // do not evaluate if user did not enter anything
        if (this.amountInput.getText().length() > 0) {
            try {
                this.amountEntered = Double.parseDouble(this.amountInput.getText());
                // throw exception if less than 1 cent entered
                if (this.amountEntered < 0.01) {
                    throw new NumberFormatException("Amount too small.");
                }
            } catch(NumberFormatException e) {
                // reset input
                this.amountInput.setText("");
                JOptionPane.showMessageDialog(this, e.getMessage(), 
                        "Invalid Amount Entered", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // if cleared then just set amount to 0
            this.amountEntered = 0.0;
        }
        
    }
            
            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup accountTypes;
    private javax.swing.JTextField amountInput;
    private javax.swing.JButton balanceButton;
    private javax.swing.JRadioButton checkingOption;
    private javax.swing.JButton depositButton;
    private javax.swing.JRadioButton savingsOption;
    private javax.swing.JButton transferButton;
    private javax.swing.JButton withdrawButton;
    // End of variables declaration//GEN-END:variables
}