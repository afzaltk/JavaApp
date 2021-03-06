/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.View;

import bsp.Controller.BSPController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Afzal
 */
public class UserView {

    private int i;
    private String Pin;
    private boolean valid;

    public void welcomeUser(ArrayList userdetails) {
        JOptionPane.showMessageDialog(null, "Welcome " + userdetails.get(2) + "....!!", "Welcome", JOptionPane.PLAIN_MESSAGE);
        mainPage(userdetails);
    }

    public void mainPage(ArrayList userdetails) {

        JLabel userNameLabel = new JLabel("Please select an account for transactions");
        userNameLabel.setBounds(370, 100, 500, 25);

        JButton SavingsAccount = new JButton("Savings Account");
        SavingsAccount.setBounds(400, 150, 200, 30);

        JButton TermDepositAccount = new JButton("Term Deposit Account");
        TermDepositAccount.setBounds(400, 210, 200, 30);

        JButton HomeLoanAccount = new JButton("Home Loan Account");
        HomeLoanAccount.setBounds(400, 270, 200, 30);

        JButton CreditCardAccount = new JButton("Credit Card Account");
        CreditCardAccount.setBounds(400, 330, 200, 30);

        JButton logOutButton = new JButton("Log out");
        logOutButton.setBounds(950, 20, 80, 25);

        JPanel accountPanel = new JPanel();

        accountPanel.setLayout(null);
        accountPanel.add(userNameLabel);
        accountPanel.add(SavingsAccount);
        accountPanel.add(TermDepositAccount);
        accountPanel.add(HomeLoanAccount);
        accountPanel.add(CreditCardAccount);
        accountPanel.add(logOutButton);
        
         accountPanel.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon(getClass().getResource("/img/test.jpg")));
        accountPanel.add(background);
        background.setLayout(new FlowLayout());

        JFrame frame = new JFrame("Banking Software Prototype");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1066, 577);
        frame.add(accountPanel);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        SavingsAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                BSPController controller = new BSPController();
                String Result = controller.savingsAccount(userdetails);
                if (Result == null) {
                    JOptionPane.showMessageDialog(accountPanel, "Savings Account does not exist or is  Blocked. Contact Administrator", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    frame.dispose();
                }
            }
        });

        TermDepositAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                BSPController controller = new BSPController();
                String Result = controller.termDepositAccount(userdetails);
                if (Result == null) {
                    JOptionPane.showMessageDialog(accountPanel, "Term Deposit Account does not exist or is  Blocked. Contact Administrator", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    frame.dispose();
                }
            }
        });

        HomeLoanAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                BSPController controller = new BSPController();
                String Result = controller.homeLoanAccount(userdetails);
                if (Result == null) {
                    JOptionPane.showMessageDialog(accountPanel, "Home Loan Account does not exist or is  Blocked. Contact Administrator", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    frame.dispose();
                }
            }
        });

        CreditCardAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    BSPController controller = new BSPController();
                    String Result = controller.creditCardAccount(userdetails);
                    if (Result == null) {
                        JOptionPane.showMessageDialog(accountPanel, "Credit Card Account does not exist or is  Blocked. Contact Administrator", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    }

    boolean enterPin(ArrayList UserDetails, int Attempts) {

        valid = true;
        BSPController ct = new BSPController();
        if (Attempts == 0) {
            ct.blockAccount(UserDetails);

            JOptionPane.showMessageDialog(null, "Your all 3 attempts are over. Your account is now blocked. Contact Administrator", "Error", JOptionPane.ERROR_MESSAGE);
            valid = false;
            mainPage(UserDetails);
        } else {
            JPasswordField pf = new JPasswordField();

            try {
                String Pin = JOptionPane.showInputDialog(
                        pf, null,
                        "Enter the 6 digit PIN to continue.",
                        JOptionPane.OK_CANCEL_OPTION
                );
                PlainDocument document = (PlainDocument) pf.getDocument();
                document.setDocumentFilter(new DocumentFilter() {

                    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                        if (string.length() <= 6) {
                            super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                        }
                    }

                });

                int number = Integer.parseInt(Pin);

                if (ct.verifyPin(UserDetails, Pin) == false) {

                    Attempts = Attempts - 1;
                    JOptionPane.showMessageDialog(null, "Wrong PIN. " + Attempts + " attempts left..!!", "Error", JOptionPane.ERROR_MESSAGE);
                    UserView u = new UserView();
                    u.enterPin(UserDetails, Attempts);

                }

            } catch (NumberFormatException en) {
                JOptionPane.showMessageDialog(null, "Number not found! Please enter an Amount.", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }

        return valid;

    }

}
