/*
 * Copyright: kimoyami
 */

package srv.server;

import srv.course.Course;
import dao.DataCourse;

import java.util.Vector;

public class CourseInfo {
    public static void run(int op) {
        try {
            if (op == 1) {
                ServerThread.cout.writeInt(insert());
                ServerThread.cout.flush();
            }
            if (op == 2) {
                ServerThread.cout.writeInt(delete());
                ServerThread.cout.flush();
            }
            if (op == 3) {
                Vector<Course> res = query();
                ServerThread.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    ServerThread.cout.writeObject(res.elementAt(i));
                }
                ServerThread.cout.flush();
            }

        } catch (Exception e) {
            return;
        }
    }

    public static synchronized int insert() {
        Course course;
        try {
            course = (Course) ServerThread.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataCourse.insert(course);
    }

    public static synchronized int delete() {
        String idx;
        String teacher;
        try {
            idx = ServerThread.cin.readUTF();
            teacher = ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataCourse.delete(idx, teacher);
    }

    public static Vector<Course> query() {
        String teacher = "";
        try {
            teacher = ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataCourse.query(teacher);
    }
}
