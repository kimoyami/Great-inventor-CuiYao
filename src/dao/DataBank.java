/*
 * Copyright: Mashiro
 *
 * exist()账户是否存在 -1异常 0不存在 1存在
 *insert()插入新用户 -1异常 0已存在用户 1成功
 *update(tag=0取款，tag=1存款)更新余额 -3余额不足 -2金额小于0 -1异常 0不存在 1成功
 *query()查询用户 返回BankInfo类对象
*/
package dao;
import srv.bank.*;

import javax.xml.crypto.Data;
import java.text.*;
import java.util.Date;
import java.sql.ResultSet;

public class DataBank {
    public static int exist(String ID){
        DataBase.start();
        try{
            String sql="select * from bank where idx='"+ID+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            if(!rs.next()){
                DataBase.stop();
                return 0;
            }
            DataBase.stop();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            DataBase.stop();
            return -1;
        }
    }

    public static int transfer(String fromID,String toID,double change){
        DataBase.start();
        try{
            if(change<=0){
                DataBase.stop();
                return -2;
            }
            if(exist(fromID)!=1||exist(toID)!=1){
                DataBase.stop();
                return 0;
            }

            String sql="select * from bank where idx='"+fromID+"'";
            ResultSet rs =DataBase.s.executeQuery(sql);
            rs.next();
            double fromBalance=rs.getDouble("balance");

            sql="select * from bank where idx='"+toID+"'";
            rs =DataBase.s.executeQuery(sql);
            rs.next();
            double toBalance=rs.getDouble("balance");

            fromBalance-=change;
            toBalance+=change;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());
            sql="update bank set balance="+fromBalance+" ,operationdate=#"+d+"# where idx='"+fromID+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();

            sql="update bank set balance="+toBalance+" ,operationdate=#"+d+"# where idx='"+toID+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }catch(Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(BankInfo account){
        DataBase.start();
        try {
            int res = exist(account.getID());
            if(res!=0){
                DataBase.stop();
                return 0;
            }
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());
            String sql="insert into bank(idx,username,balance,eCardBalance,password,operationdate) values('"+account.getID()+"','"+account.getName()+"','"+account.getBalance()+"','"+account.geteCardBalance()+"','"+account.getPassword()+"',#"+d+"#)";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }
        catch(Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(BankInfo account){
        DataBase.start();
        try {
            int res=exist(account.getID());
            if(res!=1){
                DataBase.stop();
                return 0;
            }
            String sql="delete from bank where idx='"+account.getID()+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }
        catch (Exception e) {
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static int transferToEcard(String ID,double change){
        DataBase.start();
        try{
           if(change<=0){
               DataBase.stop();
               return -2;
           }
           if(exist(ID)!=1){
               DataBase.stop();
               return 0;
           }
           String sql=sql="select * from bank where idx='"+ID+"'";
           ResultSet rs=DataBase.s.executeQuery(sql);
           rs.next();
           double balance=rs.getDouble("balance");
           double ebalance=rs.getDouble("eCardBalance");
           balance-=change;
           ebalance+=change;

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());

           sql="update bank set balance="+balance+",eCardBalance="+ebalance+",operationdate=#"+d+"# where idx='"+ID+"'";
           DataBase.s.executeUpdate(sql);
           DataBase.c.commit();
            DataBase.stop();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            DataBase.stop();
            return -1;
        }
    }

    public static BankInfo query(String ID){
        DataBase.start();
        BankInfo account=new BankInfo(null,null,0,0,null);
        try{
        String sql="select * from bank where idx='"+ID+"'";
        ResultSet rs=DataBase.s.executeQuery(sql);
        if(rs.next()) {
        account.setID(rs.getString("idx"));
        account.setName(rs.getString("username"));
        account.setBalance(rs.getDouble("balance"));
        account.setPassword(rs.getString("password"));
        account.seteCardBalance(rs.getDouble("eCardBalance"));
        }
    }
    catch(Exception e){
        e.printStackTrace();
       }
        DataBase.stop();
        return account;
    }

    public static void main(String[] args) {
        DataBase.start();
        DataBase.stop();
    }
}