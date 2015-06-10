package modular.cic.HelperComponents;

/**
 * Created by matthew on 6/10/15.
 * Purpose: Basic helper class for mundane tasks (such as killing the app)
 */
public class App {
    public static void kill(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
