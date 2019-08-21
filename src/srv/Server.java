/*
Arthor: kimoyami
服务端
 */

package srv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8888; // 端口号

    public static void run(){
        try{
            ServerSocket server = new ServerSocket(PORT);
            int count = 0; //连接的用户数
            Socket socket = null;
            while(true){
                socket = server.accept();
                ServerThread serverThread = new ServerThread(socket);
                count++;
                System.out.println(count + " client" + (count > 1 ? "s" : "") + " now, success, address is: " + socket.getInetAddress().getHostAddress());
                serverThread.start();
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String args[]){
        run();
    }
}
