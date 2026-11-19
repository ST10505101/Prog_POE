/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progassignment;

/**
 *
 * @author ammaa
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private String sender;
    private String flag;

/**
 * constructor to initialize the message with its sequence number, recipient, and text.
 */
public Message(int messageNumber, String sender, String recipientCell, String messageText, String flag) {
        this.messageNumber = messageNumber;
        this.sender = sender;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.flag = flag;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
    public Message(int messageNumber, String recipientCell, String messageText) {
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
    long id = 1000000000L + (long)(rand.nextDouble() *9000000000L);
    return String.valueOf(id);
}
 //Ensures the message ID is not more than 10 characters 
 public boolean checkMessageID(){
    return this.messageID != null && this.messageID.length() <=10;
 }
 // Validates that the recipient cell starts with an international code and is <=12 characters
    public String checkRecipientCell(){
        if(this.recipientCell != null 
            && this.recipientCell.startsWith("+")
            && this.recipientCell.length() <= 12){
            return "Cell phone number successfully captured";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again."; 
        }
    }
    // validates that the message text does not exceed 250 characters.
    public String checkMessageLength(){
        if (this.messageText != null && this.messageText.length()<= 250){
            return "Message ready to send.";
        }else{
            int excess = this.messageText.length() -250;
            return "Message exceeds the maximum length by " + excess + " characters. Please shorten your message.";
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
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return (idPrefix + ":" + (this.messageNumber - 1)+ ":" + firstWord + lastWord).toUpperCase();
    }
    // Handles choice to send, store, or disregard
    public String sentMessage(int choice){
        switch (choice){
            case 1:
                return "Message successfully sent.";
                case 2:
                    return "Press 0 to delete the message.";
                    case 3:
                        return "Message successfully stored.";
                        default:
                            return "Invalid option selected.";
        }
    }
// Returns all message details
public String printMessages(){
    return "Message ID:" + this.messageID + "\n"+
            "Message Hash:" + this.messageHash + "\n"+
            "Recipient:" + this.recipientCell + "\n"+
            "Message:" + this.messageText;
    }
// Returns total number of messages sent
public int returnTotalMessagesSent(){
    return this.messageNumber;
    }
    //Stores the message in a JSon file
    public void storeMessage(){
        String jsonContent = "{\n" +
                " \"messageID\": \"" + this.messageID + "\",\n" +
                " \"messageHash\": \"" + this.messageHash + "\",\n" +
                " \"recipientCell\": \"" + this.recipientCell + "\",\n" +
                " \"messageText\": \"" + this.messageText + "\"\n"+
                "}";
                try (FileWriter file = new FileWriter("message_" + this.messageNumber + ".json")){
                file.write(jsonContent);
                file.flush();
    }catch (IOException e){
    System.out.println("Error saving message to JSON: " +e.getMessage());              
        }
    }
    // Dynamic lists for Part 3 message tracking
    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> disregardedMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();
    private static final List<String> messageHashes = new ArrayList<>();
    private static final List<String> messageIDs = new ArrayList<>();

    public void recordInArrays() {
        messageIDs.add(this.messageID);
        messageHashes.add(this.messageHash);

        if ("Sent".equalsIgnoreCase(this.flag)) {
            sentMessages.add(this);
        } else if ("Disregard".equalsIgnoreCase(this.flag)) {
            disregardedMessages.add(this);
        } else if ("Stored".equalsIgnoreCase(this.flag)) {
            storedMessages.add(this);
        }
    }

    public static void loadStoredMessagesFromJson() {
        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.startsWith("message_") && name.endsWith(".json"));
        if (files == null) return;

        for (File file : files) {
            try {
                String content = Files.readString(file.toPath());
                String id = extractJsonField(content, "messageID");
                String hash = extractJsonField(content, "messageHash");
                String snd = extractJsonField(content, "sender");
                String rec = extractJsonField(content, "recipient");
                String txt = extractJsonField(content, "messageText");

                boolean alreadyLoaded = storedMessages.stream().anyMatch(m -> m.messageID.equals(id));
                if (!alreadyLoaded && !txt.isEmpty()) {
                    Message loaded = new Message(storedMessages.size() + 1, snd, rec, txt, "Stored");
                    if (!hash.isEmpty()) {
                        loaded.messageHash = hash;
                    }
                    storedMessages.add(loaded);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String extractJsonField(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"(.*?)\"");
        Matcher matcher = pattern.matcher(json);
        return matcher.find() ? matcher.group(1) : "";
    }

    // a) Display sender and recipient of all stored messages
    public static String getStoredSendersAndRecipients() {
        if (storedMessages.isEmpty()) {
            return "No stored messages available.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m : storedMessages) {
            sb.append("Sender: ").append(m.sender)
              .append(" | Recipient: ").append(m.recipientCell).append("\n");
        }
        return sb.toString().trim();
    }

    // b) Display the longest message across all messages
    public static String getLongestMessageAcrossAll() {
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);
        if (all.isEmpty()) return "No messages found.";

        Message longest = all.get(0);
        for (Message m : all) {
            if (m.messageText.length() > longest.messageText.length()) {
                longest = m;
            }
        }
        return longest.messageText;
    }

    // c) Search for a message ID and display corresponding recipient and message
    public static String searchByMessageID(String targetID) {
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);

        for (Message m : all) {
            if (m.messageID.equals(targetID)) {
                return "Recipient: " + m.recipientCell + "\nMessage: " + m.messageText;
            }
        }
        return "Message ID not found.";
    }

    // d) Search for all messages sent or stored for a particular recipient
    public static String searchMessagesByRecipient(String recipientCell) {
        List<String> matches = new ArrayList<>();
        for (Message m : storedMessages) {
            if (m.recipientCell.equals(recipientCell)) {
                matches.add(m.messageText);
            }
        }
        for (Message m : sentMessages) {
            if (m.recipientCell.equals(recipientCell) && !matches.contains(m.messageText)) {
                matches.add(m.messageText);
            }
        }
        if (matches.isEmpty()) {
            return "No messages found for recipient " + recipientCell;
        }
        return String.join(" ", matches);
    }

    // e) Delete a message using the message hash
    public static String deleteMessageByHash(String targetHash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).messageHash.equalsIgnoreCase(targetHash)) {
                String removedText = storedMessages.get(i).messageText;
                storedMessages.remove(i);
                messageHashes.remove(targetHash);
                return "Message: \"" + removedText + "\" successfully deleted.";
            }
        }
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).messageHash.equalsIgnoreCase(targetHash)) {
                String removedText = sentMessages.get(i).messageText;
                sentMessages.remove(i);
                messageHashes.remove(targetHash);
                return "Message: \"" + removedText + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // Display full report of all sent messages (Main Menu option 2)
    public static String getSentMessagesReport() {
        if (sentMessages.isEmpty()) {
            return "No sent messages to display.";
        }
        StringBuilder report = new StringBuilder("=== SENT MESSAGES REPORT ===\n");
        for (Message m : sentMessages) {
            report.append("Message Hash: ").append(m.messageHash).append("\n")
                  .append("Recipient: ").append(m.recipientCell).append("\n")
                  .append("Message: ").append(m.messageText).append("\n")
                  .append("----------------------------\n");
        }
        return report.toString().trim();
    }

    // f) Display full report of all stored messages
    public static String getStoredMessagesReport() {
        if (storedMessages.isEmpty()) {
            return "No stored messages to display.";
        }
        StringBuilder report = new StringBuilder("=== STORED MESSAGES REPORT ===\n");
        for (Message m : storedMessages) {
            report.append("Message ID: ").append(m.messageID).append("\n")
                  .append("Message Hash: ").append(m.messageHash).append("\n")
                  .append("Sender: ").append(m.sender).append("\n")
                  .append("Recipient: ").append(m.recipientCell).append("\n")
                  .append("Message: ").append(m.messageText).append("\n")
                  .append("------------------------------\n");
        }
        return report.toString().trim();
    }
}
 

