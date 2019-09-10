package srv.server;

import dao.DataGrade;
import org.omg.CORBA.TCKind;
import srv.course.Grade;

import java.util.Vector;

public class GradeInfo {
    public static ServerThread now;

    public static void run(int op) {
        try {
            if (op == 1) {
                now.cout.writeInt(insert());
                now.cout.flush();
            }
            if (op == 2) {
                now.cout.writeInt(delete());
                now.cout.flush();
            }
            if (op == 3) {
                now.cout.writeInt(querygrade());
                now.cout.flush();
            }
            if (op == 4) {
                Vector<Grade> res = querycourse();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            if (op == 5) {
                Vector<Grade> res = getAll();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            if(op==6) {
                now.cout.writeDouble(getAverage());
                now.cout.flush();
            }
            if(op==7){
                now.cout.writeInt(update());
                now.cout.flush();
            }
        } catch (Exception e) {
            return;
        }
    }

    public static synchronized int insert() {
        String ecardname;
        int semester;
        String coursename;
        int coursegrade;
        String teacher;

        try {
            ecardname = now.cin.readUTF();
            semester = now.cin.readInt();
            coursename = now.cin.readUTF();
            coursegrade = now.cin.readInt();
            teacher = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataGrade.insert(ecardname, semester, coursename, coursegrade, teacher);
    }

    public static synchronized int delete() {
        String eCardName;
        String courseName;
        try {
            eCardName = now.cin.readUTF();
            courseName = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataGrade.delete(eCardName, courseName);
    }

    public static int querygrade() {
        String ecardname;
        String coursename;
        try {
            ecardname = now.cin.readUTF();
            coursename = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataGrade.querygrade(ecardname, coursename);
    }

    public static Vector<Grade> querycourse() {
        String coursename = "";
        String teacher = "";
        int grade0 = 0;
        int grade1 = 0;
        try {
            coursename = now.cin.readUTF();
            teacher = now.cin.readUTF();
            grade0 = now.cin.readInt();
            grade1 = now.cin.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGrade.querycourse(coursename, teacher, grade0, grade1);
    }

    public static Vector<Grade> getAll() {
        String ecardname = "";
        try {
            ecardname = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGrade.getAll(ecardname);
    }

    public static double getAverage() {
        String coursename = "";
        String teacher = "";
        try {
            coursename = now.cin.readUTF();
            teacher = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGrade.getAverage(coursename, teacher);
    }

    public static int update(){
        String ecardname;
        String coursename;
        int grade;
        try{
            ecardname=now.cin.readUTF();
            coursename=now.cin.readUTF();
            grade=now.cin.readInt();
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        return DataGrade.update(ecardname,coursename,grade);

    }

}
