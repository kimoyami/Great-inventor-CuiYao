/*
 * Copyright: Mashiro
 */

package srv.client;

import srv.book.*;

import java.util.Vector;

public class BookInfo {
    private static final int STARTPOS = 30;
    public static int insert(Book book) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeObject(book);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static int delete(Book book) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeObject(book);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static Vector<Book> query(String bookname) {
        Client.run();
        Vector<Book> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.writeUTF(bookname);
            Client.cout.flush();
            int n = Client.cin.readInt();
            System.out.println("n="+n);
            for (int i = 0; i < n; i++) {
                res.add((Book) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            Client.stop();
            e.printStackTrace();
        }
        return res;
    }



    public static int update(String id,String state) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.writeUTF(id);
            Client.cout.writeUTF(state);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static Vector<Book>getAll(){
        Client.run();
        Vector<Book> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 5);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Book) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            Client.stop();
            e.printStackTrace();
        }
        return res;

    }

    public static void main(String args[]){
    }
}
/*


        int a=insert(new Book("0015", "西游记", "章金莱",
                "中国人民出版社", "名著", "已借"));
        int b=delete(new Book("0014", "西游记", "章金莱",
                "中国人民出版社", "名著", "已借"));



        Vector<Book>res= query("西游记");
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.elementAt(i).getBookEdit());
        }

        res=getAll();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.elementAt(i).getBOOK_PUB());
        }
*/