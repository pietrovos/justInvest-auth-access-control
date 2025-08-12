package sysc4810;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Problem3a {
    public static void main(String[] args) {

        //Creating frame
        JFrame frame = new JFrame("User Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        //Creating Username label + input box
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        //Creating Password Label + input box
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        //Creating Confirm Password Label + input box
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField();

        //Creating Sign up button + Status label (diff colour depending on valid or invalid entries)
        JButton signupButton = new JButton("Sign Up");
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);

        //adding everything to the frame
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(confirmPasswordLabel);
        frame.add(confirmPasswordField);
        frame.add(signupButton);
        frame.add(statusLabel);

        //ActionListener creation for signupbutton
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Retrieving user input into back-end attributes
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                //Checking if any of the fields have been left empty
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    statusLabel.setText("All fields are required.");
                    statusLabel.setForeground(Color.RED);
                    return;
                }
                //Checking if password input matches confirm password input
                if (!password.equals(confirmPassword)) {
                    statusLabel.setText("Passwords do not match.");
                    statusLabel.setForeground(Color.RED);
                    return;
                }

                //checking if password is is valid
                if (!Problem3b.isValidPassword(username, password)) {
                    statusLabel.setText(Problem3b.getMessage());
                    statusLabel.setForeground(Color.RED);
                    return;
                }



                try {
                    //I made it so no two users can have the same username, helped me test my application
                    String[] existingUser = Problem2c.getUserInfo(username);
                    if (existingUser != null) {
                        statusLabel.setText("Username already exists.");
                        statusLabel.setForeground(Color.RED);
                        return;
                    }

                    //Successful registration, user redirected to main page where he can choose to sign up another account or log in
                    Problem2c.addUser(username, password, "CLIENT");
                    statusLabel.setText("User registered successfully!");
                    statusLabel.setForeground(Color.GREEN);
                    frame.dispose();
                    Main.main(args);

                    //Intellij forced my to put these, tbh im not sure
                } catch (IOException ex) {
                    ex.printStackTrace();
                    statusLabel.setText("Error during registration.");
                    statusLabel.setForeground(Color.RED);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    statusLabel.setText("Error during registration.");
                    statusLabel.setForeground(Color.RED);
                }
            }
        });

        //Make the frame visible
        frame.setVisible(true);
    }
}
