/*
 * Copyright: Mashiro
 */

package srv.server;

import dao.DataBooks;
import srv.book.*;

import java.util.Vector;

public class BookInfo {
    public static void run(int op) {
        try {
            if (op == 1) {
                ServerThread.cout.writeInt(insert());
                ServerThread.cout.flush();
            }
            if (op == 2) {
                ServerThread.cout.writeInt(delete());
                ServerThread.cout.flush();
            }
            if (op == 3) {
                Vector<Book> res =query();
                ServerThread.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    ServerThread.cout.writeObject(res.elementAt(i));
                }
                ServerThread.cout.flush();
            }
            if (op == 4) {
                ServerThread.cout.writeInt(update());
                ServerThread.cout.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static synchronized int insert() {
        Book book;
        try {
            book = (Book) ServerThread.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataBooks.insert(book);
    }

    public static synchronized int delete() {
        Book book;
        try {
            book = (Book) ServerThread.cin.readObject();
        } catch (Exception e) {
            return -1;
        }
        return DataBooks.delete(book);
    }

    public static synchronized Vector<Book> query() {
        String bookname = "";
        try {
            bookname = ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataBooks.query(bookname);
    }

    public static synchronized int update() {
        Book book;
        try {
            book = (Book) ServerThread.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataBooks.update(book);
    }

}