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
        SelectCourse course;
        try {
            course = (SelectCourse) ServerThread.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataSelectCourse.insert(course);
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
