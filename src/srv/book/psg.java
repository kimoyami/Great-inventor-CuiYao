package srv.book;
//陈列 借还 查询 筛选 函数

import srv.person.Person;

public class psg {

        public static Book[] books = new Book[]{  //书库

                new Book("西游记", "11111", "中国人民出版社", "名著", "章金莱", true),

        };


        Book n = new Book();

        public void delete(Book[] books, PersonalBook PersonalBook, Person Person) {
                if (n.getBookName() != null) {
                        for (int i = 0; i < books.length - 1; i++) {
                                if (books[i].getBookName().equals(n.getBookName())) {
                                        books[i].setState(false);
                                        System.out.println("借阅成功！");
                                        PersonalBook.setBorrower(Person.getName());
                                        break;
                                } else {
                                        System.out.println("没有此书！");
                                        break;
                                }
                        }
                }
        }


        public void add(Book[] books, PersonalBook PersonalBook) {
                if (n.getBookName() != null) {
                        for (int j = 0; j < books.length - 1; j++)
                                if (books[j].getBookName().equals(n.getBookName())) {
                                        books[j].setState(true);
                                        System.out.println("还书成功！");
                                        PersonalBook.setBorrower(null);
                                        break;
                                }
                }
        }

        public void list(Book[] books) {
                System.out.println("图书列表如下：（书名-作者-出版社-ID-分类-状态）");
                for (int i = 0; i < books.length; i++) {
                        if (books[i] != null) {
                                System.out.println(books[i].getBookName() + "   " + books[i].getBookEdit() + "   " + books[i].getBOOK_PUB() + "   " + books[i].getBookID() + "   " + books[i].getCategory() + "   " + books[i].isState());
                        }
                }
        }

        public void status(PersonalBook personalBook, Person Person) {
                System.out.println("个人借书信息如下：（书名-ID-借书时间-还书时间）");
                for (int i = 0; i < books.length; i++) {
                        if (Person.getName() == personalBook.getBorrower())
                                System.out.println(personalBook.getBookID() + "   " +
                                        personalBook.getBookName() + "   " +
                                        personalBook.getborrowDate() + "   " +
                                        personalBook.getreturnDate() + "   " +
                                        personalBook.getBorrower());
                }
        }


        void search(Book[] books, String aim) {
                for (int i = 0; i < books.length; i++) {
                        if (n.getBookName() == aim)
                                System.out.println(books[i].getBookName() + "   " + books[i].getBookEdit() + "   " + books[i].getBOOK_PUB() + "   " + books[i].getBookID() + "   " + books[i].getCategory() + "   " + books[i].isState());
                }
        }

}





