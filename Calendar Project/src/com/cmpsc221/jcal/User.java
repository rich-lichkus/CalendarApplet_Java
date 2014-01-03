package com.cmpsc221.jcal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class User {

    //Profile information for user
    private Contacts userContactCard;    // Contact information
    private String userName;             // Stores the username
    private boolean authenticated;       // Stores whether the login was successful
    private String password;
    
    //Stores users calendar and contacts
    private ArrayList<JCal> userCalendars;
    private ArrayList<Contacts> userContacts;

    // default constructor
    public User() {
        userContactCard = new Contacts();
        userName = "";
        userCalendars = new ArrayList<JCal>();
        userContacts = new ArrayList<Contacts>();
        authenticated = false;
    }
    
    //Constructor
    public User(String userName) {
        this.userName = userName;                       // Set username

        this.userCalendars = new ArrayList<JCal>();     //
        userCalendars.add(new JCal());                  // Call calendar constructor  

        this.userContacts = new ArrayList<Contacts>();  // 
        userContactCard = new Contacts();               // Create User Contact Card
        authenticated = false;
    }

    public void setUsername(String username)
    {
        this.userName = username;
    }
    
    public String getUsername()
    {
        return userName;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public boolean userExists() {
        // use SQL to search for the user, if they don't exist return false
        // for now, return true...
        return true;
    }

    public boolean createUser(String password) {
        // use SQL to store password and user name
        /*
         SQL store username and password.hashCode();
         */
        // temporarily return true
        return true;
    }

    public void resetPassword(String newPassword) {
        // use SQL to replace the password for the user
    }

    public void removeUser() {
        // remove user from the SQL database
    }

    public boolean authenticate(String password) {
        // use SQL to get the stored password and username
        // here is some fake code in the mean time...
		/*
         search SQL for user's hashed password...
         */
        // for now, password is qwerty
        if (-946852072 == password.hashCode() && userName == "BobbySmith") {
            authenticated = true;
        } else {
            authenticated = false;
        }
        return authenticated;
    }
    
    public void setSampleUserContactCard() {
        userContactCard.setPrefix("Dr.");
        userContactCard.setFirstName("Robert");
        userContactCard.setNickName("Bob");
        userContactCard.setMiddleName("John");
        userContactCard.setLastName("Smith");
        userContactCard.setSuffix("III");
        userContactCard.setJobTitle("CEO jCal Industries");
    }

    public void setUserContactCard(String prefix, String first, String nickname, String middle, String last) {
        userContactCard.setPrefix(prefix);
        userContactCard.setFirstName(first);
        userContactCard.setNickName(nickname);
        userContactCard.setMiddleName(middle);
        userContactCard.setLastName(last);
    }

    // Add and Edit Contact
    public void addContact(String prefix, String first, String nickname, String middle, String last, String suffix, String jobTitle, String department, String company, String birthdate) {
        userContacts.add(new Contacts(prefix, nickname, first, middle, last, suffix, jobTitle, department, company, birthdate));
    }

    public void editContact(int contactNum, String prefix, String first, String nickname, String middle, String last, String suffix, String jobTitle, String department, String company, String birthdate) {
        userContacts.get(contactNum).setPrefix(prefix);
        userContacts.get(contactNum).setFirstName(first);
        userContacts.get(contactNum).setNickName(nickname);
        userContacts.get(contactNum).setMiddleName(middle);
        userContacts.get(contactNum).setLastName(last);
        userContacts.get(contactNum).setSuffix(suffix);
        userContacts.get(contactNum).setJobTitle(jobTitle);
        userContacts.get(contactNum).setDepartment(department);
        userContacts.get(contactNum).setCompany(company);
        userContacts.get(contactNum).setBirthdate(birthdate);
    }
    
    public ArrayList<String> getContactList() 
    {
        ArrayList<String> contactList = new ArrayList<String>();
        for (Contacts tempContact : userContacts) 
        {
            contactList.add(tempContact.getFirstName() + " " + tempContact.getLastName());
        }
        return contactList;
    }
    
    public Contacts getContactAtIndex(int index)
    {
        return userContacts.get(index);
    }
    
    public Vector getVectorContactList()
    {
        Vector vectorContacts = new Vector();
        for (Contacts eachContact : userContacts )
        {
             vectorContacts.add(eachContact.getFirstName() + " " + eachContact.getLastName());
        }
        
        return vectorContacts;
    }
    
    public DefaultTableModel getAddressModelContactAtIndex(int index)
    {   
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"Title",
                                           "Street Number",
                                           "Street Name",
                                           "City",
                                           "State",
                                           "Zipcode",
                                           "Country"});
        
        for(Address eachAddress : userContacts.get(index).getContactAddresses())
        {
            model.addRow(new String[] {eachAddress.getAddressLabel(),
                                       Double.toString(eachAddress.getStreetNumber()),
                                       eachAddress.getStreetName(),
                                       eachAddress.getCity(),
                                       eachAddress.getState(),
                                       Double.toString(eachAddress.getZipcode()),
                                       eachAddress.getCountry()});
        }
        return model;
    }
    
    
    public DefaultTableModel getPhoneNumberModelContactAtIndex(int index)
    {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"Title","Phone Number","Extension"});
        for(PhoneNumber eachPhoneNum : userContacts.get(index).getContactsNumbers())
        {
            model.addRow(new String[] {eachPhoneNum.getNumberLabel(),
                                       eachPhoneNum.getNumber(),
                                       Double.toString(eachPhoneNum.getExtension())});
        }
        return model;
    }
    
    public DefaultTableModel getEmailModelContactAtIndex(int index)
    {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"Title","Email"});
        for(Email eachEmail : userContacts.get(index).getContactsEmails())
        {
            model.addRow(new String[] {eachEmail.getAccountLabel(),
                                       eachEmail.getEmail()});
            
        }
        return model;
    }
    
    public DefaultTableModel getSocialModelContactAtIndex(int index)
    {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"Title","Social"});
        for(SocialMedia eachSocial : userContacts.get(index).getContactsSocial())
        {
            model.addRow(new String[] {eachSocial.getSocialLabel(),
                                       eachSocial.getUsername()});    
        }
        return model;
    }
    
    public Address getContactAddress(int contactIndex, int addressIndex)
    {
        ArrayList<Address> temp = new ArrayList<Address>();
        temp = userContacts.get(contactIndex).getContactAddresses();
        return temp.get(addressIndex);
    }
    
    
    public PhoneNumber getContactPhoneNumber(int contactIndex, int numberIndex)
    {
        ArrayList<PhoneNumber> temp = new ArrayList<PhoneNumber>();
        temp = userContacts.get(contactIndex).getContactsNumbers();
        return temp.get(numberIndex);
    }
    
    
    public CustomList getContactCustom(int contactIndex, int customIndex)
    {
        ArrayList<CustomList> temp = new ArrayList<CustomList>();
        temp = userContacts.get(contactIndex).getContactCustom();
        return temp.get(customIndex);
    }
    
    public SocialMedia getContactSM(int contactIndex, int smIndex)
    {
        ArrayList<SocialMedia> temp = new ArrayList<SocialMedia>();
        temp = userContacts.get(contactIndex).getContactsSocial();
        return temp.get(smIndex);
    }
    
    public Email getContactEmail(int contactIndex, int emailIndex)
    {
        ArrayList<Email> temp = new ArrayList<Email>();
        temp = userContacts.get(contactIndex).getContactsEmails();
        return temp.get(emailIndex);
    }
     
     
    public DefaultTableModel getCustomModelContactAtIndex(int index)
    {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"Title","Description"});
        for(CustomList eachCustom : userContacts.get(index).getContactCustom())
        {
            model.addRow(new String[] {eachCustom.getTitleDescriptor(),
                                       eachCustom.getBody()});    
        }
        return model;
    }
    
    /**
     * Edit Existing Contact Addresses
     */
    
    public Vector getVectorContactAddress(int contactIndex)
    {
        Vector vector = new Vector();
        for(Address eachAddress : userContacts.get(contactIndex).getContactAddresses())
        {
            vector.add(eachAddress.getAddressLabel());
        }
        return vector;
    }
    
    
    public void addContactAddress(int contactNum, String label, double streetNum, String streetName, String city, String state, double zip, String country) {
        userContacts.get(contactNum - 1).addAddress(label, streetNum, streetName, city, state, zip, country);
    }

    public Address getContactAddressAtIndex(int contactNum, int index)
    {
        return userContacts.get(contactNum-1).getAddress(index);
    }

    public void editContactAddress(int contactNum, int addressNum, String label, double streetNum, String streetName, String city, String state, double zip, String country) {
        userContacts.get(contactNum - 1).editAddress(addressNum, label, streetNum, streetName, city, state, zip, country);
    }

    public void removeContactAddress(int contactNum, int addressNum) {
        userContacts.get(contactNum - 1).removeAddress(addressNum);
    }

    public void printContactAddress(int contactNum) {
        userContacts.get(contactNum - 1).toStringAddress();
    }

    /**
     * Edit Existing Contact Phone Numbers
     */
    public Vector getVectorContactPhoneNum(int contactIndex)
    {
        Vector vector = new Vector();
        for(PhoneNumber eachPhone : userContacts.get(contactIndex).getContactsNumbers())
        {
            vector.add(eachPhone.getNumberLabel());
        }
        return vector;
    }
    public void addContactPhoneNum(int contactNum, String accountLabel, String phonNum, double exten) {
        userContacts.get(contactNum - 1).addPhoneNumber(accountLabel, phonNum, exten);
    }

    public void editContactPhoneNum(int contactNum, int numPhonNum, String accountLabel, String phonNum, double exten) {
        userContacts.get(contactNum - 1).editPhoneNumber(numPhonNum, accountLabel, phonNum, exten);
    }

    public void removeContactPhoneNum(int contactNum, int numPhonNum) {
        userContacts.get(contactNum - 1).removePhoneNumber(numPhonNum);
    }

    public void printContactPhonNum(int contactNum) {
        for (PhoneNumber each : userContacts.get(contactNum - 1).getContactsNumbers())
        {
            System.out.println(each.getNumberLabel()+ " " + each.getNumber() + " " + each.getExtension());
        }
    }

    /**
     * Edit Existing Contact Emails
     */
    public Vector getVectorContactEmail(int contactIndex)
    {
        Vector vector = new Vector();
        for(Email eachEmail : userContacts.get(contactIndex).getContactsEmails())
        {
            vector.add(eachEmail.getAccountLabel());
        }
        return vector;
    }
    public void addContactEmail(int contactNum, String accountLabel, String email) {
        userContacts.get(contactNum - 1).addEmail(accountLabel, email);
    }

    public void editContactEmail(int contactNum, int emailNum, String accountLabel, String email) {
        userContacts.get(contactNum - 1).editEmail(emailNum, accountLabel, email);
    }

    public void removeContactEmail(int contactNum, int emailNum) {
        userContacts.get(contactNum - 1).removeEmail(emailNum);
    }

    public void printContactEmail(int contactNum) {
        userContacts.get(contactNum - 1).toStringEmails();
    }

    /**
     * Edit Existing Contact Social Media
     */
    public Vector getVectorContactSM(int contactIndex)
    {
        Vector vector = new Vector();
        for(SocialMedia eachSM : userContacts.get(contactIndex).getContactsSocial())
        {
            vector.add(eachSM.getSocialLabel());
        }
        return vector;
    }
    public void addContactSocialMedia(int contactNum, String sLabel, String username) {
        userContacts.get(contactNum - 1).addSocialMedia(sLabel, username);
    }

    public void editContactSocialMedia(int contactNum, int socialMediaNum, String sLabel, String username) {
        userContacts.get(contactNum - 1).editSocialMedia(socialMediaNum, sLabel, username);
    }

    public void removeContactSocialMedia(int contactNum, int socialMediaNum) {
        userContacts.get(contactNum - 1).removeSocialMedia(socialMediaNum);
    }

    public void printContactSocailMedia(int contactNum) {
        userContacts.get(contactNum - 1).toStringSocialMedia();
    }

    /**
     * Edit Existing Contact Custom List
     */
    public Vector getVectorContactCustom(int contactIndex)
    {
        Vector vector = new Vector();
        for(CustomList eachCustom : userContacts.get(contactIndex).getContactCustom())
        {
            vector.add(eachCustom.getTitleDescriptor());
        }
        return vector;
    }
    public void addContactCustomList(int contactNum, String title, String body) {
        userContacts.get(contactNum - 1).addCustomList(title, body);
    }

    public void editContactCustomList(int contactNum, int customNum, String title, String body) {
        userContacts.get(contactNum - 1).editCustomList(customNum, title, body);
    }

    public void removeContactCustomList(int contactNum, int customNum) {
        userContacts.get(contactNum - 1).removeCustomList(customNum);
    }

    public void printContactCustomList(int contactNum) {
        userContacts.get(contactNum - 1).toStringCustomList();
    }

    public void removeContact(int contactNum) {
        userContacts.remove(contactNum);
    }

    public void printContactList() {
        int i = 1;
        for (Contacts temp : userContacts) {
            System.out.println(i + ". " + temp.getFirstName() + " " + temp.getLastName());
            i++;
        }
    }

    public void printContactCard(int contactNum) {
        userContacts.get(contactNum - 1).printContactName();
        userContacts.get(contactNum - 1).toStringAddress();
        userContacts.get(contactNum - 1).toStringPhoneNumList();
        userContacts.get(contactNum - 1).toStringEmails();
        userContacts.get(contactNum - 1).toStringSocialMedia();
        userContacts.get(contactNum - 1).toStringCustomList();
    }

    public ArrayList<Contacts> getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(ArrayList<Contacts> newUserContacts) {
        this.userContacts = newUserContacts;
    }

    // Add and Edit Calendar
    public void addCalendar(String title) {
        userCalendars.add(new JCal(title));
    }

    public JCal getCalendar(int index)
    {
        return userCalendars.get(index);
    }
    
    public void editCalendar(int calNum, String title) {
        userCalendars.set(calNum - 1, new JCal(title));
    }
    //*****

    public void addCalendarEventGroup(int calNum, String title) {
        userCalendars.get(calNum - 1).addEventGroup(title);
    }

    public EventGroup getCalendarEventGroup(int calNum, int egNum) {
        return userCalendars.get(calNum - 1).getEventGroupAtIndex(egNum);
    }

    public void editCalendarEventGroup(int calNum, int egNum) {
        userCalendars.get(calNum - 1).editEventGroup(egNum);
    }
    //************

    public void addEvent(int calNum, int egNum, Event newEvent) {
        userCalendars.get(calNum - 1).editEventGroupAddEvent(egNum - 1, newEvent);
    }
    public void printEventList(int calNum, int egNum) {
        userCalendars.get(calNum - 1).printEventList(egNum - 1);
    }
    //*****

    public void removeCalendarEventGroup(int calNum, int egNum) {
        userCalendars.get(calNum - 1).removeEventGroup(egNum - 1);
    }

    public void printCalendarEventGroupList(int calNum) {
        userCalendars.get(calNum - 1).printEventGroupList();
    }
    //*****

    public void removeCalendar(int calNum) {
        userCalendars.remove(calNum - 1);
    }

    public void printCalendarList() {
        int i = 1;
        for (JCal temp : userCalendars) {
            System.out.println(i + ". " + temp.getTitle());
            i++;
        }
    }
    
    public void printIsSelectedEGList() {
        int i = 1;
        int j = 0;
        for (JCal temp : userCalendars) {
            for(EventGroup tempEG : userCalendars.get(j).getEventGroup())
            {
                if(tempEG.isCbxIsSelected())
                {
                    System.out.println(i + ". " + tempEG.getTitle());
                    i++;
                }
                j++;
            }
        }
    }
    
    public void printIsSelectedCalendarList() {
        int i = 1;
        for (JCal temp : userCalendars) {
            if(temp.isCbxIsSelected())
            {
                System.out.println(i + ". " + temp.getTitle());
                i++;
            }
        }
    }
    
    public Vector getStringCalendarList() {
        int i = 1;
        Vector calendarList = new Vector(); 
        for (JCal temp : userCalendars) {
            calendarList.add(temp.getTitle());
            i++;
        }
        return calendarList;
    }
    
    public Vector getVectorEGList(int numCal) {
        int i = 1;
        Vector egList = new Vector(); 
        for (EventGroup temp : userCalendars.get(numCal).getEventGroup()) {
            egList.add(temp.getTitle());
            i++;
        }
        return egList;
    }

    public ArrayList<String> getCalendarList() 
    {
        ArrayList<String> calendarList = new ArrayList<String>();
        for (JCal temp : userCalendars) 
        {
            calendarList.add(temp.getTitle());
        }
        return calendarList;
    }
    
    
    public ArrayList<JCal> getUserCalendars() {
        return userCalendars;
    }

    public void setUserCalendar(ArrayList<JCal> newUserCalendars) {
        this.userCalendars = newUserCalendars;
    }

    public ArrayList<String> getContactsArray()
    {
        ArrayList<String> contactArray = new ArrayList<String>();
        
        for(Contacts tempContact : userContacts)
        {
            contactArray.add(tempContact.getFirstName() + tempContact.getLastName());
        }
        return contactArray;
    }
    
    public Contacts getSelectContact(int index )
    {
        return userContacts.get(index);
    }
   
    public String toString() {
        String returnString;

        returnString = "Username: " + userName + "\nContact Card:\n" + userContactCard.getPrefix() + " " + userContactCard.getFirstName();
        returnString += " (" + userContactCard.getNickName() + ") " + userContactCard.getMiddleName() + " " + userContactCard.getLastName() + " " + userContactCard.getSuffix();
        //returnString += Address class toString() 
        //returnString += 

        return returnString;
    }
}
