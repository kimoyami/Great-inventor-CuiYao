/*
 * Copyright: kimoyami
 */

package srv.course;

public class SelectCourse {
    private String eCardName;
    private String idx;
    private String courseName;
    private int time;

    public SelectCourse(String e,String i,String c,int t) {
        this.eCardName=e;
        this.idx=i;
        this.courseName=c;
        this.time=t;

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
}
