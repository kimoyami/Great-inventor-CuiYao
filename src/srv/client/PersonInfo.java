/*
Arthor: kimoyami
 */
// start: 10
package srv.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import srv.person.Person;

public class PersonInfo {
    private static final int STARTPOS = 10;
    public static int insert(Person person){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeObject(person);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static int delete(String eCardNumber){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static Person query(String eCardNumber){
        Client.run();
        Person person = null;
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            person = (Person)Client.cin.readObject();
            Client.stop();
        }catch (Exception e){
            Client.stop();
        }
        return person;
    }

    public static String queryStr(String eCardNumber){
        Person person = query(eCardNumber);
        return Person.getString(person);
    }

    public static int update(Person person){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.writeObject(person);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static int update(String s){
        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person();
        try {
            person = mapper.readValue(s, Person.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return update(person);
    }

    public static int isNew(String eCardNumber){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 5);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static void main(String args[]){

    }
}
