package com.revature;

import javax.print.DocFlavor;
import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveMerch {
    private Console console = System.console();
    private Scanner scanner = new Scanner(console.reader());

    public void removeMerchFlow() {
        ArrayList<Integer> keys = new ArrayList<Integer>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM items WHERE owner IS NULL;";
        try {
            conn = ConnectionMaker.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
        }
         catch (SQLException e) {
             e.getMessage();
         }
        finally {

            try {

                while (rs.next()) {
                    String s = rs.getString("name");
                    keys.add(Integer.valueOf(rs.getString("idItems")));
                    System.out.println(s);
                }

            } catch (SQLException e) {
                e.getMessage();
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
        System.out.println("Type the number of the item you would like to remove");
        try {
            int a = scanner.nextInt();
            Integer primaryKey = keys.get(a - 1);
            Connection conne = null;
            PreparedStatement state = null;
            String sequel = "DELETE FROM items WHERE idItems=?";
            conne = ConnectionMaker.getConnection();
            state = conne.prepareStatement(sequel);
            state.setInt(1, primaryKey);
            state.executeUpdate();
            System.out.println("Merchandise successfully removed");
        }
        catch (Exception e) {
            System.out.println("Invalid");
            }
        }
}