package com.revature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CustomerTest {
    Customer customer = new Customer("roy", "ooo");

    @Test
    public void shouldAnswerWithTrue() {
        assertEquals(-1, customer.register());
    }
}