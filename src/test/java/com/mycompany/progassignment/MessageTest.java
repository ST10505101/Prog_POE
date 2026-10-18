/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.progassignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ammaa
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
/**
 * Unit tests for the Message class to ensure 100% code coverage and pass Mave/CI checks.
 */
@Test
    public void testMessageLengthSuccess() {
        // Test Case 1: Message under 250 characters
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }
@Test
    public void testMessageLengthFailure() {
        // Test failure when message exceeds 250 characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            sb.append("A");
        }
        Message msg = new Message(1, "+27718693002", sb.toString());
        String result = msg.checkMessageLength();
        assertTrue(result.contains("exceeds 250 characters by 10"));
    }
@Test
    public void testRecipientCellSuccess() {
        // Test Case 1 Recipient: Valid international code format
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured", msg.checkRecipientCell());
    }
@Test
    public void testRecipientCellFailure() {
        // Test Case 2 Recipient: Format lacking proper international requirements/constraints
        Message msg = new Message(2, "08575975889", "Hi Keegan, did you receive the payment?");
        String result = msg.checkRecipientCell();
        assertTrue(result.contains("incorrectly formatted") || result.contains("does not contain"));
    }
@Test
    public void testMessageHash() {
        // Test Case 1 Hash Verification
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.createMessageHash();
        assertNotNull(hash);
        // Validates that the hash formats correctly with the ID prefix, message number index, and capitalized words (HITONIGHT)
        assertTrue(hash.endsWith(":0:HITONIGHT"));
    }
@Test
    public void testMessageIDGenerated() {
        // Verifies 10-digit Message ID creation
        Message msg = new Message(1, "+27718693002", "Test message");
        assertTrue(msg.checkMessageID());
    }
@Test
    public void testSentMessageActions() {
        // Additional test data for Send, Disregard, and Store options
        Message msg = new Message(1, "+27718693002", "Test message");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
@Test
    public void testReturnTotalMessages() {
        // Verifies total messages counter (e.g., Num Messages 2)
        Message msg = new Message(2, "+27718693002", "Test message");
        assertEquals(2, msg.returnTotalMessagesSent());
    }
}






