/*
 * Copyright: kimoyami
 */
package view;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.events.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.Date;

import com.teamdev.jxbrowser.chromium.ba;
import srv.client.*;
import srv.message.Message;
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
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setSize(3 * screenSize.width / 4, 27 * screenSize.width/ 64);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        browser.loadURL("C:\\Users\\崔峣\\Desktop\\test\\Great-inventor-CuiYao\\html\\index.html");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.createImage("F:\\GitHub\\Great-inventor-CuiYao\\html\\img\\xh.jpg");
        frame.setIconImage(image);
        browser.addLoadListener(new LoadListener() {
            @Override
            public void onStartLoadingFrame(StartLoadingEvent startLoadingEvent) {

            }

            @Override
            public void onProvisionalLoadingFrame(ProvisionalLoadingEvent provisionalLoadingEvent) {

            }

            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent finishLoadingEvent) {

            }

            @Override
            public void onFailLoadingFrame(FailLoadingEvent failLoadingEvent) {

            }

            @Override
            public void onDocumentLoadedInFrame(FrameLoadEvent frameLoadEvent) {

            }

            @Override
            public void onDocumentLoadedInMainFrame(LoadEvent loadEvent) {
                browser.setZoomLevel(-2.2);
            }
        });
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
                MessageTrans messagetrans = new MessageTrans();
                Message message = new Message();
                Bank_Info bankinfo=new Bank_Info();
                GoodInfo goodinfo=new GoodInfo();
                BookInfo bookinfo=new BookInfo();

                window.asObject().setProperty("personinfo", personinfo);
                window.asObject().setProperty("person", person);
                window.asObject().setProperty("client", client);
                window.asObject().setProperty("login", login);
                window.asObject().setProperty("head", head);
                window.asObject().setProperty("mainview", mainview);
                window.asObject().setProperty("messagetrans", messagetrans);
                window.asObject().setProperty("message", message);
                window.asObject().setProperty("bankinfo",bankinfo);
                window.asObject().setProperty("goodinfo",goodinfo);
                window.asObject().setProperty("bookinfo",bookinfo);

            }
        });
    }

    public static void choose(String eCardNumber) {
        JFileChooser chooser = new JFileChooser();
        ImagePreview imagePreview = new ImagePreview(chooser);
        chooser.addPropertyChangeListener(imagePreview);
        chooser.setAccessory(imagePreview);
        FileFilter filter = new FileFilter() {
            @Override
                    public boolean accept(File f) {
                return f.getPath().endsWith("jpg") || f.getPath().endsWith("JPG")
                        || f.getPath().endsWith("png") || f.getPath().endsWith("PNG")
                        || f.getPath().endsWith("gif") || f.getPath().endsWith("GIF")
                        || f.getPath().endsWith("jpeg") || f.getPath().endsWith("JPEG")
                        || f.getPath().endsWith("bmp") || f.getPath().endsWith("BMP")
                        || !f.getPath().contains(".");
            }

            @Override
            public String getDescription() {
                return "image";
            }
        };
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int flag = chooser.showOpenDialog(frame);
        if (flag == JFileChooser.APPROVE_OPTION) {
            int res = Head.update(eCardNumber, chooser.getSelectedFile().getPath());
            if (res == 1) {
                browser.executeJavaScript("alert('上传成功！')");
            } else browser.executeJavaScript("alert('上传失败！')");
        }
    }
}