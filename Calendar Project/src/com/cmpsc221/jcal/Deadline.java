package com.cmpsc221.jcal;

import java.util.Calendar;

/**
 * Program: jCal
 * Description: Class that stores deadline information; extends event
*/

public class Deadline extends Event{ 
    private String deadlineType;    // type of deadline
    private Calendar dueDate;       // date due for the deadline
    private double percentComplete; // percentage completed for the deadline
    private double hoursOfWork;     // hours of work completed

    // default constructor
    public Deadline()
    {
        super();
        deadlineType = "";
        dueDate = Calendar.getInstance();
        percentComplete = 0;
        hoursOfWork = 0;
    }
    // constructor for the data members
    public Deadline(String deadlineType, Calendar dueDate, double percentComplete, double hoursOfWork, boolean privateEvent, String eventTitle, String eventNotes, boolean isBusy, boolean repeating, int repeatingRate, int reminderTime, Calendar start)
    {
        super(privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, reminderTime, start);
        this.deadlineType = deadlineType;
        this.dueDate = dueDate;
        this.percentComplete = percentComplete;
        this.hoursOfWork = hoursOfWork;
    }
/**
 *  Getter and setter pairs 
 */    
    
    public String getDeadlineType() {
        return deadlineType;
    }
    public void setDeadlineType(String deadlineType) {
        this.deadlineType = deadlineType;
    }
    /**
     * Getter and setter pairs
     */

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public double getPercentComplete() {
        return percentComplete;
    }
    public void setPercentComplete(double percentComplete) {
        this.percentComplete = percentComplete;
    }

    public double getPrecentComplete() {
        return percentComplete;
    }

    public void setPrecentComplete(double percentComplete) {
        this.percentComplete = percentComplete;
    }

    public double getHoursOfWork() {
        return hoursOfWork;
    }

    public void setHoursOfWork(double hoursOfWork) {
        this.hoursOfWork = hoursOfWork;
    }

    // toString method to return the information of the object
    public String toString()
    {
        return super.toString() + "\nDeadline type: " + deadlineType + " Due Date: " + dueDate.get(dueDate.MONTH)+1 + "/" + dueDate.get(dueDate.DAY_OF_MONTH) + " % complete: " + percentComplete + " hours of work: " + hoursOfWork;
    }
}
