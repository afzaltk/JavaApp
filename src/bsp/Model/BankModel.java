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

}
