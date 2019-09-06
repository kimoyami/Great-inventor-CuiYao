/*
Author: kimoyami
服务端的多线程
 */

package srv.server;

import dao.DataBase;
import dao.DataHead;
import srv.course.SelectCourse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerThread extends Thread {
    private static final String BASE = "F:\\GitHub\\Great-inventor-CuiYao\\headimage\\";
    private Socket socket;
    public static ObjectInputStream cin = null;
    public static ObjectOutputStream cout = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            cout = new ObjectOutputStream(socket.getOutputStream());
            cin = new ObjectInputStream(socket.getInputStream());

            DataBase.start();

            while (true) {
                int op = cin.readInt();
                if (op == -1) break;
                if (op == -100) update();
                if (op >= 1 && op <= 10) Login.run(op);
                op -= 10;
                if (op >= 1 && op <= 5) PersonInfo.run(op);
                op -= 5;
                if (op >= 1 && op <= 4) MessageTrans.run(op);
                op -= 4;
                if (op >= 1 && op <= 4) BookInfo.run(op);
                op -= 4;
                if (op >= 1 && op <= 4) Bank_Info.run(op);
                op -= 4;
                if (op >= 1 && op <= 4) GoodInfo.run(op);
                op-=4;
                if(op == 1) Head.run(op);
                op -= 1;
                if(op>=1&&op<=3)CourseInfo.run(op);
                op -= 3;
                if(op>=1&&op<=2) SelectCourseInfo.run(op);
                op-=2;
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
            cout.writeInt(DataHead.update(eCardNumber, url));
            cout.flush();
            fos.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}