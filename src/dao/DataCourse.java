/*
 * Copyright: kimoyami
 */

package dao;
import srv.course.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class DataCourse {
    public static int exist(String idx,String teacher){
        try{
        String sql="select * from course where idx='"+idx+"' and teacher='"+teacher+"'";
        ResultSet rs=DataBase.s.executeQuery(sql);
        if(!rs.next()){return 0;}
        return 1;
        }
        catch(Exception e){
            return -1;
        }
    }

    public static int insert(Course course){
        try{
        if(exist(course.getIdx(),course.getTeacher())==1){return 0;}

        String sql="insert into course(idx,coursename,teacher,coursetime,maxpeople,remainnumber,state,classroom) " +
                "values ('"+course.getIdx()+"','"+course.getCourseName()+"','"+course.getTeacher()+"'," +
                ""+course.getCourseTime()+","+course.getMaxPeople()+","+course.getRemainNumber()+"," +
                "'"+course.getState()+"','"+course.getClassroom()+"')";
        DataBase.s.executeUpdate(sql);
        DataBase.c.commit();
        return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(String idx,String teacher) {
        try {
            if (exist(idx, teacher) != 1) {
                return 0;
            }
            String sql = "delete from course where idx='" + idx + "' and teacher='" + teacher + "'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static Vector<Course> query(String teacher){
            Vector<Course> res = new Vector<>();
            try{
                String sql = "select * from course where teacher = '"+teacher+"'";
                ResultSet rs = DataBase.s.executeQuery(sql);
                while(rs.next()){
                    Course tmp = new Course(rs.getString("idx"),
                            rs.getString("coursename"), rs.getInt("coursetime"),
                            rs.getString("teacher"), rs.getInt("maxpeople"),
                            rs.getInt("remainnumber"),rs.getString("state"),
                            rs.getInt("classroom"));
                    res.addElement(tmp);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return res;
        }

    public static Vector<Course> getAll(){
        Vector<Course> res = new Vector<>();
        try{
            String sql = "select * from course ";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while(rs.next()){
                Course tmp = new Course(rs.getString("idx"),
                        rs.getString("coursename"), rs.getInt("coursetime"),
                        rs.getString("teacher"), rs.getInt("maxpeople"),
                        rs.getInt("remainnumber"),rs.getString("state"),
                        rs.getInt("classroom"));
                res.addElement(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
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

    public static void main(String args[]){
            DataBase.start();
        Vector<Course>res= query("王世杰");

        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getCourseName());
        }
            DataBase.stop();
    }
}
