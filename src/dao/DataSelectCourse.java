/*
 * Copyright: Mashiro
 * insert()插入 -3已满 -2冲突 -1异常 0已选过 1成功
 */

package dao;
import srv.course.SelectCourse;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class DataSelectCourse {
    public static int exist(String eCardName,String courseName) {
        try {
            String sql="select * from selectcourse where ecardname='"+eCardName+"' and coursename='"+courseName+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            if(!rs.next()){return 0;}
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(String e,String idx,String name,int time,String teacher,int classroom){
        try{
            if(exist(e,name)==1){return 0;}
            String sql="select * from course where idx='"+idx+"' and teacher='"+teacher+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.next();
            int tmp=rs.getInt("remainnumber");
            if (tmp == 0) {
                return -3;
            }//判断能否继续选择

            sql="select * from selectcourse where ecardname='"+e+"'";
            rs= DataBase.s.executeQuery(sql);
            int cur=0;
            rs.next();
            while(rs.next()){
                int a=rs.getInt("coursetime");
                System.out.println("a="+a);
                cur=check(a,time);
                System.out.println("cur="+cur);
                if(cur==1){break;}
            }

            if(cur==1){return -2;}//冲突
            ArrayList<Integer>ct=cuttime(time);
            for (int i = 0; i <ct.size() ; i++) {
                int temp=ct.get(i);
                sql="insert into selectcourse(ecardname,idx,coursename,coursetime,teacher,classroom)" +
                        "values ('"+e+"','"+idx+"'," +
                        "'"+name+"',"+temp+",'"+teacher+"',"+classroom+")";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }

            tmp=tmp-1;
            if(tmp>0){
                sql="update course set remainnumber="+tmp+" " +
                        "where idx='"+idx+"' and teacher='"+teacher+"'";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }
            else{
                String sta="已满";
                sql="update course set remainnumber="+tmp+", state='"+sta+"'" +
                        "where idx='"+idx+"' and teacher='"+teacher+"'";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }
            return 1;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }

    public static int delete(String eCardName,String coursename,String teacher){
        try{
            if(exist(eCardName,coursename)!=1){return 0;}
            String sql="delete from selectcourse where ecardname='"+eCardName+"' and coursename='"+coursename+"'";

            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            sql="select * from course where teacher='"+teacher+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.next();
            int a=rs.getInt("remainnumber");
            String b=rs.getString("state");
            a+=1;
            if(b.equals("已满")){
                b="未满";
                sql="update course set remainnumber="+a+",state='"+b+"'where coursename='"+coursename+"' and teacher='"+teacher+"'";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }
            else{
                sql="update course set remainnumber="+a+",state='"+b+"'where coursename='"+coursename+"' and teacher='"+teacher+"'";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }
            return 1;
        }
        catch(Exception e){
            return -1;
        }
    }

    public static int check(int a,int b){
        try{
            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> list1 = new ArrayList<Integer>();
            while(a!=0){
                int tmp=a%1000/10;
                a=a/1000;
                list.add(tmp);
            }
            while(b!=0){
                int tmp=b%1000/10;
                b=b/1000;
                list1.add(tmp);
            }
            int cur=0;
            for (int i = 0; i <list.size() ; i++) {
                for (int j = 0; j <list1.size() ; j++) {
                    if(list1.get(j).equals(list.get(i))){cur=1;}
                }
            }

            return cur;
        }
        catch(Exception e){
            return -1;
        }
    }
    public static ArrayList<Integer> cuttime(int t){
        ArrayList<Integer>list =new ArrayList<>();
        while(t!=0){
            int tmp=t%1000;
            t=t/1000;
            list.add(tmp);
        }
        return list;
    }

    public static Vector<SelectCourse> query(String id){
        Vector<SelectCourse> res=new Vector<>();
        try{
            String sql="select * from selectcourse where ecardname='"+id+"' order by coursetime";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while(rs.next()){
                SelectCourse tmp=new SelectCourse(rs.getString("ecardname"),rs.getString("idx"),
                        rs.getString("coursename"),rs.getInt("coursetime"),
                        rs.getString("teacher"),rs.getInt("classroom"));
                res.add(tmp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String args[]){
        DataBase.start();
        int a=delete("213171645","野区经济学","明凯");
        System.out.println(a);

        DataBase.stop();
    }
}
