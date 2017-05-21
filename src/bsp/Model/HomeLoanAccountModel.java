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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Afzal
 */
public class HomeLoanAccountModel {
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String query;
    private ArrayList HomeLoanDetails = new ArrayList();

    public ArrayList getAccountDetails(ArrayList userdetails) {
         con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
           st = con.createStatement();
            query = "SELECT loan_start_dt,loan_end_dt,loan_amount,nxt_payment_dt from home_loan_account where account_id="+userdetails.get(3);
            ResultSet rs = st.executeQuery(query);
            rs.next();
            HomeLoanDetails.add(rs.getString("loan_start_dt"));
            HomeLoanDetails.add(rs.getString("loan_end_dt"));
            HomeLoanDetails.add(rs.getString("loan_amount"));
            HomeLoanDetails.add(rs.getString("nxt_payment_dt"));
            
            query = "select period_diff(date_format(loan_end_dt,'%Y%m'),date_format(loan_start_dt,'%Y%m')) from home_loan_account where account_id = '" + userdetails.get(3) + "'";
            rs = st.executeQuery(query);
            rs.next();
            HomeLoanDetails.add(rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return HomeLoanDetails;
    }
    
    public boolean modifyHomeLoanAmount(String userid, int newAccountLoanInt) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `home_loan_account` SET `loan_amount`=" + newAccountLoanInt + " WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '3' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyStartDate(String userid, String d) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `home_loan_account` SET `loan_start_dt`='" + d + "' WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '3' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyEndDate(String userid, String d) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `home_loan_account` SET `loan_end_dt`='" + d + "' WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '3' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyNextDate(String userid, String d) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `home_loan_account` SET `nxt_payment_dt`='" + d + "' WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '3' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyBlockedStatus(String userid, int newStatus) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `user_account` SET `isBlocked`=" + newStatus + " WHERE `account_type_id`= '3' and `user_id`= '" + userid + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyClosedStatus(String userid, int newStatus) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `user_account` SET `isClosed`=" + newStatus + " WHERE `account_type_id`= '3' and `user_id`= '" + userid + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    

    

    
}
