/*
Author: kimoyami
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
                if(op >= 1 && op <= 10) Login.run(op);
                op -= 10;
                if(op >= 1 && op <= 5) PersonInfo.run(op);
                op -= 5;
                if(op >= 1 && op <= 4) MessageTrans.run(op);
                op -= 4;
                if(op >= 1 && op <= 4) BookInfo.run(op);
                op -= 4;
                if(op >= 1 && op <= 4) Bank_Info.run(op);
                op -= 4;
                if(op >= 1 && op <= 4) GoodInfo.run(op);
                op -= 4;
            }

            cin.close();
            cout.close();
            DataBase.stop();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
