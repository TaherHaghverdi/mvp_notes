package ir.coursio.notes;

import android.app.Application;
import android.content.Context;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

public class App extends Application {

    private static App appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public static App getsInstance() {
        return appInstance;
    }

    public static Context getAppContext() {
        return appInstance.getApplicationContext();
    }
}
