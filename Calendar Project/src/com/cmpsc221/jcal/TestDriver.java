/*
 *  Developers:  Richard Lichkus, Anthony Blyler, Nicholas Hall
 *  Team Number: 23
 *  Section:     2
 *  Title:       Project JCal
 *  Desc:        Test Driver
 */

package com.cmpsc221.jcal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestDriver {
    
    
    public static void main(String[] args)
    {
        Calendar time = Calendar.getInstance();
        // 1. Create sample user
        User RobertSmith = new User("BobbySmith");
        
        // 2. Load sample user contact information
        RobertSmith.setSampleUserContactCard();
        
        // 3. Simple GUI Interaction
        System.out.println("Enter Username");
        System.out.println(">> BobbySmith");
        
        System.out.println("Enter Password:");
        System.out.println(">> ********");

        if ( RobertSmith.authenticate("qwerty") )
        {
            System.out.println("Authenticated");
        }
        else
        {
            System.out.println("Authentication failed");
        }
        
        // 4. Print Out User Contact Card
        System.out.println("\n"+RobertSmith.toString());    
        
        // 5. Add Contact Name maximus 
        RobertSmith.addContact("Gen.", "Maximus","Spaniard", "Decimus", "Meridius", "", "General", "Commander of Northern Armies","Empire of Rome", "");
        RobertSmith.addContact("Dr.", "Jason","Jay", "Xavier", "Crispleton", "III", "CEO","Corporate", "Crisp Intelligent Technologies", "");
        
        // 6. Print Contact List
        System.out.println("\nContact List");
        RobertSmith.printContactList();
        
        // 7. Update Maximus Contact with Address, Phone and Email
        RobertSmith.addContactAddress(1, "Home", 10, "Beaver Avenue", "State College", "PA", 16801, "USA");
        RobertSmith.addContactPhoneNum(1, "Home", "717-880-1234", 0);
        RobertSmith.addContactEmail(1, "PSU", "abc1234@psu");
                
        // 8. Update Jazon Contact with Social Media, Custom Data
        RobertSmith.addContactSocialMedia(2, "Twitter", "@CrispLife");
        RobertSmith.addContactCustomList(2, "Dog's Name", "Spike");
        
        // 9. Print Maximus and Jason's Contact Card
        System.out.println(" ");
        RobertSmith.printContactCard(1);
        System.out.println(" ");
        RobertSmith.printContactCard(2);
        
        //10. Add Calendar to List
        RobertSmith.addCalendar("PSUCal");
        
        //11. Print  Calendar List
        System.out.println("\nCalendar List");
        RobertSmith.printCalendarList();
       
        //12. Add Volunteering Event Group to myCal, 
        //    Add IM Sports and Classes Event Groups to PSUCal
        RobertSmith.addCalendarEventGroup(1,"Volunteering");
        RobertSmith.addCalendarEventGroup(2, "IM Sports");
        RobertSmith.addCalendarEventGroup(2, "Classes");
        
        //13. Print EventGroups
        System.out.println("\nmyCal EventGroups List");
        RobertSmith.printCalendarEventGroupList(1);
        System.out.println("\nPSUCal EventGroups List");
        RobertSmith.printCalendarEventGroupList(2);
        
        //14. Create, Populate, Add Appointment Event
        Appointment apptDentist = new Appointment();
        apptDentist.setEventTitle("Dentist Appointment");
        apptDentist.setLocation("10 Beaver Avenue");
        apptDentist.setIsBusy(true);
        time.set(2012,9,20,13,0,0);
        apptDentist.setStart(time);
        RobertSmith.addEvent(1,1,apptDentist);
        
        //15. Create, Populate, Add DayDescriptor
        DayDescriptors dayDesc = new DayDescriptors();
        dayDesc.setEventTitle("Christmas Holiday");
        time.set(2012,11,25,0,0,0);
        dayDesc.setStart(time);
        RobertSmith.addEvent(1,1,dayDesc);
        
        //16. Create, Populate, Add Deadline
        Deadline exDeadline = new Deadline();
        exDeadline.setHoursOfWork(10);
        exDeadline.setEventTitle("CS221 Calendar Project");
        exDeadline.setPercentComplete(20);
        RobertSmith.addEvent(2,2,exDeadline);
        
        //17. Create, Populate, Add Flight
        Flight newFlight = new Flight();
        newFlight.setEventTitle("My Flight");
        newFlight.setConfirmCode("SDS121");
        newFlight.setflightType("Domestic");
        time.set(2012,9,27,11,0,0);
        newFlight.setDepartureTo(time);
        RobertSmith.addEvent(1, 1, newFlight);
        
        //18. Create, Populate, Add Meeting
        Meeting newMeeting = new Meeting();
        newMeeting.setEventTitle("Coffee with Friend");
        newMeeting.setAddress("304 Duncan Street");
        newMeeting.setDuration(30);
        newMeeting.setMeetingType("Location");
        RobertSmith.addEvent(1,1, newMeeting);
        
        //19. Create, Populate, Add Quick
        Quick newQuick = new Quick();
        newQuick.setEventTitle("Rec Sports Meeting");
        newQuick.setStart(time);
        newQuick.setLocation("IM Building");
        RobertSmith.addEvent(2, 1, newQuick);
        
        //20. Create, Populate, Add School Class Event
        SchoolClass cs221 = new SchoolClass();
        cs221.setEventTitle("CMPSC 221");
        cs221.setBuildingName("IST");
        cs221.setRoomNumber(220);
        cs221.setStart(time); 
        cs221.repeating = true;
        cs221.isBusy = true;
        cs221.privateEvent = false;
        RobertSmith.addEvent(2, 2, cs221);
        
        //21. Create, Populate, Add Vacation
        Vacation newVacation = new Vacation();
        newVacation.setEventTitle("Hawaii Trip 2012");
        newVacation.setLocation("Hawaii");
        newVacation.setStart(time);
        RobertSmith.addEvent(1,1, newVacation);
        
        //22. Print Event List
        System.out.println("\nmyCall Event List");
        RobertSmith.printEventList(1,1);
        
        //23. Print Event List
        System.out.println("\nPSUCal IM Sports List");
        RobertSmith.printEventList(2,1);
        
        //24. Print Event List
        System.out.println("\nPSUCal Class List");
        RobertSmith.printEventList(2,2);
        
    }       
    
}
