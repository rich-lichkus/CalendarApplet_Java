package com.cmpsc221.jcal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Program: jCal
 * Description: Class that stores contact information
 */
public class Contacts {
   
    private String prefix;      // prefix of the person
    private String nickName;    // nickname for the person
    private String firstName;   // first name of the person
    private String middleName;  // middle name of the person
    private String lastName;    // last name of the person
    private String suffix;      // suffix of the person
    private String birthdate;
    
    private String jobTitle;    // job title of the person
    private String department;  // department of the person
    private String company;     // company name of the person
    private int id;
   
    //ArrayLists are dynmically sized
    private ArrayList<Email> contactsEmails;           // list of emails for the contacts 
    private ArrayList<PhoneNumber> contactsNumbers;    // list of numbers for the contacts
    private ArrayList<Address> contactAddresses;       // list of addresses for the contacts
    private ArrayList<SocialMedia> contactsSocial;     // list of social media information for the contacts
    private ArrayList<CustomList> contactCustom;       // list of misc things for contacts
        
    public Contacts()
    {
        id = 0;
        this.prefix = " ";
        this.nickName = " ";
        this.firstName = " ";
        this.middleName = " ";
        this.lastName = " ";
        this.suffix = " ";

        this.jobTitle = " ";
        this.department = " ";
        this.company = " ";

        birthdate = "";
        
        contactsEmails = new ArrayList<Email>();
        contactsNumbers = new ArrayList<PhoneNumber>();
        contactAddresses = new ArrayList<Address>();
        contactsSocial = new ArrayList<SocialMedia>();
        contactCustom = new ArrayList<CustomList>();  
    } 
    
    public Contacts(String prefix, String nickname, String first, String middle, String last, String suffix, String jobTitle, String department, String company, String birthdate)
    {
        id = 0;
        this.prefix = prefix;
        this.nickName = nickname;
        this.firstName = first;
        this.middleName = middle;
        this.lastName = last;
        this.suffix = suffix;

        this.jobTitle = jobTitle;
        this.department = department;
        this.company = company;

        this.birthdate = birthdate;
        
        contactsEmails = new ArrayList<Email>();
        contactsNumbers = new ArrayList<PhoneNumber>();
        contactAddresses = new ArrayList<Address>();
        contactsSocial = new ArrayList<SocialMedia>();
        contactCustom = new ArrayList<CustomList>();
    }

    /**
     *  Getters and Setters
     */
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean idIsSet() {
        return (id != 0);
    }

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
        
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
   
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    
    public void printContactName(){
        System.out.println(prefix+" "+firstName+" ("+nickName+") "+middleName+" "+lastName+" "+suffix+ "\nJob Title: \t"+jobTitle+"\nDepartment: \t"+department+"\nCompany: \t"+company);          
    }
    
        
    /**
     * Email Functions
     *  - Add       (std args)
     *  - Edit      (printed list number, std args)
     *  - Remove    (printed list number)
     *  - Print     ()
     */
    public void addEmail(String accountLabel, String email)
    {
        contactsEmails.add(new Email(accountLabel, email));
    }
    public void editEmail(int numEmail, String accountLabel, String email)
    {
        contactsEmails.set(numEmail,new Email(accountLabel, email));
    }  
    public void removeEmail(int numEmail)
    {
        contactsEmails.remove(numEmail);
    }
    public String toStringEmails()
    {
        String resultString = new String();
        for (Email temp : contactsEmails)
        {
            resultString = resultString + temp.getAccountLabel()+": "+temp.getEmail()+"\n";    
        }   
        return resultString+"\n";
    }    
    public ArrayList<Email> getContactsEmails() {
        return contactsEmails;
    }
    public void setContactsEmails(ArrayList<Email> contactsEmails) {
        this.contactsEmails = contactsEmails;
    }
    
    /**
     * PhoneNumber Functions
     *  - Add       (std args)
     *  - Edit      (printed list number, std args)
     *  - Remove    (printed list number)
     *  - Print     ()
     */
    
    public void addPhoneNumber(String accountLabel, String phonNum, double exten)
    {
        contactsNumbers.add(new PhoneNumber(accountLabel, phonNum, exten));
    }
    public void editPhoneNumber(int numNumbers, String accountLabel, String phonNum, double exten)
    {
        contactsNumbers.set(numNumbers, new PhoneNumber(accountLabel, phonNum, exten));
    }  
    public void removePhoneNumber(int numPhone)
    {
        contactsNumbers.remove(numPhone);
    }
    public String toStringPhoneNumList()
    {
        String tempString = new String();
        for (PhoneNumber temp : contactsNumbers)
        {
            tempString = (tempString + temp.getNumberLabel()+": "+temp.getNumber()+" "+temp.getExtension()+"\n");
        }   
        return tempString + "\n";
    } 
    
    public ArrayList<PhoneNumber> getContactsNumbers() {
        return contactsNumbers;
    }
    public void setContactsNumbers(ArrayList<PhoneNumber> contactsNumbers) {
        this.contactsNumbers = contactsNumbers;
    }
    
    
    /**
     * Address Functions
     *  - Add       (std args)
     *  - Edit      (printed list number, std args)
     *  - Remove    (printed list number)
     *  - Print     ()
     */
    public void addAddress(String label, double streetNum, String streetName, String city, String state, double zip, String country)
    {
        contactAddresses.add(new Address(label,streetNum,streetName,city,state,zip,country));
    }
    public void editAddress(int number, String label, double streetNum, String streetName, String city, String state, double zip, String country)
    {
        contactAddresses.set(number,new Address(label,streetNum,streetName,city,state,zip,country));
    }
    public Address getAddress(int index)
    {
        return contactAddresses.get(index);
    }
    public void removeAddress(int number)
    {
        contactAddresses.remove(number);
    }
    public String toStringAddress()
    {
        String tempString = new String();
        for (Address temp : contactAddresses)
        {
            tempString = (tempString + temp.getAddressLabel()+": "+temp.getStreetNumber()+" "+temp.getStreetName()+" "+temp.getCity()+", "+temp.getState()+" "+temp.getZipcode()+" "+temp.getCountry()+"\n" );
        }  
        return tempString + "\n";
    }

    public ArrayList<Address> getContactAddresses() {
        return contactAddresses;
    }
    public void setContactAddresses(ArrayList<Address> contactAddresses) {
        this.contactAddresses = contactAddresses;
    }
    
    /**
     * SocialMedia Functions
     *  - Add       (std args)
     *  - Edit      (printed list number, std args)
     *  - Remove    (printed list number)
     *  - Print     ()
     */
     public void addSocialMedia(String label, String username)
    {
        contactsSocial.add(new SocialMedia(label,username));
    }
     public void editSocialMedia(int number, String label, String username)
    {
        contactsSocial.set(number,new SocialMedia(label,username));
    }
     public void removeSocialMedia(int number)
    {
        contactsSocial.remove(number);
    }
     public String toStringSocialMedia()
    {
        String tempString = new String();
        for (SocialMedia temp : contactsSocial)
        {
            tempString = (tempString + temp.getSocialLabel()+": "+temp.getUsername()+"\n");
        }  
        return tempString+ "\n";
    }
    public ArrayList<SocialMedia> getContactsSocial() {
        return contactsSocial;
    }
    public void setContactsSocial(ArrayList<SocialMedia> contactsSocial) {
        this.contactsSocial = contactsSocial;
    }

    /**
     * CustomList Functions
     *  - Add       (std args)
     *  - Edit      (printed list number, std args)
     *  - Remove    (pass printed list number)
     *  - Print     ()
     */
    
    
    public void addCustomList(String title, String body)
    {
        contactCustom.add(new CustomList(title,body));
    }
    public void editCustomList(int customNum, String title, String body)
    {
        contactCustom.set(customNum,new CustomList(title,body));
    }
    public void removeCustomList(int numCustom)
    {
        contactCustom.remove(numCustom);
    }
    public String toStringCustomList()
    {
        String tempString = new String();
        for (CustomList temp : contactCustom)
        {
            tempString = (temp.getTitleDescriptor()+"\t"+temp.getBody()+"\n");
        }
        return tempString;
    }
    public ArrayList<CustomList> getContactCustom() {
        return contactCustom;
    }
    public void setContactCustom(ArrayList<CustomList> contactCustom) {
        this.contactCustom = contactCustom;
    }
    
    // toString method to return a string with all of the information of the data members inside of it
    public String toString()
    {
        String returnString = "Prefix: " + prefix + " nickname: " + nickName + " middleName: " + middleName + " lastName: " + lastName + " suffix: " + suffix + " Job title: " + jobTitle + " department: " + department + " company: " + company;
        returnString += "Birthdate: " + birthdate;
        returnString += "\nContact emails: " + contactsEmails.size() + " ContactNumbers: " + contactsNumbers.size() + " Contact Addresses: " + contactAddresses.size() + " Contact social info: " + contactsSocial.size() + " Misc info: " + contactCustom.size();

        return returnString;
    }
    
}
