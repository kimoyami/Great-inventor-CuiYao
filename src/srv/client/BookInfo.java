/*
 * Copyright: Mashiro
 */

package srv.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import srv.book.*;

import java.util.Vector;

public class BookInfo {
    private static final int STARTPOS = 19;

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
            for (int i = 0; i < n; i++) {
                res.add((Book) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            Client.stop();
        }
        return res;
    }

    public static int update(Book book) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 4);
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
}