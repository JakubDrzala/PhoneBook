package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton submitButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private String login;
    private String password = "";
    public boolean exit = false;

    public Login() {
        setContentPane(contentPane);
        setSize(200,200);
        setResizable(false);
        setModal(true);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit = true;
                dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });
    }

    private void onSubmit() {
        // add your code here
        login = textField1.getText();
        char[] passwordInChars = passwordField1.getPassword();
        for (char letter: passwordInChars) {
            password += letter;
        }
        dispose();
    }
    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }
}
