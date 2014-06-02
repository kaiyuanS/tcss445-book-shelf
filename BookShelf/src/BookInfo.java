

public class BookInfo {
    private String myISBN;
    private String myTitle;
    private int myYear;
    private String myAuthor;
    private int myFormat; // 1: hardcover, 2:paperback
    private int myPageNumber;
    private String myLanguage;
    private int myBookselfNumber;
    private int myLayerNumber;
    private String myPublisherName;
    
    public BookInfo(String anISBN, String aTitle, int aYear,
            String anAuthor, int aFormat, int aPageNumber,
            String aLanguage, int aBookselfNumber, int aLayerNumber,
            String aPublisherName) {
        myISBN = anISBN;
        myTitle = aTitle;
        myYear = aYear;
        myAuthor = anAuthor;
        myFormat = aFormat;
        myPageNumber = aPageNumber;
        myLanguage = aLanguage;
        myBookselfNumber = aBookselfNumber;
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

    public int getFormat() {
        return myFormat;
    }

    public void setFormat(int aFormat) {
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

    public int getBookselfNumber() {
        return myBookselfNumber;
    }

    public void setBookselfNumber(int aBookselfNumber) {
        myBookselfNumber = aBookselfNumber;
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
                + "\nFormat: " + (myFormat == 1?"hard cover":"paper back")
                + "\nPage Number: " + myPageNumber + "\nLanguage: " + myLanguage
                + "\nBookself NUmber: " + myBookselfNumber + "\nLayer Number: " + myLayerNumber
                + "\nPublisher: " + myPublisherName;
    }
}
