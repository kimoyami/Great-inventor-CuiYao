/*
Arthor: kimoyami
 */

package srv.server;

import dao.DataPerson;
import srv.person.Person;

public class PersonInfo {
    public static ServerThread now;
    public static void run(int op){
        try {
            if(op == 1){
                now.cout.writeInt(insert());
                now.cout.flush();
            }
            if(op == 2){
                now.cout.writeInt(delete());
                now.cout.flush();
            }
            if(op == 3){
                now.cout.writeObject(query());
                now.cout.flush();
            }
            if (op == 4) {
                now.cout.writeInt(update());
                now.cout.flush();
            }
            if(op == 5){
                now.cout.writeInt(isNew());
                now.cout.flush();
            }
        }catch (Exception e){
            return;
        }
    }

    public static synchronized int insert(){
        Person person;
        try {
            person = (Person)now.cin.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.insert(person);
    }

    public static synchronized int delete(){
        String eCardNumber;
        try {
            eCardNumber = now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.delete(eCardNumber);
    }

    public static Person query(){
        String eCardNumber = "";
        try {
            eCardNumber =  now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        return DataPerson.query(eCardNumber);
    }

    public static synchronized int update(){
        Person person;
        try{
            person = (Person)now.cin.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.update(person);
    }



    public static int isNew(){
        String eCardNumber = "";
        try {
            eCardNumber = now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.isNew(eCardNumber);
    }

}
