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
import static java.lang.Float.parseFloat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Afzal
 */
public class HomeLoanAccountView {

    private HashMap TransactionData = new HashMap();
    private Vector columnNamesVector = new Vector();
    private Vector dataVector = new Vector();
    private int AccountType = 3;
    private float Amt;

    public void homeLoanAccountDetails(ArrayList HomeLoanDetails, ArrayList userdetails) {

        JLabel Loan_date = new JLabel("Loan Start Date");
        Loan_date.setBounds(300, 150, 100, 25);

        JLabel Loan_date_value = new JLabel((String) HomeLoanDetails.get(0));
        Loan_date_value.setBounds(400, 150, 500, 25);

        JLabel Loan_Amt = new JLabel("Loan Amount");
        Loan_Amt.setBounds(300, 210, 100, 25);

        JLabel Loan_amt_value = new JLabel((String) HomeLoanDetails.get(2));
        Loan_amt_value.setBounds(400, 210, 500, 25);

        JLabel LoanEndDate = new JLabel("Loan End Date");
        LoanEndDate.setBounds(300, 270, 100, 25);

        JLabel LoanEndDateVal = new JLabel((String) HomeLoanDetails.get(1));
        LoanEndDateVal.setBounds(400, 270, 500, 25);

        JLabel NextPymntDt = new JLabel("Next Payment Date");
        NextPymntDt.setBounds(550, 150, 150, 25);

        JLabel NextPymntDtVal = new JLabel((String) HomeLoanDetails.get(3));
        NextPymntDtVal.setBounds(700, 150, 500, 25);

        JLabel InterestAmt = new JLabel("Monthly Interest");
        InterestAmt.setBounds(550, 210, 100, 25);

        Amt = parseFloat((String) HomeLoanDetails.get(5));

        JLabel InterestAmtVal = new JLabel((String) HomeLoanDetails.get(5));
        InterestAmtVal.setBounds(700, 210, 500, 25);

        JButton ViewTransactionsButton = new JButton("View All Payments");
        ViewTransactionsButton.setBounds(400, 330, 200, 30);

        JButton LogOutButton = new JButton("Log out");
        LogOutButton.setBounds(950, 20, 80, 25);

        JButton HomeButton = new JButton("Home");
        HomeButton.setBounds(50, 20, 80, 25);

        JPanel accountPanel = new JPanel();

        accountPanel.setLayout(null);
        accountPanel.add(LoanEndDate);
        accountPanel.add(LoanEndDateVal);
        accountPanel.add(Loan_Amt);
        accountPanel.add(Loan_amt_value);
        accountPanel.add(Loan_date);
        accountPanel.add(Loan_date_value);
        accountPanel.add(HomeButton);
        accountPanel.add(LogOutButton);
        accountPanel.add(NextPymntDtVal);
        accountPanel.add(NextPymntDt);
        accountPanel.add(InterestAmt);
        accountPanel.add(InterestAmtVal);
        accountPanel.add(ViewTransactionsButton);

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

                JFrame TransactionsFrame = new JFrame("Home Loan Account Transactions");
                TransactionsFrame.setSize(866, 477);
                TransactionsFrame.add(scrollPane);
                TransactionsFrame.add(buttonPanel, BorderLayout.SOUTH);
                TransactionsFrame.setLocationRelativeTo(null);
                TransactionsFrame.setVisible(true);

            }

        });

    }
}
