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
    private boolean  valid;

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
            valid=false;
            if(Actual_pin.equals(pin)){
              valid=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
           }

}
