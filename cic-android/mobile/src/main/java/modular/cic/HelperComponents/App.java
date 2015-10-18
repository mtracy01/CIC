package modular.cic.HelperComponents;

import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by matthew on 6/10/15.
 * Purpose: Basic helper class for mundane tasks (such as killing the app)
 */
public class App {
    public static void kill() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    public static boolean first = false;
    public static ArrayList<ParseObject> devices=new ArrayList<>();
}
