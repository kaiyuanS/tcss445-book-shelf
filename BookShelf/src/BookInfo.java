
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

    public String getISBN() {
        return myISBN;
    }

    public void setISBN(String anISBN) {
        myISBN = anISBN;
    }

    public String getTitle() {
        return myTitle;
    }

    public void setTitle(String aTitle) {
        myTitle = aTitle;
    }

    public int getYear() {
        return myYear;
    }

    public void setYear(int aYear) {
        myYear = aYear;
    }

    public String getAuthor() {
        return myAuthor;
    }

    public void setAuthor(String anAuthor) {
        myAuthor = anAuthor;
    }

    public String getFormat() {
        return myFormat;
    }

    public void setFormat(String aFormat) {
        myFormat = aFormat;
    }

    public int getPageNumber() {
        return myPageNumber;
    }

    public void setPageNumber(int aPageNumber) {
        myPageNumber = aPageNumber;
    }

    public String getLanguage() {
        return myLanguage;
    }

    public void setLanguage(String aLanguage) {
        myLanguage = aLanguage;
    }

    public int getBookshelfNumber() {
        return myBookshelfNumber;
    }

    public void setBookshelfNumber(int aBookshelfNumber) {
        myBookshelfNumber = aBookshelfNumber;
    }

    public int getLayerNumber() {
        return myLayerNumber;
    }

    public void setLayerNumber(int aLayerNumber) {
        myLayerNumber = aLayerNumber;
    }

    public String getPublisherName() {
        return myPublisherName;
    }

    public void setPublisherName(String aPublisherName) {
        myPublisherName = aPublisherName;
    }
    
    @Override
    public String toString() {
        return "BookInfo\nISBN: " + myISBN + "\nTitle: " + myTitle
                + "\nYear: " + myYear + "\nAuthor: " + myAuthor
                + "\nFormat: " + myFormat
                + "\nPage Number: " + myPageNumber + "\nLanguage: " + myLanguage
                + "\nBookshelf NUmber: " + myBookshelfNumber + "\nLayer Number: " + myLayerNumber
                + "\nPublisher: " + myPublisherName;
    }
}
