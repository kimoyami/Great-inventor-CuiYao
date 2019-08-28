//author：@miaosenTnT
package view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookPanel {
    private JTextField findBook = new JTextField();
    private  JButton findbtn=new JButton("查询");
    private  JButton borrowbtn = new JButton("借书");
    private  JButton returnbtn = new JButton("归还");
    private JTable table = new JTable();
    private String bookID;
    private String bookName;
    private String bookPub;
    private boolean status;
    private String Category;
    private String bookEdit;
    public bookPanel(mainViewTest mainViewTest){
        mainViewTest.cardPanel.setLayout(new BorderLayout());
        mainViewTest.bookPanel.setLayout(null);


        //按钮部分//
        ImageIcon icon = new ImageIcon("src/view/image/search.png");
        findBook.setBounds(150,100,300,20);
        findbtn.setBounds(500,100,100,20);
        findbtn.setMargin(new Insets(0,0,0,0));

        findbtn.setIcon(icon);

        borrowbtn.setBounds(250,500,50,60);
        returnbtn.setBounds(400,500,50,60);
        borrowbtn.setMargin(new Insets(0,0,0,0));
        returnbtn.setMargin(new Insets(0,0,0,0));
        mainViewTest.bookPanel.add(borrowbtn);
        mainViewTest.bookPanel.add(returnbtn);
        mainViewTest.bookPanel.add(findbtn);
        mainViewTest.bookPanel.add(findBook);

        //表格
        final String [] columnNames = new String[]{"书名","编号","出版单位","分类","作者","是否可以借出"}  ;
        final String [][] book = new String[][]{{"西游记","1","ryh","名著","吴承恩","是"},{"1","2","3","4","5","6"}};
        AbstractTableModel tableModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return book.length;
            }

            @Override//返回一共有多少列
            public int getColumnCount() {
                return  columnNames.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return book[rowIndex][columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                return columnNames[column];
            }
        };
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50,200,500,200);
        mainViewTest.bookPanel.add(sp);


        mainViewTest.cardPanel.add(mainViewTest.bookPanel);


        //监听
        borrowbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        returnbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
       findbtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

           }
       });

    }
    public String getBookID(){
        return bookID;
    }
    public String getBookPub(){

        return  bookPub;
    }
    public  String getBookName(){
        return bookName;
    }
    public String getCategory(){
        return  Category;
    }
    public boolean getStatus(){
        return status;
    }
    public String getBookEdit(){
        return bookEdit;
    }
}
