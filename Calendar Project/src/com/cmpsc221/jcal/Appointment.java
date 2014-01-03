package com.cmpsc221.jcal;

import java.util.Calendar;
import java.util.ArrayList;
/**
 * Program: jCal
 * Description: Class that stores appointment information; it extends Event
 */
public class Appointment extends Event{
    private Calendar end;                        // end date/time for the Appointment
    private double duration;                     // duration of the appointment in minutes
    private Attendees attendeeList;              // list of attendants at the appointment
    private String location;                     // location of the appointment

    // default constructor
    public Appointment()
    {
        super();
        end = Calendar.getInstance();
        duration = 0;
        attendeeList = new Attendees();
        location = "";
    }

    // constructor to set all the data members
    public Appointment(Calendar start, Calendar end, double duration, Attendees attendeeList, String location, boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime)
    {
        super(privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, reminderTime, start);
        this.end = end;
        this.duration = duration;
        this.attendeeList = attendeeList;
        this.location = location;
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

/**
 * Getter and Setter Pairs
 */    
    public Calendar getEnd() {
        return end;
    }
    public void setEnd(Calendar end) {
        this.end = end;
    }


    public double getDuration() {
        return duration;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }


    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    // outputs the string of the object
    public String toString()
    {
        String returnString = "Start: " + start.get(start.MONTH)+1 + "/" + start.get(start.DAY_OF_MONTH) + " End: " + end.get(end.MONTH)+1 + "/" + end.get(end.DAY_OF_MONTH) + " Duration: " + duration + " Location: " + location;
        returnString += "\n" + attendeeList.toString();
        return super.toString() + "\n" + returnString;
    }
}