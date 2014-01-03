package com.cmpsc221.jcal;

import java.util.ArrayList;
/**
 * Program: jCal
 * Description: Class that stores attendee information for an event
 */
public class Attendees {
    
    private ArrayList<Contacts> contactList; // list of contacts for the attendee
    private ArrayList<Boolean> confirm;      // list of confirmations for the corresponding Contact
   
    // default constructor
    public Attendees()
    {
        contactList = new ArrayList<Contacts>();
        confirm = new ArrayList<Boolean>();
    }
    // constructor to set the data members of the class 
    public Attendees(ArrayList<Contacts> contactList, ArrayList<Boolean> confirm)
    {
    	this.contactList = contactList;
    	this.confirm = confirm;
    }

    public void add(Contacts contact, boolean confirmed)
    {
        contactList.add(contact);
        confirm.add(confirmed);
    }

    public void edit(int num, Contacts contact, boolean confirmed)
    {
        contactList.set(num-1, contact);
        confirm.set(num-1, confirmed);
    }

    public void remove(int num)
    {
        contactList.remove(num-1);
        confirm.remove(num-1);
    }

    // getter/setters
    public ArrayList<Contacts> getContactList()
    {
    	return contactList;
    }

    public ArrayList<Boolean> getConfirm()
    {
    	return confirm;
    }

    public void setContactList(ArrayList<Contacts> contactList)
    {
    	this.contactList = contactList;
    }

    public void setConfirm(ArrayList<Boolean> confirm)
    {
    	this.confirm = confirm;
    }

    // toString to return the data member information to a string
    public String toString()
    {
    	String returnString = "";

    	for ( int i = 0; i < contactList.size(); i++ )
    	{
    		returnString += contactList.get(i).toString();
    		if ( confirm.get(i) )
    		{
    			returnString += " is confirmed";
    		}
    		else
    		{
    			returnString += " is not confirmed";
    		}
    		returnString += "\n";
    	}

    	return returnString;
    }
}
