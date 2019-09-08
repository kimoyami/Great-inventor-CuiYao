package srv.client;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import srv.course.SelectCourse;

import java.util.Vector;

public class SelectCourseInfo {
    private static final int STARTPOS = 80;

    public static int insert(SelectCourse course) {
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

    public static int delete(String eCardName, String courseName) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeUTF(eCardName);
            Client.cout.writeUTF(courseName);
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
    
    public static void main(String args[]) {
        Vector<SelectCourse>res=query("213171645");
        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getCourseName()+res.elementAt(i).getTime());
        }
    }

}
