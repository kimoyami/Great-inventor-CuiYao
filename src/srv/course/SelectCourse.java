/*
 * Copyright: Mashiro
 */

package srv.course;

import java.io.Serializable;

public class SelectCourse implements Serializable {
    private static final long serialVersionUID = -8211013883712433979L;
    private String eCardName;
    private String idx;
    private String courseName;
    private int time;
    private String teacher;
    private int classroom;

    public SelectCourse(String e,String i,String c,int t,String teacher,int classroom) {
        this.eCardName=e;
        this.idx=i;
        this.courseName=c;
        this.time=t;
        this.teacher=teacher;
        this.classroom=classroom;
    }


    public String getECardName() {
        return this.eCardName;
    }
    public String getIdx() {
        return this.idx;
    }
    public String getCourseName() {
        return this.courseName;
    }
    public int getTime() {
        return this.time;
    }
    public String getTeacher() {
        return this.teacher;
    }
    public int getClassroom(){return this.classroom;}

    public void setECardName(String eCardName) {
        this.eCardName = eCardName;
    }
    public void setIdx(String idx) {
        this.idx = idx;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public void setClassroom(int classroom){this.classroom=classroom;}
}
