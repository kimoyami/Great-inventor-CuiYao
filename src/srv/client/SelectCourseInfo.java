package srv.client;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import srv.course.SelectCourse;


import java.util.Vector;

public class SelectCourseInfo {
    private static final int STARTPOS = 80;

    public static int insert(String e,String idx,String name,int time,String teacher,int classroom) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeUTF(e);
            Client.cout.writeUTF(idx);
            Client.cout.writeUTF(name);
            Client.cout.writeInt(time);
            Client.cout.writeUTF(teacher);
            Client.cout.writeInt(classroom);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static int delete(String eCardName, String courseName,String teacher) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeUTF(eCardName);
            Client.cout.writeUTF(courseName);
            Client.cout.writeUTF(teacher);
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

    public static Vector<SelectCourse> query(String id) {
        Client.run();
        Vector<SelectCourse> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.writeUTF(id);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((SelectCourse) Client.cin.readObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static int exist(String eCardName, String CourseName){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.writeUTF(eCardName);
            Client.cout.writeUTF(CourseName);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            e.printStackTrace();
            return -2;
        }
    }
    
    public static void main(String args[]) {
        int a=delete("213170004","编译原理","翟玉庆");
        System.out.println(a);
        }
    }
