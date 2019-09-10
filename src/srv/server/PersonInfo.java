/*
Arthor: kimoyami
 */

package srv.server;

import dao.DataBase;
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
        DataBase.start();
        int a=DataPerson.insert(person);
        DataBase.stop();

        return a;
    }

    public static synchronized int delete(){
        String eCardNumber;
        try {
            eCardNumber = now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataPerson.delete(eCardNumber);
        DataBase.stop();
        return a;
    }

    public static Person query(){
        String eCardNumber = "";
        try {
            eCardNumber =  now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        DataBase.start();
        Person person=DataPerson.query(eCardNumber);
        DataBase.stop();


        return person;
    }

    public static synchronized int update(){
        Person person;
        try{
            person = (Person)now.cin.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataPerson.update(person);
        DataBase.stop();
        return a;
    }



    public static int isNew(){
        String eCardNumber = "";
        try {
            eCardNumber = now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataPerson.isNew(eCardNumber);
        DataBase.stop();
        return a;
    }

}
