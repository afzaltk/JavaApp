/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Afzal
 */
public class Account {

    private Connection con = null;
    private Statement st = null;
    private String query;
    private float Balance;
    private String AccountId;
    private boolean valid;
    private int AccountTypeId;

    public String getAccountId(ArrayList userdetails, String AccountType) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();

            query = "select account_id from user_account where user_id = '" + userdetails.get(0) + "' and account_type_id='" + AccountType + "'"
                    + "and isClosed=1 and isBlocked=1";
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) {
                return null;
            } else {
                do {
                    AccountId = rs.getString("account_id");
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(SavingsAccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AccountId;
    }
    
    public int getAccountType(String AccountType) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();

            query = "SELECT Account_type_id FROM account_type WHERE account_desc like '%"+AccountType+"%'";
            ResultSet rs = st.executeQuery(query);
            rs.next();
               
                    AccountTypeId = rs.getInt("Account_type_id");
                
        } catch (SQLException ex) {
            Logger.getLogger(SavingsAccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AccountTypeId;
    }

    public float checkBalance(ArrayList userdetails) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            query = "select current_balance from current_balance where account_id = '" + userdetails.get(3) + "'";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            Balance = rs.getFloat("current_balance");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Balance;
    }

    public boolean verifyPin(ArrayList userdetails, String pin) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            query = "select pin from users where userid = '" + userdetails.get(0) + "'";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String Actual_pin = rs.getString("pin");
            valid = false;
            if (Actual_pin.equals(pin)) {
                valid = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }

    public boolean blockAccount(ArrayList UserDetails) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            query = "update user_account set isBlocked=0 where account_id = " + UserDetails.get(3);
            st.executeUpdate(query);
            valid = true;

        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }

    public boolean transferAmount(ArrayList userdetails, int newAccountTransferInt, int AccountType, int TransferToAccountType) {
       con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            
            st.executeUpdate("INSERT INTO transactions (account_id, transaction_type, transaction_dt, amount)"
                    + " VALUES (" + userdetails.get(3) + ",'D',now()," + newAccountTransferInt + ")");
            st.executeUpdate("INSERT INTO transactions (account_id, transaction_type, transaction_dt, amount)"
                    + " VALUES ((SELECT account_id FROM user_account WHERE account_type_id="+TransferToAccountType+" and user_id= " + userdetails.get(0) + "),'C',now()," + newAccountTransferInt + ")");

                st.executeUpdate("UPDATE current_balance SET current_balance=current_balance+" + newAccountTransferInt + " WHERE "
                    + "account_id=(SELECT account_id FROM user_account WHERE account_type_id="+TransferToAccountType+" and user_id= '" + userdetails.get(0) + " and isClosed=1 and isBlocked=1')");
                st.executeUpdate("UPDATE current_balance SET current_balance=current_balance-" + newAccountTransferInt + " WHERE "
                    + "account_id=(SELECT account_id FROM user_account WHERE account_type_id="+AccountType+" and user_id= '" + userdetails.get(0) + " and isClosed=1 and isBlocked=1')");


        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
