/*
Arthor: kimoyami
 */

package srv.client;

import srv.person.Person;

public class PersonInfo {
    public static int update(Person person){
        Client.run();
        try {
            Client.cout.writeInt(10);
            Client.cout.flush();
            Client.cout.writeObject(person);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -3;
        }
    }

    public static Person query(String eCardNumber){
        Client.run();
        Person person = null;
        try {
            Client.cout.writeInt(11);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            person = (Person)Client.cin.readObject();
            Client.stop();
        }catch (Exception e){
            Client.stop();
        }
        return person;
    }

    public static void main(String args[]){
        Person person = query("213171645");
        if(person == null) System.out.println("pass");
    }
}
