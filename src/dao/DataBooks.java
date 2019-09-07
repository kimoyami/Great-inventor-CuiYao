/*
 * Copyright: Mashiro
 * exist()书籍是否存在 -1异常 0不存在 1存在
 *insert()插入一本书 -1异常 0不成功 1成功
 * delete()删除一本书 -1异常 0未有此书/此书未还 1成功
 * query()查询书籍 返回向量
 * update借书/还书 -1异常 0不存在 1成功
 *
 */

package dao;

import srv.bank.BankInfo;
import srv.book.Book;

import java.sql.ResultSet;
import java.util.Vector;

public class DataBooks {
    public static int exist(String idx,String state){
        try{
            String sql="select * from books where idx='"+idx+"' and state="+state+"";
            ResultSet rs=DataBase.s.executeQuery(sql);
            if(!rs.next()){return 0;}
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(Book book){
        try{
            if(!book.isState()){return 0;}
            String sta="No";
            String sql="insert into books(idx,bookname,author,publish,Category,state) values" +
                    "('"+book.getBookID()+"','"+book.getBookName()+"','"+book.getBookEdit()+"','"+book.getBOOK_PUB()+"'," +
                    "'"+book.getCategory()+"',"+sta+")";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    public static int delete(Book book){
        try{
            String sta;
            if(book.isState()){sta="No";}
            else{sta="Yes";}
            String sql="select * from books where idx='"+book.getBookID()+ "'and state="+sta+"";
            ResultSet rs=DataBase.s.executeQuery(sql);
            if(!rs.next()){return 0;}
            sql="delete *from books where idx='"+book.getBookID()+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Book> query(String bookName){
        Vector<Book> res=new Vector<>();
        try{
           String sql="select *from books where bookname='"+bookName+"'";
           ResultSet rs=DataBase.s.executeQuery(sql);
           while(rs.next()){
               Book book=new Book(rs.getString("idx"),rs.getString("bookname"),
                       rs.getString("author"), rs.getString("publish"),
                       rs.getString("category"),rs.getBoolean("state"));
               res.add(book);
           }
       }
        catch (Exception e){
           e.printStackTrace();
        }
        return res;
    }

    public static int update(Book book){
        String sta;
        if(book.isState()){sta="No";}

        else{sta="Yes";}
        System.out.println(book.isState());
        System.out.println(book.getBookID());
        int res=exist(book.getBookID(),sta);

        if(res!=1){return 0;}
        try{
            if(book.isState()){sta="Yes";}
            else{sta="No";}
            String sql="update books set state="+sta+" where idx='"+book.getBookID()+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String []args){
        DataBase.start();
        DataBase.stop();
    }
}
