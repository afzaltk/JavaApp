
package bsp.View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminSavingsView {
    
    public void viewpage(String userID, int balance)
    {
        JLabel userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(470, 100, 300, 25);
        
        JLabel balanceLabel = new JLabel("Current Balance: " + balance);
        userNameLabel.setBounds(470, 200, 300, 25);
        
        JButton backButton = new JButton("Go back");
        userNameLabel.setBounds(470, 400, 200, 25);
        
        JPanel adminSavingsPanel = new JPanel();
        adminSavingsPanel.setLayout(null);
        adminSavingsPanel.add(userNameLabel);
        adminSavingsPanel.add(balanceLabel);
        
        JFrame frame = new JFrame("Banking Software Prototype");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1066, 577);
        frame.add(adminSavingsPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
