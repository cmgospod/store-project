package com.revature;

import java.io.Console;
import java.util.Scanner;

public class EmployeeLoop {
    public static void carryOn(String input) {
        switch (input) {
            case "a":
                Console console = System.console();
                Scanner scanner = new Scanner(console.reader());
                System.out.println("What would you like to add?");
                String newItem = scanner.nextLine();
                AddMerch.addMerchFlow(newItem);
                break;
            case "r":
                RemoveMerch removeMerch = new RemoveMerch();
                removeMerch.removeMerchFlow();
                break;
            case "o":
                ReviewOffers reviewOffers = new ReviewOffers();
                reviewOffers.reviewOffersFlow();
                break;
            case "p":
                ReviewPayments.reviewPaymentsFlow();
                break;
            default:
                System.out.println("Try again");
                break;

        }
    }

}