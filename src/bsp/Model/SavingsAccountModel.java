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
public class SavingsAccountModel {
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String query;
    private ArrayList columnNames = new ArrayList();
    private ArrayList data = new ArrayList();
    private HashMap TransactionData = new HashMap();

    public boolean depositAmount(ArrayList userdetails, String Amount) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("INSERT INTO transactions (account_id, transaction_type, transaction_dt, amount)"
                    + " VALUES (" + userdetails.get(3) + ",'D',now()," + Amount + ")");
            st.executeUpdate("UPDATE current_balance SET current_balance=current_balance+" + Amount + " WHERE "
                    + "account_id=(SELECT account_id FROM user_account WHERE account_type_id=1 and user_id= '" + userdetails.get(0) + "')");

        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean withdrawAmount(ArrayList userdetails, int Amount) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            st.executeUpdate("INSERT INTO transactions (account_id, transaction_type, transaction_dt, amount)"
                    + " VALUES (" + userdetails.get(3) + ",'C',now()," + Amount + ")");
            st.executeUpdate("UPDATE current_balance SET current_balance=current_balance-" + Amount + " WHERE "
                    + "account_id=(SELECT account_id FROM user_account WHERE account_type_id=1 and user_id= '" + userdetails.get(0) + "')");

        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
          }

    public HashMap viewTransactionsModel(ArrayList userdetails) {
        con = ConnectDB.getConnection();
        try {
            st = con.createStatement();
            query = "select transaction_id as ID, transaction_dt as Date, transaction_type as Type, amount  from transactions where account_id=(select account_id from user_account where user_id = '" + userdetails.get(0) + "' and account_type_id=1 and isClosed=1 and isBlocked=1)";
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
            TransactionData.put(1,columnNames);
            TransactionData.put(2,data);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TransactionData;
        
    }

    

   

    

}
