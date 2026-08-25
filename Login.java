/*Part 1- Registration and Login Feature
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
}

  




  

  