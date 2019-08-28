/*
 * Copyright: kimoyami
 */

package view;

import srv.client.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class solvePanel {
    private static Vector<String> colName = new Vector<>();
    private static Vector<Vector<Object>> data = new Vector<Vector<Object> >();
    private static JTable jt;
    private static JButton confirm = new JButton("确认");

    public void getData(mainViewTest mainViewTest){
        data = Login.getAll();
        for(int i = 0; i < data.size(); i++){
            data.elementAt(i).add(new Boolean(false));
        }
        jt = new JTable(data, colName);
        jt.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        jt.getColumnModel().getColumn(4).setCellRenderer(new MyTableRenderer());
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jt.setDefaultRenderer(Object.class, r);
        JScrollPane scrollPane = new JScrollPane(jt);
        scrollPane.setBounds(0, 0, 600, 1000);
        mainViewTest.solvePanel.add(scrollPane);
    }

    public solvePanel(final mainViewTest mainViewTest){
        mainViewTest.cardPanel.setLayout(new BorderLayout());
        mainViewTest.solvePanel.setLayout(null);
        confirm.setBounds(100, 600, 40, 40);
        confirm.setMargin(new Insets(0, 0, 0, 0));
        mainViewTest.solvePanel.add(confirm);

        colName.add("姓名");
        colName.add("一卡 通");
        colName.add("性别");
        colName.add("身份");
        colName.add("通过/不通过");
        getData(mainViewTest);


        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    int col = jt.columnAtPoint(e.getPoint());
                    int row = jt.rowAtPoint(e.getPoint());
                    if(col == 4){
                        if(jt.getValueAt(row, col) == null) jt.setValueAt(false, row, col);
                        if(((Boolean)jt.getValueAt(row, col)).booleanValue()) {
                            System.out.println(row + " " + col);
                            jt.setValueAt(false, row, col);
                        }
                        else jt.setValueAt(true, row, col);
                    }
                }
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < data.size(); i++){
                    if(((Boolean) data.elementAt(i).elementAt(4))){
                        Login.solve((String) data.elementAt(i).elementAt(1));
                    }
                    else Login.delete((String)data.elementAt(i).elementAt(1));
                }
                getData(mainViewTest);
            }
        });
    }

    public class MyTableRenderer extends JCheckBox implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row,
                                                       int column ) {
            Boolean b = (Boolean) value;
            this.setSelected(b.booleanValue());
            return this;
        }
    }
}
