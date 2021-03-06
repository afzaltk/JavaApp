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
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Afzal
 */
public class LogInView {

    public LogInView() {

        JLabel loginCred = new JLabel("Log In with your credentials");
        loginCred.setBounds(450, 100, 200, 25);

        JLabel userIdLabel = new JLabel("User Id");
        userIdLabel.setBounds(400, 150, 80, 25);
        JTextField userId = new JTextField(20);
        PlainDocument document = (PlainDocument) userId.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 10) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
        
        userId.setBounds(500, 150, 160, 25);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(400, 190, 80, 25);
        JPasswordField password = new JPasswordField(20);
        document = (PlainDocument) password.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 10) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
        password.setBounds(500, 190, 160, 25);

        JButton logInButton = new JButton("Log In");
        logInButton.setBounds(480, 240, 80, 25);

        JPanel loginPanel = new JPanel();

        loginPanel.setLayout(null);

        loginPanel.add(loginCred);
        loginPanel.add(userIdLabel);
        loginPanel.add(userId);
        loginPanel.add(passwordLabel);
        loginPanel.add(password);
        loginPanel.add(logInButton);

        JFrame frame = new JFrame("Banking Software Prototype");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1066, 577);
        

        
        loginPanel.setLayout(new BorderLayout());
    JLabel background=new JLabel(new ImageIcon(getClass().getResource("/img/banking.jpg")));
    loginPanel.add(background);
    background.setLayout(new FlowLayout());
    frame.add(loginPanel);
    frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        BSPController controller = new BSPController();
        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    ArrayList credentials = new ArrayList();
                    int number = Integer.parseInt(userId.getText());

                    credentials.add(userId.getText());

                    String passText = new String(password.getPassword());
                    if (passText.equals(null) || passText.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Password cannot be empty. Please provide a password", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        credentials.add(passText);
                        ArrayList value = controller.checkLogin(credentials);

                        if (value.get(2).equals("error")) {
                            JOptionPane.showMessageDialog(loginPanel, "User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            frame.dispose();
                        }

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LogInView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException en) {
                    JOptionPane.showMessageDialog(frame, "Number not found! Please enter a number in User Id.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }

        });

    }

}
