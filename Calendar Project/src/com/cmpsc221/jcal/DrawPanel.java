/*
 * Sample borrowed from 221 website
 * 
 */

package com.cmpsc221.jcal;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DrawPanel extends JPanel
{
    private JButton demoButton;
    JLabel demoLabel;
    
    
    public DrawPanel() {                      // set up graphics window
       super();                               // this can be omitted
       setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        int width = getWidth();              // width of window in pixels
        int height = getHeight();            // height of window in pixels
        super.paintComponent(g);              // call superclass to make panel display correctly

        // Drawing code goes here
        // Notice that you should call super.paintComponent method before you start drawing
        // Otherwise, what's been done in the super will cover your drawing
    }

    
    /**
    public static void main(String[] args) {
        DrawPanel panel = new DrawPanel();                            // window for drawing
        JFrame application = new JFrame();                            // the program itself
        
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set frame to exit
                                                                      // when it is closed
        application.add(panel);           

        application.setSize(500, 400);         // window is 500 pixels wide, 400 high
        application.setVisible(true);   
        
        
          
    }
    */
}
    