package srv.client;

import srv.file.Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

public class FilesTrans {
    private static final int STARTPOS = 100;

    public static int insert(String eCardNumber, String fileName, String url){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.writeUTF(fileName);
            Client.cout.flush();

            FileInputStream fis = new FileInputStream(url);
            OutputStream out = Client.socket.getOutputStream();
            byte[] buf = new byte[20480];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            Client.socket.shutdownOutput();
            out.flush();
            int res = Client.cin.readInt();
            fis.close();
            out.close();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            e.printStackTrace();
            return -2;
        }
    }

    public static int delete(String url){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeUTF(url);
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

    public static Vector<Files> query(){
        Client.run();
        Vector<Files> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for(int i = 0; i < n; i++) res.add((Files)Client.cin.readObject());
        }catch (Exception e){
            Client.stop();
            e.printStackTrace();
        }
        return res;
    }

    public static int download(String url, String path){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.writeUTF(url);
            Client.cout.flush();

            InputStream in = Client.socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(path);
            byte[] buf = new byte[20480];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            fos.close();
            in.close();
            Client.stop();
            return 1;
        }catch (Exception e){
            Client.stop();
            e.printStackTrace();
            return -2;
        }
    }

}
