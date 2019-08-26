package srv.Book;
//*图书信息类*

class Book {
    public String BOOK_ID;//书籍id
    public String BOOK_NAME; //书名
    public String BOOK_EDIT; //作者
    public String BOOK_PUB; //出版社
    public String Category; //分类
    public boolean BE_BORROWED; //状态  true-未借出  false-已借出

    public Book(String BOOK_NAME, String BOOK_ID, String BOOK_PUB, String Category, String BOOK_EDIT, boolean BE_BORROWED) {
        this.BOOK_ID = BOOK_ID;
        this.BOOK_NAME = BOOK_NAME;
        this.BOOK_PUB = BOOK_PUB;
        this.Category = Category;
        this.BE_BORROWED = BE_BORROWED;
        this.BOOK_EDIT = BOOK_EDIT;
    }

    public Book() {  //默认20本书
        this(20);
    }

    public Book(int i) {
        psg.books = new Book[i];
    }

    public String getBOOK_PUB() {
        return BOOK_PUB;
    }

    public void setBOOK_PUB() {
        this.BOOK_PUB = BOOK_PUB;
    }

    public String getBookName() {
        return BOOK_NAME;
    }

    public void setBookName(String BOOK_NAME) {
        this.BOOK_NAME = BOOK_NAME;
    }

    public String getBookEdit() {
        return BOOK_EDIT;
    }

    public void setBookEdit(String BOOK_EDIT) {
        this.BOOK_EDIT = BOOK_EDIT;
    }

    public String getBookID() {
        return BOOK_ID;
    }

    public void setBookID(String BOOK_ID) {
        this.BOOK_ID = BOOK_ID;
    }

    public boolean isState() {
        return BE_BORROWED;
    }

    public void setState(boolean BE_BORROWED) {
        this.BE_BORROWED = BE_BORROWED;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }
}
