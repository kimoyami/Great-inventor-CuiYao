package srv.message;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private static final long serialVersionUID = 7768054716259077555L;
    private String sender;
    private String receiver;
    private String message;
    private Date time;

    public Message(){
        sender = "";
        receiver = "";
        message = "";
        time = new Date();
    }

    public Message(String sender, String receiver, String message){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        time = new Date();
    }

    public Message(String sender, String receiver, String message, Date date){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.time = date;
    }

    public void setSender(String sender){
        this.sender = sender;
    }

    public String getSender(){
        return sender;
    }

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }

    public String getReceiver(){
        return receiver;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public Date getTime(){return time;}
}
