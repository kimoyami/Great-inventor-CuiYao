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
            if(op==5){
                Vector<Book> res =getAll();
                ServerThread.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    ServerThread.cout.writeObject(res.elementAt(i));
                }
                ServerThread.cout.flush();
            }
            if(op==6){
                Vector<Book> res =queryrecord();
                ServerThread.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    ServerThread.cout.writeObject(res.elementAt(i));
                }
                ServerThread.cout.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static synchronized int insert() {
        String idx;
        String name;
        String author;
        String publish;
        String category;
        String state;
        try {
            idx=ServerThread.cin.readUTF();
            name=ServerThread.cin.readUTF();
            author=ServerThread.cin.readUTF();
            publish=ServerThread.cin.readUTF();
            category=ServerThread.cin.readUTF();
            state=ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataBooks.insert(idx,name,author,publish,category,state);
    }

    public static synchronized int delete() {
        String idx;
        try {
           idx = ServerThread.cin.readUTF();
        } catch (Exception e) {
            return -1;
        }
        return DataBooks.delete(idx);
    }

    public static Vector<Book> query() {
        String bookname = "";
        try {
            bookname = ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataBooks.query(bookname);
    }

    public static synchronized int update() {
        String id;
        String state;
        String ecardname;
        try {
            id = ServerThread.cin.readUTF();
            state=ServerThread.cin.readUTF();
            ecardname=ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataBooks.update(id,state,ecardname);
    }
    public static Vector<Book> queryrecord() {
        String ecardname = "";
        try {
            ecardname = ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataBooks.queryrecord(ecardname);
    }



    public static Vector<Book>getAll(){
        return DataBooks.getAll();
    }



}