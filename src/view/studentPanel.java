package view;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import  java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

public class studentPanel {
    private JLabel a1 = new JLabel("一卡通");
    private JLabel a2 = new JLabel("姓 名");
    private JLabel a8 = new JLabel("出生日期");
    private JLabel a3 = new JLabel("密 码");
    private JLabel a5 = new JLabel ("身 份");
    private JLabel a6 = new JLabel("密码确认");
    private JLabel a4 = new JLabel("性 别");
    private JLabel a7 = new JLabel("年龄");
    private String  ID ;
    private String Name ;
    private String pwd ;
    private String Repwd;
    private String Sex;
    private String item;//身份
    private Integer age;
    private int bornYear;
    private int bornMonth;
    private int bornDay;
    private JButton logbtn= new JButton("编辑");
    private JTextField JTa1 =new JTextField();
    private JTextField JTa2 =new JTextField();
    private JPasswordField JTa3 =new JPasswordField();
    private JPasswordField ReJTa3 = new JPasswordField();
    private JTextField JTa7 = new JTextField();
    private JComboBox comboBox = new JComboBox();
    private JComboBox comboBox2 =new JComboBox();
    final JXDatePicker datepick = new JXDatePicker();
    public studentPanel(mainViewTest mainViewTest)
    {   mainViewTest.cardPanel.setLayout(new BorderLayout());
        mainViewTest.studentPanel.setLayout(null);
        mainViewTest.studentPanel.add(a1);
        mainViewTest.studentPanel.add(JTa1);
        mainViewTest.studentPanel.add(a2);
        mainViewTest.studentPanel.add(JTa2);
        mainViewTest.studentPanel.add(a7);
        mainViewTest.studentPanel.add(JTa7);
        mainViewTest.studentPanel.add(a8);
        mainViewTest.studentPanel.add(datepick);
        mainViewTest.studentPanel.add(a3);
        mainViewTest.studentPanel.add(JTa3);
        mainViewTest.studentPanel.add(a6);
        mainViewTest.studentPanel.add(ReJTa3);
        mainViewTest.studentPanel.add(a4);
        mainViewTest.studentPanel.add(comboBox2);
        mainViewTest.studentPanel.add(a5);
        mainViewTest.studentPanel.add(comboBox);
        a1.setBounds(50, 20, 50, 20);
        JTa1.setBounds(120,20,120,20);
        a2.setBounds(50, 60, 50, 20);
        JTa2.setBounds(120,60,120,20);
        a7.setBounds(50,100,50,20);
        JTa7.setBounds(120,100,120,20);
        a8.setBounds(50,140,60,20);
        a3.setBounds(50,180,50,20);
        JTa3.setBounds(120,180,120,20);
        a6.setBounds(50,220,60,20);
        ReJTa3.setBounds(120,220,120,20);
        a4.setBounds(50,260,50,20);
        comboBox2.setBounds(120,260,100,20);
        a5.setBounds(50,300,50,20);
        comboBox.setBounds(120,300,100,20);
        //日期部分
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        datepick.setDate(date);
        datepick.setBounds(120,140,120,20);
        mainViewTest.cardPanel.add(mainViewTest.studentPanel);

        comboBox.addItem("无");
        comboBox.addItem("老师");
        comboBox.addItem("学生");
        comboBox2.addItem("无");
        comboBox2.addItem("男");
        comboBox2.addItem("女");

        //按钮

        mainViewTest.studentPanel.add(logbtn);
        logbtn.setBounds(350,600,50,60);
        logbtn.setMargin(new Insets(0,0,0,0));

        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int stateChange = e.getStateChange();
                Sex = e.getItem().toString();//返回性别
            }
        });
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int stateChange = e.getStateChange();
                item = e.getItem().toString();//返回学生或老师

            }
        });
        logbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getID());
                System.out.println(getName());
                System.out.println(getAge());
                System.out.println(getpwd());
                System.out.println(getRepwd());
                System.out.println(getSex());
                System.out.println(getItem());
                System.out.println(getBornYear());
                System.out.println(getBornMonth());
                System.out.println(getBornDay());
            }
        });



    }
    public String getID(){
        ID= JTa1.getText();
        return ID;
    }
    public String getpwd(){
        pwd = JTa3.getText();
        return pwd;
    }
    public String getRepwd(){
        Repwd =ReJTa3.getText();
        return Repwd;
    }
    public String getSex(){
        return Sex;
    }
    public String getName(){
        Name = JTa2.getText();
        return Name;
    }
    public String getItem(){
        return item;
    }
    public int getAge(){
        age = Integer.parseInt((JTa7.getText().equals("")||JTa7.getText()==null)?"0":JTa7.getText());
        return age;
    }
    public int getBornYear(){
        bornYear = datepick.getDate().getYear()+1900;
        return bornYear;

    }
    public int getBornMonth(){
        bornMonth = datepick.getDate().getMonth()+1;
        return bornMonth;
    }
    public int getBornDay(){
        bornDay=datepick.getDate().getDate();
        return bornDay;

    }
}
