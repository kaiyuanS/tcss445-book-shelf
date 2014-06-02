/**
 * The Publisher class holds all the information related towards a book publishing company. 
 * 
 * @author Kevin Alexander
 * @version May 31, 2014
 *
 */
public class Publisher {
	
	/**
	 * The publishing company's name.
	 */
	private String publisherName;
	/**
	 * The publishing company's street.
	 */
	private String publisherStreet;
	/**
	 * The publishing company's city.
	 */
	private String publisherCity;
	/**
	 * The publishing company's state.
	 */
	private String publisherState;
	/**
	 * The publishing company's ZipCode.
	 */
	private String publisherZip;
	/**
	 * The publishing company's country.
	 */
	private String publisherCountry;
	/**
	 * The year in which the publishing company was founded.
	 */
	private int publisherFounded;
	
	/**
	 * The Publisher constructor. It takes in values for all its private fields and sets
	 * them accordingly.
	 * 
	 * @param aName The publishing company's name.
	 * @param aStreet The publishing company's street.
	 * @param aCity The publishing company's city.
	 * @param aZipCode The publishing company's ZipCode.
	 * @param aState The publishing company's state.
	 * @param aCountry The publishing company's country.
	 * @param theFoundedYear The year in which the publishing company was founded.
	 */
	public Publisher(String aName, String aStreet, String aCity, 
					 String aState, String aZipCode, String aCountry, int theFoundedYear) {
		publisherName = aName;
		publisherStreet = aStreet;
		publisherCity = aCity;
		publisherState = aState;
		publisherCountry = aCountry;
		publisherZip = aZipCode;
		publisherFounded = theFoundedYear;
	}
	
	/**
	 * The Publisher constructor. It takes in no values and sets all string values to 
	 * "Unknown" and the year founded to 0.
	 */
	public Publisher() {
		this("Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", 0);
	}
	
	/**
	 * This method sets the name of the publishing company.
	 * @param aName The new name of the publishing company.
	 */
	public void setName(String aName) {
		publisherName = aName;
	}
	
	/**
	 * This method sets the street of the publishing company.
	 * @param aStreet The new street of the publishing company.
	 */
	public void setStreet(String aStreet) {
		publisherStreet = aStreet;
	}
	
	/**
	 * This method sets the city of the publishing company.
	 * @param aCity The new city of the publishing company.
	 */
	public void setCity(String aCity) {
		publisherCity = aCity;
	}
	
	/**
	 * This method sets the state of the publishing company.
	 * @param aState The new state of the publishing company.
	 */
	public void setState(String aState) {
		publisherState = aState;
	}
	
	/**
	 * This method sets the ZipCode of the publishing company.
	 * @param aZipCode The new ZipCode of the publishing company.
	 */
	public void setZipCode(String aZipCode) {
		publisherZip = aZipCode;
	}
	
	/**
	 * This method sets the country of the publishing company.
	 * @param aCountry The new country of the publishing company.
	 */
	public void setCountry(String aCountry) {
		publisherCountry = aCountry;
	}
	
	/**
	 * This method sets the year that the publishing company was founded.
	 * @param theFoundedYear The new year that the publishing company was founded in.
	 */
	public void setFoundedYear(int theFoundedYear) {
		publisherFounded = theFoundedYear;
	}
	
	
	/**
	 * This method returns the publishing company's name.
	 * @return The publishing company's name.
	 */
	public String getName() {
		return publisherName;
	}
	
	/**
	 * This method returns the publishing company's street.
	 * @return The publishing company's street.
	 */
	public String getStreet() {
		return publisherStreet;
	}
	
	/**
	 * This method returns the publishing company's city.
	 * @return The publishing company's city.
	 */
	public String getCity() {
		return publisherCity;
	}
	
	/**
	 * This method returns the publishing company's state.
	 * @return The publishing company's state.
	 */
	public String getState() {
		return publisherState;
	}
	
	/**
	 * This method returns the publishing company's ZipCode.
	 * @return The publishing company's ZipCode.
	 */
	public String getZipCode() {
		return publisherZip;
	}
	
	/**
	 * This method returns the publishing company's country.
	 * @return The publishing company's country.
	 */
	public String getCountry() {
		return publisherCountry;
	}
	
	/**
	 * This method returns the year in which the publishing company was founded.
	 * @return The year in which the publishing company was founded.
	 */
	public int getYearFounded() {
		return publisherFounded;
	}
}
