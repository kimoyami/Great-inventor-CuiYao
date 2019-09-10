package dao;

import srv.file.Files;

import java.sql.ResultSet;
import java.util.Vector;

public class DataFile {

    public static int exist(String url){
        try {
            String sql = "select * from file where url = '"+url+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) return 0;
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(String eCardNumber, String fileName, String url){
        int res = exist(url);
        if(res != 0) return res;
        try {
            String sql = "insert into file(ecardnumber, url, filename, clicks) values('"+eCardNumber+"', '"+url+"','"+fileName+"', 0)";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(String url){
        int res = exist(url);
        if(res != 1) return res;
        try {
            String sql = "delete from file where url = '"+url+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Files> query(){
        Vector<Files> res = new Vector<>();
        try {
            String sql = "select * from file";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while(rs.next()){
                res.add(new Files(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static int add(String url){
        int res = exist(url);
        if(res == 0) return 0;
        try {
            String sql = "select clicks from file where url = '"+url+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) return 0;
            int t = rs.getInt(1);
            t++;
            sql = "update file set clicks = "+t+" where url = '"+url+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    public static void main(String args[]){
        DataBase.start();
        //System.out.println(exist("123"));
        insert("213171645", "123.jpg", "123");
        //System.out.println(delete("123"));
       // Vector<Files> res = query();
        //for(int i = 0; i < res.size(); i++) System.out.println(res.elementAt(i).geteCardNumber() + ' ' + res.elementAt(i).getUrl() + ' ' + res.elementAt(i).getFileName() + ' ' + res.elementAt(i).getClicks());
        DataBase.stop();
    }

}
