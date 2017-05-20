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

    public String getUserId() {
        return userID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setUserId(String userId) {

        this.userID = userId;
    }

    
	public ArrayList isBlockedisClosed(String accountID) throws SQLException
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
        return i;
    }
    
    public int getSavingsID(String userID) throws SQLException {
        con = ConnectDB.getConnection();
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT `account_id` FROM `user_account` WHERE `account_type_id` = 1 AND `user_id` = '" + userID + "'");
        rs.next();
        return rs.getInt("account_id");
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
            s.add("There are " + cc + " result coloumns.");
        }
        return s;
     }
}
