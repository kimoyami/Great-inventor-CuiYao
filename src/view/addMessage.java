//author：@miaosenTnT
package view;
import javax.swing.*;
import  java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import org.jdesktop.swingx.JXDatePicker;
import srv.client.Client;
import srv.client.PersonInfo;
import srv.person.Person;

public class addMessage  extends JFrame{
    private JFrame jFrame = new JFrame("请补充信息");
    private Container c = jFrame.getContentPane();
    private JLabel birthPlace = new JLabel("出生地");
    private JLabel dormitory = new JLabel("宿舍");
    private JLabel birthTime = new JLabel("出生日期");
    private JLabel identity = new JLabel("身份");
    private JLabel academy = new JLabel("学院");
    private JComboBox comboBox = new JComboBox();
    private JTextField birthPlaceText = new JTextField();
    private JTextField dormitoryText = new JTextField();
    final JXDatePicker datepick = new JXDatePicker();
    private  JTextField academyText = new JTextField();
    private JButton logbtn = new JButton("完成");
    private String aca;//学院
    private String Identity;
    private String dor;//宿舍
    private String birthplace;

    public  addMessage(){
        jFrame.setBounds(600,200,300,440);
        c.setLayout(new BorderLayout());
        jFrame.setVisible(true);
        init();
        listener();


    }
    public void  init(){
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("请补充信息"));
        c.add(titlePanel,"North");

        Date date = new Date();
        datepick.setDate(date);


        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        birthPlace.setBounds(50,20,60,20);
        birthPlaceText.setBounds(120,20,120,20);
        birthTime.setBounds(50,60,50,20);
        datepick.setBounds(120,60,120,20);
        dormitory.setBounds(50,100,50,20);
        dormitoryText.setBounds(120,100,120,20);
        academy.setBounds(50,140,50,20);
        academyText.setBounds(120,140,120,20);
        identity.setBounds(50,180,50,20);
        comboBox.setBounds(120,180,120,20);

        comboBox.addItem("无");
        comboBox.addItem("老师");
        comboBox.addItem("学生");
        fieldPanel.add(birthPlace);
        fieldPanel.add(birthPlaceText);
        fieldPanel.add(birthTime);
        fieldPanel.add(datepick);
        fieldPanel.add(dormitory);
        fieldPanel.add(dormitoryText);
        fieldPanel.add(academy);
        fieldPanel.add(academyText);
        fieldPanel.add(identity);
        fieldPanel.add(comboBox);
        c.add(fieldPanel,"Center");


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(logbtn);
        c.add(buttonPanel,"South");




    }

    public void listener(){


            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    int stateChange = e.getStateChange();
                    Identity = e.getItem().toString();//返回学生或老师

                }
            });
            logbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String res = "";
                    if(getBirthplace().equals("")) res = "请输入出生地！";
                    else if(getAca().equals("")) res = "请输入学院！";
                    else if(getDor().equals("")) res = "请输入宿舍！";
                    else {
                        Person person = PersonInfo.query(Client.eCardNumber);
                        person.setBirthday(getDate());
                        person.setBirthplace(getBirthplace());
                        person.setAcademy(getAca());
                        person.setDormitory(getDor());
                        PersonInfo.update(person);
                        new mainViewTest();
                        jFrame.dispose();
                    }
                    if(!res.equals(""))JOptionPane.showMessageDialog(null, res, null, JOptionPane.QUESTION_MESSAGE);
                }
            });//完成按钮监听事件


    }
    public String getBirthplace(){
        birthplace = birthPlaceText.getText();
        return birthplace;
    }

    public String getIdentity(){
        return Identity ;
    }

    public  String getAca(){
        aca = academyText.getText();
        return  aca;
    }

    public  String getDor(){
        dor = dormitoryText.getText();
        return  dor;
    }

    public Date getDate(){
        return datepick.getDate();
    }


}

