package com.cmpsc221.jcal;

import java.util.Calendar;

/**
* Program: jCal
* Description: Class that stores the vacation information; it extends Event
*/

public class Vacation extends Event
{
	private Calendar end;				// end date/time for the vacation
	private String location;			// the location that the vacation is occuring at
	private double duration;			// how long the vacation is going to be in mins
	private Attendees attendeeList;		// who is coming on the vacation
	
	// default constructor
	public Vacation()
	{
		super();
		this.end = Calendar.getInstance();
		this.location = "";
		this.duration = 0;
		
		attendeeList = new Attendees();
	}

	// constructor to initialize data members
	public Vacation(Calendar start, Calendar end, String location, double duration, Attendees attendeeList, boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime)
	{
		super(privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, reminderTime, start);
		this.end = end;
		this.location = location;
		this.duration = duration;
		this.attendeeList = attendeeList;
	}

	public void addAttendee(Contacts contact, boolean confirmed)
    {
        attendeeList.add(contact, confirmed);
    }

    public void editAttendee(int num, Contacts contact, boolean confirmed)
    {
        attendeeList.edit(num, contact, confirmed);
    }

    public void removeAttendee(int num)
    {
        attendeeList.remove(num);
    }
	
	// gets the end time of the vacation
	public Calendar getEnd()
	{
		return end;
	}

	// gets the location of the vacation
	public String getLocation()
	{
		return location;
	}
	
	// gets how long the vacation is going to last
	public double getDuration()
	{
		return duration;
	}
	
	// gets the list of who is going on the vacation
	public Attendees getAttendees()
	{
		return attendeeList;
	}
	
	// sets the ending time of the vacation
	public void setEnd(Calendar end)
	{
		this.end = end;
	}
	
	// sets the location of the vacation
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	// sets the time length of the vacation
	public void setDuration(double duration)
	{
		this.duration = duration;
	}
	
	// sets the list of who are coming on the vacation
	public void setAttendees(Attendees attendeeList)
	{
		this.attendeeList = attendeeList;
	}

	// toString method to return all of the data members to user readable text
	public String toString()
	{
		String returnString = "Start: " + start.get(start.MONTH)+1 + "/" + start.get(start.DAY_OF_MONTH) + " End: " + end.get(end.MONTH)+1 + "/" + end.get(end.DAY_OF_MONTH) + " Duration: " + duration + " Location: " + location;
		returnString += "\n" + attendeeList.toString();

		return super.toString() + "\n" + returnString;
	}
}
