/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.Controller;

import bsp.Model.Account;
import bsp.Model.BankModel;
import bsp.Model.CreditCardAccountModel;
import bsp.Model.HomeLoanAccountModel;
import bsp.Model.SavingsAccountModel;
import bsp.Model.TermDepositAccountModel;
import bsp.View.AdminView;
import bsp.View.CreditCardAccountView;
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

    public void loginControl() {
        new LogInView();//Calls the loginview for the login page
    }

    public void returnAdmin(String userID) {
    }

    public ArrayList checkLogin(ArrayList credentials) throws SQLException {
        BankModel model = new BankModel();

        ArrayList user = model.checkLogin(credentials); //checks login validity using arraylist haing userid and password
        User = (String) user.get(2);

        //Validation occurs
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
        return model.getAccountID(userID, 1);
    }

    public int getCreditID(String userID) throws SQLException {
        BankModel model = new BankModel();
        return model.getAccountID(userID, 4);
    }

    public int getCredit(String userID) throws SQLException {
        BankModel model = new BankModel();
        return model.getBalance("Credit", userID);
    }

    public ArrayList getHomeLoan(String userID) throws SQLException {
        BankModel model = new BankModel();
        return model.getHomeLoanDetails(userID);
    }

    public ArrayList getTermDepositDetails(String userID) throws SQLException {
        BankModel model = new BankModel();
        return model.getTermDepositDetails(userID);
    }

    //for Savings account
    public String savingsAccount(ArrayList userdetails) {
        Account m = new Account();
        AccountType = "1";
        AccountId = m.getAccountId(userdetails, AccountType);
        if (AccountId != null) {
            userdetails.add(AccountId);
            SavingsAccountView v = new SavingsAccountView();
            v.viewpage(userdetails);
        }
        return AccountId;
    }

    //for term deposit account
    public String termDepositAccount(ArrayList userdetails) {
        Account t = new Account();
        AccountType = "2";
        AccountId = t.getAccountId(userdetails, AccountType);
        if (AccountId != null) {
            userdetails.add(AccountId);
            TermDepositAccountView v = new TermDepositAccountView();
            v.viewpage(userdetails);
        }
        return AccountId;
    }

    //check balance for all relevent accounts
    public float checkBalance(ArrayList userdetails) {
        Account a = new Account();
        Balance = a.checkBalance(userdetails);
        return Balance;
    }

    //checks penalty for term deposit account
    public float checkPenalty(ArrayList userdetails) {
        TermDepositAccountModel t = new TermDepositAccountModel();
        Interest = checkInterest(userdetails);
        Penalty = t.checkPenalty(userdetails, Interest);
        return Penalty;
    }

    //checks interest for term deposit account
    public float checkInterest(ArrayList userdetails) {
        TermDepositAccountModel t = new TermDepositAccountModel();
        Interest = t.checkInterest(userdetails);
        return Interest;
    }

    //Withdrawal of term deposit account
    public boolean withdrawAmount(ArrayList userdetails, float Interest, float Penalty) {
        TermDepositAccountModel t = new TermDepositAccountModel();
        return t.withdrawAmount(userdetails, Interest, Penalty);
    }

    //verifies pin
    public boolean verifyPin(ArrayList userdetails, String pin) {
        Account mm = new Account();
        boolean valid = mm.verifyPin(userdetails, pin);
        return valid;
    }

    //blocks account
    public boolean blockAccount(ArrayList UserDetails) {
        Account A = new Account();
        A.blockAccount(UserDetails);
        return false;
    }

    //deposits an amount
    public boolean depositAmount(ArrayList userdetails, String Amount, int AccountType) {
        SavingsAccountModel sam = new SavingsAccountModel();
        return (sam.depositAmount(userdetails, Amount, AccountType));
    }

    public ArrayList accountStatus(String accountID) throws SQLException {
        BankModel model = new BankModel();
        return model.isBlockedisClosed(accountID);
    }

    public ArrayList getCreditDetails(String userID) throws SQLException {
        BankModel model = new BankModel();
        return model.getcreditcardDetails(userID);
    }

    //withdrawas amount from credit card and savings account
    public boolean withdrawAmount(ArrayList userdetails, String Value, int AccountType) {
        int Amount = Integer.parseInt(Value);
        Account a = new Account();
        Balance = a.checkBalance(userdetails);
        if (Amount > Balance) {
            return false;
        } else {
            SavingsAccountModel sam = new SavingsAccountModel();
            sam.withdrawAmount(userdetails, Amount, AccountType);
        }
        return true;
    }

    //Gets all transaction details
    public HashMap viewTransactionsController(ArrayList userdetails, int AccountType) {
        SavingsAccountModel sam = new SavingsAccountModel();
        TransactionData = sam.viewTransactionsModel(userdetails, AccountType);
        columnNames = (ArrayList) TransactionData.get(1);
        data = (ArrayList) TransactionData.get(2);
        for (int i = 0; i < data.size(); i++) {
            ArrayList subArray = (ArrayList) data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++) {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }
        for (int i = 0; i < columnNames.size(); i++) {
            columnNamesVector.add(columnNames.get(i));
        }
        TransactionData.put(3, dataVector);
        TransactionData.put(4, columnNamesVector);
        return TransactionData;
    }

    //For home loan account
    public String homeLoanAccount(ArrayList userdetails) {
        Account t = new Account();
        AccountType = "3";
        AccountId = t.getAccountId(userdetails, AccountType);
        if (AccountId != null) {
            userdetails.add(AccountId);
            HomeLoanAccountModel hm = new HomeLoanAccountModel();
            HomeLoanDetails = hm.getAccountDetails(userdetails);
            float amt = Float.parseFloat((String) HomeLoanDetails.get(2));
            int months_num = (int) HomeLoanDetails.get(4);
            Interest = (amt * 5 * months_num) / 14400;
            String Int = String.valueOf(Interest);
            HomeLoanDetails.add(Int);
            HomeLoanAccountView hlv = new HomeLoanAccountView();
            hlv.homeLoanAccountDetails(HomeLoanDetails, userdetails);
        }
        return AccountId;
    }

    //For credit cards
    public String creditCardAccount(ArrayList userdetails) throws SQLException {
        Account t = new Account();
        AccountType = "4";//credit card  account type is 4 as per database
        AccountId = t.getAccountId(userdetails, AccountType);
        if (AccountId != null) {
            userdetails.add(AccountId);
            CreditCardAccountView c = new CreditCardAccountView();
            c.viewpage(userdetails);
        }
        return AccountId;
    }

    //Modify the daily limit for credit card limit
    public boolean modifyDailyLimit(ArrayList userdetails, int newAccountWithdrawInt, int AccountType) {
        CreditCardAccountModel cc = new CreditCardAccountModel();
        return cc.modifyDailyLimit(userdetails, newAccountWithdrawInt, AccountType);
    }   

    public boolean transferAmount(ArrayList userdetails, int newAccountTransferInt, int AccountType, int TransferToAccountType) {
        Account a=new Account();
        Balance = a.checkBalance(userdetails);
        if (Balance>newAccountTransferInt){
        a.transferAmount(userdetails,newAccountTransferInt,AccountType,TransferToAccountType);
        return true;
        }
        return false;
    }
public String createUser(ArrayList UserInfo) {
        BankModel b = new BankModel();
         String userId=b.createUser(UserInfo);
         return userId;
           }

    public HashMap viewUserListController() {
       BankModel bm = new BankModel();
        TransactionData = bm.viewUserListModel();
        columnNames = (ArrayList) TransactionData.get(1);
        data = (ArrayList) TransactionData.get(2);
        for (int i = 0; i < data.size(); i++) {
            ArrayList subArray = (ArrayList) data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++) {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }
        for (int i = 0; i < columnNames.size(); i++) {
            columnNamesVector.add(columnNames.get(i));
        }
        TransactionData.put(3, dataVector);
        TransactionData.put(4, columnNamesVector);
        return TransactionData;
    }
}
