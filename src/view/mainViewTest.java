package view;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import  java.awt.*;
import java.awt.event.*;
public class mainViewTest  extends JFrame {
    private JFrame jFrame = new JFrame("主界面");
    private Container c = jFrame.getContentPane();
    public JPanel cardPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    public JPanel bookPanel = new JPanel();
    public JPanel bankPanel = new JPanel();
    public JPanel studentPanel = new JPanel();
    public JPanel classPanel = new JPanel();
    public JPanel shopPanel = new JPanel();
    public JPanel chatPanel = new JPanel();

    private JButton bookbtn = new JButton("图书管理");
    private JButton bankbtn = new JButton("钱包管理");
    private JButton studentbtn = new JButton("信息管理");
    private JButton classbtn = new JButton("选课系统");
    private JButton shopbtn = new JButton("商店系统");
    private JButton chatbtn = new JButton("聊天系统");
    private JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanel, cardPanel);

    public mainViewTest() {
        jFrame.setBounds(600, 200, 800, 700);
        c.setLayout(new BorderLayout());
        init();
        listener();
        jFrame.setVisible(true);
        student();
        book();

    }

    public void init() {
        //界面设置
        sp.setDividerLocation(80);
        controlPanel.add(bookbtn);
        controlPanel.add(bankbtn);
        controlPanel.add(studentbtn);
        controlPanel.add(classbtn);
        controlPanel.add(shopbtn);
        controlPanel.add(chatbtn);
        //初始化界面显示，首先显示bookpanel
        bookPanel.setVisible(true);
        bankPanel.setVisible(false);
        studentPanel.setVisible(false);
        classPanel.setVisible(false);
        shopPanel.setVisible(false);
        chatPanel.setVisible(false);

        bankPanel.add(new JButton("nmsl"));


        c.add(sp);


    }

    public void listener() {
        bookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(bookPanel);
                bookPanel.setVisible(true);
                bankPanel.setVisible(false);
                studentPanel.setVisible(false);
                classPanel.setVisible(false);
                shopPanel.setVisible(false);
                chatPanel.setVisible(false);


            }
        });
        bankbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(bankPanel);
                bookPanel.setVisible(false);
                bankPanel.setVisible(true);
                studentPanel.setVisible(false);
                classPanel.setVisible(false);
                shopPanel.setVisible(false);
                chatPanel.setVisible(false);

            }
        });
        studentbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(studentPanel);
                bookPanel.setVisible(false);
                bankPanel.setVisible(false);
                studentPanel.setVisible(true);
                classPanel.setVisible(false);
                shopPanel.setVisible(false);
                chatPanel.setVisible(false);

            }
        });
        classbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(classPanel);
                bookPanel.setVisible(false);
                bankPanel.setVisible(false);
                studentPanel.setVisible(false);
                classPanel.setVisible(true);
                shopPanel.setVisible(false);
                chatPanel.setVisible(false);

            }
        });
        shopbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(shopPanel);
                bookPanel.setVisible(false);
                bankPanel.setVisible(false);
                studentPanel.setVisible(false);
                classPanel.setVisible(false);
                shopPanel.setVisible(true);
                chatPanel.setVisible(false);

            }
        });
        chatbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(chatPanel);
                bookPanel.setVisible(false);
                bankPanel.setVisible(false);
                studentPanel.setVisible(false);
                classPanel.setVisible(false);
                shopPanel.setVisible(false);
                chatPanel.setVisible(true);

            }
        });
    }

    public void student() {
        new studentPanel(this);
    }

    public void book() {
        new bookPanel(this);
    }

}