package srv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static DataInputStream cin;
    private static DataOutputStream cout;
    public static String eCardNumber = "";

    public static void run(){
        try{
            socket = new Socket("101.37.79.28", 8888);
            cin = new DataInputStream(socket.getInputStream());
            cout = new DataOutputStream(socket.getOutputStream());
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

    public static int query(String eCardNumber, String password){
        run();
        try {
            cout.writeInt(1);
            cout.writeUTF(eCardNumber);
            cout.writeUTF(password);
            cout.flush();
            int res = cin.readInt();
            stop();
            return res;
        }catch (Exception e){
            stop();
            return -3;
        }
    }

    public static int insert(String userName, String password, String eCardNumber, String sex, String status){
        run();
        try{
            cout.writeInt(2);
            cout.writeUTF(userName);
            cout.writeUTF(password);
            cout.writeUTF(eCardNumber);
            cout.writeUTF(sex);
            cout.writeUTF(status);
            cout.flush();
            int res = cin.readInt();
            stop();
            return res;
        }catch (Exception e){
            stop();
            return -3;
        }
    }

    public static int delete(String eCardNumber){
        run();
        try {
            cout.writeInt(3);
            cout.writeUTF(eCardNumber);
            cout.flush();
            int res = cin.readInt();
            stop();
            return res;
        }catch (Exception e){
            stop();
            return -3;
        }
    }

    public static int solve(String eCardNumber){
        run();
        try {
            cout.writeInt(4);
            cout.writeUTF(eCardNumber);
            cout.flush();
            int res = cin.readInt();
            stop();
            return res;
        }catch (Exception e){
            stop();
            return -3;
        }
    }

    public static int addAdmin(String eCardNumber){
        run();
        try{
            cout.writeInt(5);
            cout.writeUTF(eCardNumber);
            cout.flush();
            int res = cin.readInt();
            stop();
            return res;
        }catch (Exception e){
            stop();
            return -3;
        }
    }

    public static int cancelAdmin(String eCardNumber){
        run();
        try {
            cout.writeInt(6);
            cout.writeUTF(eCardNumber);
            cout.flush();
            int res = cin.readInt();
            stop();
            return res;
        }catch (Exception e){
            stop();
            return -3;
        }
    }

    public static int update(String userName, String password, String eCardNumber, String sex, String status){
        run();
        try{
            cout.writeInt(7);
            cout.writeUTF(userName);
            cout.writeUTF(password);
            cout.writeUTF(eCardNumber);
            cout.writeUTF(sex);
            cout.writeUTF(status);
            cout.flush();
            int res = cin.readInt();
            stop();
            return res;
        }catch (Exception e){
            stop();
            return -3;
        }
    }

    public static void main(String args[]){
        System.out.println(query("123", "sss"));
    }
}
