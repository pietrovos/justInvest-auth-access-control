package sysc4810;

import java.util.List;

public class Problem3b {

    //https://en.wikipedia.org/wiki/List_of_the_most_common_passwords
    //List of Commonly used passwords
    private static final List<String> WEAK_PASSWORDS = List.of(
            "123456", "123456789", "12345", "qwerty", "password", "12345678", "111111", "123123", "1234567890",
            "1234567", "qwerty123", "000000", "1q2w3e", "aa12345678", "abc123", "password1", "1234", "qwertyuiop", "123321", "password123"
    );

    private static String message;

    //Password constraint checker
    public static boolean isValidPassword(String username, String password) {

        //Checking if password is [8-12] characters
        if (password.length() < 8 || password.length() > 12) {
            message = "Password must be between 8 and 12 characters.";
            System.out.println("Password must be between 8 and 12 characters.");
            return false;
        }

        //Checking if password has at least one uppercase
        if (!password.matches(".*[A-Z].*")) {
            message = "Password must contain at least one uppercase letter.";
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }

        //Checking if password has at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            message = "Password must contain at least one lowercase letter.";
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }

        //Checking if password as at least a digit
        if (!password.matches(".*\\d.*")) {
            message = "Password must contain at least one digit.";
                    System.out.println("Password must contain at least one digit.");
            return false;
        }

        //Checking if password has at least one special character
        if (!password.matches(".*[!@#$%&*].*")) {
            message = "Password must contain at least one special character.";
            System.out.println("Password must contain at least one special character (!, @, #, $, %, *, &).");
            return false;
        }

        //Checking if password is the same as username
        if (password.equals(username)) {
            message = "Password cannot be the same as the username.";
            System.out.println("Password cannot be the same as the username.");
            return false;
        }

        //Checking if password is part of my list of weak passwords
        if (WEAK_PASSWORDS.contains(password)) {
            message = "Password is too weak. Please choose a stronger password.";
            System.out.println("Password is too weak. Please choose a stronger password.");
            return false;
        }

        //If all constraints are met, it is a valid password
        return true;
    }

    //Getter for messages to be used in the front-end
    public static String getMessage() {
        return message;
    }
}

