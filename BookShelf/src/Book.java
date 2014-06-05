
/**
 * The book class holds all of the information of a book.
 * @author Kaiyuan Shi
 * @version Spr. 2014
 */
public class Book {
	/** the book ID which is unique */
    private int myBookID;
    
    /** the ISBN number used to get the information of the book*/
    private String myISBN;
    
    /**
     * The Book constructor takes in an integer and String and sets myBookID and 
     * myISBN to them respectively.
     * 
     * @param aBookID An integer value.
     * @param anISBN A String value.
     */
    public Book(int aBookID, String anISBN) {
        myBookID = aBookID;
        myISBN = anISBN;
    }
    
    /**
     * This method returns the bookID for the Book.
     * @return The bookID.
     */
    public int getBookID() {
        return myBookID;
    }
    
    /**
     * This method sets the bookID of the Book.
     * @param aBookID An integer.
     */
    public void setBookID(int aBookID) {
        myBookID = aBookID;
    }
    
    /**
     * This method returns the ISBN of the Book.
     * @return A String representation of the ISBN
     */
    public String getISBN() {
        return myISBN;
    }
    
    /**
     * This method sets the ISBN to the String parameter.
     * @param anISBN A String representation of the ISBN.
     */
    public void setISBN(String anISBN) {
        myISBN = anISBN;
    }
    
    @Override
    /**
     * Returns a String representation of the class.
     */
    public String toString() {
        return "Book\nBookID: " + myBookID + "\nISBN: " + myISBN;
    }
}
