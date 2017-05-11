/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.View;

import bsp.Controller.BSPController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Afzal
 */
public class SavingsAccountView {
    
    private float Balance;

    public void viewpage(ArrayList userdetails) {

        JLabel UserNameLabel = new JLabel((String) userdetails.get(2));
        UserNameLabel.setBounds(370, 100, 100, 25);
        
        JLabel AccountIdLabel = new JLabel("Savings Account Id - "+(String) userdetails.get(3));
        AccountIdLabel.setBounds(470, 100, 300, 25);
        
        JButton CheckBalanceButton = new JButton("Check Balance");
        CheckBalanceButton.setBounds(400, 150, 200, 30);

        JButton TransferAmountButton = new JButton("Transfer Amount");
        TransferAmountButton.setBounds(400, 210, 200, 30);

        JButton DepositAmountButton = new JButton("Deposit Button");
        DepositAmountButton.setBounds(400, 270, 200, 30);

        JButton WithdrawAmountButton = new JButton("Withdraw Amount");
        WithdrawAmountButton.setBounds(400, 330, 200, 30);

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
        SavingsPanel.add(HomeButton);

        JFrame frame = new JFrame("Banking Software Prototype");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1066, 577);
        frame.add(SavingsPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
        
        CheckBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BSPController c = new BSPController();
                Balance=c.checkBalance(userdetails);
                JOptionPane.showMessageDialog(SavingsPanel, "Your current Savings balance is "+Balance, "Balance",
        JOptionPane.INFORMATION_MESSAGE);

            }
        });

    }

}
