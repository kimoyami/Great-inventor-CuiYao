package srv.course;

import java.io.Serializable;
/*
* 学生成绩
* */

public class Grade implements Serializable {
    private static final long serialVersionUID= -1290451614230058291L;
    private String courseName;
    private int courseGrade;
    private String EcardName;
    private int courseSemeter;
    private String teacher;

    public Grade(){
        this.courseGrade=0;
        this.courseName="";
        this.courseSemeter=0;
        this.EcardName="";
    }

    public Grade(String EcardNUmber,int courseSemeter,String courseName,int courseGrade,String teacher) {
        this.courseGrade=courseGrade;
        this.courseName=courseName;
        this.EcardName=EcardNUmber;
        this.courseSemeter=courseSemeter;
        this.teacher=teacher;
    }


    public String getCourseName() {
        return this.courseName;
    }
    public int getCourseGrade() {
        return this.courseGrade;
    }
    public String getEcardNUmber() {
        return this.EcardName;
    }
    public int getCourseSemeter() {
        return this.courseSemeter;
    }
    public String getTeacher(){return this.teacher;}

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseGrade(int courseGrade) {
        this.courseGrade = courseGrade;
    }
    public void setEcardNUmber(String EcardNUmber) {
        this.EcardName = EcardNUmber;
    }
    public void setCourseSemeter(int courseSemeter) {
        this.courseSemeter = courseSemeter;
    }
    public void setTeacher(String teacher){this.teacher=teacher;}
}
