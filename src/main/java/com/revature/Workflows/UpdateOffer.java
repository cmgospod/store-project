package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateOffer {
    private int primary;

    public UpdateOffer(int primary) {
        this.primary = primary;
    }

    public void declineOffer() {
        PreparedStatement pstmt = null;
        String SQL = "UPDATE offers\nSET status=1\nWHERE idOffers=?;";
        Connection conn = null;
        try {
            conn = ConnectionMaker.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, this.primary);
            pstmt.executeUpdate();
            System.out.println("Offer declined");
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    public void acceptOffer() {
        PreparedStatement pstmt = null;
        String SQL = "UPDATE offers\nSET status=1\nWHERE idOffers=?;";
        Connection conn = null;
        try {
            conn = ConnectionMaker.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, this.primary);
            pstmt.executeUpdate();
            System.out.println("Offer Accepted");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

}