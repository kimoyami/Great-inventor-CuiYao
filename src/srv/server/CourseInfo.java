/*
 * Copyright: kimoyami
 */

package srv.server;

import srv.course.Course;
import dao.DataCourse;

import java.util.Vector;

public class CourseInfo {
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
                Vector<Course> res = query();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            if (op == 4) {
                Vector<Course> res = getAll();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }

        } catch (Exception e) {
            return;
        }
    }

    public static synchronized int insert() {
        Course course;
        try {
            course = (Course) now.cin.readObject();
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
            idx = now.cin.readUTF();
            teacher = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataCourse.delete(idx, teacher);
    }

    public static Vector<Course> query() {
        String teacher = "";
        try {
            teacher = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataCourse.query(teacher);
    }

    public static Vector<Course> getAll(){
        return DataCourse.getAll();
    }
}
