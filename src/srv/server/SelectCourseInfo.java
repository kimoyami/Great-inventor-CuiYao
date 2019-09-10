package srv.server;

import dao.DataBase;
import dao.DataSelectCourse;
import srv.course.SelectCourse;

import javax.xml.crypto.Data;
import java.util.Vector;

public class SelectCourseInfo {
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
                Vector<SelectCourse> res = query();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static synchronized int insert() {
        String e;
        String idx;
        String name;
        int time;
        int classroom;
        String teacher;

        try {
            e=now.cin.readUTF();
            idx=now.cin.readUTF();
            name=now.cin.readUTF();
            time=now.cin.readInt();
            teacher=now.cin.readUTF();
            classroom=now.cin.readInt();
        } catch (Exception ex) {
            ex.printStackTrace();
            return -3;
        }
        return DataSelectCourse.insert(e,idx,name,time,teacher,classroom);
    }

    public static synchronized int delete() {
        String eCardName;
        String courseName;
        String teacher;
        try {
            eCardName = now.cin.readUTF();
            courseName = now.cin.readUTF();
            teacher=now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -4;
        }
        return DataSelectCourse.delete(eCardName, courseName,teacher);
    }

    public static Vector<SelectCourse> query() {
        String id = "";
        try {
            id = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataSelectCourse.query(id);
    }

}
