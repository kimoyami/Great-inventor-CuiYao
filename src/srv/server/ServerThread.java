/*
Author: kimoyami
服务端的多线程
 */

package srv.server;

import dao.DataBase;
import dao.DataHead;
import srv.course.SelectCourse;
import srv.message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

public class ServerThread extends Thread {
    private static final String BASE = "C:\\Users\\Administrator\\Documents\\GitHub\\Great-inventor-CuiYao\\headimage\\";
    public Socket socket;
    public ObjectInputStream cin = null;
    public ObjectOutputStream cout = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            cout = new ObjectOutputStream(socket.getOutputStream());
            cin = new ObjectInputStream(socket.getInputStream());

            DataBase.start();

            while (true) {
                synchronized(new Object()){
                int op = cin.readInt();
                if (op == -1) break;
                if (op == -100) update();
                if (op >= 1 && op <= 10) {
                    Login.now = this;
                    Login.run(op);
                }
                op -= 10;
                if (op >= 1 && op <= 10) {
                    PersonInfo.now = this;
                    PersonInfo.run(op);
                }
                op -= 10;
                if (op >= 1 && op <= 10) {
                    MessageTrans.now = this;
                    MessageTrans.run(op);
                }
                op -= 10;
                if (op >= 1 && op <= 10) {
                    BookInfo.now = this;
                    BookInfo.run(op);
                }
                op -= 10;
                if (op >= 1 && op <= 10) {
                    Bank_Info.now = this;
                    Bank_Info.run(op);
                }
                op -= 10;
                if (op >= 1 && op <= 10) {
                    GoodInfo.now = this;
                    GoodInfo.run(op);
                }
                op -= 10;
                if (op == 1) {
                    Head.now = this;
                    Head.run(op);
                }
                op -= 10;
                if (op >= 1 && op <= 10) {
                    CourseInfo.now = this;
                    CourseInfo.run(op);
                }
                op -= 10;
                if (op >= 1 && op <= 10) {
                    SelectCourseInfo.now = this;
                    SelectCourseInfo.run(op);
                }
                op -= 10;
                if (op >= 1 && op <= 10) {
                    GradeInfo.now = this;
                    GradeInfo.run(op);
                }
                op -= 10;
                if(op >= 1 && op <= 10){
                    FilesTrans.now = this;
                    FilesTrans.run(op);
                }
                op -= 10;
            }
            }
            cin.close();
            cout.close();
            DataBase.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void update() {
        String eCardNumber = "";
        try {
            eCardNumber = cin.readUTF();
            StringBuffer fileName = new StringBuffer();
            Random random = new Random();
            String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
            for(int i = 0; i < 20; i++){
                int num = random.nextInt(62);
                fileName.append(str.charAt(num));
            }
            String url = BASE + fileName.toString() + ".jpg";
            InputStream in = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(url);
            byte[] buf = new byte[2048];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            url = "http://101.37.79.28/headimage/" + fileName.toString() + ".jpg";
            cout.writeInt(DataHead.update(eCardNumber, url));
            cout.flush();
            fos.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}