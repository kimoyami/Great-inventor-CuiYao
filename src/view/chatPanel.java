package view;
import srv.Client;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class chatPanel {
    private JLabel a1 = new JLabel("联系人（ECard）");
    private JTextField username = new JTextField();
    private JButton okbtn = new JButton("确认");
    private JButton cancelbtn = new JButton("取消");
    public chatPanel(mainViewTest mainViewTest){
        mainViewTest.chatPanel.setLayout(new BorderLayout());//括号里应该是null还是borderlayout？


        //按钮部分//
        okbtn.setBounds(250,600,50,60);
        cancelbtn.setBounds(400,600,50,60);
        okbtn.setMargin(new Insets(0,0,0,0));
        mainViewTest.chatPanel.add(okbtn);
        cancelbtn.setMargin(new Insets(0,0,0,0));
        mainViewTest.chatPanel.add(cancelbtn);

        //title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("Chatting with whom"));
        mainViewTest.chatPanel.add(titlePanel,"North");

        //输入
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        a1.setBounds(50, 20, 100, 20);
        fieldPanel.add(a1);
        username.setBounds(200, 20, 120, 20);
        fieldPanel.add(username);
        mainViewTest.chatPanel.add(fieldPanel, "Center");
        listerner();
    }
    public void listerner() {
        //确认按下去获取
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // String uname = username.getText();
                //String res = null;
                /*int ans = Client.exist(uname);
                if (ans == 0) {
                    new chatbox();//聊天框（还没写）
                    Client.eCardNumber = uname;
                    // new addMessage();
                }
                else {
                    if (ans == -1) res = "用户异常！";
                    else if (ans == 1) res = "用户不存在！";
                    else if (ans == 2) res = "用户存在但未审核！";
                    else res = "错误！";
                    JOptionPane.showMessageDialog(null, res, null, JOptionPane.QUESTION_MESSAGE);
                }*/
            }
        });
        //取消按下去清空
        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
            }
        });
    }

}
