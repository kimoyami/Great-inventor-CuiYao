/*
Arthor: kimoyami
 */

package srv.server;

import dao.DataPerson;
import srv.person.Person;

public class PersonInfo {

    public static void run(int op){
        try {
            if(op == 1){
                ServerThread.cout.writeInt(insert());
                ServerThread.cout.flush();
            }
            if(op == 2){
                ServerThread.cout.writeInt(delete());
                ServerThread.cout.flush();
            }
            if(op == 3){
                ServerThread.cout.writeObject(query());
                ServerThread.cout.flush();
            }
            if (op == 4) {
                ServerThread.cout.writeInt(update());
                ServerThread.cout.flush();
            }
            if(op == 5){
                ServerThread.cout.writeInt(isNew());
                ServerThread.cout.flush();
            }
        }catch (Exception e){
            return;
        }
    }

    public static synchronized int insert(){
        Person person;
        try {
            person = (Person)ServerThread.cin.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.insert(person);
    }

    public static synchronized int delete(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.delete(eCardNumber);
    }

    public static Person query(){
        String eCardNumber = "";
        try {
            eCardNumber =  ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        return DataPerson.query(eCardNumber);
    }

    public static int update(){
        Person person;
        try{
            person = (Person)ServerThread.cin.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.update(person);
    }



    public static int isNew(){
        String eCardNumber = "";
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.isNew(eCardNumber);
    }

}
