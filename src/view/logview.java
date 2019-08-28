//author：miaosenTnT
package view;

import srv.client.Client;
import srv.client.Login;

import javax.swing.*;
import  java.awt.*;
import java.awt.event.*;


public class logview extends JFrame{
    private JFrame jFrame = new JFrame("注册");
    private Container c = jFrame.getContentPane();
private JLabel a1 = new JLabel("一卡通");
private JLabel a2 = new JLabel("姓 名");
//private JLabel a8 = new JLabel("出生日期");
private JLabel a3 = new JLabel("密 码");
private JLabel a6 = new JLabel("密码确认");
private JLabel a4 = new JLabel("性 别");
private JLabel a5 = new JLabel ("身 份");
//private JLabel a7 = new JLabel("年龄");
private JButton logbtn= new JButton("注册");
private JTextField JTa1 =new JTextField();
private JTextField JTa2 =new JTextField();
private JPasswordField JTa3 =new JPasswordField();
private JPasswordField ReJTa3 = new JPasswordField();
//private JTextField JTa7 = new JTextField();
private JComboBox comboBox = new JComboBox();
private JComboBox comboBox2 =new JComboBox();


   private String  ID ;
    private String Name ;
    private String pwd ;
    private String Repwd;
    private String Sex;
    private String item;
   // private Integer age;
   /* private int bornYear;
    private int bornMonth;
    private int bornDay;*/





   public  logview(){
        jFrame.setBounds(600,200,300,440);
        c.setLayout(new BorderLayout());
        jFrame.setVisible(true);
        init();
       listener();
   /* getID();//返回ID
    getName();//返回姓名
    getpwd();//返回密码
    getSex();//返回性别
    getAge()*/;//返回年龄



}
public  void init(){
    //标题部分//
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new FlowLayout());
    titlePanel.add(new JLabel("注册"));
    c.add(titlePanel,"North");



    //输入部分//
    JPanel fieldPanel = new JPanel();
    fieldPanel.setLayout(null);
    a1.setBounds(50, 20, 50, 20);
   JTa1.setBounds(120,20,120,20);
    a2.setBounds(50, 60, 50, 20);
    JTa2.setBounds(120,60,120,20);

    a3.setBounds(50,100,50,20);
    JTa3.setBounds(120,100,120,20);
    a6.setBounds(50,140,60,20);
    ReJTa3.setBounds(120,140,120,20);
    a4.setBounds(50,180,50,20);
   comboBox2.setBounds(120,180,100,20);
    a5.setBounds(50,220,50,20);
    comboBox.setBounds(120,220,100,20);


    fieldPanel.add(a1);
    fieldPanel.add(JTa1);
    fieldPanel.add(a2);
    fieldPanel.add(JTa2);
    fieldPanel.add(a3);
    fieldPanel.add(JTa3);
    fieldPanel.add(a6);
    fieldPanel.add(ReJTa3);
    fieldPanel.add(a4);
    fieldPanel.add(comboBox2);
    fieldPanel.add(a5);
    fieldPanel.add(comboBox);


    c.add(fieldPanel,"Center");
    //下拉菜单部分
    comboBox.addItem("无");
    comboBox.addItem("老师");
    comboBox.addItem("学生");
    comboBox2.addItem("无");
    comboBox2.addItem("男");
    comboBox2.addItem("女");




//按钮部分//
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(logbtn);
    c.add(buttonPanel,"South");

}
//下拉菜单监听，注册案件监听
public void listener(){
    comboBox.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int stateChange = e.getStateChange();
            item = e.getItem().toString();//返回学生或老师

        }
    });
    comboBox2.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int stateChange = e.getStateChange();
            Sex = e.getItem().toString();//返回性别
        }
    });

logbtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(getpwd() + "   " + getRepwd());
        if(!getRepwd().equals(getpwd())){
            JOptionPane.showMessageDialog(null,"两次输入密码不相等！","提示",JOptionPane.WARNING_MESSAGE);
        }
        else if(getID().equals("")) JOptionPane.showMessageDialog(null,"一卡通不能为空！","提示",JOptionPane.WARNING_MESSAGE);
        else if(getName().equals(""))  JOptionPane.showMessageDialog(null,"姓名不能为空！","提示",JOptionPane.WARNING_MESSAGE);
        //else if(getAge()==0) JOptionPane.showMessageDialog(null,"年龄不能为空！","提示",JOptionPane.WARNING_MESSAGE);
        else if(getpwd().equals("") || getRepwd().equals(""))  JOptionPane.showMessageDialog(null,"密码不能为空！","提示",JOptionPane.WARNING_MESSAGE);
        else if(getSex().equals(""))  JOptionPane.showMessageDialog(null,"性别必须明确！","提示",JOptionPane.WARNING_MESSAGE);
        else if(getItem().equals(""))  JOptionPane.showMessageDialog(null,"身份必须明确！","提示",JOptionPane.WARNING_MESSAGE);
        else{
            int ans = Login.insert(getName(),getpwd(),getID(),getSex(),getItem());
            String res = "";
            if(ans == 0)res = "提交注册成功！";
            else if(ans == 2) res = "该一卡通已注册，正待审核！";
            else if(ans == 1) res = "该一卡通已存在";
            JOptionPane.showMessageDialog(null,res,"提示",JOptionPane.WARNING_MESSAGE);
            if(ans == 0) System.exit(0);
        }
        System.out.println(getID());
        System.out.println(getName());

        System.out.println(getpwd());
        System.out.println(getRepwd());
        System.out.println(getSex());
        System.out.println(getItem());

    }
});
}



//返回各个值
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
/*public int getAge(){
    age = Integer.parseInt((JTa7.getText().equals("")||JTa7.getText()==null)?"0":JTa7.getText());
    return age;
}*/

}