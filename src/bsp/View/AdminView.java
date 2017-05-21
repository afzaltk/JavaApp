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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
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

public class AdminView {

    private String givenuserID;
    private int givensavingsID;
    private int givencreditID;
    private int balance;
    private HashMap UsersData = new HashMap();
    private Vector columnNamesVector = new Vector();
    private Vector dataVector = new Vector();
	private int givenhomeloanID;
    private int giventermdepositID;
    

    public void welcomeUser(ArrayList userdetails) {
        JOptionPane.showMessageDialog(null, "Welcome " + userdetails.get(2) + "....!!", "Welcome", JOptionPane.PLAIN_MESSAGE);
        mainPage(userdetails);
    }

    public void mainPage(ArrayList userdetails) {

        JButton CreateUserButton = new JButton("Create New User");
        CreateUserButton.setBounds(250, 30, 200, 25);
        JButton ViewAllUsersButton = new JButton("View All Users");
        ViewAllUsersButton.setBounds(550, 30, 200, 25);
        JLabel adminNameLabel = new JLabel("Current Admin: " + userdetails.get(2));
        adminNameLabel.setBounds(50, 20, 400, 25);
        JLabel userNameLabel = new JLabel("Please enter the UserID of the account you want to manage:");
        userNameLabel.setBounds(370, 100, 500, 25);
        JTextField userIDField = new JTextField();
        userIDField.setBounds(400, 150, 200, 30);
        JButton userConfirm = new JButton("Search for User");
        userConfirm.setBounds(400, 200, 200, 30);
        JButton logOutButton = new JButton("Log out");
        logOutButton.setBounds(950, 20, 80, 25);

        JLabel userDetailsLabel = new JLabel("User Name Here");
        userDetailsLabel.setBounds(50, 100, 500, 25);
        JButton savingsManage = new JButton("Manage Savings Account");
        savingsManage.setBounds(50, 150, 200, 30);
        JButton termdepositManage = new JButton("Manage Term Deposit Account");
        termdepositManage.setBounds(50, 200, 200, 30);
        JButton homeloanManage = new JButton("Manage Home Loan Account");
        homeloanManage.setBounds(50, 250, 200, 30);
        JButton creditManage = new JButton("Manage Credit Account");
        creditManage.setBounds(50, 300, 200, 30);
        JButton userManage = new JButton("Manage User Details");
        userManage.setBounds(50, 350, 200, 30);
        JButton userChange = new JButton("Change User");
        userChange.setBounds(400, 450, 200, 30);

        JLabel savingsBalanceLabel = new JLabel("Current Savings Balance");
        savingsBalanceLabel.setBounds(400, 100, 500, 25);
        JLabel savingsBlockedLabel = new JLabel("Account Blocked Status");
        savingsBlockedLabel.setBounds(400, 150, 500, 25);
        JLabel savingsClosedLabel = new JLabel("Account Closed Status");
        savingsClosedLabel.setBounds(400, 200, 500, 25);
        JButton savingsEditButton = new JButton("Edit Savings Account");
        savingsEditButton.setBounds(650, 100, 200, 25);
        
        JLabel homeloanStartLabel = new JLabel("Start Date");
        homeloanStartLabel.setBounds(400, 100, 500, 25);
        JLabel homeloanEndLabel = new JLabel("End Date");
        homeloanEndLabel.setBounds(400, 150, 500, 25);
        JLabel homeloanAmountLabel = new JLabel("Total Amount");
        homeloanAmountLabel.setBounds(400, 200, 500, 25);
        JLabel homeloanCancelDateLabel = new JLabel("Next Payment Date");
        homeloanCancelDateLabel.setBounds(400, 250, 500, 25);
        JLabel homeloanBlockCloseLabel = new JLabel("Blocked: Closed:");
        homeloanBlockCloseLabel.setBounds(400, 300, 500, 25);
        JButton homeloanEditButton = new JButton("Edit Home Loan Account");
        homeloanEditButton.setBounds(650, 100, 200, 25);
        
        JLabel termdepositStartLabel = new JLabel("Term Deposit Start Date");
        termdepositStartLabel.setBounds(400, 100, 500, 25);
        JLabel termdepositEndLabel = new JLabel("End Date");
        termdepositEndLabel.setBounds(400, 150, 500, 25);
        JLabel termdepositInterestLabel = new JLabel("Interest Rate");
        termdepositInterestLabel.setBounds(400, 200, 500, 25);
        JLabel termdepositAmountLabel = new JLabel("Total Amount");
        termdepositAmountLabel.setBounds(400, 250, 500, 25);
        JLabel termdepositBalanceLabel = new JLabel("Remaining Balance");
        termdepositBalanceLabel.setBounds(400, 300, 500, 25);
        JLabel termdepositEarlyDateLabel = new JLabel("Early Withdraw Date");
        termdepositEarlyDateLabel.setBounds(400, 350, 500, 25);
        JLabel termdepositBlockCloseLabel = new JLabel("Blocked: Closed: ");
        termdepositBlockCloseLabel.setBounds(400, 400, 500, 25);
        JButton termdepositEditButton = new JButton("Edit Term Deposit Account");
        termdepositEditButton.setBounds(650, 100, 200, 25);

        JLabel creditBalanceLabel = new JLabel("Current Credit Card Balance: ");
        creditBalanceLabel.setBounds(400, 100, 500, 25);
        JLabel creditDailyWithdraw = new JLabel("Daily Withdrawal Limit: ");
        creditDailyWithdraw.setBounds(400, 150, 500, 25);
        JLabel creditMaxDebt = new JLabel("Maximum Debt: ");
        creditMaxDebt.setBounds(400, 200, 500, 25);
        JLabel creditBlockedClosed = new JLabel("Blocked: Closed: ");
        creditBlockedClosed.setBounds(400, 250, 500, 25);
        JButton creditEditButton = new JButton("Edit Credit Card Account");
        creditEditButton.setBounds(650, 100, 200, 25);

        JPanel accountPanel = new JPanel();

        accountPanel.setLayout(null);
        accountPanel.add(adminNameLabel);
        accountPanel.add(userNameLabel);
        accountPanel.add(userIDField);
        accountPanel.add(userConfirm);
        accountPanel.add(logOutButton);
        accountPanel.add(CreateUserButton);
        accountPanel.add(ViewAllUsersButton);

        accountPanel.add(savingsManage);
        accountPanel.add(creditManage);
        accountPanel.add(homeloanManage);
        accountPanel.add(termdepositManage);

        accountPanel.add(userDetailsLabel);
        accountPanel.add(userChange);
        accountPanel.add(userManage);
        
        accountPanel.add(savingsBalanceLabel);
        accountPanel.add(savingsBlockedLabel);
        accountPanel.add(savingsClosedLabel);
        accountPanel.add(savingsEditButton);

        accountPanel.add(creditBalanceLabel);
        accountPanel.add(creditDailyWithdraw);
        accountPanel.add(creditMaxDebt);
        accountPanel.add(creditEditButton);
        accountPanel.add(creditBlockedClosed);
        
        accountPanel.add(homeloanStartLabel);
        accountPanel.add(homeloanEndLabel);
        accountPanel.add(homeloanAmountLabel);
        accountPanel.add(homeloanEditButton);
        accountPanel.add(homeloanCancelDateLabel);
        accountPanel.add(homeloanBlockCloseLabel);
        
        accountPanel.add(termdepositStartLabel);
        accountPanel.add(termdepositEndLabel);
        accountPanel.add(termdepositInterestLabel);
        accountPanel.add(termdepositAmountLabel);
        accountPanel.add(termdepositBalanceLabel);
        accountPanel.add(termdepositEarlyDateLabel);
        accountPanel.add(termdepositBlockCloseLabel);
        accountPanel.add(termdepositEditButton);

        JFrame frame = new JFrame("Banking Software Prototype");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1066, 577);
        frame.add(accountPanel);
        frame.setLocationRelativeTo(null);

        savingsManage.setVisible(false);
        creditManage.setVisible(false);
        homeloanManage.setVisible(false);
        termdepositManage.setVisible(false);

        userDetailsLabel.setVisible(false);
        userChange.setVisible(false);
        userManage.setVisible(false);
        
        savingsBalanceLabel.setVisible(false);
        savingsClosedLabel.setVisible(false);
        savingsBlockedLabel.setVisible(false);
        savingsEditButton.setVisible(false);

        creditBalanceLabel.setVisible(false);
        creditDailyWithdraw.setVisible(false);
        creditMaxDebt.setVisible(false);
        creditEditButton.setVisible(false);
        creditBlockedClosed.setVisible(false);
        
        homeloanStartLabel.setVisible(false);
        homeloanEndLabel.setVisible(false);
        homeloanAmountLabel.setVisible(false);
        homeloanEditButton.setVisible(false);
        homeloanCancelDateLabel.setVisible(false);
        homeloanBlockCloseLabel.setVisible(false);
        
        termdepositStartLabel.setVisible(false);
        termdepositEndLabel.setVisible(false);
        termdepositInterestLabel.setVisible(false);
        termdepositAmountLabel.setVisible(false);
        termdepositBalanceLabel.setVisible(false);
        termdepositEarlyDateLabel.setVisible(false);
        termdepositEditButton.setVisible(false);
        termdepositBlockCloseLabel.setVisible(false);
        frame.setVisible(true);

        BSPController controller = new BSPController();
        
        userManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ArrayList ar = controller.getUserDetails(givenuserID);
                    Object[] userEditOptions = {"Password", "Pin", "First Name", "Last Name", "Email", "Address", "Phone Number"};
                    String x = System.getProperty("line.separator");
                    String userEditChoice = (String)JOptionPane.showInputDialog(frame,
                            "User Name: " + ar.get(2).toString() + " " + ar.get(3).toString()
                            + x + "Password: " + ar.get(0).toString()
                            + x + "Pin: " + ar.get(1).toString()
                            + x + "Email: " + ar.get(4).toString()
                            + x + "Address: " + ar.get(5).toString()
                            + x + "Phone Number: " + ar.get(6).toString()
                            + x + x + "What would you like to edit?", "User Edit Options", JOptionPane.PLAIN_MESSAGE, null, userEditOptions, "Password");
                    if (userEditChoice.equals("Password"))
                    {
                        String newPassword = (String)JOptionPane.showInputDialog(frame, "Current user Password is '" + ar.get(0) + "'. Please enter new Password: ", "User Password Edit", JOptionPane.PLAIN_MESSAGE, null, null, "password");
                        Boolean isSuccessful = controller.setUserDetails(givenuserID, newPassword, 1);
                        if (isSuccessful)
                        {
                            JOptionPane.showMessageDialog(frame, "Success!");
                        } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                    }
                    if (userEditChoice.equals("Pin"))
                    {
                        String newPin = (String)JOptionPane.showInputDialog(frame, "Current user Pin is '" + ar.get(1) + "'. Please enter new 4 digit pin: ", "User Pin Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0000");
                        Boolean isSuccessful = controller.setUserDetails(givenuserID, newPin, 2);
                        if (isSuccessful)
                        {
                            JOptionPane.showMessageDialog(frame, "Success!");
                        } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                    }
                    if (userEditChoice.equals("First Name"))
                    {
                        String newFname = (String)JOptionPane.showInputDialog(frame, "Current user First Name is '" + ar.get(2) + "'. Please enter new First Name: ", "User First Name Edit", JOptionPane.PLAIN_MESSAGE, null, null, "First Name");
                        Boolean isSuccessful = controller.setUserDetails(givenuserID, newFname, 3);
                        if (isSuccessful)
                        {
                            JOptionPane.showMessageDialog(frame, "Success!");
                        } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                    }
                    if (userEditChoice.equals("Last Name"))
                    {
                        String newLname = (String)JOptionPane.showInputDialog(frame, "Current user Last Name is '" + ar.get(3) + "'. Please enter new Last Name: ", "User Last Name Edit", JOptionPane.PLAIN_MESSAGE, null, null, "Last Name");
                        Boolean isSuccessful = controller.setUserDetails(givenuserID, newLname, 4);
                        if (isSuccessful)
                        {
                            JOptionPane.showMessageDialog(frame, "Success!");
                        } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                    }
                    if (userEditChoice.equals("Email"))
                    {
                        String newEmail = (String)JOptionPane.showInputDialog(frame, "Current user Email is '" + ar.get(4) + "'. Please enter new Email: ", "User Email Edit", JOptionPane.PLAIN_MESSAGE, null, null, "Email");
                        Boolean isSuccessful = controller.setUserDetails(givenuserID, newEmail, 5);
                        if (isSuccessful)
                        {
                            JOptionPane.showMessageDialog(frame, "Success!");
                        } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                    }
                    if (userEditChoice.equals("Address"))
                    {
                        String newAddress = (String)JOptionPane.showInputDialog(frame, "Current user Address is '" + ar.get(5) + "'. Please enter new Address: ", "User Address Edit", JOptionPane.PLAIN_MESSAGE, null, null, "1 Example St, Caulfield");
                        Boolean isSuccessful = controller.setUserDetails(givenuserID, newAddress, 6);
                        if (isSuccessful)
                        {
                            JOptionPane.showMessageDialog(frame, "Success!");
                        } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                    }
                    if (userEditChoice.equals("Phone Number"))
                    {
                        String newPhone = (String)JOptionPane.showInputDialog(frame, "Current user Phone Number is '" + ar.get(6) + "'. Please enter new Phone Number: ", "User Phone Number Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0412345678");
                        Boolean isSuccessful = controller.setUserDetails(givenuserID, newPhone, 7);
                        if (isSuccessful)
                        {
                            JOptionPane.showMessageDialog(frame, "Success!");
                        } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                    }
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }        
        });
        
        
        userConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String givenID = userIDField.getText();
                    givenuserID = userIDField.getText();
                    int number = Integer.parseInt(givenuserID);
                    ArrayList auResult = controller.getUser(givenID);
                    if (Objects.equals(auResult.get(0), "success")) 
					{
                        JOptionPane.showMessageDialog(null, "Now managing " + auResult.get(2).toString() + " " + auResult.get(3).toString(), "Welcome", JOptionPane.PLAIN_MESSAGE);
                        userNameLabel.setVisible(false);
                        userIDField.setVisible(false);
                        userConfirm.setVisible(false);
                        userChange.setVisible(true);
                        userManage.setVisible(true);
                    }
                    ArrayList acResult = controller.getAccounts(givenID);

                    userDetailsLabel.setText("Name: " + auResult.get(2) + " " + auResult.get(3));
                    userDetailsLabel.setVisible(true);

                    if (acResult.contains("1")) 
					{
                        savingsManage.setVisible(true);
                        givensavingsID = controller.getSavingsID(givenuserID);
                    }
                    if (acResult.contains("2")) 
					{
                        termdepositManage.setVisible(true);
                        giventermdepositID = controller.getTermDepositID(givenuserID);
                    }
                    if (acResult.contains("3"))
					 {
                        homeloanManage.setVisible(true);
                        givenhomeloanID = controller.getHomeLoanID(givenuserID);
                    }
                    if (acResult.contains("4"))
					 {
                        creditManage.setVisible(true);
                        givencreditID = controller.getCreditID(givenuserID);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException en) {
                    JOptionPane.showMessageDialog(frame, "Number not found! Please enter a UserId.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        savingsManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // HIDING SECTION
                creditBalanceLabel.setVisible(false);
                creditDailyWithdraw.setVisible(false);
                creditMaxDebt.setVisible(false);
                creditEditButton.setVisible(false);
                creditBlockedClosed.setVisible(false);
                
                homeloanStartLabel.setVisible(false);
                homeloanEndLabel.setVisible(false);
                homeloanAmountLabel.setVisible(false);
                homeloanEditButton.setVisible(false);
                homeloanCancelDateLabel.setVisible(false);
                homeloanBlockCloseLabel.setVisible(false);
                
                termdepositStartLabel.setVisible(false);
                termdepositEndLabel.setVisible(false);
                termdepositInterestLabel.setVisible(false);
                termdepositAmountLabel.setVisible(false);
                termdepositBalanceLabel.setVisible(false);
                termdepositEarlyDateLabel.setVisible(false);
                termdepositEditButton.setVisible(false);
                termdepositBlockCloseLabel.setVisible(false);
                
                try {savingsBalanceLabel.setText("Current Savings Balance: " + controller.getSavings(givenuserID));
                ArrayList savStatus = controller.accountStatus(givensavingsID);
                String a = "Account is blocked";
                String b = "Account is closed";
                
                if (savStatus.get(0).toString().equals("1"))
                {
                    a = "Account is not blocked.";
                }
                
                if (savStatus.get(1).toString().equals("1"))
                {
                    b = " Account is not closed";
                }
                
                savingsBlockedLabel.setText(a);
                savingsClosedLabel.setText(b);
                
                } catch (SQLException f) {
                    f.printStackTrace();
                }

                //Savings Edit Button function
                savingsEditButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Object[] savingsEditOptions = {"Account Balance", "Blocked Status", "Closed Status"};
                        String savingsEditChoice = (String) JOptionPane.showInputDialog(frame, "What would you like to edit?", "Savings Edit Options", JOptionPane.PLAIN_MESSAGE, null, savingsEditOptions, "Account Balance");
                        if (savingsEditChoice.equals("Account Balance")) 
						{
                            String newAccountBalanceString = (String) JOptionPane.showInputDialog(frame, savingsBalanceLabel.getText() + " . Please enter new balance: ", "Savings Balance Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountBalanceInt = Integer.parseInt(newAccountBalanceString);
                            Boolean isSuccessful = controller.setBalance(givensavingsID, newAccountBalanceInt);
                            if (isSuccessful)
                            {
                                JOptionPane.showMessageDialog(frame, "New Account Balance: " + newAccountBalanceInt);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                        }
                        
                        if (savingsEditChoice.equals("Blocked Status"))
                        {
                            Object[] savingsBlockOptions = {"Block Account", "Unblock Account"};
                            String savingsBlockStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Block Savings Account?", JOptionPane.PLAIN_MESSAGE, null, savingsBlockOptions, "Balance");
                            if (savingsBlockStatus.equals("Block Account"))
                            {
                                controller.blockSavings(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Blocked");
                            }
                            if (savingsBlockStatus.equals("Unblock Account"))
                            {
                                controller.unblockSavings(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Unblocked");
                            }
                        }
                        
                        if (savingsEditChoice.equals("Closed Status"))
                        {
                            Object[] savingsClosedOptions = {"Close Account", "Don't Close Account"};
                            String savingsClosedStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Close Savings Account?", JOptionPane.PLAIN_MESSAGE, null, savingsClosedOptions, "Balance");
                            if (savingsClosedStatus.equals("Close Account"))
                            {
                                controller.closeSavings(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Closed");
                            }
                            if (savingsClosedStatus.equals("Don't Close Account"))
                            {
                                controller.uncloseSavings(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Unclosed");
                            }
                        }
                    }
                });

                // VISIBLE SECTION
                savingsBalanceLabel.setVisible(true);
                savingsBlockedLabel.setVisible(true);
                savingsClosedLabel.setVisible(true);
                savingsEditButton.setVisible(true);
            }
        });

        creditManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savingsBalanceLabel.setVisible(false);
                savingsBlockedLabel.setVisible(false);
                savingsClosedLabel.setVisible(false);
                savingsEditButton.setVisible(false);
                
                homeloanStartLabel.setVisible(false);
                homeloanEndLabel.setVisible(false);
                homeloanAmountLabel.setVisible(false);
                homeloanEditButton.setVisible(false);
                homeloanCancelDateLabel.setVisible(false);
                homeloanBlockCloseLabel.setVisible(false);
                
                termdepositStartLabel.setVisible(false);
                termdepositEndLabel.setVisible(false);
                termdepositInterestLabel.setVisible(false);
                termdepositAmountLabel.setVisible(false);
                termdepositBalanceLabel.setVisible(false);
                termdepositEarlyDateLabel.setVisible(false);
                termdepositEditButton.setVisible(false);
                termdepositBlockCloseLabel.setVisible(false);
                
                ArrayList ar = new ArrayList();
                ArrayList ar2 = new ArrayList();
                try {
				creditBalanceLabel.setText("Current Credit Balance: " + controller.getCredit(givenuserID));
                ar = controller.getCreditDetails(givenuserID);
                ar2 = controller.accountStatus(givencreditID);
                creditDailyWithdraw.setText("Daily Withdraw Limit: " + ar.get(0));
                creditMaxDebt.setText("Maximum Debt Allowed: " + ar.get(1));
                String creditBlockedClosedMessage = "";
                if (ar2.get(0).toString().equals("1"))
                {
                    creditBlockedClosedMessage += "Account is not Blocked. ";
                } else
                {
                    creditBlockedClosedMessage += "Account is Blocked. ";
                }
                if (ar2.get(1).toString().equals("1"))
                {
                    creditBlockedClosedMessage += "Account is not Closed.";
                } else
                {
                    creditBlockedClosedMessage += "Account is Closed.";
                }
                creditBlockedClosed.setText(creditBlockedClosedMessage);
                } catch (SQLException f) {
                    f.printStackTrace();
                }

                //Savings Edit Button function
                creditEditButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Object[] creditEditOptions = {"Account Balance", "Daily Withdraw Limit", "Maximum Debt", "Blocked Status", "Closed Status"};
                        String creditEditChoice = (String)JOptionPane.showInputDialog(frame, "What would you like to edit?", "Credit Edit Options", JOptionPane.PLAIN_MESSAGE, null, creditEditOptions, "Balance");
                        if (creditEditChoice.equals("Account Balance"))
                        {
                            String newAccountBalanceString = (String)JOptionPane.showInputDialog(frame, creditBalanceLabel.getText() + " . Please enter new balance: ", "Credit Balance Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountBalanceInt = Integer.parseInt(newAccountBalanceString);
                            Boolean isSuccessful = controller.setBalance(givencreditID, newAccountBalanceInt);
                            if (isSuccessful)
                            {
                                JOptionPane.showMessageDialog(frame, "New Account Balance: " + newAccountBalanceInt);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                        }
                        
                        if (creditEditChoice.equals("Daily Withdraw Limit"))
                        {
                            String newAccountWithdrawString = (String)JOptionPane.showInputDialog(frame, creditDailyWithdraw.getText() + " . Please enter new Withdraw Limit: ", "Credit Withdraw Limit Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountWithdrawInt = Integer.parseInt(newAccountWithdrawString);
                            
                            Boolean isSuccessful = controller.modifyCreditDailyLimit(givenuserID, newAccountWithdrawInt);
                            if (isSuccessful)
                            {
                                JOptionPane.showMessageDialog(frame, "New Daily Withdraw Limit: " + newAccountWithdrawInt);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                        }
                        
                        if (creditEditChoice.equals("Maximum Debt"))
                        {
                            String newAccountDebtString = (String)JOptionPane.showInputDialog(frame, creditMaxDebt.getText() + " . Please enter new Debt Limit: ", "Credit Card Debt Limit Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountDebtInt = Integer.parseInt(newAccountDebtString);
                            Boolean isSuccessful = controller.modifyCreditDebtLimit(givenuserID, newAccountDebtInt);
                            if (isSuccessful)
                            {
                                JOptionPane.showMessageDialog(frame, "New Maximum Debt Limit: " + newAccountDebtInt);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                        }
                        
                        if (creditEditChoice.equals("Blocked Status"))
                        {
                            Object[] savingsBlockOptions = {"Block Account", "Unblock Account"};
                            String savingsBlockStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Block Savings Account?", JOptionPane.PLAIN_MESSAGE, null, savingsBlockOptions, "Block Account");
                            if (savingsBlockStatus.equals("Block Account"))
                            {
                                controller.blockCredit(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Blocked");
                            }
                            if (savingsBlockStatus.equals("Unblock Account"))
                            {
                                controller.unblockCredit(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Unblocked");
                            }
                        }
                        
                        if (creditEditChoice.equals("Closed Status"))
                        {
                            Object[] savingsClosedOptions = {"Close Account", "Don't Close Account"};
                            String savingsClosedStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Close Savings Account?", JOptionPane.PLAIN_MESSAGE, null, savingsClosedOptions, "Close Account");
                            if (savingsClosedStatus.equals("Close Account"))
                            {
                                controller.closeCredit(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Closed");
                            }
                            if (savingsClosedStatus.equals("Don't Close Account"))
                            {
                                controller.uncloseCredit(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Unclosed");
                            }
                        }
                    }
                });

                //Visible
                creditBalanceLabel.setVisible(true);
                creditDailyWithdraw.setVisible(true);
                creditMaxDebt.setVisible(true);
                creditEditButton.setVisible(true);
                creditBlockedClosed.setVisible(true);
            }
        });

        homeloanManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savingsBalanceLabel.setVisible(false);
                savingsBlockedLabel.setVisible(false);
                savingsClosedLabel.setVisible(false);
                savingsEditButton.setVisible(false);

                creditBalanceLabel.setVisible(false);
                creditDailyWithdraw.setVisible(false);
                creditMaxDebt.setVisible(false);
                creditEditButton.setVisible(false);
                creditBlockedClosed.setVisible(false);
                
                termdepositStartLabel.setVisible(false);
                termdepositEndLabel.setVisible(false);
                termdepositInterestLabel.setVisible(false);
                termdepositAmountLabel.setVisible(false);
                termdepositBalanceLabel.setVisible(false);
                termdepositEarlyDateLabel.setVisible(false);
                termdepositEditButton.setVisible(false);
                termdepositBlockCloseLabel.setVisible(false);
                
                ArrayList ar = new ArrayList();
                ArrayList ar2 = new ArrayList();
                try {ar = controller.getHomeLoan(givenuserID);
                ar2 = controller.accountStatus(givenhomeloanID);
                } catch (SQLException f) {
                    f.printStackTrace();
                }

                homeloanStartLabel.setText("Start Date: " + ar.get(0).toString());
                homeloanEndLabel.setText("End Date: " + ar.get(1).toString());
                homeloanAmountLabel.setText("Total Amount: " + ar.get(2).toString());
                homeloanCancelDateLabel.setText("Next Payment Date: " + ar.get(3).toString());
                
                String homeloanBlockedClosedMessage = "";
                if (ar2.get(0).toString().equals("1"))
                {
                    homeloanBlockedClosedMessage += "Account is not Blocked. ";
                } else
                {
                    homeloanBlockedClosedMessage += "Account is Blocked. ";
                }
                if (ar2.get(1).toString().equals("1"))
                {
                    homeloanBlockedClosedMessage += "Account is not Closed.";
                } else
                {
                    homeloanBlockedClosedMessage += "Account is Closed.";
                }
                homeloanBlockCloseLabel.setText(homeloanBlockedClosedMessage);
                
                
                
                homeloanEditButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                        Object[] homeloanEditOptions = {"Start Date", "End Date", "Next Payment Date", "Loan Amount", "Blocked Status", "Closed Status"};
                        String homeloanEditChoice = (String)JOptionPane.showInputDialog(frame, "What would you like to edit?", "Home Loan Edit Options", JOptionPane.PLAIN_MESSAGE, null, homeloanEditOptions, "Start Date");
                        
                        if (homeloanEditChoice.equals("Start Date"))
                        {
                            String newhomeloanStartString = (String)JOptionPane.showInputDialog(frame, homeloanStartLabel.getText() + " . Please enter new date: ", "Home Loan Start Date Edit", JOptionPane.PLAIN_MESSAGE, null, null, "2017-05-21");
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            try {
                                Date homeloanstartresult = df.parse(newhomeloanStartString);
                                Boolean isSuccessful = controller.setHomeLoanStartDate(givenuserID, newhomeloanStartString);
                                if (isSuccessful)
                                {
                                    JOptionPane.showMessageDialog(frame, "New Start Date: " + newhomeloanStartString);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Error");
                                }
                                } catch (ParseException ex) {
                                    Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(frame, "Error: Unsupported Date Format");
                                }
                            
                        }
                        
                        if (homeloanEditChoice.equals("End Date"))
                        {
                            String newhomeloanEndString = (String)JOptionPane.showInputDialog(frame, homeloanEndLabel.getText() + " . Please enter new date: ", "Home Loan End Date Edit", JOptionPane.PLAIN_MESSAGE, null, null, "2017-05-21");
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            try {
                                Date homeloanendresult = df.parse(newhomeloanEndString);
                                Boolean isSuccessful = controller.setHomeLoanEndDate(givenuserID, newhomeloanEndString);
                                if (isSuccessful)
                                {
                                    JOptionPane.showMessageDialog(frame, "New End Date: " + newhomeloanEndString);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Error");
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(frame, "Error: Unsupported Date Format");
                            }
                        }
                        
                        if (homeloanEditChoice.equals("Next Payment Date"))
                        {
                            String newhomeloanPayString = (String)JOptionPane.showInputDialog(frame, homeloanCancelDateLabel.getText() + " . Please enter new date: ", "Home Loan Payment Date Edit", JOptionPane.PLAIN_MESSAGE, null, null, "2017-05-21");
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            try {
                                Date homeloanpayresult = df.parse(newhomeloanPayString);
                                Boolean isSuccessful = controller.setHomeLoanNextDate(givenuserID, newhomeloanPayString);
                                if (isSuccessful)
                                {
                                    JOptionPane.showMessageDialog(frame, "New Next Payment Date: " + newhomeloanPayString);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Error");
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(frame, "Error: Unsupported Date Format");
                            }
                        }
                        
                        if (homeloanEditChoice.equals("Loan Amount"))
                        {
                            String newHomeLoanAmountString = (String)JOptionPane.showInputDialog(frame, homeloanAmountLabel.getText() + " . Please enter new total Home Loan amount: ", "Home Loan Amount Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newHomeAmountInt = Integer.parseInt(newHomeLoanAmountString);
                            Boolean isSuccessful = controller.modifyHomeLoanAmount(givenuserID, newHomeAmountInt);
                            if (isSuccessful)
                            {
                                JOptionPane.showMessageDialog(frame, "New Home Loan Amount: " + newHomeAmountInt);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                        }
                        
                        if (homeloanEditChoice.equals("Blocked Status"))
                        {
                            Object[] savingsBlockOptions = {"Block Account", "Unblock Account"};
                            String savingsBlockStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Block Home Loan Account?", JOptionPane.PLAIN_MESSAGE, null, savingsBlockOptions, "Block Account");
                            if (savingsBlockStatus.equals("Block Account"))
                            {
                                controller.blockHomeLoan(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Blocked");
                            }
                            if (savingsBlockStatus.equals("Unblock Account"))
                            {
                                controller.unblockHomeLoan(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Unblocked");
                            }
                        }
                        
                        if (homeloanEditChoice.equals("Closed Status"))
                        {
                            Object[] savingsClosedOptions = {"Close Account", "Don't Close Account"};
                            String savingsClosedStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Close Home Loan Account?", JOptionPane.PLAIN_MESSAGE, null, savingsClosedOptions, "Close Account");
                            if (savingsClosedStatus.equals("Close Account"))
                            {
                                controller.closeHomeLoan(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Closed");
                            }
                            if (savingsClosedStatus.equals("Don't Close Account"))
                            {
                                controller.uncloseHomeLoan(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Unclosed");
                            }
                        }
                    }
                });
                
                homeloanStartLabel.setVisible(true);
                homeloanEndLabel.setVisible(true);
                homeloanAmountLabel.setVisible(true);
                homeloanEditButton.setVisible(true);
                homeloanCancelDateLabel.setVisible(true);
                homeloanBlockCloseLabel.setVisible(true);
            }
        });

        termdepositManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Hiding previous sections
                savingsBalanceLabel.setVisible(false);
                savingsBlockedLabel.setVisible(false);
                savingsClosedLabel.setVisible(false);
                savingsEditButton.setVisible(false);

                creditBalanceLabel.setVisible(false);
                creditDailyWithdraw.setVisible(false);
                creditMaxDebt.setVisible(false);
                creditEditButton.setVisible(false);
                creditBlockedClosed.setVisible(false);
                
                homeloanStartLabel.setVisible(false);
                homeloanEndLabel.setVisible(false);
                homeloanAmountLabel.setVisible(false);
                homeloanEditButton.setVisible(false);
                homeloanCancelDateLabel.setVisible(false);
                homeloanBlockCloseLabel.setVisible(false);
                
                //Connecting to DB
                 ArrayList ar = new ArrayList();
                 ArrayList ar2 = new ArrayList();
                try {ar = controller.getTermDepositDetails(givenuserID);
                ar2 = controller.accountStatus(giventermdepositID);
                } catch (SQLException f) {
                    f.printStackTrace();
                }

                termdepositStartLabel.setText("Start Date: " + ar.get(0).toString());
                termdepositEndLabel.setText("End Date: " + ar.get(1).toString());
                termdepositInterestLabel.setText("Interest Rate: " + ar.get(2).toString());
                termdepositAmountLabel.setText("Total Amount: " + ar.get(3).toString());
                termdepositBalanceLabel.setText("Balance Remaining: " + controller.getBalanceFloat(giventermdepositID));
                termdepositEarlyDateLabel.setText("Early Withdrawal Date: " + ar.get(4).toString());
                String termdepositBlockedClosedMessage = "";
                if (ar2.get(0).toString().equals("1"))
                {
                    termdepositBlockedClosedMessage += "Account is not Blocked. ";
                } else
                {
                    termdepositBlockedClosedMessage += "Account is Blocked. ";
                }
                if (ar2.get(1).toString().equals("1"))
                {
                    termdepositBlockedClosedMessage += "Account is not Closed.";
                } else
                {
                    termdepositBlockedClosedMessage += "Account is Closed.";
                }
                
                termdepositBlockCloseLabel.setText(termdepositBlockedClosedMessage);
                termdepositEditButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                        Object[] termdepositEditOptions = {"Start Date", "End Date", "Interest Rate", "Total Amount", "Remaining Balance", "Early Withdrawal Date", "Blocked Status", "Closed Status"};
                        String termdepositEditChoice = (String)JOptionPane.showInputDialog(frame, "What would you like to edit?", "Term Deposit Edit Options", JOptionPane.PLAIN_MESSAGE, null, termdepositEditOptions, "Start Date");
                        
                        if (termdepositEditChoice.equals("Start Date"))
                        {
                            String newtermdepositStartString = (String)JOptionPane.showInputDialog(frame, termdepositStartLabel.getText() + " . Please enter new date: ", "Home Loan Start Date Edit", JOptionPane.PLAIN_MESSAGE, null, null, "2017-05-21");
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            try {
                                Date termdepositstartresult = df.parse(newtermdepositStartString);
                                Boolean isSuccessful = controller.setTermDepositStartDate(givenuserID, newtermdepositStartString);
                                if (isSuccessful)
                                {
                                    JOptionPane.showMessageDialog(frame, "New Start Date: " + newtermdepositStartString);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Error");
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(frame, "Error: Unsupported Date Format");
                            }
                        }
                        
                        if (termdepositEditChoice.equals("End Date"))
                        {
                            String newtermdepositEndString = (String)JOptionPane.showInputDialog(frame, termdepositEndLabel.getText() + " . Please enter new date: ", "Term Deposit End Date Edit", JOptionPane.PLAIN_MESSAGE, null, null, "2017-05-21");
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            try {
                                Date termdepositendresult = df.parse(newtermdepositEndString);
                                Boolean isSuccessful = controller.setTermDepositEndDate(givenuserID, newtermdepositEndString);
                                if (isSuccessful)
                                {
                                    JOptionPane.showMessageDialog(frame, "New Start Date: " + newtermdepositEndString);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Error");
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(frame, "Error: Unsupported Date Format");
                            }
                        }
                        
                        if (termdepositEditChoice.equals("Interest Rate"))
                        {
                            String newtermdepositInterestString = (String)JOptionPane.showInputDialog(frame, termdepositInterestLabel.getText() + " . Please enter new Interest Rate: ", "Term Deposit Interest Rate Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            float newInterest = Float.parseFloat(newtermdepositInterestString);
                            Boolean isSuccessful = controller.modifyTermDepositInterestRate(givenuserID, newInterest);
                            if (isSuccessful)
                            {
                                JOptionPane.showMessageDialog(frame, "New Term Deposit Interest Rate: " + newInterest);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                        }
                        
                        if (termdepositEditChoice.equals("Total Amount"))
                        {
                            String newtermdepositAmountString = (String)JOptionPane.showInputDialog(frame,termdepositAmountLabel.getText() + " . Please enter new Term Deposit Total: ", "Term Deposit Amount Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            float newTotal = Float.parseFloat(newtermdepositAmountString);
                            Boolean isSuccessful = controller.modifyTermDepositTotal(givenuserID, newTotal);
                            if (isSuccessful)
                            {
                                JOptionPane.showMessageDialog(frame, "New Term Deposit Total Amount: " + newTotal);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                        }
                        
                        if (termdepositEditChoice.equals("Remaining Balance"))
                        {
                            String termdepositBalanceString = (String)JOptionPane.showInputDialog(frame, termdepositBalanceLabel.getText() + " . Please enter new Balance: ", "Term Deposit Balance Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newTermDepositInt = Integer.parseInt(termdepositBalanceString);
                            Boolean isSuccessful = controller.setBalance(giventermdepositID, newTermDepositInt);
                            if (isSuccessful)
                            {
                                JOptionPane.showMessageDialog(frame, "New Account Balance: " + newTermDepositInt);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error");
                            }
                        }
                        
                        if (termdepositEditChoice.equals("Early Withdrawal Date"))
                        {
                            String newtermdepositWithdrawString = (String)JOptionPane.showInputDialog(frame, termdepositEarlyDateLabel.getText() + " . Please enter new date: ", "Term Deposit Early Withdraw Date Edit", JOptionPane.PLAIN_MESSAGE, null, null, "2017-05-21");
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            try {
                                Date termdepositendresult = df.parse(newtermdepositWithdrawString);
                                Boolean isSuccessful = controller.setTermDepositWithdrawDate(givenuserID, newtermdepositWithdrawString);
                                if (isSuccessful)
                                {
                                    JOptionPane.showMessageDialog(frame, "New Start Date: " + newtermdepositWithdrawString);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Error");
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(frame, "Error: Unsupported Date Format");
                            }
                        }
                        if (termdepositEditChoice.equals("Blocked Status"))
                        {
                            Object[] savingsBlockOptions = {"Block Account", "Unblock Account"};
                            String savingsBlockStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Block Term Deposit Account?", JOptionPane.PLAIN_MESSAGE, null, savingsBlockOptions, "Balance");
                            if (savingsBlockStatus.equals("Block Account"))
                            {
                                controller.blockTermDeposit(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Blocked");
                            }
                            if (savingsBlockStatus.equals("Unblock Account"))
                            {
                                controller.unblockTermDeposit(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Unblocked");
                            }
                        }
                        
                        if (termdepositEditChoice.equals("Closed Status"))
                        {
                            Object[] savingsClosedOptions = {"Close Account", "Don't Close Account"};
                            String savingsClosedStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Close Term Deposit Account?", JOptionPane.PLAIN_MESSAGE, null, savingsClosedOptions, "Balance");
                            if (savingsClosedStatus.equals("Close Account"))
                            {
                                controller.closeTermDeposit(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Closed");
                            }
                            if (savingsClosedStatus.equals("Don't Close Account"))
                            {
                                controller.uncloseTermDeposit(givenuserID);
                                JOptionPane.showMessageDialog(frame, "Account Unclosed");
                            }
                        }
                        
                    }
                });
                
                //Showing results
                termdepositStartLabel.setVisible(true);
                termdepositEndLabel.setVisible(true);
                termdepositInterestLabel.setVisible(true);
                termdepositAmountLabel.setVisible(true);
                termdepositBalanceLabel.setVisible(true);
                termdepositEarlyDateLabel.setVisible(true);
                termdepositEditButton.setVisible(true);
                termdepositBlockCloseLabel.setVisible(true);
                
            }
        });

        logOutButton.addActionListener(new ActionListener() {
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
        
         CreateUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    frame.dispose();        
                    CreateUserView cuv=new CreateUserView(userdetails);
            }
        });
         
         ViewAllUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    BSPController c = new BSPController();
                    UsersData = c.viewUserListController();
                    dataVector = (Vector) UsersData.get(3);
                    columnNamesVector = (Vector) UsersData.get(4);
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

                    JFrame TransactionsFrame = new JFrame("All Users");
                    TransactionsFrame.setSize(866, 477);
                    TransactionsFrame.add(scrollPane);
                    TransactionsFrame.add(buttonPanel, BorderLayout.SOUTH);
                    TransactionsFrame.setLocationRelativeTo(null);
                    TransactionsFrame.setVisible(true);
            }
        });

        userChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userNameLabel.setVisible(true);
                userIDField.setVisible(true);
                userConfirm.setVisible(true);

                savingsManage.setVisible(false);
                creditManage.setVisible(false);
                homeloanManage.setVisible(false);
                termdepositManage.setVisible(false);

                userDetailsLabel.setVisible(false);
                userChange.setVisible(false);
                userManage.setVisible(false);
                
                savingsBalanceLabel.setVisible(false);
                savingsBlockedLabel.setVisible(false);
                savingsClosedLabel.setVisible(false);
                savingsEditButton.setVisible(false);

                creditBalanceLabel.setVisible(false);
                creditDailyWithdraw.setVisible(false);
                creditMaxDebt.setVisible(false);
                creditEditButton.setVisible(false);
                creditBlockedClosed.setVisible(false);
                
                homeloanStartLabel.setVisible(false);
                homeloanEndLabel.setVisible(false);
                homeloanAmountLabel.setVisible(false);
                homeloanEditButton.setVisible(false);
                homeloanCancelDateLabel.setVisible(false);
                homeloanBlockCloseLabel.setVisible(false);
                
                termdepositStartLabel.setVisible(false);
                termdepositEndLabel.setVisible(false);
                termdepositInterestLabel.setVisible(false);
                termdepositAmountLabel.setVisible(false);
                termdepositBalanceLabel.setVisible(false);
                termdepositEarlyDateLabel.setVisible(false);
                termdepositEditButton.setVisible(false);
                termdepositBlockCloseLabel.setVisible(false);
            }
        });

    }

}
