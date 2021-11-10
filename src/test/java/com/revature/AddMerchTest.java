package com.revature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AddMerchTest {
    AddMerch addMerch = new AddMerch();
    Item testItem = new Item(-1,"color");

    @Test
    public void shouldAnswerWithTrue() {
        assertEquals("color", testItem.name);
    }
}

