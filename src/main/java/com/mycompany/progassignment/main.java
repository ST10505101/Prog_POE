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

    System.out.println("==========================");
    System.out.println("  QUICKCHAT REGISTRATION    ");    
    System.out.println("==========================");

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

String regResult= loginSystem.registerUser(username, password, cellPhoneNumber);
if (regResult.contains("Successfully registered")){
    System.out.println("User successfully registered");
}else{
    System.out.println(regResult);
}

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

scanner.close();
    } 
 }


