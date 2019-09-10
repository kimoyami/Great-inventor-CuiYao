/*
 * Copyright: Mashiro
 */

package srv.server;

import dao.DataBase;
import dao.DataBooks;
import srv.book.*;

import java.util.Vector;

public class BookInfo {
    public static ServerThread now;
    public static void run(int op) {
        try {
            if (op == 1) {
                now.cout.writeInt(insert());
                now.cout.flush();
            }
            if (op == 2) {
                now.cout.writeInt(delete());
                now.cout.flush();
            }
            if (op == 3) {
                Vector<Book> res =query();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            if (op == 4) {
                now.cout.writeInt(update());
                now.cout.flush();
            }
            if(op==5){
                Vector<Book> res =getAll();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            if(op==6){
                Vector<Book> res =queryrecord();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            if(op == 7){
                now.cout.writeInt(getState());
                now.cout.flush();
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
            idx=now.cin.readUTF();
            name=now.cin.readUTF();
            author=now.cin.readUTF();
            publish=now.cin.readUTF();
            category=now.cin.readUTF();
            state=now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataBooks.insert(idx,name,author,publish,category,state);
        DataBase.stop();
        return a;
    }

    public static synchronized int delete() {
        String idx;
        try {
           idx = now.cin.readUTF();
        } catch (Exception e) {
            return -1;
        }
        DataBase.start();
        int a=DataBooks.delete(idx);
        DataBase.stop();

        return a;
    }

    public static Vector<Book> query() {
        String bookname = "";
        try {
            bookname = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataBase.start();
        Vector<Book>res=DataBooks.query(bookname);
        DataBase.stop();

        return res;
    }

    public static synchronized int update() {
        String id;
        String state;
        String ecardname;
        try {
            id = now.cin.readUTF();
            state=now.cin.readUTF();
            ecardname=now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataBooks.update(id,state,ecardname);
        DataBase.stop();
        return a;
    }
    public static Vector<Book> queryrecord() {
        String ecardname = "";
        try {
            ecardname = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataBase.start();
        Vector<Book>res=DataBooks.queryrecord(ecardname);
        DataBase.stop();
        return res;
    }



    public static Vector<Book>getAll(){
        DataBase.start();
        Vector<Book>res=DataBooks.getAll();
        DataBase.stop();
        return res;
    }

    public static synchronized int getState(){
        String ID = "";
        try {
            ID = now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -2;
        }
        DataBase.start();
        int res = DataBooks.getState(ID);
        DataBase.stop();
        return res;
    }

}