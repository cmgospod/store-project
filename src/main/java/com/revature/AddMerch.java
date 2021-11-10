package com.revature;

public class AddMerch {

    public static void addMerchFlow(String newItem) {
        Item item = new Item(-1,newItem);
        item.upload();
    }
}
