package srv;

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

public class Course {
    private static final long serialVersionUID = 6445069540877410708L;

    private String courseName;
    private String courseTime;
    private String instructor;
    private int numberOfClass;
    private int numberOfSelected;
    private String[] studentList;

    public Course(String cName, String cTime, String cInstr, int numberOfClass, int numberOfSelected){
        this.courseName = cName;
        this.courseTime = cTime;
        this.instructor = cInstr;
        this.numberOfClass = numberOfClass;
        this.numberOfSelected = numberOfSelected;
        this.studentList = new String[numberOfClass];
        for(int i = 0;i < numberOfClass;i++){
            this.studentList[i] = null;
        }
    }

    public void setCourseName(String param){
        this.courseName = param;
    }
    public String getCourseName(){
        return this.courseName;
    }

    public void setCourseTime(String param){
        this.courseTime = param;
    }
    public String getCourseTime(){
        return this.courseTime;
    }

    public void setInstructor(String param){
        this.instructor = param;
    }
    public String getInstructor(){
        return this.instructor;
    }

    public void setNumberOfClass(int param){
        this.numberOfClass = param;
    }
    public int getNumberOfClass(){
        return this.numberOfClass;
    }

    public void setNumberOfSelected(int param){
        this.numberOfSelected = param;
    }
    public int getGetNumberOfClass() {
        return this.numberOfSelected;
    }

    public void setStudentList(String param){
        this.studentList[this.numberOfSelected - 1] = param;
    }
    public void getStudentList(){
        for(int i=0; i < this.studentList.length; i++)
            System.out.println(this.studentList[i] + "  ");
    }
}
