package com.revature;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private String name;
    private String password;
    private static final Logger logger = Logger.getLogger("mts");

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int register() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionMaker.getConnection();
            String SQL = "INSERT INTO customers (name, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            int i = pstmt.executeUpdate();
            ResultSet primaryKey = pstmt.getGeneratedKeys();
            if (primaryKey.next()) {
                return primaryKey.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception: Insert failed " + e.getMessage());
            int idCustomer = -1;
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }


            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }

        }
        return  -1;
    }
}
