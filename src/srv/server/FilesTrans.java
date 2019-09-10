package srv.server;

import dao.DataBase;
import dao.DataFile;
import srv.file.Files;


import java.io.*;
import java.util.Random;
import java.util.Vector;

public class FilesTrans {
    private static final String BASE = "C:\\Users\\Administrator\\Documents\\GitHub\\Great-inventor-CuiYao\\file\\";
    public static ServerThread now;

    public static void run(int op) {
        try {
            if (op == 1) insert();
            if (op == 2) {
                now.cout.writeInt(delete());
                now.cout.flush();
            }
            if (op == 3) {
                Vector<Files> res = query();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) now.cout.writeObject(res.elementAt(i));
                now.cout.flush();
            }
            if (op == 4) download();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void insert() {
        String eCardNumber = "";
        String fileName = "";
        try {
            eCardNumber = now.cin.readUTF();
            fileName = now.cin.readUTF();
            StringBuffer rand = new StringBuffer();
            Random random = new Random();
            String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

            for (int i = 0; i < 20; i++) {
                int num = random.nextInt(62);
                rand.append(str.charAt(num));
            }

            for (int i = fileName.length() - 1; i >= 0; i--) {
                if (fileName.charAt(i) == '.') {
                    for (int j = i; j < fileName.length(); j++) {
                        rand.append(fileName.charAt(j));
                    }
                    break;
                }
            }

            String url = BASE + rand.toString();
            InputStream in = now.socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(url);
            byte[] buf = new byte[20480];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();

            now.cout.writeInt(DataFile.insert(eCardNumber, fileName, url));
            now.cout.flush();
            fos.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void download() {
        String url = "";
        try {
            url = now.cin.readUTF();
            DataFile.add(url);

            FileInputStream fis = new FileInputStream(url);
            OutputStream out = now.socket.getOutputStream();

            byte[] buf = new byte[20480];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            now.socket.shutdownOutput();
            out.flush();
            now.cout.flush();
            fis.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized int delete() {
        String url = "";
        try {
            url = now.cin.readUTF();
            File file = new File(url);
            if (!file.exists()) return 0;
            file.delete();
            DataBase.start();
            int a = DataFile.delete(url);
            DataBase.stop();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Files> query() {
        DataBase.start();
        Vector<Files> res = DataFile.query();
        DataBase.stop();

        return res;
    }

}
