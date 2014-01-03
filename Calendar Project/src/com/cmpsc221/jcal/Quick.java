package com.cmpsc221.jcal;

import java.util.Calendar;

/**
* Program: jCal
* Description: Class that stores the information for a quick event; it extends Event
*/
public class Quick extends Event
{
	private Calendar end;	 // defines when the event ends
	private String location; // defines the location of the event
	private int id;

	// default constructor
	public Quick()
	{
		super();
		end = Calendar.getInstance();
		location = "";
	}
	// constructor for the quick event
	public Quick(Calendar start, Calendar end, String location, boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime)
	{
		super(privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, reminderTime, start);
		this.end = end;
		this.location = location;
	}

	public Calendar getEnd()
	{
		return end;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getLocation()
	{
		return location;
	}

	public void setEnd(Calendar end)
	{
		this.end = end;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	// toString method to return all of the data members to the user
	public String toString()
	{
		return super.toString() + "\nStart: " + start.get(start.MONTH)+1 + "/" + start.get(start.DAY_OF_MONTH) + " End: " + end.get(end.MONTH)+1 + "/" + end.get(end.DAY_OF_MONTH) + " Location: " + location;
	}
}