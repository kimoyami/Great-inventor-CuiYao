/*
 * Copyright: kimoyami
 */

package dao;
import srv.course.SelectCourse;

import java.sql.ResultSet;
import java.util.ArrayList;

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
            String sql="select * from selectcourse where ecardname='"+course.getECardName()+"'";
            ResultSet rs= DataBase.s.executeQuery(sql);
            int cur=0;
            while(rs.next()){
                int a=rs.getInt("coursetime");
                cur=check(a,course.getTime());
                if(cur==1){break;}
            }
            System.out.println(cur);
            if(cur==1){return -2;}

            sql="insert into selectcourse(ecardname,idx,coursename,coursetime)values ('"+course.getECardName()+"','"+course.getIdx()+"','"+course.getCourseName()+"',"+course.getTime()+")";

            DataBase.s.executeUpdate(sql);

            DataBase.c.commit();
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
            String sql="delete from selectcourse where ecardname='"+eCardName+"' and coursename='"+coursename+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }
        catch(Exception e){
         return -1;
        }
    }
    public static void main(String args[]){
        //SelectCourse course=new SelectCourse();

        DataBase.start();
       int a=insert(new SelectCourse("213170001","0011","接Q学",135256));
        int b=exist("213170002","压刀学");
        int c=insert(new SelectCourse("213170001","0011","接Q学",156256));

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        DataBase.stop();


    }


}
