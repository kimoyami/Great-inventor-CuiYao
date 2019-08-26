package view;

import javax.swing.*;
import java.awt.*;

public class bookPanel {
    private  JButton borrowbtn = new JButton("借书");
    private  JButton returnbtn = new JButton("归还");
    public bookPanel(mainViewTest mainViewTest){
        mainViewTest.cardPanel.setLayout(new BorderLayout());
        mainViewTest.bookPanel.setLayout(null);


        //按钮部分//
     borrowbtn.setBounds(250,600,50,60);
     returnbtn.setBounds(400,600,50,60);
borrowbtn.setMargin(new Insets(0,0,0,0));
        mainViewTest.bookPanel.add(borrowbtn);
        mainViewTest.bookPanel.add(returnbtn);
returnbtn.setMargin(new Insets(0,0,0,0));
        mainViewTest.cardPanel.add(mainViewTest.bookPanel);

    }
}
