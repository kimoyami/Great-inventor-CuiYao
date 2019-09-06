package view;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.events.ScriptContextAdapter;
import com.teamdev.jxbrowser.chromium.events.ScriptContextEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

import com.teamdev.jxbrowser.chromium.ba;
import srv.client.*;
import srv.person.Person;

public class MainView {
    static {
        try {
            Field e = ba.class.getDeclaredField("e");
            e.setAccessible(true);
            Field f = ba.class.getDeclaredField("f");
            f.setAccessible(true);
            Field modifersField = Field.class.getDeclaredField("modifiers");
            modifersField.setAccessible(true);
            modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
            modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            e.set(null, new BigInteger("1"));
            f.set(null, new BigInteger("1"));
            modifersField.setAccessible(false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static JFrame frame;
    public static final Browser browser = new Browser();

    public static void main(String args[]) {
        BrowserView view = new BrowserView(browser);
        frame = new JFrame("东南大学校园管理系统");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(view, BorderLayout.CENTER);
        frame.setLocation(50, 50);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        browser.loadURL("F:\\GitHub\\Great-inventor-CuiYao\\html\\index.html");
        Toolkit kit =Toolkit.getDefaultToolkit();
        Image image = kit.createImage("F:\\GitHub\\Great-inventor-CuiYao\\html\\img\\xh.jpg");
        frame.setIconImage(image);
        browser.addScriptContextListener(new ScriptContextAdapter() {
            @Override
            public void onScriptContextCreated(ScriptContextEvent event) {
                Browser browser = event.getBrowser();
                JSValue window = browser.executeJavaScriptAndReturnValue("window");

                Person person = new Person();
                PersonInfo personinfo = new PersonInfo();
                Client client = new Client();
                Login login = new Login();
                Head head = new Head();
                MainView mainview = new MainView();

                window.asObject().setProperty("personinfo", personinfo);
                window.asObject().setProperty("person", person);
                window.asObject().setProperty("client", client);
                window.asObject().setProperty("login", login);
                window.asObject().setProperty("head", head);
                window.asObject().setProperty("mainview", mainview);
            }
        });
    }

    public static void choose(String eCardNumber){
        JFileChooser chooser = new JFileChooser();
        ImagePreview imagePreview = new ImagePreview(chooser);
        chooser.addPropertyChangeListener(imagePreview);
        chooser.setAccessory(imagePreview);
        int flag = chooser.showOpenDialog(frame);
        if (flag == JFileChooser.APPROVE_OPTION){
            int res = Head.update(eCardNumber, chooser.getSelectedFile().getPath());
            if(res == 1) {
                browser.executeJavaScript("alert('上传成功！')");
            }
            else browser.executeJavaScript("alert('上传失败！')");
        }
    }

}