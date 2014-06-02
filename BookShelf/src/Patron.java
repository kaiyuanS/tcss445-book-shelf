

public class Patron {

    private int myPatronID;
    private String myFirstName;
    private String myLastName;
    private String myPatronEmail;
    private String myPhoneNumber;
    private String myStreetAddress;
    private String myCity;
    private String myState;
    private String myZip;

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

    public int getPatronID() {
        return myPatronID;
    }

    public void setPatronID(int aPatronID) {
        myPatronID = aPatronID;
    }

    public String getFirstName() {
        return myFirstName;
    }

    public void setFirstName(String aFirstName) {
        myFirstName = aFirstName;
    }

    public String getLastName() {
        return myLastName;
    }

    public void setLastName(String aLastName) {
        myLastName = aLastName;
    }

    public String getPatronEmail() {
        return myPatronEmail;
    }

    public void setPatronEmail(String aPatronEmail) {
        myPatronEmail = aPatronEmail;
    }

    public String getPhoneNumber() {
        return myPhoneNumber;
    }

    public void setPhoneNumber(String aPhoneNumber) {
        myPhoneNumber = aPhoneNumber;
    }

    public String getStreetAddress() {
        return myStreetAddress;
    }

    public void setStreetAddress(String aStreetAddress) {
        myStreetAddress = aStreetAddress;
    }

    public String getCity() {
        return myCity;
    }

    public void setCity(String aCity) {
        myCity = aCity;
    }

    public String getState() {
        return myState;
    }

    public void setState(String aState) {
        myState = aState;
    }
    
    public String getZip() {
        return myZip;
    }
    
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
