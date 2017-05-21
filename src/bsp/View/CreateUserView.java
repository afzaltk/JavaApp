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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;

/**
 *
 * @author Afzal
 */
public class CreateUserView {

    private ArrayList UserInfo = new ArrayList();
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public CreateUserView(ArrayList userdetails) {

        JLabel userNameLabel = new JLabel("Please provide details of the user");
        userNameLabel.setBounds(370, 100, 500, 25);

        JLabel FirstName = new JLabel("First Name");
        FirstName.setBounds(350, 150, 150, 30);

        JTextField FirstNameVal = new JTextField(20);
        FirstNameVal.setBounds(500, 150, 150, 30);

        JLabel LastName = new JLabel("Last Name");
        LastName.setBounds(350, 190, 150, 30);

        JTextField LastNameVal = new JTextField(20);
        LastNameVal.setBounds(500, 190, 150, 30);

        JLabel Email = new JLabel("Email ID");
        Email.setBounds(350, 230, 150, 30);

        JTextField EmailVal = new JTextField(20);
        EmailVal.setBounds(500, 230, 150, 30);

        JLabel Phone = new JLabel("Phone Number");
        Phone.setBounds(350, 270, 150, 30);

        JTextField PhoneVal = new JTextField(20);
        PhoneVal.setBounds(500, 270, 150, 30);

        JLabel Address = new JLabel("Address");
        Address.setBounds(350, 310, 150, 30);

        JTextArea AddressVal = new JTextArea("");
        AddressVal.setBounds(500, 310, 200, 50);

        JLabel ProofID = new JLabel("ID Proof Number");
        ProofID.setBounds(350, 370, 150, 30);

        JTextField ProofIDVal = new JTextField(20);
        ProofIDVal.setBounds(500, 370, 150, 30);

        JCheckBox AdminCheck = new JCheckBox("Admin");
        AdminCheck.setBounds(400, 410, 200, 30);

        JButton CreateUserButton = new JButton("Create User");
        CreateUserButton.setBounds(400, 450, 200, 30);

        JButton logOutButton = new JButton("Log out");
        logOutButton.setBounds(950, 20, 80, 25);
        JButton HomeButton = new JButton("Home");
        HomeButton.setBounds(50, 20, 80, 25);

        JPanel accountPanel = new JPanel();

        accountPanel.setLayout(null);
        accountPanel.add(userNameLabel);
        accountPanel.add(FirstName);
        accountPanel.add(FirstNameVal);
        accountPanel.add(LastName);
        accountPanel.add(LastNameVal);
        accountPanel.add(Email);
        accountPanel.add(EmailVal);
        accountPanel.add(Phone);
        accountPanel.add(PhoneVal);
        accountPanel.add(Address);
        accountPanel.add(AddressVal);
        accountPanel.add(ProofID);
        accountPanel.add(ProofIDVal);
        accountPanel.add(CreateUserButton);
        accountPanel.add(logOutButton);
        accountPanel.add(HomeButton);
        accountPanel.add(AdminCheck);

        AdminCheck.setSelected(false);

        JFrame frame = new JFrame("Banking Software Prototype");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1066, 577);
        frame.add(accountPanel);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        CreateUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserInfo.clear();
                BSPController controller = new BSPController();
                try {
                    if (FirstNameVal.getText().matches("^[a-zA-Z]+$")) {
                        UserInfo.add(FirstNameVal.getText());
                        if (LastNameVal.getText().matches("^[a-zA-Z]+$")) {
                            UserInfo.add(LastNameVal.getText());
                            pattern = Pattern.compile(EMAIL_PATTERN);
                            matcher = pattern.matcher(EmailVal.getText().trim());
                            if (matcher.matches()) {
                                UserInfo.add(EmailVal.getText());

                                int number = Integer.parseInt(PhoneVal.getText());
                                UserInfo.add(PhoneVal.getText());

                                if (AddressVal.getText() == null || AddressVal.getText().trim().equals("")) {
                                    UserInfo.clear();
                                    JOptionPane.showMessageDialog(accountPanel, "Please enter a Valid  Address", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    UserInfo.add(AddressVal.getText());
                                    if (ProofIDVal.getText() == null || ProofIDVal.getText().trim().equals("")) {
                                        UserInfo.clear();
                                        JOptionPane.showMessageDialog(accountPanel, "Please enter a Valid ID proof detail", "Error", JOptionPane.ERROR_MESSAGE);

                                    } else {
                                        UserInfo.add(ProofIDVal.getText());
                                        if (AdminCheck.isSelected()) {
                                            UserInfo.add("1");
                                        } else {
                                            UserInfo.add("0");
                                        }
                                        BSPController c = new BSPController();
                                        String userId = c.createUser(UserInfo);
                                        if (userId == null) {

                                            JOptionPane.showMessageDialog(accountPanel, "Unable to create user", "Error", JOptionPane.ERROR_MESSAGE);

                                        } else {
                                            JOptionPane.showMessageDialog(accountPanel, "User Created. ID is " + userId, "User Created", JOptionPane.INFORMATION_MESSAGE);
                                            frame.dispose();
                                            CreateUserView cd = new CreateUserView(userdetails);
                                        }
                                    }
                                }
                            } else {
                                UserInfo.clear();
                                JOptionPane.showMessageDialog(accountPanel, "Please enter a Valid Email ID", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            UserInfo.clear();
                            JOptionPane.showMessageDialog(accountPanel, "Please enter a Valid  Last Name", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        UserInfo.clear();
                        JOptionPane.showMessageDialog(accountPanel, "Please enter a Valid First Name", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException en) {
                    JOptionPane.showMessageDialog(frame, "Number not found! Please enter a Valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
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

        HomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AdminView v = new AdminView();
                v.mainPage(userdetails);
            }
        });
    }
}
