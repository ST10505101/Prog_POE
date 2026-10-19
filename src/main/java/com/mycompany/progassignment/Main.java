/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progassignment;

/**
 *
 * @author ammaa
 */
import java.util.Scanner;
public class Main{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

        boolean isRegistered = false;

    System.out.println("==========================");
    System.out.println("  QUICKCHAT REGISTRATION    ");    
    System.out.println("==========================");
// Keep looping until registration is completely successful
while (!isRegistered){
    System.out.print(" Enter your first name: ");
    String firstName= scanner.nextLine();

    System.out.print("Enter your last name: ");
    String lastName= scanner.nextLine();

    System.out.print("Enter your username: ");
    String username= scanner.nextLine();

    System.out.print("Enter your password: ");
    String password= scanner.nextLine();

    System.out.print("Enter your South African cell phone number: ");
    String cellPhoneNumber= scanner.nextLine();

//Create an instance of your login class
Login loginSystem= new Login();

System.out.println("\n==========================");
System.out.println("    REGISTRATION RESULTS    ");
System.out.println("============================");
if (loginSystem.checkUserName(username)){
    System.out.println("Username successfully captured");
}else{
    System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
}
if (loginSystem.checkPasswordComplexity(password)){
    System.out.println("Password successfully captured");
}else{
    System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
}
if (loginSystem.checkCellPhoneNumber(cellPhoneNumber)){
    System.out.println("Cell phone number successfully captured");
}else{
    System.out.println("Cell phone number is not correctly formatted or does not match the international/local requirements.");
}

String regResult= loginSystem.registerUser(username, password, cellPhoneNumber);
System.out.println(regResult);
if (regResult.contains("successfully registered")) {
        isRegistered = true; 
System.out.println("\n==========================");
System.out.println("             LOGIN          ");
System.out.println("============================");

System.out.print("Enter username: ");
String loginUsername= scanner.nextLine();

System.out.print("Enter password: ");
String loginPassword= scanner.nextLine();

boolean isLoggedIn= loginSystem.loginUser(loginUsername, loginPassword);
String statusMessage= loginSystem.returnLoginStatus(isLoggedIn,firstName, lastName);
System.out.println(statusMessage);


//=========================================
//Part 2: Quickchat menu and messaging
//=========================================
if (isLoggedIn){
    System.out.println("\nWelcome to quickchat.");

    boolean running = true;
    int totalSent = 0;

    while(running){
        System.out.println("\n==========================");
        System.out.println("QUICKCHAT MAIN MENU");
        System.out.println("=============================");
        System.out.println("1) Send messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");
        System.out.print("Choose and option (1-3): ");

        if (!scanner.hasNextInt()){
            System.out.println("Invalid input. Please enter a number between 1 & 3."); 
            scanner.nextLine();
            continue;
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newLine

        switch (choice){
            case 1: 
            System.out.print("How messages do you wish to enter? ");
            int numMessages = scanner.nextInt();
            scanner.nextLine();

            for (int i=1; i<= numMessages; i++){
                System.out.println("\n---EnteringMessage " + i + " of " + numMessages + "---");

            System.out.print("Enter recipient cell phone number: ");
            String recipient = scanner.nextLine();

            System.out.print("Enter message text (max 250 characters): ");
            String text = scanner.nextLine();

            Message msg = new Message(i, recipient, text);

            //validate message length
            String lengthCheck = msg.checkMessageLength();
            System.out.println(lengthCheck);
            if (!lengthCheck.equals("Messageready to send.")){
                i--; // Return iteration
                continue;
            }

            //Validate recipient cell number
            String recipientCheck = msg.checkRecipientCell();
            System.out.println(recipientCheck);
            if(!recipientCheck.contains("successfully captured")){
                i--; // Retry iteration
                continue;
            }

            // Choose action
            System.out.println("\nChoose an action:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregrad Message");
            System.out.println("3) Store Message to send later");
            System.out.print("Enter option (1-3): ");

            int action = scanner.nextInt();
            scanner.nextLine();

            System.out.println(msg.sentMessage(action));

            if (action == 1) {
                totalSent++;
                System.out.println("\nMessage Details ---");
                System.out.println(msg.printMessages());
            }else if(action == 3) {
                msg.storeMessage();
            }
         }
         System.out.println("\nTotal messages processed:   "  + totalSent);
         break;

         case 2: 
         System.out.println("Coming Soon.");
         break;

         case 3:
            System.out.println("Exiting QuickChat. Goodbye!");
            running = false;
            break;

            default:
                System.out.println("Please choose a valid option (1-3).");
            }
        }
    }

}
scanner.close();
    } 
}
}



