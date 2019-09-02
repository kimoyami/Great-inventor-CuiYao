package srv.person;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    static ObjectMapper mapper = new ObjectMapper();
    /**
     * 学生信息
     * academy学院
     * dormitory宿舍
     */

    private static final long serialVersionUeCardNumber = 2963524741253039910L;
    private String name;
    private String eCardNumber;
    private int age;
    private String gender;
    private Date birthday;
    private String birthplace;
    private String academy;
    private String dormitory;
    private String state;

    public Person(){
        name = "";
        eCardNumber = "";
        age = 0;
        gender = "";
        birthday = new Date();
        birthplace = "";
        academy = "";
        dormitory = "";
        state = "";
    }


    public Person(String name, String eCardNumber, String gender, Date birthday,
           String birthplace, String academy, String dormitory, String state) {
        this.name = name;
        this.eCardNumber = eCardNumber;
        this.gender = gender;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.state = state;
        this.academy = academy;
        this.dormitory = dormitory;
        this.age = new Date().getYear() - this.birthday.getYear();
    }


    public String getName() {
        return this.name;
    }

    public String geteCardNumber() {
        return this.eCardNumber;
    }

    public int getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public String getAcademy() {
        return this.academy;
    }

    public String getDormitory() {
        return this.dormitory;
    }

    public String getState() {
        return this.state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void seteCardNumber(String eCardNumber) {
        this.eCardNumber = eCardNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.age = new Date().getYear() - birthday.getYear();
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static String getString(Person person){
        String res = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            res = mapper.writeValueAsString(person);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

}

