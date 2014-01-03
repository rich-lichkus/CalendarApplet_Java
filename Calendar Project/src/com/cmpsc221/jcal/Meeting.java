package com.cmpsc221.jcal;

import java.util.Calendar;

/**
* Program: jCal
* Description: Class that stores the Meeting information; it extends Event
*/

public class Meeting extends Event 
{
    private Calendar end;        			// end date/time for the meeting
    private double duration;				// how long the meeting is going to be in mins
    private String meetingType;				// what type of meeting it is
    private double phoneNumber;				// number for conference call
    private double password;				// password for conference call
    private String address;					// address of the meeting
    private Attendees attendeesList;		// list of who is coming to the meeting
	
	// default constructor
	public Meeting()
	{
		super();
        this.end = Calendar.getInstance();
		this.duration = 0;
		this.meetingType = "";
		this.phoneNumber = 0;
		this.password = 0;
		this.address = "";
		
		this.attendeesList = new Attendees();
	}
	
	// constructor to set the data members
	public Meeting(double duration, String meetingType, double phoneNumber,	double password, String address, Attendees attendeesList, boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime, Calendar start, Calendar end) {
		super(privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, reminderTime, start);
		this.duration = duration;
		this.meetingType = meetingType;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;		
		this.attendeesList = attendeesList;
        this.end = end;
	}

	public void addAttendee(Contacts contact, boolean confirmed)
    {
        attendeesList.add(contact, confirmed);
    }

    public void editAttendee(int num, Contacts contact, boolean confirmed)
    {
        attendeesList.edit(num, contact, confirmed);
    }

    public void removeAttendee(int num)
    {
        attendeesList.remove(num);
    }
	
/**
 *  Getter and Setter Pairs
 */ 
    public Calendar getEnd() 
	{
        return end;
    }
    public void setEnd(Calendar end) 
	{
        this.end = end;
    }

  
    public double getDuration() 
	{
        return duration;
    }
    public void setDuration(double duration) 
	{
        this.duration = duration;
    }
    
	public String getMeetingType() 
	{
        return meetingType;
    }
    public void setMeetingType(String meetingType) 
	{
        this.meetingType = meetingType;
    }
   
    public double getPhoneNumber() 
	{
        return phoneNumber;
    }
    public void setPhoneNumber(double phoneNumber) 
	{
        this.phoneNumber = phoneNumber;
    }
    
    
    public double getPassword() 
	{
        return password;
    }
    public void setPassword(double password) 
	{
        this.password = password;
    }
    
    
    public String getAddress() 
	{
        return address;
    }
    public void setAddress(String address) 
	{
        this.address = address;
    }
	
	public Attendees getAttendees()
	{
		return attendeesList;
	}
	public void setAttendees(Attendees attendeesList)
	{
		this.attendeesList = attendeesList;
	}
	
	// returns the string of the object
	public String ToString()
	{
		String returnString = "Start: " + start.get(start.MONTH)+1 + "/" + start.get(start.DAY_OF_MONTH) 
		+ ", End: " + end.get(end.MONTH)+1 + "/" + end.get(end.DAY_OF_MONTH) + ", Duration: " + duration 
		+ ", Meeting Type: " + meetingType + ", Phone Number: " + phoneNumber + ", password: " + password
		+ ", Address: " + address;
		
        returnString += "\n" + attendeesList.toString();
		
        return super.toString() + "\n" + returnString;
	}
}
