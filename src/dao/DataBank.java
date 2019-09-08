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
import java.util.Vector;

public class DataBank {
    public static int exist(String ID){
        try{
            String sql="select * from bank where idx='"+ID+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            if(!rs.next()){return 0;}
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int transfer(String fromID,String toID,double change){
        try{
            if(change<=0){return -2;}
            if(exist(fromID)!=1||exist(toID)!=1){return 0;}

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
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(BankInfo account){
        try {
            int res = exist(account.getID());
            if(res!=0){return 0;}
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());
            String sql="insert into bank(idx,username,balance,eCardBalance,password,operationdate) values('"+account.getID()+"','"+account.getName()+"','"+account.getBalance()+"','"+account.geteCardBalance()+"','"+account.getPassword()+"',#"+d+"#)";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(BankInfo account){
        try {
            int res=exist(account.getID());
            if(res!=1){return 0;}
            String sql="delete from bank where idx='"+account.getID()+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int transferToEcard(String ID,double change){
        try{
           if(change<=0){return -2;}
           if(exist(ID)!=1){return 0;}
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

            sql="insert into bankrecord(ecardname,change,record,otime) values('"+ID+"',"+change+",'一卡通充值',#"+d+"#)";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
           return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int transferTocard(String ID,double change){
        try{
            if(change<=0){return -2;}
            if(exist(ID)!=1){return 0;}
            String sql=sql="select * from bank where idx='"+ID+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.next();
            double balance=rs.getDouble("balance");
            double ebalance=rs.getDouble("eCardBalance");
            balance+=change;
            ebalance-=change;

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());

            sql="update bank set balance="+balance+",eCardBalance="+ebalance+",operationdate=#"+d+"# where idx='"+ID+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();

            sql="insert into bankrecord(ecardname,change,record,otime) values('"+ID+"',"+change+",'一卡通提现',#"+d+"#)";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    public static BankInfo query(String ID){
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
    return account;
    }

    public static Vector<Bankrecord>queryrecord(String ID){
            Vector<Bankrecord> res = new Vector<>();
            try{
                String sql="select * from bankrecord where ecardname='"+ID+"'";
                ResultSet rs=DataBase.s.executeQuery(sql);
                while(rs.next()){
                    Bankrecord tmp=new Bankrecord(rs.getString("ecardname"),
                            rs.getDouble("change"),rs.getString("record"),
                            rs.getString("otime"));
                    res.addElement(tmp);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return res;
        }

    public static void main(String[] args) {
        DataBase.start();

        Vector<Bankrecord> res=queryrecord("213170002");
        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getRecord()+res.elementAt(i).getOtime());
        }

        DataBase.stop();
    }
}