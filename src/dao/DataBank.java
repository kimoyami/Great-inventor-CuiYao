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

    public static int insert(BankInfo account){
        try {
            int res = exist(account.getID());
            if(res!=0){return 0;}
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());
            String sql="insert into bank(idx,username,balance,eCardBalance,password,state,operationdate) values('"+account.getID()+"','"+account.getName()+"','"+account.getBalance()+"','"+account.geteCardBalance()+"','"+account.getPassword()+"','正常',#"+d+"#)";
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

    public static int update(BankInfo account,double change,int tag){
        try{
            if(change<=0){return -2;}
            String sql="select * from bank where idx='"+account.getID()+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            double cur=0;
            double eCardcur=0;
            if(rs.next()){
                double tmp=rs.getDouble("balance");
                double eCardtmp=rs.getDouble("eCardBalance");
                if(tag==1){ cur=tmp+change;}//虚空向银行卡充值
                else{
                    if(tmp-change<0) { return -3; }
                    else{
                        cur=tmp-change;
                    }
                    if(tag==2){//银行卡向一卡通充值
                        cur=tmp-change;
                        eCardcur=eCardtmp+change;
                    }
                }
            }
            else{return 0;}

            String sta;
            if(cur<0){ sta="欠费"; }
            else{sta="正常";}

            sql="update bank set balance='"+cur+"',eCardBalance='"+eCardcur+"',state='"+sta+"' where idx='"+account.getID()+"'";
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

    public static void main(String[] args) {
        DataBase.start();
        BankInfo a=new BankInfo("213170001","明凯",4396,0,"cznb");
        int b=update(a,4397,0);
        int c=update(a,100,2);
        System.out.println(b);
        System.out.println(c);

        DataBase.stop();
    }
}