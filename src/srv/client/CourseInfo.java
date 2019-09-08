/*
 * Copyright: kimoyami
 */

package srv.client;

import srv.course.Course;

import java.util.Vector;

public class CourseInfo {
    private static final int STARTPOS = 70;

    public static int insert(Course course) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeObject(course);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }


    public static int delete(String idx, String teacher) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeUTF(idx);
            Client.cout.writeUTF(teacher);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -3;
        }
    }

    public static Vector<Course> query(String teacher) {
        Client.run();
        Vector<Course> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.writeUTF(teacher);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Course) Client.cin.readObject());
            }
            Client.stop();

        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
        }
        return res;
    }

    public static Vector<Course>getAll(){
        Client.run();
        Vector<Course> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Course) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            Client.stop();
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String args[]){
        Vector<Course>res= getAll();
        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getCourseName());
        }
    }
}
