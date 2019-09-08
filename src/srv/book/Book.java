package srv.book;
//*图书信息类*

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = -1925809561781496530L;
    private String BOOK_ID;//书籍id
    private String BOOK_NAME; //书名
    private String BOOK_EDIT; //作者
    private String BOOK_PUB; //出版社
    private String Category; //分类
    private String state; //状态

    public Book( String BOOK_ID, String BOOK_NAME,String BOOK_EDIT,String BOOK_PUB, String Category,  String BE_BORROWED) {
        this.BOOK_ID = BOOK_ID;
        this.BOOK_NAME = BOOK_NAME;
        this.BOOK_PUB = BOOK_PUB;
        this.Category = Category;
        this.state = BE_BORROWED;
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

    public String getState(){return state;}

    public void setState(String BE_BORROWED) {
        this.state = BE_BORROWED;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }
}
