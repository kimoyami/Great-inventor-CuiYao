/*
 * Copyright: Mashiro
 */

package srv.client;

import srv.book.*;

import java.util.Vector;

public class BookInfo {
    public static int insert(Book book) {
        Client.run();
        try {
            Client.cout.writeInt(20);
            Client.cout.writeObject(book);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            Client.stop();
            return -4;
        }
    }

    public static int delete(Book book) {
        Client.run();
        try {
            Client.cout.writeInt(21);
            Client.cout.writeObject(book);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            Client.stop();
            return -4;
        }
    }

    public static Vector<Book> query(String bookname) {
        Client.run();
        Vector<Book> res = new Vector<>();
        try {
            Client.cout.writeInt(22);
            Client.cout.writeUTF(bookname);
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

    public static int update(Book book) {
        Client.run();
        try {
            Client.cout.writeInt(23);
            Client.cout.writeObject(book);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            Client.stop();
            return -4;
        }
    }

}
