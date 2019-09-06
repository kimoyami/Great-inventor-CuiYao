package srv.client;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import srv.course.SelectCourse;

public class SelectCourseInfo {
    private static final int STARTPOS = 35;

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
public static void main(String args[]){

}
}
