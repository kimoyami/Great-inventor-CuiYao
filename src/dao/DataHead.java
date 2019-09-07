/*
 * Copyright: kimoyami
 */

package dao;

import java.sql.ResultSet;

public class DataHead {
    private static final String DEFAULTURL = "../headimage/timg.jpg";
    public static int update(String eCardNumber, String url){
        DataBase.start();
        try{
            String sql = "update head set url = '"+url+"' where ecardnumber = '"+eCardNumber+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            DataBase.stop();
            return -1;
        }
    }

    public static String query(String eCardNumber){
        DataBase.start();
        try {
            String sql = "select url from head where ecardnumber = '"+eCardNumber+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) {
                DataBase.stop();
                return DEFAULTURL;
            }
            DataBase.stop();
            return rs.getString(1);
        }catch (Exception e){
            e.printStackTrace();
            DataBase.stop();
            return DEFAULTURL;
        }
    }
}
