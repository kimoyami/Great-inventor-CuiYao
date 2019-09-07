/*
 * Copyright: kimoyami
 */

package dao;
import srv.course.*;

import java.sql.ResultSet;
import java.util.Vector;

public class DataCourse {
    public static int exist(String idx,String teacher){
        DataBase.start();
        try{
        String sql="select * from course where idx='"+idx+"' and teacher='"+teacher+"'";
        ResultSet rs=DataBase.s.executeQuery(sql);
        if(!rs.next()){
            DataBase.stop();
            return 0;
        }
            DataBase.stop();
        return 1;
        }
        catch(Exception e){
            DataBase.stop();
            return -1;
        }
    }

    public static int insert(Course course){
        DataBase.start();
        try{
        if(exist(course.getIdx(),course.getTeacher())==1){
            DataBase.stop();
            return 0;
        }
        String sql="insert into course(idx,coursename,teacher,coursetime,maxpeople,remainnumber,state) " +
                "values ('"+course.getIdx()+"','"+course.getCourseName()+"','"+course.getTeacher()+"'," +
                ""+course.getCourseTime()+","+course.getMaxPeople()+","+course.getRemainNumber()+"," +
                "'"+course.getState()+"')";
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

    public static int delete(String idx,String teacher) {
        DataBase.start();
        try {
            if (exist(idx, teacher) != 1) {
                DataBase.stop();
                return 0;
            }
            String sql = "delete from course where idx='" + idx + "' and teacher='" + teacher + "'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        } catch (Exception e) {
            DataBase.stop();
            return -1;
        }
    }
        public static Vector<Course> query(String teacher){
            DataBase.start();
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
            DataBase.stop();
            return res;
        }

    public static void main(String args[]){
            DataBase.start();
            DataBase.stop();
    }
}
