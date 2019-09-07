/*
 * Copyright: kimoyami
 */

//
package srv.server;

import dao.DataMessage;
import srv.message.Message;

import java.util.Vector;

public class MessageTrans {

    public static void run(int op){
        try {
            if(op == 1){
                ServerThread.cout.writeInt(insert());
                ServerThread.cout.flush();
            }
            else if(op == 2){
                ServerThread.cout.writeInt(delete());
                ServerThread.cout.flush();
            }
            else if(op == 3){
                Vector<Message> res = query();
                ServerThread.cout.writeInt(res.size());
                for(int i = 0; i < res.size(); i++){
                    ServerThread.cout.writeObject(res.elementAt(i));
                }
                ServerThread.cout.flush();
            }
            else if(op == 4){
                Vector<Message> res = querynew();
                ServerThread.cout.writeInt(res.size());
                for(int i = 0; i < res.size(); i++){
                    ServerThread.cout.writeObject(res.elementAt(i));
                }
                ServerThread.cout.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized int insert(){
        Message message = new Message();
        try {
            message = (Message)ServerThread.cin.readObject();
            System.out.println(message.getSender());
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataMessage.insert(message);
    }

    public static synchronized int delete(){
        Message message = new Message();
        try{
            message = (Message)ServerThread.cin.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataMessage.delete(message);
    }

    public static synchronized Vector<Message> query(){
        String sender = "", receiver = "";
        try {
            sender = ServerThread.cin.readUTF();
            receiver = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        return DataMessage.query(sender, receiver);
    }

    public static synchronized Vector<Message> querynew(){
        String sender = "", receiver = "";
        try {
            sender = ServerThread.cin.readUTF();
            receiver = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        return DataMessage.querynew(sender, receiver);
    }

}
