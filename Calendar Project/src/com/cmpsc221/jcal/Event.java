
package com.cmpsc221.jcal;
/**
* Program: jCal
* Description: Class that stores simple event data
*/
import java.util.Calendar;
public class Event {
    
    protected boolean privateEvent;		// All events are defualt private until manually changed
    protected String eventTitle;		// event title
    protected String eventNotes;		// notes for the event
    protected boolean isBusy;			// determines if you are busy during this time
    protected boolean repeating;		// determines if the event is repeating
    protected int repeatingRate;		// amount of days after the intial set date to repeat the event
    protected int reminderTime;			// minutes to remind the user in advance for an event
    protected Calendar start;       // time of event start


    // constructor to store all data members
    public Event()
    {
    	this.privateEvent = true;
    	this.eventTitle = "";
    	this.eventNotes = "";
    	this.isBusy = true;
    	this.repeating = false;
    	this.repeatingRate = 0;
    	this.reminderTime = 0;
        this.start = Calendar.getInstance();
    }
    
    // constructor to store all data members
    public Event(boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime, Calendar start)
    {
    	this.privateEvent = privateEvent;
    	this.eventTitle = eventTitle;
    	this.eventNotes = eventNotes;
    	this.isBusy = isBusy;
    	this.repeating = repeating;
    	this.repeatingRate = repeatingRate;
    	this.reminderTime = reminderTime;
        this.start = start;
    }

    // getters/setters
    public boolean getPrivateEvent()
    {
    	return privateEvent;
    }

    public String getEventTitle()
    {
    	return eventTitle;
    }

    public String getEventNotes()
    {
    	return eventNotes;
    }

    public boolean getIsBusy()
    {
    	return isBusy;
    }

    public boolean getRepeating()
    {
        return repeating;
    }

    public int getRepeatingRate()
    {
    	return repeatingRate;
    }

    public int getReminderTime()
    {
    	return reminderTime;
    }

    public Calendar getStart()
    {
        return start;
    }

    public void setPrivateEvent(boolean privateEvent)
    {
    	this.privateEvent = privateEvent;
    }

    public void setEventTitle(String eventTitle)
    {
    	this.eventTitle = eventTitle;
    }

    public void setEventNotes(String eventNotes)
    {
    	this.eventNotes = eventNotes;
    }

    public void setIsBusy(boolean isBusy)
    {
    	this.isBusy = isBusy;
    }

    public void setRepeatingRate(int repeatingRate)
    {
    	this.repeatingRate = repeatingRate;
    }

    public void setReminderTime(int reminderTime)
    {
    	this.reminderTime = reminderTime;
    }

    public void setStart(Calendar start)
    {
        this.start = start;
    }

    public String getLocation()
    {
        return "";
    }

    // toString method to output the information for the event
    public String toString()
    {
    	String returnString;

    	if ( privateEvent )
    	{
    		returnString = "Private event ";
    	}
    	else
    	{
    		returnString = "Public event ";
    	}

    	if ( isBusy )
    	{
    		returnString += "Busy";
    	}
    	else
    	{
    		returnString += "Not Busy";
    	}

    	return returnString + " Title: " + eventTitle + " Event Notes: " + eventNotes + " RepeatingRate: " + repeatingRate + " reminder time: " + reminderTime;
    }
}
