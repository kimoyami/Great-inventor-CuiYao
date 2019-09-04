/*
Arthor: kimoyami
 */

package srv.client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static Socket socket;
    public static ObjectInputStream cin;
    public static ObjectOutputStream cout;
    public static String eCardNumber = "";

    public static void run(){
        try{
<<<<<<< Updated upstream
            socket = new Socket("101.37.79.28", 8888);//
=======
            socket = new Socket("101.37.79.28", 8888);
>>>>>>> Stashed changes
            cin = new ObjectInputStream(socket.getInputStream());
            cout = new ObjectOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void stop(){
        try {
            socket.close();
            cin.close();
            cout.close();
        }catch (Exception e){
            return;
        }
    }

}
