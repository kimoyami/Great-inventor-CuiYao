package dao;

import srv.course.Grade;

import java.sql.ResultSet;
import java.util.Vector;

public class DataGrade {
    public static int exist(String coursename, String ecardname) {
        try {
            String sql = "select * from grade where  ecardname='" + ecardname + "' and coursename='" + coursename + "'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if (!rs.next()) {
                return 0;
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    public static int insert(String ecardname, int semester, String coursename, int coursegrade, String teacher) {
        try {
            int a = exist(coursename, ecardname);
            if (exist(coursename, ecardname) == 1) {
                return 0;
            }

            String sql = "insert into grade(ecardname,semester,coursename,coursegrade,teacher)" +
                    " values('" + ecardname + "','" + semester + "'," +
                    "'" + coursename + "','" + coursegrade + "','" + teacher + "')";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int update(String ecardname,String coursename,int grade){
        try{
            if(exist(coursename,ecardname)!=1){return 0;}
            String sql="update grade set coursegrade="+grade+" where ecardname='"+ecardname+"' and coursename='"+coursename+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(String ecardname, String coursename) {
        try {
            if (exist(coursename, ecardname) != 1) {
                return 0;
            }
            String sql = "delete from grade  where coursename='" + coursename + "' and ecardname='" + ecardname + "'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int querygrade(String ecardname, String coursename) {
        Grade grade = new Grade();
        int tmpgrade=0;
        try {
            String sql = "select * from grade where coursename='" + coursename + "' and ecardname='" + ecardname + "'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if (rs.next()) {
                grade = new Grade(rs.getString("ecardname"), rs.getInt("semester"),
                        rs.getString("coursename"), rs.getInt("coursegrade"),
                        rs.getString("teacher"));
                tmpgrade=grade.getCourseGrade();
            }
            return tmpgrade;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Grade> querycourse(String coursename, String teacher, int grade0, int grade1) {
        Vector<Grade> res = new Vector<>();
        try {
            String sql = "select * from grade where coursename='" + coursename + "' " + "and teacher='" + teacher + "' " +
                    "order by coursegrade DESC";
            ResultSet rs = DataBase.s.executeQuery(sql);

            while (rs.next()) {
                Grade tmp = new Grade(rs.getString("ecardname"), rs.getInt("semester"),
                        rs.getString("coursename"), rs.getInt("coursegrade"),
                        rs.getString("teacher"));
                if (tmp.getCourseGrade() >= grade0 && tmp.getCourseGrade() <= grade1)
                    res.add(tmp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Vector<Grade> getAll(String ecardname) {
        Vector<Grade> res = new Vector<>();
        try {
            String sql = "select * from grade where ecardname='" + ecardname + "'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while (rs.next()) {
                Grade tmp = new Grade(rs.getString("ecardname"), rs.getInt("semester"),
                        rs.getString("coursename"), rs.getInt("coursegrade"),
                        rs.getString("teacher"));
                res.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static double getAverage(String coursename, String teacher) {
        double sum=0;
        int num=0;
        try {
            String sql = "select * from grade where coursename='" + coursename + "' " + "and teacher='" + teacher + "' " +
                    "order by coursegrade DESC";
            ResultSet rs = DataBase.s.executeQuery(sql);

            while (rs.next()) {
                Grade tmp = new Grade(rs.getString("ecardname"), rs.getInt("semester"),
                        rs.getString("coursename"), rs.getInt("coursegrade"),
                        rs.getString("teacher"));
                    sum+=tmp.getCourseGrade();
                    num+=1;
            }
            return sum/num;
        } catch (Exception e) {
        e.printStackTrace();
        return -1;
        }
    }

    public static void main(String args[]) {
        DataBase.start();
        int a=insert("213170001",18191,"信号与系统",100,"王世杰");
        System.out.println(a);
        int b=update("213170001","信号与系统",10);
        System.out.println(b);
        DataBase.stop();
    }
}
