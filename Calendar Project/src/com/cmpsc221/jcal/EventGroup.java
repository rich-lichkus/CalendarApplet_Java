package com.cmpsc221.jcal;

/*
 * Developers:
 */


import java.util.ArrayList;
import javax.swing.JCheckBox;

/**
 * Program: jCal
 * Description: Class that stores multiple events in one grouping
*/
public class EventGroup{
    
    private JCheckBox cbxEGCalTree;
    private String title;                   // title of the eventGroup
    private ArrayList<Event> userEventList; // arrayList of the event list
    private int id;
   
    // default initializer 
    public EventGroup()
    {
        id = 0;
        this.title = " ";
        cbxEGCalTree = new JCheckBox(title);
        userEventList = new ArrayList<Event>();
    }
   
    // constructor to set all the data members 
    public EventGroup(String title)
    {
        id = 0;
        this.title = title;
        cbxEGCalTree = new JCheckBox(title);
        userEventList = new ArrayList<Event>();
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean idIsSet() {
        return (id != 0);
    }
    
    /**
     * Add, Edit, Remove
     * 
     */
    
    public void addEvent(Event newEvent)
    {        
        userEventList.add(newEvent);
    }

    public void removeEvent(int indexP1)
    {
        userEventList.remove(indexP1-1);
    }
    public void editEvent()
    {
        //userEventList.get(numEvent).
    }
    public ArrayList<Event> getEvent()
    {
        return userEventList;
    }
    public void printEventList()
    {
        int i =1;
        for (Event temp : userEventList)
        {
            System.out.println(i+". "+temp.eventTitle);
            i++;
        } 
    }

    // toString method to return the string form of all data members
    public String toString()
    {
        return "Title: " + title + " userEventList size: " + userEventList.size();
    }
    
    
    
    
    //GUI
    // - Box background color
    // - Event text color
    // - Event text font

    public boolean isCbxIsSelected() {
        return cbxEGCalTree.isSelected();
    }

    public void setCbxIsSelected(boolean cbxIsSelected) {
        cbxEGCalTree.setSelected(cbxIsSelected);
    }

    public JCheckBox getCbxEGCalTree() {
        return cbxEGCalTree;
    }

    public void setCbxEGCalTree(JCheckBox cbxEGCalTree) {
        this.cbxEGCalTree = cbxEGCalTree;
    }

}
