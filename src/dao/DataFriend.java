package dao;

import java.sql.ResultSet;
import java.util.Vector;

public class DataFriend {

    public static int exist(String e1, String e2){
        try {
            String sql = "select * from friend where e1 = '"+e1+"' and e2 = '"+e2+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) return 0;
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(String e1, String e2){ // 两个一卡通号
        int res = exist(e1, e2);
        if(res != 0) return res;
        try {
            String sql = "insert into friend(e1, e2) values('"+e1+"', '"+e2+"')";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }



    public static int delete(String e1, String e2){
        int res = exist(e1, e2);
        if(res != 1) return res;
        try {
            String sql = "delete from friend where e1 = '"+e1+"' and e2 = '"+e2+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<String> query(String eCardNumber){
        Vector<String> res = new Vector<>();
        try {
            String sql = "select * from friend where e1 = '"+eCardNumber+"' or e2 = '"+eCardNumber+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while(rs.next()){
                if(eCardNumber.endsWith(rs.getString(1))) res.add(rs.getString(2));
                else res.add(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

}
