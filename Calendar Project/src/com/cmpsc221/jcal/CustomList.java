package com.cmpsc221.jcal;

/**
* Program: jCal
* Description: Class that stores the information for a custom list
*/

public class CustomList {
    
    private String titleDescriptor; // title for the list
    private String body;            // body for the list
    private int id;
    
    // default constructor 
    public CustomList(){
        titleDescriptor = "";
        body =  " ";         
    }
   
    // constructor to set all of the data members for the class 
    public CustomList(String newTitle, String newBody){
        this.titleDescriptor = newTitle;
        this.body = newBody;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // getter/setters 
    public String getTitleDescriptor() {
        return titleDescriptor;
    }
    public void setTitleDescriptor(String titleDescriptor) {
        this.titleDescriptor = titleDescriptor;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    // toString method to return the string version of the data members of the object
    public String toString()
    {
        return "Title: " + titleDescriptor + " Body: " + body;
    }
}
