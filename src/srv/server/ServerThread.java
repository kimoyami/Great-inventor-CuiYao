/*
Arthor: kimoyami
服务端的多线程
 */

package srv.server;

import dao.DataBase;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    public static ObjectInputStream cin = null;
    public static ObjectOutputStream cout = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            cout = new ObjectOutputStream(socket.getOutputStream());
            cin = new ObjectInputStream(socket.getInputStream());

            DataBase.start();

            while(true){
                int op = cin.readInt();
                System.out.println(op);
                if(op == -1) break;
                if(op >= 1 && op <= 9) Login.run(op);
                op -= 9;
                if(op >= 1) PersonInfo.run(op);
            }

            cin.close();
            cout.close();
            DataBase.stop();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
