package com.revature;

import java.io.Console;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Item {
    private int owner;
    protected String name;
    private static final Logger logger = Logger.getLogger("mts");

    public Item(int owner, String name)
    {
        this.owner = owner;
        this.name = name;
    }
    public void upload() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO items (owner, name) VALUES (null, ?)";
        try {
            conn = ConnectionMaker.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, this.name);
            int i = pstmt.executeUpdate();
            System.out.println("Item uploaded successfully!");
    }
            catch (SQLException e) {
                logger.error("SQL Exception" + e.getMessage());
            } finally {
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
                        e.getMessage();
                    }
                }
            }
        Console console = System.console();
        Scanner scanner = new Scanner(console.reader());
        System.out.println("Select \"a\" to add merchandise, \"r\" to remove it, \"o\" to review offers, or \"p\" to view payments");
        String employeeSelection = scanner.nextLine();
        EmployeeLoop.carryOn(employeeSelection);
    }
}
