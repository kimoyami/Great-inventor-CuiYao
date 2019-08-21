/*
Arthor: kimoyami
function:

start() 启动数据库
stop() 停止数据库
query(userName, password) 查询账号密码是否正确
insert(userName password) 注册新账号(当且仅当用户名不存在)
exist(userName) 查询用户名是否存在
 */

package dao;

import java.sql.*;

public class DataBase {
    static private String dbUrl = "jdbc:odbc:account";
    static private String user = "Nemo Sherry";
    static private String password = "ryhlovelyt";
    static public Statement s;
    public static void start(){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection c = DriverManager.getConnection(dbUrl, user, password);
            s = c.createStatement();
            System.out.println("success");

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        try {
            s.close();
        }catch (Exception e){
            return;
        }
    }

    public static boolean query(String userName, String password){
        try {
            ResultSet rs = s.executeQuery("select password from login where username like '"+userName+"'");
            if(!rs.next()) return false;
            if(password.equals(rs.getString(1))) return true;
            else return false;
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean exist(String userName){
        try{
            ResultSet rs = s.executeQuery("select username from login where username = '"+userName+"'");
            if(!rs.next()) return false;
            if(rs.getString(1).equals(userName)) return true;
            return false;
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean insert(String userName, String password){
        if(exist(userName)) return false;
        try{
            s.executeUpdate("insert into login(username, password) values ('"+userName+"', '"+password+"')");
            return true;
        }catch (SQLException e){
            return false;
        }
    }
}
