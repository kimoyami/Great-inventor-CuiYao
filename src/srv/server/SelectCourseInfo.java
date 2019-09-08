package srv.server;

import dao.DataBase;
import dao.DataSelectCourse;
import srv.course.SelectCourse;

import javax.xml.crypto.Data;

public class SelectCourseInfo {
    public static void run(int op) {
        try {
            if (op == 1) {
                ServerThread.cout.writeInt(insert());
                ServerThread.cout.flush();
            }
            if (op == 2) {
                System.out.println("opd");
                ServerThread.cout.writeInt(delete());
                ServerThread.cout.flush();
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
        String teacher;

        try {
            e=ServerThread.cin.readUTF();;
            idx=ServerThread.cin.readUTF();;
            name=ServerThread.cin.readUTF();;
            time=ServerThread.cin.readInt();;
            teacher=ServerThread.cin.readUTF();;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -3;
        }
        return DataSelectCourse.insert(e,idx,name,time,teacher);
    }

    public static synchronized int delete() {
        String eCardName;
        String courseName;
        try {
            eCardName = ServerThread.cin.readUTF();
            courseName = ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -4;
        }
        return DataSelectCourse.delete(eCardName, courseName);
    }
}
