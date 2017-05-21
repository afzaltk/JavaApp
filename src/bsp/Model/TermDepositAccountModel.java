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
public class TermDepositAccountModel {

    private Connection con = null;
    private Statement st = null;
    private String query;
    private float Penalty;
    private float Interest;
    private String AccountId;
    private java.util.Date Start_date;
    private java.util.Date End_date;
    private float Interest_rate;
    private float Term_Amount;
    private int Months_num;
    private float Withdrawal_amount;

    public float checkPenalty(ArrayList userdetails, float Interest) {

        Penalty = Interest / 2;

        return Penalty;

    }

    public float checkInterest(ArrayList userdetails) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            query = "SELECT * FROM term_deposit_account where account_id = '" + userdetails.get(3) + "'";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            Start_date = rs.getDate(2);
            End_date = rs.getDate(3);
            Interest_rate = rs.getFloat(4);
            Term_Amount = rs.getFloat(5);
            query = "select period_diff(date_format(now(),'%Y%m'),date_format(term_start_dt,'%Y%m')) from term_deposit_account where account_id = '" + userdetails.get(3) + "'";
            rs = st.executeQuery(query);
            rs.next();
            Months_num = rs.getInt(1);
            Interest = (Term_Amount * Interest_rate * Months_num) / 1200;

        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Interest;
    }

    public boolean withdrawAmount(ArrayList userdetails, float Interest, float Penalty) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            query = "SELECT * FROM term_deposit_account where account_id = '" + userdetails.get(3) + "'";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            Term_Amount = rs.getFloat(5);
            Withdrawal_amount = Term_Amount + Interest - Penalty;
            st.executeUpdate("INSERT INTO transactions (account_id, transaction_type, transaction_dt, amount)"
                    + " VALUES (" + userdetails.get(3) + ",'C',now()," + Withdrawal_amount + ")");
            st.executeUpdate("INSERT INTO transactions (account_id, transaction_type, transaction_dt, amount)"
                    + " VALUES ((SELECT account_id FROM user_account WHERE account_type_id=1 and user_id= " + userdetails.get(0) + "),'D',now()," + Withdrawal_amount + ")");

            st.executeUpdate("UPDATE term_deposit_account SET early_withdrawal_dt=now() WHERE account_id=" + userdetails.get(3) );
            
            st.executeUpdate("UPDATE current_balance SET current_balance=current_balance+" + Withdrawal_amount + " WHERE "
                    + "account_id=(SELECT account_id FROM user_account WHERE account_type_id=1 and user_id= '" + userdetails.get(0) + "')");
            st.executeUpdate("UPDATE current_balance SET current_balance=0 WHERE account_id=" + userdetails.get(3));
            st.executeUpdate("UPDATE user_account SET isClosed=0 WHERE account_id=" + userdetails.get(3) );


        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
	public boolean modifyTermDepositInterestRate(String userid, float newInterestRate) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `term_deposit_account` SET `interest_rate`=" + newInterestRate + " WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '2' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyTermDepositTotal(String userid, float newInterestRate) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `term_deposit_account` SET `term-amount`=" + newInterestRate + " WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '2' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyStartDate(String userid, String d) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `term_deposit_account` SET `term_start_dt`='" + d + "' WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '2' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyEndDate(String userid, String d) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `term_deposit_account` SET `term_end_dt`='" + d + "' WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '2' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyWithdrawDate(String userid, String d) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `term_deposit_account` SET `early_withdrawal_dt`='" + d + "' WHERE `account_id`=(SELECT `account_id` FROM `user_account` WHERE `account_type_id`= '2' and `user_id`= '" + userid + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyBlockedStatus(String userid, int newStatus) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `user_account` SET `isBlocked`=" + newStatus + " WHERE `account_type_id`= '2' and `user_id`= '" + userid + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean modifyClosedStatus(String userid, int newStatus) {
      con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `user_account` SET `isClosed`=" + newStatus + " WHERE `account_type_id`= '2' and `user_id`= '" + userid + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
