/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Afzal
 */
public class BankModel extends ConnectDB {

    private Connection con = null;

    private Statement st = null;
    private ResultSet rs = null;
    private String query;
    private String userID;
    private String fname;
    private ArrayList columnNames = new ArrayList();
    private ArrayList data = new ArrayList();
    private HashMap TransactionData = new HashMap();

    public ArrayList checkLogin(ArrayList creds) throws SQLException {
        con = ConnectDB.getConnection();
        st = con.createStatement();

        String query1 = "SELECT count(*) as rowcount,isadmin FROM users WHERE userid = '" + creds.get(0) + "' AND password = '" + creds.get(1) + "'";
        String query2 = "SELECT fname FROM user_details WHERE userid = '" + creds.get(0) + "'";
        ResultSet rs = st.executeQuery(query1);
        rs.next();
        if (rs.getInt("rowcount") == 1 && rs.getBoolean("isadmin")) {
            creds.add("admin");
            return creds;
        } else if (rs.getInt("rowcount") == 1 && rs.getBoolean("isadmin") == false) {

            rs = st.executeQuery(query2);
            rs.next();
            creds.add(rs.getString("fname"));
            return creds;
        }
        creds.add("error");
        return creds;
    }

    public ArrayList isBlockedisClosed(int accountID) throws SQLException 
	{
        con = ConnectDB.getConnection();
        st = con.createStatement();
        ArrayList blockedClosedArray = new ArrayList();
        ResultSet res = st.executeQuery("SELECT `isBlocked`, `isClosed` FROM `user_account` WHERE `account_id` = '" + accountID + "'");
        res.next();
        blockedClosedArray.add(res.getInt("isBlocked"));
        blockedClosedArray.add((res.getInt("isClosed")));
        return blockedClosedArray;
    }

    public ArrayList getAccounts(String userID) throws SQLException {
        con = ConnectDB.getConnection();
        st = con.createStatement();
        ArrayList<String> s = new ArrayList<>();
        String query1 = "SELECT `account_type_id` FROM `user_account` WHERE user_id = '" + userID + "'";
        ResultSet rs = st.executeQuery(query1);
        while (rs.next()) 
		{
            s.add(rs.getString("account_type_id"));
        }
        return s;
    }

    public int getBalance(String acctype, String userID) throws SQLException 
	{
        con = ConnectDB.getConnection();
        st = con.createStatement();
        int i = 0;
        if (acctype.equals("Savings"))
		 {
            ResultSet rs = st.executeQuery("SELECT `account_id`, `user_id` FROM `user_account` WHERE `account_type_id` = 1 AND `user_id` = '" + userID + "'");
            rs.next();
            int accountNumber = rs.getInt("account_id");
            ResultSet rs2 = st.executeQuery("SELECT `current_balance` FROM `current_balance` WHERE `account_id` = '" + accountNumber + "'");
            rs2.next();
            int accountBalance = rs2.getInt("current_balance");
            i = accountBalance;
        }
        if (acctype.equals("Credit")) 
		{
            ResultSet rs = st.executeQuery("SELECT `account_id`, `user_id` FROM `user_account` WHERE `account_type_id` = 4 AND `user_id` = '" + userID + "'");
            rs.next();
            int accountNumber = rs.getInt("account_id");
            ResultSet rs2 = st.executeQuery("SELECT `current_balance` FROM `current_balance` WHERE `account_id` = '" + accountNumber + "'");
            rs2.next();
            int accountBalance = rs2.getInt("current_balance");
            i = accountBalance;
        }
        return i;
    }

    public ArrayList getUser(String userID) throws SQLException {
        con = ConnectDB.getConnection();
        st = con.createStatement();
        ArrayList<String> s = new ArrayList<>();
        String query1 = "SELECT * FROM `user_details` WHERE userid = '" + userID + "'";
        ResultSet rs = st.executeQuery(query1);
        ResultSetMetaData rsmd = rs.getMetaData();
        int coloumns = rsmd.getColumnCount();
        String cc = Integer.toString(coloumns);
        rs.next();
        s.add("success");
        s.add(Integer.toString(rs.getInt("USERID")));
        s.add(rs.getString("fname"));
        s.add(rs.getString("lname"));
        s.add(rs.getString("email"));
        s.add(rs.getString("address"));
        s.add(rs.getString("ID"));
        s.add(Integer.toString(rs.getInt("phone_number")));

        if (s.isEmpty()) 
		{
           s.add("Empty result");
        }
        return s;
    }

    public int getAccountID(String userID, int acctype) throws SQLException {
        con = ConnectDB.getConnection();
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT `account_id` FROM `user_account` WHERE `account_type_id` = " + acctype + " AND `user_id` = '" + userID + "'");
        rs.next();
        return rs.getInt("account_id");
    }

    public ArrayList getcreditcardDetails(String userID) throws SQLException
	 {
        con = ConnectDB.getConnection();
        st = con.createStatement();
        int creditID = this.getAccountID(userID, 4);
        String q = "SELECT `daily_limit`, `maximum_debt` FROM `credit_card_account` WHERE `account_id` = '" + creditID + "'";
        ResultSet rs = st.executeQuery(q);
        ArrayList ar = new ArrayList();
        rs.next();
        ar.add(rs.getInt("daily_limit"));
        ar.add(rs.getInt("maximum_debt"));
        return ar;
    }

    public ArrayList getHomeLoanDetails(String userID) throws SQLException
	 {
        con = ConnectDB.getConnection();
        st = con.createStatement();
        int homeloanID = this.getAccountID(userID, 3);
        String q = "SELECT * FROM `home_loan_account` WHERE `account_id` = '" + homeloanID + "'";
        ResultSet rs = st.executeQuery(q);
        ArrayList ar = new ArrayList();
        rs.next();
        ar.add(rs.getDate("loan_start_dt"));
        ar.add(rs.getDate("loan_end_dt"));
       
        ar.add(rs.getInt("loan_amount"));
		ar.add(rs.getDate("nxt_payment_dt"));
        return ar;
    }

    public ArrayList getTermDepositDetails(String userID) throws SQLException 
	{
        con = ConnectDB.getConnection();
        st = con.createStatement();
        int termdepositID = this.getAccountID(userID, 2);
        String q = "SELECT * FROM `term_deposit_account` WHERE `account_id` = '" + termdepositID + "'";
        ResultSet rs = st.executeQuery(q);
        ArrayList ar = new ArrayList();
        rs.next();
        ResultSetMetaData rsmd = rs.getMetaData();
        ar.add(rs.getDate("term_start_dt"));
        ar.add(rs.getDate("term_end_dt"));
        ar.add(rs.getFloat("interest_rate"));
        ar.add(rs.getInt("term-amount"));
        ar.add(rs.getDate("early_withdrawal_dt"));
        return ar;
    }

    public String createUser(ArrayList UserInfo) {
        con = ConnectDB.getConnection();
        String userId=null;
        try {
            st = con.createStatement();
            st.executeUpdate("INSERT INTO `user_details`( `fname`, `lname`, `email`, `address`, `ID`, `phone_number`) VALUES "
                    + "('" + UserInfo.get(0) + "','" + UserInfo.get(1) + "','" + UserInfo.get(2) + "','" + UserInfo.get(4) + "','" + UserInfo.get(5) + "','" + UserInfo.get(3) + "')");
            st.executeUpdate("INSERT INTO `user_account`(`user_id`, `account_type_id`, `isClosed`, `isBlocked`) VALUES "
                    + "((select userid from user_details where ID='" + UserInfo.get(5) + "'),1,0,0),"
                    + "((select userid from user_details where ID='" + UserInfo.get(5) + "'),2,0,0),"
                    + "((select userid from user_details where ID='" + UserInfo.get(5) + "'),3,0,0),"
                    + "((select userid from user_details where ID='" + UserInfo.get(5) + "'),4,0,0)");
            st.executeUpdate("INSERT INTO `users`(`USERID`, `PASSWORD`, `ISADMIN`, `PIN`) VALUES "
                    + "((select userid from user_details WHERE id ='" + UserInfo.get(5) + "' ),'password'," + UserInfo.get(6) + " ,123456)");

            st.executeUpdate("INSERT INTO `current_balance`(`account_id`, `current_balance`) VALUES "
                    + "((SELECT account_id from user_account where account_type_id=1 and"
                    + "  user_id = (select userid from user_details where id='" + UserInfo.get(5) + "' )),0),"
                    + "  ((SELECT account_id from user_account where account_type_id=2 and "
                    + "  user_id = (select userid from user_details where id='" + UserInfo.get(5) + "' )),0),"
                    + "  ((SELECT account_id from user_account where account_type_id=3 and "
                    + "  user_id = (select userid from user_details where id='" + UserInfo.get(5) + "' )),0),"
                    + "  ((SELECT account_id from user_account where account_type_id=4 and "
                    + "  user_id = (select userid from user_details where id= '" + UserInfo.get(5) + "')),0)");
            
            ResultSet rs = st.executeQuery("select userid from user_details where id= '" + UserInfo.get(5) + "'");
            rs.next();
       
            userId=rs.getString("userid");
            
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userId;
    }

    public HashMap viewUserListModel() {
       con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            query = "select * from user_details";
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.add(md.getColumnName(i));
            }
            while (rs.next()) {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++) {
                    row.add(rs.getObject(i));
                }

                data.add(row);
            }
            TransactionData.put(1, columnNames);
            TransactionData.put(2, data);

        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TransactionData;
    }
	public ArrayList getUserDetails(String userID) throws SQLException
     {
         con = ConnectDB.getConnection();
         st = con.createStatement();
         ArrayList ar = new ArrayList();
         String q1 = "SELECT `password`, `pin` FROM `users` WHERE `userid`='" + userID + "'";
         ResultSet rs1 = st.executeQuery(q1);
         rs1.next();
         ar.add(rs1.getString("password"));
         ar.add(rs1.getString("pin"));
         String q2 = "SELECT * FROM `user_details` WHERE `userid`='" + userID + "'";
         ResultSet rs2 = st.executeQuery(q2);
         rs2.next();
         ar.add(rs2.getString("fname"));
         ar.add(rs2.getString("lname"));
         ar.add(rs2.getString("email"));
         ar.add(rs2.getString("address"));
         ar.add(rs2.getInt("phone_number"));
         return ar;
     }
     
     public boolean setUserDetails(String userid, String detail, int i)
     {
         String q = "SELECT * FROM `users`";
         switch (i)
         {
             case 1: q = "UPDATE `users` SET `password`='" + detail + "' WHERE `userid`='" + userid + "'";
             break;
             case 2: q = "UPDATE `users` SET `pin`='" + detail + "' WHERE `userid`='" + userid + "'";
             break;
             case 3: q = "UPDATE `user_details` SET `fname`='" + detail + "' WHERE `userid`='" + userid + "'";
             break;
             case 4: q = "UPDATE `user_details` SET `lname`='" + detail + "' WHERE `userid`='" + userid + "'";
             break;
             case 5: q = "UPDATE `user_details` SET `email`='" + detail + "' WHERE `userid`='" + userid + "'";
             break;
             case 6: q = "UPDATE `user_details` SET `address`='" + detail + "' WHERE `userid`='" + userid + "'";
             break;
             case 7: q = "UPDATE `user_details` SET `phone_number`='" + detail + "' WHERE `userid`='" + userid + "'";
         }

         con = ConnectDB.getConnection();
         
         try {
             st = con.createStatement();
             st.executeUpdate(q);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         
         return true;
         
     }

}
