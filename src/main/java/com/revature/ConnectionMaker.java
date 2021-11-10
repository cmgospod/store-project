package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;



public class ConnectionMaker {
    private static final Logger logger = Logger.getLogger("mts");
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            logger.error("Driver Error" + e.getMessage());
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project0", "cmgospod", "dcU&@4Q=mk3VE46");
        } catch (Exception e) {
            logger.error("Connection Error" + e.getMessage());
        }
        return conn;

    }
}