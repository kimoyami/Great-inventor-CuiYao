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

    public static int insert(SelectCourse course){
        try{
            if(exist(course.getECardName(),course.getCourseName())==1){return 0;}
            String sql="select * from course where idx='"+course.getIdx()+"' and teacher='"+course.getTeacher()+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.next();
            int tmp=rs.getInt("remainnumber");
            if (tmp == 0) {
                return -3;
            }

            sql="select * from selectcourse where ecardname='"+course.getECardName()+"'";
            rs= DataBase.s.executeQuery(sql);
            int cur=0;
            while(rs.next()){
                int a=rs.getInt("coursetime");
                cur=check(a,course.getTime());
                if(cur==1){break;}
            }
            System.out.println(cur);
            if(cur==1){return -2;}

            sql="insert into selectcourse(ecardname,idx,coursename,coursetime,teacher)" +
                    "values ('"+course.getECardName()+"','"+course.getIdx()+"'," +
                    "'"+course.getCourseName()+"',"+course.getTime()+",'"+course.getTeacher()+"')";
            tmp=tmp-1;
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            if(tmp>0){
                sql="update course set remainnumber="+tmp+" " +
                        "where idx='"+course.getIdx()+"' and teacher='"+course.getTeacher()+"'";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }
            else{
                String sta="已满";
                sql="update course set remainnumber="+tmp+", state='"+sta+"'" +
                        "where idx='"+course.getIdx()+"' and teacher='"+course.getTeacher()+"'";
                DataBase.s.executeUpdate(sql);
                DataBase.c.commit();
            }
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(String eCardName,String coursename){
        try{
            if(exist(eCardName,coursename)!=1){return 0;}
            System.out.println("nb");
            String sql="delete from selectcourse where ecardname='"+eCardName+"' and coursename='"+coursename+"'";
            System.out.println("nb");
            DataBase.s.executeUpdate(sql);
            System.out.println("nb");
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
         return -1;
        }
    }

    public static Vector<SelectCourse> query(String id){
        Vector<SelectCourse> res=new Vector<>();
        try{
            String sql="select * from selectcourse where ecardname='"+id+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while(rs.next()){
                SelectCourse tmp=new SelectCourse(rs.getString("ecardname"),rs.getString("idx"),
                        rs.getString("coursename"),rs.getInt("coursetime"),
                        rs.getString("teacher"));
                res.add(tmp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }


    public static void main(String args[]){
        //SelectCourse course=new SelectCourse();

        DataBase.start();

        int b=delete("213170004","一步大棋");

        System.out.println(b);
        DataBase.stop();
    }
}
