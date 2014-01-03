package com.cmpsc221.jcal;

import java.util.Calendar;

/**
* Program: jCal
* Description: Class that stores the information for a day descriptor; it extends Event
*/
public class DayDescriptors extends Event {
    private Calendar end;	// date for the end of the day descriptor
    private String title;	// title for the day descriptor

    // default constructor
    public DayDescriptors()
    {
        super();
        end = Calendar.getInstance();
        title = "";
    }
    // constructor to set the data members of the class
    public DayDescriptors(Calendar start, Calendar end, String title, boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime)
    {
        super(privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, reminderTime, start);
    	this.end = end;
    	this.title = title;
    }

    // getters/setters
    public Calendar getEnd()
    {
    	return end;
    }

    public String getTitle()
    {
    	return title;
    }

    public void setEnd(Calendar end)
    {
    	this.end = end;
    }

    public void setTitle(String title)
    {
    	this.title = title;
    }

    // toString method to return a string of all the data types
    public String toString()
    {
    	return super.toString() + "\nStart: " + start.get(start.MONTH)+1 + "/" + start.get(start.DAY_OF_MONTH) + " End: " + end.get(end.MONTH)+1 + "/" + end.get(end.DAY_OF_MONTH) + " Title: " + title;
    }
}
