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
        String sql="insert into course(idx,coursename,teacher,coursetime,maxpeople,remainnumber,state) " +
                "values ('"+course.getIdx()+"','"+course.getCourseName()+"','"+course.getTeacher()+"'," +
                ""+course.getCourseTime()+","+course.getMaxPeople()+","+course.getRemainNumber()+"," +
                "'"+course.getState()+"')";
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
                            rs.getInt("remainnumber"),rs.getString("state"));
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
                        rs.getInt("remainnumber"),rs.getString("state"));
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
                        rs.getInt("remainnumber"),rs.getString("state"));
                res.addElement(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
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


    public static void main(String args[]){
            DataBase.start();
            DataBase.stop();
    }
}
