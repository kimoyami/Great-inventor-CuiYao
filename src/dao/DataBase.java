/*
Arthor: kimoyami
function:

start() 启动数据库
stop() 停止数据库
query() 查询账号密码是否正确  -1异常 0错误 1正确 2待审核 3不存在
insert() 注册新账号(当且仅当用户名不存在) -2异常 -1异常 0成功 1已存在且审核 2已存在待审核
exist() 查询用户名是否存在 -1异常 0不存在 1存在且审核 2存在待审核
delete() 删除用户 -1异常 0不存在 1存在且审核过 2存在且待审核
solve() 审核用户 -1异常 0不存在 1审核成功
addAdmin() 增加管理员 -1异常 0不存在 1成功 2未通过审核
cancelAdmin() 取消管理员 -1异常 0不存在 1成功 2未通过审核
udpate() 更新信息 -1异常 0不存在 1更新成功 2未通过审核
 */

package dao;

import java.sql.*;
import java.util.Scanner;

public class DataBase {
    static private String dbUrl = "jdbc:odbc:account";
    static private String user = "Nemo Sherry";
    static private String password = "ryhlovelyt";
    static public Statement s = null;
    static public Connection c = null;
    public static void start(){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            c = DriverManager.getConnection(dbUrl, user, password);
            c.setAutoCommit(false);
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
            c.close();
        }catch (Exception e){
            return;
        }
    }

    public static int query(String eCardNumber, String password){
        try {
            String sql = "select password from upsolved where username like '"+eCardNumber+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()) return 2;
            sql = "select password from login where username like '"+eCardNumber+"'";
            rs = s.executeQuery(sql);
            if(!rs.next()) return 3;
            if(password.equals(rs.getString(1))) return 1;
            else return 0;
        }catch (SQLException e){
            return -1;
        }
    }

    public static int exist(String eCardNumber){
        try{
            String sql = "select username from upsolved where ecardnumber = '"+eCardNumber+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()) return 2;
            sql = "select username from login where ecardnumber = '"+eCardNumber+"'";;
            rs = s.executeQuery(sql);
            if(!rs.next()) return 0;
            else return 1;
        }catch (SQLException e){
            return -1;
        }
    }

    public static int insert(String userName, String password, String eCardNumber, String sex, int age, String status){
        int res = exist(eCardNumber);
        if(res != 0) return res;
        try{
            s.executeUpdate("insert into upsolved(username, password, ecardnumber, sex, age, status) values ('"+userName+"', '"+password+"', '"+eCardNumber+"', '"+sex+"', "+age+", '"+status+"')");
            c.commit();
            return 0;
        }catch (SQLException e){
            return -2;
        }
    }

    public static int delete(String eCardNumber){
        int res = exist(eCardNumber);
        if(res != 1 && res != 2) return res;
        try{
            if(res == 1) {
                String sql = "delete from login where ecardnumber = '"+eCardNumber+"'";
                s.executeUpdate(sql);
                c.commit();
            }
            else{
                String sql = "delete from upsolved where ecardnumber = '"+eCardNumber+"'";
                s.executeUpdate(sql);
                c.commit();
            }
            return res;
        }catch (SQLException e){
            return -1;
        }
    }

    public static int solve(String eCardNumber){
        try{
            String sql = "select * from upsolved where ecardnumber = '"+eCardNumber+"'";
            ResultSet rs = s.executeQuery(sql);
            if(!rs.next()) return 0;
            sql = "insert into login(username, password, ecardnumber, sex, age, status) values ('"+rs.getString(2)+"', '"+rs.getString(3)+"', '"+rs.getString(4)+"', '"+rs.getString(5)+"', "+rs.getInt(6)+", '"+rs.getString(7)+"')";
            s.executeUpdate(sql);
            sql = "delete from upsolved where ecardnumber = '"+eCardNumber+"'";
            s.executeUpdate(sql);
            c.commit();
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    public static int addAdmin(String eCardNumber){
        int res = exist(eCardNumber);
        if(res != 1) return res;
        try{
            String sql = "update login set control = Yes where ecardnumber = '"+eCardNumber+"'";
            s.executeUpdate(sql);
            c.commit();
            return 1;
        }catch (SQLException e){
            return -1;
        }
    }

    public static int cancelAdmin(String eCardNumber){
        int res = exist(eCardNumber);
        if(res != 1) return res;
        try{
            String sql = "update login set control = No where ecardnumber = '"+eCardNumber+"'";
            s.executeUpdate(sql);
            c.commit();
            return 1;
        }catch (SQLException e){
            return -1;
        }
    }

    public static int update(String userName, String password, String eCardNumber, String sex, int age, String status){
        int res = exist(eCardNumber);
        if(res != 1) return res;
        try{
            String sql = "update login set username = '"+userName+"', password = '"+password+"', sex = '"+sex+"', age = "+age+", status = '"+status+"' where ecardnumber = '"+eCardNumber+"'";
            s.executeUpdate(sql);
            c.commit();
            return 0;
        }catch (SQLException e){
            return -2;
        }
    }

    public static void main(String args[]){
        start();
        stop();
    }
}
