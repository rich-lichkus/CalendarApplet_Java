

/**
 * Program: jCal
 * Description: Class that stores addresses and their information
 */

package com.cmpsc221.jcal;

public class Address {
    
    private String addressLabel; // user label for the address
    private double streetNumber; // street number for the address
    private String streetName;   // stree name for the address
    private String city;         // city name for the address
    private String state;        // state name for the address
    private double zipcode;      // zip code for the address
    private String country;      // country for the adddress
    private int id;

    // default constructor
    public Address(){
        this.addressLabel = "";
        this.streetNumber = 0;
        this.streetName = "";
        this.city = "";
        this.state = "";
        this.zipcode = 0;
        this.country = "";
    }
    
    // constructor to set all data members
    public Address(String aLabel, double srtNum, String strName, String newCity, String newState, double zip, String country){
        this.addressLabel = aLabel;
        this.streetNumber = srtNum;
        this.streetName = strName;
        this.city = newCity;
        this.state = newState;
        this.zipcode = zip;
        this.country = country;
    }
    
    // Getters/Setters 
    public String getAddressLabel() {
        return addressLabel;
    }
    public void setAddressLabel(String addressLabel) {
        this.addressLabel = addressLabel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Double getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(Double streetNumber) {
        this.streetNumber = streetNumber;
    }


    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    
    public double getZipcode() {
        return zipcode;
    }
    public void setZipcode(double zipcode) {
        this.zipcode = zipcode;
    }

 
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    // returns the string of the object
    public String toString()
    {
        return "AddressLabel: " + addressLabel + " Street#: " + streetNumber + " StreetName: " + streetName + " City: " + city + " State: " + state + " ZipCode: " + zipcode + " Country: " + country;
    }
}
