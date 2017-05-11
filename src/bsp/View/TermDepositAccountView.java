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
import javax.swing.JPasswordField;

/**
 *
 * @author Afzal
 */
public class TermDepositAccountView {

    private float Balance;
    private float Penalty;
    private float Interest;
    private String pin;

    public void viewpage(ArrayList userdetails) {

        JLabel UserNameLabel = new JLabel((String) userdetails.get(2));
        UserNameLabel.setBounds(370, 100, 100, 25);

        JLabel AccountIdLabel = new JLabel("Term Deposit Account Id - " + (String) userdetails.get(3));
        AccountIdLabel.setBounds(470, 100, 300, 25);

        JButton CheckBalanceButton = new JButton("Check Balance");
        CheckBalanceButton.setBounds(400, 150, 200, 30);

        JButton WithdrawAmountButton = new JButton("Withdraw Amount");
        WithdrawAmountButton.setBounds(400, 210, 200, 30);

        JButton LogOutButton = new JButton("Log out");
        LogOutButton.setBounds(950, 20, 80, 25);

        JButton HomeButton = new JButton("Home");
        HomeButton.setBounds(50, 20, 80, 25);

        JPanel TermDepositPanel = new JPanel();
        TermDepositPanel.setLayout(null);
        TermDepositPanel.add(UserNameLabel);
        TermDepositPanel.add(AccountIdLabel);
        TermDepositPanel.add(LogOutButton);
        TermDepositPanel.add(WithdrawAmountButton);
        TermDepositPanel.add(CheckBalanceButton);
        TermDepositPanel.add(HomeButton);

        JFrame frame = new JFrame("Banking Software Prototype");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1066, 577);
        frame.add(TermDepositPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        HomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userdetails.remove(3);
                UserView v = new UserView();
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
                Balance = c.checkBalance(userdetails);
                JOptionPane.showMessageDialog(TermDepositPanel, "Your Term Deposit Account balance is " + Balance, "Balance",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });

        WithdrawAmountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BSPController ct = new BSPController();
                JPasswordField pf = new JPasswordField();
                pin = JOptionPane.showInputDialog(
                        pf, null,
                        "Enter the 4 digit PIN to continue (" + 36 + " tries left)",
                        JOptionPane.WARNING_MESSAGE
                );

                boolean valid = ct.verifyPin(userdetails, pin);
                if (valid == true) {
                    Interest = ct.checkInterest(userdetails);
                    Penalty = ct.checkPenalty(userdetails);

                    String message = "End Date not Reached. Total interest till date is " + Interest + ". An amount of " + Penalty + " will be levied on Withdrawal. Continue?";
                    String title = "Penalty ";
                    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        boolean update;
                        update = ct.withdrawAmount(userdetails, Interest, Penalty);
                        if (update == true) {
                            JOptionPane.showMessageDialog(TermDepositPanel, "Your Withdrawn Amount has been transferred to your Savings Account", null,
                        JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            userdetails.remove(3);
                            UserView v = new UserView();
                            v.mainPage(userdetails);

                        } else {
                            JOptionPane.showMessageDialog(TermDepositPanel, "Unable to Withdraw Amount", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                } 

            }
        });

    }

}