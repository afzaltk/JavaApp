/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.View;

import bsp.Controller.BSPController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AdminView {
    
    String givenuserID;
    int givensavingsID;
    int givencreditID;
    int balance;
    
    
    

    public void welcomeUser(ArrayList userdetails) {
        JOptionPane.showMessageDialog(null, "Welcome " + userdetails.get(2) + "....!!", "Welcome", JOptionPane.PLAIN_MESSAGE);
        mainPage(userdetails);
    }

    public void mainPage(ArrayList userdetails) {
        
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
        
        JLabel homeloanMessage = new JLabel("Home Loan Details:");
        homeloanMessage.setBounds(400, 100, 500, 25);
        JLabel homeloanStartLabel = new JLabel("Start Date");
        homeloanStartLabel.setBounds(400, 150, 500, 25);
        JLabel homeloanEndLabel = new JLabel("End Date");
        homeloanEndLabel.setBounds(400, 200, 500, 25);
        JLabel homeloanInterestLabel = new JLabel("Interest Rate");
        homeloanInterestLabel.setBounds(400, 250, 500, 25);
        JLabel homeloanAmountLabel = new JLabel("Total Amount");
        homeloanAmountLabel.setBounds(400, 300, 500, 25);
        JButton homeloanEditButton = new JButton("Edit Home Loan Account");
        homeloanEditButton.setBounds(650, 100, 200, 25);
        
        JLabel termdepositMessage = new JLabel("Term Deposit: ");
        termdepositMessage.setBounds(400, 100, 500, 25);
        JLabel termdepositStartLabel = new JLabel("Start Date");
        termdepositStartLabel.setBounds(400, 150, 500, 25);
        JLabel termdepositEndLabel = new JLabel("End Date");
        termdepositEndLabel.setBounds(400, 200, 500, 25);
        JLabel termdepositInterestLabel = new JLabel("Interest Rate");
        termdepositInterestLabel.setBounds(400, 250, 500, 25);
        JLabel termdepositAmountLabel = new JLabel("Total Amount");
        termdepositAmountLabel.setBounds(400, 300, 500, 25);
        JLabel termdepositEarlyDateLabel = new JLabel("Early Withdraw Date");
        termdepositEarlyDateLabel.setBounds(400, 350, 500, 25);
        JButton termdepositEditButton = new JButton("Edit Term Deposit Account");
        termdepositEditButton.setBounds(650, 100, 200, 25);
        
        JLabel creditBalanceLabel = new JLabel("Current Credit Card Balance: ");
        creditBalanceLabel.setBounds(400, 100, 500, 25);
        JLabel creditDailyWithdraw = new JLabel("Daily Withdrawal Limit: ");
        creditDailyWithdraw.setBounds(400, 150, 500, 25);
        JLabel creditMaxDebt = new JLabel("Maximum Debt: ");
        creditMaxDebt.setBounds(400, 200, 500, 25);
        JButton creditEditButton = new JButton("Edit Credit Card Account");
        creditEditButton.setBounds(650, 100, 200, 25);
        
        JPanel accountPanel = new JPanel();

        accountPanel.setLayout(null);
        accountPanel.add(adminNameLabel);
        accountPanel.add(userNameLabel);
        accountPanel.add(userIDField);
        accountPanel.add(userConfirm);
        accountPanel.add(logOutButton);
        
        accountPanel.add(savingsManage);
        accountPanel.add(creditManage);
        accountPanel.add(homeloanManage);
        accountPanel.add(termdepositManage);
        
        accountPanel.add(userDetailsLabel);
        accountPanel.add(userChange);
        
        accountPanel.add(savingsBalanceLabel);
        accountPanel.add(savingsBlockedLabel);
        accountPanel.add(savingsClosedLabel);
        accountPanel.add(savingsEditButton);
        
        accountPanel.add(creditBalanceLabel);
        accountPanel.add(creditDailyWithdraw);
        accountPanel.add(creditMaxDebt);
        accountPanel.add(creditEditButton);
        
        accountPanel.add(homeloanMessage);
        accountPanel.add(homeloanStartLabel);
        accountPanel.add(homeloanEndLabel);
        accountPanel.add(homeloanInterestLabel);
        accountPanel.add(homeloanAmountLabel);
        accountPanel.add(homeloanEditButton);
        
        accountPanel.add(termdepositMessage);
        accountPanel.add(termdepositStartLabel);
        accountPanel.add(termdepositEndLabel);
        accountPanel.add(termdepositInterestLabel);
        accountPanel.add(termdepositAmountLabel);
        accountPanel.add(termdepositEarlyDateLabel);
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
        
        savingsBalanceLabel.setVisible(false);
        savingsClosedLabel.setVisible(false);
        savingsBlockedLabel.setVisible(false);
        savingsEditButton.setVisible(false);
        
        creditBalanceLabel.setVisible(false);
        creditDailyWithdraw.setVisible(false);
        creditMaxDebt.setVisible(false);
        creditEditButton.setVisible(false);
        
        homeloanMessage.setVisible(false);
        homeloanStartLabel.setVisible(false);
        homeloanEndLabel.setVisible(false);
        homeloanInterestLabel.setVisible(false);
        homeloanAmountLabel.setVisible(false);
        homeloanEditButton.setVisible(false);
        
        termdepositMessage.setVisible(false);
        termdepositStartLabel.setVisible(false);
        termdepositEndLabel.setVisible(false);
        termdepositInterestLabel.setVisible(false);
        termdepositAmountLabel.setVisible(false);
        termdepositEarlyDateLabel.setVisible(false);
        termdepositEditButton.setVisible(false);
        
        frame.setVisible(true);
        
 
        BSPController controller = new BSPController();
        
        userConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String givenID = userIDField.getText();
                    givenuserID = userIDField.getText();
                    ArrayList auResult = controller.getUser(givenID);
                    if (Objects.equals(auResult.get(0), "success"))
                    {
                        JOptionPane.showMessageDialog(null, "Now managing " + auResult.get(2).toString() + " " + auResult.get(3).toString() , "Welcome",  JOptionPane.PLAIN_MESSAGE);
                        userNameLabel.setVisible(false);
                        userIDField.setVisible(false);
                        userConfirm.setVisible(false);
                        userChange.setVisible(true);
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
                    }
                    if (acResult.contains("3"))
                    {
                        homeloanManage.setVisible(true);
                    }
                    if (acResult.contains("4"))
                    {
                        creditManage.setVisible(true);
                        givencreditID = controller.getCreditID(givenuserID);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
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
                        
                homeloanMessage.setVisible(false);
                homeloanStartLabel.setVisible(false);
                homeloanEndLabel.setVisible(false);
                homeloanInterestLabel.setVisible(false);
                homeloanAmountLabel.setVisible(false);
                homeloanEditButton.setVisible(false);
                        
                termdepositMessage.setVisible(false);
                termdepositStartLabel.setVisible(false);
                termdepositEndLabel.setVisible(false);
                termdepositInterestLabel.setVisible(false);
                termdepositAmountLabel.setVisible(false);
                termdepositEarlyDateLabel.setVisible(false);
                termdepositEditButton.setVisible(false);
                
                try {savingsBalanceLabel.setText("Current Savings Balance: " + controller.getSavings(givenuserID));
                ArrayList savStatus = controller.accountStatus(Integer.toString(givensavingsID));
                String a = "Account is blocked";
                String b = "Account is blocked";
                
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
                        String savingsEditChoice = (String)JOptionPane.showInputDialog(frame, "What would you like to edit?", "Savings Edit Options", JOptionPane.PLAIN_MESSAGE, null, savingsEditOptions, "Account Balance");
                        if (savingsEditChoice.equals("Account Balance"))
                        {
                            String newAccountBalanceString = (String)JOptionPane.showInputDialog(frame, savingsBalanceLabel.getText() + " . Please enter new balance: ", "Savings Balance Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountBalanceInt = Integer.parseInt(newAccountBalanceString);
                            JOptionPane.showMessageDialog(frame, "New Balance: " + newAccountBalanceInt);
                        }
                        
                        if (savingsEditChoice.equals("Blocked Status"))
                        {
                            Object[] savingsBlockOptions = {"Block Account", "Unblock Account"};
                            String savingsBlockStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Block Savings Account?", JOptionPane.PLAIN_MESSAGE, null, savingsBlockOptions, "Balance");
                            JOptionPane.showMessageDialog(frame, "New Status: " + savingsBlockStatus);
                        }
                        
                        if (savingsEditChoice.equals("Closed Status"))
                        {
                            Object[] savingsClosedOptions = {"Close Account", "Don't Close Account"};
                            String savingsClosedStatus = (String)JOptionPane.showInputDialog(frame, "Select your choice: ", "Close Savings Account?", JOptionPane.PLAIN_MESSAGE, null, savingsClosedOptions, "Balance");
                            JOptionPane.showMessageDialog(frame, "New Status: " + savingsClosedStatus);
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
                
                homeloanMessage.setVisible(false);
                homeloanStartLabel.setVisible(false);
                homeloanEndLabel.setVisible(false);
                homeloanInterestLabel.setVisible(false);
                homeloanAmountLabel.setVisible(false);
                homeloanEditButton.setVisible(false);
                
                termdepositMessage.setVisible(false);
                termdepositStartLabel.setVisible(false);
                termdepositEndLabel.setVisible(false);
                termdepositInterestLabel.setVisible(false);
                termdepositAmountLabel.setVisible(false);
                termdepositEarlyDateLabel.setVisible(false);
                termdepositEditButton.setVisible(false);
                
                ArrayList ar = new ArrayList();
                try {creditBalanceLabel.setText("Current Credit Balance: " + controller.getCredit(givenuserID));
                ar = controller.getCreditDetails(givenuserID);
                creditDailyWithdraw.setText("Daily Withdraw Limit: " + ar.get(0));
                creditMaxDebt.setText("Maximum Debt Allowed: " + ar.get(0));
                
                } catch (SQLException f) {
                    f.printStackTrace();
                }
                
                //Savings Edit Button function
                creditEditButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Object[] creditEditOptions = {"Account Balance", "Daily Withdraw Limit", "Maximum Debt"};
                        String savingsEditChoice = (String)JOptionPane.showInputDialog(frame, "What would you like to edit?", "Credit Edit Options", JOptionPane.PLAIN_MESSAGE, null, creditEditOptions, "Balance");
                        if (savingsEditChoice.equals("Account Balance"))
                        {
                            String newAccountBalanceString = (String)JOptionPane.showInputDialog(frame, creditBalanceLabel.getText() + " . Please enter new balance: ", "Credit Balance Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountBalanceInt = Integer.parseInt(newAccountBalanceString);
                            JOptionPane.showMessageDialog(frame, "New Balance: " + newAccountBalanceInt);
                        }
                        
                        if (savingsEditChoice.equals("Daily Withdraw Limit"))
                        {
                            String newAccountWithdrawString = (String)JOptionPane.showInputDialog(frame, creditDailyWithdraw.getText() + " . Please enter new Withdraw Limit: ", "Credit Withdraw Limit Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountWithdrawInt = Integer.parseInt(newAccountWithdrawString);
                            JOptionPane.showMessageDialog(frame, "New Account Limit: " + newAccountWithdrawInt);
                        }
                        
                        if (savingsEditChoice.equals("Maximum Debt"))
                        {
                            String newAccountDebtString = (String)JOptionPane.showInputDialog(frame, creditMaxDebt.getText() + " . Please enter new Debt Limit: ", "Debt Limit Edit", JOptionPane.PLAIN_MESSAGE, null, null, "0");
                            int newAccountDebtInt = Integer.parseInt(newAccountDebtString);
                            JOptionPane.showMessageDialog(frame, "New Account Limit: " + newAccountDebtInt);
                        }
                    }
                });
                
                
                //Visible
                creditBalanceLabel.setVisible(true);
                creditDailyWithdraw.setVisible(true);
                creditMaxDebt.setVisible(true);
                creditEditButton.setVisible(true);
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
                
                termdepositMessage.setVisible(false);
                termdepositStartLabel.setVisible(false);
                termdepositEndLabel.setVisible(false);
                termdepositInterestLabel.setVisible(false);
                termdepositAmountLabel.setVisible(false);
                termdepositEarlyDateLabel.setVisible(false);
                termdepositEditButton.setVisible(false);
                
                ArrayList ar = new ArrayList();
                try {ar = controller.getHomeLoan(givenuserID);
                } catch (SQLException f) {
                    f.printStackTrace();
                }
                
                homeloanStartLabel.setText("Start Date: " + ar.get(0).toString());
                homeloanEndLabel.setText("End Date: " + ar.get(1).toString());
                homeloanInterestLabel.setText("Interest Rate: " + ar.get(2).toString());
                homeloanAmountLabel.setText("Total Amount: " + ar.get(3).toString());
                
                homeloanMessage.setVisible(true);
                homeloanStartLabel.setVisible(true);
                homeloanEndLabel.setVisible(true);
                homeloanInterestLabel.setVisible(true);
                homeloanAmountLabel.setVisible(true);
                homeloanEditButton.setVisible(true);
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
                
                homeloanMessage.setVisible(false);
                homeloanStartLabel.setVisible(false);
                homeloanEndLabel.setVisible(false);
                homeloanInterestLabel.setVisible(false);
                homeloanAmountLabel.setVisible(false);
                homeloanEditButton.setVisible(false);
                
                //Connecting to DB
                 ArrayList ar = new ArrayList();
                try {ar = controller.getTermDepositDetails(givenuserID);
                } catch (SQLException f) {
                    f.printStackTrace();
                }
                
                termdepositStartLabel.setText("Start Date: " + ar.get(0).toString());
                termdepositEndLabel.setText("End Date: " + ar.get(1).toString());
                termdepositInterestLabel.setText("Interest Rate: " + ar.get(2).toString());
                termdepositAmountLabel.setText("Total Amount: " + ar.get(3).toString());
                termdepositEarlyDateLabel.setText("Early Withdrawal Date: " + ar.get(4).toString());
                
                //Showing results
                termdepositMessage.setVisible(true);
                termdepositStartLabel.setVisible(true);
                termdepositEndLabel.setVisible(true);
                termdepositInterestLabel.setVisible(true);
                termdepositAmountLabel.setVisible(true);
                termdepositEarlyDateLabel.setVisible(true);
                termdepositEditButton.setVisible(true);
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
                
                savingsBalanceLabel.setVisible(false);
                savingsBlockedLabel.setVisible(false);
                savingsClosedLabel.setVisible(false);
                savingsEditButton.setVisible(false);
                
                creditBalanceLabel.setVisible(false);
                creditDailyWithdraw.setVisible(false);
                creditMaxDebt.setVisible(false);
                creditEditButton.setVisible(false);
                
                homeloanMessage.setVisible(false);
                homeloanStartLabel.setVisible(false);
                homeloanEndLabel.setVisible(false);
                homeloanInterestLabel.setVisible(false);
                homeloanAmountLabel.setVisible(false);
                homeloanEditButton.setVisible(false);
                
                termdepositMessage.setVisible(false);
                termdepositStartLabel.setVisible(false);
                termdepositEndLabel.setVisible(false);
                termdepositInterestLabel.setVisible(false);
                termdepositAmountLabel.setVisible(false);
                termdepositEarlyDateLabel.setVisible(false);
                
            }
        });
        
        
    }
    
}