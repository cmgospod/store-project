package com.revature;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class ReviewOffers {
    private Console console = System.console();
    private Scanner scanner = new Scanner(console.reader());

    private static final Logger logger = Logger.getLogger("mts");


    public void reviewOffersFlow() {
        ArrayList<Integer> keys = new ArrayList<Integer>();
        PreparedStatement pstmt = null;
        String SQL = "SELECT * FROM offers\nLEFT JOIN items\nON offers.idItems=items.idItems\nWHERE status=0;";
        ResultSet rs = null;
        Connection conn = null;
        try{
            conn = ConnectionMaker.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String s = rs.getString("name");
                Integer i = rs.getInt("price");
                keys.add(Integer.valueOf(rs.getString("idOffers")));
                System.out.println("There is an offer to buy the " + s + " for " + i);
            }
            System.out.println("Type the number of the offer you would like to decide on");
            int a = scanner.nextInt();
            Integer primary = keys.get(a - 1);
            System.out.println("y to accept or n to decline");
            Scanner viewer = new Scanner(console.reader());
            String decision = viewer.nextLine();
            if (decision.equals("y")){
                UpdateOffer updateOffer = new UpdateOffer(primary);
                updateOffer.acceptOffer();
            }
            else if (decision.equals("n")){
                UpdateOffer updateOffer = new UpdateOffer(primary);
                updateOffer.declineOffer();
            }
            else {
                System.out.println("Please take this seriously");
            }

        } catch (SQLException e) {
            logger.error("SQL or invalid input" + e.getMessage());
            System.out.println("Looks like something went wrong");
        }


}}