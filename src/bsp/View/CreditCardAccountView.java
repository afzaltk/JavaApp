/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.View;

import bsp.Controller.BSPController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Afzal
 */
public class CreditCardAccountView {

    private float Balance;
    private boolean valid;
    private int Attempts = 3;
    private HashMap TransactionData = new HashMap();
    private Vector columnNamesVector = new Vector();
    private Vector dataVector = new Vector();
    private int AccountType=4;
    private String user_id;
    private String amt;
    private String CreditBal;
    private ArrayList ar = new ArrayList();

    public void viewpage(ArrayList userdetails) throws SQLException {
       
        BSPController c = new BSPController();
        user_id=(String) userdetails.get(0);
        ar = c.getCreditDetails(user_id);
        CreditBal = Integer.toString((int) ar.get(0));
        
        JLabel UserNameLabel = new JLabel((String) userdetails.get(2));
        UserNameLabel.setBounds(370, 100, 100, 25);

        JLabel AccountIdLabel = new JLabel("Credit card Account Id - " + (String) userdetails.get(3));
        AccountIdLabel.setBounds(470, 100, 300, 25);

        JButton CheckBalanceButton = new JButton("Check Balance");
        CheckBalanceButton.setBounds(400, 150, 200, 30);

        JButton TransferAmountButton = new JButton("Transfer Amount");
        TransferAmountButton.setBounds(400, 210, 200, 30);

        JButton DepositAmountButton = new JButton("Deposit Amount");
        DepositAmountButton.setBounds(400, 270, 200, 30);

        JButton WithdrawAmountButton = new JButton("Withdraw Amount");
        WithdrawAmountButton.setBounds(400, 330, 200, 30);

        JButton ViewTransactionsButton = new JButton("View all Transactions");
        ViewTransactionsButton.setBounds(400, 390, 200, 30);
        
        JLabel DailyLimit = new JLabel("Daily Limit - ");
        DailyLimit.setBounds(700, 150, 100, 25);

        JLabel DailyLimitVal = new JLabel(CreditBal);
        DailyLimitVal.setBounds(800, 150, 300, 25);
        
        JButton ModifyDailyLimit = new JButton("Modify Daily Limit");
        ModifyDailyLimit.setBounds(730, 180, 150, 25);
        
        JButton LogOutButton = new JButton("Log out");
        LogOutButton.setBounds(950, 20, 80, 25);

        JButton HomeButton = new JButton("Home");
        HomeButton.setBounds(50, 20, 80, 25);

        JPanel SavingsPanel = new JPanel();
        SavingsPanel.setLayout(null);
        SavingsPanel.add(UserNameLabel);
        SavingsPanel.add(AccountIdLabel);
        SavingsPanel.add(LogOutButton);
        SavingsPanel.add(TransferAmountButton);
        SavingsPanel.add(WithdrawAmountButton);
        SavingsPanel.add(DepositAmountButton);
        SavingsPanel.add(CheckBalanceButton);
        SavingsPanel.add(ViewTransactionsButton);
        SavingsPanel.add(HomeButton);
        SavingsPanel.add(DailyLimit);
        SavingsPanel.add(DailyLimitVal);
        SavingsPanel.add(ModifyDailyLimit);
        

        JFrame frame = new JFrame("Banking Software Prototype");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1066, 577);
        frame.add(SavingsPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {

            HomeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    UserView v = new UserView();
                    userdetails.remove(3);
                    v.mainPage(userdetails);
                }
            });

            LogOutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String message = " Confirm Log Out ? ";
                    String title = "Log Out";
                    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        frame.dispose();
                        new LogInView();
                    }

                }
            });
            
            
            
            ModifyDailyLimit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String newAccountWithdrawString = (String)JOptionPane.showInputDialog(frame, "Please enter new Withdraw Limit: ", "Credit Withdraw Limit Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountWithdrawInt = Integer.parseInt(newAccountWithdrawString);
                            JOptionPane.showMessageDialog(frame, "New Account Limit: " + newAccountWithdrawInt);
                            BSPController c=new BSPController();
                           if(
                                   c.modifyDailyLimit(userdetails,newAccountWithdrawInt, AccountType))
                           {
                               CreditCardAccountView cc = new CreditCardAccountView();
                        try {
                            cc.viewpage(userdetails);
                            frame.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(CreditCardAccountView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           }
                }
            });

            CheckBalanceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    Balance = c.checkBalance(userdetails);
                    JOptionPane.showMessageDialog(SavingsPanel, "Your current Credit card balance is " + Balance, "Balance",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            });

            TransferAmountButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    BSPController c = new BSPController();
                    Balance = c.checkBalance(userdetails);
                    JOptionPane.showMessageDialog(SavingsPanel, "Your current Savings balance is " + Balance, "Balance",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            });

            WithdrawAmountButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    BSPController c = new BSPController();
                    UserView uv = new UserView();
                    valid = uv.enterPin(userdetails, Attempts);
                    if (valid == true) {
                        JTextField tf = new JTextField();
                        String Amount = JOptionPane.showInputDialog(
                                tf, null,
                                "Enter the Amount to be withdrawn.",
                                JOptionPane.OK_CANCEL_OPTION
                        );
                        int number = Integer.parseInt(Amount);
                        if (c.withdrawAmount(userdetails, Amount, AccountType) == true) {
                            JOptionPane.showMessageDialog(SavingsPanel, "Your Credit card Account has been credited with " + Amount + " successfully..!!", "Deposit",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(SavingsPanel, "Unable to withdraw amount. Low Balance", "Balance",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        frame.dispose();
                    }
                }
            });

            DepositAmountButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    BSPController c = new BSPController();
                    UserView uv = new UserView();
                    valid = uv.enterPin(userdetails, Attempts);
                    if (valid == true) {
                        JTextField tf = new JTextField();
                        String Amount = JOptionPane.showInputDialog(
                                tf, null,
                                "Enter the Amount to be deposited.",
                                JOptionPane.OK_CANCEL_OPTION
                        );
                        int number = Integer.parseInt(Amount);
                        if (c.depositAmount(userdetails, Amount, AccountType) == true) {
                            JOptionPane.showMessageDialog(SavingsPanel, "Your Credit card Account has been debited with " + Amount + " successfully..!!", "Deposit",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(SavingsPanel, "Unable to deposit amount. Contact adminisrator", "Balance",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        frame.dispose();
                    }
                }
            });

            ViewTransactionsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {                
                    BSPController c = new BSPController();
                    TransactionData = c.viewTransactionsController(userdetails, AccountType);
                    dataVector = (Vector) TransactionData.get(3);
                    columnNamesVector = (Vector) TransactionData.get(4);
                    JTable table = new JTable(dataVector, columnNamesVector) {
                        public Class getColumnClass(int column) {
                            for (int row = 0; row < getRowCount(); row++) {
                                Object o = getValueAt(row, column);

                                if (o != null) {
                                    return o.getClass();
                                }
                            }

                            return Object.class;
                        }
                    };

                    JScrollPane scrollPane = new JScrollPane(table);

                    JPanel buttonPanel = new JPanel();

                    JFrame TransactionsFrame = new JFrame("Savings Account Transactions");
                    TransactionsFrame.setSize(866, 477);
                    TransactionsFrame.add(scrollPane);
                    TransactionsFrame.add(buttonPanel, BorderLayout.SOUTH);
                    TransactionsFrame.setLocationRelativeTo(null);
                    TransactionsFrame.setVisible(true);

                }

            });

        } catch (NumberFormatException en) {
            JOptionPane.showMessageDialog(frame, "Number not found! Please enter an Amount.", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

}
