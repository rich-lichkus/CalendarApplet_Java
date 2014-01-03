package com.cmpsc221.jcal;

/**
 * Program: jCal
 * Description: Class that stores emails
 */
public class Email {
   
    private String accountLabel; // label for the email
    private String email;        // the email address
    private int id;

    // default constructor 
    public Email(){
        accountLabel = " ";
        email = " ";
    }
   
    // constructor to initialize the data members 
    public Email(String accountLabel, String email){
        this.accountLabel = accountLabel;
        this.email = email;
    }
    
    // getters and setters 
    public String getAccountLabel() {
        return accountLabel;
    }
    public void setAccountLabel(String accountLabel) {
        this.accountLabel = accountLabel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // toString method to return the data members of the object
    public String toString()
    {
        return "Account label: " + accountLabel + " Email: " + email;
    }    
}
