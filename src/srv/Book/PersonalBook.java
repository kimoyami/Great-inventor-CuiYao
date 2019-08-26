package srv.Book;
//私有的借书信息
 class PersonalBook {
    private String BOOK_ID;
    private String BOOK_NAME;
    private long borrowDate;
    private long returnDate;
    private String Borrower;

     PersonalBook(String BOOK_ID, String BOOK_NAME, String Borrower, long borrowDate, long returnDate) {
        this.BOOK_ID = BOOK_ID;
        this.BOOK_NAME = BOOK_NAME;
        this.Borrower = Borrower;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
     public String getBookName() {
         return BOOK_NAME;
     }
     public void setBookName(String BOOK_NAME) {
         this.BOOK_NAME = BOOK_NAME;
     }
     public String getBookID() {
         return BOOK_ID;
     }
     public void setBookID(String BOOK_ID) {
         this.BOOK_ID = BOOK_ID;
     }
     public String getBorrower() {
         return Borrower;
     }
     public void setBorrower(String Borrower) {
         this.BOOK_ID = Borrower;
     }
     public long getborrowDate() {
         return borrowDate;
     }
     public void setborrowDate(long borrowDate) {
         this.borrowDate = borrowDate;
     }
     public long getreturnDate() {
         return returnDate;
     }
     public void setreturnDate(long returnDate) {
         this.returnDate = returnDate;
     }

}
