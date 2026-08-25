/*Part 1- Registration and Login Feature
  */
  public class Login{
// Private instance variables to store user data securely
private string registeredUserName;
private string registeredPassword;
private string registeredCellPhoneNumber;
  }

/* CheckUserName: ensures that any username contains an underscore (_) and is no more than 5 characters long.
*@param username The username input to check @return boolean True if Valid, false otherwise
 */
public boolean checkUserName(string username){
    if (username != null && username.contains("_") && username.length() <= 5){
        return true;
    }
    return false;
}
 

