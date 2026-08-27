/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progassignment;

/**
 *
 * @author ammaa
 */
/**
 * Part 1: Registration and Login Feature
 */
  public class Login{
// Private instance variables to store user data securely
private String registeredUserName;
private String registeredPassword;
private String registeredCellPhoneNumber;
  

/**
 * CheckUserName: ensures that any username contains an underscore (_) and is no more than 5 characters long.
 * @param username The username input to check
 * @return boolean True if valid, false otherwise
 */
public boolean checkUserName(String username) {
    if (username != null && username.contains("_") && username.length() <= 5) {
        return true;
    }
    return false;
    }


/** checkPasswordComplexity: ensures passwords meet complexity rules:
 * At least 8 characters long, contain a capital letter, a number, and a special character).
 * @param password The Password input to check
 * @return boolean True if criteria are met, false otherwise
 */
public boolean checkPasswordComplexity(String password) {
    if (password == null || password.length() < 8) {
        return false;
    }
// Password validation checks
boolean hascapital = false;
boolean hasNumber = false;
boolean hasSpecial = false;

// Loop
for(int i=0; i < password.length(); i++) {
    char ch = password.charAt(i);
    if (Character.isUpperCase(ch)) {
        hascapital = true;
        } else if (Character.isDigit(ch)){
        hasNumber = true;
        } else if (!Character.isLetterOrDigit(ch)) {
        hasSpecial = true;
        }
    }
    return hascapital && hasNumber && hasSpecial;
    }
 /**
  * CheckCellPhoneNumber: Validates South African cell phone numbers using RegEx.
  * Citation / Reference: Oracle Java Regular Expressions Pattern Documentation.
  * @param cellPhoneNumber The cell phone string input
  * @return boolean True if formatted correctly with international code, false otherwise
  */ 
  public boolean checkCellPhoneNumber(String cellPhoneNumber) {
    // Regular expression matching international code +27 followed by 9 digits
    String regex = "^\\+27\\d{9}$";
    return cellPhoneNumber != null && cellPhoneNumber.matches(regex);
  }   

  /**
   * registerUser: Validates inputs and returns registration success or error messages.
   */
  public String registerUser(String username, String password, String cellPhoneNumber) {
    if (!checkUserName(username)) {
        return "Username is not correctly formatted; please ensure that yuor username contains an underscore and is no more than 5 characters in length.";
    }
    if (!checkPasswordComplexity(password)) {
        return "Password is not correctly formatted; please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
    }
    if (!checkCellPhoneNumber(cellPhoneNumber)) {
        return " Cell phone number is incorrectly formatted or does not meet the international/local requirements.";
    }
    // If all the checks pass, save the data to your instance variables and return success
    this.registeredUserName= username;
    this.registeredPassword= password;
    this.registeredCellPhoneNumber= cellPhoneNumber;

    return " The user has been successfully registered.";
  }
 /**
  * LoginUser: Verifies if login credentials match stored registration data.
   */ 
  public boolean loginUser(String username, String password) {
    if (this.registeredUserName != null && this.registeredPassword != null) {
        return this.registeredUserName.equals(username) && this.registeredPassword.equals(password);
    }
    return false;
  }
  /**
   * returnLoginStatus: Returns status messaging for success or failure.
   */
  public String returnLoginStatus(boolean isLoggedIn, String firstName, String lastName) {
      if (isLoggedIn){
          return "Welcome" + firstName + ", " + lastName + " it is great to see you again.";
      } else {
          return "Username or password is incorrect, please try again.";
      }
    }
    /**
     * Returns the registered cell phone number.
     * Added to resolve the IDE stylistic warning regarding an unread private field.
   */
  public String getRegisteredCellPhoneNumber(){
    return this.registeredCellPhoneNumber;
  }
}
