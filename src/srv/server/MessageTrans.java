/*
 * Copyright: kimoyami
 */

//
package srv.server;

import dao.DataBase;
import dao.DataMessage;
import srv.message.Message;

import java.util.Vector;

public class MessageTrans {
    public static ServerThread now;
    
    public static void run(int op){
        try {
            if(op == 1){
                now.cout.writeInt(insert());
                now.cout.flush();
            }
            else if(op == 2){
                now.cout.writeInt(delete());
                now.cout.flush();
            }
            else if(op == 3){
                Vector<Message> res = query();
                now.cout.writeInt(res.size());
                for(int i = 0; i < res.size(); i++){
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            else if(op == 4){
                Vector<Message> res = querynew();
                now.cout.writeInt(res.size());
                for(int i = 0; i < res.size(); i++){
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized int insert(){
        Message message = new Message();
        try {
            message = (Message)now.cin.readObject();
            System.out.println(message.getSender());
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataMessage.insert(message);
        DataBase.stop();

        return a;
    }

    public static synchronized int delete(){
        Message message = new Message();
        try{
            message = (Message)now.cin.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataMessage.delete(message);
        DataBase.stop();

        return a;
    }

    public static Vector<Message> query(){
        String sender = "", receiver = "";
        try {
            sender = now.cin.readUTF();
            receiver = now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        DataBase.start();
        Vector<Message>res=DataMessage.query(sender, receiver);
        DataBase.stop();

        return res;
    }

    public static Vector<Message> querynew(){
        String sender = "", receiver = "";
        try {
            sender = now.cin.readUTF();
            receiver = now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        DataBase.start();
        Vector<Message>res=DataMessage.querynew(sender, receiver);
        DataBase.stop();

        return res;
    }

}
