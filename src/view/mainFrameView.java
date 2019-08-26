package view;
import javax.swing.*;
import  java.awt.*;
import java.awt.event.*;
public class mainFrameView extends JFrame {

    private JFrame jFrame = new JFrame("主界面");
    private Container c = jFrame.getContentPane();
    private JPanel cardPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private JPanel bookPanel = new JPanel();
    private JPanel bankPanel = new JPanel();
    private CardLayout card = new CardLayout();
    private JButton bookbtn = new JButton("图书管理");
    private JButton bankbtn = new JButton("钱包管理");

    public mainFrameView() {

        jFrame.setBounds(600, 200, 800, 700);
        cardPanel.setLayout(card);
        c.setLayout(new BorderLayout());


        init();

        jFrame.setVisible(true);

    }

    public void init() {


        c.add(cardPanel, BorderLayout.CENTER);
        c.add(controlPanel, BorderLayout.WEST);

        //按钮
       /* controlPanel.add(bookbtn);
        controlPanel.add(bankbtn);*/
        Box b1 = Box.createVerticalBox();
        b1.add(Box.createRigidArea(new Dimension(0, 0)));
        b1.add(bookbtn);
        b1.add(bankbtn);
        controlPanel.add(b1);

        //卡片添加
        cardPanel.add(bookPanel, "card1");
        cardPanel.add(bankPanel, "card2");
        bookPanel.add(new JButton("NMSL"));
        bankPanel.add(new JButton("zby"));


        //不知道为什么无法设置为类
        final CardLayout cl = (CardLayout) (cardPanel.getLayout());


        //按钮响应
        bookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cardPanel, "card1");
            }
        });
        bankbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cardPanel, "card2");
            }
        });
    }
}
