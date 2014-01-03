/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmpsc221.jcal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JPanel;


public class GUI2 extends JApplet implements ActionListener, ItemListener{

    //private FlowLayout mainGridBagLayout;        //BorderLayout mainBorderLayout;
    private GridBagConstraints mainConstraints; 
    
    //**************************************************************************
    //West Objects 
    private JPanel pnlWestMain;
    
    //**************************************************************************
    //Center Objects 
    private JPanel pnlCenterMain;
    
    //**************************************************************************
    //East Objects 
    private JPanel pnlEastMain;
    
    @Override
    public void init()
    {
        JPanel main = new JPanel(null);
        
        pnlEastMain = new JPanel(new BorderLayout());
        pnlEastMain.setBounds(0, 0, 165, 500);
        pnlEastMain.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JPanel pnlEastUpper = new JPanel();
        pnlEastUpper.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlEastMain.add(pnlEastUpper, BorderLayout.NORTH);
        
        
        
        main.add(pnlEastMain);

        pnlCenterMain = new JPanel();
        pnlCenterMain.setBounds(165, 0, 495, 500);
        pnlCenterMain.setBorder(BorderFactory.createLineBorder(Color.black));
        main.add(pnlCenterMain);
            
        pnlWestMain =  new JPanel(); 
        pnlWestMain.setBounds(660, 0, 165, 500);
        pnlWestMain.setBorder(BorderFactory.createLineBorder(Color.black));
        main.add(pnlWestMain);
        
        add(main);
        
   
        

        
        

    }


    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}
