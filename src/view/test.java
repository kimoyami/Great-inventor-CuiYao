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
import java.util.Date;

import com.teamdev.jxbrowser.chromium.ba;
import srv.client.Client;
import srv.client.Login;
import srv.client.PersonInfo;
import srv.person.Person;


/**
 * The sample demonstrates how to create Browser instance, embed it,
 * load HTML content from string, and display it.
 */
public class test {
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
    public static void main(String args[]) {
        final Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        JFrame frame = new JFrame("JxBrowser");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(view, BorderLayout.CENTER);
        frame.setLocation(50, 50);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       /* browser.loadURL("F:\\GitHub\\Great-inventor-CuiYao\\login\\index.html");
        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                super.onFinishLoadingFrame(event);
                JSValue window = browser.executeJavaScriptAndReturnValue("window");
                Login x = new Login();

                window.asObject().setProperty("login", x);
            }
        });*/
        browser.loadURL("F:\\GitHub\\Great-inventor-CuiYao\\src\\view\\student.html");
        browser.addScriptContextListener(new ScriptContextAdapter() {
            @Override
            public void onScriptContextCreated(ScriptContextEvent event) {
                Browser browser = event.getBrowser();
                JSValue window = browser.executeJavaScriptAndReturnValue("window");



                Person person = new Person();


                PersonInfo personinfo = new PersonInfo();

                Date date = new Date();
                Client client = new Client();
                Login login = new Login();

                System.out.println(person.getBirthday());
                window.asObject().setProperty("personinfo", personinfo);
                window.asObject().setProperty("person", person);
                window.asObject().setProperty("birthday", new Date());
                window.asObject().setProperty("Client", client);
                window.asObject().setProperty("login", login);
            }
        });
    }


}