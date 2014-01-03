package com.cmpsc221.jcal;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JCheckBox;

/**
 * Program: jCal
 * Description: Class that stores an array of EventGroups
*/
public class JCal {
 
    private JCheckBox cbxCalTree;
    private String title;                           // title of the jCal instance
    private ArrayList<EventGroup> userEventGroups;  // group of events
    private int id;
   
    // default constructor 
    public JCal()
    {
        id = 0;
        cbxCalTree = new JCheckBox(title);
        title = "myCal";
        userEventGroups = new ArrayList<EventGroup>();
    }
   
    // constructor to set the data members
    public JCal(String title)
    {
        id = 0;
        this.title = title;
        cbxCalTree = new JCheckBox(title);
        userEventGroups = new ArrayList<EventGroup>();
    }
   
    public ArrayList<Event> getEventsOnDate(Calendar dateToFind)
    {
        ArrayList<Event> returnList = new ArrayList<Event>();
        for ( int i = 0; i < userEventGroups.size(); i++ )
        {
            for ( int x = 0; x < userEventGroups.get(i).getEvent().size(); x++ )
            {
                Calendar cal = userEventGroups.get(i).getEvent().get(x).getStart();
                if ( cal.get(Calendar.YEAR) == dateToFind.get(Calendar.YEAR) && cal.get(Calendar.MONTH) == dateToFind.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) == dateToFind.get(Calendar.DAY_OF_MONTH) )
                {
                    returnList.add(userEventGroups.get(i).getEvent().get(x));
                }
            }
        }
        return returnList;
    }

    public ArrayList<Event> getAllEvents()
    {
        ArrayList<Event> returnList = new ArrayList<Event>();

        for ( int i = 0; i < userEventGroups.size(); i++ )
        {
            for ( int x = 0; x < userEventGroups.get(i).getEvent().size(); x++ )
            {
                returnList.add(userEventGroups.get(i).getEvent().get(x));
            }
        }
        return returnList;
    }
    /**
     *  Getters and Setters
     */
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
    
    public void addEventGroup(String title)
    {
        userEventGroups.add(new EventGroup(title));
    }
    public void editEventGroup(int egNum){
        userEventGroups.set(egNum, new EventGroup());
    }
    public void editEventGroupAddEvent(int egNum, Event newEvent)
    {
        userEventGroups.get(egNum).addEvent(newEvent);
    }
    public void printEventList(int egNum)
    {
        userEventGroups.get(egNum).printEventList();
    }
    
    public void removeEventGroup(int egNum)
    {
        userEventGroups.remove(egNum-1);
    }
    public void printEventGroupList()
    {
        int i =1;
        for (EventGroup temp : userEventGroups)
        {
            System.out.println(i+". "+temp.getTitle());
            i++; 
        }
    }

    // toString method to return the string of the data members
    public String toString()
    {
        return "Title: " + title + " amount in userEventGroups: " + userEventGroups.size();
    }
    
    public ArrayList<EventGroup> getEventGroup()
    {
        return userEventGroups;
    }
    
    public EventGroup getEventGroupAtIndex(int index)
    {
        return userEventGroups.get(index);
    }
    
    
    
    public boolean isCbxIsSelected() 
    {
        return cbxCalTree.isSelected();
    }

    public void setCbxIsSelected(boolean cbxIsSelected) 
    {
        cbxCalTree.setSelected(cbxIsSelected);
    }                                

    public JCheckBox getCbxCalTree() {
        return cbxCalTree;
    }

    public void setCbxCalTree(JCheckBox cbxCalTree) {
        this.cbxCalTree = cbxCalTree;
    }
    
     
}
