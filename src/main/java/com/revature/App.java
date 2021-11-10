package com.revature;


import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class App {
    private static final Logger logger = Logger.getLogger("mts");

    public static void main(String[] args) {
        String employeePassword = "secure";
        Console console = System.console();
        Scanner scanner = new Scanner(console.reader());

        boolean GoOn = true;
        while (GoOn) {
            System.out.println("Welcome! Are you an employee or a customer?");
            String TypeInput = scanner.nextLine().toLowerCase();
            if (TypeInput.equals("employee")) {
                System.out.println("Hello! Please enter the employee password.");
                String passwordInput = scanner.nextLine();
                if (passwordInput.equals(employeePassword)) {
                    System.out.println("Logging in now...");
                    System.out.println("Select \"a\" to add merchandise, \"r\" to remove it, \"o\" to review offers, or \"p\" to view payments");
                    String employeeSelection = scanner.nextLine();
                    EmployeeLoop.carryOn(employeeSelection);
                    break;
                } else {
                    System.out.println("Incorrect");
                    continue;
                }

            } else if (TypeInput.equals("customer")) {
                System.out.println("Please enter your username");
                String username = scanner.nextLine();
                System.out.println("Please enter your password, or type \"register\" if you don't have an account");
                String password = scanner.nextLine().toLowerCase();

                if (password.equals("register")) {
                    System.out.println("Please select a password");
                    String newPassword = scanner.nextLine();
                    Customer customer = new Customer(username, newPassword);
                    int idCustomer = customer.register();
                    CustomerLoop customerLoop = new CustomerLoop(idCustomer);
                    customerLoop.carryOn();
                } else {
                    Connection conn = null;
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String SQL = "SELECT idCustomers FROM customers WHERE name=? AND password=?";
                    try {
                        conn = ConnectionMaker.getConnection();
                        pstmt = conn.prepareStatement(SQL);
                        pstmt.setString(1, username);
                        pstmt.setString(2, password);
                        rs = pstmt.executeQuery();
                        if (rs != null) {
                            rs.next();
                            int idCustomer = rs.getInt(1);
                            CustomerLoop customerLoop = new CustomerLoop(idCustomer);
                            customerLoop.carryOn();
                        } else {
                            System.out.println("Incorrect username or password");
                        }
                    } catch (SQLException e) {
                        System.out.println("Incorrect username or password");
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
                                logger.error("SQL Exception" + e.getMessage());
                            }
                        }
                    }
                }

            } else {
                System.out.println("Please check your spelling");
            }
        }
    }
}