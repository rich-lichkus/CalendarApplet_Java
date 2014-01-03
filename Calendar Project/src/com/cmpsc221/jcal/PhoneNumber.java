
package com.cmpsc221.jcal;
/**
* Program: jCal
* Description: Class that stores the Phone Number information
*/

public class PhoneNumber 
{
    private String numberLabel;		// the name of the phone number "home phone", "work phone", etc
    private String number;			// the number for the phone number
    private double extension;		// the extension number for the phone numbers
    private int id;

    // default constructor
    public PhoneNumber()
	{
        this.numberLabel = " ";
        this.number = " ";
        this.extension = 0;
    }
    
	// constructor to se the data members
    public PhoneNumber(String newLabel, String phonNum, double exten)
	{
        this.numberLabel = newLabel;
        this.number = phonNum;
        this.extension = exten;
    }
    
/**
 *  Getter and Setter Pairs
 */
    public String getNumberLabel()
	{
        return numberLabel;
    }
    public void setNumberLabel(String numberLabel) 
	{
        this.numberLabel = numberLabel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 
    public String getNumber() 
	{
        return number;
    }
    public void setNumber(String number) 
	{
        this.number = number;
    }

    
    public double getExtension() 
	{
        return extension;
    }
    public void setExtension(double extension) 
	{
        this.extension = extension;
    }
    
	// returns the string of the object
	public String ToString()
	{
		return "Number Label: " + numberLabel + ", Number: " + number + ", Number Extension: " + extension;
	}
}
