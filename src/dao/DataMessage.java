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
        try {
            String time = trans.format(message.getTime());
            System.out.println(time);
            String sql = "insert into message(sender, receiver, message, clock, tags, tagr) values('"+message.getSender()+"', '"+message.getReceiver()+"', '"+message.getMessage()+"', #"+time+"#, No, No)";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(Message message){
        try {
            String time = trans.format(message.getTime());
            String sql = "select * from message where sender = '"+message.getSender()+"' and receiver = '"+message.getReceiver()+"' and message = '"+message.getMessage()+"' and clock = #"+time+"#";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) return 0;
            sql = "delete from message where sender = '"+message.getSender()+"' and receiver = '"+message.getReceiver()+"' and message = '"+message.getMessage()+"' and clock = #"+time+"#";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Message> query(String sender, String receiver){
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
        return res;
    }

    public static Vector<Message> querynew(String sender, String receiver){
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
        return res;
    }

    public static void main(String args[]){
        DataBase.start();
        Message message = new Message("213171645", "213171643", "fuck you", new Date());
        System.out.println(delete(message));
        DataBase.stop();
    }
}
