package org.example;

import javax.swing.*;
import java.awt.event.*;

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
        textField1.setText("Cieszyn16");
        passwordField1.setText("haslo");

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
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if("Enter".equals(KeyEvent.getKeyText(e.getKeyChar()))){
                    passwordField1.requestFocus();
                }
            }
        });
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
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
