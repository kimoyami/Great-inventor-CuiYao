/*
 * Copyright: kimoyami
 */

package srv.client;

import java.io.FileInputStream;
import java.io.OutputStream;

public class Head {

    private static final int STARTPOS = 60;
    private static final String DEFAULTURL = "../headimage/timg.jpg";
    public static int update(String eCardNumber, String url){
        Client.run();
        try {
            Client.cout.writeInt(-100);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();

            FileInputStream fis = new FileInputStream(url);
            OutputStream out = Client.socket.getOutputStream();
            byte[] buf = new byte[2048];
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
            e.printStackTrace();
            Client.stop();
            return -3;
        }
    }

    public static String query(String eCardNumber){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            String res = Client.cin.readUTF();
            Client.stop();
            return res;
        }catch (Exception e){
            e.printStackTrace();
            Client.stop();
            return DEFAULTURL;
        }
    }

    public static void main(String args[]){
        String url = "F:\\GitHub\\untitled\\5a4af734f0021.jpg";
        System.out.println(update("213171645", url));
        System.out.println(query("213171645"));
    }
}
