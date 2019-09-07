package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class ImagePreview extends JPanel implements PropertyChangeListener {
    private JFileChooser jfc;
    private Image img;

    public ImagePreview(JFileChooser jfc) {
        this.jfc = jfc;
        Dimension sz = new Dimension(300, 200);
        setPreferredSize(sz);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        try {
            File file = jfc.getSelectedFile();
            updateImage(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateImage(File file) throws IOException {
        if (file == null) {
            return;
        }
        img = ImageIO.read(file);
        repaint();
    }

    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }
        else {
            g.setColor(Color.black);
            g.drawString("Not an image", 30, 100);
        }
    }
}
