package com.revature;

import java.io.Console;
import java.util.Scanner;


public class CustomerLoop {
    private int idCustomer;
    private Console console = System.console();
    private Scanner scanner = new Scanner(console.reader());

    public CustomerLoop (int idCustomer){
        this.idCustomer = idCustomer;
    }

    public void carryOn() {
        System.out.println("Welcome! Type \"s\" to view items on sale, \"o\" to review your items owned, or \"p\" to view outstanding payments");
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "s":
                ViewItems viewItems = new ViewItems(this.idCustomer);
                viewItems.viewItemsLoop();
                break;
            case "o":
                ReviewOwned reviewOwned = new ReviewOwned();
                reviewOwned.reviewOwnedLoop(this.idCustomer);
                break;
            case "p":
                ReviewDebts reviewDebts = new ReviewDebts();
                reviewDebts.reviewDebtsLoop();
                break;
            default:
                System.out.println("Try again");
                break;

        }
    }

}