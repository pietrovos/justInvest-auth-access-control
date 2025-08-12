package sysc4810;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class Problem4a {
    public static void main(String[] args) {

        //Creating main frame
        JFrame frame = new JFrame("User Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        //Creating Username label + input box
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        //Creating Password label + input box
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        //Creating login button
        JButton loginButton = new JButton("Login");
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);

        //Adding it all to frame
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(statusLabel);

        //Login Button Action Listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Storing user input into back-end attributes
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                //Checking if username or password was left empty
                if (username.isEmpty() || password.isEmpty()) {
                    statusLabel.setText("All fields are required.");
                    statusLabel.setForeground(Color.RED);
                    return;
                }


                try {
                    //Storing if password is valid or not
                    boolean isValid = Problem2c.validateLogin(username, password);

                    //If password is valid
                    if (isValid) {
                        statusLabel.setText("Login successful!");
                        statusLabel.setForeground(Color.GREEN);

                        //Destroy frame
                        frame.dispose();

                        //Create a new frame, which will be the main page once logged in
                        JFrame welcomeFrame = new JFrame("Welcome");
                        welcomeFrame.setSize(800, 600);
                        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        welcomeFrame.setLocationRelativeTo(null);

                        //Creating Panel to add components later on
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        panel.setBackground(Color.WHITE);
                        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

                        //Display welcome message
                        JLabel welcomeLabel = new JLabel("Welcome to the JustInvest System!");
                        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
                        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                        //Displaying username that has logged in
                        JLabel usernameLabel = new JLabel("Hello, " + username);
                        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                        //Displaying role of user
                        String user_role = Problem2c.getUserRole(username);
                        JLabel roleLabel = new JLabel("Role: " + user_role);
                        roleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                        //adding it all to the panel, we will add the panel to the frame later
                        panel.add(welcomeLabel);
                        panel.add(Box.createRigidArea(new Dimension(0, 20)));
                        panel.add(usernameLabel);
                        panel.add(Box.createRigidArea(new Dimension(0, 10)));
                        panel.add(roleLabel);

                        //Initializing a roles attribute that stores all role keys and their values
                        Map<String, List<String>> roles = Problem1c.getRoles();

                        //Iterating through each role
                        for (Map.Entry<String, List<String>> entry : roles.entrySet()) {

                            //Storing the role that we're at in the iteration
                            String role_iterate = entry.getKey();
                            //Storing the permissions of the role we're at
                            List<String> permissions = entry.getValue();

                            //Eventually we will find the role that matches the user's role
                            if (role_iterate.equals(user_role)) {

                                //We will now iterate through all the permissions authorized by the user's role
                                System.out.println("Permissions for " + user_role + ":");

                                //Special condition: if user role is: "TELLER", they will only see their authorized actions between 9:00AM and 5:00PM
                                if (user_role.equals("TELLER") && !Problem1c.isBusinessHours()) {
                                    JLabel permissionDenied = new JLabel("Teller's can only access the system between 9:00AM and 5:00PM");
                                    permissionDenied.setFont(new Font("Arial", Font.PLAIN, 18));
                                    permissionDenied.setAlignmentX(Component.CENTER_ALIGNMENT);
                                    panel.add(permissionDenied);
                                }

                                //If not special condition: display the user's authorized actions
                                else {
                                    for (String permission : permissions) {
                                        JLabel permissionLabel = new JLabel(permission);
                                        permissionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                                        permissionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        panel.add(permissionLabel);
                                    }
                                }
                                break;
                            }

                        }

                        //Prompt user to pick which action he/she would like to perform
                        JLabel query = new JLabel("What action would you like to perform:");
                        query.setFont(new Font("Arial", Font.PLAIN, 18));
                        query.setAlignmentX(Component.CENTER_ALIGNMENT);
                        panel.add(query);


                        JTextField actionField = new JTextField();


                        panel.add(actionField);

                        welcomeFrame.add(panel);

                        //Make frame Visible
                        welcomeFrame.setVisible(true);
                    }
                    //If invalid credential we're entered
                    else {
                        statusLabel.setText("Invalid username or password.");
                        statusLabel.setForeground(Color.RED);
                    }
                }
                //again not sure, but needed
                catch (Exception ex) {
                    ex.printStackTrace();
                    statusLabel.setText("Error during login.");
                    statusLabel.setForeground(Color.RED);
                }
            }
        });

        //make frame visible
        frame.setVisible(true);
    }
}
