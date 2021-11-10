package com.revature;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class ViewItems {
    private int idCustomer;

    private static final Logger logger = Logger.getLogger("mts");


    public ViewItems (int idCustomer){
        this.idCustomer = idCustomer;
    }

    public void viewItemsLoop() {
        ArrayList<Integer> keys = new ArrayList<Integer>();
        Console console = System.console();
        Scanner scanner = new Scanner(console.reader());
        PreparedStatement pstmt = null;
        String SQL = "SELECT * FROM items WHERE owner IS NULL;";
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = ConnectionMaker.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            logger.error("SQL Exception" + e.getMessage());
        } finally {

            try {

                while (rs.next()) {
                    String s = rs.getString("name");
                    keys.add(Integer.valueOf(rs.getString("idItems")));
                    System.out.println(s);
                }

            } catch (SQLException e) {
                logger.error("SQL Exception" + e.getMessage());
            }
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
        System.out.println("Type the number of the item you would like to purchase, or type \"back\"");
        String input = scanner.nextLine();
        if (input.equals("back")){
            CustomerLoop customerLoop = new CustomerLoop(idCustomer);
            customerLoop.carryOn();
        }
        else {
            try{
                int a = Integer.parseInt(input);
                Integer b = keys.get(a + 1);
                System.out.println("And how much would you like to offer?");
                String offerAmount = scanner.nextLine();
                int numericOffer = Integer.parseInt(offerAmount);
                Offer offer = new Offer(idCustomer, b, numericOffer, 0);
                System.out.println("Your offer has been sent!");

            }
            catch (Exception e) {
                System.out.println("Invalid");
            }
    }}}