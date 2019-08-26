package dao;

import srv.person.Person;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DataPerson {
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
            SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
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

    public static void main(String args[]){
        DataBase.start();
        Date now = new Date(2018 - 1900, Calendar.MARCH,1);
        Person person = new Person("ryh", "213171642", 16, "男", now, "cq", "CS", "梅园", "student");
        System.out.println(insert(person));
        System.out.println(delete("213171645"));
        DataBase.stop();
    }
}
