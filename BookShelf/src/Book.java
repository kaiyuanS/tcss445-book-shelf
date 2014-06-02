

public class Book {
    private int myBookID;
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
