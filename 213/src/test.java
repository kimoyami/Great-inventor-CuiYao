import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 500;
        int height = 500;
        frame.setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
    }

    private JButton Button;
    private JPanel panel1;
}
