package view;
import srv.client.Client;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.nio.charset.*;
import java.text.*;

public class chatbox {
    private JFrame jFrame = new JFrame("聊天框");
    private Container c = jFrame.getContentPane();
    //创建发送按钮
    JButton btnSend = new JButton("发送");
    //创建清除按钮
    JButton btnClear = new JButton("清屏");
    //创建退出按钮
    JButton btnExit = new JButton("退出");
    //创建文本输入框, 参数分别为行数和列数
    JTextArea jtaSay = new JTextArea();
    //创建聊天消息框
    JTextArea jtaChat = new JTextArea();
    //创建聊天消息框的滚动窗
    JScrollPane jspChat = new JScrollPane(jtaChat);

    public chatbox(){
        //设置窗体的位置及大小
        jFrame.setBounds(600, 200, 700, 700);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        listerner();
        //设置窗体可见
        jFrame.setVisible(true);
    }
    public void init(){
        //title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("Chatting Box"));
        c.add(titlePanel,"North");

        //Button
        /*btnSend.setBounds(20, 600, 100, 60);
        btnClear.setBounds(140, 600, 100, 60);
        btnExit.setBounds(260, 600, 100, 60);
        c.add(btnSend);
        c.add(btnClear);
        c.add(btnExit);*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnSend);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);
        c.add(buttonPanel, "South");

        //输入文本框
        // 设置文本输入框大小和位置
        jtaSay.setBounds(20, 460, 360, 120);
        //设置文本输入框字体
        jtaSay.setFont(new Font("楷体", Font.BOLD, 16));
        //添加文本输入框
        c.add(jtaSay);

        //聊天框
        //聊天消息框自动换行
        jtaChat.setLineWrap(true);
        //聊天框不可编辑，只用来显示
        jtaChat.setEditable(false);
        //设置聊天框字体
        jtaChat.setFont(new Font("楷体", Font.BOLD, 16));
        //设置滚动窗的水平滚动条属性:不出现
        jspChat.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //设置滚动窗的垂直滚动条属性:需要时自动出现
        jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //设置滚动窗大小和位置
        jspChat.setBounds(20, 20, 360, 400);
        //添加聊天窗口的滚动窗
        c.add(jspChat);


    }
    public void listerner(){

    }
}
