package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Offer {
    private int customerId;
    private int itemId;
    private int price;
    private int status;

    private static final Logger logger = Logger.getLogger("mts");

    public Offer(int customerId, int itemId, int price, int status) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.price = price;
        this.status = status;
    }

    public void sendOffer() {
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO offers (idCustomers, idItems, price, status) VALUES (?, ?, ?, 0);";
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = ConnectionMaker.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, this.customerId);
            pstmt.setInt(2, this.itemId);
            pstmt.setInt(3, this.price);
            int databaseTest = pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL Exception" + e.getMessage());
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    logger.error("SQL Exception" + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error("SQL Exception" + e.getMessage());
                }
            }
        }
    }
}