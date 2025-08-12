package sysc4810;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Problem2c {
    private static final String PASSWORD_FILE = "passwd.txt";
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;
    private static final int ITERATION_COUNT = 100000;

    //https://docs.oracle.com/javase/7/docs/api/java/security/SecureRandom.html
    private static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[SALT_LENGTH];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

   //https://hackernoon.com/understanding-the-pbkdf2-algorithm-with-a-java-service-example
    private static String hashPassword(String password, String salt) throws Exception {
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, ITERATION_COUNT, HASH_LENGTH * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }


    public static void addUser(String username, String password, String role) throws Exception {

        //Generate salt, then hash(pass,salt)
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);

        //How each user record will be formated:
        String userInfo = String.format("%s|%s|%s|%s\n", username, salt, hashedPassword, role);

        //Write to password file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE, true))) {
            writer.write(userInfo);
        }
    }
    //https://stackoverflow.com/questions/71531236/how-do-make-a-login-function-using-bufferedreader
    public static String[] getUserInfo(String username) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(username)) {
                    return parts;
                }
            }
        }
        return null;
    }

    //Validating user login, checking if stored hash is the same as the hash of the user input
    public static boolean validateLogin(String username, String password) throws Exception {
        //Check if the username even exists
        String[] userInfo = getUserInfo(username);
        if (userInfo == null) {
            return false;
        }

        //salt stored in password file
        String salt = userInfo[1];
        //Hashed password stored in password file
        String storedHash = userInfo[2];
        //Take userinputed password, with the salt in the password file and hash it
        String hashedPassword = hashPassword(password, salt);

        //Validate
        return storedHash.equals(hashedPassword);
    }

    //Method to role associated to user's record
    public static String getUserRole(String username) throws IOException {
        String[] userInfo = getUserInfo(username);
        if (userInfo != null) {
            return userInfo[3];
        }
        return null;
    }


}
