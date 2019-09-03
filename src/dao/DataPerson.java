/*
Arthor: kimoyami
 */
package dao;

import srv.person.Person;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DataPerson {
    private static SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");

    public static int exist(String eCardNumber){
        try {
            String sql = "select * from personinfo where ecardnumber = '"+eCardNumber+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) return 0;
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    public static int insert(Person person){
        try {
            if(exist(person.geteCardNumber()) == 1) return 0;
            String date = trans.format(person.getBirthday());
            System.out.println(date);
            String sql = "insert into personinfo(username, ecardnumber, age, sex, birthday, birthplace, academy, dormitory, state) values('"+person.getName()+"', '"+person.geteCardNumber()+"', "+person.getAge()+", '"+person.getGender()+"', #"+date+"#, '"+person.getBirthplace()+"', '"+person.getAcademy()+"', '"+person.getDormitory()+"', '"+person.getState()+"')";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    public static int delete(String eCardNumber){
        try {
            if(exist(eCardNumber) == 0) return 0;
            String sql = "delete from personinfo where ecardnumber = '"+eCardNumber+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    public static Person query(String eCardNumber){
        Person person = new Person();
        try{
            String sql = "select * from personinfo where ecardnumber = '"+eCardNumber+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(rs.next()){
                person = new Person(rs.getString(2), rs.getString(3), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return person;
    }

    public static int update(Person person){
        if(exist(person.geteCardNumber()) == 0) return 0;
        try{
            String sql = "update personinfo set username = '"+person.getName()+"', age = "+person.getAge()+", sex = '"+person.getGender()+"', birthday = #"+trans.format(person.getBirthday())+"#, birthplace = '"+person.getBirthplace()+"', academy = '"+person.getAcademy()+"', dormitory = '"+person.getDormitory()+"', state = '"+person.getState()+"' where ecardnumber = '"+person.geteCardNumber()+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    public static int isNew(String eCardNumber){
        try {
            String sql = "select academy from personinfo where ecardnumber = '"+eCardNumber+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) return -2;
            if(rs.getString(1) == null) return 1;
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    public static void main(String args[]){
        DataBase.start();
        Date now = new Date( 1998 - 1900, Calendar.MARCH,1);
        Person person = new Person("ryh", "213171642", "男", now, "cq", "CS", "梅园", "student");
     /*   System.out.println(insert(person));
        System.out.println(delete("213171645"));*/
       // System.out.println(isNew("213171645"));
       // first("21314", now, "cq", "CS", "梅园");
       // update(person);
        System.out.println(isNew("213171645"));
        DataBase.stop();
    }
}
