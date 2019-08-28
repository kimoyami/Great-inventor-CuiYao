/*
Arthor: kimoyami
 */

package srv.server;

import dao.DataPerson;
import srv.person.Person;

public class PersonInfo {

    public static void run(int op){
        try {
            if (op == 1) {
                ServerThread.cout.writeInt(update());
                ServerThread.cout.flush();
            }
            if(op == 2){
                ServerThread.cout.writeObject(query());
                ServerThread.cout.flush();
            }
        }catch (Exception e){
            return;
        }
    }

    public static int update(){
        Person person;
        try{
            person = (Person)ServerThread.cin.readObject();
        }catch (Exception e){
            return -3;
        }
        return DataPerson.update(person);
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

}
