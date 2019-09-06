/*
 * Copyright: kimoyami
 */

//15
package srv.client;

import srv.message.Message;

import java.util.Date;
import java.util.Vector;

public class MessageTrans {
    private static final int STARTPOS = 20;
    public static int insert(Message message){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeObject(message);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static int delete(Message message){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeObject(message);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static Vector<Message> query(){
        Client.run();
        Vector<Message> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for(int i = 0; i < n; i++){
                res.add((Message)Client.cin.readObject());
            }
        }catch (Exception e){
            e.printStackTrace();
            Client.stop();
        }
        return res;
    }

    public static Vector<Message> querynew(){
        Client.run();
        Vector<Message> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for(int i = 0; i < n; i++){
                res.add((Message)Client.cin.readObject());
            }
        }catch (Exception e){
            e.printStackTrace();
            Client.stop();
        }
        return res;
    }

    public static void main(String args[]){
        Message message = new Message("213171645", "213171643", "fuck you", new Date());
        System.out.println(delete(message));
    }
}
