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
public class CreditCardAccountModel {
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String query;

    public boolean modifyDailyLimit(ArrayList userdetails, int newAccountWithdrawInt, int AccountType) {
      con = ConnectDB.getConnection();
        try {
            
            st = con.createStatement();
                        st.executeUpdate("UPDATE credit_card_account SET daily_limit="+newAccountWithdrawInt+" WHERE "
                    + "account_id=(SELECT account_id FROM user_account WHERE account_type_id=" + AccountType + " and user_id= '" + userdetails.get(0) + "' and isClosed=1 and isBlocked=1)");

        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
	public boolean modifyDailyLimit(String userid, int newAccountWithdrawInt) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `credit_card_account` SET `daily_limit`=" + newAccountWithdrawInt + " WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '4' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyMaxDebt(String userid, int newAccountDebtInt) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `credit_card_account` SET `maximum_debt`=" + newAccountDebtInt + " WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '4' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyBlockedStatus(String userid, int newStatus) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `user_account` SET `isBlocked`=" + newStatus + " WHERE `account_type_id`= '4' and `user_id`= '" + userid + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyClosedStatus(String userid, int newStatus) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `user_account` SET `isClosed`=" + newStatus + " WHERE `account_type_id`= '4' and `user_id`= '" + userid + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
