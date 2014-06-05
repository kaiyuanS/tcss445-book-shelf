
/**
 * This class holds the information of a book
 * @author Kaiyuan Shi
 * @version Spr. 2014
 */
public class BookInfo {
	
	/** the ISBN of a book */
    private String myISBN;
    
    /** the title of a book */
    private String myTitle;
    
    /** the publisher year of a book */
    private int myYear;
    
    /** the author of a book */
    private String myAuthor;
    
    /** the format of a book */
    private String myFormat; //hardcover or paperback
    
    /** the number of pages of a book */
    private int myPageNumber;
    
    /** the language of a book */
    private String myLanguage;
    
    /** the bookshelf number of a book */
    private int myBookshelfNumber;
    
    /** the layer number of a book on the bookshelf*/
    private int myLayerNumber;
    
    /** the name of the publisher of the book */
    private String myPublisherName;
    
    /**
     * The BookInfo constructor takes in all the values necessary to assign its
     * private members to.
     * 
     * @param anISBN A String representation of the ISBN.
     * @param aTitle A String representation of the title.
     * @param aYear A String representation of the publishing year.
     * @param anAuthor A String representation of the author
     * @param aFormat A String representation of the format.
     * @param aPageNumber An integer for the page number.
     * @param aLanguage A String representation of the language.
     * @param aBookshelfNumber An integer for where the bookshelf is located.
     * @param aLayerNumber An integer for where the layer that the book is on is.
     * @param aPublisherName A String representation of the publisher.
     */
    public BookInfo(String anISBN, String aTitle, int aYear,
            String anAuthor, String aFormat, int aPageNumber,
            String aLanguage, int aBookshelfNumber, int aLayerNumber,
            String aPublisherName) {
        myISBN = anISBN;
        myTitle = aTitle;
        myYear = aYear;
        myAuthor = anAuthor;
        myFormat = aFormat;
        myPageNumber = aPageNumber;
        myLanguage = aLanguage;
        myBookshelfNumber = aBookshelfNumber;
        myLayerNumber = aLayerNumber;
        myPublisherName = aPublisherName;
    }

    /**
     * This method returns the ISBN.
     * @return A String representation of the ISBN.
     */
    public String getISBN() {
        return myISBN;
    }

    /**
     * This method sets the ISBN.
     * @param anISBN A String that the ISBN will be set to.
     */
    public void setISBN(String anISBN) {
        myISBN = anISBN;
    }

    /**
     * This method returns the title.
     * @return The title.
     */
    public String getTitle() {
        return myTitle;
    }
    
    /**
     * This method sets the title.
     * @param aTitle A String that the title is set to.
     */
    public void setTitle(String aTitle) {
        myTitle = aTitle;
    }

    /**
     * This returns the published year.
     * @return The published year.
     */
    public int getYear() {
        return myYear;
    }

    /**
     * This method sets the published year.
     * @param aYear The published year.
     */
    public void setYear(int aYear) {
        myYear = aYear;
    }

    /**
     * This method returns the author.
     * @return The author.
     */
    public String getAuthor() {
        return myAuthor;
    }

    /**
     * This method sets the author.
     * @param anAuthor The author.
     */
    public void setAuthor(String anAuthor) {
        myAuthor = anAuthor;
    }

    /**
     * This method returns the format.
     * @return The format of the book.
     */
    public String getFormat() {
        return myFormat;
    }

    /**
     * This method sets the format.
     * @param aFormat The format of the book.
     */
    public void setFormat(String aFormat) {
        myFormat = aFormat;
    }

    /**
     * This method returns the number of pages.
     * @return The number of pages.
     */
    public int getPageNumber() {
        return myPageNumber;
    }
    
    /**
     * This method sets the number of pages.
     * @param aPageNumber The number of pages.
     */
    public void setPageNumber(int aPageNumber) {
        myPageNumber = aPageNumber;
    }

    /**
     * This method returns the language of the book.
     * @return The language of the book.
     */
    public String getLanguage() {
        return myLanguage;
    }

    /**
     * This method sets the language of the book.
     * @param aLanguage The language of the book.
     */
    public void setLanguage(String aLanguage) {
        myLanguage = aLanguage;
    }

    /**
     * This method gets the book shelf number.
     * @return The book shelf number.
     */
    public int getBookshelfNumber() {
        return myBookshelfNumber;
    }

    /**
     * This method sets the book shelf number.
     * @param aBookshelfNumber The book shelf number.
     */
    public void setBookshelfNumber(int aBookshelfNumber) {
        myBookshelfNumber = aBookshelfNumber;
    }

    /**
     * This method returns the layer number.
     * @return The layer number.
     */
    public int getLayerNumber() {
        return myLayerNumber;
    }

    /**
     * This method sets the layer number.
     * @param aLayerNumber The layer number.
     */
    public void setLayerNumber(int aLayerNumber) {
        myLayerNumber = aLayerNumber;
    }

    /**
     * This method reutrns the publisher name.
     * @return The publisher name.
     */
    public String getPublisherName() {
        return myPublisherName;
    }

    /**
     * This method sets the publisher name.
     * @param aPublisherName The publisher name.
     */
    public void setPublisherName(String aPublisherName) {
        myPublisherName = aPublisherName;
    }
    
    @Override
    /**
     * The String representation of the class.
     */
    public String toString() {
        return "BookInfo\nISBN: " + myISBN + "\nTitle: " + myTitle
                + "\nYear: " + myYear + "\nAuthor: " + myAuthor
                + "\nFormat: " + myFormat
                + "\nPage Number: " + myPageNumber + "\nLanguage: " + myLanguage
                + "\nBookshelf NUmber: " + myBookshelfNumber + "\nLayer Number: " + myLayerNumber
                + "\nPublisher: " + myPublisherName;
    }
}
