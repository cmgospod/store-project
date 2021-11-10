package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


public class ReviewOwned {

    private static final Logger logger = Logger.getLogger("mts");


    public void reviewOwnedLoop(int idCustomer) {
        PreparedStatement pstmt = null;
        String SQL = "SELECT name FROM items WHERE owner=?;";
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = ConnectionMaker.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, idCustomer);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("You have a " + rs.getString(1));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            }
        }

    }