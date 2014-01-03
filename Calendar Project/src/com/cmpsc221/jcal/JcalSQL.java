/*
 * 
 */
package com.cmpsc221.jcal;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JcalSQL {
    
    private Connection dbConnection;
    private static int userID;
    
    public JcalSQL()
    {
        userID = 1;
    }
   
    public void OpenConnection() throws ClassNotFoundException
    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://tonyblyler.com:3306/jcal", "root", "root");
            
        }
        catch (SQLException e)
        {
            System.out.println("exception: open connection");
        }  
    }
    
    public void CloseConnection()
    {
        try 
        {
            dbConnection.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println("exception: close connection");
        }
    }
    
    public ResultSet getData(String sqlStatement) throws SQLException
    {
        Statement stmt = dbConnection.createStatement();    
        ResultSet results = stmt.executeQuery(sqlStatement);
        stmt.close();
        return results;
    }
    
    public int updateData(String sqlStatement) throws SQLException
    {
        Statement stmt = dbConnection.createStatement();    
        int results = stmt.executeUpdate(sqlStatement);
        stmt.close();        
        return results;    
    }
    
    public void PrintResultSet(ResultSet results, String resultsToPrint)
    {
        try 
        {
            while (results.next())
                {
                    //results.getString("username") + results.getInt("userID")
                    System.out.println(resultsToPrint);
                }
                results.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println("exception: print results");
        }
    
    }
      
    public Vector getDBCalendarVector()
    {
        Vector contactVector = new Vector();
        try 
        { 
            JcalSQL database = new JcalSQL();
            database.OpenConnection();
            ResultSet results = database.getData("Select * from calendars where userID = '" + userID + "' order by title;");
            while(results.next())
            {
                contactVector.add(results.getString("title"));
            }            
            results.close();
            database.CloseConnection();
        }
        catch (SQLException ex) 
        {
            System.out.println("exception: sfsf");
        }
        catch (ClassNotFoundException ex)
        {            
        }
        return contactVector;
    }

    public Vector getDBEventGroupVector(String calendar)
    {
        Vector eventGroupVector = new Vector();
        try
        {
            JcalSQL database = new JcalSQL();
            database.OpenConnection();
            ResultSet results = database.getData("select eg_title from event_groups where userID = '" + userID + "' and jcalID = (select jcalID from calendars where userID = '" + userID + "' and title = '" + calendar + "')");
            while (results.next())
            {
                eventGroupVector.add(results.getString("eg_title"));
            }
            results.close();
            database.CloseConnection();
        }
        catch (SQLException ex)
        {
            System.out.println("exception: sfsf");
        }
        catch (ClassNotFoundException ex)
        {
        }
        return eventGroupVector;
    }

    public Vector getDBQuickEventVector(int calId, int egId)
    {
        Vector quickEventVector = new Vector();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try
        {
            JcalSQL database = new JcalSQL();
            database.OpenConnection();
            ResultSet results = database.getData("select * from quick_event where userID = '" + userID + "' and jcalID = " + calId + " and eg_ID = " + egId);
            while ( results.next() )
            {
                start = Calendar.getInstance();
                end = Calendar.getInstance();
                start.setTime(results.getTime("start_date"));
                end.setTime(results.getTime("end_date"));
                quickEventVector.add(new Quick(start, end, results.getString("location"), results.getBoolean("privateEvent"), results.getString("eventTitle"), results.getString("eventNotes"), results.getBoolean("isBusy"), results.getBoolean("repeating"), results.getInt("repeatingRate"), results.getInt("reminderTime")));
            }
            results.close();
            database.CloseConnection();
        }
        catch (SQLException ex)
        {
            System.out.println("exception: sfsf");
        }
        catch (ClassNotFoundException ex)
        {
        }
        return quickEventVector;
    }

    public void loadUserData(User sampleUser)
    {        
        ResultSet rsCalendars;
        ResultSet rsEGroups;
        ResultSet rsEvents;
        try 
        { 
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://tonyblyler.com:3306/jcal", "root", "root");
            // gets calendars
            String sqlGetCalendars = ("Select * from calendars where userID = '" + userID + "' order by title;");
            Statement stmt = dbConnection.createStatement();
            rsCalendars = stmt.executeQuery(sqlGetCalendars);
            // loop goes through each calendar
            for ( int cali = 0; rsCalendars.next(); cali++ )
            {
                // adds calendar and its id
                sampleUser.addCalendar(rsCalendars.getString("title"));
                sampleUser.getCalendar(cali).setCbxIsSelected(rsCalendars.getBoolean("isSelected"));
                sampleUser.getCalendar(cali).setId(rsCalendars.getInt("jcalID"));
                // gets event groups for the current calendar
                String sqlGetEventGroups = "select * from event_groups where userID = '" + userID + "' and jcalID = " + rsCalendars.getInt("jcalID");
                Statement stmt2 = dbConnection.createStatement();    
                rsEGroups = stmt2.executeQuery(sqlGetEventGroups);
                // loop goes through event event group of the current calendar
                for ( int egi = 0; rsEGroups.next(); egi++ )
                {
                    // adds event group and its id
                    sampleUser.addCalendarEventGroup(cali+1, rsEGroups.getString("eg_title"));
                    sampleUser.getCalendarEventGroup(cali+1, egi).setCbxIsSelected(rsEGroups.getBoolean("eg_isSelected"));
                    sampleUser.getCalendarEventGroup(cali+1, egi).setId(rsEGroups.getInt("eg_ID"));

                    // adds quick events
                    Statement stmt3 = dbConnection.createStatement();
                    rsEvents = stmt3.executeQuery("select * from quick_event where userID = '" + userID + "' and jcalID = " + rsCalendars.getInt("jcalID") + " and eg_ID = " + rsEGroups.getInt("eg_ID"));
                    while ( rsEvents.next() )
                    {
                        Calendar start = Calendar.getInstance();
                        Calendar end = Calendar.getInstance();
                        Calendar startTime = Calendar.getInstance();
                        Calendar endTime = Calendar.getInstance();
                        startTime.setTime(new Date(rsEvents.getTime("start_date").getTime()));
                        endTime.setTime(new Date(rsEvents.getTime("end_date").getTime()));
                        
                        start.setTime(new Date(rsEvents.getDate("start_date").getTime()));
                        start.set(Calendar.HOUR_OF_DAY, startTime.get(Calendar.HOUR_OF_DAY));
                        start.set(Calendar.MINUTE, startTime.get(Calendar.MINUTE));
                        start.set(Calendar.SECOND, startTime.get(Calendar.SECOND));
                        
                        end.setTime(new Date(rsEvents.getDate("end_date").getTime()));
                        end.set(Calendar.HOUR_OF_DAY, endTime.get(Calendar.HOUR_OF_DAY));
                        end.set(Calendar.MINUTE, endTime.get(Calendar.MINUTE));
                        end.set(Calendar.SECOND, endTime.get(Calendar.SECOND));
                        
                        sampleUser.addEvent(cali+1, egi+1, new Quick(start, end, rsEvents.getString("location"), rsEvents.getBoolean("privateEvent"), rsEvents.getString("eventTitle"), rsEvents.getString("eventNotes"), rsEvents.getBoolean("isBusy"), rsEvents.getBoolean("repeating"), rsEvents.getInt("repeatingRate"), rsEvents.getInt("reminderTime")));
                    }
                    stmt3.close();
                }
                stmt2.close();
            }
            stmt.close();
            // Contacts!
            ResultSet results;
            ResultSet results2;
            Statement results2Stmt;
            Statement resultsStmt = dbConnection.createStatement();
            results = resultsStmt.executeQuery("Select * from contacts where userID = " + userID + " order by firstname;");
            for (int contactNum = 1; results.next(); contactNum++ )
            {
                sampleUser.addContact(results.getString("nprefix"), results.getString("firstname"), results.getString("nickname"), results.getString("middlename"), results.getString("lastname"), results.getString("nsuffix"), results.getString("job_title"), results.getString("department"), results.getString("company"), results.getString("birthdate"));
                sampleUser.getContactAtIndex(contactNum-1).setId(results.getInt("contactID"));
                // address
                results2Stmt = dbConnection.createStatement();
                results2 = results2Stmt.executeQuery("select * from address where contactID = '" + results.getInt("contactID") + "' and userID = " + userID);
                while ( results2.next() )
                {
                    sampleUser.addContactAddress(contactNum, results2.getString("label"), results2.getDouble("street_num"), results2.getString("street_name"), results2.getString("city"), results2.getString("states"), results2.getDouble("zipcode"), results2.getString("country"));
                }
                results2Stmt.close();
                // phone
                results2Stmt = dbConnection.createStatement();
                results2 = results2Stmt.executeQuery("select * from phone where contactID = '" + results.getInt("contactID") + "' and userID = " + userID);
                while ( results2.next() )
                {
                    sampleUser.addContactPhoneNum(contactNum, results2.getString("label"), results2.getString("number"), results2.getDouble("extension"));
                }
                results2Stmt.close();
                // email
                results2Stmt = dbConnection.createStatement();
                results2 = results2Stmt.executeQuery("select * from email where contactID = '" + results.getInt("contactID") + "' and userID = " + userID);
                while ( results2.next() )
                {
                    sampleUser.addContactEmail(contactNum, results2.getString("label"), results2.getString("account"));
                }
                results2Stmt.close();
                // social media
                results2Stmt = dbConnection.createStatement();
                results2 = results2Stmt.executeQuery("select * from social_media where contactID = '" + results.getInt("contactID") + "' and userID = " + userID);
                while ( results2.next() )
                {
                    sampleUser.addContactSocialMedia(contactNum, results2.getString("label"), results2.getString("social_media"));
                }
                results2Stmt.close();
                // custom list
                results2Stmt = dbConnection.createStatement();
                results2 = results2Stmt.executeQuery("select * from custom_list where contactID = '" + results.getInt("contactID") + "' and userID = " + userID);
                while ( results2.next() )
                {
                    sampleUser.addContactCustomList(contactNum, results2.getString("label"), results2.getString("note"));
                }
                results2Stmt.close();
            }
            resultsStmt.close();
            dbConnection.close(); // database.CloseConnection();
        }
        catch (SQLException ex) 
        {
            System.out.println("exception: sfsf");
        }
        catch (ClassNotFoundException ex)
        {            
        }
    }

    public void saveUserData(User sampleUser)
    {
        String[] deleteTables = {"address", "custom_list", "email", "phone", "quick_event", "social_media", "contacts", "event_groups", "calendars"};
        try
        {
            ResultSet results;
            Statement stmt;
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://tonyblyler.com:3306/jcal", "root", "root");

            int maxJcalID = 0;
            int maxEgID = 0;
            int maxContactID = 0;
            for ( String tempString : deleteTables )
            {
                if ( tempString == "contacts" )
                {
                    stmt = dbConnection.createStatement();
                    results = stmt.executeQuery("select max(contactID) maxID from contacts");
                    if ( results.next() )
                    {
                        maxContactID = results.getInt("maxID");
                    }
                    stmt.close();
                }
                else if ( tempString == "calendars" )
                {
                    stmt = dbConnection.createStatement();
                    results = stmt.executeQuery("select max(jcalID) maxID from calendars");
                    if ( results.next() )
                    {
                        maxJcalID = results.getInt("maxID");
                    }
                    stmt.close();
                }
                else if ( tempString == "event_groups")
                {
                    stmt = dbConnection.createStatement();
                    results = stmt.executeQuery("select max(eg_ID) maxID from event_groups");
                    if ( results.next() )
                    {
                        maxEgID = results.getInt("maxID");
                    }
                    stmt.close();
                }
                stmt = dbConnection.createStatement();
                stmt.executeUpdate("delete from " + tempString + " where userID = " + userID);
                stmt.close();
            }
            // each stored jcal
            for ( JCal tempCal : sampleUser.getUserCalendars() )
            {
                if ( !tempCal.idIsSet() )
                {
                    tempCal.setId(maxJcalID+1);
                    maxJcalID++;
                }
                stmt = dbConnection.createStatement();
                stmt.executeUpdate("insert into calendars (userID, jcalID, title, isSelected) values (" + userID + "," + tempCal.getId() + ",'" + tempCal.getTitle() + "', " + tempCal.isCbxIsSelected() +")");
                stmt.close();
                // each stored eventgroup in this jcal
                for ( EventGroup tempEventGroup : tempCal.getEventGroup() )
                {
                    if ( !tempEventGroup.idIsSet() )
                    {
                        tempEventGroup.setId(maxEgID+1);
                        maxEgID++;
                    }
                    stmt = dbConnection.createStatement();
                    stmt.executeUpdate("insert into event_groups (userID, jcalID, eg_ID, eg_title, eg_isSelected) values (" + userID + ", " + tempCal.getId() + ", " + tempEventGroup.getId() + ", '" + tempEventGroup.getTitle() + "', " + tempEventGroup.isCbxIsSelected() + ")");
                    stmt.close();
                    // each stored event in this eventgroup
                    for ( Event tempEvent : tempEventGroup.getEvent() )
                    {
                        Quick tempQuick = (Quick)tempEvent;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String start_date = format.format(tempQuick.getStart().getTime());
                        String end_date = format.format(tempQuick.getEnd().getTime());
                        stmt = dbConnection.createStatement();
                        stmt.executeUpdate("insert into quick_event (userID, jcalID, eg_ID, privateEvent, eventTitle, eventNotes, isBusy, repeating, repeatingRate, start_date, end_date, location) values (" + userID + ", " + tempCal.getId() + ", " + tempEventGroup.getId() + ", " + tempQuick.getPrivateEvent() + ", '" + tempQuick.getEventTitle() + "', '" + tempQuick.getEventNotes() + "', " + tempQuick.getIsBusy() + ", " + tempQuick.getRepeating() + ", " + tempQuick.getRepeatingRate() + ", '" + start_date + "', '" + end_date + "', '" + tempQuick.getLocation() + "')");
                        stmt.close();
                    }
                }
            }

            // Contacts!
            for ( Contacts tempContact : sampleUser.getUserContacts() )
            {
                if ( !tempContact.idIsSet() )
                {
                    tempContact.setId(maxContactID+1);
                    maxContactID++;
                }
                stmt = dbConnection.createStatement();
                stmt.executeUpdate("insert into contacts (userID, contactID, nprefix, firstname, nickname, middlename, lastname, nsuffix, job_title, department, company, birthdate) values (" + userID + ", " + tempContact.getId() + ", '" + tempContact.getPrefix() + "', '" + tempContact.getFirstName() + "', '" + tempContact.getNickName() + "', '" + tempContact.getMiddleName() + "', '" + tempContact.getLastName() + "', '" + tempContact.getSuffix() + "', '" + tempContact.getJobTitle() + "', '" + tempContact.getDepartment() + "', '" + tempContact.getCompany() + "', '" + tempContact.getBirthdate() + "')");
                stmt.close();
                // address
                for ( Address tempAddress : tempContact.getContactAddresses() )
                {
                    stmt = dbConnection.createStatement();
                    stmt.executeUpdate("insert into address (userID, contactID, label, street_num, street_name, city, states, zipcode, country) values (" + userID + ", " + tempContact.getId() + ", '" + tempAddress.getAddressLabel() + "', " + tempAddress.getStreetNumber() + ", '" + tempAddress.getStreetName() + "', '" + tempAddress.getCity() + "', '" + tempAddress.getState() + "', " + tempAddress.getZipcode() + ", '" + tempAddress.getCountry() + "')");
                    stmt.close();
                }

                // phone
                for ( PhoneNumber tempPhone : tempContact.getContactsNumbers() )
                {
                    stmt = dbConnection.createStatement();
                    stmt.executeUpdate("insert into phone (userID, contactID, label, number, extension) values (" + userID + ", " + tempContact.getId() + ", '" + tempPhone.getNumberLabel() + "', '" + tempPhone.getNumber() + "', " + tempPhone.getExtension() + ")");
                    stmt.close();
                }

                // email
                for ( Email tempEmail : tempContact.getContactsEmails() )
                {
                    stmt = dbConnection.createStatement();
                    stmt.executeUpdate("insert into email (userID, contactID, label, account) values (" + userID + ", " + tempContact.getId() + ", '" + tempEmail.getAccountLabel() + "', '" + tempEmail.getEmail() + "')");
                    stmt.close();
                }

                // social media
                for ( SocialMedia tempSocial : tempContact.getContactsSocial() )
                {
                    stmt = dbConnection.createStatement();
                    stmt.executeUpdate("insert into social_media (userID, contactID, label, social_media) values (" + userID + ", " + tempContact.getId() + ", '" + tempSocial.getSocialLabel() + "', '" + tempSocial.getUsername() + "')");
                    stmt.close();
                }

                // custom list
                for ( CustomList tempCustom : tempContact.getContactCustom() )
                {
                    stmt = dbConnection.createStatement();
                    stmt.executeUpdate("insert into custom_list (userID, contactID, label, note) values (" + userID + ", " + tempContact.getId() + ", '" + tempCustom.getTitleDescriptor() + "', '" + tempCustom.getBody() + "')");
                    stmt.close();
                }
            }
            dbConnection.close();
        }
        catch (SQLException ex)
        {
            System.out.println("exception: sfsf");
        }
        catch (ClassNotFoundException ex)
        {
        }
    }
    
    public boolean verifyUser(String userName, String passWord)
    {        
        boolean isAuthenticated = false;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection myConnection = DriverManager.getConnection("jdbc:mysql://tonyblyler.com:3306/jcal", "root", "root");
            Statement stmt = myConnection.createStatement();    
            String sql = "Select * from users";
            ResultSet results = stmt.executeQuery(sql);
            //System.out.println("Course List:");
            //System.out.printf("%-2s %-8s %-6s %-30s %-10s %-10s %-20s %n", "ID", "Department", "Number", "Course Name", "Credits", "Sections" , "Major Notes");
            
            String dbUsername = new String();
            String dbPassword = new String();
            
            while(results.next())
            {
                System.out.print(results.getString("username")+"=" +userName+" "+results.getString("password")+"="+passWord);
                
                dbUsername = results.getString("username");
                dbPassword = results.getString("password");
                
                if(dbUsername.equals(userName) && dbPassword.equals(passWord))
                {
                    isAuthenticated = true;
                    System.out.println(isAuthenticated);
                }
            }
            
            if(isAuthenticated)
            {    
                int updateResult = stmt.executeUpdate("update users set authenticated = true where username=\'"+userName+"\' and password=\'"+passWord+"\'");
                results = stmt.executeQuery("select userID from users where username='" + userName + "' and password='" + passWord + "'");
                results.next();
                userID = results.getInt("userID");
            }
            
            results.close();
            stmt.close();
            myConnection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error Occurred: View Courses: Please check database connections");
        }
        catch (ClassNotFoundException e)
        {
        
        }        
        return isAuthenticated;
    }
    
    public static void main (String[] args)
    {
       
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection myConnection = DriverManager.getConnection("jdbc:mysql://tonyblyler.com:3306/jcal", "root", "root");
            Statement stmt = myConnection.createStatement();    
            String sql = "Select * from users";
            ResultSet results = stmt.executeQuery(sql);
            //System.out.println("Course List:");
            //System.out.printf("%-2s %-8s %-6s %-30s %-10s %-10s %-20s %n", "ID", "Department", "Number", "Course Name", "Credits", "Sections" , "Major Notes");
            
            while (results.next())
            {
                //System.out.printf("%-2d %-10s %-6d %-30s %-10d %-10d %-25s %n",results.getInt("course_ID"),results.getString("dept"),results.getInt("number"),results.getString("name"),results.getInt("credits"),results.getInt("num_sections"),results.getString("major_notes"));
                System.out.print(results.getString("username")+" "+results.getString("password"));
            }
            
            results.close();
            stmt.close();
            myConnection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error Occurred: View Courses: Please check database connections");
        }
        catch (ClassNotFoundException f)
        {
        
        }
        
    }
}