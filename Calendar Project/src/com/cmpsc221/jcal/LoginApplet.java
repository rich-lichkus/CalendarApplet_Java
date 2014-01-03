/*
 * Developer: Richard Lichkus
 * 
 */
package com.cmpsc221.jcal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginApplet extends JApplet implements ActionListener
{
    
    //Login
    private JPanel pnlLogin;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JTextField txtPassword;
    private JButton btnLogin;
    private JLabel lblError;
    private JcalSQL database;
    
    @Override
    public void init()
    {
        database = new JcalSQL();
        JPanel pnlMain = new JPanel(null);
        JPanel pnlLogin = new JPanel(new GridLayout(3,2));
        pnlLogin.setBounds(400,250,200,100);
        pnlLogin.setBorder(BorderFactory.createLoweredBevelBorder());
        
        lblUsername = new JLabel("Username:");
        lblUsername.setHorizontalAlignment(JLabel.CENTER); 
        txtUsername = new JTextField();
        lblPassword = new JLabel("Password:");
        lblPassword.setHorizontalAlignment(JLabel.CENTER);
        txtPassword = new JTextField();
        btnLogin = new JButton("Login");
        lblError = new JLabel("Try again.");
        lblError.setVisible(false);
        
        btnLogin.addActionListener(this);
        
        pnlLogin.add(lblUsername);
        pnlLogin.add(txtUsername);
        pnlLogin.add(lblPassword);
        pnlLogin.add(txtPassword);
        pnlLogin.add(btnLogin);
        pnlLogin.add(lblError);

        pnlMain.add(pnlLogin);
        add(pnlMain);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
    
    }
    
    @Override 
    public void actionPerformed(ActionEvent e)
    {       
        if(e.getSource() == btnLogin)
        {
            if (database.verifyUser(txtUsername.getText(), txtPassword.getText()))
            {
                //Load Applet Page
                lblError.setVisible(true);
                lblError.setText("Welcome!");
                
            }
            else
            {
                lblError.setVisible(true);
            }
                
            //System.out.println(txtUsername.getText());
        }
        
    }
}
