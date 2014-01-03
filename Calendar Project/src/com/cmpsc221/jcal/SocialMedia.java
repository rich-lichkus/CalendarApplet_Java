package com.cmpsc221.jcal;

/**
* Program: jCal
* Description: Class that stores the social media information
*/

public class SocialMedia {

    private String socialLabel;		// the name of the social site, "facebook","twiter", etc
    private String username;		// the user name of the corresponding socialLabel
    private int id;

    // default constructor
    public SocialMedia() {
        this.socialLabel = "";
        this.username = "";
    }

    // constructor to set all data member
    public SocialMedia(String sLabel, String username) {
        this.socialLabel = sLabel;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // getters/setters
    public String getSocialLabel() {
        return socialLabel;
    }

    public void setSocialLabel(String socialLabel) {
        this.socialLabel = socialLabel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // returns the string of the object
    public String toString() {
        return "Social Label: " + socialLabel + ", User name: " + username;
    }
}
