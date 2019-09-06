/*
 * Copyright: kimoyami
 */

//15
package srv.client;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static int insert(String s){
        Message message = new Message();
        try {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(s, Message.class);
        }catch (Exception e){
            e.printStackTrace();
            return -4;
        }
        return insert(message);
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

    public static Vector<Message> query(String sender, String receiver){
        Client.run();
        Vector<Message> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.writeUTF(sender);
            Client.cout.writeUTF(receiver);
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
        Vector<Message> res = query("123", "234");
        for(int i = 0; i < res.size(); i++){
            System.out.println(res.elementAt(i).getSender() + " " + res.elementAt(i).getReceiver() + " " + res.elementAt(i).getMessage() + " " + res.elementAt(i).getTime());
        }
    }

}
