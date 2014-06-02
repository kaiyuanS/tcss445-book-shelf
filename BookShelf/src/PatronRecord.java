import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * The PatronRecord class holds all the information related to when a patron will check out
 * a book.
 * @author Kevin Alexander
 * @version May 31, 2014
 *
 */
public class PatronRecord {
	/**
	 * The static recordNum for the recordID.
	 */
	private static int recordNum = 0;
	/**
	 * The ID of the current record.
	 */
	private int recordID;
	/**
	 * The ID of the patron for this record.
	 */
	private int patronID;
	/**
	 * The ID of the book for this record.
	 */
	private int bookID;
	/**
	 * The date that this book was borrowed by.
	 */
	private Date borrowBy;
	/**
	 * The date that this book was/will be returned by.
	 */
	private Date returnBy;
	
	/**
	 * The PatronRecord constructor that will be used when populating a PatronRecord with 
	 * already existing information.
	 * @param aRecordID The ID of the current PatronRecord.
	 * @param aPatronID The ID of the patron for this record.
	 * @param aBookID The ID of the book for this record.
	 * @param aBorrowByDate The date that this book was borrowed by.
	 * @param aReturnByDate The date that this book was/will be returned by.
	 */
	public PatronRecord(int aRecordID, int aPatronID, int aBookID, Date aBorrowByDate, 
						Date aReturnByDate) {
		recordID = aRecordID;
		patronID = aPatronID;
		bookID = aBookID;
		borrowBy = aBorrowByDate;
		returnBy = aReturnByDate;
	}
	
	/**
	 * The PatronRecord constructor which will be used when new PatronRecords need to be
	 * made.
	 * @param aPatronID The ID of the patron for this record.
	 * @param aBookID The ID of the book for this record.
	 */
	public PatronRecord(int aPatronID, int aBookID) {
		this(recordNum, aPatronID, aBookID, new Date(GregorianCalendar.getInstance().getTimeInMillis()), null);
		recordNum++;
	}
	
	
	/**
	 * This method sets the recordID field to the given recordID.
	 * @param aRecordID The new recordID.
	 */
	public void setRecordID(int aRecordID) {
		recordID = aRecordID;
	}
	
	/**
	 * This method sets the patronID field to the given patronID.
	 * @param aPatronID The new patronID.
	 */
	public void setPatronID(int aPatronID) {
		patronID = aPatronID;
	}
	
	/**
	 * This method sets the bookID field to the given bookID.
	 * @param aBookID The new bookID.
	 */
	public void setBookID(int aBookID) {
		bookID = aBookID;
	}
	
	/**
	 * This method sets the borrowBy field to the new borrowBy date.
	 * @param aBorrowByDate The new date the book was borrowed by.
	 */
	public void setBorrowByDate(Date aBorrowByDate) {
		
		borrowBy = aBorrowByDate;
	}
	
	/**
	 * This method sets the returnBy field to the new returnBy date.
	 * @param aReturnByDate The new date the book was returned by.
	 */
	public void setReturnByDate(Date aReturnByDate) {
		returnBy = aReturnByDate;
	}
	
	// getters
	/**
	 * This method returns the recordID of the current record.
	 * @return The recordID.
	 */
	public int getRecordID() {
		return recordID;
	}
	
	/**
	 * This method returns the patronID of the current record.
	 * @return The patronID.
	 */
	public int getPatronID() {
		return patronID;
	}
	
	/**
	 * This method returns the bookID of the current record.
	 * @return The bookID.
	 */
	public int getBookID() {
		return bookID;
	}
	
	/**
	 * This method returns the borrowBy date of the current record.
	 * @return The borrowBy date.
	 */
	public Date getBorrowByDate() {
		return borrowBy;
	}
	
	/**
	 * This method returns the returnBy date of the current record.
	 * @return The returnBy date.
	 */
	public Date getReturnByDate() {
		return returnBy;
	}
}
