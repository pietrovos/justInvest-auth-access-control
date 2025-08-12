package sysc4810;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args)  {


        //Creating frame
        JFrame frame = new JFrame("User Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        //Creating Login button
        JButton loginButton = new JButton("Login In");

        //Creating Sign up Button
        JButton signupButton = new JButton("Sign Up");

        //Adding buttons to frame and making frame visible
        frame.add(loginButton);
        frame.add(signupButton);
        frame.setVisible(true);

        //If pressing on login button, Close current frame and call LoginInterface
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Problem4a.main(args);
            }


        });

        //If pressing on Sign Up button, Close current frame and call SignUpInterface
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Problem3a.main(args);

            }
        });
    }}
