/*
Arthor: kimoyami
服务端的多线程
 */

package srv;

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
                if(op == 1) {
                    cout.writeInt(query());
                    cout.flush();
                }
                else if(op == 2){
                    cout.writeInt(insert());
                    cout.flush();
                }
                else if(op == 3){
                    cout.writeInt(delete());
                    cout.flush();
                }
                else if(op == 4){
                    cout.writeInt(solve());
                    cout.flush();
                }
                else if(op == 5){
                    cout.writeInt(addAdmin());
                    cout.flush();
                }
                else if(op == 6){
                    cout.writeInt(cancelAdmin());
                    cout.flush();
                }
                else if(op == 7){
                    cout.writeInt(update());
                    cout.flush();
                }
            }

            cin.close();
            cout.close();
            DataBase.stop();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static int query(){
        String eCardNumber, password;
        try {
            eCardNumber = cin.readUTF();
            password = cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.query(eCardNumber, password);
    }

    public static int insert(){
        String userName;
        String password;
        String eCardNumber;
        String sex;
        int age;
        String status;
        try {
            userName = cin.readUTF();
            password = cin.readUTF();
            eCardNumber = cin.readUTF();
            sex = cin.readUTF();
            age = cin.readInt();
            status = cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.insert(userName, password, eCardNumber, sex, age, status);
    }

    public static int delete(){
        String eCardNumber;
        try {
            eCardNumber = cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.delete(eCardNumber);
    }

    public static int solve(){
        String eCardNumber;
        try {
            eCardNumber = cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.solve(eCardNumber);
    }

    public static int addAdmin(){
        String eCardNumber;
        try {
            eCardNumber = cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.addAdmin(eCardNumber);
    }

    public static int cancelAdmin(){
        String eCardNumber;
        try {
            eCardNumber = cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.cancelAdmin(eCardNumber);
    }

    public static int update() {
        String userName;
        String password;
        String eCardNumber;
        String sex;
        int age;
        String status;
        try {
            userName = cin.readUTF();
            password = cin.readUTF();
            eCardNumber = cin.readUTF();
            sex = cin.readUTF();
            age = cin.readInt();
            status = cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.update(userName, password, eCardNumber, sex, age, status);
    }
}
