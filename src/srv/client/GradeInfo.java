package srv.client;

import srv.course.Grade;

import java.util.Vector;

public class GradeInfo {
    private static final int STARTPOS = 90;
    public static int insert(String ecardname, int semester, String coursename, int coursegrade, String teacher) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeUTF(ecardname);
            Client.cout.writeInt(semester);
            Client.cout.writeUTF(coursename);
            Client.cout.writeInt(coursegrade);
            Client.cout.writeUTF(teacher);
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

    public static int querygrade(String eCardName, String courseName){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 3);
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

    public static Vector<Grade> querycourse(String coursename,String teacher,int grade0,int grade1) {
        Client.run();
        Vector<Grade> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.writeUTF(coursename);
            Client.cout.writeUTF(teacher);
            Client.cout.writeInt(grade0);
            Client.cout.writeInt(grade1);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Grade) Client.cin.readObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Vector<Grade> getAll(String ecardname) {
        Client.run();
        Vector<Grade> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 5);
            Client.cout.writeUTF(ecardname);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Grade) Client.cin.readObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static double getAverage(String coursename, String teacher){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 6);
            Client.cout.writeUTF(coursename);
            Client.cout.writeUTF(teacher);
            Client.cout.flush();
            double res = Client.cin.readDouble();
            System.out.println("res="+res);
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }
    public static int update(String ecardname,String coursename,int grade){
        Client.run();
        try{
            Client.cout.writeInt(STARTPOS+7);
            Client.cout.writeUTF(ecardname);
            Client.cout.writeUTF(coursename);
            Client.cout.writeInt(grade);
            Client.cout.flush();
            int res=Client.cin.readInt();
            Client.stop();
            return res;
        }catch(Exception e){
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }


    public static void main(String args[]) {
        int a=update("213170001","信号与系统",20);
    }
}
/*
* int a=insert("213170009",19201,"软件实践",96,"王世杰");
* System.out.println(a);
*  int a=delete("213170009","软件实践");
        System.out.println(a);
               int b=querygrade("213170002","软件实践2");
        System.out.println(b);
        Vector<Grade>res=querycourse("信号与系统","王世杰",0,100);
        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getEcardNUmber());
        }

* */