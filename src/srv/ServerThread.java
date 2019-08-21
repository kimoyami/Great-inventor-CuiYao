/*
Arthor: kimoyami
服务端的多线程
 */

package srv;

import dao.DataBase;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerThread extends Thread{
    private Socket socket;
    Map<String, String> mp = new HashMap<String, String>();

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        DataInputStream cin = null;
        DataOutputStream cout = null;
        String line = "";

        try{
            cin = new DataInputStream(socket.getInputStream());
            cout = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);


            DataBase.start();
            while(true){
                line = "登陆/注册（0/1）";
                cout.writeUTF(line);
                cout.flush();
                line = cin.readUTF();
                if(line.equals("end")) break;
                if(line.equals("0")){
                    line = "账号和密码: ";
                    cout.writeUTF(line);
                    cout.flush();
                    line = cin.readUTF();
                    String password = cin.readUTF();
                    if(DataBase.query(line, password)) {
                        cout.writeUTF("YES");
                        cout.flush();
                    }
                    else {
                        cout.writeUTF("NO");
                        cout.flush();
                    }
                }
                else if(line.equals("1")){
                    line = "账号和密码: ";
                    cout.writeUTF(line);
                    cout.flush();
                    line = cin.readUTF();
                    String password = cin.readUTF();
                    if(!DataBase.insert(line, password)){
                        cout.writeUTF("exist");
                        cout.flush();
                    }
                    else {
                        cout.writeUTF("success");
                        cout.flush();
                        mp.put(line, password);
                    }
                }
            }
            cin.close();
            cout.close();
            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
