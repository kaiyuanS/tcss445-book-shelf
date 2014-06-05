/**
 * This class represents a information of a patron
 * @author Kaiyuan
 * @version Spr. 2014
 */

public class Patron {

	/** the ID number of a patron */
    private int myPatronID;
    
    /** the first name of a patron */
    private String myFirstName;
    
    /** the last name of a patron */
    private String myLastName;
    
    /** the email address of a patron */
    private String myPatronEmail;
    
    /** the phone number of a patron */
    private String myPhoneNumber;
    
    /** the street addressa of a patron */
    private String myStreetAddress;
    
    /** the city of apatron */
    private String myCity;
    
    /** the state of a patron */
    private String myState;
    
    /** the zip code of a patron */
    private String myZip;

    /**
     * the constructor of a patron
     * @param aPatronID the patron id
     * @param aFirstName the first name of a patron
     * @param aLastName the last name of a patron
     * @param aPatronEmail the email of a patron
     * @param aPhoneNumber the phone number of a patron
     * @param aStreetAddress the street address of a patron
     * @param aCity the city of a patron
     * @param aState the state of a patron
     * @param aZip the zip code of a patron
     */
    public Patron(int aPatronID, String aFirstName, String aLastName,
            String aPatronEmail, String aPhoneNumber, String aStreetAddress,
            String aCity, String aState, String aZip) {
        myPatronID = aPatronID;
        myFirstName = aFirstName;
        myLastName = aLastName;
        myPatronEmail = aPatronEmail;
        myPhoneNumber = aPhoneNumber;
        myStreetAddress = aStreetAddress;
        myCity = aCity;
        myState = aState;
        myZip = aZip;
    }

    /**
     * This method get the ID of a patron
     * @return the ID of a patron
     */
    public int getPatronID() {
        return myPatronID;
    }

    /**
     * this method set a ID to apatron
     * @param aPatronID the ID of a patron
     */
    public void setPatronID(int aPatronID) {
        myPatronID = aPatronID;
    }

    /**
     * this method get the first name of a patron
     * @return the first of a patron
     */
    public String getFirstName() {
        return myFirstName;
    }

    /**
     * this method set the first name of a patron
     * @param aFirstName the first name of a patron
     */
    public void setFirstName(String aFirstName) {
        myFirstName = aFirstName;
    }

    /**
     * this method gets the last name of a patron
     * @return the first name of a patron
     */
    public String getLastName() {
        return myLastName;
    }

    /**
     * this method gets a last name of a patron
     * @param aLastName the last name of a patron
     */
    public void setLastName(String aLastName) {
        myLastName = aLastName;
    }

    /**
     * this method get a email of a patron
     * @return the email of a patron
     */
    public String getPatronEmail() {
        return myPatronEmail;
    }

    /**
     * this method set the email of a patron
     * @param aPatronEmail the email of a patron
     */
    public void setPatronEmail(String aPatronEmail) {
        myPatronEmail = aPatronEmail;
    }

    /**
     * this method gets a phone number of patron
     * @return the phone number of a patron
     */
    public String getPhoneNumber() {
        return myPhoneNumber;
    }

    /**
     * this method set phone number of a patron
     * @param aPhoneNumber the phone number of a patron
     */
    public void setPhoneNumber(String aPhoneNumber) {
        myPhoneNumber = aPhoneNumber;
    }

    /**
     * this method gets street address of a patron
     * @return the street address of a patron
     */
    public String getStreetAddress() {
        return myStreetAddress;
    }

    /**
     * this method sets the street address of a patron
     * @param aStreetAddress the steet of a patron
     */
    public void setStreetAddress(String aStreetAddress) {
        myStreetAddress = aStreetAddress;
    }

    /** 
     * this method gets the city of a patron
     * @return the city of a patron
     */
    public String getCity() {
        return myCity;
    }

    /**
     * this method sets a city of a patron
     * @param aCity the city of a patron
     */
    public void setCity(String aCity) {
        myCity = aCity;
    }

    /**
     * this method gets the state of a patron
     * @return the state of a patron
     */
    public String getState() {
        return myState;
    }

    /**
     * this method sets the state of a patron 
     * @param aState the state of a patron
     */
    public void setState(String aState) {
        myState = aState;
    }
    
    /**
     * this method sets the zip code of a patron
     * @return the zip code of a patron
     */
    public String getZip() {
        return myZip;
    }
    
    /**
     * this method sets a zip code of a patron
     * @param aZip the zip code of a patron
     */
    public void setZip(String aZip) {
        myZip = aZip;
    }

    @Override
    public String toString() {
        return "Patron\nPatronID: " + myPatronID + "\nFirst Name: " + myFirstName
                + "\nLast Name: " + myLastName + "\nPatron Enail: " + myPatronEmail
                + "\nPhone Number: " + myPhoneNumber + "\nStreet Address: " + myStreetAddress
                + "\nCity: " + myCity + "\nState: " + myState + "\nzip: " + myZip;
    }
}
