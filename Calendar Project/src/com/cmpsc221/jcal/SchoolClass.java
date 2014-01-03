package com.cmpsc221.jcal;

import java.util.Calendar;

/**
 * Program: jCal Description: Class that stores the SchoolClass information; it
 * extends Event
 */
public class SchoolClass extends Event {

    private Calendar end;							// end date/time for the School class
    private double duration;						// how long the class is going to be in mins
    private String buildingName;					// the name of the building the class is at
    private double roomNumber;						// the room that the class is in
    private Attendees attendeesList;				// the people in the class

    // default constructor
    public SchoolClass() {
        super();
        this.end = Calendar.getInstance();
        this.duration = 0;
        this.buildingName = "";
        this.roomNumber = 0;
        this.attendeesList = new Attendees();
    }

    // constructor to set the data members
    public SchoolClass(double duration, String buildingName, double roomNumber, Attendees attendeesList, boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime, Calendar end, Calendar start) {
        super(privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, reminderTime, start);
        this.duration = duration;
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public double getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(double roomNumber) {
        this.roomNumber = roomNumber;
    }

    // returns the string of the object
    public String ToString() {
        String returnString = "Start: " + start.get(start.MONTH) + 1 + "/" + start.get(start.DAY_OF_MONTH)
                + " End: " + end.get(end.MONTH) + 1 + "/" + end.get(end.DAY_OF_MONTH) + " Duration: " + duration
                + " Building Name: " + buildingName + " Room Number: " + roomNumber;

        returnString += "\n" + attendeesList.toString();

        return super.toString() + "\n" + returnString;
    }
}
