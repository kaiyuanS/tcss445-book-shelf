
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
    
    public Book(int aBookID, String anISBN) {
        myBookID = aBookID;
        myISBN = anISBN;
    }
    
    public int getBookID() {
        return myBookID;
    }
    
    public void setBookID(int aBookID) {
        myBookID = aBookID;
    }
    
    public String getISBN() {
        return myISBN;
    }
    
    public void setISBN(String anISBN) {
        myISBN = anISBN;
    }
    
    @Override
    public String toString() {
        return "Book\nBookID: " + myBookID + "\nISBN: " + myISBN;
    }
}
