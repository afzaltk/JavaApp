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
    int givenaccountID;
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
        
        JLabel savingsBlockedClosedLabel = new JLabel("Account Blocked / Closed Status");
        savingsBlockedClosedLabel.setBounds(400, 150, 500, 25);
        
        JLabel creditBalanceLabel = new JLabel("Current Credit Card Balance: ");
        creditBalanceLabel.setBounds(400, 100, 500, 25);
        
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
        accountPanel.add(creditBalanceLabel);
        accountPanel.add(savingsBlockedClosedLabel);
        
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
        savingsBlockedClosedLabel.setVisible(false);
        creditBalanceLabel.setVisible(false);
        
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
                        givenaccountID = controller.getSavingsID(givenuserID);
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
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
                
        savingsManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creditBalanceLabel.setVisible(false);
                
                try {savingsBalanceLabel.setText("Current Savings Balance: " + controller.getSavings(givenuserID));
                ArrayList savStatus = controller.accountStatus(Integer.toString(givenaccountID));
                String a = "Account is blocked";
                String b = "Account is blocked";
                if (savStatus.get(0).toString().equals("1"))
                {
                    a = "Account is not blocked.";
                }
                if (savStatus.get(1).toString().equals("1"))
                {
                    b = " Account is not Closed";
                }
                
                savingsBlockedClosedLabel.setText(a + b);
                } catch (SQLException f) {
                    f.printStackTrace();
                }
                
                savingsBalanceLabel.setVisible(true);
                savingsBlockedClosedLabel.setVisible(true);
                
            }
        });
        
        creditManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savingsBalanceLabel.setVisible(false);
                creditBalanceLabel.setVisible(true);
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
            }
        });
        
        
    }
    
}
