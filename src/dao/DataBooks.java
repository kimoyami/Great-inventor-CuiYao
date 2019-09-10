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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class DataBooks {
    public static int exist(String idx,String state){
        try{
            String sql="select * from books where idx='"+idx+"' and state='"+state+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            if(!rs.next()){return 0;}
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(String idx,String name,String author,String publish,String category,String state){
        try{

            String sql="insert into books(idx,bookname,author,publish,Category,state) values" +
                    "('"+idx+"','"+name+"','"+author+"','"+publish+"'," +
                    "'"+category+"','"+state+"')";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    public static int delete(String idx){
        try{
            String sql="select * from books where idx='"+idx+ "'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            if(!rs.next()){return 0;}
            sql="delete *from books where idx='"+idx+"'";
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
           String sql="select * from books where bookname='"+bookName+"'";
           ResultSet rs=DataBase.s.executeQuery(sql);
           while(rs.next()){
               Book book=new Book(rs.getString("idx"),rs.getString("bookname"),
                       rs.getString("author"), rs.getString("publish"),
                       rs.getString("category"),rs.getString("state"),rs.getString("borrowtime"));
               res.add(book);
           }
       }
        catch (Exception e){
           e.printStackTrace();
        }
        return res;
    }

    public static Vector<Book> queryrecord(String ecardname){
        Vector<Book> res=new Vector<>();
        try{
            String sql="select * from books where ecardname='"+ecardname+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            while(rs.next()){
                Book book=new Book(rs.getString("idx"),rs.getString("bookname"),
                        rs.getString("author"), rs.getString("publish"),
                        rs.getString("category"),rs.getString("state"),rs.getString("borrowtime"));
                res.add(book);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static int update(String ID,String state,String ecardname){

        int res=exist(ID,state);

        if(res!=1){return 0;}
        try{
            String sql;
            if(state.equals("未借")){
                state="已借";
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = f.format(new Date());
                sql="update books set state='"+state +"',ecardname='"+ecardname+"',borrowtime=#"+d+"# where idx='"+ID+"'";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }
            else {
                state = "未借";
                String tmp="";
                sql="update books set state='"+state +"',ecardname='"+tmp+"',borrowtime='"+tmp+"' where idx='"+ID+"'";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Book>getAll(){
        Vector<Book> res=new Vector<>();
        try{
            String sql="select * from books order by idx";
            ResultSet rs=DataBase.s.executeQuery(sql);
            while(rs.next()){
                Book book=new Book(rs.getString("idx"),rs.getString("bookname"),
                        rs.getString("author"), rs.getString("publish"),
                        rs.getString("category"),rs.getString("state"),rs.getString("borrowtime"));
                res.add(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static int getState(String ID){
        try {
            String sql = "select state from books where idx = '"+ID+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            rs.next();
            if(rs.getString("state").equals("已借")) return 1;
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    public static void main(String []args){
        DataBase.start();
        int a=insert("0016", "西游记", "章金莱",
                "中国人民出版社", "名著", "已借");
        System.out.println(a);

        DataBase.stop();
    }
}
/* Vector<Book>res=queryrecord("213170002");
        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getBookName());
        }*/