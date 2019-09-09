package srv.course;

import java.io.Serializable;
/*
课程名称courseName
上课时间courseTime
授课老师instructor
开课人数numberOfClass
已选学生数numberOfSelected
学生名单studentList
课程类Course(String, String, String, int, int)
 */

public class Course implements Serializable{
    private static final long serialVersionUID = 6445069540877410708L;
    private String idx;
    private String courseName;
    private int courseTime;
    private String teacher;
    private int maxPeople;
    private int remainNumber;
    private String state;
    private int classroom;

    public Course(String idx,String Name, int Time, String teacher, int maxPeople, int remainNumber,String state,int classroom){
        this.classroom=classroom;
        this.idx=idx;
        this.courseName = Name;
        this.courseTime = Time;
        this.teacher = teacher;
        this.maxPeople=maxPeople;
        this.remainNumber=remainNumber;
        this.state=state;
    }


    public String getIdx() {
        return this.idx;
    }
    public String getCourseName() {
        return this.courseName;
    }
    public int getCourseTime() {
        return this.courseTime;
    }
    public String getTeacher() {
        return this.teacher;
    }
    public int getMaxPeople() {
        return this.maxPeople;
    }
    public int getRemainNumber() {
        return this.remainNumber;
    }
    public String getState() {
        return this.state;
    }
    public int getClassroom(){return this.classroom;}

    public void setIdx(String idx) {
        this.idx = idx;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }
    public void setRemainNumber(int remainNumber) {
        this.remainNumber = remainNumber;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setClassroom(int classroom){this.classroom=classroom;}
}
