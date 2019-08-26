/*
Arthor: kimoyami
服务端的多线程
 */

package srv.Server;

import dao.DataBase;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerThread extends Thread{
    private Socket socket;
    public static DataInputStream cin = null;
    public static DataOutputStream cout = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            cin = new DataInputStream(socket.getInputStream());
            cout = new DataOutputStream(socket.getOutputStream());

            DataBase.start();

            while(true){
                int op = cin.readInt();
                System.out.println(op);
                if(op == -1) break;
                if(op >= 1 && op <= 7) Login.run(op);
            }

            cin.close();
            cout.close();
            DataBase.stop();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
