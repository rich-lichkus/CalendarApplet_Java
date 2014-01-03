package com.cmpsc221.jcal;

import java.util.Calendar;

/**
* Program: jCal
* Description: Class that stores the flight information; it extends Event
*/

public class Flight extends Event 
{    
    private Calendar departureTo;			// time for flight to leave
    private Calendar departureReturn;		// time for the return flight
    private String confirmCode;				// confirmation code for flight
    private String flightType;				// type of flight (one way or round way trip)
	private double checkingReminder;		// time of when you get reminded to check in to flight
	private double boardingReminder;		// time of when you get reminded to board the flight
	private Attendees attendeesList;		// list of people going on the flight

	// default constructor
	public Flight()
	{
		super();
		this.confirmCode = "";
		this.flightType = "";
		this.checkingReminder = 0;
		this.boardingReminder = 0;
		this.departureTo = Calendar.getInstance();
		this.departureReturn = Calendar.getInstance();
		
		this.attendeesList = new Attendees();
	}
	
	// constructor to set the data members
	public Flight(String confirmCode, String flightType, double checkingReminder, double boardingReminder, Attendees attendeesList, boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime, Calendar start)
	{
		super(privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, reminderTime, start);
		this.confirmCode = confirmCode;
		this.flightType = flightType;
		this.checkingReminder = checkingReminder;
		this.boardingReminder = boardingReminder;		
		this.attendeesList = attendeesList;
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
 *  Getters and Setter Pairs
 * 
 */      
    public Calendar getDepartureTo() 
	{
        return departureTo;
    }
    public void setDepartureTo(Calendar departureTo) 
	{
        this.departureTo = departureTo;
    }

 
    public Calendar getDepartureReturn() 
	{
        return departureReturn;
    }
    public void setDepartureReturn(Calendar departureReturn) 
	{
        this.departureReturn = departureReturn;
    }

   
    public String getConfirmCode() 
	{
        return confirmCode;
    }
    public void setConfirmCode(String confirmCode) 
	{
        this.confirmCode = confirmCode;
    }


    public String getflightType() 
	{
        return flightType;
    }
    public void setflightType(String flightType) 
	{
        this.flightType = flightType;
    }
	
	private double getCheckingReminder()
	{        
        return checkingReminder;
    }
	private void setCheckingReminder()
	{        
        this.checkingReminder = checkingReminder;
    }
    
	private double getBoardingReminder()
	{        
        return boardingReminder;
    }
    private void setBoardingReminder()
	{        
        this.boardingReminder = boardingReminder;
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
		String returnString = "Start: " + departureTo.get(departureTo.MONTH)+1 + "/" + departureTo.get(departureTo.DAY_OF_MONTH) 
		+ ", End: " + departureReturn.get(departureReturn.MONTH)+1 + "/" + departureReturn.get(departureReturn.DAY_OF_MONTH) + ", Confirm Code: " + confirmCode 
		+ ", Fight Type: " + flightType + ", Checking Reminder: " + checkingReminder + ", Boarding Reminder: "
		+ boardingReminder;
		
        returnString += "\n" + attendeesList.toString();
		
        return super.toString() + "\n" + returnString;
	}
}
