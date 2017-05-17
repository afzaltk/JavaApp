/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.Controller;

import bsp.Model.Account;
import bsp.Model.BankModel;
import bsp.Model.SavingsAccountModel;
import bsp.Model.TermDepositAccountModel;
import bsp.View.AdminView;
import bsp.View.LogInView;
import bsp.View.SavingsAccountView;
import bsp.View.TermDepositAccountView;
import bsp.View.UserView;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Afzal
 */
public class BSPController {

    private String User;
    private String AccountId;
    private float Balance;
    private String AccountType;
    private float Penalty; 
    private float Interest; 

    public BSPController() {

    }

    public void loginControl() {
        new LogInView();
    }

    public ArrayList checkLogin(ArrayList credentials) throws SQLException {
        BankModel model = new BankModel();

        ArrayList user = model.checkLogin(credentials);
        User = (String) user.get(2);

        if (!User.equals("error") && !User.equals("admin")) {

            UserView userview = new UserView();
            userview.welcomeUser(user);
        } else if (User.equals("admin")) {
            new AdminView();

        }

        return user;

    }

    
    public String savingsAccount(ArrayList userdetails) {
        Account m = new Account();
        AccountType="1";
        AccountId = m.getAccountId(userdetails,AccountType);
        
        
        if (AccountId != null) {
            userdetails.add(AccountId);
            SavingsAccountView v = new SavingsAccountView();
            v.viewpage(userdetails);
        } 
        return AccountId;

    }
    
 public String termDepositAccount(ArrayList userdetails) {
        
        Account t = new Account();
        
        AccountType="2";
        AccountId = t.getAccountId(userdetails,AccountType);
                
        if (AccountId != null) {
            
            userdetails.add(AccountId);
            TermDepositAccountView v = new TermDepositAccountView();
            v.viewpage(userdetails);
        } 
        return AccountId;
      
    }
    public float checkBalance(ArrayList userdetails) {
        Account a=new Account();
        Balance=a.checkBalance(userdetails);
        return Balance;
    }

    public float checkPenalty(ArrayList userdetails) {
        TermDepositAccountModel t=new TermDepositAccountModel();
        Interest=checkInterest(userdetails);
        Penalty = t.checkPenalty(userdetails,Interest);
        return Penalty;
    }

   public float checkInterest(ArrayList userdetails) {
        TermDepositAccountModel t=new TermDepositAccountModel();
        Interest = t.checkInterest(userdetails);
        return Interest;
    }

    public boolean withdrawAmount(ArrayList userdetails, float Interest, float Penalty) {
        TermDepositAccountModel t=new TermDepositAccountModel();
         return t.withdrawAmount(userdetails,Interest,Penalty);
        
    }

    public boolean verifyPin(ArrayList userdetails, String pin) {
        
        Account mm =new Account();
        boolean valid=mm.verifyPin(userdetails,pin);
        return valid;
        
    }

     public boolean blockAccount(ArrayList UserDetails) {
    Account A = new Account();
    A.blockAccount(UserDetails);
    return false;
    }

   public boolean depositAmount(ArrayList userdetails, String Amount) {
       SavingsAccountModel sam=new SavingsAccountModel();
      return (sam.depositAmount(userdetails,Amount));
       
       
   }

    public void withdrawAmount(ArrayList userdetails, String Amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   

}
