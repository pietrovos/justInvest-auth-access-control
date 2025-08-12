package sysc4810;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class Problem2cTest {
    private static final String PASSWORD_FILE = "passwd.txt";

    //Clearing Password file before each test
    @BeforeEach
    void setup() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE))) {
            writer.write("");
        }
    }

    //Testing adding a user
    @Test
    void testAddUser() throws Exception {
        Problem2c.addUser("PietroAdam", "PietroAdam1!", "client");
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            String record = reader.readLine();
            assertNotNull(record);
            String[] parts = record.split("\\|");
            assertEquals(4, parts.length);
            assertEquals("PietroAdam", parts[0]);
        }
    }

    //Testing retrieving user record
    @Test
    void testGetUserRecord() throws Exception {
        Problem2c.addUser("PietroAdam", "PietroAdam1!", "client");
        String[] record = Problem2c.getUserInfo("PietroAdam");
        assertNotNull(record);
        assertEquals("SashaKim", record[0]);
    }

    //Testing Validation of valid login credentials
    @Test
    void testValidateLoginCorrect() throws Exception {
        Problem2c.addUser("PietroAdam", "PietroAdam1!", "client");
        assertTrue(Problem2c.validateLogin("PietroAdam", "PietroAdam1!"));
    }

    //Testing Validation of invalid login credentials
    @Test
    void testValidateLoginIncorrect() throws Exception {
        Problem2c.addUser("PietroAdam", "PietroAdam1!", "client");
        assertFalse(Problem2c.validateLogin("PietroAdam", "PietroAdam"));
    }

    //Testing validation of non-existent user
    @Test
    void testValidateLoginNonExistentUser() throws Exception {
        assertFalse(Problem2c.validateLogin("idontexist", "Wownice1!"));
    }

    //Testing Getter for user role
    @Test
    void testGetUserRole() throws Exception {
        Problem2c.addUser("PietroAdam", "PietroAdam!", "client");
        assertEquals( "client", Problem2c.getUserRole("PietroAdam"));

    }


}
