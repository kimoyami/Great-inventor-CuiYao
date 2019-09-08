/*
 * Copyright: kimoyami
 */

package dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import srv.book.bookrecord;
public class DataBookRecord {
    public static int insert(String eCardName,String bookID){
        try{
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());
            String sql="insert into bookrecord(eCardName,bookID,borrowtime) values('"+eCardName+"','"+bookID+"'," +
                    "#"+d+"#)";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    public static int returnbook(String eCardName,String bookID){
        try{
            String sql="select * from bookrecord where eCardName='"+eCardName+"'and bookID='"+bookID+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.last();
            String last=rs.getString("borrowtime");
            System.out.println(last);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());
            sql="update bookrecord set returntime=#"+d+"# where eCardName='"+eCardName+"'and bookID='"+bookID+"' and borrowtime='"+rs.getString("borrowtime")+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String []args) {
        DataBase.start();
        bookrecord tmp=new bookrecord("213170001","0001");
        //insert("213170002","0002");
        int a=returnbook("213170002","0002");
        System.out.println(a);
        DataBase.stop();

    }

}
