/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.Controller;

import bsp.Model.Account;
import bsp.Model.BankModel;
import bsp.Model.HomeLoanAccountModel;
import bsp.Model.SavingsAccountModel;
import bsp.Model.TermDepositAccountModel;
import bsp.View.AdminView;
import bsp.View.HomeLoanAccountView;
import bsp.View.LogInView;
import bsp.View.SavingsAccountView;
import bsp.View.TermDepositAccountView;
import bsp.View.UserView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

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
    private HashMap TransactionData = new HashMap();
    private ArrayList columnNames = new ArrayList();
    private ArrayList data = new ArrayList();
    private Vector columnNamesVector = new Vector();
    private Vector dataVector = new Vector();
    private ArrayList HomeLoanDetails = new ArrayList();

    public BSPController() {

    }

    public void loginControl() {
        new LogInView();
    }
 public void returnAdmin(String userID) {
    
    }
    public ArrayList checkLogin(ArrayList credentials) throws SQLException {
        BankModel model = new BankModel();

        ArrayList user = model.checkLogin(credentials);
        User = (String) user.get(2);

        if (!User.equals("error") && !User.equals("admin")) {

            UserView userview = new UserView();
            userview.welcomeUser(user);
        } else if (User.equals("admin")) {
           AdminView adminview = new AdminView();
            adminview.welcomeUser(user);

        }

        return user;

    }
	 public ArrayList getAccounts(String userID) throws SQLException {
        BankModel model = new BankModel();
        return model.getAccounts(userID);
    }
    
    public ArrayList getUser(String userID) throws SQLException {
        BankModel model = new BankModel();
        return model.getUser(userID);
    }
    
    public int getSavings(String userID) throws SQLException {
        BankModel model = new BankModel();
        return model.getBalance("Savings", userID);
    }
    
    public int getSavingsID(String userID) throws SQLException {
        BankModel model = new BankModel(); 	 	 	     
        return model.getSavingsID(userID);
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

    public boolean withdrawAmount(ArrayList userdetails, String Value) {
        int Amount = Integer.parseInt(Value);
        Account a=new Account();
        Balance=a.checkBalance(userdetails);
        if (Amount > Balance){
            
        return false;
        }
        else{
            SavingsAccountModel sam=new SavingsAccountModel();
            sam.withdrawAmount(userdetails,Amount);
        }
        return true;
    }

    public HashMap viewTransactionsController(ArrayList userdetails,int AccountType) {
       SavingsAccountModel sam=new SavingsAccountModel();
       
            TransactionData=sam.viewTransactionsModel(userdetails,AccountType);
            columnNames=(ArrayList) TransactionData.get(1);
            data=(ArrayList) TransactionData.get(2);
            
            for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }
            
            for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

            
            TransactionData.put(3,dataVector);
            TransactionData.put(4,columnNamesVector);
            
            return TransactionData;
                    
            
            
            
    }
	public ArrayList accountStatus(String accountID) throws SQLException
    {
        BankModel model = new BankModel();
        return model.isBlockedisClosed(accountID);
    }

    public String homeLoanAccount(ArrayList userdetails) {
       Account t = new Account();
        
        AccountType="3";
        AccountId = t.getAccountId(userdetails,AccountType);
                
        if (AccountId != null) {
            
            userdetails.add(AccountId);
            HomeLoanAccountModel hm = new HomeLoanAccountModel();
            HomeLoanDetails=hm.getAccountDetails(userdetails);
            float amt=Float.parseFloat((String) HomeLoanDetails.get(3));
            float rate=Float.parseFloat((String) HomeLoanDetails.get(2));
            int months_num=(int) HomeLoanDetails.get(5);
            Interest = (amt*rate*months_num)/14400;
            String Int = String.valueOf(Interest);
            HomeLoanDetails.add(Int);
            HomeLoanAccountView hlv = new HomeLoanAccountView();
            hlv.homeLoanAccountDetails(HomeLoanDetails,userdetails);
            
        } 
        return AccountId;
          }

    

    
   

}
