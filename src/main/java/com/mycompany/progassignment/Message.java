/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progassignment;

/**
 *
 * @author ammaa
 */
import java.io.FilterWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The message class handles individual message validation,
 * ID generation, hash creation, and JSon file storage for QuickChat messages.
 */
public class Message {
    private String recipientCell;
    private String messageText;
    private String messageID;
    private String messageHash;
    private int messageNumber;

/**
 * constructor to initialize the message with its sequence number, recipient, and text.
 */
public Message(int messageNumber, String recipientCell, String messageText){
    this.messageNumber = messageNumber;
    this.recipientCell = recipientCell;
    this.messageText = messageText;
    this.messageID = generateMessageID();
    this.messageHash = createMessageHash();
}

/**
 * Helper method to generate a random 10-digit message ID for tracking.
 */
private String generateMessageID(){
    Random rand = new Random();
    long id = 1000000000L + (long)(rand.nextDouble()) *9000000000L;
    return String.valueOf(id);
}
 //Ensures the message ID is not more than 10 characters 
 public boolean checkMessageID(){
    return this.messageID != null && this.messageID.length() <=10;
 }
 // Validates that the recipient cell starts wuth an international code andis <=10 characters
 public String checkRecipientCell(){
    if(this.recipientCell != null 
        && (this.recipientCell.startsWith("+")|| this.recipientCell.matches("^\\d+$"))
    && this.recipientCell.length()<=10){
        return "Cell phone number successfully cpatured";
    }else{
        return "Cell phone number is incorrectly formatted or does not contain an international code. PLease correct the number and try again."
;    }
    }
    // validates that the message text does not exceed 250 characters.
    public String checkMessageLength(){
        if (this.messageText != null && this.messageText.length()<= 250){
            return "Message ready to send.";
        }else{
            int excess = this.messageText.length() -250;
        }
    }
    //Creates and returns the Message Hash
    public String createMessageHash(){
        if (this.messageID == null || this.messageText == null || this.messageText.trim().isEmpty()){
            return "";
        }
        String idPrefix = this.messageID.substring(0, 2);
        String [] words = this.messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        return (idPrefix + ":" + (this.messageNumber - 1)+ ":" + firstWord + lastWord).topUpperCase();
    }
 }

