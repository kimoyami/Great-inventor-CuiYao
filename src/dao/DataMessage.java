/*
Arthor: kimoyami
 */
package dao;

import srv.message.Message;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class DataMessage {
    private static SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static int insert(Message message){
        DataBase.start();
        try {
            String time = trans.format(message.getTime());
            System.out.println(time);
            String sql = "insert into message(sender, receiver, message, clock, tags, tagr) values('"+message.getSender()+"', '"+message.getReceiver()+"', '"+message.getMessage()+"', #"+time+"#, No, No)";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            DataBase.stop();
            return -1;
        }
    }

    public static int delete(Message message){
        DataBase.start();
        try {
            String time = trans.format(message.getTime());
            String sql = "select * from message where sender = '"+message.getSender()+"' and receiver = '"+message.getReceiver()+"' and message = '"+message.getMessage()+"' and clock = #"+time+"#";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) {
                DataBase.stop();
                return 0;
            }
            sql = "delete from message where sender = '"+message.getSender()+"' and receiver = '"+message.getReceiver()+"' and message = '"+message.getMessage()+"' and clock = #"+time+"#";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }catch (Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Message> query(String sender, String receiver){
        DataBase.start();
        Vector<Message> res = new Vector<>();
        try {
            String sql = "select * from message where sender = '"+sender+"' and receiver = '"+receiver+"' or (sender = '"+receiver+"' and receiver = '"+sender+"')";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while(rs.next()){
                res.addElement(new Message(rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DataBase.stop();
        return res;
    }

    public static Vector<Message> querynew(String sender, String receiver){
        DataBase.start();
        Vector<Message> res = new Vector<>();
        try {
            String sql = "select * from message where (sender = '"+sender+"' and receiver = '"+receiver+"' and tags = No) or (sender = '"+receiver+"' and receiver = '"+sender+"' and tagr = No)";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while(rs.next()){
                res.addElement(new Message(rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5)));
            }
            sql = "update message set tags = Yes where (sender = '"+sender+"' and receiver = '"+receiver+"')";
            DataBase.s.executeUpdate(sql);
            sql = "update message set tagr = Yes where (sender = '"+receiver+"' and receiver = '"+sender+"')";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        DataBase.stop();
        return res;
    }

    public static void main(String args[]){
        DataBase.start();

        DataBase.stop();
    }
}
