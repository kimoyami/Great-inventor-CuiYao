/*
 * Copyright: Mashiro
 */

package srv.book;

public class bookrecord {
    private String eCardName;
    private String bookID;

    public bookrecord(String eCardName,String bookID) {
        this.setBookID(bookID);
        this.setECardName(eCardName);
    }

    public String getECardName() {
        return this.eCardName;
    }

    public String getBookID() {
        return this.bookID;
    }

    public void setECardName(String eCardName) {
        this.eCardName = eCardName;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }



}
