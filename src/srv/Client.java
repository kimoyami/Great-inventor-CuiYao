package srv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void run(){

        try{
            Socket socket = new Socket("localhost", 8888);
            DataInputStream cin = new DataInputStream(socket.getInputStream());
            DataOutputStream cout = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String line;
            while(true){
                line = cin.readUTF();
                if(line.equals("end")) break;
                System.out.println("Server: " + line);
                line = scanner.nextLine();
                cout.writeUTF(line);
                cout.flush();
                line = cin.readUTF();
                System.out.println(line);
                line = scanner.nextLine();
                cout.writeUTF(line);
                cout.flush();
                line = scanner.nextLine();
                cout.writeUTF(line);
                cout.flush();
                line = cin.readUTF();
                System.out.println("Server: " + line);
            }
            cin.close();
            cout.close();
            scanner.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        run();
    }
}
