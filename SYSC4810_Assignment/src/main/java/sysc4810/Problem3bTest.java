package sysc4810;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Problem3bTest {

    @Test
    public void testInvalidLengthPassword()  {
        String userName = "pietro";
        String password = "Pietr!1";
        assertFalse(Problem3b.isValidPassword(userName, password));

        userName = "pietro";
        password = "PietroAdam11!";
        assertFalse(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testValidLengthPassword()  {
        String userName = "pietro";
        String password = "Pietr!11";
        assertTrue(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testNoUpperCasePassword(){
        String userName = "pietro";
        String password = "pietro1!";
        assertFalse(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testUpperCasePassword(){
        String userName = "pietro";
        String password = "Pietro1!";
        assertTrue(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testAllUpperCasePassword(){
        String userName = "pietro";
        String password = "PIETRO1!";
        assertFalse(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testOneLowerCasePassword(){
        String userName = "pietro";
        String password = "pIETRO1!";
        assertTrue(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testNoDigitPassword(){
        String userName = "pietro";
        String password = "Pietro!!";
        assertFalse(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testOneDigitPassword(){
        String userName = "pietro";
        String password = "Pietro1!";
        assertTrue(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testNoSpecialCharacterPassword(){
        String userName = "pietro";
        String password = "Pietro11";
        assertFalse(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testOneSpecialCharacterPassword(){
        String userName = "pietro";
        String password = "Pietro1!";
        assertTrue(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testUsernameMatchesPassword(){
        String userName = "Pietro1!";
        String password = "Pietro1!";
        assertFalse(Problem3b.isValidPassword(userName, password));
    }

    @Test
    public void testWeakPassword(){
        String userName = "pietro";
        String password = "123456";
        assertFalse(Problem3b.isValidPassword(userName, password));

        password = "password";
        assertFalse(Problem3b.isValidPassword(userName, password));

        password = "qwerty";
        assertFalse(Problem3b.isValidPassword(userName, password));
    }

}
