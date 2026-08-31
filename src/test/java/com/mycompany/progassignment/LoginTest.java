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
public class LoginTest {
    
    public LoginTest() {
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
@Test
public void testCheckUserNameValid(){
  Login login= new Login();
  assertTrue(login.checkUserName("kyl_1"), "Username should be valid");
}
@Test
public void testCheckUserNameInvalid(){
    Login login = new Login();
    assertFalse(login.checkUserName("kyleabceed"), "Username should be invalid");
}
@Test
public void testCheckPasswordComplexityValid(){
    Login login = new Login();
    assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"), "Password should pass complexity rules");
}
@Test
public void testCheckPasswordComplexityInvalid(){
    Login login = new Login();
    assertFalse(login.checkPasswordComplexity("password"), "Password should fail complexity rules");
}
@Test
public void testRegisterUser(){
    Login login = new Login();
    String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27831234567");
    assertNotNull(result, "Registration should return a status message");
}  
}
